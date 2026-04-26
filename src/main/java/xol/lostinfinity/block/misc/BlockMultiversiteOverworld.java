package xol.lostinfinity.block.misc;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.ItemInit;
public class BlockMultiversiteOverworld extends BlockBasic {
    public BlockMultiversiteOverworld(String name) {
        super(name);
        func_149711_c(2.0f);
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return ItemInit.fracturedMultiversite;
    }
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 1;
    }
}
