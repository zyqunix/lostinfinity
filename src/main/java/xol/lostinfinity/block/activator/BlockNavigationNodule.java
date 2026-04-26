package xol.lostinfinity.block.activator;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.SoundInit;
public class BlockNavigationNodule extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 3);
    public BlockNavigationNodule(String name) {
        super(name);
    }
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(AMOUNT, 0);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            int meta = func_176201_c(state);
            if (meta < 3) {
                worldIn.func_175656_a(pos, func_176203_a(meta + 1));
            } else {
                worldIn.func_175656_a(pos, func_176203_a(0));
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GENERIC_UI_3, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    public IBlockState func_176203_a(int meta) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(meta));
    }
    public int func_176201_c(IBlockState state) {
        return ((Integer) state.func_177229_b(AMOUNT)).intValue();
    }
    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{AMOUNT});
    }
}
