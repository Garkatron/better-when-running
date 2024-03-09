package jamdoggie.staminamod.network;

public interface INetHandler
{
	void handleStaminaPacket(PacketSendStamina packet);
	void handleAddStaminaPacket(PacketAddStamina packet);
}
