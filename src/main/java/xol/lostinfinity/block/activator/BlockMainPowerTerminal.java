package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.item.activate.ItemEncryptedPowerBlueprints;
public class BlockMainPowerTerminal extends BlockBasic {
    public BlockMainPowerTerminal(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184586_b(hand);
        if (held.func_77973_b() == ItemInit.wiredRing) {
            if (!worldIn.field_72995_K) {
                held.func_190918_g(1);
                EntityItem item = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.encryptedPowerBlueprints));
                item.field_70159_w = 0.0d;
                item.field_70181_x = 0.0d;
                item.field_70179_y = 0.0d;
                worldIn.func_72838_d(item);
                return true;
            }
            return true;
        }
        if (held.func_77973_b() == ItemInit.encryptedPowerBlueprints && !worldIn.field_72995_K) {
            ItemEncryptedPowerBlueprints scroll = (ItemEncryptedPowerBlueprints) held.func_77973_b();
            if (scroll.getCompletion(held) == 3) {
                held.func_190918_g(1);
                EntityItem item2 = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.electricalAccelerator));
                item2.field_70159_w = 0.0d;
                item2.field_70181_x = 0.0d;
                item2.field_70179_y = 0.0d;
                worldIn.func_72838_d(item2);
                return true;
            }
            return true;
        }
        return true;
    }
}
