package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityChemistryTable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketChemistryTable.class */
public class PacketChemistryTable implements IMessage {
    private int field;
    private int value;
    private int x;
    private int y;
    private int z;

    public PacketChemistryTable() {
    }

    public PacketChemistryTable(BlockPos pos, int field, int value) {
        this.field = field;
        this.value = value;
        this.x = pos.func_177958_n();
        this.y = pos.func_177956_o();
        this.z = pos.func_177952_p();
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.field = buf.readInt();
        this.value = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.field);
        buf.writeInt(this.value);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketChemistryTable$ChemistryTableGUIPacketHandler.class */
    public static class ChemistryTableGUIPacketHandler implements IMessageHandler<PacketChemistryTable, IMessage> {
        public IMessage onMessage(PacketChemistryTable message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                TileEntity tileEntity = world.func_175625_s(new BlockPos(message.x, message.y, message.z));
                if (tileEntity instanceof TileEntityChemistryTable) {
                    ((TileEntityChemistryTable) tileEntity).func_174885_b(message.field, message.value);
                    if (message.field != 9) {
                        ((TileEntityChemistryTable) tileEntity).func_174885_b(9, ((TileEntityChemistryTable) tileEntity).func_174887_a_(9) + 1);
                    }
                }
            });
            return null;
        }
    }
}
