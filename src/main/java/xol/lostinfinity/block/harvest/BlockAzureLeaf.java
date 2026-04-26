package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockCappedPlant;
import xol.lostinfinity.init.ItemInit;
public class BlockAzureLeaf extends BlockCappedPlant {
    public BlockAzureLeaf(String name) {
        super(name);
        func_149675_a(true);
    }
    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K && func_176201_c(state) == 0) {
            world.func_175656_a(pos, func_176203_a(1));
        }
    }
    @Override // xol.lostinfinity.block.basic.BlockCappedPlant
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184614_ca();
        if (held.func_77973_b() == ItemInit.heatResistantTongs && !worldIn.field_72995_K) {
            EntityItem leaf = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o() + 2, pos.func_177952_p(), new ItemStack(ItemInit.azureLeaf));
            leaf.field_70159_w = 0.0d;
            leaf.field_70181_x = 0.0d;
            leaf.field_70179_y = 0.0d;
            worldIn.func_72838_d(leaf);
            worldIn.func_175656_a(pos, func_176203_a(0));
            worldIn.func_184133_a((EntityPlayer) null, pos.func_177984_a(), SoundEvents.field_187693_cj, SoundCategory.BLOCKS, 2.0f, 1.0f);
        }
        return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
