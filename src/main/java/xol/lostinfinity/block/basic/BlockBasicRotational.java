package xol.lostinfinity.block.basic;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicRotational.class */
public class BlockBasicRotational extends BlockHorizontal {
    public BlockBasicRotational(String name) {
        this(name, 60.0f, Material.field_151576_e);
    }

    public BlockBasicRotational(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }

    public BlockBasicRotational(String name, float hardness, Material material, CreativeTabs tab) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149711_c(hardness);
        func_149752_b(60.0f);
        func_149647_a(tab);
        func_149672_a(SoundType.field_185851_d);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{field_185512_D});
    }

    public IBlockState func_185499_a(IBlockState state, Rotation rot) {
        return state.func_177226_a(field_185512_D, rot.func_185831_a(state.func_177229_b(field_185512_D)));
    }

    public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn) {
        return state.func_185907_a(mirrorIn.func_185800_a(state.func_177229_b(field_185512_D)));
    }

    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(field_185512_D, placer.func_174811_aO().func_176734_d());
    }

    public int func_176201_c(IBlockState state) {
        int i = 0 | state.func_177229_b(field_185512_D).func_176736_b();
        return i;
    }

    public IBlockState func_176203_a(int meta) {
        return func_176223_P().func_177226_a(field_185512_D, EnumFacing.func_176731_b(meta));
    }
}
