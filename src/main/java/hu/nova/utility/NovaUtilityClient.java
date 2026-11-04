package hu.nova.utility;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

public final class NovaUtilityClient implements ClientModInitializer {
    private static final Identifier HUD_ID =
            Identifier.fromNamespaceAndPath("nova_utility_client", "utility_hud");

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                HUD_ID,
                (graphics, deltaTracker) -> {
                    Minecraft client = Minecraft.getInstance();

                    if (client.player == null || client.level == null) {
                        return;
                    }

                    int x = 8;
                    int y = 8;
                    int lineHeight = 12;

                    graphics.text(client.font, "NOVA UTILITY", x, y, 0xFF72D5FF, true);
                    graphics.text(client.font, "FPS: " + client.getFps(),
                            x, y + lineHeight, 0xFFFFFFFF, true);

                    var pos = client.player.blockPosition();
                    graphics.text(client.font,
                            "XYZ: " + pos.getX() + " / " + pos.getY() + " / " + pos.getZ(),
                            x, y + lineHeight * 2, 0xFFFFFFFF, true);
                }
        );
    }
}
