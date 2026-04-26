package xol.lostinfinity.block.misc;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockLightReceiver extends BlockBasic {
    public BlockLightReceiver(String name) {
        super(name);
    }
    public void openGate(World worldIn, BlockPos pos) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i != 0 || j != 0 || k != 0) {
                        BlockPos check = pos.func_177982_a(i, j, k);
                        Block block = worldIn.func_180495_p(check).func_177230_c();
                        if (block.equals(BlockInit.galaxyGate)) {
                            propogatePass(worldIn, check, null, 1);
                        }
                    }
                }
            }
        }
    }
    public void closeGate(World worldIn, BlockPos pos) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i != 0 || j != 0 || k != 0) {
                        BlockPos check = pos.func_177982_a(i, j, k);
                        Block block = worldIn.func_180495_p(check).func_177230_c();
                        if (block.equals(BlockInit.galaxyGate)) {
                            propogatePass(worldIn, check, null, 0);
                        }
                    }
                }
            }
        }
    }
    public void propogatePass(World worldIn, BlockPos pos, ArrayList<BlockPos> visited, int meta) {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        if (!visited.contains(pos)) {
            visited.add(pos);
            worldIn.func_175656_a(pos, BlockInit.galaxyGate.func_176203_a(meta));
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        if (i != 0 || j != 0 || k != 0) {
                            BlockPos check = pos.func_177982_a(i, j, k);
                            Block block = worldIn.func_180495_p(check).func_177230_c();
                            if (block.equals(BlockInit.galaxyGate)) {
                                propogatePass(worldIn, check, visited, meta);
                            }
                        }
                    }
                }
            }
        }
    }
}
