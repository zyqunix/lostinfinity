package xol.lostinfinity.block.tileentity;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import xol.lostinfinity.init.BlockInit;
public class TileEntityAlignmentDialGame extends TileEntity implements ITickable {
    private static final int spinTime = 1;
    private boolean active = false;
    private ArrayList<ArrayList<BlockPos>> rings = null;
    private boolean[] ringStates = null;
    private int dir = 0;
    private int timer = 0;
    private int ringCount = 0;
    public void func_73660_a() {
        if (this.active && !this.field_145850_b.field_72995_K) {
            if (this.timer <= 0 && this.ringStates != null) {
                this.timer = spinTime;
                for (int i = 0; i < this.ringStates.length; i += spinTime) {
                    if (this.ringStates[i]) {
                        rotateRing(i);
                    }
                }
                return;
            }
            this.timer -= spinTime;
        }
    }
    public void toggleRing(BlockPos pos) {
        int i = Math.max(Math.abs(pos.func_177958_n() - func_174877_v().func_177958_n()), Math.abs(pos.func_177952_p() - func_174877_v().func_177952_p())) - spinTime;
        if (this.ringStates != null && this.ringStates.length >= i - spinTime) {
            this.ringStates[i] = !this.ringStates[i];
        }
    }
    private void rotateRing(int i) {
        if (this.rings != null) {
            ArrayList<BlockPos> ring = this.rings.get(i);
            BlockPos lit = null;
            Iterator<BlockPos> it = ring.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockPos pos = it.next();
                if (this.field_145850_b.func_180495_p(pos).equals(BlockInit.alignmentTile.func_176203_a(spinTime))) {
                    lit = pos;
                    break;
                }
            }
            if (lit != null) {
                int dist = i + spinTime;
                Vec3i dir = null;
                int x = lit.func_177958_n() - func_174877_v().func_177958_n();
                int z = lit.func_177952_p() - func_174877_v().func_177952_p();
                if (x == dist && z > (-dist)) {
                    dir = new Vec3i(0, 0, -1);
                } else if (x == (-dist) && z < dist) {
                    dir = new Vec3i(0, 0, spinTime);
                } else if (z == dist && x < dist) {
                    dir = new Vec3i(spinTime, 0, 0);
                } else if (z == (-dist) && x > (-dist)) {
                    dir = new Vec3i(-1, 0, 0);
                }
                if (dir != null) {
                    BlockPos check = lit.func_177971_a(dir);
                    if (ring.contains(check)) {
                        this.field_145850_b.func_175656_a(check, BlockInit.alignmentTile.func_176203_a(spinTime));
                        this.field_145850_b.func_175656_a(lit, BlockInit.alignmentTile.func_176203_a(0));
                    }
                }
            }
        }
    }
    public int getRingCount() {
        return this.ringCount;
    }
    public String getDir() {
        switch (this.dir) {
            case 0:
                return "North";
            case spinTime :
                return "East";
            case 2:
                return "West";
            case 3:
                return "South";
            default:
                return "North";
        }
    }
    public void reset() {
        int ringCount = 0;
        this.rings = new ArrayList<>();
        boolean foundRings = false;
        while (!foundRings) {
            int dist = ringCount + spinTime;
            ArrayList<BlockPos> ring = new ArrayList<>();
            int i = -dist;
            while (true) {
                if (i > dist) {
                    break;
                }
                for (int j = -dist; j <= dist; j += spinTime) {
                    BlockPos check = func_174877_v().func_177982_a(i, 0, j);
                    int ringNum = Math.max(Math.abs(check.func_177958_n() - func_174877_v().func_177958_n()), Math.abs(check.func_177952_p() - func_174877_v().func_177952_p()));
                    if ((i != 0 || j != 0) && ringNum == dist) {
                        if (this.field_145850_b.func_180495_p(check).func_177230_c().equals(BlockInit.alignmentTile)) {
                            ring.add(check);
                            this.field_145850_b.func_175656_a(check, BlockInit.alignmentTile.func_176203_a(0));
                        } else {
                            foundRings = spinTime;
                            break;
                        }
                    }
                }
                i += spinTime;
            }
            if (!ring.isEmpty()) {
                this.rings.add(ring);
                ringCount += spinTime;
            }
        }
        this.ringCount = ringCount;
        if (this.ringCount > 0) {
            this.ringStates = new boolean[ringCount];
            for (ArrayList<BlockPos> ringPositions : this.rings) {
                int rand = this.field_145850_b.field_73012_v.nextInt(ringPositions.size());
                this.field_145850_b.func_175656_a(ringPositions.get(rand), BlockInit.alignmentTile.func_176203_a(spinTime));
            }
            this.active = true;
            this.dir = this.field_145850_b.field_73012_v.nextInt(4);
        }
    }
}
