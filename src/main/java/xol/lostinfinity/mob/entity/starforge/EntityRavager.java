package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityRavager.class */
public class EntityRavager extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityRavager.class, DataSerializers.field_187198_h);

    public EntityRavager(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 3.0f);
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

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
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

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            setAngry(func_70638_az() != null);
            if (func_70638_az() != null && this.field_70173_aa % 20 == 0) {
                int level = 0;
                if (func_70638_az().func_70644_a(PotionInit.SHOCKED)) {
                    level = func_70638_az().func_70660_b(PotionInit.SHOCKED).func_76458_c() + 1;
                    func_70638_az().func_184589_d(PotionInit.SHOCKED);
                }
                if (IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 10, 1 + (2 * level)).didSuccessfulHit()) {
                    func_70638_az().func_70690_d(new PotionEffect(PotionInit.SHOCKED, 40, level));
                }
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.RAVAGER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.RAVAGER_HURT;
    }

    protected SoundEvent func_184639_G() {
        if (getAngry()) {
            return SoundInit.RAVAGER_AMBIENT_ALTERNATE;
        }
        return SoundInit.RAVAGER_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_RAVAGER;
    }
}
