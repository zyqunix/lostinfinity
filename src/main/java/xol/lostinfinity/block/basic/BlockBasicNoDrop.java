package xol.lostinfinity.block.basic;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicNoDrop.class */
public class BlockBasicNoDrop extends BlockBasic {
    public BlockBasicNoDrop(String name) {
        super(name);
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Items.field_190931_a;
    }
}
