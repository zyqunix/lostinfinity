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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockLightSwitch.class */
public class BlockLightSwitch extends BlockBasic {
    public BlockLightSwitch(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            if (state.func_177230_c().equals(BlockInit.lightSwitchOn)) {
                worldIn.func_175656_a(pos, BlockInit.lightSwitchOff.func_176223_P());
            } else {
                worldIn.func_175656_a(pos, BlockInit.lightSwitchOn.func_176223_P());
            }
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundEvents.field_187750_dc, SoundCategory.BLOCKS, 2.0f, 1.0f);
            return true;
        }
        return true;
    }
}
