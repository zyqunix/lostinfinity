package xol.lostinfinity.mob.entity.cthulhu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuTurretBullet;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCthulhuTurret.class */
public class EntityCthulhuTurret extends AbstractCthulhuMinion {
    public EntityCthulhuTurret(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 1.5f);
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    public void func_70636_d() {
        EntityPlayer player;
        super.func_70636_d();
        if (func_110143_aJ() <= 0.0f && didDeathAction()) {
            func_70106_y();
        }
        if (this.field_70170_p.field_72995_K || this.field_70173_aa % 4 != 0 || !this.field_70122_E || (player = findClosestPlayer()) == null) {
            return;
        }
        EntityCthulhuTurretBullet bullet = new EntityCthulhuTurretBullet(this.field_70170_p);
        bullet.setThrower(this);
        bullet.setSecondaryThrower(this.owner);
        bullet.func_70107_b(this.field_70165_t, this.field_70163_u + 2.0d, this.field_70161_v);
        bullet.func_70186_c(player.field_70165_t - bullet.field_70165_t, (player.field_70163_u + 1.0d) - bullet.field_70163_u, player.field_70161_v - bullet.field_70161_v, 2.0f, 0.0f);
        this.field_70170_p.func_72838_d(bullet);
        for (EntityPlayer entityPlayer : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(7.0d))) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.LASER_WEAPON_7, SoundCategory.HOSTILE, 1.0f, 1.0f);
        }
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion
    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        return source != DamageSource.field_76379_h && super.func_70097_a(source, amount);
    }

    private EntityPlayer findClosestPlayer() {
        double dist = 2304.0d;
        EntityPlayer closest = null;
        for (EntityPlayer player : this.field_70170_p.func_73046_m().func_184103_al().func_181057_v()) {
            double d = player.func_70068_e(this);
            if (dist > d) {
                dist = d;
                closest = player;
            }
        }
        return closest;
    }
}
