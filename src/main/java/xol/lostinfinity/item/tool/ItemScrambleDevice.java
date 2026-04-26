package xol.lostinfinity.item.tool;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/tool/ItemScrambleDevice.class */
public class ItemScrambleDevice extends ItemCooldown {
    public ItemScrambleDevice(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn)) && !worldIn.field_72995_K) {
            for (EntityFireball entityFireball : worldIn.func_72872_a(Entity.class, playerIn.func_174813_aQ().func_72314_b(30.0d, 30.0d, 30.0d))) {
                if ((entityFireball instanceof EntityThrowable) || (entityFireball instanceof EntityArrow) || (entityFireball instanceof EntityFireball)) {
                    ((Entity) entityFireball).field_70159_w *= -1.0d;
                    ((Entity) entityFireball).field_70181_x *= -1.0d;
                    ((Entity) entityFireball).field_70179_y *= -1.0d;
                    ((Entity) entityFireball).field_70133_I = true;
                    if (entityFireball instanceof EntityFireball) {
                        entityFireball.field_70232_b *= -1.0d;
                        entityFireball.field_70233_c *= -1.0d;
                        entityFireball.field_70230_d *= -1.0d;
                    }
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MAGIC_WEAPON_2, SoundCategory.MASTER, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deflects projectiles in a 30 block radius.");
    }
}
