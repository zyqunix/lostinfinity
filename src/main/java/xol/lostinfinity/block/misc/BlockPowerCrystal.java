package xol.lostinfinity.block.misc;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockPowerCrystal extends BlockBasic {
    public BlockPowerCrystal(String name) {
        super(name, Material.field_151576_e);
    }
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.field_72995_K && (placer instanceof EntityPlayer)) {
            int place_case = powerCoordsCorrect(worldIn, state.func_177230_c(), pos);
            if (place_case == 0) {
                worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
                if (!((EntityPlayer) placer).func_184812_l_()) {
                    placer.func_145779_a(state.func_177230_c().func_185473_a(worldIn, pos, state).func_77973_b(), 1);
                    return;
                }
                return;
            }
            if (place_case == 2) {
                worldIn.func_175698_g(pos);
                BlockPos teleport = telePos(state.func_177230_c());
                placer.func_70634_a(teleport.func_177958_n(), teleport.func_177956_o(), teleport.func_177952_p());
            }
        }
    }
    private BlockPos telePos(Block block) {
        if (block.equals(BlockInit.powerBlockBlue)) {
            return new BlockPos(767, 32, 357);
        }
        if (block.equals(BlockInit.powerBlockYellow)) {
            return new BlockPos(767, 32, 363);
        }
        if (block.equals(BlockInit.powerBlockPurple)) {
            return new BlockPos(784, 32, 363);
        }
        if (block.equals(BlockInit.powerBlockGreen)) {
            return new BlockPos(784, 32, 363);
        }
        return new BlockPos(784, 24, 355);
    }
    private int powerCoordsCorrect(World world, Block block, BlockPos pos) {
        BlockPos below = pos.func_177977_b();
        if (world.func_180495_p(below).func_177230_c() == BlockInit.powerKeyBlock) {
            if (powerGateComplete(world, block, pos)) {
                return 2;
            }
            return 1;
        }
        return 0;
    }
    private boolean powerGateComplete(World wl, Block blockcheck, BlockPos pos) {
        List<BlockPos> memory = new ArrayList<>();
        int otherRow = -2;
        if (wl.func_180495_p(pos.func_177984_a()).func_177230_c() == BlockInit.powerKeyBlock) {
            otherRow = 2;
        }
        int keycount = 0;
        int offset = 0;
        boolean keepGoing = true;
        while (keepGoing) {
            offset--;
            BlockPos testpos = pos.func_177977_b().func_177982_a(0, 0, offset);
            if (wl.func_180495_p(testpos).func_177230_c() == BlockInit.powerKeyBlock) {
                if (wl.func_180495_p(testpos.func_177984_a()).func_177230_c() == blockcheck) {
                    keycount++;
                    memory.add(testpos.func_177984_a());
                } else {
                    keepGoing = false;
                }
            } else {
                keepGoing = false;
            }
        }
        boolean keepGoing2 = true;
        int offset2 = 0;
        while (keepGoing2) {
            offset2++;
            BlockPos testpos2 = pos.func_177977_b().func_177982_a(0, 0, offset2);
            if (wl.func_180495_p(testpos2).func_177230_c() == BlockInit.powerKeyBlock) {
                if (wl.func_180495_p(testpos2.func_177984_a()).func_177230_c() == blockcheck) {
                    keycount++;
                    memory.add(testpos2.func_177984_a());
                } else {
                    keepGoing2 = false;
                }
            } else {
                keepGoing2 = false;
            }
        }
        boolean keepGoing3 = true;
        int offset3 = 0;
        while (keepGoing3) {
            offset3++;
            BlockPos testpos3 = pos.func_177977_b().func_177982_a(0, otherRow, offset3);
            if (wl.func_180495_p(testpos3).func_177230_c() == BlockInit.powerKeyBlock) {
                if (wl.func_180495_p(testpos3.func_177984_a()).func_177230_c() == blockcheck) {
                    keycount++;
                    memory.add(testpos3.func_177984_a());
                } else {
                    keepGoing3 = false;
                }
            } else {
                keepGoing3 = false;
            }
        }
        boolean keepGoing4 = true;
        int offset4 = 0;
        while (keepGoing4) {
            BlockPos testpos4 = pos.func_177977_b().func_177982_a(0, otherRow, offset4);
            if (wl.func_180495_p(testpos4).func_177230_c() == BlockInit.powerKeyBlock) {
                if (wl.func_180495_p(testpos4.func_177984_a()).func_177230_c() == blockcheck) {
                    keycount++;
                    memory.add(testpos4.func_177984_a());
                } else {
                    keepGoing4 = false;
                }
            } else {
                keepGoing4 = false;
            }
            offset4--;
        }
        if (keycount >= 7) {
            for (BlockPos rem : memory) {
                wl.func_175698_g(rem);
            }
        }
        return keycount >= 7;
    }
}
