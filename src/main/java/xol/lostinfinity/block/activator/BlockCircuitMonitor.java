package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityCircuitCalibrator;
import xol.lostinfinity.init.BlockInit;
public class BlockCircuitMonitor extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 2);
    public BlockCircuitMonitor(String name) {
        super(name);
    }
    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(AMOUNT, 0);
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
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity;
        if (!worldIn.field_72995_K) {
            Vec3i offset = new Vec3i(0, 0, 0);
            for (int i = -7; i <= 7; i++) {
                for (int k = -7; k <= 7; k++) {
                    BlockPos check = new BlockPos(pos.func_177971_a(offset).func_177982_a(i, 0, k));
                    IBlockState checkState = worldIn.func_180495_p(check);
                    Block checkBlock = checkState.func_177230_c();
                    if (checkBlock == BlockInit.circuitCalibrator && (tileEntity = worldIn.func_175625_s(check)) != null && (tileEntity instanceof TileEntityCircuitCalibrator)) {
                        TileEntityCircuitCalibrator calibrator = (TileEntityCircuitCalibrator) tileEntity;
                        int meta = func_176201_c(state);
                        calibrator.press(meta, pos, playerIn);
                    }
                }
            }
            return true;
        }
        return true;
    }
}
