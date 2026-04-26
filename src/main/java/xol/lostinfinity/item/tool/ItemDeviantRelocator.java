package xol.lostinfinity.item.tool;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.projectile.entity.EntityDeviantDeployer;
import xol.lostinfinity.projectile.entity.EntityDeviantSucker;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/tool/ItemDeviantRelocator.class */
public class ItemDeviantRelocator extends Item {
    private List<Class<? extends EntityLiving>> devList = new ArrayList();

    public ItemDeviantRelocator(String regName) {
        func_77637_a(TabsInit.TAB_AUXWEP);
        setRegistryName(regName);
        func_77655_b(regName);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K) {
            if (playerIn.func_70093_af()) {
                EntityDeviantDeployer shot = new EntityDeviantDeployer(worldIn, playerIn);
                shot.giveList(this.devList);
                this.devList.clear();
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                worldIn.func_72838_d(shot);
            } else {
                EntityDeviantSucker shot2 = new EntityDeviantSucker(worldIn, playerIn);
                shot2.setThrower(playerIn);
                shot2.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                worldIn.func_72838_d(shot2);
            }
        }
        playerIn.func_184185_a(SoundInit.MAGIC_WEAPON_1, 1.0f, 1.0f);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    public void passDeviantList(List<Class<? extends EntityLiving>> newList) {
        this.devList.addAll(newList);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "Fire: Shoots a projectile that stores all nearby deviants.");
        tooltip.add(TextFmt.Light_Purple + "Shift-Fire: Shoots a projectile to deploy all stored deviants.");
        tooltip.add(TextFmt.Red + "> If deployment hits a player, super-mutate all stored deviants.");
        tooltip.add(TextFmt.Gold + "Stored: " + this.devList.size());
    }
}
