package xol.lostinfinity.common.packets.clientbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSupplyInventoryClient.class */
public class PacketSupplyInventoryClient implements IMessage {
    private int sourceId;
    private int slot;
    private ItemStack stack;

    public PacketSupplyInventoryClient() {
    }

    public PacketSupplyInventoryClient(int sourceId, int slot, ItemStack stack) {
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

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketSupplyInventoryClient$SupplyInventoryClientPacketHandler.class */
    public static class SupplyInventoryClientPacketHandler implements IMessageHandler<PacketSupplyInventoryClient, IMessage> {
        public IMessage onMessage(PacketSupplyInventoryClient message, MessageContext ctx) {
            EntitySupplyTrader trader = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.sourceId);
            trader.setInventorySlotContents(message.slot, message.stack);
            return null;
        }
    }
}
