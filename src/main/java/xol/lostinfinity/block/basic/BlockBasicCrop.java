package xol.lostinfinity.block.basic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.TabsInit;
public class BlockBasicCrop extends BlockCrops {
    private Item seed = null;
    private Item crop = null;
    private Block soilBlock = null;
    public BlockBasicCrop(String name) {
        func_149663_c(name);
        setRegistryName(name);
        func_149647_a(TabsInit.TAB_BLOCKS);
        BlockInit.BLOCKS.add(this);
    }
    public void setSeedCropSoil(Item seedIn, Item cropIn, Block block) {
        this.seed = seedIn;
        this.crop = cropIn;
        this.soilBlock = block;
    }
    protected Item func_149866_i() {
        return this.seed;
    }
    protected Item func_149865_P() {
        return this.crop;
    }
    public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
        return worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == this.soilBlock;
    }
    public int getCropAge(IBlockState state) {
        return ((Integer) state.func_177229_b(func_185524_e())).intValue();
    }
}
