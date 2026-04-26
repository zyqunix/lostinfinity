package xol.lostinfinity.util.coordinates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import xol.lostinfinity.client.TextFmt;
public class GalaxyCoordinates {
    public static AxisAlignedBB getBlueAABB() {
        return new AxisAlignedBB(new BlockPos(808, 20, 312), new BlockPos(839, 35, 343));
    }
    public static AxisAlignedBB getGreenAABB() {
        return new AxisAlignedBB(new BlockPos(808, 20, 344), new BlockPos(839, 35, 375));
    }
    public static AxisAlignedBB getPinkAABB() {
        return new AxisAlignedBB(new BlockPos(840, 20, 312), new BlockPos(871, 35, 344));
    }
    public static AxisAlignedBB getYellowAABB() {
        return new AxisAlignedBB(new BlockPos(840, 20, 344), new BlockPos(871, 35, 375));
    }
    public static AxisAlignedBB getSwordAABB() {
        return new AxisAlignedBB(new BlockPos(872, 20, 312), new BlockPos(935, 45, 375));
    }
    public static AxisAlignedBB getBombAABB() {
        return new AxisAlignedBB(new BlockPos(936, 20, 312), new BlockPos(999, 45, 375));
    }
    public static AxisAlignedBB getKnifeAABB() {
        return new AxisAlignedBB(new BlockPos(1000, 20, 312), new BlockPos(1063, 45, 375));
    }
    public static AxisAlignedBB getAugmenticonAABB() {
        return new AxisAlignedBB(new BlockPos(1547, 9, -579), new BlockPos(1605, 23, -523));
    }
    public static BlockPos galaxyDungeonEntry() {
        return new BlockPos(776, 41, 348);
    }
    public static BlockPos ChipTable() {
        return new BlockPos(-170, 26, -426);
    }
    public static BlockPos getLightReceiver() {
        return new BlockPos(694, 23, 354);
    }
    public static BlockPos SynchroniteSignal() {
        return new BlockPos(1193, 87, 55);
    }
    public static AxisAlignedBB getShockArenaAABB() {
        return new AxisAlignedBB(new BlockPos(616, 20, 312), new BlockPos(679, 32, 375));
    }
    public static AxisAlignedBB crusherAABB() {
        return new AxisAlignedBB(new BlockPos(2352, 31, -291), new BlockPos(2356, 34, -287));
    }
    public static AxisAlignedBB materializerOreAABB() {
        return new AxisAlignedBB(new BlockPos(-147, 24, -389), new BlockPos(-143, 26, -384));
    }
    public static AxisAlignedBB materializerOrganicsAABB() {
        return new AxisAlignedBB(new BlockPos(-147, 24, -395), new BlockPos(-143, 26, -390));
    }
    public static AxisAlignedBB materializerSacrificeAABB() {
        return new AxisAlignedBB(new BlockPos(-148, 24, -402), new BlockPos(-142, 27, -396));
    }
    public static AxisAlignedBB lucientDrillAABB() {
        return new AxisAlignedBB(new BlockPos(1075, 43, 223), new BlockPos(1109, 43, 281));
    }
    public static BlockPos lucientOreFunnelPos() {
        return new BlockPos(1122, 43, 239);
    }
    public static BlockPos lucientOreConsolePos() {
        return new BlockPos(1119, 42, 232);
    }
    public static BlockPos getGalaxyTeleporter(int index) {
        List<BlockPos> galaxyPortals = new ArrayList<>();
        galaxyPortals.add(new BlockPos(763, 26, 359));
        galaxyPortals.add(new BlockPos(-183, 20, -382));
        galaxyPortals.add(new BlockPos(993, 43, -384));
        galaxyPortals.add(new BlockPos(1536, 62, -787));
        galaxyPortals.add(new BlockPos(776, 41, 348));
        galaxyPortals.add(new BlockPos(-183, 19, -382));
        return galaxyPortals.get(index);
    }
    public static Map<BlockPos, String> securityWithMSG() {
        Map<BlockPos, String> specialGates = new HashMap<>();
        specialGates.put(new BlockPos(-180, 18, -478), TextFmt.Red + "Warning: Very large and dangerous lifeform detected in this cave system. Proceed with extreme caution.");
        return specialGates;
    }
    public static ArrayList<BlockPos> getCorePositions() {
        ArrayList<BlockPos> corePositions = new ArrayList<>();
        corePositions.add(new BlockPos(2342, 40, -107));
        corePositions.add(new BlockPos(2310, 40, -108));
        corePositions.add(new BlockPos(2267, 40, -100));
        corePositions.add(new BlockPos(2238, 40, -112));
        corePositions.add(new BlockPos(2217, 40, -99));
        return corePositions;
    }
}
