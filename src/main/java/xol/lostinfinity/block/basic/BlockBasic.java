package xol.lostinfinity.block.basic;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasic.class */
public class BlockBasic extends Block {
    public BlockBasic(String name) {
        this(name, Material.field_151576_e);
    }

    public BlockBasic(String name, Material material) {
        this(name, material, TabsInit.TAB_BLOCKS);
    }

    public BlockBasic(String name, Material material, CreativeTabs tab) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(60.0f);
        func_149752_b(60.0f);
        func_149647_a(tab);
        func_149672_a(SoundType.field_185851_d);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
}
