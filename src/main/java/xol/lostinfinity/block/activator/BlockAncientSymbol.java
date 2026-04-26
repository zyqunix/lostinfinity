package xol.lostinfinity.block.activator;

import java.util.ArrayList;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.block.tileentity.TileEntityAncientSymbolInterpreter;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockAncientSymbol.class */
public class BlockAncientSymbol extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 15);

    public BlockAncientSymbol(String name) {
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
        BlockPos interpreter;
        TileEntity tileEntity;
        if (!worldIn.field_72995_K && func_176201_c(state) == 0 && (interpreter = findInterpreter(worldIn, pos)) != null && (tileEntity = worldIn.func_175625_s(interpreter)) != null && (tileEntity instanceof TileEntityAncientSymbolInterpreter)) {
            TileEntityAncientSymbolInterpreter TE = (TileEntityAncientSymbolInterpreter) tileEntity;
            TE.flip(pos);
            return true;
        }
        return true;
    }

    private BlockPos findInterpreter(World worldIn, BlockPos pos) {
        ArrayList<BlockPos> visited = new ArrayList<>();
        Stack<BlockPos> toVisit = new Stack<>();
        visited.add(pos);
        toVisit.addAll(getNeighbours(pos));
        while (!toVisit.isEmpty()) {
            BlockPos node = toVisit.pop();
            if (node != null) {
                Block block = worldIn.func_180495_p(node).func_177230_c();
                Block upBlock = worldIn.func_180495_p(node.func_177982_a(0, 3, 0)).func_177230_c();
                if (block instanceof BlockAncientSymbol) {
                    visited.add(node);
                    ArrayList<BlockPos> neighbours = getNeighbours(node);
                    for (BlockPos neighbour : neighbours) {
                        if (!visited.contains(neighbour)) {
                            toVisit.add(neighbour);
                        }
                    }
                } else if (upBlock instanceof BlockAncientSymbolInterpreter) {
                    return node.func_177982_a(0, 3, 0);
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
