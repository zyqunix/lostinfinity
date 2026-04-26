package xol.lostinfinity.util.player;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.item.armor.ItemLostArmor;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/player/PlayerManager.class */
public class PlayerManager {
    public static boolean isPlayerWearingFullSet(EntityPlayer player, ArmorInit.ArmorSet set) {
        List<ItemStack> equipment = (List) player.func_184193_aE();
        return equipment.get(EntityEquipmentSlot.HEAD.func_188454_b()).func_77973_b() == set.helmet && equipment.get(EntityEquipmentSlot.CHEST.func_188454_b()).func_77973_b() == set.chestplate && equipment.get(EntityEquipmentSlot.LEGS.func_188454_b()).func_77973_b() == set.leggings && equipment.get(EntityEquipmentSlot.FEET.func_188454_b()).func_77973_b() == set.boots;
    }

    public static boolean isWearingAnySet(EntityPlayer playerIn) {
        ItemLostArmor itemLostArmorFunc_77973_b = ((ItemStack) playerIn.field_71071_by.field_70460_b.get(3)).func_77973_b();
        if (!(itemLostArmorFunc_77973_b instanceof ItemLostArmor)) {
            return false;
        }
        ItemLostArmor helmet = itemLostArmorFunc_77973_b;
        ArmorInit.ArmorSet set = helmet.getArmorSet();
        return isPlayerWearingFullSet(playerIn, set);
    }
}
