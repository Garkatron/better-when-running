package jamdoggie.staminamod.mixins;

import jamdoggie.staminamod.mixininterfaces.IEntityPlayerMixin;
import jamdoggie.staminamod.network.INetHandler;
import jamdoggie.staminamod.network.PacketAddStamina;
import jamdoggie.staminamod.network.PacketSendStamina;
import net.minecraft.core.net.ICommandListener;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = net.minecraft.server.net.handler.NetServerHandler.class, remap = false)
public abstract class NetServerHandlerMixin extends NetHandler implements INetHandler
{
	@Shadow
	public abstract String getUsername();

	@Shadow
	private EntityPlayerMP playerEntity;

	@Override
	public boolean isServerHandler()
	{
		return true;
	}

	@Override
	public void handleStaminaPacket(PacketSendStamina packet)
	{
		((IEntityPlayerMixin)this.playerEntity).setStamina(packet.stamina);
		((IEntityPlayerMixin)this.playerEntity).setExhausted(packet.exhausted);
	}

	@Override
	public void handleAddStaminaPacket(PacketAddStamina packet)
	{

	}
}
