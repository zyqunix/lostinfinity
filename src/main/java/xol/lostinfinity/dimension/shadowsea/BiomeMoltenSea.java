package xol.lostinfinity.dimension.shadowsea;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.dimension.util.CustomBiome;
import xol.lostinfinity.dimension.util.IDamageRestricted;
import xol.lostinfinity.dimension.util.WorldGenStructure;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/BiomeMoltenSea.class */
public class BiomeMoltenSea extends CustomBiome implements IDamageRestricted {
    private static Biome.BiomeProperties properties = new Biome.BiomeProperties("moltensea").func_185396_a();
    private CoralGenerator coralGen;

    public BiomeMoltenSea() {
        super(properties, "moltensea");
        this.coralGen = new CoralGenerator();
        this.field_76752_A = BlockInit.heatsand.func_176223_P();
        this.field_76753_B = BlockInit.moltenSeastone.func_176223_P();
        this.terrainBlock = BlockInit.igneousSeastone.func_176223_P();
    }

    @SideOnly(Side.CLIENT)
    public int func_76731_a(float par1) {
        return 9902613;
    }

    public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
        generatePlants(worldIn, rand, pos);
        this.coralGen.setWorld(worldIn, this.field_76752_A);
        int currentX = pos.func_177958_n();
        int currentZ = pos.func_177952_p();
        int runs = 1 + rand.nextInt(3);
        for (int k = 0; k < runs; k++) {
            int x = currentX + rand.nextInt(8) + 8;
            int z = currentZ + rand.nextInt(8) + 8;
            int y = worldIn.func_189649_b(x, z);
            BlockPos treePos = new BlockPos(x, y, z);
            this.coralGen.genVolcanicCoral(treePos);
        }
        if (rand.nextInt(120) == 75) {
            int x2 = currentX + rand.nextInt(8) + 8;
            int z2 = currentZ + rand.nextInt(8) + 8;
            int y2 = worldIn.func_189649_b(x2, z2);
            BlockPos genPos = new BlockPos(x2, y2, z2);
            generatePearlHouse(worldIn, rand, genPos);
        }
        for (int i = 0; i < 25; i++) {
            int x3 = currentX + rand.nextInt(8) + 8;
            int z3 = currentZ + rand.nextInt(8) + 8;
            int y3 = 30 + rand.nextInt(60);
            BlockPos pearlPos = new BlockPos(x3, y3, z3);
            if (worldIn.func_180495_p(pearlPos) == this.terrainBlock) {
                worldIn.func_175656_a(pearlPos, BlockInit.igneousPearlOre.func_176223_P());
            }
        }
    }

    public int getWaterColorMultiplier() {
        return 9902613;
    }

    @Override // xol.lostinfinity.dimension.util.IDamageRestricted
    public String allowedTypes() {
        return "Aquatic";
    }

    private void generatePlants(World world, Random rand, BlockPos pos) {
        int runs = 10 + rand.nextInt(15);
        int currentX = pos.func_177958_n();
        int currentZ = pos.func_177952_p();
        for (int k = 0; k < runs; k++) {
            int x = currentX + rand.nextInt(8) + 8;
            int z = currentZ + rand.nextInt(8) + 8;
            int y = world.func_189649_b(x, z);
            BlockPos genPos = new BlockPos(x, y, z);
            if (!world.func_175623_d(genPos.func_177977_b())) {
                int weightedPick = rand.nextInt(50);
                if (weightedPick < 5) {
                    generateEmberkelp(world, rand, genPos);
                } else if (weightedPick < 10) {
                    generateSmokeweed(world, rand, genPos);
                } else if (weightedPick < 15) {
                    generateHeatweed(world, rand, genPos);
                } else {
                    world.func_175656_a(genPos, BlockInit.moltrock.func_176223_P());
                }
            }
        }
    }

    private void generateEmberkelp(World world, Random rand, BlockPos pos) {
        int height = 15 + rand.nextInt(20);
        for (int i = 0; i <= height; i++) {
            world.func_175656_a(pos.func_177982_a(0, i, 0), ((float) i) < ((float) height) * 0.75f ? BlockInit.emberkelp.func_176223_P() : BlockInit.emberkelp.func_176203_a(1));
        }
    }

    private void generateSmokeweed(World world, Random rand, BlockPos pos) {
        int height = 7 + rand.nextInt(12);
        int i = 0;
        while (i <= height) {
            world.func_175656_a(pos.func_177982_a(0, i, 0), i != height ? BlockInit.smokeweed.func_176203_a(0) : BlockInit.smokeweed.func_176203_a(1));
            i++;
        }
    }

    private void generateHeatweed(World world, Random rand, BlockPos pos) {
        int height = 3 + rand.nextInt(5);
        int i = 0;
        while (i <= height) {
            world.func_175656_a(pos.func_177982_a(0, i, 0), i != height ? BlockInit.heatweed.func_176203_a(0) : BlockInit.heatweed.func_176203_a(1));
            i++;
        }
    }

    private void generatePearlHouse(World world, Random rand, BlockPos pos) {
        IBlockState belowState = world.func_180495_p(pos.func_177977_b());
        if (belowState == this.field_76752_A || belowState == this.terrainBlock) {
            BlockPos curPos = pos;
            boolean shiftLast = true;
            int height = 35 + rand.nextInt(30);
            for (int i = 0; i <= height; i++) {
                if (i == height) {
                    new WorldGenStructure("sea/pearl_house").func_180709_b(world, rand, curPos.func_177982_a(-(2 + rand.nextInt(3)), 0, -(2 + rand.nextInt(3))));
                } else {
                    for (int xPos = -1; xPos <= 1; xPos++) {
                        for (int zPos = -1; zPos <= 1; zPos++) {
                            world.func_175656_a(curPos.func_177982_a(xPos, 0, zPos), BlockInit.igneousSeastone.func_176223_P());
                        }
                    }
                    if (!shiftLast) {
                        curPos = curPos.func_177982_a((-1) + rand.nextInt(3), 1, (-1) + rand.nextInt(3));
                        shiftLast = true;
                    } else {
                        curPos = curPos.func_177982_a(0, 1, 0);
                        shiftLast = false;
                    }
                }
            }
        }
    }
}
