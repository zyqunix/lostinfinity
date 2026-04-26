package xol.lostinfinity.common.packets.serverbound;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
public class PacketSupplyInventoryServer implements IMessage {
    private int sourceId;
    private int slot;
    private ItemStack stack;
    public PacketSupplyInventoryServer() {
    }
    public PacketSupplyInventoryServer(int sourceId, int slot, ItemStack stack) {
        this.sourceId = sourceId;
        this.slot = slot;
        this.stack = stack;
    }
    public void fromBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        this.sourceId = buf.func_150792_a();
        this.slot = buf.func_150792_a();
        try {
            this.stack = buf.func_150791_c();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void toBytes(ByteBuf b) {
        PacketBuffer buf = new PacketBuffer(b);
        buf.func_150787_b(this.sourceId);
        buf.func_150787_b(this.slot);
        buf.func_150788_a(this.stack);
    }
    public static class SupplyInventoryServerPacketHandler implements IMessageHandler<PacketSupplyInventoryServer, IMessage> {
        public IMessage onMessage(PacketSupplyInventoryServer message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            EntitySupplyTrader trader = player.field_70170_p.func_73045_a(message.sourceId);
            trader.setInventorySlotContents(message.slot, message.stack);
            return null;
        }
    }
}
