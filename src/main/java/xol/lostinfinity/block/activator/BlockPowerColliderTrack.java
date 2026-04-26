package xol.lostinfinity.block.activator;
import java.util.ArrayList;
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
import xol.lostinfinity.block.tileentity.TileEntityPowerCollider;
public class BlockPowerColliderTrack extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 2);
    private static final Vec3i offset = new Vec3i(0, 0, 0);
    public BlockPowerColliderTrack(String name) {
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
        BlockPos collider;
        TileEntity tileEntity;
        if (!worldIn.field_72995_K && (collider = findCollider(worldIn, pos)) != null && (tileEntity = worldIn.func_175625_s(collider)) != null && (tileEntity instanceof TileEntityPowerCollider)) {
            TileEntityPowerCollider TE = (TileEntityPowerCollider) tileEntity;
            TE.light(pos);
            return true;
        }
        return true;
    }
    private BlockPos findCollider(World worldIn, BlockPos pos) {
        BlockPos ref = pos.func_177971_a(offset);
        for (int i = -30; i < 30; i++) {
            for (int j = -30; j < 30; j++) {
                BlockPos check = ref.func_177982_a(i, 0, j);
                Block block = worldIn.func_180495_p(check).func_177230_c();
                if (block instanceof BlockPowerCollider) {
                    return check;
                }
            }
        }
        return null;
    }
    ArrayList<BlockPos> getNeighbours(BlockPos pos) {
        ArrayList<BlockPos> neighbours = new ArrayList<>();
        neighbours.add(pos.func_177982_a(1, 0, 0));
        neighbours.add(pos.func_177982_a(-1, 0, 0));
        neighbours.add(pos.func_177982_a(1, 0, 1));
        neighbours.add(pos.func_177982_a(1, 0, -1));
        neighbours.add(pos.func_177982_a(-1, 0, 1));
        neighbours.add(pos.func_177982_a(-1, 0, -1));
        neighbours.add(pos.func_177982_a(0, 0, 1));
        neighbours.add(pos.func_177982_a(0, 0, -1));
        return neighbours;
    }
}
