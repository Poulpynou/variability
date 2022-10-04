package com.variability.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.variability.Variability.MOD_ID;

public class VItemsRegistry {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

	//public static final RegistryObject<Item> FLAMINGO_STAFF = reg("flamingo_staff", new Item(new Item.Properties().tab(V_TAB)));

	/*
	//Armors
	public static final RegistryObject<Item> IRON_PLATE_ARMOR_HEAD = reg(new IronPlateArmorItem(HEAD));
	public static final RegistryObject<Item> IRON_PLATE_ARMOR_CHEST = reg(new IronPlateArmorItem(CHEST));
	public static final RegistryObject<Item> IRON_PLATE_ARMOR_LEGS = reg(new IronPlateArmorItem(LEGS));
	public static final RegistryObject<Item> IRON_PLATE_ARMOR_FEET = reg(new IronPlateArmorItem(FEET));

	private static RegistryObject<Item> reg(CustomArmorItem item){
		return reg(item.getItemPartName(), item);
	}
	*/

	public static RegistryObject<Item> reg(String name, Item item) {
		return ITEMS.register(name, () -> item);
	}
}
