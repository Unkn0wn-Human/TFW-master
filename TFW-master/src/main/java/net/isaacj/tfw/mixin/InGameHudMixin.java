package net.isaacj.tfw.mixin;

import net.isaacj.tfw.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    private static final Identifier FROZEN_EFFECT_LEVEL = new Identifier("textures/misc/powder_snow_outline.png");
    private static final Identifier POWDER_SNOW_OUTLINE = new Identifier("textures/misc/powder_snow_outline.png");


    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void renderOverlay(Identifier texture, float opacity);




    @Inject(at = @At("TAIL"), method = "render")
    private void Frozen(MatrixStack matrices, float tickDelta, CallbackInfo ci){
        if(this.client.player.hasStatusEffect(ModEffects.FROZEN)){
            this.renderOverlay(FROZEN_EFFECT_LEVEL, 1);
        }



    }



}
