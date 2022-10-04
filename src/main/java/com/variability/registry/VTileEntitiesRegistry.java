package com.variability.registry;

import com.variability.tileentity.GoldenAnvilTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.variability.Variability.MOD_ID;

public class VTileEntitiesRegistry {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

	public static final RegistryObject<TileEntityType<GoldenAnvilTileEntity>> GOLDEN_ANVIL_TE = reg("golden_anvil_te", GoldenAnvilTileEntity::new, () -> new Block[]{VBlocksRegistry.GOLDEN_ANVIL.get()});

	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> reg(String name, Supplier<T> factoryIn, Supplier<Block[]> validBlocksSupplier){
		return TILE_ENTITY_TYPES.register(name, () -> TileEntityType.Builder.of(factoryIn, validBlocksSupplier.get()).build(null));
	}
}