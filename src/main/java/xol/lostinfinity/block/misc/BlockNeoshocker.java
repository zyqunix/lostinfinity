package xol.lostinfinity.block.misc;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.util.damagesource.DeathMessage;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockNeoshocker.class */
public class BlockNeoshocker extends BlockBasic {
    public static final PropertyInteger AMOUNT = PropertyInteger.func_177719_a("amount", 0, 2);

    public BlockNeoshocker(String name) {
        super(name);
    }

    public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return func_176223_P().func_177226_a(AMOUNT, 0);
    }

    public IBlockState func_176203_a(int meta) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(meta));
    }

    public int func_176201_c(IBlockState state) {
        return ((Integer) state.func_177229_b(AMOUNT)).intValue();
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer(this, new IProperty[]{AMOUNT});
    }

    public IBlockState getStateWithAmount(int amount) {
        return func_176223_P().func_177226_a(AMOUNT, Integer.valueOf(amount));
    }

    public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
        super.func_176199_a(worldIn, pos, entityIn);
        if (!worldIn.field_72995_K && (entityIn instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) entityIn;
            int shockValue = func_176201_c(worldIn.func_180495_p(pos));
            if (shockValue == 2 && !player.func_184812_l_() && player.field_70173_aa % 3 == 0 && player.func_110143_aJ() > 0.0f) {
                player.func_70606_j(player.func_110143_aJ() - (player.func_110138_aP() / 4.0f));
                if (player.func_110143_aJ() <= 0.0f) {
                    DeathMessage.broadcastDeathMessage(player.func_184102_h(), TextFmt.Red + player.func_70005_c_() + " was electrocuted.");
                }
            }
        }
    }
}
