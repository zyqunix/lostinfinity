package xol.lostinfinity.block.misc;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasicGlass;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/misc/BlockLaserField.class */
public class BlockLaserField extends BlockBasicGlass {
    private int securityLevel;

    public BlockLaserField(String name, int security) {
        super(name);
        this.securityLevel = 0;
        this.securityLevel = security;
        func_149715_a(1.0f);
    }

    @Nullable
    public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return field_185506_k;
    }

    public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.field_72995_K) {
            boolean kill = true;
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                if (player.func_70644_a(PotionInit.SECURITY_CLEARANCE)) {
                    int security_clear = player.func_70660_b(PotionInit.SECURITY_CLEARANCE).func_76458_c() + 1;
                    if (security_clear >= this.securityLevel) {
                        kill = false;
                    }
                }
            }
            if (kill && (entityIn instanceof EntityLivingBase)) {
                EntityLivingBase killed = (EntityLivingBase) entityIn;
                if (killed.func_110143_aJ() > 0.0f) {
                    killed.func_70606_j(0.0f);
                    worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.SCANNER, SoundCategory.MASTER, 1.0f, 1.0f);
                }
            }
        }
    }
}
