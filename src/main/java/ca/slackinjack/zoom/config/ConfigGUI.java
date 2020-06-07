package ca.slackinjack.zoom.config;

import ca.slackinjack.zoom.Zoom;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig {
	
    public ConfigGUI(GuiScreen parent) {
        super(parent, new ConfigElement(ConfigLoader.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Zoom.MODID, false, false, "Zoom Config");
    }
    
    @Override
    public void initGui() {
        super.initGui();
    }

    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
    }
}