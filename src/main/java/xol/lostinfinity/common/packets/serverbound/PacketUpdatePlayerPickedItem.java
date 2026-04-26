package xol.lostinfinity.common.packets.serverbound;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
public class PacketUpdatePlayerPickedItem implements IMessage {
    private int targetId;
    private ItemStack stack;
    public PacketUpdatePlayerPickedItem() {
    }
    public PacketUpdatePlayerPickedItem(int targetId, ItemStack stack) {
        this.targetId = targetId;
        this.stack = stack;
    }
    public void fromBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        this.targetId = buffer.func_150792_a();
        try {
            this.stack = buffer.func_150791_c();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void toBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        buffer.func_150787_b(this.targetId);
        buffer.func_150788_a(this.stack);
    }
    public static class UpdatePlayerPickedItemPacketHandler implements IMessageHandler<PacketUpdatePlayerPickedItem, IMessage> {
        public IMessage onMessage(PacketUpdatePlayerPickedItem message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().field_147369_b;
            EntityPlayer target = serverPlayer.field_70170_p.func_73045_a(message.targetId);
            target.field_71071_by.func_70437_b(message.stack);
            return null;
        }
    }
}
