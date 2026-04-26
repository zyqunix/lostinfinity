package xol.lostinfinity.block.activator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.entity.sea.EntityFish;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockFishChow.class */
public class BlockFishChow extends BlockBasic {
    public BlockFishChow(String name) {
        super(name);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            playerIn.func_145747_a(new TextComponentString(TextFmt.Red + "You break off some of the fish chow."));
            for (EntityFish fish : worldIn.func_72872_a(EntityFish.class, new AxisAlignedBB(pos).func_72314_b(25, 25, 25))) {
                fish.setTargetPos(pos.func_177984_a());
            }
            return true;
        }
        return true;
    }
}
