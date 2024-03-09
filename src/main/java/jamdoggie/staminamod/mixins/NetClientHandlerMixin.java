package jamdoggie.staminamod.mixins;

import jamdoggie.staminamod.mixininterfaces.IEntityPlayerMixin;
import jamdoggie.staminamod.network.INetHandler;
import jamdoggie.staminamod.network.PacketAddStamina;
import jamdoggie.staminamod.network.PacketSendStamina;
import net.minecraft.client.Minecraft;
import net.minecraft.core.net.handler.NetHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = net.minecraft.client.net.handler.NetClientHandler.class, remap = false)
public class NetClientHandlerMixin extends NetHandler implements INetHandler
{
	@Shadow
	@Final
	private Minecraft mc;

	@Override
	public void handleStaminaPacket(PacketSendStamina packet)
	{
		IEntityPlayerMixin playerMixin = (IEntityPlayerMixin)this.mc.thePlayer;
		playerMixin.setStamina(packet.stamina);
		playerMixin.setExhausted(packet.exhausted);

		System.out.println("Stamina: " + packet.stamina + " Exhausted: " + packet.exhausted);
	}

	@Override
	public void handleAddStaminaPacket(PacketAddStamina packet)
	{
		IEntityPlayerMixin playerMixin = (IEntityPlayerMixin)this.mc.thePlayer;
		playerMixin.setStamina(
			Math.min(
				Math.max(
					playerMixin.getStamina() + packet.stamina,
				0),
			100));

		if (playerMixin.getStamina() <= 0)
			playerMixin.setExhausted(true);
	}

	@Override
	public boolean isServerHandler()
	{
		return false;
	}
}
