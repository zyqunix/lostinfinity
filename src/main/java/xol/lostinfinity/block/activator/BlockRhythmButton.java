package xol.lostinfinity.block.activator;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.mob.entity.misc.EntityRhythmGameMerchant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockRhythmButton.class */
public class BlockRhythmButton extends BlockBasicBoolState {
    public BlockRhythmButton(String name) {
        super(name);
        func_149715_a(1.0f);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.field_72995_K) {
            Random rand = new Random();
            AxisAlignedBB checkBox = new AxisAlignedBB(pos.func_177982_a(-10, -10, -10), pos.func_177982_a(10, 10, 10));
            for (EntityRhythmGameMerchant merch : worldIn.func_72872_a(EntityRhythmGameMerchant.class, checkBox)) {
                ((WorldServer) worldIn).func_175739_a(EnumParticleTypes.PORTAL, merch.field_70165_t, merch.field_70163_u, merch.field_70161_v, 5, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + rand.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                merch.pressButton(pos);
            }
            return true;
        }
        return true;
    }

    public IBlockState getActiveState() {
        return func_176223_P().func_177226_a(ACTIVE, true);
    }

    public IBlockState getInactiveState() {
        return func_176223_P().func_177226_a(ACTIVE, false);
    }
}
