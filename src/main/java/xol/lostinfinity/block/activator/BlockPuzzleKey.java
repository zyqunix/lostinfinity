package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
public class BlockPuzzleKey extends BlockBasic {
    public BlockPuzzleKey(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldstack = playerIn.func_184586_b(hand);
        if (heldstack.func_77973_b() == ItemInit.puzzleKey) {
            if (!worldIn.field_72995_K) {
                playerIn.func_191521_c(new ItemStack(ItemInit.beaconKey));
                playerIn.func_70634_a(0.0d, 33.0d, 0.0d);
                playerIn.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "The Echo: Activate the beacon if you wish to play " + playerIn.func_70005_c_()));
            }
            heldstack.func_190918_g(1);
            return true;
        }
        return true;
    }
}
