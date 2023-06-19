package net.isaacj.tfw.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.isaacj.tfw.TFWmod;
import net.isaacj.tfw.block.entity.NetheriteBlasterEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class NetheriteBlasterScreen extends HandledScreen<NetheriteBlasterScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(TFWmod.MOD_ID, "textures/gui/netherite_blaster_gui.png");

    public NetheriteBlasterScreen(NetheriteBlasterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if(handler.isCrafting()) {
            drawTexture(matrices, x + 79, y + 35, 176, 14, handler.getScaledProgress(), 12);
        }

        if(handler.hasFuel()) {
            drawTexture(matrices, x + 48, y + 38 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }

        this.addDrawableChild(new TexturedButtonWidget(x + 10, y + 10, 10,10, 176,31,
                TEXTURE, (button) ->{
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
