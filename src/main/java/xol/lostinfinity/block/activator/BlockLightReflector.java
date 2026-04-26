package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLightReflector.class */
public class BlockLightReflector extends BlockBasicRotational {
    public BlockLightReflector(String name) {
        this(name, 60.0f, Material.field_151576_e);
        func_149715_a(0.7f);
    }

    public BlockLightReflector(String name, float hardness, Material material) {
        this(name, hardness, material, TabsInit.TAB_BLOCKS);
    }

    public BlockLightReflector(String name, float hardness, Material material, CreativeTabs tab) {
        super(name, hardness, material, tab);
    }

    /* JADX INFO: renamed from: xol.lostinfinity.block.activator.BlockLightReflector$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLightReflector$1.class */
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

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            worldIn.func_175656_a(pos, getStateNextRotation(state));
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.GEAR_MACHINE, SoundCategory.BLOCKS, 0.75f, 0.8f + (0.4f * worldIn.field_73012_v.nextFloat()));
            return true;
        }
        return true;
    }

    public IBlockState getStateWithFacing(EnumFacing facing) {
        return func_176223_P().func_177226_a(field_185512_D, facing);
    }

    public ArrayList<Vec3d> getBeamDirs(IBlockState state) {
        EnumFacing facing = state.func_177229_b(field_185512_D);
        ArrayList<Vec3d> beamDirs = new ArrayList<>();
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
            case 1:
                beamDirs.add(new Vec3d(0.0d, 0.0d, -1.0d));
                beamDirs.add(new Vec3d(1.0d, 0.0d, 0.0d));
                break;
            case 2:
                beamDirs.add(new Vec3d(1.0d, 0.0d, 0.0d));
                beamDirs.add(new Vec3d(0.0d, 0.0d, 1.0d));
                break;
            case 3:
                beamDirs.add(new Vec3d(0.0d, 0.0d, 1.0d));
                beamDirs.add(new Vec3d(-1.0d, 0.0d, 0.0d));
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                beamDirs.add(new Vec3d(-1.0d, 0.0d, 0.0d));
                beamDirs.add(new Vec3d(0.0d, 0.0d, -1.0d));
                break;
            default:
                beamDirs.add(new Vec3d(0.0d, 0.0d, -1.0d));
                beamDirs.add(new Vec3d(1.0d, 0.0d, 0.0d));
                break;
        }
        return beamDirs;
    }
}
