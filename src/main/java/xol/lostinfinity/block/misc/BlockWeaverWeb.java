package xol.lostinfinity.block.misc;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import xol.lostinfinity.block.basic.BlockBasicPlant;
import xol.lostinfinity.block.basic.ITetherable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockWeaverWeb.class */
public class BlockWeaverWeb extends BlockBasicPlant implements ITetherable {
    public BlockWeaverWeb(String name) {
        super(name, Material.field_151569_G);
        func_149672_a(SoundType.field_185854_g);
    }
}
