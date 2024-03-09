package jamdoggie.staminamod.network;

import jamdoggie.staminamod.mixininterfaces.IEntityPlayerMixin;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.server.net.handler.NetServerHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSendStamina extends Packet
{
	public float stamina;
	public boolean exhausted;

	public PacketSendStamina()
	{
	}

	public PacketSendStamina(float stamina, boolean exhausted)
	{
		this.stamina = stamina;
		this.exhausted = exhausted;
	}

	@Override
	public void readPacketData(DataInputStream dataInputStream) throws IOException
	{
		stamina = dataInputStream.readFloat();
		exhausted = dataInputStream.readBoolean();
	}

	@Override
	public void writePacketData(DataOutputStream dataOutputStream) throws IOException
	{
		dataOutputStream.writeFloat(stamina);
		dataOutputStream.writeBoolean(exhausted);
	}

	@Override
	public void processPacket(NetHandler netHandler)
	{
		((INetHandler)netHandler).handleStaminaPacket(this);
	}

	@Override
	public int getPacketSize()
	{
		return 0;
	}
}
