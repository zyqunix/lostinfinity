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
import xol.lostinfinity.item.activate.ItemEncryptedPowerBlueprints;
public class BlockPowerGenerator extends BlockBasic {
    public BlockPowerGenerator(String name) {
        super(name);
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            ItemStack held = playerIn.func_184586_b(hand);
            if (held.func_77973_b() == ItemInit.encryptedPowerBlueprints) {
                ItemEncryptedPowerBlueprints scroll = (ItemEncryptedPowerBlueprints) held.func_77973_b();
                if (scroll.getCompletion(held) < 3 && scroll.matchBlock(held, pos, worldIn)) {
                    scroll.progress(held, playerIn);
                    return true;
                }
                playerIn.func_145747_a(new TextComponentString(TextFmt.Italic + "You can not currently connect this generator, please consult the main blueprints."));
                return true;
            }
            return true;
        }
        return true;
    }
}
