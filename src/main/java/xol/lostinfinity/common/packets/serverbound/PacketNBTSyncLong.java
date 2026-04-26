package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketNBTSyncLong.class */
public class PacketNBTSyncLong implements IMessage {
    private String key;
    private long value;
    private ItemStack stack;

    public PacketNBTSyncLong() {
    }

    public PacketNBTSyncLong(String nbtKey, long nbtValue, ItemStack adjustedStack) {
        this.key = nbtKey;
        this.value = nbtValue;
        this.stack = adjustedStack;
    }

    public void toBytes(ByteBuf buf) {
        PacketBuffer modifiedBuffer = new PacketBuffer(buf);
        modifiedBuffer.func_180714_a(this.key);
        modifiedBuffer.func_150788_a(this.stack);
        buf.writeLong(this.value);
    }

    public void fromBytes(ByteBuf buf) {
        PacketBuffer modifiedBuffer = new PacketBuffer(buf);
        this.key = modifiedBuffer.func_150789_c(20);
        try {
            this.stack = modifiedBuffer.func_150791_c();
        } catch (IOException e) {
        }
        this.value = buf.readLong();
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketNBTSyncLong$PacketNBTSyncLongHandler.class */
    public static class PacketNBTSyncLongHandler implements IMessageHandler<PacketNBTSyncLong, IMessage> {
        public IMessage onMessage(PacketNBTSyncLong message, MessageContext ctx) {
            ItemStack stack = message.stack;
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74772_a(message.key, message.value);
            return null;
        }
    }
}
