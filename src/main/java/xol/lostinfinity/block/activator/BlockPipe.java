package xol.lostinfinity.block.activator;
import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.mob.entity.misc.EntityPipeGameMerchant;
public class BlockPipe extends BlockBasicRotational {
    public BlockPipe(String name) {
        this(name, 60.0f, Material.field_151576_e);
    }
    public BlockPipe(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }
    public BlockPipe(String name, float hardness, Material material, CreativeTabs tab) {
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
        if (!worldIn.field_72995_K && hand == playerIn.func_184600_cs()) {
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-40, -40, -40), pos.func_177982_a(40, 40, 40));
            Iterator it = worldIn.func_72872_a(EntityPipeGameMerchant.class, checkBox).iterator();
            if (it.hasNext()) {
                EntityPipeGameMerchant merch = (EntityPipeGameMerchant) it.next();
                merch.rotate(pos);
            }
        }
        return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
    public IBlockState getStateWithFacing(EnumFacing facing) {
        return func_176223_P().func_177226_a(field_185512_D, facing);
    }
}
