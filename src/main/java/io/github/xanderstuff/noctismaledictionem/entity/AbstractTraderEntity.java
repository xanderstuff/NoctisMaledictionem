package io.github.xanderstuff.noctismaledictionem.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTraderEntity extends PathAwareEntity implements Merchant {
	// This class is similar to Minecraft's MerchantEntity class (which also implements the Merchant interface).
	// However, we can't use MerchantEntity because it has a lot of villager-specific functionality, and Minecraft uses
	// "instanceof MerchantEntity" checks elsewhere, such as zombies targeting any MerchantEntity (normally just villagers and wandering traders).
	// note: the Mojang name for "MerchantEntity" is "AbstractVillager", which explains why it's tied to villagers so much.

	@Nullable
	private PlayerEntity customer;
	@Nullable
	private TradeOfferList offers;
	private static final String TRADE_OFFERS_NBT_KEY = "Offers";

	public AbstractTraderEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public PlayerEntity getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(@Nullable PlayerEntity customer) {
		this.customer = customer;
	}

	public boolean hasCustomer() {
		return customer != null;
	}

	@Override
	public TradeOfferList getOffers() {
		if (offers == null) {
			offers = createTradeOffers();
		}
		return offers;
	}

	protected abstract TradeOfferList createTradeOffers();

	@Override
	public void trade(TradeOffer offer) {
		offer.use();
		ambientSoundChance = -getMinAmbientSoundDelay();

		// xp orb spawning is normally handled in MerchantEntity#afterUsing
//		if (offer.shouldRewardPlayerExperience()) { // note: a TradeOffer has no public method to set shouldRewardPlayerExperience for some unknown reason. It's default true, and normally only changeable via NBT editing
//			int xp = 3 + random.nextInt(4);
//			getWorld().spawnEntity(new ExperienceOrbEntity(getWorld(), getX(), getY() + 0.5, getZ(), xp));
//		}

		onSuccessfulTrade(offer);
	}

	protected void onSuccessfulTrade(TradeOffer offer) {
		// default no-op, but meant to be overridden
		// This method replaces MerchantEntity#afterUsing, but with a better name for clarity.
		// This method could be used for leveling up a villager, for example.
		// The xp orb spawning code originally found in MerchantEntity#afterUsing is moved to trade().
	}

	@Override
	public void onSellingItem(ItemStack itemStack) {
		// This method actually only runs when an item is *attempted* to be sold. itemStack may be empty
		if (!isClient() && ambientSoundChance > -getMinAmbientSoundDelay() + 20) {
			ambientSoundChance = -getMinAmbientSoundDelay();
			if (itemStack.isEmpty()) {
				playSound(getTradeFailedSound(), getSoundVolume(), getSoundPitch());
			} else {
				playSound(getTradeSuccessfulSound(), getSoundVolume(), getSoundPitch());
			}
		}
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		if (hasCustomer() || isBaby() || !isAlive()) {
			return super.interactMob(player, hand);
		}

		if (!isClient() && !getOffers().isEmpty()) {
			setCustomer(player);
			sendOffers(player, getDisplayName(), getLevel());
		}

		return ActionResult.success(isClient()); // note: Mojang's name for ActionResult#success is "sidedSuccess", which explains the isClient argument
	}

	@Override
	public boolean isLeveledMerchant() {
		return false;
	}

	@Override
	public boolean canRefreshTrades() {
		// Oddly, this is one of the few methods in the Merchant interface that actually has a default implementation,
		// and the default is also false. But it's overridden here for consistency, to be clearer
		// to subclasses of AbstractTraderEntity that this is available to be overridden.
		return false;
	}

	public int getLevel() {
		return 0;
	}

	@Override
	public int getExperience() {
		return 0;
	}

	@Override
	public void setExperienceFromServer(int experience) {
		// no-op
		// This appears to only be implemented for client-side rendering of the trading GUI (in the MerchantScreenHandler class).
		// This is probably only relevant if isLeveledMerchant() returns true.
	}

	@Override
	public void setOffersFromServer(TradeOfferList offers) {
		// no-op
		// This appears to only be implemented for client-side rendering of the trading GUI (in the MerchantScreenHandler class).
	}

	protected abstract SoundEvent getTradeSuccessfulSound(); // normally uses the same sound as getYesSound()

	protected abstract SoundEvent getTradeFailedSound();

	protected abstract SoundEvent getTradeAcceptableSound();

	@Override
	public SoundEvent getYesSound() {
		return getTradeAcceptableSound(); // Redirected to a method with a different name to be more clear with what this does
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		TradeOfferList tradeOfferList = getOffers();
		if (!tradeOfferList.isEmpty()) {
			nbt.put(TRADE_OFFERS_NBT_KEY, tradeOfferList.toNbt());
		}
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		if (nbt.contains(TRADE_OFFERS_NBT_KEY, NbtElement.COMPOUND_TYPE)) {
			offers = new TradeOfferList(nbt.getCompound(TRADE_OFFERS_NBT_KEY));
		}
	}

	@Nullable
	@Override
	public Entity moveToWorld(ServerWorld destination) {
		setCustomer(null);
		return super.moveToWorld(destination);
	}

	@Override
	public void onDeath(DamageSource damageSource) {
		super.onDeath(damageSource);
		setCustomer(null);
	}

	@Override
	public boolean isClient() {
		// This really doesn't need to be in the Merchant interface. It's not even used in all places.
		// Minecraft's code is weird.
		return getWorld().isClient();
	}

	public TradeOffer createTradeOffer(Item buyItem, int buyAmount, Item sellItem, int sellAmount, int maxUses) {
		// This is just a helper method
		return new TradeOffer(
				new ItemStack(buyItem, buyAmount),
				new ItemStack(sellItem, sellAmount),
				maxUses, 0, 0.0f
		);
	}

	public TradeOffer createTradeOffer(Item firstBuyItem, int firstBuyAmount, Item secondBuyItem, int secondBuyAmount, Item sellItem, int sellAmount, int maxUses) {
		// This is just a helper method
		return new TradeOffer(
				new ItemStack(firstBuyItem, firstBuyAmount),
				new ItemStack(secondBuyItem, secondBuyAmount),
				new ItemStack(sellItem, sellAmount),
				maxUses, 0, 0.0f
		);
	}
}
