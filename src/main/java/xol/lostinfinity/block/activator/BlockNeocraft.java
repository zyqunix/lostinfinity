package xol.lostinfinity.block.activator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
public class BlockNeocraft extends BlockBasic {
    public BlockNeocraft(String name) {
        super(name);
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.func_184586_b(hand);
        Block makeBlock = blockFromItem(held.func_77973_b());
        if (makeBlock != null && makeBlock != worldIn.func_180495_p(pos).func_177230_c()) {
            if (!worldIn.field_72995_K) {
                worldIn.func_175656_a(pos, makeBlock.func_176223_P());
                worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            }
            held.func_190918_g(1);
            return true;
        }
        return true;
    }
    private Block blockFromItem(Item item) {
        if (item == ItemInit.cureSampleBlue) {
            return BlockInit.neocraftBlue;
        }
        if (item == ItemInit.cureSampleGreen) {
            return BlockInit.neocraftGreen;
        }
        if (item == ItemInit.cureSampleYellow) {
            return BlockInit.neocraftYellow;
        }
        if (item == ItemInit.cureSampleOrange) {
            return BlockInit.neocraftOrange;
        }
        if (item == ItemInit.cureSamplePink) {
            return BlockInit.neocraftPink;
        }
        return null;
    }
}
