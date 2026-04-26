package xol.lostinfinity.dimension.shadowsea;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/CaveGeneratorShadowSea.class */
public class CaveGeneratorShadowSea extends MapGenCaves {
    protected boolean func_175793_a(IBlockState targetBlock, IBlockState replacementBlock) {
        return targetBlock.func_177230_c() == BlockInit.seastone;
    }

    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
        Biome biome = this.field_75039_c.func_180494_b(new BlockPos(x + (chunkX * 16), 0, z + (chunkZ * 16)));
        IBlockState top = biome.field_76752_A;
        IBlockState filler = biome.field_76753_B;
        if (func_175793_a(state, up) || state.func_177230_c() == top.func_177230_c() || state.func_177230_c() == filler.func_177230_c()) {
            if (y < 10) {
                data.func_177855_a(x, y, z, BlockInit.murkcore.func_176223_P());
                return;
            }
            data.func_177855_a(x, y, z, field_186127_b);
            if (foundTop && data.func_177856_a(x, y - 1, z).func_177230_c() == filler.func_177230_c()) {
                data.func_177855_a(x, y - 1, z, top.func_177230_c().func_176223_P());
            }
        }
    }
}
