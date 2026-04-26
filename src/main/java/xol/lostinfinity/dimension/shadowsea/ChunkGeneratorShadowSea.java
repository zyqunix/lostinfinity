package xol.lostinfinity.dimension.shadowsea;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import xol.lostinfinity.dimension.util.CustomBiome;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/ChunkGeneratorShadowSea.class */
public class ChunkGeneratorShadowSea implements IChunkGenerator {
    private final World world;
    private final Random rand;
    private ChunkPrimer primer;
    private int curChunkX;
    private int curChunkZ;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    private NoiseGeneratorOctaves scaleNoise;
    private NoiseGeneratorOctaves depthNoise;
    private double[] mainNoiseRegion;
    private double[] minLimitRegion;
    private double[] maxLimitRegion;
    private double[] depthRegion;
    private static final double depthNoiseScaleX = 200.0d;
    private static final double depthNoiseScaleZ = 200.0d;
    private static final double depthNoiseScaleExponent = 0.5d;
    private static final double coordScale = 684.412d;
    private static final int mainNoiseScaleX = 80;
    private static final int mainNoiseScaleY = 350;
    private static final int mainNoiseScaleZ = 30;
    private static final double heightScale = 684.412d;
    private static final int biomeDepthOffset = 0;
    private static final int biomeScaleOffset = 0;
    private static final double heightStretch = 30.0d;
    private static final double baseSize = 8.5d;
    private static final double lowerLimitScale = 512.0d;
    private static final double upperLimitScale = 512.0d;
    private static final float biomeDepthWeight = 1.0f;
    private static final float biomeScaleWeight = 1.0f;
    private final Biome biome = DimensionInit.biomeShadowSea;
    private final Biome biome2 = DimensionInit.biomeMoltenSea;
    private double[] heightMap = new double[825];
    private float[] biomeWeights = new float[25];
    private double[] depthBuffer = new double[256];
    private CaveGeneratorShadowSea caveGen = new CaveGeneratorShadowSea();

    protected ChunkGeneratorShadowSea(World world) {
        this.world = world;
        this.rand = new Random(world.func_72905_C());
        this.world.func_181544_b(0);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                this.biomeWeights[i + 2 + ((j + 2) * 5)] = 10.0f / MathHelper.func_76129_c(((i * i) + (j * j)) + 0.2f);
            }
        }
        InitNoiseGensEvent.ContextOverworld context = TerrainGen.getModdedNoiseGenerators(world, this.rand, new InitNoiseGensEvent.ContextOverworld(this.minLimitPerlinNoise, this.maxLimitPerlinNoise, this.mainPerlinNoise, this.surfaceNoise, this.scaleNoise, this.depthNoise, (NoiseGeneratorOctaves) null));
        this.minLimitPerlinNoise = context.getLPerlin1();
        this.maxLimitPerlinNoise = context.getLPerlin2();
        this.mainPerlinNoise = context.getPerlin();
        this.surfaceNoise = context.getHeight();
        this.scaleNoise = context.getScale();
        this.depthNoise = context.getDepth();
    }

    public Chunk func_185932_a(int chunkX, int chunkZ) {
        this.curChunkX = chunkX;
        this.curChunkZ = chunkZ;
        this.rand.setSeed((((long) chunkX) * 341873128712L) + (((long) chunkZ) * 132897987541L));
        this.primer = new ChunkPrimer();
        generateHeightMap();
        setBlocksInChunk(chunkX, chunkZ);
        replaceBiomeBlocks();
        this.caveGen.func_186125_a(this.world, chunkX, chunkZ, this.primer);
        Chunk chunk = new Chunk(this.world, this.primer, this.curChunkX, this.curChunkZ);
        byte[] biomeArray = chunk.func_76605_m();
        for (int i = 0; i < biomeArray.length; i++) {
            biomeArray[i] = (byte) Biome.func_185362_a(getBiomeByCoords(chunkX, chunkZ));
        }
        chunk.func_76603_b();
        return chunk;
    }

    private Biome getBiomeByCoords(int chunkX, int chunkZ) {
        if (chunkX < 100) {
            return this.biome;
        }
        return this.biome2;
    }

    private void generateHeightMap() {
        double depthBy8k;
        int offsetX = this.curChunkX * 4;
        int offsetZ = this.curChunkZ * 4;
        this.depthRegion = this.depthNoise.func_76305_a(this.depthRegion, offsetX, offsetZ, 5, 5, 200.0d, 200.0d, depthNoiseScaleExponent);
        this.mainNoiseRegion = this.mainPerlinNoise.func_76304_a(this.mainNoiseRegion, offsetX, 0, offsetZ, 5, 33, 5, 8.555150000000001d, 1.9554628571428572d, 22.813733333333335d);
        this.minLimitRegion = this.minLimitPerlinNoise.func_76304_a(this.minLimitRegion, offsetX, 0, offsetZ, 5, 33, 5, 684.412d, 684.412d, 684.412d);
        this.maxLimitRegion = this.maxLimitPerlinNoise.func_76304_a(this.maxLimitRegion, offsetX, 0, offsetZ, 5, 33, 5, 684.412d, 684.412d, 684.412d);
        int i = 0;
        int j = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                float accumulatedHeightVariation = 0.0f;
                float accumulatedHeight = 0.0f;
                float accumulatedWeightedHeightFactor = 0.0f;
                for (int m = -2; m <= 2; m++) {
                    for (int n = -2; n <= 2; n++) {
                        float baseHeight = 0.0f + (getBiomeByCoords(this.curChunkX, this.curChunkZ).func_185355_j() * 1.0f);
                        float heightVariation = 0.0f + (getBiomeByCoords(this.curChunkX, this.curChunkZ).func_185360_m() * 1.0f);
                        float heightVariation2 = 1.0f + (heightVariation * 20.0f);
                        float weightedHeightFactor = this.biomeWeights[(m + 2) + ((n + 2) * 5)] / (baseHeight + 2.0f);
                        accumulatedHeightVariation += heightVariation2 * weightedHeightFactor;
                        accumulatedHeight += baseHeight * weightedHeightFactor;
                        accumulatedWeightedHeightFactor += weightedHeightFactor;
                    }
                }
                float accumulatedHeightVariation2 = ((accumulatedHeightVariation / accumulatedWeightedHeightFactor) * 0.9f) + 0.1f;
                float accumulatedHeight2 = (((accumulatedHeight / accumulatedWeightedHeightFactor) * 4.0f) - 1.0f) / 8.0f;
                double depthBy8k2 = this.depthRegion[j] / 8000.0d;
                if (depthBy8k2 < 0.0d) {
                    depthBy8k2 = (-depthBy8k2) * 0.3d;
                }
                double depthBy8k3 = (depthBy8k2 * 3.0d) - 2.0d;
                if (depthBy8k3 < 0.0d) {
                    double depthBy8k4 = depthBy8k3 / 2.0d;
                    if (depthBy8k4 < -1.0d) {
                        depthBy8k4 = -1.0d;
                    }
                    depthBy8k = depthBy8k4 / 2.8d;
                } else {
                    if (depthBy8k3 > 1.0d) {
                        depthBy8k3 = 1.0d;
                    }
                    depthBy8k = depthBy8k3 / 8.0d;
                }
                j++;
                double heightAvg = baseSize + ((((((double) accumulatedHeight2) + (depthBy8k * 0.2d)) * baseSize) / 8.0d) * 4.0d);
                for (int o = 0; o < 33; o++) {
                    double d1 = ((((((double) o) - heightAvg) * heightStretch) * 128.0d) / 256.0d) / ((double) accumulatedHeightVariation2);
                    if (d1 < 0.0d) {
                        d1 *= 4.0d;
                    }
                    double minLimitScaled = this.minLimitRegion[i] / 512.0d;
                    double maxLimitScaled = this.maxLimitRegion[i] / 512.0d;
                    double noiseValue = ((this.mainNoiseRegion[i] / 10.0d) + 1.0d) / 2.0d;
                    double linearInterpHeight = MathHelper.func_151238_b(minLimitScaled, maxLimitScaled, noiseValue) - d1;
                    if (o > 29) {
                        double d11 = (o - 29) / 3.0f;
                        linearInterpHeight = (linearInterpHeight * (1.0d - d11)) + ((-10.0d) * d11);
                    }
                    this.heightMap[i] = linearInterpHeight;
                    i++;
                }
            }
        }
    }

    private IBlockState getBiomeTerrainBlock(Biome biome) {
        if (biome instanceof CustomBiome) {
            CustomBiome cb = (CustomBiome) biome;
            return cb.terrainBlock;
        }
        return BlockInit.astroRock.func_176223_P();
    }

    private void setBlocksInChunk(int x, int z) {
        IBlockState terrainBlock = getBiomeTerrainBlock(getBiomeByCoords(x, z));
        for (int i = 0; i < 4; i++) {
            int j = i * 5;
            int k = (i + 1) * 5;
            for (int l = 0; l < 4; l++) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;
                for (int i2 = 0; i2 < 32; i2++) {
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[(i1 + i2) + 1] - d1) * 0.125d;
                    double d6 = (this.heightMap[(j1 + i2) + 1] - d2) * 0.125d;
                    double d7 = (this.heightMap[(k1 + i2) + 1] - d3) * 0.125d;
                    double d8 = (this.heightMap[(l1 + i2) + 1] - d4) * 0.125d;
                    for (int j2 = 0; j2 < 8; j2++) {
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25d;
                        double d13 = (d4 - d2) * 0.25d;
                        for (int k2 = 0; k2 < 4; k2++) {
                            double d16 = (d11 - d10) * 0.25d;
                            double lvt_45_1_ = d10 - d16;
                            for (int l2 = 0; l2 < 4; l2++) {
                                double d = lvt_45_1_ + d16;
                                lvt_45_1_ = d;
                                if (d > 0.0d) {
                                    this.primer.func_177855_a((i * 4) + k2, (i2 * 8) + j2, (l * 4) + l2, terrainBlock);
                                } else if ((i2 * 8) + j2 < this.world.func_181545_F()) {
                                    this.primer.func_177855_a((i * 4) + k2, (i2 * 8) + j2, (l * 4) + l2, terrainBlock);
                                }
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    private void replaceBiomeBlocks() {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, this.curChunkX, this.curChunkZ, this.primer, this.world)) {
            return;
        }
        this.depthBuffer = this.surfaceNoise.func_151599_a(this.depthBuffer, this.curChunkX * 16, this.curChunkZ * 16, 16, 16, 0.0625d, 0.0625d, 1.0d);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                generateBiomeTerrain(this.world, this.rand, this.primer, (this.curChunkX * 16) + x, (this.curChunkZ * 16) + z, this.depthBuffer[z + (x * 16)], this.curChunkX, this.curChunkZ);
            }
        }
    }

    private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, int chunkX, int chunkZ) {
        int seaLevel = worldIn.func_181545_F();
        Biome biomeUse = getBiomeByCoords(chunkX, chunkZ);
        IBlockState topBlock = biomeUse.field_76752_A;
        IBlockState fillerBlock = biomeUse.field_76753_B;
        IBlockState terrainBlock = getBiomeTerrainBlock(biomeUse);
        BlockPos.MutableBlockPos tempCheckPos = new BlockPos.MutableBlockPos();
        int j = -1;
        int k = (int) ((noiseVal / 3.0d) + 3.0d + (rand.nextDouble() * 0.25d));
        int l = x & 15;
        int i1 = z & 15;
        for (int j1 = 255; j1 >= 0; j1--) {
            if (j1 <= 0) {
                chunkPrimerIn.func_177855_a(i1, j1, l, Blocks.field_150357_h.func_176223_P());
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.func_177856_a(i1, j1, l);
                if (iblockstate2.func_185904_a() == Material.field_151579_a) {
                    j = -1;
                } else if (iblockstate2 == terrainBlock) {
                    if (j == -1) {
                        if (k <= 0) {
                            topBlock = Blocks.field_150350_a.func_176223_P();
                            fillerBlock = biomeUse.field_76753_B;
                        } else if (j1 >= seaLevel - 4 && j1 <= seaLevel + 1) {
                            topBlock = biomeUse.field_76752_A;
                            fillerBlock = biomeUse.field_76753_B;
                        }
                        if (j1 < seaLevel && (topBlock == null || topBlock.func_185904_a() == Material.field_151579_a)) {
                            if (this.biome.func_180626_a(tempCheckPos.func_181079_c(x, j1, z)) < 0.15f) {
                                topBlock = Blocks.field_150353_l.func_176223_P();
                            } else {
                                topBlock = terrainBlock;
                            }
                        }
                        j = k;
                        if (j1 >= seaLevel - 1) {
                            chunkPrimerIn.func_177855_a(i1, j1, l, biomeUse.field_76752_A);
                        } else if (j1 < (seaLevel - 7) - k) {
                            topBlock = Blocks.field_150350_a.func_176223_P();
                            fillerBlock = biomeUse.field_76753_B;
                            chunkPrimerIn.func_177855_a(i1, j1, l, biomeUse.field_76753_B);
                        } else {
                            chunkPrimerIn.func_177855_a(i1, j1, l, fillerBlock);
                        }
                    } else if (j > 0) {
                        j--;
                        chunkPrimerIn.func_177855_a(i1, j1, l, fillerBlock);
                    }
                }
            }
        }
    }

    public void func_185931_b(int chunkX, int chunkZ) {
        this.rand.setSeed(this.world.func_72905_C());
        long a = ((this.rand.nextLong() / 2) * 2) + 1;
        long b = ((this.rand.nextLong() / 2) * 2) + 1;
        int baseX = (chunkX * 16) + 1;
        int baseZ = (chunkZ * 16) + 1;
        new BlockPos.MutableBlockPos();
        BlockPos basePos = new BlockPos(baseX, 0, baseZ);
        Biome biome = getBiomeByCoords(chunkX, chunkZ);
        this.rand.setSeed(((((long) chunkX) * a) + (((long) chunkZ) * b)) ^ this.world.func_72905_C());
        this.rand.setSeed(((((long) chunkX) * a) + (((long) chunkZ) * b)) ^ this.world.func_72905_C());
        biome.func_180624_a(this.world, this.rand, basePos);
        WorldEntitySpawner.func_77191_a(this.world, biome, baseX + 8, baseZ + 8, 16, 16, this.rand);
    }

    public boolean func_185933_a(Chunk chunkIn, int x, int z) {
        return false;
    }

    public List<Biome.SpawnListEntry> func_177458_a(EnumCreatureType creatureType, BlockPos pos) {
        Chunk chunk = this.world.func_175726_f(pos);
        return getBiomeByCoords(chunk.field_76635_g, chunk.field_76647_h).func_76747_a(creatureType);
    }

    @Nullable
    public BlockPos func_180513_a(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    public void func_180514_a(Chunk chunkIn, int x, int z) {
    }

    public boolean func_193414_a(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
