package net.isaacj.tfw.screen;


import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.entity.NetheriteBlasterEntity;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<NetheriteBlasterScreenHandler> NETHERITE_BLASTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(TFWmod.MOD_ID, "netherite_blaster"),
                    NetheriteBlasterScreenHandler::new);
}

