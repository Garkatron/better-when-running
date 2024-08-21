package jamdoggie.staminamod.hud.mainmenu;

import jamdoggie.staminamod.StaminaMod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.hud.HudComponents;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.lang.I18n;
import org.lwjgl.opengl.GL11;

public class GuiFirstTimeSetupScreen extends GuiScreen
{
	private GuiButton resetHudButton;
	private GuiButton dontResetHudButton;

	@Override
	public void init()
	{
		resetHudButton = new GuiButton(0, this.width / 2 - 105, (this.height / 2) + 5, 100, 20, "Reset HUD");
		dontResetHudButton = new GuiButton(1, this.width / 2 + 5, (this.height / 2) + 5, 100, 20, "Don't Reset HUD");
		controlList.add(resetHudButton);
		controlList.add(dontResetHudButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick)
	{
		GL11.glDisable(2896);
		GL11.glDisable(2912);
		Tessellator tessellator = Tessellator.instance;
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/background.png"));
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		float f = 32.0f;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(0x404040);
		tessellator.addVertexWithUV(0.0, this.height, 0.0, 0.0, (float)this.height / f);
		tessellator.addVertexWithUV(this.width, this.height, 0.0, (float)this.width / f, (float)this.height / f);
		tessellator.addVertexWithUV(this.width, 0.0, 0.0, (float)this.width / f, 0.0);
		tessellator.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		tessellator.draw();

		I18n loc = I18n.getInstance();

		int lines = 2;

		int titleHeight = (height / 2) - 25;

		// Title
		drawStringCentered(fontRenderer, loc.translateKey("betterwhenrunning.fts.title"), width / 2, titleHeight - 12, 0xFFFFFF);

		// Lines
		for (int i = 1; i <= lines; i++)
		{
			drawStringCentered(fontRenderer, loc.translateKey("betterwhenrunning.fts.line" + i), width / 2, (titleHeight + ((12 * (i - 1)))), 0x9E9E9E);
		}

		super.drawScreen(mouseX, mouseY, partialTick);
	}

	@Override
	protected void buttonReleased(GuiButton button)
	{
		if (button.id == 0 || button.id == 1)
		{
			mc.displayGuiScreen(null);
		}

		if (button.id == 0)
		{
			HudComponents.INSTANCE.fromSettingsString(StaminaMod.hudManager.initialSettingsString);
			mc.gameSettings.saveOptions();
		}

		StaminaMod.options.initialRunSetupFinished().set(true);
	}
}
