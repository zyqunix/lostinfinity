package xol.lostinfinity.common.packets.clientbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketShipmentBoxUpdate.class */
public class PacketShipmentBoxUpdate implements IMessage {
    private ItemStack boxStack;
    private int slot;
    private ItemStack stack;

    public PacketShipmentBoxUpdate() {
    }

    public PacketShipmentBoxUpdate(ItemStack boxStack, int slot, ItemStack stack) {
        this.boxStack = boxStack;
        this.slot = slot;
        this.stack = stack;
    }

    public void fromBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        this.slot = buffer.func_150792_a();
        try {
            this.boxStack = buffer.func_150791_c();
            this.stack = buffer.func_150791_c();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        buffer.func_150787_b(this.slot);
        buffer.func_150788_a(this.boxStack);
        buffer.func_150788_a(this.stack);
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/clientbound/PacketShipmentBoxUpdate$ShipmentBoxUpdatePacketHandler.class */
    public static class ShipmentBoxUpdatePacketHandler implements IMessageHandler<PacketShipmentBoxUpdate, IMessage> {
        public IMessage onMessage(PacketShipmentBoxUpdate message, MessageContext ctx) {
            ItemStack shipmentBox = message.boxStack;
            ItemStackHandler boxHandler = (ItemStackHandler) shipmentBox.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing) null);
            boxHandler.insertItem(message.slot, message.stack, false);
            return null;
        }
    }
}
