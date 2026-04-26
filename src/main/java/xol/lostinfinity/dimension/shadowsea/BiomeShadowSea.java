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
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/shadowsea/BiomeShadowSea.class */
public class BiomeShadowSea extends CustomBiome implements IDamageRestricted {
    private static Biome.BiomeProperties properties = new Biome.BiomeProperties("shadowsea").func_185396_a();
    private CoralGenerator coralGen;

    public BiomeShadowSea() {
        super(properties, "shadowsea");
        this.coralGen = new CoralGenerator();
        this.field_76752_A = BlockInit.seasand.func_176223_P();
        this.field_76753_B = BlockInit.sandySeastone.func_176223_P();
        this.terrainBlock = BlockInit.seastone.func_176223_P();
    }

    @SideOnly(Side.CLIENT)
    public int func_76731_a(float par1) {
        return 3149998;
    }

    public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
        generatePlants(worldIn, rand, pos);
        this.coralGen.setWorld(worldIn, this.field_76752_A);
        int currentX = pos.func_177958_n();
        int currentZ = pos.func_177952_p();
        int runs = 1 + rand.nextInt(4);
        for (int k = 0; k < runs; k++) {
            int x = currentX + rand.nextInt(8) + 8;
            int z = currentZ + rand.nextInt(8) + 8;
            int y = worldIn.func_189649_b(x, z);
            BlockPos treePos = new BlockPos(x, y, z);
            this.coralGen.genRandomCoral(treePos);
        }
        if (rand.nextInt(25) == 5) {
            int x2 = currentX + rand.nextInt(8) + 8;
            int z2 = currentZ + rand.nextInt(8) + 8;
            int y2 = worldIn.func_189649_b(x2, z2);
            BlockPos genPos = new BlockPos(x2, y2, z2);
            generateClaySpire(worldIn, rand, genPos);
        }
        if (rand.nextInt(280) == 45) {
            int x3 = currentX + rand.nextInt(8) + 8;
            int z3 = currentZ + rand.nextInt(8) + 8;
            int y3 = worldIn.func_189649_b(x3, z3);
            BlockPos treePos2 = new BlockPos(x3, y3, z3);
            this.coralGen.genRandomExtras(treePos2);
        }
    }

    public int getWaterColorMultiplier() {
        return 3139384;
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
                    generateKelp(world, rand, genPos);
                } else if (weightedPick < 10) {
                    generateWiggleWeed(world, rand, genPos);
                } else if (weightedPick < 25) {
                    world.func_175656_a(genPos, BlockInit.seabush.func_176223_P());
                } else {
                    world.func_175656_a(genPos, BlockInit.searock.func_176223_P());
                }
            }
        }
    }

    private void generateKelp(World world, Random rand, BlockPos pos) {
        int height = 15 + rand.nextInt(20);
        for (int i = 0; i <= height; i++) {
            world.func_175656_a(pos.func_177982_a(0, i, 0), ((float) i) < ((float) height) * 0.75f ? BlockInit.kelp.func_176223_P() : BlockInit.denseKelp.func_176223_P());
        }
    }

    private void generateWiggleWeed(World world, Random rand, BlockPos pos) {
        int height = 7 + rand.nextInt(12);
        int i = 0;
        while (i <= height) {
            world.func_175656_a(pos.func_177982_a(0, i, 0), i != height ? BlockInit.wiggleweed.func_176203_a(0) : BlockInit.wiggleweed.func_176203_a(1));
            i++;
        }
    }

    private void generateClaySpire(World world, Random rand, BlockPos pos) {
        boolean z;
        IBlockState belowState = world.func_180495_p(pos.func_177977_b());
        if (belowState == this.field_76752_A || belowState == this.terrainBlock) {
            BlockPos curPos = pos;
            boolean shiftLast = true;
            int height = 15 + rand.nextInt(34);
            for (int i = 0; i < height; i++) {
                for (int xPos = -1; xPos <= 1; xPos++) {
                    for (int zPos = -1; zPos <= 1; zPos++) {
                        if (i != height - 1 || xPos != 0 || zPos != 0) {
                            world.func_175656_a(curPos.func_177982_a(xPos, 0, zPos), rand.nextBoolean() ? BlockInit.seastone.func_176223_P() : BlockInit.seaClay.func_176223_P());
                        }
                    }
                }
                if (!shiftLast) {
                    curPos = curPos.func_177982_a((-1) + rand.nextInt(3), 1, (-1) + rand.nextInt(3));
                    z = true;
                } else {
                    curPos = curPos.func_177982_a(0, 1, 0);
                    z = false;
                }
                shiftLast = z;
            }
        }
    }
}
