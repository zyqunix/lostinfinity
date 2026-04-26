package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityNavigationDevice.class */
public class TileEntityNavigationDevice extends TileEntity implements ITickable {
    private static final int numArrows = 8;
    private static final Vec3i offset = new Vec3i(0, 0, 0);
    public boolean active = false;
    private Vec3i upDir = null;
    private Vec3i rightDir = null;
    private BlockPos cornerPos = null;
    private ArrayList<BlockPos> sequencePositions = null;
    private int index = 0;
    private int rows = 0;
    private int columns = 0;
    private int arrowTimer = 25;
    private int timer = 0;
    private BlockPos[][] grid = (BlockPos[][]) null;
    ArrayList<Integer> sequence = null;

    public void activate() {
        this.active = true;
        if (!this.field_145850_b.field_72995_K) {
            startGame();
        }
    }

    private void findDirs() {
        BlockPos ref = func_174877_v().func_177971_a(offset);
        if (this.field_145850_b.func_180495_p(ref.func_177982_a(-1, 0, 0)).func_177230_c() == BlockInit.navigationNodule) {
            this.upDir = new Vec3i(1, 0, 0);
            this.rightDir = new Vec3i(0, 0, 1);
            return;
        }
        if (this.field_145850_b.func_180495_p(ref.func_177982_a(1, 0, 0)).func_177230_c() == BlockInit.navigationNodule) {
            this.upDir = new Vec3i(-1, 0, 0);
            this.rightDir = new Vec3i(0, 0, -1);
        } else if (this.field_145850_b.func_180495_p(ref.func_177982_a(0, 0, 1)).func_177230_c() == BlockInit.navigationNodule) {
            this.upDir = new Vec3i(0, 0, -1);
            this.rightDir = new Vec3i(1, 0, 0);
        } else if (this.field_145850_b.func_180495_p(ref.func_177982_a(0, 0, -1)).func_177230_c() == BlockInit.navigationNodule) {
            this.upDir = new Vec3i(0, 0, 1);
            this.rightDir = new Vec3i(-1, 0, 0);
        }
    }

    private void startGame() {
        BlockPos cur;
        int newNum;
        findDirs();
        BlockPos ref = func_174877_v().func_177971_a(offset);
        BlockPos cur2 = ref.func_177973_b(this.upDir);
        int upCount = 0;
        while (this.field_145850_b.func_180495_p(cur2).func_177230_c() == BlockInit.navigationNodule) {
            cur2 = cur2.func_177973_b(this.upDir);
            upCount++;
        }
        BlockPos blockPosFunc_177971_a = cur2.func_177971_a(this.upDir);
        while (true) {
            cur = blockPosFunc_177971_a;
            if (this.field_145850_b.func_180495_p(cur).func_177230_c() != BlockInit.navigationNodule) {
                break;
            } else {
                blockPosFunc_177971_a = cur.func_177973_b(this.rightDir);
            }
        }
        this.cornerPos = cur.func_177971_a(this.rightDir);
        BlockPos temp = this.cornerPos.func_177971_a(this.rightDir);
        int rightCount = 0;
        while (this.field_145850_b.func_180495_p(temp).func_177230_c() == BlockInit.navigationNodule) {
            temp = temp.func_177971_a(this.rightDir);
            rightCount++;
        }
        this.grid = new BlockPos[rightCount][upCount];
        this.columns = rightCount;
        this.rows = upCount;
        for (int i = 0; i < rightCount; i++) {
            for (int j = 0; j < upCount; j++) {
                BlockPos gridPos = this.cornerPos.func_177982_a((this.rightDir.func_177958_n() * i) + (this.upDir.func_177958_n() * j), 0, (this.rightDir.func_177952_p() * i) + (this.upDir.func_177952_p() * j));
                this.grid[i][j] = gridPos;
            }
        }
        int randCol = this.field_145850_b.field_73012_v.nextInt(this.columns);
        int randRow = this.field_145850_b.field_73012_v.nextInt(this.rows);
        BlockPos startPos = getGridPos(randCol, randRow);
        this.field_145850_b.func_175656_a(startPos, BlockInit.navigationNodule.func_176203_a(1));
        if (startPos == null) {
            return;
        }
        this.sequencePositions = new ArrayList<>();
        this.sequencePositions.add(startPos);
        this.sequence = new ArrayList<>();
        BlockPos curPos = startPos;
        for (int i2 = 0; i2 < numArrows; i2++) {
            int dirNum = this.field_145850_b.field_73012_v.nextInt(4);
            int colorNum = this.field_145850_b.field_73012_v.nextInt(3);
            Vec3i toAdd = getDir(dirNum);
            int iterations = 0;
            boolean noDir = false;
            BlockPos checkPos = curPos.func_177971_a(toAdd);
            while (true) {
                if (!this.sequencePositions.contains(checkPos) && this.field_145850_b.func_180495_p(checkPos).func_177230_c() == BlockInit.navigationNodule) {
                    break;
                }
                int iNextInt = this.field_145850_b.field_73012_v.nextInt(4);
                while (true) {
                    newNum = iNextInt;
                    if (newNum != dirNum) {
                        break;
                    } else {
                        iNextInt = this.field_145850_b.field_73012_v.nextInt(4);
                    }
                }
                dirNum = newNum;
                Vec3i toAdd2 = getDir(dirNum);
                checkPos = curPos.func_177971_a(toAdd2);
                iterations++;
                if (iterations > 5) {
                    noDir = true;
                    break;
                }
            }
            if (noDir) {
                break;
            }
            int metaNum = dirNum + (colorNum * 4) + 1;
            this.sequence.add(Integer.valueOf(metaNum));
            this.sequencePositions.add(checkPos);
            curPos = checkPos;
        }
        this.index = 0;
        this.timer = 0;
    }

    private Vec3i getDir(int dirNum) {
        Vec3i toAdd;
        switch (dirNum) {
            case 1:
                toAdd = new Vec3i(-this.rightDir.func_177958_n(), 0, -this.rightDir.func_177952_p());
                break;
            case 2:
                toAdd = new Vec3i(-this.upDir.func_177958_n(), 0, -this.upDir.func_177952_p());
                break;
            case 3:
                toAdd = this.rightDir;
                break;
            default:
                toAdd = this.upDir;
                break;
        }
        return toAdd;
    }

    private BlockPos getGridPos(int col, int row) {
        if (this.grid != null && col >= 0 && col < this.grid.length && row >= 0 && row < this.grid[col].length) {
            return this.grid[col][row];
        }
        return null;
    }

    public boolean isActive() {
        return this.active;
    }

    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.sequence != null && this.index < this.sequence.size()) {
                if (this.timer == this.arrowTimer) {
                    int meta = this.sequence.get(this.index).intValue();
                    this.field_145850_b.func_175656_a(func_174877_v(), BlockInit.navigationDevice.func_176203_a(meta));
                    this.field_145850_b.func_175690_a(this.field_174879_c, this);
                    this.field_145846_f = false;
                    this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.GAME_DING, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    this.index++;
                    this.timer = 0;
                    return;
                }
                this.timer++;
                return;
            }
            this.active = true;
        }
    }

    public void checkCompletion(EntityPlayer playerIn) {
        this.active = false;
        if (!this.field_145850_b.field_72995_K) {
            boolean foundAll = true;
            if (this.sequencePositions == null || this.sequencePositions.isEmpty()) {
                foundAll = false;
            } else {
                this.field_145850_b.func_175656_a(this.sequencePositions.get(0), BlockInit.navigationNodule.func_176203_a(0));
                this.sequencePositions.remove(0);
                if (this.sequence != null && this.sequencePositions.size() == this.sequence.size()) {
                    int i = 0;
                    while (true) {
                        if (i >= this.sequence.size()) {
                            break;
                        }
                        int meta = (this.sequence.get(i).intValue() - 1) / 4;
                        BlockPos seqPos = this.sequencePositions.get(i);
                        IBlockState state = this.field_145850_b.func_180495_p(seqPos);
                        Block block = state.func_177230_c();
                        if (block == BlockInit.navigationNodule) {
                            int blockMeta = block.func_176201_c(state) - 1;
                            if (blockMeta == meta) {
                                i++;
                            } else {
                                foundAll = false;
                                break;
                            }
                        } else {
                            foundAll = false;
                            break;
                        }
                    }
                }
            }
            reset();
            if (foundAll) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Green + "Completed the navigation sequence! Here is your reward!"));
                playerIn.func_191521_c(new ItemStack(ItemInit.mechanicalSextant, 1));
            } else {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Failed to complete the sequence. Try again"));
            }
        }
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    private void reset() {
        this.field_145850_b.func_175656_a(func_174877_v(), BlockInit.navigationDevice.func_176203_a(0));
        this.field_145846_f = false;
        for (int i = -1; i < this.columns + 1; i++) {
            for (int j = -1; j < this.rows + 1; j++) {
                BlockPos checkPos = this.cornerPos.func_177982_a((this.rightDir.func_177958_n() * i) + (this.upDir.func_177958_n() * j), 0, (this.rightDir.func_177952_p() * i) + (this.upDir.func_177952_p() * j));
                if (this.field_145850_b.func_180495_p(checkPos).func_177230_c() == BlockInit.navigationNodule) {
                    this.field_145850_b.func_175656_a(checkPos, BlockInit.navigationNodule.func_176203_a(0));
                }
            }
        }
        this.grid = (BlockPos[][]) null;
        this.columns = 0;
        this.rows = 0;
        this.active = false;
        this.timer = 0;
        this.sequence = null;
        this.sequencePositions = null;
    }
}
