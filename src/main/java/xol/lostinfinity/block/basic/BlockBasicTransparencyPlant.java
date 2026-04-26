package xol.lostinfinity.block.basic;

import javax.annotation.Nullable;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/basic/BlockBasicTransparencyPlant.class */
public class BlockBasicTransparencyPlant extends BlockBush {
    public BlockBasicTransparencyPlant(String name, Material material) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149647_a(TabsInit.TAB_BLOCKS);
        func_149672_a(SoundType.field_185850_c);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        return true;
    }

    public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
        return true;
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
