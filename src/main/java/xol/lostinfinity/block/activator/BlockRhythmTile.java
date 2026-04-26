package xol.lostinfinity.block.activator;

import net.minecraft.block.state.IBlockState;
import xol.lostinfinity.block.basic.BlockBasicBoolState;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRhythmTile.class */
public class BlockRhythmTile extends BlockBasicBoolState {
    public BlockRhythmTile(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public IBlockState getActiveState() {
        return func_176223_P().func_177226_a(ACTIVE, true);
    }

    public IBlockState getInactiveState() {
        return func_176223_P().func_177226_a(ACTIVE, false);
    }
}
