package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityWeldingChamber;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketWeldingChamber.class */
public class PacketWeldingChamber implements IMessage {
    private int acetyleneAmount;
    private int x;
    private int y;
    private int z;

    public PacketWeldingChamber() {
    }

    public PacketWeldingChamber(BlockPos blockPos, int acetyleneAmount) {
        this.x = blockPos.func_177958_n();
        this.y = blockPos.func_177956_o();
        this.z = blockPos.func_177952_p();
        this.acetyleneAmount = acetyleneAmount;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.acetyleneAmount = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.acetyleneAmount);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketWeldingChamber$WeldingChamberGUIPacketHandler.class */
    public static class WeldingChamberGUIPacketHandler implements IMessageHandler<PacketWeldingChamber, IMessage> {
        public IMessage onMessage(PacketWeldingChamber message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                TileEntity tileEntity = world.func_175625_s(new BlockPos(message.x, message.y, message.z));
                if (tileEntity instanceof TileEntityWeldingChamber) {
                    ((TileEntityWeldingChamber) tileEntity).func_174885_b(2, message.acetyleneAmount);
                }
            });
            return null;
        }
    }
}
