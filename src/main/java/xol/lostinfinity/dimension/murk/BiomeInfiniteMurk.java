package xol.lostinfinity.dimension.murk;
import java.awt.Color;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.dimension.util.DimensionNoBuild;
import xol.lostinfinity.dimension.util.IDamageRestricted;
public class BiomeInfiniteMurk extends DimensionNoBuild implements IDamageRestricted {
    private static Biome.BiomeProperties properties = new Biome.BiomeProperties("infinitemurk").func_185396_a();
    public BiomeInfiniteMurk() {
        super(properties, "infinitemurk");
    }
    @SideOnly(Side.CLIENT)
    public int func_76731_a(float par1) {
        return Color.BLACK.getRGB();
    }
    public void func_180624_a(World worldIn, Random rand, BlockPos pos) {
    }
    public int getWaterColorMultiplier() {
        return 3139384;
    }
    @Override // xol.lostinfinity.dimension.util.IDamageRestricted
    public String allowedTypes() {
        return "Darkborn";
    }
}
