package xol.lostinfinity.item.classify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import xol.lostinfinity.client.TextFmt;
public interface ItemSoulbound {
    default String soulBoundMessage() {
        return TextFmt.Dark_Purple + "This item is lifebound. It cannot be brought across dimensions and is lost on death.";
    }
    static void removeSoulBound(EntityPlayer play) {
        for (int i = 0; i < play.field_71071_by.func_70302_i_(); i++) {
            if (play.field_71071_by.func_70301_a(i).func_77973_b() instanceof ItemSoulbound) {
                play.field_71071_by.func_70299_a(i, ItemStack.field_190927_a);
                if (!play.field_70170_p.field_72995_K) {
                    play.func_145747_a(new TextComponentString(TextFmt.Red + "You had a lifebound item removed from your inventory."));
                }
            }
        }
    }
}
