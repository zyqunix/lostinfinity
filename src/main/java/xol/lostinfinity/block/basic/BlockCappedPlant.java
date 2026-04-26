package xol.lostinfinity.block.basic;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class BlockCappedPlant extends BlockBasicPlant {
    public static final PropertyBool ACTIVE = PropertyBool.func_177716_a("active");
    public BlockCappedPlant(String name) {
        super(name, Material.field_151585_k);
        func_149672_a(SoundType.field_185850_c);
        func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(ACTIVE, false));
    }
    public IBlockState func_176203_a(int meta) {
        switch (meta) {
            case 0:
                return func_176223_P().func_177226_a(ACTIVE, false);
            case 1:
                return func_176223_P().func_177226_a(ACTIVE, true);
            default:
                return func_176223_P();
        }
    }
    public int func_176201_c(IBlockState state) {
        if (!state.equals(func_176223_P().func_177226_a(ACTIVE, false)) && state.equals(func_176223_P().func_177226_a(ACTIVE, true))) {
            return 1;
        }
        return 0;
    }
    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{ACTIVE});
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K && (playerIn.func_184586_b(hand).func_77973_b() instanceof ItemHoe)) {
            if (func_176201_c(state) == 0) {
                worldIn.func_175656_a(pos, func_176203_a(1));
                return true;
            }
            worldIn.func_175656_a(pos, func_176203_a(0));
            return true;
        }
        return false;
    }
}
