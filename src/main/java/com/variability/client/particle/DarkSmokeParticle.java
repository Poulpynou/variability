package com.variability.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class DarkSmokeParticle extends SpriteTexturedParticle {

    private final double xTarget;
    private final double yTarget;
    private final double zTarget;
    private final float quadSizeTarget;

    private DarkSmokeParticle(ClientWorld world, double x, double y, double z, double xTarget, double yTarget, double zTarget, IAnimatedSprite sprite) {
        super(world, x, y, z);
        this.setSize(1.0F, 1.0F);
        this.lifetime = 200 + this.random.nextInt(50);
        this.gravity = 0.0001F;
        this.xTarget = xTarget;
        this.yTarget = yTarget;
        this.zTarget = zTarget;
        this.xd = xTarget / 100;
        this.yd = yTarget / 100;
        this.zd = zTarget / 100;
        this.pickSprite(sprite);
        this.setAlpha(0.19F);
        this.quadSizeTarget = this.quadSize * 10.0F;
        this.quadSize = 0.0F;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.age++;
        if(this.age < this.lifetime && !(this.alpha <= 0.0F)) {
            if(this.age > 0){
                if(this.age <= 10){
                    this.xd -= this.xTarget / 15000.0F;
                    this.yd -= this.yTarget / 15000.0F + this.gravity;
                    this.zd -= this.zTarget / 15000.0F;
                    this.alpha += 0.08F;
                }else{
                    this.xd -= this.xTarget / 25000.0F;
                    this.yd -= this.yTarget / 25000.0F + this.gravity;
                    this.zd -= this.zTarget / 25000.0F;
                }
                if(age <= 30){
                    this.quadSize = (float) ((1 - Math.cos(this.age / 30.0F * Math.PI)) * this.quadSizeTarget / 2.0F);
                }
            }
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }
        } else {
            this.remove();
        }
    }

    @Nonnull
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;

        public Factory(IAnimatedSprite animatedSprite) {
            this.sprites = animatedSprite;
        }

        public Particle createParticle(@Nonnull BasicParticleType type, @Nonnull ClientWorld world, double x, double y, double z, double xMove, double yMove, double zMove) {
            return new DarkSmokeParticle(world, x, y, z, xMove, yMove, zMove, this.sprites);
        }
    }
}
