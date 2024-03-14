package jamdoggie.staminamod;

import jamdoggie.staminamod.config.IStaminaSettings;
import jamdoggie.staminamod.hud.HudManager;
import jamdoggie.staminamod.hud.mainmenu.GuiFirstTimeSetupScreen;
import jamdoggie.staminamod.network.NetManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class StaminaMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "staminamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final HudManager hudManager = new HudManager();
	public static final NetManager netManager = new NetManager();
	public static IStaminaSettings options;
    @Override
    public void onInitialize() {
        LOGGER.info("Better When Running reporting for duty!");
		hudManager.onInitialize();
		netManager.onInitialize();
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}
}
