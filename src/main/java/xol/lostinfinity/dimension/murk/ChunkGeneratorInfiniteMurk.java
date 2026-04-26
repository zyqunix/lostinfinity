package xol.lostinfinity.dimension.murk;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/murk/ChunkGeneratorInfiniteMurk.class */
public class ChunkGeneratorInfiniteMurk implements IChunkGenerator {
    private final World world;
    private final Random rand;
    private Biome[] biomesForGeneration;

    public ChunkGeneratorInfiniteMurk(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
    }

    public Chunk func_185932_a(int x, int z) {
        this.rand.setSeed((((long) x) * 341873128712L) + (((long) z) * 132897987541L));
        this.biomesForGeneration = this.world.func_72959_q().func_76933_b(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        ChunkPrimer primer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.world, primer, x, z);
        byte[] abyte = chunk.func_76605_m();
        for (int i = 0; i < abyte.length; i++) {
            abyte[i] = (byte) Biome.func_185362_a(this.biomesForGeneration[i]);
        }
        chunk.func_76603_b();
        return chunk;
    }

    public void func_185931_b(int x, int z) {
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.func_180494_b(blockpos.func_177982_a(16, 0, 16));
        WorldEntitySpawner.func_77191_a(this.world, biome, i + 8, j + 8, 16, 16, this.world.field_73012_v);
    }

    public List<Biome.SpawnListEntry> func_177458_a(EnumCreatureType creatureType, BlockPos pos) {
        return this.world.func_180494_b(pos).func_76747_a(creatureType);
    }

    public boolean func_185933_a(Chunk chunkIn, int x, int z) {
        return false;
    }

    public void func_180514_a(Chunk chunkIn, int x, int z) {
    }

    public boolean func_193414_a(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    public BlockPos func_180513_a(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }
}
