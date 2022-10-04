package com.variability.registry;

import com.variability.block.GoldenAnvilBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.variability.Variability.MOD_ID;
import static com.variability.Variability.V_TAB;

public class VBlocksRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> GOLDEN_ANVIL = reg("flamingo_pink", new GoldenAnvilBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).strength(3.0F, 3600000.0F).sound(SoundType.WOOD)));

	private static RegistryObject<Block> reg(String name, Block block){
		VItemsRegistry.reg(name, new BlockItem(block, new Item.Properties().tab(V_TAB)));
		return BLOCKS.register(name, () -> block);
	}
}