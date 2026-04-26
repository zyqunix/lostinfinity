package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityWitherBomb.class */
public class EntityWitherBomb extends EntityBaseThrowable {
    public EntityWitherBomb(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityWitherBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g == null || !result.field_72308_g.equals(func_85052_h())) {
                this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5f, false);
                for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d))) {
                    IMaxAttack.dealMaxHealth(this, target, 2);
                }
                func_70106_y();
            }
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175682_a(ParticleInit.WITHER_RINGS, true, this.field_70165_t + getROD(1), this.field_70163_u + getROD(1), this.field_70161_v + getROD(1), 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
