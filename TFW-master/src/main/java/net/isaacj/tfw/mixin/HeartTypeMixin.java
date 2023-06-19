package net.isaacj.tfw.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.isaacj.tfw.effect.ModEffects;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(targets = "net/minecraft/client/gui/hud/InGameHud$HeartType")
@Environment(EnvType.CLIENT)
public abstract class HeartTypeMixin {

    @ModifyExpressionValue(method = "fromPlayerState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isFrozen()Z"))
    private static boolean ifFrozen(boolean original, PlayerEntity player){
        return original || player.hasStatusEffect(ModEffects.FROZEN);
    }




}
