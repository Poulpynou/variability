package com.variability.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.io.IOException;

import static com.variability.Variability.MOD_ID;

public class SRainbowPositionPacket extends STitlePacket {

    private BlockPos rainbowPos;

    public SRainbowPositionPacket(BlockPos rainbowPos){
        super(STitlePacket.Type.SUBTITLE, new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_default"));
        this.rainbowPos = rainbowPos;
    }

    @Override
    public void read(@Nonnull PacketBuffer buffer) throws IOException {
        super.read(buffer);
        this.rainbowPos = buffer.readBlockPos();
    }

    @Override
    public void write(@Nonnull PacketBuffer buffer) throws IOException {
        super.write(buffer);
        buffer.writeBlockPos(this.rainbowPos);
    }

    @Nonnull
    @Override
    @OnlyIn(Dist.CLIENT)
    public ITextComponent getText() {
        if(Minecraft.getInstance().player == null){
            return super.getText();
        }else{
            Vector3d playerPos = Minecraft.getInstance().player.getPosition(0);
            float rotation = (float) (Math.atan((playerPos.z - this.rainbowPos.getZ() - 0.5F) / Math.abs(this.rainbowPos.getX() + 0.5F - playerPos.x)) / Math.PI * 180.0F);
            if(this.rainbowPos.getX() + 0.5 < playerPos.x){
                if(rotation < -67.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_south");
                if(rotation < -22.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_south_west");
                if(rotation < 22.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_west");
                if(rotation < 67.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_north_west");
                return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_north");
            }else{
                if(rotation < -67.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_south");
                if(rotation < -22.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_south_east");
                if(rotation < 22.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_east");
                if(rotation < 67.5F) return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_north_east");
                return new TranslationTextComponent("title." + MOD_ID + "." + "flamingo_direction_north");
            }
        }
    }
}
