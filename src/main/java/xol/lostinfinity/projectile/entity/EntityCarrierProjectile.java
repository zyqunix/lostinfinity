package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCarrierProjectile.class */
public class EntityCarrierProjectile extends EntityBaseThrowable {
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityCarrierProjectile.class, DataSerializers.field_187191_a);
    private static final DataParameter<Byte> LIFETICKS = EntityDataManager.func_187226_a(EntityCarrierProjectile.class, DataSerializers.field_187191_a);

    public EntityCarrierProjectile(World par1World) {
        super(par1World);
    }

    public EntityCarrierProjectile(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
        this.field_70180_af.func_187214_a(LIFETICKS, (byte) 0);
    }

    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }

    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }

    public byte getRemainingLife() {
        return ((Byte) this.field_70180_af.func_187225_a(LIFETICKS)).byteValue();
    }

    public void setRemainingLife(byte f) {
        this.field_70180_af.func_187227_b(LIFETICKS, Byte.valueOf(f));
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("WepType", getForm());
        tag.func_74774_a("LifeTicks", getRemainingLife());
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("WepType"));
        setRemainingLife(tag.func_74771_c("LifeTicks"));
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            int life = getRemainingLife() - 1;
            if (life == 0) {
                if (getForm() == 0) {
                    EntityFallingStar star = new EntityFallingStar(this.field_70170_p);
                    star.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    star.setStartLoc(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    star.func_70186_c((this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, -0.1d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, 0.4f, 0.0f);
                    star.setThrower(func_85052_h());
                    this.field_70170_p.func_72838_d(star);
                } else if (getForm() == 1) {
                    EntityComet comet = new EntityComet(this.field_70170_p);
                    comet.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    comet.setStartLoc(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    comet.func_70186_c((this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, -0.1d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.10000000149011612d, 0.4f, 0.0f);
                    comet.setThrower(func_85052_h());
                    this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, comet.field_70165_t, comet.field_70163_u, comet.field_70161_v, 2, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
                    this.field_70170_p.func_72838_d(comet);
                }
                func_70106_y();
                return;
            }
            setRemainingLife((byte) life);
            return;
        }
        for (int k = 0; k < 4; k++) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_INSTANT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }

    protected float func_70185_h() {
        return 0.020000001f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        setRemainingLife((byte) 1);
    }
}
