package xol.lostinfinity.item.cthulhu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketCthulhuBarrier;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/cthulhu/AbstractCthulhuSword.class */
public abstract class AbstractCthulhuSword extends ItemSword {
    public AbstractCthulhuSword(String regName) {
        super(Item.ToolMaterial.DIAMOND);
        func_77637_a(TabsInit.TAB_INFINITYWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    protected static void sendCthulhuPacket(int flag, int value, int entityId) {
        lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketCthulhuBarrier(flag, value, entityId));
    }

    @SideOnly(Side.CLIENT)
    protected static boolean isHittingBarrier(EntityPlayer player) {
        EntityCthulhu cthulhu = (EntityCthulhu) player.field_70170_p.field_72996_f.stream().filter(entity -> {
            return entity instanceof EntityCthulhu;
        }).map(entity2 -> {
            return (EntityCthulhu) entity2;
        }).findFirst().orElse(null);
        if (cthulhu == null || !cthulhu.isBarrierActive() || cthulhu.getPhase() != 3) {
            return false;
        }
        double distance = cthulhu.func_70011_f(player.func_180425_c().func_177958_n(), cthulhu.func_180425_c().func_177956_o(), player.func_180425_c().func_177952_p());
        if (distance < 48.0d || distance > 52.0d) {
            return false;
        }
        return true;
    }
}
