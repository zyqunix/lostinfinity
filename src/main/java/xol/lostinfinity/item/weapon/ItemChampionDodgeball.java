package xol.lostinfinity.item.weapon;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.projectile.entity.EntityChampionDodgeball;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/weapon/ItemChampionDodgeball.class */
public class ItemChampionDodgeball extends ItemCooldown implements ISwitchModels {
    public ItemChampionDodgeball(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
        setModelSwitch("balltype", this, 3);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int type = stack.func_77978_p().func_74762_e("balltype_data");
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                if (type == 1) {
                    for (int i = 0; i < 4; i++) {
                        EntityChampionDodgeball shot = new EntityChampionDodgeball(worldIn, playerIn);
                        shot.setType(type);
                        shot.setThrower(playerIn);
                        shot.shootNoVel(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.7f, 6.0f);
                        worldIn.func_72838_d(shot);
                    }
                } else {
                    EntityChampionDodgeball shot2 = new EntityChampionDodgeball(worldIn, playerIn);
                    shot2.setType(type);
                    shot2.setThrower(playerIn);
                    shot2.shootNoVel(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, type == 2 ? 3.0f : 1.5f, 0.0f);
                    worldIn.func_72838_d(shot2);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187578_au, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            stack.func_190918_g(1);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 2000;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A premium dodgeball.");
    }
}
