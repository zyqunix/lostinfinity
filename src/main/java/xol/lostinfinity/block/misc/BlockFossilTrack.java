package xol.lostinfinity.block.misc;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.TabsInit;
public class BlockFossilTrack extends Block {
    protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0d, 0.0d, 0.0d, 1.0d, 0.0625d, 1.0d);
    public BlockFossilTrack(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }
    public BlockFossilTrack(String name, float hardness, Material material, CreativeTabs tab) {
        super(material);
        func_149663_c(name);
        setRegistryName(name);
        func_149647_a(tab);
        func_149722_s();
        func_149672_a(SoundType.field_185849_b);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.TRANSLUCENT;
    }
    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CARPET_AABB;
    }
    public boolean func_149662_c(IBlockState state) {
        return false;
    }
    public boolean func_149686_d(IBlockState state) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.UP) {
            return true;
        }
        return false;
    }
    private Vec3i rotVec(Vec3i vec, double angle) {
        int x = (int) Math.round((((double) vec.func_177958_n()) * Math.cos(angle)) + (((double) vec.func_177952_p()) * Math.sin(angle)));
        int z = (int) Math.round((((double) (-vec.func_177958_n())) * Math.sin(angle)) + (((double) vec.func_177952_p()) * Math.cos(angle)));
        return new Vec3i(x, vec.func_177956_o(), z);
    }
    public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
}
