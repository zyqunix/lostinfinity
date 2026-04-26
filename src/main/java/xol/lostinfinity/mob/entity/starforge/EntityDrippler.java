package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityFirePellet;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityDrippler.class */
public class EntityDrippler extends EntityFloatingBase implements IMaxAttack {
    public EntityDrippler(World worldIn) {
        super(worldIn);
        func_70105_a(1.75f, 1.75f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.DRIPPLER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.DRIPPLER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.DRIPPLER_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 40 == 0) {
            EntityFirePellet shot = new EntityFirePellet(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            shot.setThrower(this);
            shot.func_70186_c(0.0d, -0.5d, 0.0d, 0.5f, 0.0f);
            this.field_70170_p.func_72838_d(shot);
            func_184185_a(SoundInit.DRIPPLER_FIRE, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
        }
    }

    public int func_70641_bl() {
        return 1;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_DRIPPLER;
    }
}
