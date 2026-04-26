package xol.lostinfinity.dimension.cartographerrealm;
import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.dimension.util.WorldGenStructure;
public interface ICartographerRealm {
    String teleRoom();
    String gateRoom();
    String[] stairsRoom();
    String[] twoLevelRoom();
    String connectorRoom();
    String crossRoom();
    String gameRoom();
    String portalRoom();
    default void doGeneration(World world, Random rand, int chunkX, int chunkZ) {
        String top_name;
        String bottom_name;
        int posX = chunkX * 16;
        int posZ = chunkZ * 16;
        Rotation top_rot = randomRota(rand);
        Rotation bottom_rot = randomRota(rand);
        int x_off = Math.abs(chunkX % 10);
        int z_off = Math.abs(chunkZ % 10);
        if (x_off == 0 && z_off == 0) {
            if (chunkX == chunkZ) {
                bottom_name = gateRoom();
                top_name = teleRoom();
            } else {
                bottom_name = teleRoom();
                top_name = gateRoom();
            }
        } else {
            int style_pick = rand.nextInt(20);
            if (style_pick == 0) {
                top_name = crossRoom();
                bottom_name = portalRoom();
            } else if (style_pick == 2) {
                top_name = crossRoom();
                bottom_name = gameRoom();
            } else if (style_pick == 3) {
                top_name = gameRoom();
                bottom_name = crossRoom();
            } else if (style_pick == 4 || style_pick == 5) {
                top_name = crossRoom();
                bottom_name = crossRoom();
            } else if (style_pick == 6 || style_pick == 7) {
                String[] stairs = stairsRoom();
                top_name = stairs[0];
                bottom_name = stairs[1];
            } else if (style_pick == 8) {
                String[] stairs2 = twoLevelRoom();
                top_name = stairs2[0];
                bottom_name = stairs2[1];
            } else {
                top_name = connectorRoom();
                bottom_name = connectorRoom();
            }
        }
        new WorldGenStructure(top_name).generateWithRotation(world, rand, new BlockPos(posX + 8, 20, posZ + 8), top_rot);
        new WorldGenStructure(bottom_name).generateWithRotation(world, rand, new BlockPos(posX + 8, 11, posZ + 8), bottom_rot);
    }
    default Rotation randomRota(Random rand) {
        int rot_pick = rand.nextInt(4);
        switch (rot_pick) {
            case 0:
                return Rotation.NONE;
            case 1:
                return Rotation.CLOCKWISE_90;
            case 2:
                return Rotation.COUNTERCLOCKWISE_90;
            case 3:
                return Rotation.CLOCKWISE_180;
            default:
                return Rotation.NONE;
        }
    }
}
