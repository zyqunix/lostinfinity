package xol.lostinfinity.block.activator;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.block.tileentity.TileEntityLightEmitter;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLightEmitter.class */
public class BlockLightEmitter extends BlockBasicRotational implements ITileEntityProvider {
    public BlockLightEmitter(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    /* JADX INFO: renamed from: xol.lostinfinity.block.activator.BlockLightEmitter$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLightEmitter$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

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
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return func_176223_P().func_177226_a(field_185512_D, EnumFacing.NORTH);
            default:
                return state;
        }
    }

    public IBlockState getStateWithFacing(EnumFacing facing) {
        return func_176223_P().func_177226_a(field_185512_D, facing);
    }

    public Vec3d getBeamDir(IBlockState state) {
        EnumFacing facing = state.func_177229_b(field_185512_D);
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
            case 1:
                return new Vec3d(0.0d, 0.0d, -1.0d);
            case 2:
                return new Vec3d(1.0d, 0.0d, 0.0d);
            case 3:
                return new Vec3d(0.0d, 0.0d, 1.0d);
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return new Vec3d(-1.0d, 0.0d, 0.0d);
            default:
                return new Vec3d(0.0d, 0.0d, -1.0d);
        }
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, getStateNextRotation(state));
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.BLOCKS, 0.75f, 0.8f + (0.4f * worldIn.field_73012_v.nextFloat()));
            return true;
        }
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLightEmitter();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    private TileEntityLightEmitter getTE(World world, BlockPos pos) {
        return (TileEntityLightEmitter) world.func_175625_s(pos);
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return null;
    }
}
