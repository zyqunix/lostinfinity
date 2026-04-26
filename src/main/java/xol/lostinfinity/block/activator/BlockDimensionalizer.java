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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.misc.BlockLaunchCoordinator;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.dimension.util.DimensionActivator;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.activate.ItemComplexLostMap;
public class BlockDimensionalizer extends Block {
    public BlockDimensionalizer(String name) {
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
        if (!playerIn.func_70093_af()) {
            boolean valid_hold = false;
            ItemStack held = playerIn.func_184586_b(hand);
            if ((held.func_77973_b() instanceof ItemComplexLostMap) && held.func_77942_o() && held.func_77978_p().func_74762_e("MapProgress") == 2) {
                valid_hold = true;
            }
            if (!worldIn.field_72995_K && valid_hold) {
                int[] goals = {held.func_77978_p().func_74762_e("MapSouth"), held.func_77978_p().func_74762_e("MapNorth"), held.func_77978_p().func_74762_e("MapEast"), held.func_77978_p().func_74762_e("MapWest")};
                boolean correct = true;
                for (int i = 0; i < 4; i++) {
                    if (correct) {
                        int running_total = 0;
                        for (int j = 0; j < 7; j++) {
                            if (i < 2) {
                                check_pos = pos.func_177982_a((-3) + j, 0, i % 2 == 0 ? 4 : -4);
                            } else {
                                check_pos = pos.func_177982_a(i % 2 == 0 ? 4 : -4, 0, (-3) + j);
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
                        int goal = goals[i];
                        if (running_total != goal) {
                            correct = false;
                            String[] rownames = {"South", "North", "East", "West"};
                            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + rownames[i] + " Row did not equal goal of:  " + goal + ". Outcome was: " + running_total));
                        }
                    }
                }
                if (correct) {
                    held.func_190918_g(1);
                    playerIn.func_184611_a(hand, new ItemStack(ItemInit.mazeToken, 1));
                    DimensionActivator.transferEntityWithCoords(playerIn, DimensionInit.celestialVoid, 1465.0d, 65.0d, 431.0d);
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Match launch coordinator values to Advanced Celestial Map.");
    }
}
