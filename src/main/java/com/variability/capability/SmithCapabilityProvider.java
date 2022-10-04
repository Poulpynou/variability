package com.variability.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

import static com.variability.Variability.SMITH_CAPABILITY;

public class SmithCapabilityProvider implements ICapabilitySerializable<INBT> {

    private final ISmithCapability capability;
    private final LazyOptional<ISmithCapability> capabilityOptional;

    public SmithCapabilityProvider(){
        this.capability = new ISmithCapability.Impl();
        this.capabilityOptional = LazyOptional.of(() -> capability);
    }

    @Override
    public INBT serializeNBT(){
        return SMITH_CAPABILITY.getStorage().writeNBT(SMITH_CAPABILITY, this.capability, null);
    }

    @Override
    public void deserializeNBT(final INBT nbt){
        SMITH_CAPABILITY.getStorage().readNBT(SMITH_CAPABILITY, this.capability, null, nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final Direction direction){
        return cap == SMITH_CAPABILITY ? this.capabilityOptional.cast() : LazyOptional.empty();
    }
}