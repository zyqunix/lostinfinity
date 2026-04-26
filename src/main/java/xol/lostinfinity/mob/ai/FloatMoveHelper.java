package xol.lostinfinity.mob.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/ai/FloatMoveHelper.class */
public class FloatMoveHelper extends EntityMoveHelper {
    private final EntityLiving parentEntity;
    private int courseChangeCooldown;

    public FloatMoveHelper(EntityLiving ghast) {
        super(ghast);
        this.parentEntity = ghast;
    }

    public void func_75641_c() {
        if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
            double d0 = this.field_75646_b - this.parentEntity.field_70165_t;
            double d1 = this.field_75647_c - this.parentEntity.field_70163_u;
            double d2 = this.field_75644_d - this.parentEntity.field_70161_v;
            double d3 = (d0 * d0) + (d1 * d1) + (d2 * d2);
            int i = this.courseChangeCooldown;
            this.courseChangeCooldown = i - 1;
            if (i <= 0) {
                this.courseChangeCooldown += this.parentEntity.func_70681_au().nextInt(5) + 2;
                double d32 = MathHelper.func_76133_a(d3);
                if (isNotColliding(this.field_75646_b, this.field_75647_c, this.field_75644_d, d32)) {
                    this.parentEntity.field_70159_w += (d0 / d32) * 0.1d;
                    this.parentEntity.field_70181_x += (d1 / d32) * 0.1d;
                    this.parentEntity.field_70179_y += (d2 / d32) * 0.1d;
                    return;
                }
                this.field_188491_h = EntityMoveHelper.Action.WAIT;
            }
        }
    }

    private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
        double d0 = (x - this.parentEntity.field_70165_t) / p_179926_7_;
        double d1 = (y - this.parentEntity.field_70163_u) / p_179926_7_;
        double d2 = (z - this.parentEntity.field_70161_v) / p_179926_7_;
        AxisAlignedBB axisalignedbb = this.parentEntity.func_174813_aQ();
        for (int i = 1; i < p_179926_7_; i++) {
            axisalignedbb = axisalignedbb.func_72317_d(d0, d1, d2);
            if (!this.parentEntity.field_70170_p.func_184144_a(this.parentEntity, axisalignedbb).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
