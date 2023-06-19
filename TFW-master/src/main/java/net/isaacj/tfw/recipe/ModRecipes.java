package net.isaacj.tfw.recipe;


import net.isaacj.tfw.TFWmod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(TFWmod.MOD_ID, NetheriteBlasterRecipes.Serializer.ID),
                NetheriteBlasterRecipes.Serializer.INSTANCE);

        Registry.register(Registry.RECIPE_TYPE, new Identifier(TFWmod.MOD_ID, NetheriteBlasterRecipes.Type.ID),
                NetheriteBlasterRecipes.Type.INSTANCE);
    }
}
