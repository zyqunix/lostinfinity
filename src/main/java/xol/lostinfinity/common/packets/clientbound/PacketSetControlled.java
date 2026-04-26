package xol.lostinfinity.common.packets.clientbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.client.special.ClientMindControlHandler;
import xol.lostinfinity.common.special.CommonMindControlHandler;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSetControlled.class */
public class PacketSetControlled implements IMessage {
    private CommonMindControlHandler.State state;

    public PacketSetControlled() {
        this.state = CommonMindControlHandler.State.NONE;
    }

    public PacketSetControlled(CommonMindControlHandler.State state) {
        this.state = state;
    }

    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.state = (CommonMindControlHandler.State) buf.func_179257_a(CommonMindControlHandler.State.class);
    }

    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.func_179249_a(this.state);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSetControlled$SetControlledPacketHandler.class */
    public static class SetControlledPacketHandler implements IMessageHandler<PacketSetControlled, IMessage> {
        public IMessage onMessage(PacketSetControlled message, MessageContext ctx) {
            ClientMindControlHandler.INSTANCE.state = message.state;
            return null;
        }
    }
}
