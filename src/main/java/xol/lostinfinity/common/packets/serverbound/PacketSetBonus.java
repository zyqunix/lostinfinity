package xol.lostinfinity.common.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketSetBonus.class */
public class PacketSetBonus implements IMessage {
    public void toBytes(ByteBuf buf) {
    }

    public void fromBytes(ByteBuf buf) {
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/common/packets/serverbound/PacketSetBonus$SetBonusPacketHandler.class */
    public static class SetBonusPacketHandler implements IMessageHandler<PacketSetBonus, IMessage> {
        public IMessage onMessage(PacketSetBonus message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
            ItemStack helmet = (ItemStack) player.field_71071_by.field_70460_b.get(3);
            player.func_71121_q().func_152344_a(() -> {
                if (helmet.func_77978_p() == null) {
                    helmet.func_77982_d(new NBTTagCompound());
                }
                NBTTagCompound tag = helmet.func_77978_p();
                if (tag.func_74764_b("setBonus")) {
                    tag.func_74757_a("setBonus", !tag.func_74767_n("setBonus"));
                } else {
                    tag.func_74757_a("setBonus", false);
                }
                boolean enabled = tag.func_74767_n("setBonus");
                player.func_145747_a(new TextComponentString(enabled ? TextFmt.Green + "Set Effect enabled" : TextFmt.Red + "Set Effect disabled"));
                player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), enabled ? SoundInit.ARMOR_ACTIVATE : SoundInit.ARMOR_DEACTIVATE, SoundCategory.MASTER, 1.0f, 1.0f);
            });
            return null;
        }
    }
}
