package xol.lostinfinity.mob.ai;

import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/ai/EntityAIRandomFly.class */
public class EntityAIRandomFly extends EntityAIBase {
    private final EntityLiving parentEntity;

    public EntityAIRandomFly(EntityLiving ghast) {
        this.parentEntity = ghast;
        func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityMoveHelper entitymovehelper = this.parentEntity.func_70605_aq();
        if (!entitymovehelper.func_75640_a()) {
            return true;
        }
        double d0 = entitymovehelper.func_179917_d() - this.parentEntity.field_70165_t;
        double d1 = entitymovehelper.func_179919_e() - this.parentEntity.field_70163_u;
        double d2 = entitymovehelper.func_179918_f() - this.parentEntity.field_70161_v;
        double d3 = (d0 * d0) + (d1 * d1) + (d2 * d2);
        return d3 < 1.0d || d3 > 3600.0d;
    }

    public boolean func_75253_b() {
        return false;
    }

    public void func_75249_e() {
        Random random = this.parentEntity.func_70681_au();
        double d0 = this.parentEntity.field_70165_t + ((double) (((random.nextFloat() * 2.0f) - 1.0f) * 16.0f));
        double d1 = this.parentEntity.field_70163_u + ((double) (((random.nextFloat() * 2.0f) - 1.0f) * 16.0f));
        double d2 = this.parentEntity.field_70161_v + ((double) (((random.nextFloat() * 2.0f) - 1.0f) * 16.0f));
        this.parentEntity.func_70605_aq().func_75642_a(d0, d1, d2, 1.0d);
    }
}
