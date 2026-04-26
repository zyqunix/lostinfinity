package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicLight;
import xol.lostinfinity.block.tileentity.TileEntityPowerConduit;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
public class BlockPowerNodule extends BlockBasicLight {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 3);
    public BlockPowerNodule(String name) {
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
        BlockPos gameBlock;
        TileEntity tileEntity;
        if (!worldIn.field_72995_K && (gameBlock = findGameBlock(worldIn, pos)) != null && (tileEntity = worldIn.func_175625_s(gameBlock)) != null && (tileEntity instanceof TileEntityPowerConduit)) {
            TileEntityPowerConduit TE = (TileEntityPowerConduit) tileEntity;
            ArrayList<BlockPos> tiles = TE.getTiles();
            BlockPos connectorPos = null;
            Iterator<BlockPos> it = tiles.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos tilePos = it.next();
                if (func_176201_c(worldIn.func_180495_p(tilePos)) == 3) {
                    connectorPos = tilePos;
                    break;
                }
            }
            if (connectorPos != null) {
                boolean xDir = pos.func_177958_n() - connectorPos.func_177958_n() > 0;
                boolean zDir = pos.func_177952_p() - connectorPos.func_177952_p() > 0;
                Vec3i upDir = TE.getUpDir();
                Vec3i newDir = null;
                if (xDir) {
                    if (upDir.func_177958_n() > 0 || upDir.func_177958_n() < 0) {
                        newDir = new Vec3i(1, 0, 0);
                    }
                } else if (upDir.func_177958_n() > 0 || upDir.func_177958_n() < 0) {
                    newDir = new Vec3i(-1, 0, 0);
                }
                if (zDir) {
                    if (upDir.func_177952_p() > 0 || upDir.func_177952_p() < 0) {
                        newDir = new Vec3i(0, 0, 1);
                    }
                } else if (upDir.func_177952_p() > 0 || upDir.func_177952_p() < 0) {
                    newDir = new Vec3i(0, 0, -1);
                }
                BlockPos next = connectorPos.func_177971_a(newDir);
                IBlockState nextState = worldIn.func_180495_p(next);
                if (!nextState.func_177230_c().equals(BlockInit.powerNodule)) {
                    return true;
                }
                worldIn.func_184133_a((EntityPlayer) null, connectorPos, SoundInit.GENERIC_UI_2, SoundCategory.BLOCKS, 1.0f, 0.8f * worldIn.field_73012_v.nextFloat() * 0.4f);
                if (BlockInit.powerNodule.func_176201_c(nextState) == 2) {
                    worldIn.func_175656_a(next, BlockInit.powerNodule.func_176203_a(3));
                    worldIn.func_175656_a(connectorPos, BlockInit.powerNodule.func_176203_a(2));
                    return true;
                }
                if (BlockInit.powerNodule.func_176201_c(nextState) == 0) {
                    worldIn.func_175656_a(next, BlockInit.powerNodule.func_176203_a(1));
                    worldIn.func_175656_a(connectorPos, BlockInit.powerNodule.func_176203_a(2));
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private BlockPos findGameBlock(World worldIn, BlockPos pos) {
        Vec3i offset = new Vec3i(0, 2, 0);
        ArrayList<BlockPos> visited = new ArrayList<>();
        Stack<BlockPos> toVisit = new Stack<>();
        visited.add(pos);
        toVisit.addAll(getNeighbours(pos));
        while (!toVisit.isEmpty()) {
            BlockPos node = toVisit.pop();
            if (node != null) {
                Block block = worldIn.func_180495_p(node).func_177230_c();
                Block upBlock = worldIn.func_180495_p(node.func_177971_a(offset)).func_177230_c();
                if (block instanceof BlockPowerNodule) {
                    visited.add(node);
                    ArrayList<BlockPos> neighbours = getNeighbours(node);
                    for (BlockPos neighbour : neighbours) {
                        if (!visited.contains(neighbour)) {
                            toVisit.add(neighbour);
                        }
                    }
                } else if (upBlock instanceof BlockPowerConduit) {
                    return node.func_177971_a(offset);
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
