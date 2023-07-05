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

    @Shadow @Final private MinecraftClient client;

    @Shadow protected abstract void renderOverlay(Identifier texture, float opacity);

    private long fadeStartTime = 0L;
    private long fadeOutStartTime = 0L;
    private static final long FADE_IN_DURATION_MS = 5000L;  // 5 seconds
    private static final long FADE_OUT_DURATION_MS = 5000L;  // 5 seconds
    private boolean isFadingIn = false;
    private boolean isFadingOut = false;

    @Inject(at = @At("TAIL"), method = "render")
    private void Frozen(MatrixStack matrices, float tickDelta, CallbackInfo ci){
        if (this.client.player.hasStatusEffect(ModEffects.FROZEN)) {
            if (!isFadingIn && !isFadingOut) {
                fadeStartTime = System.currentTimeMillis();
                fadeOutStartTime = 0L;
                isFadingIn = true;
                isFadingOut = false;
            }

            long currentTime = System.currentTimeMillis();
            float opacity = 1f;

            if (isFadingIn) {
                long elapsedTime = currentTime - fadeStartTime;
                opacity = Math.min(1f, (float) elapsedTime / FADE_IN_DURATION_MS);
                this.renderOverlay(FROZEN_EFFECT_LEVEL, opacity);
                if (opacity == 1f) {
                    isFadingIn = false;
                }
            } else {
                this.renderOverlay(FROZEN_EFFECT_LEVEL, 1f);
            }

            if (isFadingOut) {
                long elapsedTime = currentTime - fadeOutStartTime;
                opacity = Math.max(0f, 1f - (float) elapsedTime / FADE_OUT_DURATION_MS);
                if (opacity > 0f) {
                    this.renderOverlay(FROZEN_EFFECT_LEVEL, opacity);
                } else {
                    isFadingOut = false;
                    fadeStartTime = 0L;
                }
            }
        } else {
            fadeStartTime = 0L;
            if (!isFadingOut) {
                fadeOutStartTime = System.currentTimeMillis();
                isFadingOut = true;
            }
        }
    }
}
