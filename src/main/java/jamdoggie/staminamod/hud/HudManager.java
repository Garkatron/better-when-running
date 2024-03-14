package jamdoggie.staminamod.hud;

import net.minecraft.client.gui.hud.ComponentAnchor;
import net.minecraft.client.gui.hud.HudComponents;
import net.minecraft.client.gui.hud.SnapLayout;
import org.apache.logging.log4j.core.Layout;

import javax.swing.*;

public class HudManager
{
	public String initialSettingsString;

	public void onInitialize()
	{
		StaminaBarComponent staminaBar = new StaminaBarComponent("stamina", 182, 7,
			new SnapLayout(HudComponents.HOTBAR, ComponentAnchor.TOP_LEFT, ComponentAnchor.BOTTOM_LEFT));

		HudComponents.register(staminaBar);

		if (HudComponents.HEALTH_BAR.getLayout() instanceof SnapLayout layout)
			layout.setParent(staminaBar);

		if (HudComponents.ARMOR_BAR.getLayout() instanceof SnapLayout layout)
			layout.setParent(staminaBar);

		initialSettingsString = HudComponents.INSTANCE.toSettingsString();
	}
}
