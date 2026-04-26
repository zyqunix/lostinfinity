package xol.lostinfinity.block.activator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.init.BlockInit;
public class BlockLightTile extends BlockBasic {
    public BlockLightTile(String name) {
        super(name);
        func_149715_a(1.0f);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            if (state.func_177230_c().equals(BlockInit.lightTileLit)) {
                worldIn.func_175656_a(pos, BlockInit.lightTileDark.func_176223_P());
            } else {
                worldIn.func_175656_a(pos, BlockInit.lightTileLit.func_176223_P());
            }
            BlockPos[] adjblocks = {pos.func_177978_c(), pos.func_177974_f(), pos.func_177968_d(), pos.func_177976_e()};
            for (BlockPos side : adjblocks) {
                if (worldIn.func_180495_p(side).func_177230_c().equals(BlockInit.lightTileDark)) {
                    worldIn.func_175656_a(side, BlockInit.lightTileLit.func_176223_P());
                } else if (worldIn.func_180495_p(side).func_177230_c().equals(BlockInit.lightTileLit)) {
                    worldIn.func_175656_a(side, BlockInit.lightTileDark.func_176223_P());
                }
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187620_cL, SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        }
        return true;
    }
}
