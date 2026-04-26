package xol.lostinfinity.block.harvest;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
public class BlockIgneousPearlOre extends BlockBasic {
    public BlockIgneousPearlOre(String name) {
        super(name);
        func_149711_c(3.0f);
        func_149752_b(5.0f);
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return ItemStack.field_190927_a.func_77973_b();
    }
    public void func_176206_d(World worldIn, BlockPos pos, IBlockState state) {
        EntityPlayer player;
        if (!worldIn.field_72995_K && (player = worldIn.func_184137_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 3.0d, false)) != null) {
            boolean found = false;
            int i = 0;
            while (true) {
                if (i >= player.field_71071_by.func_70302_i_()) {
                    break;
                }
                ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                if (playerStack.func_77973_b() != ItemInit.pearlIgneous) {
                    i++;
                } else {
                    found = true;
                    break;
                }
            }
            if (found) {
                player.func_145747_a(new TextComponentString(TextFmt.Red + "A strange magical property in the pearl prevents you from collecting another."));
            } else {
                player.func_191521_c(new ItemStack(ItemInit.pearlIgneous));
            }
        }
    }
}
