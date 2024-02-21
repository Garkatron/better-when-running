package jamdoggie.staminamod.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import jamdoggie.staminamod.mixininterfaces.IEntityPlayerSPMixin;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin extends EntityPlayer implements IEntityPlayerSPMixin
{
	// From 0.0f to 100.0f
	public float stamina = 100.0f;
	public boolean exhausted = false;
	private float exhaustionSpeed = 30f; // Amount to be subtracted from stamina per second while sprinting.
	private float exhaustionRecoverySpeed = 25f; // Amount to be added to stamina per second while recovering.

	@Inject(method = "onLivingUpdate()V", at = @At("HEAD"))
	private void playerTick(CallbackInfo ci)
	{
		if (!this.gamemode.equals(Gamemode.creative))
		{
			if (!exhausted)
			{
				if (this.isSprinting())
				{
					stamina -= exhaustionSpeed / 20f;

					if (stamina <= 0)
					{
						stamina = 0;
						exhausted = true;
					}
				}
				else
				{
					recoverStamina();
				}
			}
			else
			{
				// We're exhausted, only recover stamina.
				recoverStamina();
			}

			if (exhausted)
			{
				this.setSprinting(false);
			}
		}
	}

	@Inject(method = "onLivingUpdate()V", at = @At(value = "JUMP",
		ordinal = 28)) // Literally could not find a better way to inject this.
	private void cancelSprint(CallbackInfo ci, @Local(name="canSprint") LocalBooleanRef canSprint)
	{
		if (exhausted)
		{
			canSprint.set(false);
		}
	}

	@Unique
	private void recoverStamina()
	{
		stamina += exhaustionRecoverySpeed / 20f;
		if (stamina >= 100)
		{
			stamina = 100;
			exhausted = false;
		}
	}

	public EntityPlayerSPMixin(World world)
	{
		super(world);
	}

	@Override
	@Unique
	public float getStamina()
	{
		return stamina;
	}

	@Override
	@Unique
	public void setStamina(float stamina)
	{
		this.stamina = stamina;
	}

	@Override
	public boolean isExhausted()
	{
		return exhausted;
	}

	// Wtf is this? The implementation is empty in the original class too.
	@Override
	public void func_6420_o()
	{

	}
}
