package xol.lostinfinity.mob.entity.deviant.titan;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantZombie;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/titan/EntityTitanZombie.class */
public class EntityTitanZombie extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanZombie(World worldIn) {
        super(worldIn);
        func_70105_a(4.0f, 9.0f);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.43200000000000005d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1600.0d);
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
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            int count = 0;
            for (EntityLiving entityLiving : this.field_70170_p.func_72872_a(EntityLiving.class, getArenaAABB())) {
                count++;
            }
            if (count < 35) {
                EntityDeviantZombie zombie = new EntityDeviantZombie(this.field_70170_p);
                zombie.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                zombie.field_70181_x = 0.3d;
                zombie.setNotSummoner();
                zombie.setMutation(3);
                this.field_70170_p.func_72838_d(zombie);
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187930_hd;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187934_hh;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187899_gZ;
    }

    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_ZOMBIE;
        }
        return null;
    }
}
