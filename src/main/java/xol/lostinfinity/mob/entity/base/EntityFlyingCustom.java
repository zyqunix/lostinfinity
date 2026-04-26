package xol.lostinfinity.mob.entity.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityFlyingCustom.class */
public abstract class EntityFlyingCustom extends EntityMultipleLives {
    protected float rawFlySpeed;

    public EntityFlyingCustom(World worldIn) {
        super(worldIn);
        this.rawFlySpeed = 0.91f;
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    protected void func_184231_a(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    public void func_191986_a(float strafe, float vertical, float forward) {
        if (func_70090_H()) {
            func_191958_b(strafe, vertical, forward, 0.02f);
            func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.800000011920929d;
            this.field_70181_x *= 0.800000011920929d;
            this.field_70179_y *= 0.800000011920929d;
        } else if (func_180799_ab()) {
            func_191958_b(strafe, vertical, forward, 0.02f);
            func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.5d;
            this.field_70181_x *= 0.5d;
            this.field_70179_y *= 0.5d;
        } else {
            float f = this.rawFlySpeed;
            if (this.field_70122_E) {
                BlockPos underPos = new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(func_174813_aQ().field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
                IBlockState underState = this.field_70170_p.func_180495_p(underPos);
                f = underState.func_177230_c().getSlipperiness(underState, this.field_70170_p, underPos, this) * 0.91f;
            }
            float f1 = 0.16277136f / ((f * f) * f);
            func_191958_b(strafe, vertical, forward, this.field_70122_E ? 0.1f * f1 : 0.02f);
            float f2 = this.rawFlySpeed;
            if (this.field_70122_E) {
                BlockPos underPos2 = new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(func_174813_aQ().field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v));
                IBlockState underState2 = this.field_70170_p.func_180495_p(underPos2);
                f2 = underState2.func_177230_c().getSlipperiness(underState2, this.field_70170_p, underPos2, this) * 0.91f;
            }
            func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double) f2;
            this.field_70181_x *= (double) f2;
            this.field_70179_y *= (double) f2;
        }
        this.field_184618_aE = this.field_70721_aZ;
        double d1 = this.field_70165_t - this.field_70169_q;
        double d0 = this.field_70161_v - this.field_70166_s;
        float f22 = MathHelper.func_76133_a((d1 * d1) + (d0 * d0)) * 4.0f;
        if (f22 > 1.0f) {
            f22 = 1.0f;
        }
        this.field_70721_aZ += (f22 - this.field_70721_aZ) * 0.4f;
        this.field_184619_aG += this.field_70721_aZ;
    }

    public void func_191958_b(float strafe, float up, float forward, float friction) {
        float f = (strafe * strafe) + (up * up) + (forward * forward);
        if (f >= 1.0E-4f) {
            float f2 = MathHelper.func_76129_c(f);
            if (f2 < 1.0f) {
                f2 = 1.0f;
            }
            float f3 = friction / f2;
            float strafe2 = strafe * f3;
            float up2 = up * f3;
            float forward2 = forward * f3;
            if (func_70090_H() || func_180799_ab()) {
                strafe2 *= (float) func_110148_a(SWIM_SPEED).func_111126_e();
                up2 *= (float) func_110148_a(SWIM_SPEED).func_111126_e();
                forward2 *= (float) func_110148_a(SWIM_SPEED).func_111126_e();
            }
            float f1 = MathHelper.func_76126_a(this.field_70177_z * 0.017453292f);
            float f22 = MathHelper.func_76134_b(this.field_70177_z * 0.017453292f);
            this.field_70159_w += (double) ((strafe2 * f22) - (forward2 * f1));
            this.field_70181_x += (double) up2;
            this.field_70179_y += (double) ((forward2 * f22) + (strafe2 * f1));
        }
    }

    public boolean func_70617_f_() {
        return false;
    }
}
