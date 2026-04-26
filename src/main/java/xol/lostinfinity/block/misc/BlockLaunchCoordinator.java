package xol.lostinfinity.block.misc;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
public class BlockLaunchCoordinator extends BlockBasicRotational {
    private int coord_color;
    private int coord_shape;
    public BlockLaunchCoordinator(String name, int color, int shape) {
        super(name);
        func_149715_a(1.0f);
        this.coord_color = color;
        this.coord_shape = shape;
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            ItemStack held = playerIn.func_184586_b(hand);
            boolean flag = false;
            if (held.func_77973_b().equals(ItemInit.configurationCellColor)) {
                flag = true;
                if (!worldIn.field_72995_K) {
                    boolean run = true;
                    while (run) {
                        BlockLaunchCoordinator newBlock = getRandomCoordinator(worldIn);
                        if (newBlock.getShape() == getShape() && newBlock.getColor() != getColor()) {
                            worldIn.func_175656_a(pos, newBlock.func_176203_a(func_176201_c(state)));
                            run = false;
                        }
                    }
                }
            } else if (held.func_77973_b().equals(ItemInit.configurationCellShape)) {
                flag = true;
                if (!worldIn.field_72995_K) {
                    boolean run2 = true;
                    while (run2) {
                        BlockLaunchCoordinator newBlock2 = getRandomCoordinator(worldIn);
                        if (newBlock2.getColor() == getColor() && newBlock2.getShape() != getShape()) {
                            worldIn.func_175656_a(pos, newBlock2.func_176203_a(func_176201_c(state)));
                            run2 = false;
                        }
                    }
                }
            }
            if (flag) {
                held.func_190918_g(1);
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187626_cN, SoundCategory.MASTER, 1.0f, 1.0f);
                return true;
            }
            return true;
        }
        return true;
    }
    public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.field_72995_K && (placer instanceof EntityPlayer)) {
            worldIn.func_175656_a(pos, getRandomCoordinator(worldIn).func_176223_P());
        }
    }
    public int getColor() {
        return this.coord_color;
    }
    public int getShape() {
        return this.coord_shape;
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return null;
    }
    private static Block getRandomCoordinator(World worldIn) {
        Block[] coord = {BlockInit.launchCoordinatorBlue1, BlockInit.launchCoordinatorBlue3, BlockInit.launchCoordinatorBlue4, BlockInit.launchCoordinatorBlue5, BlockInit.launchCoordinatorPurple1, BlockInit.launchCoordinatorPurple3, BlockInit.launchCoordinatorPurple4, BlockInit.launchCoordinatorPurple5, BlockInit.launchCoordinatorGreen1, BlockInit.launchCoordinatorGreen3, BlockInit.launchCoordinatorGreen4, BlockInit.launchCoordinatorGreen5, BlockInit.launchCoordinatorRed1, BlockInit.launchCoordinatorRed3, BlockInit.launchCoordinatorRed4, BlockInit.launchCoordinatorRed5};
        int block = worldIn.field_73012_v.nextInt(coord.length);
        return coord[block];
    }
    @SideOnly(Side.CLIENT)
    public void func_190948_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "Becomes a random launch coordinator when placed.");
        tooltip.add(TextFmt.Red + "Drops nothing when mined.");
    }
}
