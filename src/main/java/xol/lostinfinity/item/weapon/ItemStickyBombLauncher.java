package xol.lostinfinity.item.weapon;
import java.util.ArrayList;
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
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.mob.entity.misc.EntityStickyBomb;
import xol.lostinfinity.projectile.entity.EntityStickyProjectile;
public class ItemStickyBombLauncher extends ItemCooldown implements ICustomHoldPose, IModeSelect {
    private List<EntityStickyBomb> bombs;
    public ItemStickyBombLauncher(String regName) {
        super(regName);
        this.bombs = new ArrayList();
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!showDurabilityBar(playerIn.func_184586_b(handIn))) {
            if (!worldIn.field_72995_K) {
                ItemStack stack = playerIn.func_184586_b(handIn);
                EntityStickyProjectile shot = new EntityStickyProjectile(worldIn, playerIn);
                shot.setThrower(playerIn);
                shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.5f, 1.0f);
                shot.setStack(stack);
                worldIn.func_72838_d(shot);
            }
            playerIn.func_184185_a(SoundInit.ITEM_STARSTORM, 1.0f, 1.0f);
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    public void addBomb(EntityStickyBomb bomb) {
        this.bombs.add(bomb);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 500;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Yellow + "Fires sticky bombs.");
        tooltip.add(TextFmt.Red + "Bombs can be detonated to deal 50% Max Health Damage.");
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!showDurabilityBar(stack)) {
            World world = player.field_70170_p;
            if (!world.field_72995_K) {
                for (EntityStickyBomb bomb : this.bombs) {
                    if (bomb != null && !bomb.field_70128_L) {
                        bomb.explosionEffect();
                    }
                }
                this.bombs.clear();
            }
            stack.func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
    }
}
