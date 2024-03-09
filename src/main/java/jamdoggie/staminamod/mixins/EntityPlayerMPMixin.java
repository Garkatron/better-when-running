package jamdoggie.staminamod.mixins;

import jamdoggie.staminamod.mixininterfaces.IEntityPlayerMixin;
import jamdoggie.staminamod.network.PacketAddStamina;
import jamdoggie.staminamod.network.PacketSendStamina;
import jamdoggie.staminamod.util.StaminaConstants;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.server.entity.player.EntityPlayerMP.class, remap = false)
public class EntityPlayerMPMixin extends EntityPlayer
{
	@Shadow
	public NetServerHandler playerNetServerHandler;

	public EntityPlayerMPMixin(World world)
	{
		super(world);
	}

	@Inject(method = "onDeath", at = @At("TAIL"))
	private void onDeath(Entity entity, CallbackInfo ci)
	{
		PacketSendStamina packet = new PacketSendStamina(100, false);
		this.playerNetServerHandler.sendPacket(packet);
	}

	@Override
	public boolean hurt(Entity attacker, int damage, DamageType type)
	{
		boolean hurtSuccess = super.hurt(attacker, damage, type);

		if (hurtSuccess)
		{
			PacketAddStamina packet = new PacketAddStamina((damage / 2f) * -(StaminaConstants.staminaLossPerHeart));
			this.playerNetServerHandler.sendPacket(packet);
		}

		return hurtSuccess;
	}

	@Override
	public void func_6420_o()
	{

	}
}
