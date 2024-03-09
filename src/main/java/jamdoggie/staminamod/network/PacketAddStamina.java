package jamdoggie.staminamod.network;

import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAddStamina extends Packet
{
	public float stamina;

	public PacketAddStamina()
	{
	}

	public PacketAddStamina(float stamina)
	{
		this.stamina = stamina;
	}

	@Override
	public void readPacketData(DataInputStream dataInputStream) throws IOException
	{
		stamina = dataInputStream.readFloat();
	}

	@Override
	public void writePacketData(DataOutputStream dataOutputStream) throws IOException
	{
		dataOutputStream.writeFloat(stamina);
	}

	@Override
	public void processPacket(NetHandler netHandler)
	{
		((INetHandler)netHandler).handleAddStaminaPacket(this);
	}

	@Override
	public int getPacketSize()
	{
		return 0;
	}
}
