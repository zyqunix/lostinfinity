package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityStunAttack.class */
public class EntityStunAttack extends EntityBaseThrowable {
    private float grav;
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityStunAttack.class, DataSerializers.field_187191_a);

    public EntityStunAttack(World par1World) {
        super(par1World);
        this.grav = 0.030000001f;
    }

    public EntityStunAttack(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.grav = 0.030000001f;
    }

    public EntityStunAttack(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.grav = 0.030000001f;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
    }

    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }

    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }

    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("AttackStyle", getForm());
    }

    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("AttackStyle"));
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 4);
                result.field_72308_g.field_70159_w = 0.0d;
                result.field_72308_g.field_70181_x = 0.0d;
                result.field_72308_g.field_70179_y = 0.0d;
                result.field_72308_g.func_145747_a(new TextComponentString(TextFmt.Dark_Purple + "Your muscles briefly freeze up."));
            }
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            if (getForm() == 0) {
                for (int i = 0; i < 3; i++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_DROP, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                }
                return;
            }
            this.field_70170_p.func_175688_a(EnumParticleTypes.LAVA, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
