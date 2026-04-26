package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
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
import xol.lostinfinity.projectile.entity.EntityPowerPulse;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemPowerPulse.class */
public class ItemPowerPulse extends Item {
    public ItemPowerPulse(String regName) {
        func_77637_a(TabsInit.TAB_AUXMATS);
        setRegistryName(regName);
        func_77655_b(regName);
        func_77625_d(1);
        ItemInit.ITEMS.add(this);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.field_72995_K) {
            EntityPowerPulse shot = new EntityPowerPulse(worldIn, playerIn);
            shot.setThrower(playerIn);
            shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 0.5f, 1.0f);
            worldIn.func_72838_d(shot);
        }
        playerIn.func_184586_b(handIn).func_190918_g(1);
        playerIn.func_184185_a(SoundInit.GALAXYFIRE, 1.0f, 1.0f);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Aqua + "A slow moving projectile with severe knockback.");
    }
}
