package com.variability.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.variability.tileentity.GoldenAnvilTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class GoldenAnvilTileEntityRenderer extends TileEntityRenderer<GoldenAnvilTileEntity> {

    public GoldenAnvilTileEntityRenderer(TileEntityRendererDispatcher renderer) {
        super(renderer);
    }

    @Override
    public void render(@Nonnull GoldenAnvilTileEntity entity, float partialTicks, @Nonnull MatrixStack matrix, @Nonnull IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

    }
}
