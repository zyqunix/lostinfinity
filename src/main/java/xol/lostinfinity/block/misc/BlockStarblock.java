package xol.lostinfinity.block.misc;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.mob.entity.misc.EntityStarfiend;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockStarblock.class */
public class BlockStarblock extends BlockBasic {
    public BlockStarblock(String name) {
        super(name);
    }

    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.field_72995_K && (placer instanceof EntityPlayer)) {
            List<BlockPos> validBlocks = isStructureComplete(worldIn, state.func_177230_c(), pos);
            if (!validBlocks.isEmpty()) {
                AxisAlignedBB aabb = getAxisFromList(validBlocks);
                double d = aabb.field_72337_e;
                while (true) {
                    double y = d;
                    if (y >= aabb.field_72338_b) {
                        double d2 = aabb.field_72336_d;
                        while (true) {
                            double x = d2;
                            if (x >= aabb.field_72340_a) {
                                double d3 = aabb.field_72334_f;
                                while (true) {
                                    double z = d3;
                                    if (z >= aabb.field_72339_c) {
                                        worldIn.func_175698_g(new BlockPos(x, y, z));
                                        d3 = z - 1.0d;
                                    }
                                }
                                d2 = x - 1.0d;
                            }
                        }
                        d = y - 1.0d;
                    } else {
                        EntityStarfiend starfiend = new EntityStarfiend(worldIn);
                        starfiend.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
                        worldIn.func_72838_d(starfiend);
                        return;
                    }
                }
            }
        }
    }

    private AxisAlignedBB getAxisFromList(List<BlockPos> blocks) {
        int lowx = 0;
        int lowy = 0;
        int lowz = 0;
        for (int i = 0; i < blocks.size(); i++) {
            BlockPos pos = blocks.get(i);
            if (i == 0) {
                lowx = pos.func_177958_n();
                lowy = pos.func_177956_o();
                lowz = pos.func_177952_p();
            } else {
                if (pos.func_177958_n() < lowx) {
                    lowx = pos.func_177958_n();
                }
                if (pos.func_177956_o() < lowy) {
                    lowy = pos.func_177956_o();
                }
                if (pos.func_177952_p() < lowz) {
                    lowz = pos.func_177952_p();
                }
            }
        }
        return new AxisAlignedBB(new BlockPos(lowx, lowy, lowz), new BlockPos(lowx + 5, lowy + 4, lowz + 5));
    }

    private Block getBlockAt(BlockPos pos, World world) {
        return world.func_180495_p(pos).func_177230_c();
    }

    private List<BlockPos> addBlocksToList(List<BlockPos> blocks, BlockPos basePos, int operationType) {
        blocks.add(basePos);
        if (operationType <= 1) {
            for (int i = 1; i <= 4; i++) {
                blocks.add(new BlockPos(basePos).func_177982_a(operationType == 0 ? i : -i, -1, 0));
                if (i == 2) {
                    int z = 2;
                    while (z >= -2) {
                        blocks.add(new BlockPos(basePos).func_177982_a(operationType == 0 ? i : -i, (z == 2 || z == -2) ? 0 : -1, operationType == 0 ? z : -z));
                        if (z == 0) {
                            blocks.add(new BlockPos(basePos).func_177982_a(operationType == 0 ? i : -i, -2, operationType == 0 ? z : -z));
                            blocks.add(new BlockPos(basePos).func_177982_a(operationType == 0 ? i : -i, -3, operationType == 0 ? z : -z));
                        }
                        z--;
                    }
                }
            }
        } else {
            for (int i2 = 1; i2 <= 4; i2++) {
                blocks.add(new BlockPos(basePos).func_177982_a(0, -1, operationType == 2 ? i2 : -i2));
                if (i2 == 2) {
                    int z2 = 2;
                    while (z2 >= -2) {
                        blocks.add(new BlockPos(basePos).func_177982_a(operationType == 2 ? z2 : -z2, (z2 == 2 || z2 == -2) ? 0 : -1, operationType == 2 ? i2 : -i2));
                        if (z2 == 0) {
                            blocks.add(new BlockPos(basePos).func_177982_a(operationType == 2 ? z2 : -z2, -2, operationType == 2 ? i2 : -i2));
                            blocks.add(new BlockPos(basePos).func_177982_a(operationType == 2 ? z2 : -z2, -3, operationType == 2 ? i2 : -i2));
                        }
                        z2--;
                    }
                }
            }
        }
        return blocks;
    }

    private List<BlockPos> getValidBlocks(World world, Block block, BlockPos basePos) {
        List<BlockPos> allValidBlocks = new ArrayList<>();
        if (getBlockAt(new BlockPos(basePos).func_177982_a(4, 0, 0), world).equals(block)) {
            allValidBlocks.add(new BlockPos(basePos).func_177982_a(4, 0, 0));
            return addBlocksToList(allValidBlocks, basePos, 0);
        }
        if (getBlockAt(new BlockPos(basePos).func_177982_a(-4, 0, 0), world).equals(block)) {
            allValidBlocks.add(new BlockPos(basePos).func_177982_a(-4, 0, 0));
            return addBlocksToList(allValidBlocks, basePos, 1);
        }
        if (getBlockAt(new BlockPos(basePos).func_177982_a(0, 0, 4), world).equals(block)) {
            allValidBlocks.add(new BlockPos(basePos).func_177982_a(0, 0, 4));
            return addBlocksToList(allValidBlocks, basePos, 2);
        }
        if (getBlockAt(new BlockPos(basePos).func_177982_a(0, 0, -4), world).equals(block)) {
            allValidBlocks.add(new BlockPos(basePos).func_177982_a(0, 0, -4));
            return addBlocksToList(allValidBlocks, basePos, 3);
        }
        return allValidBlocks;
    }

    private List<BlockPos> isStructureComplete(World world, Block block, BlockPos pos) {
        List<BlockPos> validBlocks = getValidBlocks(world, block, pos);
        if (!validBlocks.isEmpty()) {
            int starblock = 0;
            int redstone = 0;
            for (int i = 0; i < validBlocks.size(); i++) {
                Block b = world.func_180495_p(validBlocks.get(i)).func_177230_c();
                if (b.equals(block)) {
                    starblock++;
                } else if (b.equals(Blocks.field_189877_df)) {
                    redstone++;
                }
            }
            if (starblock == 4 && redstone == 9) {
                return validBlocks;
            }
            validBlocks.clear();
        }
        return validBlocks;
    }
}
