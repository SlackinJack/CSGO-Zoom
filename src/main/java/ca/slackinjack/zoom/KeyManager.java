package ca.slackinjack.zoom;

import ca.slackinjack.zoom.config.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class KeyManager {
	public Minecraft mc = Minecraft.getMinecraft();
	private static int zoomLevel;
	private static boolean previousZoom;
	private static boolean canZoomOut;
	public static final KeyBinding zoom = new KeyBinding("Zoom - Regular", Keyboard.KEY_C, "key.categories.gameplay");
	public static final KeyBinding csgoZoom = new KeyBinding("Zoom - CSGO", Keyboard.KEY_Z, "key.categories.gameplay");
	static {
		ClientRegistry.registerKeyBinding(zoom);
		ClientRegistry.registerKeyBinding(csgoZoom);
	}
	
	public void setZoomLevel(int zl) {
		zoomLevel = zl;
	}
	
	@SubscribeEvent
	public void zoomFunc(FOVUpdateEvent event) {
		if (mc.currentScreen == null) {
			if (zoomLevel == 0) {
				event.newfov = event.fov;
				mc.gameSettings.mouseSensitivity = ConfigLoader.mouseSensDefault / 200F;
			}
			if (zoomLevel == 1) {
				event.newfov = (1.0F / ConfigLoader.zoomAmountOne);
				mc.gameSettings.mouseSensitivity = ConfigLoader.mouseSensOne / 200F;
			}
			if (zoomLevel == 2) {
				event.newfov = (1.0F / ConfigLoader.zoomAmountTwo);
				mc.gameSettings.mouseSensitivity = ConfigLoader.mouseSensTwo / 200F;
			}
			if (zoomLevel > 2) {
				setZoomLevel(0);
			}
			if (mc.thePlayer.isSneaking()) {
				if (ConfigLoader.csgoZoomEnabled) {
					if (csgoZoom.isPressed()) {
						zoomLevel = ++zoomLevel;
					}
					if (previousZoom && !zoom.isKeyDown()) {
						if (zoomLevel == 1 && canZoomOut) {
							canZoomOut = false;
							setZoomLevel(0);
						}
						if (zoomLevel == 2 && canZoomOut) {
							canZoomOut = false;
							setZoomLevel(1);
						}
					}
				}
				else {
					if (zoom.isKeyDown()) {
						previousZoom = true;
						canZoomOut = true;
						setZoomLevel(1);
					}
					else {
						previousZoom = false;
						canZoomOut = false;
						setZoomLevel(0);
					}
				}
			}
			if (!mc.thePlayer.isSneaking()) {
				if (zoom.isKeyDown()) {
					previousZoom = true;
					canZoomOut = true;
					setZoomLevel(1);
				}
				if (!zoom.isKeyDown() || zoomLevel > 1) {
					previousZoom = false;
					canZoomOut = false;
					setZoomLevel(0);
				}
				if (csgoZoom.isPressed()) {
				}
			}
		}
	}
}