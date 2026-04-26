package xol.lostinfinity.block.activator;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerBase;
import xol.lostinfinity.util.coordinates.ContestCoordinates;
public class BlockDungeonSelector extends BlockBasic {
    public BlockDungeonSelector(String name) {
        super(name, Material.field_151576_e);
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        AxisAlignedBB aabb;
        if (!worldIn.field_72995_K) {
            if (worldIn.field_73011_w.func_186058_p() == DimensionInit.grandmasterOutpost && (aabb = gameByPos(pos)) != null) {
                for (EntityControllerBase search_cont : worldIn.func_72872_a(EntityControllerBase.class, aabb)) {
                    search_cont.registerTouch(playerIn);
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
    private AxisAlignedBB gameByPos(BlockPos pos) {
        AxisAlignedBB game = null;
        if (posInAABB(ContestCoordinates.huntersArenaAABB(), pos)) {
            game = ContestCoordinates.huntersArenaAABB();
        } else if (posInAABB(ContestCoordinates.redlightArenaAABB(), pos)) {
            game = ContestCoordinates.redlightArenaAABB();
        } else if (posInAABB(ContestCoordinates.lightBridgeArenaAABB(), pos)) {
            game = ContestCoordinates.lightBridgeArenaAABB();
        } else if (posInAABB(ContestCoordinates.parkourArenaAABB(), pos)) {
            game = ContestCoordinates.parkourArenaAABB();
        }
        return game;
    }
    private boolean posInAABB(AxisAlignedBB aabb, BlockPos pos) {
        return ((double) pos.func_177958_n()) >= aabb.field_72340_a && ((double) pos.func_177958_n()) <= aabb.field_72336_d && ((double) pos.func_177956_o()) >= aabb.field_72338_b && ((double) pos.func_177956_o()) <= aabb.field_72337_e && ((double) pos.func_177952_p()) >= aabb.field_72339_c && ((double) pos.func_177952_p()) <= aabb.field_72334_f;
    }
}
