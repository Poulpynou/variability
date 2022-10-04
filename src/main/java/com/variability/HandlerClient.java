package com.variability;

import com.variability.client.particle.DarkSmokeParticle;
import com.variability.registry.VParticlesRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.variability.Variability.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HandlerClient {

	@SubscribeEvent
	public static void fMLClientSetupEvent(final FMLClientSetupEvent event){
		// Bind TileEntity renderers.
		// ClientRegistry.bindTileEntityRenderer(FLAMINGO_RAINBOW_TE.get(), RainbowTileEntityRenderer::new);
	}

	@SubscribeEvent
	public static void particleFactoryRegisterEvent(final ParticleFactoryRegisterEvent event){
		Minecraft.getInstance().particleEngine.register(VParticlesRegistry.DARK_SMOKE_PARTICLE.get(), DarkSmokeParticle.Factory::new);
	}
}
