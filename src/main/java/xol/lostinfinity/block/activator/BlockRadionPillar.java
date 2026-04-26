package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.basic.ISpecialHarvest;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockRadionPillar extends BlockBasicBoolState implements ISpecialHarvest {
    private static final boolean DEBUG = false;
    private static final int PILLAR_MAX_HEIGHT = 10;
    public BlockRadionPillar(String name) {
        super(name);
        func_149675_a(true);
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getHarvestResult(World world, BlockPos pos) {
        return null;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public Item getToolNeeded() {
        return null;
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void worldHarvestEffect(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public void failedHarvest(World world, BlockPos pos, EntityPlayer harvester) {
    }
    @Override // xol.lostinfinity.block.basic.ISpecialHarvest
    public boolean isHarvestable(World world, BlockPos pos, EntityPlayer harvester) {
        return false;
    }
    public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.field_72995_K) {
            unlightPillarSegment(worldIn, pos);
        }
    }
    public void func_180649_a(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        Iterable<BlockPos> nearBlocks = BlockPos.func_177980_a(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
        List<BlockRadionPillar> pillarBlocks = new ArrayList<>();
        List<BlockPos> pillarBlockPositions = new ArrayList<>();
        int pillarCharge = DEBUG;
        for (BlockPos nearPos : nearBlocks) {
            if ((worldIn.func_180495_p(nearPos).func_177230_c() instanceof BlockRadionPillar) && nearPos.func_177958_n() == pos.func_177958_n() && nearPos.func_177952_p() == pos.func_177952_p()) {
                pillarBlocks.add((BlockRadionPillar) worldIn.func_180495_p(nearPos).func_177230_c());
                pillarBlockPositions.add(nearPos);
                if (worldIn.func_180495_p(nearPos).func_177230_c().func_176201_c(worldIn.func_180495_p(nearPos)) == 1) {
                    pillarCharge++;
                }
            }
        }
        if (pillarCharge == pillarBlocks.size()) {
            if (!worldIn.field_72995_K) {
                for (int i = DEBUG; i < pillarBlocks.size(); i++) {
                    pillarBlocks.get(i).unlightPillarSegment(worldIn, pillarBlockPositions.get(i));
                }
                playerIn.func_145779_a(ItemInit.radionFragment, new Random().nextInt(4) + 3);
            }
            playerIn.func_184185_a(SoundInit.ENERGY_PULSE, 1.0f, 1.0f);
        }
    }
    public void lightUpPillarSegment(World worldIn, BlockPos pos) {
        if (!worldIn.field_72995_K) {
            Iterable<BlockPos> nearBlocks = BlockPos.func_177980_a(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            List<BlockRadionPillar> pillarBlocks = new ArrayList<>();
            List<BlockPos> pillarBlockPositions = new ArrayList<>();
            for (BlockPos nearPos : nearBlocks) {
                if (worldIn.func_180495_p(nearPos).func_177230_c() instanceof BlockRadionPillar) {
                    BlockRadionPillar pillarBlock = (BlockRadionPillar) worldIn.func_180495_p(nearPos).func_177230_c();
                    if (nearPos.func_177958_n() == pos.func_177958_n() && nearPos.func_177952_p() == pos.func_177952_p()) {
                        pillarBlocks.add(pillarBlock);
                        pillarBlockPositions.add(nearPos);
                    }
                }
            }
            for (int i = DEBUG; i < pillarBlocks.size(); i++) {
                BlockRadionPillar pillarBlock2 = pillarBlocks.get(i);
                BlockPos pillarBlockPos = pillarBlockPositions.get(i);
                int meta = pillarBlock2.func_176201_c(worldIn.func_180495_p(pillarBlockPos));
                if (meta == 0) {
                    worldIn.func_175656_a(pillarBlockPos, pillarBlock2.func_176203_a(1));
                    return;
                }
            }
        }
    }
    private void unlightPillarSegment(World worldIn, BlockPos pos) {
        if (!worldIn.field_72995_K) {
            Iterable<BlockPos> nearBlocks = BlockPos.func_177980_a(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            List<BlockRadionPillar> pillarBlocks = new ArrayList<>();
            List<BlockPos> pillarBlockPositions = new ArrayList<>();
            for (BlockPos nearPos : nearBlocks) {
                if (worldIn.func_180495_p(nearPos).func_177230_c() instanceof BlockRadionPillar) {
                    BlockRadionPillar pillarBlock = (BlockRadionPillar) worldIn.func_180495_p(nearPos).func_177230_c();
                    if (nearPos.func_177958_n() == pos.func_177958_n() && nearPos.func_177952_p() == pos.func_177952_p()) {
                        pillarBlocks.add(pillarBlock);
                        pillarBlockPositions.add(nearPos);
                    }
                }
            }
            Collections.reverse(pillarBlocks);
            Collections.reverse(pillarBlockPositions);
            for (int i = DEBUG; i < pillarBlocks.size(); i++) {
                BlockRadionPillar pillarBlock2 = pillarBlocks.get(i);
                BlockPos pillarBlockPos = pillarBlockPositions.get(i);
                int meta = pillarBlock2.func_176201_c(worldIn.func_180495_p(pillarBlockPos));
                if (meta == 1) {
                    worldIn.func_175656_a(pillarBlockPos, pillarBlock2.func_176203_a(DEBUG));
                    return;
                }
            }
        }
    }
}
