package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.dimension.data.MazeMap;
import xol.lostinfinity.dimension.data.MazeNode;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockTimelineStabilizerConsole extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 1);
    public BlockTimelineStabilizerConsole(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.field_72995_K || playerIn.func_70093_af()) {
            if (!worldIn.field_72995_K) {
                int meta = func_176201_c(state);
                if (meta < 1) {
                    worldIn.func_175656_a(pos, func_176203_a(meta + 1));
                    return true;
                }
                worldIn.func_175656_a(pos, func_176203_a(0));
                return true;
            }
            return true;
        }
        int meta2 = func_176201_c(state);
        ItemStack stack = playerIn.func_184586_b(hand);
        if (stack.func_77973_b().equals(ItemInit.timelineMonitor)) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound compound = stack.func_77978_p();
            switch (meta2) {
                case 0:
                    if (compound.func_74764_b("1x") && compound.func_74764_b("2x")) {
                        generateMaze(stack, worldIn);
                        worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.MANUFACTURE_MACHINE, SoundCategory.BLOCKS, 1.5f, 1.0f);
                    } else {
                        compound.func_74768_a("1x", pos.func_177958_n());
                        compound.func_74768_a("1y", pos.func_177956_o());
                        compound.func_74768_a("1z", pos.func_177952_p());
                        playerIn.func_145747_a(new TextComponentString("Marked 1st Console"));
                    }
                    break;
                case 1:
                    compound.func_74768_a("2x", pos.func_177958_n());
                    compound.func_74768_a("2y", pos.func_177956_o());
                    compound.func_74768_a("2z", pos.func_177952_p());
                    playerIn.func_145747_a(new TextComponentString("Marked 2nd Console"));
                    break;
            }
            return true;
        }
        return true;
    }
    public static Vec3i getRotatedVec(Vec3i vec, double rad) {
        return new Vec3i(Math.round(((double) vec.func_177958_n()) * Math.cos(rad)) + Math.round(((double) vec.func_177952_p()) * Math.sin(rad)), vec.func_177956_o(), Math.round(((double) (-vec.func_177958_n())) * Math.sin(rad)) + Math.round(((double) vec.func_177952_p()) * Math.cos(rad)));
    }
    private void generateMaze(ItemStack stack, World worldIn) {
        double rad;
        MazeNode mazeNode;
        MazeMap map1 = new MazeMap(29, 16);
        MazeMap map2 = new MazeMap(29, 16);
        NBTTagCompound compound = stack.func_77978_p();
        ArrayList<BlockPos> pillars = new ArrayList<>();
        Vec3i offset = new Vec3i(0, 3, 0);
        pillars.add(new BlockPos(compound.func_74762_e("1x") + offset.func_177958_n(), compound.func_74762_e("1y") + offset.func_177956_o(), compound.func_74762_e("1z") + offset.func_177952_p()));
        pillars.add(new BlockPos(compound.func_74762_e("2x") + offset.func_177958_n(), compound.func_74762_e("2y") + offset.func_177956_o(), compound.func_74762_e("2z") + offset.func_177952_p()));
        worldIn.func_73046_m().func_71218_a(0);
        for (int i = 0; i < 2; i++) {
            for (int h = 0; h < 4; h++) {
                for (int j = 0; j < 29 / 4; j++) {
                    for (int k = 0; k < 16; k++) {
                        BlockPos ref = pillars.get(i);
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
                        BlockPos ref2 = ref.func_177982_a(xDir * 1, -4, zDir * 1);
                        Vec3i front = new Vec3i(0, 0, -1);
                        getRotatedVec(front, rad);
                        Vec3i dir = new Vec3i(1, 0, 0);
                        Vec3i refOffset = new Vec3i(0, 0, 0);
                        switch (h) {
                            case 1:
                                refOffset = new Vec3i(29 / 4, 0, 0);
                                dir = new Vec3i(0, 0, 1);
                                break;
                            case 2:
                                refOffset = new Vec3i(29 / 4, 0, 29 / 4);
                                dir = new Vec3i(-1, 0, 0);
                                break;
                            case 3:
                                refOffset = new Vec3i(0, 0, 29 / 4);
                                dir = new Vec3i(0, 0, -1);
                                break;
                        }
                        BlockPos ref3 = ref2.func_177971_a(getRotatedVec(refOffset, rad));
                        Vec3i dir2 = getRotatedVec(dir, rad);
                        BlockPos ref4 = ref3.func_177982_a(dir2.func_177958_n() * j, k, dir2.func_177952_p() * j);
                        if (i == 0) {
                            mazeNode = map1.getNodeAtLocation(j + (h * (29 / 4)), k);
                        } else {
                            mazeNode = map2.getNodeAtLocation(j + (h * (29 / 4)), k);
                        }
                        worldIn.func_175656_a(ref4, BlockInit.trackWall.func_176223_P());
                        if ((mazeNode.getType().equals("path") || mazeNode.getType().equals("trigger")) && mazeNode.isVisited()) {
                            worldIn.func_175698_g(ref4);
                        }
                        if (i == 0 && j == 0 && h == 0 && k == 0) {
                            worldIn.func_175656_a(ref4, BlockInit.timelineStabilizerCapsule.func_176223_P());
                        } else if (h == 0 && j == 0 && k == 16 - 1) {
                            worldIn.func_175656_a(ref4, BlockInit.timelineStabilizerGoal.func_176223_P());
                        }
                    }
                }
            }
        }
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
