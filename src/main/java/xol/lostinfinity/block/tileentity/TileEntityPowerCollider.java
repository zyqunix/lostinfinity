package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import xol.lostinfinity.dimension.data.PowerColliderGrid;
import xol.lostinfinity.dimension.data.PowerColliderNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityPowerCollider.class */
public class TileEntityPowerCollider extends TileEntity implements ITickable {
    private static final int dimension = 25;
    private static final Vec3i offset = new Vec3i(0, 0, 0);
    private static final int numTicks = 3;
    private static final int numPaths = 2;
    private static final int minLength = 25;
    private Vec3i upDir = new Vec3i(0, 0, 1);
    private Vec3i leftDir = new Vec3i(1, 0, 0);
    private ArrayList<BlockPos> tiles = new ArrayList<>();
    private boolean game = false;
    private BlockPos ref = null;
    private ArrayList<ArrayList<PowerColliderNode>> tracks = null;
    private int[] trackIndices = null;
    private PowerColliderGrid grid = null;
    private boolean[] trackActive = null;
    private boolean wonLast = false;

    public void setUpDir(Vec3i upDir) {
        this.upDir = upDir;
    }

    public void setLeftDir(Vec3i leftDir) {
        this.leftDir = leftDir;
    }

    private Vec3i findTileDir(BlockPos pos) {
        ArrayList<Vec3i> dirs = new ArrayList<>();
        dirs.add(new Vec3i(1, 0, 1));
        dirs.add(new Vec3i(-1, 0, 1));
        dirs.add(new Vec3i(-1, 0, -1));
        dirs.add(new Vec3i(1, 0, -1));
        for (Vec3i dir : dirs) {
            if (this.field_145850_b.func_180495_p(pos.func_177971_a(dir)).func_177230_c().equals(BlockInit.powerColliderTrack)) {
                return dir;
            }
        }
        return null;
    }

    public void reset() {
        this.tiles.clear();
        BlockPos ref = this.field_174879_c.func_177971_a(offset);
        Vec3i dir = new Vec3i(-1, 0, 1);
        Vec3i upDir = new Vec3i(0, 0, 1);
        Vec3i leftDir = new Vec3i(-1, 0, 0);
        BlockPos ref2 = ref.func_177971_a(dir);
        this.ref = ref2;
        this.grid = new PowerColliderGrid(25, 25, 25, numPaths);
        this.tracks = this.grid.getPaths();
        if (this.tracks == null || this.tracks.size() < 1) {
            return;
        }
        this.trackIndices = new int[this.tracks.size()];
        this.trackActive = new boolean[this.tracks.size()];
        for (int k = 0; k < this.trackIndices.length; k++) {
            this.trackIndices[k] = this.tracks.get(k).size() - 1;
            this.trackActive[k] = false;
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                BlockPos tile = ref2.func_177982_a((upDir.func_177958_n() * i) + (leftDir.func_177958_n() * j), 0, (upDir.func_177952_p() * i) + (leftDir.func_177952_p() * j));
                PowerColliderNode node = this.grid.getNodeAtLocation(i, j);
                boolean isTrack = false;
                boolean start = false;
                boolean end = false;
                if (node != null) {
                    Iterator<ArrayList<PowerColliderNode>> it = this.tracks.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ArrayList<PowerColliderNode> track = it.next();
                        if (track.contains(node)) {
                            if (track.indexOf(node) == track.size() - 1) {
                                start = true;
                            }
                            if (track.indexOf(node) == 0) {
                                end = true;
                            }
                            isTrack = true;
                        }
                    }
                }
                if (isTrack) {
                    if (start) {
                        this.field_145850_b.func_175656_a(tile, BlockInit.powerColliderTrack.func_176203_a(1));
                    } else if (end) {
                        this.field_145850_b.func_175656_a(tile, BlockInit.powerColliderTrack.func_176203_a(numPaths));
                    } else {
                        this.field_145850_b.func_175656_a(tile, BlockInit.powerColliderTrack.func_176203_a(0));
                    }
                } else {
                    this.field_145850_b.func_175656_a(tile, BlockInit.neosteelBlack.func_176223_P());
                }
                this.tiles.add(tile);
            }
        }
        this.upDir = upDir;
        this.leftDir = leftDir;
        this.game = true;
        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.MANUFACTURE_MACHINE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    public void setTiles(ArrayList<BlockPos> tiles) {
        this.tiles = tiles;
    }

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_82737_E() % 3 == 0 && this.game && this.trackActive != null) {
            int count = 0;
            if (this.wonLast) {
                count = 0 + 1;
                this.wonLast = false;
            }
            for (int i = 0; i < this.trackActive.length; i++) {
                if (this.trackActive[i]) {
                    ArrayList<PowerColliderNode> track = this.tracks.get(i);
                    int index = this.trackIndices[i];
                    PowerColliderNode trackNode = track.get(index);
                    BlockPos trackPos = this.ref.func_177982_a((this.upDir.func_177958_n() * trackNode.getX()) + (this.leftDir.func_177958_n() * trackNode.getZ()), 0, (this.upDir.func_177952_p() * trackNode.getX()) + (this.leftDir.func_177952_p() * trackNode.getZ()));
                    if (index > 0) {
                        this.field_145850_b.func_175656_a(trackPos, BlockInit.powerColliderTrack.func_176203_a(0));
                        this.trackIndices[i] = index - 1;
                        PowerColliderNode trackNode2 = track.get(index - 1);
                        this.field_145850_b.func_175656_a(this.ref.func_177982_a((this.upDir.func_177958_n() * trackNode2.getX()) + (this.leftDir.func_177958_n() * trackNode2.getZ()), 0, (this.upDir.func_177952_p() * trackNode2.getX()) + (this.leftDir.func_177952_p() * trackNode2.getZ())), BlockInit.powerColliderTrack.func_176203_a(1));
                    } else {
                        this.field_145850_b.func_175656_a(trackPos, BlockInit.powerColliderTrack.func_176203_a(numPaths));
                        this.trackIndices[i] = track.size() - 1;
                        PowerColliderNode trackNode3 = track.get(track.size() - 1);
                        this.field_145850_b.func_175656_a(this.ref.func_177982_a((this.upDir.func_177958_n() * trackNode3.getX()) + (this.leftDir.func_177958_n() * trackNode3.getZ()), 0, (this.upDir.func_177952_p() * trackNode3.getX()) + (this.leftDir.func_177952_p() * trackNode3.getZ())), BlockInit.powerColliderTrack.func_176203_a(1));
                        this.trackActive[i] = false;
                        count++;
                        this.wonLast = true;
                    }
                }
            }
            if (count == numPaths) {
                EntityItem item = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + numPaths, this.field_174879_c.func_177952_p(), new ItemStack(ItemInit.powerClamp, 1));
                item.field_70159_w = 0.0d;
                item.field_70181_x = 0.0d;
                item.field_70179_y = 0.0d;
                this.field_145850_b.func_72838_d(item);
                this.game = false;
            }
        }
    }

    public void light(BlockPos pos) {
        int j;
        int i;
        if (this.game && this.ref != null && this.grid != null) {
            if (this.upDir.func_177958_n() == 0) {
                i = Math.abs(pos.func_177952_p() - this.ref.func_177952_p());
                j = Math.abs(pos.func_177958_n() - this.ref.func_177958_n());
            } else {
                j = Math.abs(pos.func_177952_p() - this.ref.func_177952_p());
                i = Math.abs(pos.func_177958_n() - this.ref.func_177958_n());
            }
            PowerColliderNode node = this.grid.getNodeAtLocation(i, j);
            if (node != null && this.trackIndices != null && this.trackActive != null) {
                for (int k = 0; k < this.trackIndices.length; k++) {
                    ArrayList<PowerColliderNode> track = this.tracks.get(k);
                    int index = this.trackIndices[k];
                    if (index == track.size() - 1) {
                        this.field_145850_b.func_184133_a((EntityPlayer) null, pos, SoundInit.GENERIC_UI_1, SoundCategory.BLOCKS, 1.0f, 0.8f * this.field_145850_b.field_73012_v.nextFloat() * 0.4f);
                        PowerColliderNode trackNode = track.get(index);
                        BlockPos trackPos = this.ref.func_177982_a((this.upDir.func_177958_n() * trackNode.getX()) + (this.leftDir.func_177958_n() * trackNode.getZ()), 0, (this.upDir.func_177952_p() * trackNode.getX()) + (this.leftDir.func_177952_p() * trackNode.getZ()));
                        if (trackPos.equals(pos)) {
                            this.trackActive[k] = true;
                            return;
                        }
                    }
                }
            }
        }
    }
}
