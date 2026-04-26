package xol.lostinfinity.dimension.nonexistence;
import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.dimension.util.DimensionNoBuild;
public class BiomeNonexistence extends DimensionNoBuild {
    private static Biome.BiomeProperties properties = new Biome.BiomeProperties("nonexistence").func_185396_a();
    public BiomeNonexistence() {
        super(properties, "nonexistence");
    }
    @SideOnly(Side.CLIENT)
    public int func_76731_a(float par1) {
        return Color.BLACK.getRGB();
    }
    public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
    }
}
