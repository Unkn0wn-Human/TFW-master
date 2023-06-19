package net.isaacj.tfw.mixin;


import net.isaacj.tfw.effect.ModEffects;

import net.isaacj.tfw.item.ModArmorMaterial;
import net.isaacj.tfw.tag.CustomBiomeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void tick();


    @Shadow
    public abstract PlayerInventory getInventory();

    @Shadow
    protected boolean isSubmergedInWater;



    private int effectTimer;
    private int effectLevel;
    private int hudEffectLevel;

    public void getEffectLevel() {
        BlockPos pos = getBlockPos();

        if (getWorld().isDay()) {
            effectLevel = 1;


        } else if (getWorld().isNight()) {
            effectLevel = 2;

        }


        if (isSubmergedInWater()) {
            effectLevel = effectLevel + 1;

        }

        if (pos.getY() > 175){
            effectLevel = effectLevel + 1;

        }

        if (pos.getY() < 10) {
            effectLevel = effectLevel - 1;


            if (pos.getY() < -10) {
                effectLevel = effectLevel - 1;

            }
        }
        if (hasFullSuitOfArmorOn())
            if (hasLeatherArmorOn()){
                effectLevel = effectLevel - 1;
            } else if(hasHeavyLeatherArmorOn()){
                effectLevel = effectLevel -3;
            }
    }



    public boolean hasFullSuitOfArmorOn() {
        ItemStack boots = getInventory().getArmorStack(0);
        ItemStack leggings = getInventory().getArmorStack(1);
        ItemStack breastplate = getInventory().getArmorStack(2);
        ItemStack helmet = getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }


    public boolean hasLeatherArmorOn() {
        ArmorItem boots = ((ArmorItem) getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) getInventory().getArmorStack(3).getItem());

        ArmorMaterial bootsMaterial = boots.getMaterial();
        ArmorMaterial leggingsMaterial = leggings.getMaterial();
        ArmorMaterial breastplateMaterial = breastplate.getMaterial();
        ArmorMaterial helmetMaterial = helmet.getMaterial();

        return bootsMaterial.equals(ArmorMaterials.LEATHER) && leggingsMaterial.equals(ArmorMaterials.LEATHER) &&
                breastplateMaterial.equals(ArmorMaterials.LEATHER) && helmetMaterial.equals(ArmorMaterials.LEATHER);
    }

    public boolean hasHeavyLeatherArmorOn() {
        ArmorItem boots = ((ArmorItem) getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) getInventory().getArmorStack(3).getItem());

        ArmorMaterial bootsMaterial = boots.getMaterial();
        ArmorMaterial leggingsMaterial = leggings.getMaterial();
        ArmorMaterial breastplateMaterial = breastplate.getMaterial();
        ArmorMaterial helmetMaterial = helmet.getMaterial();

        return bootsMaterial.equals(ModArmorMaterial.HEAVY_LEATHER) && leggingsMaterial.equals(ModArmorMaterial.HEAVY_LEATHER) &&
                breastplateMaterial.equals(ModArmorMaterial.HEAVY_LEATHER)
                && helmetMaterial.equals(ModArmorMaterial.HEAVY_LEATHER);
    }






    @Inject(at = @At("TAIL"), method = "tick")
    private void frozenPlayer(CallbackInfo ci) {
        BlockPos pos = getBlockPos();

        if (world.getBiomeAccess().getBiome(pos).isIn(CustomBiomeTags.IS_FROZEN)) {

            getEffectLevel();


            ++this.effectTimer;

            if (effectLevel >= 1 && effectTimer > 100) {

                if (effectLevel == 1) {

                    this.addStatusEffect(new StatusEffectInstance(ModEffects.FROZEN, 200, 1, false,
                            false, true));
                }
                if (effectLevel == 2) {
                    this.addStatusEffect(new StatusEffectInstance(ModEffects.FROZEN, 200, 1, false,
                            false, true));
                }
                if (effectLevel == 3) {
                    this.addStatusEffect(new StatusEffectInstance(ModEffects.FROZEN, 200, 2, false,
                            false, true));
                }
                if (effectLevel >= 4) {
                    this.addStatusEffect(new StatusEffectInstance(ModEffects.FROZEN, 200, 2, false,
                            false, true));
                }
            }

            if (effectTimer > 100) {
                effectTimer = 0;

            }
        }
    }
}


