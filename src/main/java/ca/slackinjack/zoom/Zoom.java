package ca.slackinjack.zoom;

import ca.slackinjack.zoom.config.ConfigLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Zoom.MODID, guiFactory = "ca.slackinjack.zoom.config.GUIConfigFactory", acceptedMinecraftVersions = "1.8.9")

public class Zoom {
   public static final String MODID = "zoom";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigLoader.load(event);
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new KeyManager());
	}
}
