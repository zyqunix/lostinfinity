package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
public class BlockRingSlideButton extends BlockBasic {
    public BlockRingSlideButton(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K) {
            BlockPos ref = pos.func_177982_a(0, -1, 0);
            ArrayList<BlockPos> tiles = new ArrayList<>();
            ArrayList<IBlockState> states = new ArrayList<>();
            Vec3i dir = findTileDir(worldIn, ref);
            if (dir != null) {
                BlockPos blockPosFunc_177971_a = ref.func_177971_a(dir);
                while (true) {
                    BlockPos nextPos = blockPosFunc_177971_a;
                    if (!(worldIn.func_180495_p(nextPos).func_177230_c() instanceof BlockRingTile)) {
                        break;
                    }
                    tiles.add(nextPos);
                    states.add(worldIn.func_180495_p(nextPos));
                    blockPosFunc_177971_a = nextPos.func_177971_a(dir);
                }
                ArrayList<IBlockState> newStates = new ArrayList<>();
                for (int i = 1; i < states.size(); i++) {
                    newStates.add(states.get(i));
                }
                newStates.add(states.get(0));
                for (BlockPos tile : tiles) {
                    worldIn.func_175656_a(tile, newStates.get(tiles.indexOf(tile)));
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private static Vec3i findTileDir(World worldIn, BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 0));
        dirs.add(new Vec3i(-1, 0, 0));
        dirs.add(new Vec3i(0, 0, 1));
        dirs.add(new Vec3i(0, 0, -1));
        for (Vec3i dir : dirs) {
            if (worldIn.func_180495_p(pos.func_177971_a(dir)).func_177230_c() instanceof BlockRingTile) {
                return dir;
            }
        }
        return null;
    }
}
