package xol.lostinfinity.block.activator;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.misc.BlockLaunchCoordinator;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLaunchCore.class */
public class BlockLaunchCore extends Block {
    public BlockLaunchCore(String name) {
        super(Material.field_151573_f);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(3.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185851_d);
        func_149715_a(1.0f);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos check_pos;
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K) {
            if (iteratePads(pos, worldIn, false) != 24) {
                playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Launch pad missing!"));
                return true;
            }
            boolean correct = true;
            for (int i = 0; i < 4; i++) {
                if (correct) {
                    int running_total = 0;
                    for (int j = 0; j < 5; j++) {
                        if (i < 2) {
                            check_pos = pos.func_177982_a((-2) + j, 0, i % 2 == 0 ? 3 : -3);
                        } else {
                            check_pos = pos.func_177982_a(i % 2 == 0 ? 3 : -3, 0, (-2) + j);
                        }
                        BlockLaunchCoordinator blockLaunchCoordinatorFunc_177230_c = worldIn.func_180495_p(check_pos).func_177230_c();
                        if (blockLaunchCoordinatorFunc_177230_c instanceof BlockLaunchCoordinator) {
                            BlockLaunchCoordinator coordinator = blockLaunchCoordinatorFunc_177230_c;
                            running_total += coordinator.getColor() * coordinator.getShape();
                        } else {
                            correct = false;
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Coordinator missing!"));
                        }
                    }
                    int goal = 22 + (5 * i);
                    if (running_total != goal) {
                        correct = false;
                        playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "Row did not equal goal of:  " + goal + ". Outcome was: " + running_total));
                    }
                }
            }
            if (correct) {
                iteratePads(pos, worldIn, true);
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SUPERMUTATION, SoundCategory.MASTER, 1.0f, 1.0f);
                return true;
            }
            return true;
        }
        return true;
    }

    public int iteratePads(BlockPos pos, World worldIn, boolean light) {
        int light_total = 0;
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (j != 0 || i != 0) {
                    BlockPos check_pos = pos.func_177982_a(i, 0, j);
                    if (light) {
                        worldIn.func_175656_a(check_pos, BlockInit.launchPad.func_176223_P());
                    } else {
                        Block block_check = worldIn.func_180495_p(check_pos).func_177230_c();
                        if (block_check.equals(BlockInit.launchPad) || block_check.equals(BlockInit.launchPadUnpowered)) {
                            light_total++;
                        }
                    }
                }
            }
        }
        return light_total;
    }

    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Needs launch coordinators with the following values:");
        tooltip.add(TextFmt.Aqua + "South: 22");
        tooltip.add(TextFmt.Light_Purple + "North: 27");
        tooltip.add(TextFmt.Aqua + "East: 32");
        tooltip.add(TextFmt.Light_Purple + "West: 37");
    }
}
