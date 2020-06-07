package ca.slackinjack.zoom.config;

import ca.slackinjack.zoom.Zoom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigLoader {
	public static Configuration config;
	public static int zoomAmountOne;
	public static int zoomAmountTwo;
	public static int mouseSensDefault;
	public static int mouseSensOne;
	public static int mouseSensTwo;
	public static boolean csgoZoomEnabled;
	
	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		setConfigFileVars();
		saveConfig();
		MinecraftForge.EVENT_BUS.register(new ConfigLoader());
	}

	public static void setConfigFileVars() {
		zoomAmountOne = config.getInt("Zoom Amount Level 1", Configuration.CATEGORY_GENERAL, 2, 1, 10, "Higher value = More zoom (Also used for normal zoom)");
		zoomAmountTwo = config.getInt("Zoom Amount Level 2", Configuration.CATEGORY_GENERAL, 4, 1, 10, "Higher value = More zoom");
		mouseSensDefault = config.getInt("Default Mouse Sensitivity", Configuration.CATEGORY_GENERAL, 120, 0, 200, "Mouse sensitivity without zoom");
		mouseSensOne = config.getInt("Mouse Sensitivity - Zoom Level 1", Configuration.CATEGORY_GENERAL, 60, 0, 200, "Mouse sensitivity in first-level zoom (Also used for normal zoom)");
		mouseSensTwo = config.getInt("Mouse Sensitivity - Zoom Level 2", Configuration.CATEGORY_GENERAL, 15, 0, 200, "Mouse sensitivity in second-level zoom");
		csgoZoomEnabled = config.getBoolean("Enable CSGO-esque Zoom", Configuration.CATEGORY_GENERAL, true, "Enable multi-level zoom like CSGO");
	}
		
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(Zoom.MODID)) {
			saveConfig();
		}
	}
	
	public static void configLoader() {
		try {
			config.load();
			Property getZoomAmountOne = config.get(Configuration.CATEGORY_GENERAL, "Zoom Amount Level 1", 2, "Zoom Amount Level 1");
			Property getZoomAmountTwo = config.get(Configuration.CATEGORY_GENERAL, "Zoom Amount Level 2", 4, "Zoom Amount Level 2");
			Property getMouseSensDefault = config.get(Configuration.CATEGORY_GENERAL, "Default Mouse Sensitivity", 120, "Default Mouse Sensitivity");
			Property getMouseSensOne = config.get(Configuration.CATEGORY_GENERAL, "Mouse Sensitivity - Zoom Level 1", 60, "Mouse Sensitivity - Zoom Level 1");
			Property getMouseSensTwo = config.get(Configuration.CATEGORY_GENERAL, "Mouse Sensitivity - Zoom Level 2", 15, "Mouse Sensitivity - Zoom Level 2");
			Property getCsgoZoomEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable CSGO-esque Zoom", true, "Enable CSGO-esque Zoom");
			
			zoomAmountOne = getZoomAmountOne.getInt();
			zoomAmountTwo = getZoomAmountTwo.getInt();
			mouseSensDefault = getMouseSensDefault.getInt();
			mouseSensOne = getMouseSensOne.getInt();
			mouseSensTwo = getMouseSensTwo.getInt();
			csgoZoomEnabled = getCsgoZoomEnabled.getBoolean();
		} catch (Exception e) {
		} finally {
		}
	}
	
	public static void saveConfig() {
		if (config.hasChanged()) {
			config.save();
			configLoader();
		}
	}
}