package xol.lostinfinity.mob.entity.misc;
import com.google.common.base.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityEffigyEffect extends Entity implements IMaxAttack {
    protected static final DataParameter<Optional<UUID>> CASTER_ID = EntityDataManager.func_187226_a(EntityEffigyEffect.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Optional<UUID>> TARGET_ID = EntityDataManager.func_187226_a(EntityEffigyEffect.class, DataSerializers.field_187203_m);
    public EntityEffigyEffect(World worldIn) {
        super(worldIn);
        func_184224_h(true);
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        EntityPlayer caster = getCaster();
        EntityPlayer target = getTarget();
        if ((this.field_70173_aa > 2 && target == null) || target.field_70128_L) {
            if (!this.field_70170_p.field_72995_K) {
                func_70106_y();
                return;
            }
            return;
        }
        if (target != null) {
            if (!this.field_70170_p.field_72995_K) {
                func_70634_a(target.field_70165_t, target.field_70163_u, target.field_70161_v);
            }
            if (this.field_70173_aa <= 40) {
                if (!this.field_70170_p.field_72995_K) {
                    if (caster != null && this.field_70173_aa % 10 == 0) {
                        IMaxAttack.dealMaxHealth(caster, target, 20);
                        return;
                    }
                    return;
                }
                double d = 0.0d;
                while (true) {
                    double i = d;
                    if (i <= 4.0d) {
                        this.field_70170_p.func_175688_a(ParticleInit.FLAME_SMALL, target.field_70165_t, target.field_70163_u + 0.4d, target.field_70161_v, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, this.field_70146_Z.nextDouble() * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, new int[0]);
                        d = i + 1.0d;
                    } else {
                        return;
                    }
                }
            } else if (this.field_70173_aa <= 80) {
                if (!this.field_70170_p.field_72995_K) {
                    if (caster != null && this.field_70173_aa % 10 == 0) {
                        IMaxAttack.dealMaxHealth(caster, target, 10);
                        return;
                    }
                    return;
                }
                double d2 = 0.0d;
                while (true) {
                    double i2 = d2;
                    if (i2 <= 4.0d) {
                        this.field_70170_p.func_175688_a(ParticleInit.FLAME_MEDIUM, target.field_70165_t, target.field_70163_u + 0.4d, target.field_70161_v, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, this.field_70146_Z.nextDouble() * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, new int[0]);
                        d2 = i2 + 1.0d;
                    } else {
                        return;
                    }
                }
            } else if (this.field_70173_aa <= 120) {
                if (!this.field_70170_p.field_72995_K) {
                    if (caster != null && this.field_70173_aa % 10 == 0) {
                        IMaxAttack.dealMaxHealth(caster, target, 5);
                        return;
                    }
                    return;
                }
                double d3 = 0.0d;
                while (true) {
                    double i3 = d3;
                    if (i3 <= 4.0d) {
                        this.field_70170_p.func_175688_a(ParticleInit.FLAME_LARGE, target.field_70165_t, target.field_70163_u + 0.4d, target.field_70161_v, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, this.field_70146_Z.nextDouble() * 0.2d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.2d, new int[0]);
                        d3 = i3 + 1.0d;
                    } else {
                        return;
                    }
                }
            } else if (!this.field_70170_p.field_72995_K) {
                target.func_70606_j(0.0f);
                func_145779_a(ItemInit.lifeSynchronizer, 1);
            } else {
                this.field_70170_p.func_175688_a(ParticleInit.EXPLOSION_RED, target.field_70165_t, target.field_70163_u + 0.4d, target.field_70161_v, 0.0d, this.field_70146_Z.nextDouble() * 0.2d, 0.0d, new int[0]);
            }
        }
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(CASTER_ID, Optional.absent());
        this.field_70180_af.func_187214_a(TARGET_ID, Optional.absent());
    }
    protected void func_70037_a(NBTTagCompound compound) {
    }
    protected void func_70014_b(NBTTagCompound compound) {
    }
    public EntityPlayer getCaster() {
        if (((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(CASTER_ID)).get());
        }
        return null;
    }
    public void setCaster(EntityPlayer player) {
        this.field_70180_af.func_187227_b(CASTER_ID, Optional.fromNullable(player.func_110124_au()));
    }
    public EntityPlayer getTarget() {
        if (((Optional) this.field_70180_af.func_187225_a(TARGET_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(TARGET_ID)).get());
        }
        return null;
    }
    public void setTarget(UUID id) {
        this.field_70180_af.func_187227_b(TARGET_ID, Optional.fromNullable(id));
    }
}
