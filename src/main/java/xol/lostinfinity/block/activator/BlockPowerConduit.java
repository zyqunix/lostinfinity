package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityPowerConduit;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
public class BlockPowerConduit extends BlockBasic implements ITileEntityProvider {
    private static final Vec3i offset = new Vec3i(0, -2, 0);
    public BlockPowerConduit(String name) {
        super(name);
    }
    private boolean validInput(ItemStack stack) {
        return stack.func_77973_b().equals(ItemInit.blankOpticalDisc);
    }
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean func_180639_a(net.minecraft.world.World r15, net.minecraft.util.math.BlockPos r16, net.minecraft.block.state.IBlockState r17, net.minecraft.entity.player.EntityPlayer r18, net.minecraft.util.EnumHand r19, net.minecraft.util.EnumFacing r20, float r21, float r22, float r23) {
        /*
            Method dump skipped, instruction units count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.block.activator.BlockPowerConduit.func_180639_a(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand, net.minecraft.util.EnumFacing, float, float, float):boolean");
    }
    private void reset(World worldIn, BlockPos pos, IBlockState state, boolean end) {
        TileEntity tileEntity;
        BlockPos ref = pos.func_177971_a(offset);
        Vec3i dir = findTileDir(worldIn, ref);
        Vec3i upDir = new Vec3i(0, 0, 1);
        Vec3i leftDir = new Vec3i(1, 0, 0);
        if (dir.func_177958_n() < 0) {
            if (dir.func_177952_p() < 0) {
                upDir = new Vec3i(0, 0, -1);
                leftDir = new Vec3i(-1, 0, 0);
            } else {
                upDir = new Vec3i(-1, 0, 0);
                leftDir = new Vec3i(0, 0, 1);
            }
        } else if (dir.func_177952_p() < 0) {
            upDir = new Vec3i(1, 0, 0);
            leftDir = new Vec3i(0, 0, -1);
        }
        BlockPos ref2 = ref.func_177971_a(dir);
        ArrayList<BlockPos> tiles = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                BlockPos tile = ref2.func_177982_a((upDir.func_177958_n() * i) + (leftDir.func_177958_n() * j), 0, (upDir.func_177952_p() * i) + (leftDir.func_177952_p() * j));
                worldIn.func_175656_a(tile, BlockInit.powerNodule.func_176203_a(0));
                tiles.add(tile);
            }
        }
        if (1 != 0 && (worldIn.func_180495_p(pos).func_177230_c() instanceof BlockPowerConduit) && (tileEntity = worldIn.func_175625_s(pos)) != null && (tileEntity instanceof TileEntityPowerConduit)) {
            TileEntityPowerConduit TE = (TileEntityPowerConduit) tileEntity;
            TE.setDirs(upDir, leftDir);
            TE.setTiles(tiles);
            TE.reset(11, 11);
            if (end) {
                TE.endGame();
            }
        }
    }
    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(1, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.powerNodule)) {
                return dir;
            }
        }
        return null;
    }
    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
    public boolean func_149716_u() {
        return true;
    }
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPowerConduit();
    }
}
