package xol.lostinfinity.block.misc;
import javax.annotation.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicRotational;
import xol.lostinfinity.block.tileentity.TileEntityCthulhuSpawner;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
public class BlockCthulhuSpawner extends BlockBasicRotational implements ITileEntityProvider {
    public static final int BLOCK_DISTANCE = 30;
    public BlockCthulhuSpawner(String name) {
        super(name);
    }
    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileEntityCthulhuSpawner();
    }
    public static EnumFacing getFacing(World world, BlockPos pos) {
        IBlockState state = world.func_180495_p(pos);
        if (state.func_177230_c() != BlockInit.cthulhuSpawner) {
            return EnumFacing.DOWN;
        }
        return state.func_177229_b(field_185512_D);
    }
    public static boolean spawnCthulhu(World world, BlockPos pos) {
        IBlockState state = world.func_180495_p(pos);
        if (state.func_177230_c() != BlockInit.cthulhuSpawner) {
            return false;
        }
        BlockPos center = pos.func_177967_a(EnumFacing.NORTH, 30);
        BlockPos east = center.func_177967_a(EnumFacing.WEST, 30);
        BlockPos south = center.func_177967_a(EnumFacing.NORTH, 30);
        BlockPos west = center.func_177967_a(EnumFacing.EAST, 30);
        if (getFacing(world, east) != EnumFacing.EAST || getFacing(world, south) != EnumFacing.SOUTH || getFacing(world, west) != EnumFacing.WEST) {
            return false;
        }
        TileEntityCthulhuSpawner eastSpawner = (TileEntityCthulhuSpawner) world.func_175625_s(east);
        TileEntityCthulhuSpawner southSpawner = (TileEntityCthulhuSpawner) world.func_175625_s(south);
        TileEntityCthulhuSpawner westSpawner = (TileEntityCthulhuSpawner) world.func_175625_s(west);
        if (isValid(eastSpawner) && isValid(southSpawner) && isValid(westSpawner)) {
            eastSpawner.spawned = true;
            southSpawner.spawned = true;
            westSpawner.spawned = true;
            EntityCthulhu cthulhu = new EntityCthulhu(world);
            cthulhu.func_70107_b(((double) center.func_177958_n()) + 0.5d, center.func_177956_o(), ((double) center.func_177952_p()) + 0.5d);
            world.func_72838_d(cthulhu);
            world.func_184133_a((EntityPlayer) null, cthulhu.func_180425_c(), SoundInit.TT_PORTAL, SoundCategory.HOSTILE, 1.0f, 1.0f);
            for (EntityPlayer contender : world.func_72872_a(EntityPlayer.class, new AxisAlignedBB(cthulhu.func_180425_c()).func_186662_g(70.0d))) {
                world.func_184133_a((EntityPlayer) null, contender.func_180425_c(), SoundInit.TT_PORTAL, SoundCategory.HOSTILE, 1.0f, 1.0f);
            }
            return true;
        }
        return false;
    }
    private static boolean isValid(TileEntityCthulhuSpawner spawner) {
        return spawner != null && spawner.canSpawn();
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.func_70093_af()) {
            playerIn.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.CTHULHU_SPAWNER.getId(), worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
            return true;
        }
        return true;
    }
}
