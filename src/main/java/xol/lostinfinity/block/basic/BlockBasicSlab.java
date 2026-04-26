package xol.lostinfinity.block.basic;
import java.util.Random;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class BlockBasicSlab extends BlockSlab {
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.func_177709_a("variant", Variant.class);
    public BlockBasicSlab mySlab;
    public BlockBasicSlab(String name, Material Material) {
        super(Material);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(60.0f);
        func_149752_b(60.0f);
        func_149647_a(TabsInit.TAB_BLOCKS);
        IBlockState iblockstate = this.field_176227_L.func_177621_b();
        if (!func_176552_j()) {
            iblockstate = iblockstate.func_177226_a(field_176554_a, BlockSlab.EnumBlockHalf.BOTTOM);
            ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
        }
        func_180632_j(iblockstate.func_177226_a(VARIANT, Variant.DEFAULT));
        BlockInit.BLOCKS.add(this);
    }
    public void setMySlab(BlockBasicSlab slab) {
        this.mySlab = slab;
    }
    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return Item.func_150898_a(this.mySlab);
    }
    public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this.mySlab);
    }
    public IBlockState func_176203_a(int meta) {
        IBlockState iblockstate = func_176223_P().func_177226_a(VARIANT, Variant.DEFAULT);
        if (!func_176552_j()) {
            iblockstate = iblockstate.func_177226_a(field_176554_a, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return iblockstate;
    }
    public int func_176201_c(IBlockState state) {
        int i = 0;
        if (!func_176552_j() && state.func_177229_b(field_176554_a) == BlockSlab.EnumBlockHalf.TOP) {
            i = 0 | 8;
        }
        return i;
    }
    protected BlockStateContainer func_180661_e() {
        return func_176552_j() ? new BlockStateContainer(this, new IProperty[]{VARIANT}) : new BlockStateContainer(this, new IProperty[]{field_176554_a, VARIANT});
    }
    public String func_150002_b(int meta) {
        return super.func_149739_a();
    }
    public IProperty<?> func_176551_l() {
        return VARIANT;
    }
    public Comparable<?> func_185674_a(ItemStack stack) {
        return Variant.DEFAULT;
    }
    public static class Double extends BlockBasicSlab {
        public Double(String name, Material material) {
            super(name, material);
        }
        @Override // xol.lostinfinity.block.basic.BlockBasicSlab
        public boolean func_176552_j() {
            return true;
        }
    }
    public static class Half extends BlockBasicSlab {
        public Half(String name, Material material) {
            super(name, material);
        }
        @Override // xol.lostinfinity.block.basic.BlockBasicSlab
        public boolean func_176552_j() {
            return false;
        }
    }
    public enum Variant implements IStringSerializable {
        DEFAULT;
        public String func_176610_l() {
            return "default";
        }
    }
    public boolean func_176552_j() {
        this.field_176227_L.func_177621_b();
        if (func_176552_j()) {
            return true;
        }
        return false;
    }
}
