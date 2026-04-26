package xol.lostinfinity.common.packets.serverbound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
public class PacketFusionTable implements IMessage {
    private int x;
    private int y;
    private int z;
    private int shapeId;
    private int fieldId;
    public PacketFusionTable() {
    }
    public PacketFusionTable(BlockPos blockPos, int shapeId, int fieldId) {
        this.x = blockPos.func_177958_n();
        this.y = blockPos.func_177956_o();
        this.z = blockPos.func_177952_p();
        this.shapeId = shapeId;
        this.fieldId = fieldId;
    }
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.shapeId = buf.readInt();
        this.fieldId = buf.readInt();
    }
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.shapeId);
        buf.writeInt(this.fieldId);
    }
    public static class FusionTableGUIPacketHandler implements IMessageHandler<PacketFusionTable, IMessage> {
        public IMessage onMessage(PacketFusionTable message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                TileEntity tileEntity = world.func_175625_s(new BlockPos(message.x, message.y, message.z));
                if (tileEntity instanceof TileEntityFusionTable) {
                    ((TileEntityFusionTable) tileEntity).func_174885_b(message.fieldId, message.shapeId);
                }
            });
            return null;
        }
    }
}
