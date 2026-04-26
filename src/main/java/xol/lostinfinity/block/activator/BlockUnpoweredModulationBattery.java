package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockUnpoweredModulationBattery extends BlockBasicLight {
    public BlockUnpoweredModulationBattery(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.func_184586_b(hand);
        if (!stack.func_190926_b() && stack.func_77973_b().equals(ItemInit.fabricationPowerCore)) {
            if (!worldIn.field_72995_K) {
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GENERIC_UI_3, SoundCategory.MASTER, 1.0f, 1.0f);
                worldIn.func_175656_a(pos, BlockInit.modulationBattery.func_176223_P());
            }
            stack.func_190918_g(1);
            return true;
        }
        return false;
    }
}
