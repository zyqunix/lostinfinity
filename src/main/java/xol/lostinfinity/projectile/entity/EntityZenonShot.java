package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityZenonShot extends EntityBaseThrowable implements IMaxAttack {
    private static final float mult = 0.96f;
    private ArrayList<EntityLivingBase> entitiesHit;
    public EntityZenonShot(World par1World) {
        super(par1World);
        this.entitiesHit = null;
        func_189654_d(true);
    }
    public EntityZenonShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.entitiesHit = null;
        func_189654_d(true);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.entitiesHit == null) {
                this.entitiesHit = new ArrayList<>();
            }
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase) && this.field_70192_c != null) {
                EntityLivingBase target = result.field_72308_g;
                if (target.func_110124_au().equals(this.field_70192_c.func_110124_au())) {
                    func_70106_y();
                } else if (!this.entitiesHit.contains(target)) {
                    IMaxAttack.dealMaxHealth((Entity) this.field_70192_c, target, 3, 2.0f);
                    this.entitiesHit.add(target);
                }
            }
        }
    }
    public void clearHits() {
        if (this.entitiesHit != null) {
            this.entitiesHit.clear();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            this.field_70159_w *= 0.9599999785423279d;
            this.field_70181_x *= 0.9599999785423279d;
            this.field_70179_y *= 0.9599999785423279d;
            this.field_70133_I = true;
            if (this.field_70173_aa > 400) {
                func_70106_y();
            }
        }
    }
}
