package xol.lostinfinity.projectile.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.mob.entity.base.EntityFloatingDeviant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDeviantDeployer.class */
public class EntityDeviantDeployer extends EntityBaseThrowable {
    private List<Class<? extends EntityLiving>> summonList;

    public EntityDeviantDeployer(World par1World) {
        super(par1World);
        this.summonList = new ArrayList();
    }

    public EntityDeviantDeployer(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.summonList = new ArrayList();
    }

    public void giveList(List<Class<? extends EntityLiving>> newList) {
        this.summonList.addAll(newList);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.summonList.size() > 0) {
                func_184185_a(SoundInit.DEVIATION, 1.0f, 1.0f);
                boolean flag = false;
                if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                    flag = true;
                }
                for (Class<? extends EntityLiving> dev : this.summonList) {
                    try {
                        Constructor<?> con = dev.getConstructor(World.class);
                        Object[] obj = {this.field_70170_p};
                        Object devEntity = con.newInstance(obj);
                        ((Entity) devEntity).func_70107_b((this.field_70165_t - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), this.field_70163_u + 0.2d, (this.field_70161_v - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d));
                        if (flag) {
                            if (devEntity instanceof EntityDeviantMob) {
                                ((EntityDeviantMob) devEntity).setMutation(3);
                            } else if (devEntity instanceof EntityFloatingDeviant) {
                                ((EntityFloatingDeviant) devEntity).setMutation(3);
                            }
                        }
                        this.field_70170_p.func_72838_d((Entity) devEntity);
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                        e.printStackTrace();
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
