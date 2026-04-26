package xol.lostinfinity.common.packets;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.entity.Entity;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketInput;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/LostInfinityPacketListener.class */
public class LostInfinityPacketListener extends ChannelDuplexHandler {
    private final NetHandlerPlayServer connection;

    public LostInfinityPacketListener(NetHandlerPlayServer connection) {
        this.connection = connection;
    }

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CPacketInput) {
            CPacketInput input = (CPacketInput) msg;
            PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
            buf.writeFloat(input.func_149620_c());
            buf.writeFloat(input.func_192620_b());
            byte flags = 0;
            if (input.func_149618_e()) {
                flags = (byte) (0 | 1);
            }
            if (input.func_149617_f()) {
                Entity mount = this.connection.field_147369_b.func_184208_bv();
                if (!(mount instanceof EntityMultipleLivesMount)) {
                    flags = (byte) (flags | 2);
                }
            }
            buf.writeByte(flags);
            input.func_148837_a(buf);
        }
        super.channelRead(ctx, msg);
    }
}
