package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityGalaxyKnife extends EntityBaseThrowable {
    private float grav;
    private boolean doubleDamage;
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityGalaxyKnife.class, DataSerializers.field_187191_a);
    public EntityGalaxyKnife(World par1World) {
        super(par1World);
        this.grav = 0.030000001f;
        this.doubleDamage = false;
    }
    public EntityGalaxyKnife(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.grav = 0.030000001f;
        this.doubleDamage = false;
    }
    public EntityGalaxyKnife(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.grav = 0.030000001f;
        this.doubleDamage = false;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
    }
    public void setGravity(Float g) {
        this.grav = g.floatValue();
    }
    public void setDouble() {
        this.doubleDamage = true;
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
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityLivingBase attacker = func_85052_h();
                EntityLivingBase target = result.field_72308_g;
                int multi = this.doubleDamage ? 2 : 1;
                switch (getForm()) {
                    case 0:
                        if (attacker.func_110143_aJ() >= 4.0f * (attacker.func_110138_aP() / 5.0f)) {
                            IMaxAttack.dealMaxHealth((Entity) this, target, 5, multi);
                        }
                        break;
                    case 1:
                        if (target.func_110143_aJ() <= target.func_110138_aP() / 2.0f) {
                            IMaxAttack.dealMaxHealth((Entity) this, target, 20, 3 * multi);
                        }
                        break;
                    case 2:
                        if (target.func_110143_aJ() >= target.func_110138_aP() / 2.0f) {
                            IMaxAttack.dealMaxHealth((Entity) this, target, 20, 3 * multi);
                        }
                        break;
                    case 3:
                        if (attacker.func_110143_aJ() <= attacker.func_110138_aP() / 5.0f) {
                            IMaxAttack.dealMaxHealth((Entity) this, target, 4, multi);
                        }
                        break;
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return this.grav;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            switch (getForm()) {
                case 0:
                    this.field_70170_p.func_175688_a(ParticleInit.GALAXY_BLUE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 1:
                    this.field_70170_p.func_175688_a(ParticleInit.GALAXY_PURPLE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 2:
                    this.field_70170_p.func_175688_a(ParticleInit.GALAXY_GREEN, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
                case 3:
                    this.field_70170_p.func_175688_a(ParticleInit.GALAXY_YELLOW, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), 0.0d, 0.0d, 0.0d, new int[0]);
                    break;
            }
        }
    }
}
