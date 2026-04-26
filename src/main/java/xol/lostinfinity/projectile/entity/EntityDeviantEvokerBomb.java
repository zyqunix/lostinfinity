package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantEvokerBomb extends EntityBaseThrowable {
    private boolean landed;
    private int timer;
    public EntityDeviantEvokerBomb(World par1World) {
        super(par1World);
        this.landed = false;
        this.timer = 0;
    }
    public EntityDeviantEvokerBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.landed = false;
        this.timer = 0;
    }
    public EntityDeviantEvokerBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.landed = false;
        this.timer = 0;
    }
    protected void func_70088_a() {
        super.func_70088_a();
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.BLOCK) {
            this.field_70159_w = 0.0d;
            this.field_70181_x = 0.0d;
            this.field_70179_y = 0.0d;
            this.field_70133_I = true;
            this.landed = true;
            func_189654_d(true);
        }
    }
    public void func_70030_z() {
        super.func_70030_z();
        if (!this.field_70170_p.field_72995_K && this.landed) {
            if (this.timer < 40) {
                this.timer++;
            } else {
                explode();
            }
        }
    }
    private void explode() {
        EntityLivingBase attacker = func_85052_h();
        this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, false);
        for (EntityLivingBase target : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
            if (!attacker.func_110124_au().equals(target.func_110124_au())) {
                IMaxAttack.dealMaxHealth(this, target, 2);
            }
        }
        func_70106_y();
    }
    protected float func_70185_h() {
        return 0.030000001f;
    }
}
