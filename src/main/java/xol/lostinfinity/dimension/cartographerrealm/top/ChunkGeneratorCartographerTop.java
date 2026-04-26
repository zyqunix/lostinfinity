package xol.lostinfinity.dimension.cartographerrealm.top;
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
public class ChunkGeneratorCartographerTop implements IChunkGenerator, ICartographerRealm {
    private final Random rand;
    private final World world;
    private Biome[] biomesForGeneration;
    public ChunkGeneratorCartographerTop(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
    }
    public void func_185931_b(int chunkX, int chunkZ) {
        this.rand.setSeed(this.world.func_72905_C());
        long k = ((this.rand.nextLong() / 2) * 2) + 1;
        long l = ((this.rand.nextLong() / 2) * 2) + 1;
        this.rand.setSeed(((((long) chunkX) * k) + (((long) chunkZ) * l)) ^ this.world.func_72905_C());
        doGeneration(this.world, this.rand, chunkX, chunkZ);
    }
    private String randomLowerConnector() {
        int con_pick = this.rand.nextInt(16);
        return "labyrinth/lowerlabcon" + (con_pick + 1);
    }
    private String randomLowerCross() {
        int cross_pick = this.rand.nextInt(6);
        return "labyrinth/lowerlabcross" + (cross_pick + 1);
    }
    private String randomLowSpecCross() {
        int cross_pick = this.rand.nextInt(12);
        return "labyrinth/lowerlabspeccross" + (cross_pick + 1);
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String teleRoom() {
        return "labyrinth/labteleroom";
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String gateRoom() {
        return "labyrinth/labgateroom";
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String[] stairsRoom() {
        int style = this.rand.nextInt(2) + 1;
        String[] stairs = {"labyrinth/labelevatortop" + style, "labyrinth/labelevatorbase" + style};
        return stairs;
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String[] twoLevelRoom() {
        int game_pick = this.rand.nextInt(3);
        String[] room = {"labyrinth/labaspectroom", "labyrinth/labgameroom" + (game_pick + 1)};
        return room;
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String connectorRoom() {
        int con_pick = this.rand.nextInt(16);
        return "labyrinth/labconnector" + (con_pick + 1);
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String crossRoom() {
        int cross_pick = this.rand.nextInt(8);
        return "labyrinth/labcross" + (cross_pick + 1);
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String gameRoom() {
        int cross_pick = this.rand.nextInt(13);
        return "labyrinth/labspeccross" + (cross_pick + 1);
    }
    @Override // xol.lostinfinity.dimension.cartographerrealm.ICartographerRealm
    public String portalRoom() {
        return "labyrinth/labportal";
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
