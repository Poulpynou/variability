package com.variability;

import com.variability.capability.ISmithCapability;
import com.variability.registry.VBlocksRegistry;
import com.variability.registry.VItemsRegistry;
import com.variability.registry.VParticlesRegistry;
import com.variability.registry.VTileEntitiesRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

import static com.variability.registry.VBlocksRegistry.GOLDEN_ANVIL;

@Mod(Variability.MOD_ID)
public class Variability {

    public static final String MOD_ID = "variability";
    public static final ItemGroup V_TAB = new ItemGroup(ItemGroup.getGroupCountSafe(), MOD_ID) {
        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(GOLDEN_ANVIL.get());
        }
    };

    @CapabilityInject(ISmithCapability.class)
    public static Capability<ISmithCapability> SMITH_CAPABILITY;

    public Variability(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VConfig.COMMON_CONFIG);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        VBlocksRegistry.BLOCKS.register(eventBus);
        VItemsRegistry.ITEMS.register(eventBus);
        VParticlesRegistry.PARTICLE_TYPES.register(eventBus);
        VTileEntitiesRegistry.TILE_ENTITY_TYPES.register(eventBus);

        eventBus.addListener(HandlerCommon::fMLCommonSetupEvent);
        eventBus.addListener(HandlerClient::fMLClientSetupEvent);
        eventBus.addListener(HandlerClient::particleFactoryRegisterEvent);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }
}
