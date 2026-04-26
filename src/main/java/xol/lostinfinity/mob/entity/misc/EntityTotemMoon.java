package xol.lostinfinity.mob.entity.misc;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.entity.EntityGenericBomb;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityTotemMoon.class */
public class EntityTotemMoon extends EntityLiving {
    private EntityPlayer owner;
    private int timer;

    public EntityTotemMoon(World worldIn) {
        super(worldIn);
        this.owner = null;
        this.timer = 150;
        func_70105_a(1.0f, 1.0f);
        func_184224_h(true);
    }

    public void setOwner(EntityPlayer play) {
        this.owner = play;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if ((this.timer >= 40 && this.timer % 40 == 0) || (this.timer <= 40 && this.timer % 10 == 0)) {
                double x0 = this.field_70165_t;
                double y0 = this.field_70163_u + 0.5d;
                double z0 = this.field_70161_v;
                float f = 0.0f;
                while (true) {
                    float angle = f;
                    if (angle > 6.283185307179586d) {
                        break;
                    }
                    EntityGenericBomb shot = new EntityGenericBomb(this.field_70170_p);
                    shot.func_70107_b(x0, y0, z0);
                    double velocity_x = 0.05999999865889549d * 5.0d * Math.cos(angle);
                    double velocity_z = 0.05999999865889549d * 5.0d * Math.sin(angle);
                    shot.setThrower(this);
                    shot.calculateVelocity(velocity_x, 0.800000011920929d, velocity_z);
                    this.field_70170_p.func_72838_d(shot);
                    f = (float) (((double) angle) + 0.39269908169872414d);
                }
                func_184185_a(SoundInit.GENERIC_WEAPON_7, 1.0f, 1.0f);
            }
            if (this.timer <= 0) {
                func_70106_y();
            }
            this.timer--;
        }
    }
}
