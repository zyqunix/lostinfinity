package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityMinimite.class */
public class EntityMinimite extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private boolean growing;
    private float creatureScale;
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityMinimite.class, DataSerializers.field_187198_h);

    public EntityMinimite(World worldIn) {
        super(worldIn);
        this.growing = false;
        this.creatureScale = 0.75f;
        func_70105_a(1.25f, 1.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
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

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    public boolean isGrowing() {
        return this.growing;
    }

    public float myScale() {
        return this.creatureScale;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 8;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            boolean shouldBeAngry = func_70638_az() != null;
            setAngry(shouldBeAngry);
            if (shouldBeAngry) {
                if (this.creatureScale < 3.0f) {
                    this.creatureScale += 0.125f;
                    func_70105_a(1.6f * this.creatureScale, 1.3f * this.creatureScale);
                    return;
                }
                return;
            }
            if (this.creatureScale > 0.75f) {
                this.creatureScale -= 0.75f;
                func_70105_a(1.6f * this.creatureScale, 1.3f * this.creatureScale);
                return;
            }
            return;
        }
        boolean angry = getAngry();
        if (!angry) {
            this.growing = false;
            if (this.creatureScale > 0.75f) {
                this.creatureScale -= 0.125f;
                func_70105_a(1.6f * this.creatureScale, 1.3f * this.creatureScale);
                return;
            }
            return;
        }
        if (this.creatureScale < 3.0f) {
            this.creatureScale += 0.125f;
            func_70105_a(1.6f * this.creatureScale, 1.3f * this.creatureScale);
            this.growing = true;
            return;
        }
        this.growing = false;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.MINIMITE_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.MINIMITE_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.MINIMITE_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_MINIMITE;
    }
}
