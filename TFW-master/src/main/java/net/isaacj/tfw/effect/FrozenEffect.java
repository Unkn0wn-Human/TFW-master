package net.isaacj.tfw.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.TickDurationMonitor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.TickTimeTracker;
import net.minecraft.world.World;
import net.minecraft.world.tick.Tick;
import net.minecraft.world.tick.TickScheduler;

import java.util.Random;

public class FrozenEffect extends StatusEffect {
    protected FrozenEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }



        @Override
        public void applyUpdateEffect (LivingEntity pLivingEntity,int pAmplifier){
            if (!pLivingEntity.world.isClient()) {
                pLivingEntity.damage(DamageSource.FREEZE, 1);
            }

            super.applyUpdateEffect(pLivingEntity, pAmplifier);
        }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i;
        if (this == ModEffects.FROZEN) {
            i = 200 >> amplifier;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }


        }
        return true;
    }
}


