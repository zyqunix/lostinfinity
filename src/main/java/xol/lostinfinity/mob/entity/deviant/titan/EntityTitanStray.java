package xol.lostinfinity.mob.entity.deviant.titan;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.projectile.entity.EntitySkullShot;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/titan/EntityTitanStray.class */
public class EntityTitanStray extends EntityDeviantTitan {
    public EntityTitanStray(World worldIn) {
        super(worldIn);
        func_70105_a(3.0f, 8.0f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_190033_gv;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_190034_gw;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_190032_gu;
    }

    public void func_70636_d() {
        super.func_70636_d();
        EntityLivingBase target = func_70638_az();
        if (this.field_70173_aa % 7 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 8 + this.field_70170_p.field_73012_v.nextInt(4); i++) {
                    EntitySkullShot shot = new EntitySkullShot(this.field_70170_p, (target.field_70165_t - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d), target.field_70163_u + 15.0d, (target.field_70161_v - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d), 1.0f);
                    shot.setThrower(this);
                    shot.func_70186_c(0.0d, -0.05d, 0.0d, 0.7f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
        }
    }

    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_STRAY;
        }
        return null;
    }
}
