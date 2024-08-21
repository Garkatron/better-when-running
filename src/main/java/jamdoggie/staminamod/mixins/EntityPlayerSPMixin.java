package jamdoggie.staminamod.mixins;

import jamdoggie.staminamod.mixininterfaces.IEntityPlayerMixin;
import jamdoggie.staminamod.network.PacketSendStamina;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayerSP.class, remap = false)
public abstract class EntityPlayerSPMixin extends EntityPlayer
{
	@Shadow
	protected Minecraft mc;

	public EntityPlayerSPMixin(World world)
	{
		super(world);
	}

	@Inject(method = "onLivingUpdate()V", at = @At("TAIL"), remap = false)
	private void playerSPTick(CallbackInfo ci)
	{
		IEntityPlayerMixin playerMixin = (IEntityPlayerMixin)this;

		if (playerMixin.getStamina() != playerMixin.getPrevStamina())
		{
			if (this.mc.getSendQueue() != null)
				this.mc.getSendQueue().addToSendQueue(new PacketSendStamina(playerMixin.getStamina(), playerMixin.isExhausted()));
		}
	}

	@Override
	public void func_6420_o()
	{

	}

	@Override
	public void sendMessage(String message) {
		this.mc.ingameGUI.addChatMessage(message);
	}

	@Override
	public void sendStatusMessage(String message) {
		this.mc.ingameGUI.guiHeldItemTooltip.setString(message);
	}
}
