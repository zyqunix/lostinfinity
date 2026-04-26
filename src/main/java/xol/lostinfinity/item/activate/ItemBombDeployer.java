package xol.lostinfinity.item.activate;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.mob.entity.misc.EntityBomberBomb;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemBombDeployer.class */
public class ItemBombDeployer extends ItemCooldown {
    public ItemBombDeployer(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXMATS);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            ItemStack stack = playerIn.func_184586_b(handIn);
            if (!worldIn.field_72995_K) {
                int speedVal = stack.func_77978_p().func_74762_e("BombSpeed");
                int sizeVal = stack.func_77978_p().func_74762_e("BombSize");
                EntityBomberBomb bomb = new EntityBomberBomb(worldIn);
                double xPos = Math.floor(playerIn.field_70165_t) + 0.5d;
                double zPos = Math.floor(playerIn.field_70161_v) + 0.5d;
                bomb.func_70107_b(xPos, playerIn.field_70163_u, zPos);
                bomb.setCreator(playerIn.func_110124_au());
                bomb.setDeploymentTime(80 - (12 * speedVal));
                bomb.setBombSize(1 + sizeVal);
                worldIn.func_72838_d(bomb);
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
            int cdVal = stack.func_77978_p().func_74762_e("BombCooldown");
            stack.func_77978_p().func_74768_a("ComplexCooldown", 6000 - (1000 * cdVal));
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected boolean hasSimpleCooldown() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Deploys bombs that explode into two crossing lines.");
        tooltip.add(TextFmt.Italic + "Bombs break soft walls.");
    }
}
