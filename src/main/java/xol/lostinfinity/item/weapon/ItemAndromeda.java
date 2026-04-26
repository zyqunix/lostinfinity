package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ISummon;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaController;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemAndromeda.class */
public class ItemAndromeda extends ItemCooldown implements ISummon {
    public ItemAndromeda(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.func_184586_b(hand);
        if (!showDurabilityBar(stack)) {
            if (!world.field_72995_K) {
                despawnPrevious(player);
                EntityAndromedaController andromeda = new EntityAndromedaController(player.field_70170_p);
                andromeda.setOwner(player);
                andromeda.setHand(hand);
                andromeda.setLastSlot(player.field_71071_by.field_70461_c);
                andromeda.setTrackedItemStack(stack);
                andromeda.func_70107_b(player.field_70165_t, player.field_70163_u + 60.0d, player.field_70161_v);
                world.func_72838_d(andromeda);
                world.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.MAGIC_WEAPON_18, SoundCategory.PLAYERS, 0.7f, 0.6f);
            }
            startCooldown(stack);
        } else if (!world.field_72995_K) {
            despawnPrevious(player);
        }
        return super.func_77659_a(world, player, hand);
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Dark_Red + "What have you done...");
        tooltip.add(TextFmt.Gold + "Creates a giant metal serpent known as the Andromeda");
    }
}
