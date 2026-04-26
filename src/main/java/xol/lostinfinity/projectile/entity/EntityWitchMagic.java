package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityWitchMagic.class */
public class EntityWitchMagic extends EntityBaseThrowable {
    public EntityWitchMagic(World par1World) {
        super(par1World);
    }

    public EntityWitchMagic(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityWitchMagic(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 2);
                boolean canStop = false;
                BlockPos telePos = new BlockPos(this.field_70165_t, this.field_70163_u + 2.0d, this.field_70161_v);
                int ypos = 10;
                while (!canStop) {
                    BlockPos test = telePos.func_177982_a((-10) + this.field_70146_Z.nextInt(20), ypos, (-10) + this.field_70146_Z.nextInt(20));
                    if (this.field_70170_p.func_175623_d(test)) {
                        canStop = true;
                        this.field_70170_p.func_184133_a((EntityPlayer) null, telePos.func_177982_a(0, ypos, 0), SoundEvents.field_187534_aX, SoundCategory.MASTER, 2.0f, 1.0f);
                        result.field_72308_g.func_70634_a(test.func_177958_n(), test.func_177956_o(), test.func_177952_p());
                    } else {
                        ypos++;
                    }
                }
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.05f;
    }
}
