package xol.lostinfinity.item.weapon;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.projectile.entity.EntityProjectileKiller;
public class ItemWandSelfReflection extends ItemCooldown {
    public ItemWandSelfReflection(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                EntityProjectileKiller shot = new EntityProjectileKiller(worldIn, playerIn);
                shot.setThrower(playerIn);
                if (playerIn.func_70093_af()) {
                    shot.shortTimer();
                }
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundInit.ITEM_STARSTORM, 1.0f, 1.0f);
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
        tooltip.add(TextFmt.Yellow + "Fires a projectile that explodes shortly after firing.");
        tooltip.add(TextFmt.Yellow + "The explosion destroys nearby projectiles.");
        tooltip.add(TextFmt.Red + "Deal 33% max health damage to the shooter.");
        tooltip.add(TextFmt.Green + "Shift Fire: Projectile detonates much faster.");
    }
}
