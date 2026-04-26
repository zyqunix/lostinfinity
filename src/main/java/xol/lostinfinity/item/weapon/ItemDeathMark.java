package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.projectile.entity.EntityDeathShot;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemDeathMark.class */
public class ItemDeathMark extends ItemCooldown implements ICustomHoldPose {
    private static final int COOLDOWN = 750;
    private static final SoundEvent SHOOT_SOUND = SoundInit.SPACE_BOW;

    public ItemDeathMark(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                EntityDeathShot shot = new EntityDeathShot(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.3f, 0.0f);
                worldIn.func_72838_d(shot);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SHOOT_SOUND, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return COOLDOWN;
    }

    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "A powerful bow that marks enemies for death.");
        tooltip.add(TextFmt.Aqua + "The projectile marks any enemies within 15 blocks of itself.");
        tooltip.add(TextFmt.Gold + "Upon Collision:");
        tooltip.add(TextFmt.Red + "Marked entities take 8% Health True Damage Per Marked Entity");
        tooltip.add(TextFmt.Dark_Aqua + "Darkborne, Aquatic");
    }
}
