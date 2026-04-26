package xol.lostinfinity.block.basic;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicStairs.class */
public class BlockBasicStairs extends BlockStairs {
    public BlockBasicStairs(String name, float hardness, IBlockState modelState) {
        super(modelState);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(hardness);
        func_149647_a(TabsInit.TAB_BLOCKS);
        this.field_149783_u = true;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
}
