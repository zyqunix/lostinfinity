package xol.lostinfinity.mob.entity.base;
import com.google.common.base.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
public class EntityFloatingTameable extends EntityFloatingBase implements IEntityOwnable {
    protected static final DataParameter<Boolean> TAMED = EntityDataManager.func_187226_a(EntityFloatingTameable.class, DataSerializers.field_187198_h);
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.func_187226_a(EntityFloatingTameable.class, DataSerializers.field_187203_m);
    public EntityFloatingTameable(World worldIn) {
        super(worldIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TAMED, false);
        this.field_70180_af.func_187214_a(OWNER_UNIQUE_ID, Optional.absent());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        super.func_184651_r();
        this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, false));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }
    public boolean isTamed() {
        return ((Boolean) this.field_70180_af.func_187225_a(TAMED)).booleanValue();
    }
    public void setTamed(boolean tamed) {
        this.field_70180_af.func_187227_b(TAMED, Boolean.valueOf(tamed));
    }
    public void setTamedBy(EntityPlayer player) {
        setTamed(true);
        setOwnerId(player.func_110124_au());
    }
    public void setOwnerId(@Nullable UUID uuid) {
        this.field_70180_af.func_187227_b(OWNER_UNIQUE_ID, Optional.fromNullable(uuid));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        if (func_184753_b() == null) {
            compound.func_74778_a("OwnerUUID", "");
        } else {
            compound.func_74778_a("OwnerUUID", func_184753_b().toString());
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound compound) {
        String s;
        super.func_70037_a(compound);
        if (compound.func_150297_b("OwnerUUID", 8)) {
            s = compound.func_74779_i("OwnerUUID");
        } else {
            String s1 = compound.func_74779_i("Owner");
            s = PreYggdrasilConverter.func_187473_a(func_184102_h(), s1);
        }
        if (!s.isEmpty()) {
            try {
                setOwnerId(UUID.fromString(s));
                setTamed(true);
            } catch (Throwable th) {
                setTamed(false);
            }
        }
    }
    @Nullable
    public EntityLivingBase func_70902_q() {
        try {
            UUID uuid = func_184753_b();
            if (uuid == null) {
                return null;
            }
            return this.field_70170_p.func_152378_a(uuid);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    @Nullable
    public UUID func_184753_b() {
        return (UUID) ((Optional) this.field_70180_af.func_187225_a(OWNER_UNIQUE_ID)).orNull();
    }
    public boolean isOwner(EntityLivingBase entityIn) {
        return entityIn == func_70902_q();
    }
    public Team func_96124_cp() {
        EntityLivingBase entitylivingbase;
        if (isTamed() && (entitylivingbase = func_70902_q()) != null) {
            return entitylivingbase.func_96124_cp();
        }
        return super.func_96124_cp();
    }
    public boolean func_184191_r(Entity entityIn) {
        if (isTamed()) {
            EntityLivingBase entitylivingbase = func_70902_q();
            if (entityIn == entitylivingbase) {
                return true;
            }
            if (entitylivingbase != null) {
                return entitylivingbase.func_184191_r(entityIn);
            }
        }
        return super.func_184191_r(entityIn);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if ((target instanceof EntityPlayer) && (owner instanceof EntityPlayer) && !((EntityPlayer) owner).func_96122_a((EntityPlayer) target)) {
            return false;
        }
        return true;
    }
}
