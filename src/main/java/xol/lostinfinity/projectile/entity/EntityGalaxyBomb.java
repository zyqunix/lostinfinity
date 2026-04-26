package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityGalaxyBomb extends EntityBaseThrowable {
    private float grav;
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityGalaxyBomb.class, DataSerializers.field_187191_a);
    public EntityGalaxyBomb(World par1World) {
        super(par1World);
        this.grav = 0.030000001f;
    }
    public EntityGalaxyBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.grav = 0.030000001f;
    }
    public EntityGalaxyBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.grav = 0.030000001f;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
    }
    public void setGravity(Float g) {
        this.grav = g.floatValue();
    }
    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }
    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("WepType", getForm());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("WepType"));
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                EntityLivingBase attacker = func_85052_h();
                this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
                for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                    switch (getForm()) {
                        case 0:
                            if (attacker.func_110143_aJ() >= 4.0f * (attacker.func_110138_aP() / 5.0f)) {
                                IMaxAttack.dealMaxHealth((Entity) this, target, 20, 3.0f);
                            }
                            break;
                        case 1:
                            if (target.func_110143_aJ() <= target.func_110138_aP() / 2.0f) {
                                IMaxAttack.dealMaxHealth(this, target, 10);
                            }
                            break;
                        case 2:
                            if (target.func_110143_aJ() >= target.func_110138_aP() / 2.0f) {
                                IMaxAttack.dealMaxHealth(this, target, 10);
                            }
                            break;
                        case 3:
                            if (attacker.func_110143_aJ() <= attacker.func_110138_aP() / 5.0f) {
                                IMaxAttack.dealMaxHealth(this, target, 5);
                            }
                            break;
                    }
                }
            }
            func_70106_y();
        }
        circle();
    }
    protected float func_70185_h() {
        return this.grav;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            EnumParticleTypes particle = EnumParticleTypes.SMOKE_LARGE;
            switch (getForm()) {
                case 0:
                    particle = EnumParticleTypes.WATER_WAKE;
                    break;
                case 1:
                    particle = EnumParticleTypes.SPELL_WITCH;
                    break;
                case 2:
                    particle = EnumParticleTypes.TOTEM;
                    break;
                case 3:
                    particle = EnumParticleTypes.DRIP_LAVA;
                    break;
            }
            for (int par = 0; par < 5; par++) {
                this.field_70170_p.func_175688_a(particle, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
    }
    private void circle() {
        if (this.field_70170_p.field_72995_K && this.field_70173_aa > 5) {
            double d = 0.0d;
            while (true) {
                double i = d;
                if (i <= 6.283185307179586d) {
                    double d2 = 0.0d;
                    while (true) {
                        double a = d2;
                        if (a <= 360.0d) {
                            double x = 2.0d * Math.cos(a) * Math.sin(i);
                            double y = 2.0d * Math.cos(i);
                            double z = 2.0d * Math.sin(a) * Math.sin(i);
                            double newX = func_174791_d().field_72450_a + x;
                            double newY = func_174791_d().field_72448_b - y;
                            double newZ = func_174791_d().field_72449_c + z;
                            Vec3d newVec = new Vec3d(newX, newY, newZ).func_178788_d(func_174791_d()).func_186678_a(0.5d);
                            this.field_70170_p.func_190523_a(EnumParticleTypes.FLAME.func_179348_c(), newX, newY, newZ, newVec.field_72450_a, newVec.field_72448_b, newVec.field_72449_c, new int[0]);
                            d2 = a + 0.3141592653589793d;
                        }
                    }
                    d = i + 0.3141592653589793d;
                } else {
                    return;
                }
            }
        }
    }
}
