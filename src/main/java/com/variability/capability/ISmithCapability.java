package com.variability.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ISmithCapability {

    class Impl implements ISmithCapability {

    }

    /**
     * The storage class of the capability.
     */
    class Storage implements Capability.IStorage<ISmithCapability> {

        private final String TAG_FLAMINGO_LIST = "flamingo_list";

        @Override
        public INBT writeNBT(@Nonnull final Capability<ISmithCapability> capability, @Nonnull final ISmithCapability instance, @Nullable final Direction side) {
            return new CompoundNBT();
        }

        @Override
        public void readNBT(@Nonnull final Capability<ISmithCapability> capability, @Nonnull final ISmithCapability instance, @Nullable final Direction side, @Nonnull final INBT nbt) {
            if (nbt instanceof CompoundNBT) {
                final CompoundNBT compound = (CompoundNBT) nbt;

                // if (compound.contains(TAG_HAS_SMOKE_MONSTER)){
                //    instance.setHasSmokeMonster(compound.getBoolean(TAG_HAS_SMOKE_MONSTER));
                // }
            }
        }
    }
}