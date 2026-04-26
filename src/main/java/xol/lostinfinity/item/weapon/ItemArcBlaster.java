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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.projectile.entity.EntityArcBlast;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemArcBlaster.class */
public class ItemArcBlaster extends ItemCooldown implements IMaxAttack, ICustomHoldPose {
    public ItemArcBlaster(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (handIn == EnumHand.MAIN_HAND) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!showDurabilityBar(stack)) {
                if (!worldIn.field_72995_K) {
                    EntityArcBlast shot = new EntityArcBlast(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 4.0f, 0.0f);
                    worldIn.func_72838_d(shot);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.PLAYERS, 0.7f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                }
                boolean hasOffhand = (playerIn.func_184592_cb().func_77973_b() instanceof ItemArcBlaster) || (playerIn.func_184592_cb().func_77973_b() instanceof ItemArcOfTheForbidden);
                if (hasOffhand) {
                    stack.func_77978_p().func_74768_a("ComplexCooldown", 100);
                } else {
                    stack.func_77978_p().func_74768_a("ComplexCooldown", 500);
                }
                playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Light_Purple + "Shoots an electric attack.");
        tooltip.add(TextFmt.Gold + "Attack deals 75% maximum health and applies shock.");
        tooltip.add(TextFmt.Gold + "If shock is already applied, deal 125% damage instead and consume the shock.");
    }
}
