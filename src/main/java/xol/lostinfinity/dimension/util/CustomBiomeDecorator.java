package xol.lostinfinity.dimension.util;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
public abstract class CustomBiomeDecorator extends BiomeDecorator {
    public void func_180292_a(World world, Random rand, Biome biome, BlockPos pos) {
        doOreGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
        doPlantGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
        doTreeGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
        doMiscGen(world, biome, rand, pos, new BlockPos.MutableBlockPos(), 0, 0, 0);
        new ChunkPos(pos);
    }
    protected void doOreGen(World world, Biome biome, Random rand, BlockPos basePos, BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
    }
    protected void doPlantGen(World world, Biome biome, Random rand, BlockPos basePos, BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
    }
    protected void doTreeGen(World world, Biome biome, Random rand, BlockPos basePos, BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
    }
    protected void doMiscGen(World world, Biome biome, Random rand, BlockPos basePos, BlockPos.MutableBlockPos pos, int posX, int posY, int posZ) {
    }
}
