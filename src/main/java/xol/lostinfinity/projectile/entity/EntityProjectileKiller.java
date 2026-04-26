package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityProjectileKiller.class */
public class EntityProjectileKiller extends EntityBaseThrowable {
    private int timer;

    public EntityProjectileKiller(World par1World) {
        super(par1World);
        this.timer = 20;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityProjectileKiller(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.timer = 20;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityProjectileKiller(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.timer = 20;
        func_70105_a(0.75f, 0.75f);
    }

    public void shortTimer() {
        this.timer = 5;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < 2; k++) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.DRIP_LAVA, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
            return;
        }
        if (this.timer == 0) {
            for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
                if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball) || (proj instanceof EntityShulkerBullet)) {
                    vaporizeProjectile(this.field_70170_p, proj);
                }
            }
            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0f, false);
            func_70106_y();
        }
        this.timer--;
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    private void vaporizeProjectile(World world, Entity projectile) {
        world.func_184133_a((EntityPlayer) null, new BlockPos(projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v), SoundInit.ITEM_AXIOMAVORUM, SoundCategory.MASTER, 2.0f, 1.0f);
        if (!world.field_72995_K) {
            if (func_85052_h() != null) {
                if (projectile instanceof EntityThrowable) {
                    EntityThrowable thrown_proj = (EntityThrowable) projectile;
                    if (thrown_proj.func_85052_h() != null && !thrown_proj.func_85052_h().func_110124_au().equals(func_85052_h().func_110124_au())) {
                        IMaxAttack.dealMaxHealth(this, thrown_proj.func_85052_h(), 3);
                    }
                } else if (projectile instanceof EntityFireball) {
                    EntityFireball thrown_proj2 = (EntityFireball) projectile;
                    if (thrown_proj2.field_70235_a != null) {
                        IMaxAttack.dealMaxHealth(this, thrown_proj2.field_70235_a, 3);
                    }
                } else if (projectile instanceof EntityArrow) {
                    EntityArrow thrown_proj3 = (EntityArrow) projectile;
                    if (thrown_proj3.field_70250_c != null && (thrown_proj3.field_70250_c instanceof EntityLivingBase)) {
                        IMaxAttack.dealMaxHealth(this, thrown_proj3.field_70250_c, 3);
                    }
                }
                projectile.func_70106_y();
                return;
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            world.func_175688_a(EnumParticleTypes.LAVA, projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v, (world.field_73012_v.nextDouble() - 0.5d) * 2.0d, -world.field_73012_v.nextDouble(), (world.field_73012_v.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
}
