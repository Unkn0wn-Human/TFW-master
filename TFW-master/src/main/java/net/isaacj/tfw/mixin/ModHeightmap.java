package net.isaacj.tfw.mixin;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.effect.ModEffects;
import net.isaacj.tfw.tag.CustomBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.State;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;


@Mixin(targets = "net.minecraft.world.Heightmap$Type")
abstract class ModHeightmap {


    @ModifyReturnValue(method = "method_16686", at = @At(value = "RETURN", target = "Lnet/minecraft/world/Heightmap$Type;MOTION_BLOCKING_NO_LEAVES:Lnet/minecraft/world/Heightmap$Type;"))
        private static boolean ifFrozen (boolean original, BlockState state){
        return original && !(state.isIn(CustomBlockTags.HEIGHTMAP_IGNORE));
        }
    }
