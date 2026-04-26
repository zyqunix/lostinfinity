package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockTimelineStabilizerArrow extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 3);
    public BlockTimelineStabilizerArrow(String name) {
        super(name);
    }
    public static Vec3i getRotatedVec(Vec3i vec, double rad) {
        return new Vec3i(Math.round(((double) vec.func_177958_n()) * Math.cos(rad)) + Math.round(((double) vec.func_177952_p()) * Math.sin(rad)), vec.func_177956_o(), Math.round(((double) (-vec.func_177958_n())) * Math.sin(rad)) + Math.round(((double) vec.func_177952_p()) * Math.cos(rad)));
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        double rad;
        ItemStack stack = playerIn.func_184586_b(hand);
        if (!worldIn.field_72995_K && playerIn.func_70093_af()) {
            int meta = func_176201_c(state);
            if (meta < 3) {
                worldIn.func_175656_a(pos, func_176203_a(meta + 1));
                return true;
            }
            worldIn.func_175656_a(pos, func_176203_a(0));
            return true;
        }
        if (!worldIn.field_72995_K) {
            NBTTagCompound compound = stack.func_77978_p();
            if (stack.func_77942_o() && compound.func_74764_b("1x") && compound.func_74764_b("2x")) {
                ArrayList<BlockPos> pillars = new ArrayList<>();
                Vec3i offset = new Vec3i(0, 3, 0);
                pillars.add(new BlockPos(compound.func_74762_e("1x") + offset.func_177958_n(), compound.func_74762_e("1y") + offset.func_177956_o(), compound.func_74762_e("1z") + offset.func_177952_p()));
                pillars.add(new BlockPos(compound.func_74762_e("2x") + offset.func_177958_n(), compound.func_74762_e("2y") + offset.func_177956_o(), compound.func_74762_e("2z") + offset.func_177952_p()));
                double closestDist = 999999.0d;
                BlockPos closest = null;
                for (BlockPos pillar : pillars) {
                    double dist = pillar.func_185332_f(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
                    if (dist < closestDist) {
                        closestDist = dist;
                        closest = pillar;
                    }
                }
                if (closest != null) {
                    for (int i = -40; i < 40; i++) {
                        for (int j = -40; j < 40; j++) {
                            for (int k = -40; k < 40; k++) {
                                BlockPos check = pos.func_177982_a(i, j, k);
                                if (worldIn.func_180495_p(check).func_177230_c().equals(BlockInit.timelineStabilizerCapsule)) {
                                    int pillarNum = pillars.indexOf(closest);
                                    BlockPos ref = pillars.get(pillarNum);
                                    int xDir = 1;
                                    int zDir = 1;
                                    if (worldIn.func_180495_p(ref.func_177982_a(-1, 0, 0)).func_177230_c().equals(BlockInit.labyrinthGlassBlue)) {
                                        xDir = -1;
                                    }
                                    if (worldIn.func_180495_p(ref.func_177982_a(0, 0, -1)).func_177230_c().equals(BlockInit.labyrinthGlassBlue)) {
                                        zDir = -1;
                                    }
                                    if (xDir == 1) {
                                        if (zDir == 1) {
                                            rad = 0.0d;
                                        } else {
                                            rad = 1.5707963267948966d;
                                        }
                                    } else if (zDir == 1) {
                                        rad = 4.71238898038469d;
                                    } else {
                                        rad = 3.141592653589793d;
                                    }
                                    ref.func_177982_a(xDir, 0, zDir);
                                    int meta2 = func_176201_c(state);
                                    int xDiff = (check.func_177958_n() - closest.func_177958_n()) - (1 * xDir);
                                    int zDiff = (check.func_177952_p() - closest.func_177952_p()) - (1 * zDir);
                                    Vec3i cornerDir = null;
                                    boolean byRight = xDiff == 0;
                                    boolean byLeft = xDiff == 29 / 4;
                                    boolean byFront = zDiff == 0;
                                    boolean byBack = zDiff == 29 / 4;
                                    boolean z = xDiff == -1;
                                    boolean z2 = xDiff == (29 / 4) + 1;
                                    boolean z3 = zDiff == (29 / 4) + 1;
                                    boolean z4 = zDiff == -1;
                                    if (rad == 0.0d) {
                                        byRight = xDiff == 0;
                                        byLeft = xDiff == 29 / 4;
                                        byFront = zDiff == 0;
                                        byBack = zDiff == 29 / 4;
                                        boolean z5 = xDiff == -1;
                                        boolean z6 = xDiff == (29 / 4) + 1;
                                        boolean z7 = zDiff == (29 / 4) + 1;
                                        boolean z8 = zDiff == -1;
                                    } else if (rad == 1.5707963267948966d) {
                                        byRight = zDiff == 0;
                                        byLeft = zDiff == (-(29 / 4));
                                        byBack = xDiff == 29 / 4;
                                        byFront = xDiff == 0;
                                        boolean z9 = zDiff == 1;
                                        boolean z10 = zDiff == (-(29 / 4)) - 1;
                                        boolean z11 = xDiff == -1;
                                        boolean z12 = xDiff == (29 / 4) + 1;
                                    } else if (rad == 3.141592653589793d) {
                                        byLeft = xDiff == (-29) / 4;
                                        byRight = xDiff == 0;
                                        byBack = zDiff == (-(29 / 4));
                                        byFront = zDiff == 0;
                                        boolean z13 = xDiff == ((-29) / 4) - 1;
                                        boolean z14 = xDiff == 1;
                                        boolean z15 = zDiff == 1;
                                        boolean z16 = zDiff == (-(29 / 4)) - 1;
                                    } else if (rad == 4.71238898038469d) {
                                        byLeft = zDiff == 29 / 4;
                                        byRight = zDiff == 0;
                                        byFront = xDiff == 0;
                                        byBack = xDiff == (-(29 / 4));
                                        boolean z17 = zDiff == (29 / 4) + 1;
                                        boolean z18 = zDiff == -1;
                                        boolean z19 = xDiff == (-(29 / 4)) - 1;
                                        boolean z20 = xDiff == 1;
                                    }
                                    Vec3i dir = new Vec3i(0, 0, 0);
                                    switch (meta2) {
                                        case 0:
                                            if (byFront && !byRight) {
                                                dir = new Vec3i(-1, 0, 0);
                                            } else if (byRight && !byBack) {
                                                dir = new Vec3i(0, 0, 1);
                                            } else if (byBack && !byLeft) {
                                                dir = new Vec3i(1, 0, 0);
                                            } else if (byLeft && !byFront) {
                                                dir = new Vec3i(0, 0, -1);
                                            }
                                            break;
                                        case 1:
                                            dir = new Vec3i(0, 1, 0);
                                            break;
                                        case 2:
                                            if (byFront && !byLeft) {
                                                dir = new Vec3i(1, 0, 0);
                                            } else if (byRight && !byFront) {
                                                dir = new Vec3i(0, 0, -1);
                                            } else if (byBack && !byRight) {
                                                dir = new Vec3i(-1, 0, 0);
                                            } else if (byLeft && !byBack) {
                                                dir = new Vec3i(0, 0, 1);
                                            }
                                            break;
                                        case 3:
                                            dir = new Vec3i(0, -1, 0);
                                            break;
                                    }
                                    Vec3i dir2 = getRotatedVec(dir, rad);
                                    int relativeY = check.func_177973_b(closest).func_177956_o();
                                    if (relativeY >= 11) {
                                        return true;
                                    }
                                    if (0 != 0) {
                                        cornerDir = getRotatedVec(null, rad);
                                    }
                                    if (worldIn.func_180495_p(check.func_177971_a(dir2)).func_177230_c().equals(BlockInit.timelineStabilizerGoal)) {
                                        if (pillarNum == 0) {
                                            BlockPos next = pillars.get(pillarNum + 1);
                                            int nextXDir = 1;
                                            int nextZDir = 1;
                                            if (worldIn.func_180495_p(next.func_177982_a(-1, 0, 0)).func_177230_c().equals(BlockInit.labyrinthGlassBlue)) {
                                                nextXDir = -1;
                                            }
                                            if (worldIn.func_180495_p(next.func_177982_a(0, 0, -1)).func_177230_c().equals(BlockInit.labyrinthGlassBlue)) {
                                                nextZDir = -1;
                                            }
                                            if (nextXDir == 1) {
                                                if (nextZDir == 1) {
                                                }
                                            } else if (nextZDir == 1) {
                                            }
                                            BlockPos nextRef = next.func_177982_a(nextXDir * 1, -4, nextZDir * 1);
                                            worldIn.func_175698_g(check);
                                            worldIn.func_175656_a(nextRef, BlockInit.timelineStabilizerCapsule.func_176223_P());
                                            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MYSTERY_VOICE_TRANSMISSION, SoundCategory.BLOCKS, 1.5f, 1.0f);
                                            return true;
                                        }
                                        worldIn.func_175698_g(check);
                                        win(check.func_177971_a(dir2), worldIn, playerIn);
                                        return true;
                                    }
                                    if ((worldIn.func_175623_d(check.func_177971_a(dir2)) || (cornerDir != null && worldIn.func_175623_d(check.func_177971_a(cornerDir)))) && worldIn.func_175623_d(check.func_177971_a(dir2))) {
                                        worldIn.func_175656_a(check.func_177971_a(dir2), BlockInit.timelineStabilizerCapsule.func_176223_P());
                                        worldIn.func_175698_g(check);
                                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GEAR_MACHINE_2, SoundCategory.BLOCKS, 1.5f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    private void win(BlockPos pos, World worldIn, EntityPlayer playerIn) {
        EntityItem reward = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, new ItemStack(ItemInit.multiTimelineDataChip));
        reward.field_70159_w = 0.0d;
        reward.field_70181_x = 0.0d;
        reward.field_70179_y = 0.0d;
        worldIn.func_72838_d(reward);
        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MYSTERY_VOICE_TRANSMISSION, SoundCategory.BLOCKS, 1.5f, 1.0f);
    }
    private boolean onTrack(BlockPos check, Chunk chunk) {
        ArrayList<Vec3i> positions = new ArrayList<>();
        positions.add(new Vec3i(1, 0, 0));
        positions.add(new Vec3i(-1, 0, 0));
        positions.add(new Vec3i(0, 0, 1));
        positions.add(new Vec3i(0, 0, -1));
        for (Vec3i pos : positions) {
            if (chunk.func_177435_g(check.func_177971_a(pos)).func_177230_c().equals(BlockInit.trackWall)) {
                return true;
            }
        }
        return false;
    }
    private void transfer(World worldIn, BlockPos pos) {
    }
    private boolean onTrack(BlockPos check, World worldIn) {
        ArrayList<Vec3i> positions = new ArrayList<>();
        positions.add(new Vec3i(1, 0, 0));
        positions.add(new Vec3i(-1, 0, 0));
        positions.add(new Vec3i(0, 0, 1));
        positions.add(new Vec3i(0, 0, -1));
        for (Vec3i pos : positions) {
            if (worldIn.func_180495_p(check.func_177971_a(pos)).func_177230_c().equals(BlockInit.trackWall)) {
                return true;
            }
        }
        return false;
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
}
