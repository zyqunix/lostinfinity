package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketDismount.class */
public class PacketDismount implements IMessage {
    public void toBytes(ByteBuf buf) {
    }

    public void fromBytes(ByteBuf buf) {
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketDismount$DismountPacketHandler.class */
    public static class DismountPacketHandler implements IMessageHandler<PacketDismount, IMessage> {
        public IMessage onMessage(PacketDismount message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            WorldServer worldServerFunc_71121_q = player.func_71121_q();
            player.getClass();
            worldServerFunc_71121_q.func_152344_a(player::func_184210_p);
            return null;
        }
    }
}
