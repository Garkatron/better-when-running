package jamdoggie.staminamod.mixins;

import jamdoggie.staminamod.config.IStaminaSettings;
import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = GameSettings.class, remap = false)
public class GameSettingsMixin implements IStaminaSettings
{
	private final GameSettings mixinInst = (GameSettings)((Object)this);
	@Unique
	public BooleanOption initialRunSetupFinished =
		new BooleanOption(mixinInst, "staminamod.options.initialRunSetupFinished", false);

	@Override
	public BooleanOption initialRunSetupFinished()
	{
		return initialRunSetupFinished;
	}
}
