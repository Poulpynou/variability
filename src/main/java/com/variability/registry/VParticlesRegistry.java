package com.variability.registry;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.variability.Variability.MOD_ID;

public class VParticlesRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);

    public static final RegistryObject<BasicParticleType> DARK_SMOKE_PARTICLE = reg("dark_smoke", new BasicParticleType(true));

    private static RegistryObject<BasicParticleType> reg(String name, BasicParticleType particle) {
        return PARTICLE_TYPES.register(name, () -> particle);
    }
}