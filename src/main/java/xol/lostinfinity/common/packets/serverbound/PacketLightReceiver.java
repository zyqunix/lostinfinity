package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityLightEmitter;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketLightReceiver.class */
public class PacketLightReceiver implements IMessage {
    private boolean complete;
    private int x;
    private int y;
    private int z;
    private boolean active;

    public PacketLightReceiver() {
    }

    public PacketLightReceiver(boolean complete, int x, int y, int z) {
        this.complete = complete;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.complete);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public void fromBytes(ByteBuf buf) {
        this.complete = buf.readBoolean();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketLightReceiver$LightReceiverPacketHandler.class */
    public static class LightReceiverPacketHandler implements IMessageHandler<PacketLightReceiver, IMessage> {
        public IMessage onMessage(PacketLightReceiver message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                int x = message.x;
                int y = message.y;
                int z = message.z;
                TileEntity te = player.field_70170_p.func_175625_s(new BlockPos(x, y, z));
                if (te instanceof TileEntityLightEmitter) {
                    ((TileEntityLightEmitter) te).setComplete(message.complete);
                }
            });
            return null;
        }
    }
}
