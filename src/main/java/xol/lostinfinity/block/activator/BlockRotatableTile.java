package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.TabsInit;
public class BlockRotatableTile extends BlockBasicRotational {
    public BlockRotatableTile(String name) {
        this(name, 60.0f, Material.field_151576_e);
    }
    public BlockRotatableTile(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }
    public BlockRotatableTile(String name, float hardness, Material material, CreativeTabs tab) {
        super(name, hardness, material, tab);
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];
        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    public IBlockState getStateNextRotation(IBlockState state) {
        EnumFacing facing = state.func_177229_b(field_185512_D);
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
            case 1:
                return func_176223_P().func_177226_a(field_185512_D, EnumFacing.EAST);
            case 2:
                return func_176223_P().func_177226_a(field_185512_D, EnumFacing.SOUTH);
            case 3:
                return func_176223_P().func_177226_a(field_185512_D, EnumFacing.WEST);
            case TileEntityFusionTable.BOARD_ROWS :
                return func_176223_P().func_177226_a(field_185512_D, EnumFacing.NORTH);
            default:
                return state;
        }
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, getStateNextRotation(state));
            ArrayList<Vec3i> neighbours = new ArrayList<>();
            neighbours.add(new Vec3i(1, 0, 0));
            neighbours.add(new Vec3i(-1, 0, 0));
            neighbours.add(new Vec3i(0, 0, 1));
            neighbours.add(new Vec3i(0, 0, -1));
            for (Vec3i neighbour : neighbours) {
                IBlockState neighbourState = worldIn.func_180495_p(pos.func_177971_a(neighbour));
                Block block = neighbourState.func_177230_c();
                if (block instanceof BlockRotatableTile) {
                    worldIn.func_175656_a(pos.func_177971_a(neighbour), getStateNextRotation(neighbourState));
                }
            }
            return true;
        }
        return true;
    }
    public IBlockState getStateWithFacing(EnumFacing facing) {
        return func_176223_P().func_177226_a(field_185512_D, facing);
    }
}
