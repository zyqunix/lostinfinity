package xol.lostinfinity.mob.entity.deviant.prime;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityLivoraxBullet;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/deviant/prime/EntityLivorax.class */
public class EntityLivorax extends EntityFloatingBase implements IMaxAttack {
    public EntityLivorax(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 2.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 1, 2.0f);
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.LIVORAX_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LIVORAX_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.LIVORAX_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    @Nullable
    protected EntityAIFloatAttack createShootAI() {
        return new EntityAIFloatAttack(this, (world1, parent, x, y, z, fireballStrength) -> {
            return new EntityLivoraxBullet(this.field_70170_p, parent, func_70638_az());
        }, SoundInit.LIVORAX_ABILITY, 5);
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_LIVORAX;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 80;
    }
}
