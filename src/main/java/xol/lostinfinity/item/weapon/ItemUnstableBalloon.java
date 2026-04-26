package xol.lostinfinity.item.weapon;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.projectile.entity.EntityUnstableBalloon;
public class ItemUnstableBalloon extends ItemBasic {
    public ItemUnstableBalloon(String regName, CreativeTabs tab) {
        super(regName, tab);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K) {
            EntityUnstableBalloon shot = new EntityUnstableBalloon(worldIn);
            shot.func_70634_a(playerIn.field_70165_t, playerIn.field_70163_u + ((double) playerIn.field_70131_O) + 1.0d, playerIn.field_70161_v);
            shot.setThrower(playerIn);
            shot.func_70186_c(0.0d, 1.0d, 0.0d, 1.0f, 0.5f);
            worldIn.func_72838_d(shot);
        }
        stack.func_190918_g(1);
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
}
