package net.isaacj.tfw.effect;

import net.isaacj.tfw.TFWmod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {

    public static StatusEffect FROZEN;

    public static StatusEffect registerStatusEffects(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(TFWmod.MOD_ID, name),
                new FrozenEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        FROZEN = registerStatusEffects("frozen");
    }

}
