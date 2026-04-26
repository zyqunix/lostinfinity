package xol.lostinfinity.block.harvest;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.activator.BlockRadionPillar;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/harvest/BlockRadionOre.class */
public class BlockRadionOre extends BlockBasicBoolState {
    private static final boolean DEBUG = true;

    public BlockRadionOre(String name) {
        super(name);
        func_149675_a(true);
        func_149711_c(3.0f);
        func_149752_b(5.0f);
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(pos.func_177982_a(-3, -3, -3), pos.func_177982_a(3, 3, 3));
            for (BlockPos nearpos : nearblocks) {
                if ((world.func_180495_p(nearpos).func_177230_c() instanceof BlockRadionOre) && world.field_73012_v.nextBoolean()) {
                    IBlockState nearState = world.func_180495_p(nearpos);
                    int meta = func_176201_c(nearState);
                    if (meta == 0) {
                        world.func_175656_a(nearpos, func_176203_a(DEBUG));
                    } else {
                        world.func_175656_a(nearpos, func_176203_a(0));
                    }
                }
            }
        }
    }

    public Item func_180660_a(IBlockState state, Random rand, int fortune) {
        return ItemStack.field_190927_a.func_77973_b();
    }

    public void func_176206_d(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.field_72995_K && !worldIn.func_184137_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 15.0d, false).func_184812_l_()) {
            worldIn.func_175656_a(pos, func_176203_a(0));
            if (func_176201_c(state) == DEBUG) {
                Iterable<BlockPos> nearBlocks = BlockPos.func_177980_a(pos.func_177982_a(-15, -15, -15), pos.func_177982_a(15, 15, 15));
                Iterator<BlockPos> it = nearBlocks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BlockPos nearPos = it.next();
                    if ((worldIn.func_180495_p(nearPos).func_177230_c() instanceof BlockRadionPillar) && worldIn.func_180495_p(nearPos).func_177230_c().func_176201_c(worldIn.func_180495_p(nearPos)) == 0) {
                        System.out.println("RadionOre destroyed, charging RadionPillar at " + nearPos);
                        BlockRadionPillar pillarBlock = (BlockRadionPillar) worldIn.func_180495_p(nearPos).func_177230_c();
                        pillarBlock.lightUpPillarSegment(worldIn, nearPos);
                        break;
                    }
                }
            }
        }
        if (worldIn.field_72995_K && func_176201_c(state) == DEBUG) {
            EntityPlayer player = worldIn.func_184137_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 15.0d, false);
            EnumFacing facing = player.func_174811_aO();
            player.func_184185_a(SoundInit.ORE_LIGHT, 0.5f, 1.0f + ((worldIn.field_73012_v.nextFloat() - worldIn.field_73012_v.nextFloat()) * 0.2f));
            double centerX = ((double) pos.func_177958_n()) + 0.5d;
            double centerY = ((double) pos.func_177956_o()) + 0.5d;
            double centerZ = ((double) pos.func_177952_p()) + 0.5d;
            switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
                case DEBUG /* 1 */:
                    for (int i = 0; i < 15; i += DEBUG) {
                        double offSetX = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.8d : -(worldIn.field_73012_v.nextDouble() % 0.8d);
                        double offsetY = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.5d : -(worldIn.field_73012_v.nextDouble() % 0.5d);
                        worldIn.func_175688_a(EnumParticleTypes.DRAGON_BREATH, centerX + offSetX, centerY + offsetY, centerZ + (((double) worldIn.field_73012_v.nextFloat()) % 0.8d), 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                    break;
                case 2:
                    for (int i2 = 0; i2 < 15; i2 += DEBUG) {
                        double offSetX2 = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.8d : -(worldIn.field_73012_v.nextDouble() % 0.8d);
                        double offsetY2 = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.5d : -(worldIn.field_73012_v.nextDouble() % 0.5d);
                        worldIn.func_175688_a(EnumParticleTypes.DRAGON_BREATH, centerX + offSetX2, centerY + offsetY2, centerZ - (((double) worldIn.field_73012_v.nextFloat()) % 0.8d), 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                    break;
                case 3:
                    for (int i3 = 0; i3 < 15; i3 += DEBUG) {
                        double offSetZ = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.8d : -(worldIn.field_73012_v.nextDouble() % 0.8d);
                        double offsetY3 = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.5d : -(worldIn.field_73012_v.nextDouble() % 0.5d);
                        worldIn.func_175688_a(EnumParticleTypes.DRAGON_BREATH, centerX - (((double) worldIn.field_73012_v.nextFloat()) % 0.8d), centerY + offsetY3, centerZ + offSetZ, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    for (int i4 = 0; i4 < 15; i4 += DEBUG) {
                        double offSetZ2 = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.8d : -(worldIn.field_73012_v.nextDouble() % 0.8d);
                        double offsetY4 = worldIn.field_73012_v.nextBoolean() ? worldIn.field_73012_v.nextDouble() % 0.5d : -(worldIn.field_73012_v.nextDouble() % 0.5d);
                        worldIn.func_175688_a(EnumParticleTypes.DRAGON_BREATH, centerX + (((double) worldIn.field_73012_v.nextFloat()) % 0.8d), centerY + offsetY4, centerZ + offSetZ2, 0.0d, 0.0d, 0.0d, new int[0]);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: xol.lostinfinity.block.harvest.BlockRadionOre$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/harvest/BlockRadionOre$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = BlockRadionOre.DEBUG;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
}
