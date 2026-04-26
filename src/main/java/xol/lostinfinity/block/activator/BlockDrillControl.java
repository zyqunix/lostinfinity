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
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityDrillConsole;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
public class BlockDrillControl extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 5);
    public BlockDrillControl(String name) {
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
        TileEntity te;
        if (!worldIn.field_72995_K) {
            if (playerIn.func_70093_af()) {
                int meta = func_176201_c(state);
                if (meta < 5) {
                    worldIn.func_175656_a(pos, func_176203_a(meta + 1));
                    return true;
                }
                worldIn.func_175656_a(pos, func_176203_a(0));
                return true;
            }
            int meta2 = func_176201_c(state);
            BlockPos checkPos = GalaxyCoordinates.lucientOreConsolePos();
            IBlockState checkState = worldIn.func_180495_p(checkPos);
            Block block = checkState.func_177230_c();
            if ((block instanceof BlockDrillConsole) && (te = worldIn.func_175625_s(checkPos)) != null && (te instanceof TileEntityDrillConsole)) {
                ((TileEntityDrillConsole) te).moveDrill(meta2);
                return true;
            }
            return true;
        }
        return true;
    }
}
