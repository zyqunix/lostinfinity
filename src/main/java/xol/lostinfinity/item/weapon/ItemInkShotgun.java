package xol.lostinfinity.item.weapon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.projectile.entity.EntityInkShot;
public class ItemInkShotgun extends ItemCooldown implements ICustomHoldPose {
    public ItemInkShotgun(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K) {
                for (int i = 0; i < 5; i++) {
                    EntityInkShot shot = new EntityInkShot(worldIn, playerIn);
                    shot.setThrower(playerIn);
                    shot.func_184538_a(playerIn, playerIn.field_70125_A, playerIn.field_70177_z, 0.0f, 1.0f, 15.0f);
                    worldIn.func_72838_d(shot);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.PAINT_GUN, SoundCategory.PLAYERS, 1.0f, 0.5f + (worldIn.field_73012_v.nextFloat() * 0.4f));
            }
            playerIn.func_184586_b(handIn).func_77978_p().func_74772_a("lastUse", System.currentTimeMillis());
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 1000;
    }
}
