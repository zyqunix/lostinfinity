package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketCthulhuBarrier.class */
public class PacketCthulhuBarrier implements IMessage {
    private int flag;
    private int value;
    private int entityID;

    public PacketCthulhuBarrier() {
    }

    public PacketCthulhuBarrier(int flag, int value, int entityID) {
        this.flag = flag;
        this.value = value;
        this.entityID = entityID;
    }

    public void fromBytes(ByteBuf buf) {
        this.flag = buf.readInt();
        this.value = buf.readInt();
        this.entityID = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.flag);
        buf.writeInt(this.value);
        buf.writeInt(this.entityID);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketCthulhuBarrier$CthulhuBarrierPacketHandler.class */
    public static class CthulhuBarrierPacketHandler implements IMessageHandler<PacketCthulhuBarrier, IMessage> {
        public IMessage onMessage(PacketCthulhuBarrier message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            World world = player.field_70170_p;
            player.func_71121_q().func_152344_a(() -> {
                EntityCthulhu entityCthulhuFunc_73045_a = world.func_73045_a(message.entityID);
                if (entityCthulhuFunc_73045_a instanceof EntityCthulhu) {
                    EntityCthulhu cthulhu = entityCthulhuFunc_73045_a;
                    switch (message.flag) {
                        case 1:
                            cthulhu.setBarrierCd1(message.value);
                            break;
                        case 2:
                            cthulhu.setBarrierCd2(message.value);
                            break;
                        case 3:
                            cthulhu.setBarrierCd3(message.value);
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            cthulhu.setBarrierCd4(message.value);
                            break;
                        case 5:
                            cthulhu.setAllBarrierCd(message.value);
                            break;
                    }
                }
            });
            return null;
        }
    }
}
