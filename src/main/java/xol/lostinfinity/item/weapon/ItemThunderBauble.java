package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.item.classify.IMovingSoundSource;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemThunderBauble.class */
public class ItemThunderBauble extends Item implements IHeldTick, IMovingSoundSource {
    public ItemThunderBauble(String regName) {
        func_77637_a(TabsInit.TAB_DEVIANTWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        World world = player.field_70170_p;
        if (player.field_70173_aa % 20 == 0) {
            if (world.field_72995_K) {
                if (player == Minecraft.func_71410_x().field_71439_g) {
                    playSound(SoundInit.ITEM_THUNDERBAUBLE, SoundCategory.PLAYERS, MOVING, 0.7f, 1.0f);
                }
            } else {
                playSoundAround(SoundInit.ITEM_THUNDERBAUBLE, SoundCategory.PLAYERS, player, 2.0f, 1.0f, false, 0);
            }
        }
        if (player.field_70173_aa % 5 == 0) {
            for (EntityLivingBase li : world.func_72872_a(EntityLivingBase.class, player.func_174813_aQ().func_72314_b(6.0d, 5.0d, 6.0d))) {
                if (li.func_110124_au() != player.func_110124_au()) {
                    li.func_70024_g(Math.signum(player.field_70165_t - li.field_70165_t) * (-1.5d), 0.2d, Math.signum(player.field_70161_v - li.field_70161_v) * (-1.5d));
                }
            }
        }
        float maxSpd = 1.5f;
        if (player.func_70051_ag()) {
            maxSpd = 2.0f;
        } else if (player.func_70093_af()) {
            maxSpd = 1.0f;
        }
        if (player.field_70159_w > (-maxSpd) && player.field_70159_w < maxSpd && player.field_70179_y > (-maxSpd) && player.field_70179_y < maxSpd) {
            player.field_70159_w *= 1.2000000476837158d;
            player.field_70179_y *= 1.2000000476837158d;
            player.field_70181_x *= 1.0199999809265137d;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "When held:");
        tooltip.add(TextFmt.Gold + "Grants super speed.");
        tooltip.add(TextFmt.Gold + "Forces creatures in your path out of the way.");
    }
}
