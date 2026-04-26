package xol.lostinfinity.mob.entity.murk;

import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/murk/EntityTorpedon.class */
public class EntityTorpedon extends EntityFloatingBase implements IMaxAttack {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityTorpedon.class, DataSerializers.field_187198_h);

    public EntityTorpedon(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 2.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ATTACKING, false);
    }

    public boolean getAngry() {
        return ((Boolean) this.field_70180_af.func_187225_a(ATTACKING)).booleanValue();
    }

    public void setAngry(boolean f) {
        this.field_70180_af.func_187227_b(ATTACKING, Boolean.valueOf(f));
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 1, (List<String>) Arrays.asList("Darkborn"));
            return true;
        }
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() != null) {
                setAngry(true);
                this.rawFlySpeed = 0.95f;
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
                if (this.field_70173_aa % 40 == 0) {
                    func_184185_a(SoundInit.TORPEDON_ABILITY, 1.5f, 1.0f);
                    return;
                }
                return;
            }
            setAngry(false);
            this.rawFlySpeed = 0.7f;
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.TORPEDON_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.TORPEDON_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.TORPEDON_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 15;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return super.func_70601_bi() && nothingInRadius(45);
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_TORPEDON;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
