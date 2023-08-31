package io.github.xanderstuff.noctismaledictionem.render;

import io.github.xanderstuff.noctismaledictionem.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ModBlockRenderLayers {
	public static void registerAll() {
		//note: see net.minecraft.client.render.RenderLayer for examples from minecraft
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACONITE, RenderLayer.getCutout());
	}
}
