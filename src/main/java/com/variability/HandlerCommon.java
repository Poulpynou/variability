package com.variability;

import com.variability.capability.ISmithCapability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nonnull;

import static com.variability.Variability.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HandlerCommon {

    @SubscribeEvent
    public static void fMLCommonSetupEvent(@Nonnull final FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(ISmithCapability.class, new ISmithCapability.Storage(), ISmithCapability.Impl::new);
    }
}