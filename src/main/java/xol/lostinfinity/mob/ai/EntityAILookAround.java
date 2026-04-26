package xol.lostinfinity.mob.ai;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
public class EntityAILookAround extends EntityAIBase {
    private final EntityLiving parentEntity;
    public EntityAILookAround(EntityLiving ghast) {
        this.parentEntity = ghast;
        func_75248_a(2);
    }
    public boolean func_75250_a() {
        return true;
    }
    public void func_75246_d() {
        if (this.parentEntity.func_70638_az() == null) {
            this.parentEntity.field_70177_z = (-((float) MathHelper.func_181159_b(this.parentEntity.field_70159_w, this.parentEntity.field_70179_y))) * 57.295776f;
            this.parentEntity.field_70761_aq = this.parentEntity.field_70177_z;
            return;
        }
        EntityLivingBase entitylivingbase = this.parentEntity.func_70638_az();
        if (entitylivingbase.func_70068_e(this.parentEntity) < 4096.0d) {
            double d1 = entitylivingbase.field_70165_t - this.parentEntity.field_70165_t;
            double d2 = entitylivingbase.field_70161_v - this.parentEntity.field_70161_v;
            this.parentEntity.field_70177_z = (-((float) MathHelper.func_181159_b(d1, d2))) * 57.295776f;
            this.parentEntity.field_70761_aq = this.parentEntity.field_70177_z;
        }
    }
}
