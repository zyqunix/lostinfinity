package xol.lostinfinity.projectile.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityPiercingShot.class */
public class EntityPiercingShot extends EntityBaseThrowable {
    private List<EntityLivingBase> hitCreatures;

    public EntityPiercingShot(World par1World) {
        super(par1World);
        this.hitCreatures = new ArrayList();
    }

    public EntityPiercingShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.hitCreatures = new ArrayList();
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.BLOCK) {
            System.out.println(this.hitCreatures.size());
            for (EntityLivingBase hit : this.hitCreatures) {
                if (hit != null) {
                    IMaxAttack.dealMaxHealth((Entity) this, hit, 10, 6 + (3 * (this.hitCreatures.size() - 1)));
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.02f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(ParticleInit.LASER_FIZZLE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            return;
        }
        for (EntityLivingBase near_creature : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(0.5d))) {
            if (!this.hitCreatures.contains(near_creature)) {
                this.hitCreatures.add(near_creature);
            }
        }
    }
}
