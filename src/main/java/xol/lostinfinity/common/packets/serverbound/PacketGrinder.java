package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityGrinder;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketGrinder.class */
public class PacketGrinder implements IMessage {
    private int x;
    private int y;
    private int z;
    private boolean success;

    public PacketGrinder() {
    }

    public PacketGrinder(BlockPos blockPos, boolean success) {
        this.x = blockPos.func_177958_n();
        this.y = blockPos.func_177956_o();
        this.z = blockPos.func_177952_p();
        this.success = success;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.success = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeBoolean(this.success);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketGrinder$GUIGrinderPacketHandler.class */
    public static class GUIGrinderPacketHandler implements IMessageHandler<PacketGrinder, IMessage> {
        public IMessage onMessage(PacketGrinder message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                TileEntity tileEntity = world.func_175625_s(new BlockPos(message.x, message.y, message.z));
                if (tileEntity instanceof TileEntityGrinder) {
                    if (message.success) {
                        ((TileEntityGrinder) tileEntity).grind();
                    } else {
                        ((TileEntityGrinder) tileEntity).failGrind();
                    }
                }
            });
            return null;
        }
    }
}
