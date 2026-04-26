package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityArcBlast extends EntityBaseThrowable {
    public EntityArcBlast(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityArcBlast(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityArcBlast(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase) && func_85052_h() != null) {
                EntityLivingBase hit = result.field_72308_g;
                if (!hit.equals(func_85052_h())) {
                    if (hit.func_70644_a(PotionInit.SHOCKED)) {
                        if (IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 4, 5.0f).didSuccessfulHit()) {
                            hit.func_184589_d(PotionInit.SHOCKED);
                        }
                    } else if (IMaxAttack.dealMaxHealth((Entity) this, result.field_72308_g, 4, 3.0f).didSuccessfulHit()) {
                        hit.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 100));
                    }
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
