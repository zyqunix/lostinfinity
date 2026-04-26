package xol.lostinfinity.mob.entity.labyrinth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/labyrinth/EntityCyclos.class */
public class EntityCyclos extends EntityMultipleLives implements IMaxAttack {
    public EntityCyclos(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.5f);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70173_aa % 120 < 50) {
            if (this.field_70173_aa % 120 == 0) {
                func_184185_a(SoundInit.CYCLOS_AMBIENT, 2.0f, 1.0f);
            }
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(12.0d, 12.0d, 12.0d))) {
                if (!near_pl.func_184812_l_()) {
                    near_pl.field_70181_x = 0.85d;
                    near_pl.field_70133_I = true;
                }
            }
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.CYCLOS_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CYCLOS_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 5;
    }
}
