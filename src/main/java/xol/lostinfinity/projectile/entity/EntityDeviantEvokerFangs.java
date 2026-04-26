package xol.lostinfinity.projectile.entity;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantEvokerFangs extends Entity implements IMaxAttack {
    private int warmupDelayTicks;
    private boolean sentSpikeEvent;
    private int lifeTicks;
    private boolean clientSideAttackStarted;
    private EntityLivingBase caster;
    private UUID casterUuid;
    public EntityDeviantEvokerFangs(World worldIn) {
        super(worldIn);
        this.lifeTicks = 22;
        func_70105_a(0.5f, 0.8f);
    }
    public EntityDeviantEvokerFangs(World worldIn, double x, double y, double z, float yaw, int warmupTicks, EntityLivingBase casterIn) {
        this(worldIn);
        this.warmupDelayTicks = warmupTicks;
        setCaster(casterIn);
        this.field_70177_z = yaw * 57.295776f;
        func_70107_b(x, y, z);
    }
    protected void func_70088_a() {
    }
    public void setCaster(@Nullable EntityLivingBase caster) {
        this.caster = caster;
        this.casterUuid = caster == null ? null : caster.func_110124_au();
    }
    @Nullable
    public EntityLivingBase getCaster() {
        if (this.caster == null && this.casterUuid != null && (this.field_70170_p instanceof WorldServer)) {
            EntityLivingBase entityLivingBaseFunc_175733_a = this.field_70170_p.func_175733_a(this.casterUuid);
            if (entityLivingBaseFunc_175733_a instanceof EntityLivingBase) {
                this.caster = entityLivingBaseFunc_175733_a;
            }
        }
        return this.caster;
    }
    protected void func_70037_a(NBTTagCompound compound) {
        this.warmupDelayTicks = compound.func_74762_e("Warmup");
        this.casterUuid = compound.func_186857_a("OwnerUUID");
    }
    protected void func_70014_b(NBTTagCompound compound) {
        compound.func_74768_a("Warmup", this.warmupDelayTicks);
        if (this.casterUuid != null) {
            compound.func_186854_a("OwnerUUID", this.casterUuid);
        }
    }
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            if (this.clientSideAttackStarted) {
                this.lifeTicks--;
                if (this.lifeTicks == 14) {
                    for (int i = 0; i < 12; i++) {
                        double d0 = this.field_70165_t + (((this.field_70146_Z.nextDouble() * 2.0d) - 1.0d) * ((double) this.field_70130_N) * 0.5d);
                        double d1 = this.field_70163_u + 0.05d + (this.field_70146_Z.nextDouble() * 1.0d);
                        double d2 = this.field_70161_v + (((this.field_70146_Z.nextDouble() * 2.0d) - 1.0d) * ((double) this.field_70130_N) * 0.5d);
                        double d3 = ((this.field_70146_Z.nextDouble() * 2.0d) - 1.0d) * 0.3d;
                        double d4 = 0.3d + (this.field_70146_Z.nextDouble() * 0.3d);
                        double d5 = ((this.field_70146_Z.nextDouble() * 2.0d) - 1.0d) * 0.3d;
                        this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, d0, d1 + 1.0d, d2, d3, d4, d5, new int[0]);
                    }
                    return;
                }
                return;
            }
            return;
        }
        int i2 = this.warmupDelayTicks - 1;
        this.warmupDelayTicks = i2;
        if (i2 < 0) {
            if (this.warmupDelayTicks == -8) {
                for (EntityLivingBase entitylivingbase : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(0.2d, 0.0d, 0.2d))) {
                    damage(entitylivingbase);
                }
            }
            if (!this.sentSpikeEvent) {
                this.field_70170_p.func_72960_a(this, (byte) 4);
                this.sentSpikeEvent = true;
            }
            int i3 = this.lifeTicks - 1;
            this.lifeTicks = i3;
            if (i3 < 0) {
                func_70106_y();
            }
        }
    }
    private void damage(EntityLivingBase target) {
        EntityLivingBase entitylivingbase = getCaster();
        if (target.func_70089_S() && !target.func_190530_aW() && target != entitylivingbase) {
            if (entitylivingbase == null) {
                IMaxAttack.dealMaxHealth(this, target, 1);
            } else {
                if (entitylivingbase.func_184191_r(target)) {
                    return;
                }
                IMaxAttack.dealMaxHealth(entitylivingbase, target, 1);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte id) {
        super.func_70103_a(id);
        if (id == 4) {
            this.clientSideAttackStarted = true;
            if (!func_174814_R()) {
                this.field_70170_p.func_184134_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_191242_bl, func_184176_by(), 1.0f, (this.field_70146_Z.nextFloat() * 0.2f) + 0.85f, false);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public float getAnimationProgress(float partialTicks) {
        if (!this.clientSideAttackStarted) {
            return 0.0f;
        }
        int i = this.lifeTicks - 2;
        if (i <= 0) {
            return 1.0f;
        }
        return 1.0f - ((i - partialTicks) / 20.0f);
    }
}
