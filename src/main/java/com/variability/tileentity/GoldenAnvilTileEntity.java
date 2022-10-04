package com.variability.tileentity;

import net.minecraft.tileentity.TileEntity;

import static com.variability.registry.VTileEntitiesRegistry.GOLDEN_ANVIL_TE;

public class GoldenAnvilTileEntity extends TileEntity {

    public GoldenAnvilTileEntity() {
        super(GOLDEN_ANVIL_TE.get());
    }

}
