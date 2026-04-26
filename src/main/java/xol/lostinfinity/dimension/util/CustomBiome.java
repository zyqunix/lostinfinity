package xol.lostinfinity.dimension.util;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import xol.lostinfinity.util.Reference;
public abstract class CustomBiome extends Biome {
    public IBlockState terrainBlock;
    public CustomBiome(Biome.BiomeProperties properties, String name) {
        super(properties);
        this.terrainBlock = Blocks.field_150357_h.func_176223_P();
        setRegistryName(Reference.MODID, name);
        this.field_76762_K.clear();
        this.field_76761_J.clear();
        this.field_82914_M.clear();
        this.field_76755_L.clear();
        this.flowers.clear();
        this.field_76760_I.field_76802_A = 0;
        this.field_76760_I.field_76803_B = 0;
    }
}
