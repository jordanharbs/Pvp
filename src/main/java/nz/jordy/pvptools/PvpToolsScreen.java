package nz.jordy.pvptools;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class PvpToolsScreen extends Screen {
    public PvpToolsScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = this.height / 2 - 42;

        this.addRenderableWidget(Button.builder(
                Component.literal("Totem Counter: " +
                        (JordyPvpToolsClient.totemCounterEnabled ? "ON" : "OFF")),
                button -> {
                    JordyPvpToolsClient.totemCounterEnabled =
                            !JordyPvpToolsClient.totemCounterEnabled;
                    button.setMessage(Component.literal("Totem Counter: " +
                            (JordyPvpToolsClient.totemCounterEnabled ? "ON" : "OFF")));
                }
        ).bounds(centerX - 100, startY, 200, 20).build());

        Button fireInfo = Button.builder(
                Component.literal("Fire Overlay: coming in next update"),
                button -> {}
        ).bounds(centerX - 100, startY + 28, 200, 20).build();
        fireInfo.active = false;
        this.addRenderableWidget(fireInfo);

        this.addRenderableWidget(Button.builder(
                Component.literal("Done"),
                button -> this.onClose()
        ).bounds(centerX - 100, startY + 58, 200, 20).build());
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(null);
    }

    @Override
    public void extractRenderState(net.minecraft.client.gui.GuiGraphicsExtractor graphics,
                                   int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        graphics.text(this.font, "Press Right Shift to open this menu",
                this.width / 2 - 105, this.height / 2 + 58, 0xFFAAAAAA, false);
    }
}
