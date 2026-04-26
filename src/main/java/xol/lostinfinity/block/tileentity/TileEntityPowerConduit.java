package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityPowerConduit.class */
public class TileEntityPowerConduit extends TileEntity implements ITickable {
    private static final Vec3i offset = new Vec3i(0, -2, 0);
    private Vec3i upDir = null;
    private Vec3i leftDir = null;
    private ArrayList<BlockPos> tiles = null;
    private BlockPos ref = null;
    private int upCount = 0;
    private int leftCount = 0;
    ArrayList<LogData> logs = new ArrayList<>();
    private boolean game = false;
    private boolean lost = false;
    private int loseTimer = 0;

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityPowerConduit$LogData.class */
    private class LogData {
        ArrayList<BlockPos> positions;
        int timer;
        int progress;
        boolean moveLeft;
        BlockPos ref;
        private int row;

        private LogData(ArrayList<BlockPos> positions, int timer, boolean moveLeft, BlockPos ref, int row) {
            this.positions = null;
            this.timer = 100;
            this.progress = 0;
            this.moveLeft = true;
            this.ref = null;
            this.row = 0;
            this.positions = positions;
            this.timer = timer;
            this.moveLeft = moveLeft;
            this.progress = 0;
            this.ref = ref;
            this.row = row;
        }

        public void move(World world, Vec3i leftDir, Vec3i upDir, int leftCount, TileEntityPowerConduit TE) {
            BlockPos rowPos;
            BlockPos next;
            if (this.positions != null && this.ref != null) {
                this.progress++;
                if (this.progress >= this.timer) {
                    this.progress = 0;
                    if (this.moveLeft) {
                        rowPos = this.ref.func_177982_a(upDir.func_177958_n() * this.row, 0, upDir.func_177952_p() * this.row);
                    } else {
                        rowPos = this.ref.func_177982_a((upDir.func_177958_n() * this.row) + (leftDir.func_177958_n() * (leftCount - 1)), 0, (upDir.func_177952_p() * this.row) + (leftDir.func_177952_p() * (leftCount - 1)));
                    }
                    ArrayList<BlockPos> toReset = new ArrayList<>();
                    ArrayList<BlockPos> set = new ArrayList<>();
                    BlockPos connectorPos = null;
                    for (BlockPos position : this.positions) {
                        int dist = Math.abs(((rowPos.func_177958_n() - position.func_177958_n()) + rowPos.func_177952_p()) - position.func_177952_p());
                        if (dist < leftCount - 1) {
                            if (this.moveLeft) {
                                next = position.func_177982_a(leftDir.func_177958_n(), 0, leftDir.func_177952_p());
                            } else {
                                next = position.func_177982_a(-leftDir.func_177958_n(), 0, -leftDir.func_177952_p());
                            }
                            if (world.func_180495_p(position).func_177230_c().equals(BlockInit.powerNodule)) {
                                if (BlockInit.powerNodule.func_176201_c(world.func_180495_p(position)) == 3) {
                                    connectorPos = next;
                                }
                                set.add(next);
                                toReset.add(position);
                            }
                        } else {
                            BlockPos next2 = rowPos;
                            if (BlockInit.powerNodule.func_176201_c(world.func_180495_p(position)) == 3) {
                                TE.lose();
                                return;
                            } else {
                                world.func_175656_a(next2, BlockInit.powerNodule.func_176203_a(2));
                                set.add(next2);
                                toReset.add(position);
                            }
                        }
                    }
                    this.positions.clear();
                    this.positions.addAll(set);
                    toReset.removeAll(set);
                    for (BlockPos setPos : set) {
                        world.func_175656_a(setPos, BlockInit.powerNodule.func_176203_a(2));
                    }
                    for (BlockPos resetPos : toReset) {
                        world.func_175656_a(resetPos, BlockInit.powerNodule.func_176203_a(0));
                    }
                    if (connectorPos != null) {
                        world.func_175656_a(connectorPos, BlockInit.powerNodule.func_176203_a(3));
                    }
                }
            }
        }
    }

    public void lose() {
        this.lost = true;
        this.loseTimer = 0;
    }

    public void reset(int upCount, int leftCount) {
        BlockPos temp;
        BlockPos blockPosFunc_177982_a;
        this.ref = null;
        this.upCount = upCount;
        this.leftCount = leftCount;
        this.logs.clear();
        if (this.tiles != null && this.upDir != null && this.leftDir != null) {
            this.ref = this.field_174879_c.func_177971_a(this.leftDir).func_177971_a(this.upDir).func_177971_a(offset);
            for (int i = 0; i < this.leftCount; i++) {
                BlockPos temp2 = this.ref.func_177982_a(this.leftDir.func_177958_n() * i, 0, this.leftDir.func_177952_p() * i);
                BlockPos tempEnd = temp2.func_177982_a(this.upDir.func_177958_n() * (this.upCount - 1), 0, this.upDir.func_177952_p() * (this.upCount - 1));
                this.field_145850_b.func_175656_a(tempEnd, BlockInit.powerNodule.func_176203_a(2));
                if (i == this.leftCount / 2) {
                    this.field_145850_b.func_175656_a(temp2, BlockInit.powerNodule.func_176203_a(3));
                } else if (this.tiles.contains(temp2)) {
                    this.field_145850_b.func_175656_a(temp2, BlockInit.powerNodule.func_176203_a(2));
                }
            }
            boolean moveLeft = true;
            for (int i2 = 1; i2 < this.upCount - 1; i2++) {
                if (moveLeft) {
                    temp = this.ref.func_177982_a(this.upDir.func_177958_n() * i2, 0, this.upDir.func_177952_p() * i2);
                } else {
                    temp = this.ref.func_177982_a((this.upDir.func_177958_n() * i2) + (this.leftDir.func_177958_n() * (this.leftCount - 1)), 0, (this.upDir.func_177952_p() * i2) + (this.leftDir.func_177952_p() * (this.leftCount - 1)));
                }
                int startPointer = 0;
                int endPointer = 0;
                Random rand = new Random();
                int timer = rand.nextInt(10) + 8;
                while (endPointer < this.leftCount - 3) {
                    int spacesLeft = (this.leftCount - 1) - endPointer;
                    int logLength = rand.nextInt(2) + 1;
                    int spacing = Math.max(rand.nextInt(Math.min(Math.max(spacesLeft - logLength, 1), 3)), 2);
                    if (spacing + logLength > spacesLeft) {
                        spacing = spacesLeft - logLength;
                    }
                    endPointer += logLength + spacing;
                    ArrayList<BlockPos> logPositions = new ArrayList<>();
                    for (int k = startPointer; k < startPointer + logLength; k++) {
                        if (moveLeft) {
                            blockPosFunc_177982_a = temp.func_177982_a(k * this.leftDir.func_177958_n(), 0, k * this.leftDir.func_177952_p());
                        } else {
                            blockPosFunc_177982_a = temp.func_177982_a((-k) * this.leftDir.func_177958_n(), 0, (-k) * this.leftDir.func_177952_p());
                        }
                        BlockPos logPos = blockPosFunc_177982_a;
                        this.field_145850_b.func_175656_a(logPos, BlockInit.powerNodule.func_176203_a(2));
                        logPositions.add(logPos);
                    }
                    LogData log = new LogData(logPositions, timer, moveLeft, this.ref, i2);
                    this.logs.add(log);
                    startPointer = endPointer + 1;
                }
                moveLeft = !moveLeft;
            }
        }
        this.game = true;
        this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GEAR_MACHINE_2, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    public void setTiles(ArrayList<BlockPos> tiles) {
        this.tiles = tiles;
    }

    public void endGame() {
        this.game = false;
    }

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.lost) {
                this.loseTimer++;
                if (this.loseTimer > 30) {
                    this.lost = false;
                    reset(this.upCount, this.leftCount);
                }
            }
            if (this.logs != null && this.logs.size() > 0 && this.game) {
                for (LogData log : this.logs) {
                    log.move(this.field_145850_b, this.leftDir, this.upDir, this.leftCount, this);
                }
            }
            if (this.tiles != null) {
                for (BlockPos tilePos : this.tiles) {
                    IBlockState state = this.field_145850_b.func_180495_p(tilePos);
                    Block block = state.func_177230_c();
                    if (block.func_176201_c(state) == 1) {
                        lose();
                    }
                }
            }
        }
    }

    public void setDirs(Vec3i upDir, Vec3i leftDir) {
        this.upDir = upDir;
        this.leftDir = leftDir;
    }

    public ArrayList<BlockPos> getTiles() {
        return this.tiles;
    }

    public Vec3i getUpDir() {
        return this.upDir;
    }
}
