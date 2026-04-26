package xol.lostinfinity.dimension.cartographerrealm.bot;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm;
import xol.lostinfinity.dimension.util.WorldGenStructure;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/cartographerrealm/bot/ChunkGeneratorCartographerBot.class */
public class ChunkGeneratorCartographerBot implements IChunkGenerator, ICartographerRealm {
    private final Random rand;
    private final World world;
    private Biome[] biomesForGeneration;

    public ChunkGeneratorCartographerBot(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
    }

    public void func_185931_b(int chunkX, int chunkZ) {
        this.rand.setSeed(this.world.func_72905_C());
        long k = ((this.rand.nextLong() / 2) * 2) + 1;
        long l = ((this.rand.nextLong() / 2) * 2) + 1;
        this.rand.setSeed(((((long) chunkX) * k) + (((long) chunkZ) * l)) ^ this.world.func_72905_C());
        doGeneration(this.world, this.rand, chunkX, chunkZ);
        if (Math.abs(chunkX) <= 1 && Math.abs(chunkZ) <= 1) {
            if (chunkX == -1 && chunkZ == -1) {
                int posX = chunkX * 16;
                int posZ = chunkZ * 16;
                new WorldGenStructure("labyrinth/labyrinth_puzzlemaster1").func_180709_b(this.world, this.rand, new BlockPos(posX + 8, 30, posZ + 8));
            }
            if (chunkX == 0 && chunkZ == -1) {
                int posX2 = chunkX * 16;
                int posZ2 = chunkZ * 16;
                new WorldGenStructure("labyrinth/labyrinth_puzzlemaster2").func_180709_b(this.world, this.rand, new BlockPos(posX2 + 8, 30, posZ2 + 8));
            }
            if (chunkX == -1 && chunkZ == 0) {
                int posX3 = chunkX * 16;
                int posZ3 = chunkZ * 16;
                new WorldGenStructure("labyrinth/labyrinth_puzzlemaster3").func_180709_b(this.world, this.rand, new BlockPos(posX3 + 8, 30, posZ3 + 8));
            }
            if (chunkX == 0 && chunkZ == 0) {
                int posX4 = chunkX * 16;
                int posZ4 = chunkZ * 16;
                new WorldGenStructure("labyrinth/labyrinth_puzzlemaster4").func_180709_b(this.world, this.rand, new BlockPos(posX4 + 8, 30, posZ4 + 8));
            }
        }
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String teleRoom() {
        return "labyrinth/bottomlabcross1";
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String gateRoom() {
        return "labyrinth/bottomlabcross1";
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String[] stairsRoom() {
        String[] stairs = {"labyrinth/bottomlabelevatortop", "labyrinth/bottomlabelevatorbot"};
        return stairs;
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String[] twoLevelRoom() {
        String[] stairs = {"labyrinth/bottomlabelevatortop", "labyrinth/bottomlabelevatorbot"};
        return stairs;
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String connectorRoom() {
        int con_pick = this.rand.nextInt(16);
        return "labyrinth/bottomlabcon" + (con_pick + 1);
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String crossRoom() {
        int cross_pick = this.rand.nextInt(5);
        return "labyrinth/bottomlabcross" + (cross_pick + 1);
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String gameRoom() {
        int game_pick = this.rand.nextInt(11);
        return "labyrinth/bottomlabspeccross" + (game_pick + 1);
    }

    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String portalRoom() {
        int con_pick = this.rand.nextInt(16);
        return "labyrinth/bottomlabcon" + (con_pick + 1);
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

    public List<Biome.SpawnListEntry> func_177458_a(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    public boolean func_185933_a(Chunk chunkIn, int chunkX, int chunkZ) {
        return false;
    }

    public void func_180514_a(Chunk p_180514_1_, int x, int z) {
    }

    public boolean func_193414_a(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    public BlockPos func_180513_a(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }
}
