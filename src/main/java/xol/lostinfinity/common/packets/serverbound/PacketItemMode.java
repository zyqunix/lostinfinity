package xol.lostinfinity.common.packets.serverbound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.item.classify.IModeSelect;
public class PacketItemMode implements IMessage {
    public void toBytes(ByteBuf buf) {
    }
    public void fromBytes(ByteBuf buf) {
    }
    public static class ItemModePacketHandler implements IMessageHandler<PacketItemMode, IMessage> {
        public IMessage onMessage(PacketItemMode message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            ItemStack stack = player.func_184614_ca();
            player.func_71121_q().func_152344_a(() -> {
                if (stack.func_77973_b() instanceof IModeSelect) {
                    IModeSelect mode_item = stack.func_77973_b();
                    mode_item.modeUpdate(stack, player);
                }
            });
            return null;
        }
    }
}
