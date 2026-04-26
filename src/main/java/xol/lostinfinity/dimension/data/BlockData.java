package xol.lostinfinity.dimension.data;

import net.minecraft.util.math.BlockPos;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/data/BlockData.class */
public class BlockData {
    private BlockPos pos;
    private int meta;
    private int id;

    public BlockData(BlockPos pos, int meta, int id) {
        this.pos = null;
        this.meta = 0;
        this.id = 0;
        this.pos = pos;
        this.meta = meta;
        this.id = id;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public int getMeta() {
        return this.meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
