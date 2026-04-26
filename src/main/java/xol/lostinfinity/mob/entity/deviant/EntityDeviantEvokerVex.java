package xol.lostinfinity.mob.entity.deviant;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantEvokerVex extends EntityFloatingDeviant implements IMaxAttack, IEntityOwnable {
    protected static final DataParameter<Boolean> TAMED = EntityDataManager.func_187226_a(EntityDeviantEvokerVex.class, DataSerializers.field_187198_h);
    protected static final DataParameter<Integer> OWNER_ID = EntityDataManager.func_187226_a(EntityDeviantEvokerVex.class, DataSerializers.field_187192_b);
    public EntityDeviantEvokerVex(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.3f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(800.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_191266_he;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_191267_hf;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_191264_hc;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            if (target instanceof EntityDeviantEvoker) {
                func_70624_b(null);
            } else {
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant, xol.lostinfinity.mob.entity.base.EntityDeviantMob, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TAMED, false);
        this.field_70180_af.func_187214_a(OWNER_ID, 0);
    }
    public boolean isTamed() {
        return ((Boolean) this.field_70180_af.func_187225_a(TAMED)).booleanValue();
    }
    public void setTamed(boolean tamed) {
        this.field_70180_af.func_187227_b(TAMED, Boolean.valueOf(tamed));
    }
    public void setTamedBy(EntityLivingBase tamer) {
        setTamed(true);
        setOwnerId(tamer.func_145782_y());
    }
    public void setOwnerId(int id) {
        this.field_70180_af.func_187227_b(OWNER_ID, Integer.valueOf(id));
    }
    public UUID func_184753_b() {
        Entity owner = this.field_70170_p.func_73045_a(((Integer) this.field_70180_af.func_187225_a(OWNER_ID)).intValue());
        if (owner != null) {
            return owner.func_110124_au();
        }
        return null;
    }
    public Entity func_70902_q() {
        return this.field_70170_p.func_73045_a(((Integer) this.field_70180_af.func_187225_a(OWNER_ID)).intValue());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingDeviant
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
