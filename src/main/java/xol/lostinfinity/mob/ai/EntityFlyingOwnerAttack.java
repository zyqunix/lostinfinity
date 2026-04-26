package xol.lostinfinity.mob.ai;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import xol.lostinfinity.mob.entity.base.EntityFloatingTameable;
public class EntityFlyingOwnerAttack extends EntityAITarget {
    EntityFloatingTameable tameable;
    EntityLivingBase attacker;
    private int timestamp;
    public EntityFlyingOwnerAttack(EntityFloatingTameable theEntityTameableIn) {
        super(theEntityTameableIn, false);
        this.tameable = theEntityTameableIn;
        func_75248_a(1);
    }
    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase;
        if (!this.tameable.isTamed() || (entitylivingbase = this.tameable.func_70902_q()) == null) {
            return false;
        }
        this.attacker = entitylivingbase.func_110144_aD();
        int i = entitylivingbase.func_142013_aG();
        return i != this.timestamp && func_75296_a(this.attacker, false) && this.tameable.shouldAttackEntity(this.attacker, entitylivingbase);
    }
    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.attacker);
        EntityLivingBase entitylivingbase = this.tameable.func_70902_q();
        if (entitylivingbase != null) {
            this.timestamp = entitylivingbase.func_142013_aG();
        }
        super.func_75249_e();
    }
}
