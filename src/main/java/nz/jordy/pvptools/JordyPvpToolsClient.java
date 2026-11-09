package nz.jordy.pvptools;

import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

public final class JordyPvpToolsClient implements ClientModInitializer {
    public static boolean totemCounterEnabled = true;

    private static final Identifier HUD_ID =
            Identifier.fromNamespaceAndPath("jordy_nz_pvp_tools", "totem_counter");

    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("jordy_nz_pvp_tools", "main")
    );

    private static KeyMapping openMenuKey;

    @Override
    public void onInitializeClient() {
        openMenuKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.jordy_nz_pvp_tools.open_menu",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.consumeClick()) {
                if (client.player != null && client.gui.screen() == null) {
                    client.gui.setScreen(new PvpToolsScreen(Component.literal("JORDY NZ PvP Tools")));
                }
            }
        });

        HudElementRegistry.addLast(HUD_ID, (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            if (!totemCounterEnabled || client.player == null || client.options.hideGui) {
                return;
            }

            int totems = client.player.getInventory().countItem(Items.TOTEM_OF_UNDYING);
            graphics.text(client.font, "Totems: " + totems, 8, 8, 0xFFFFFFFF, true);
        });
    }
}
