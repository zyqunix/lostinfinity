package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGlangler.class */
public class EntityGlangler extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    public EntityGlangler(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 2.2f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 3, 2.0f);
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.37d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GLANGLER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GLANGLER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.GLANGLER_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GLANGLER;
    }
}
