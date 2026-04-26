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
public class EntitySightwalker extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private static final DataParameter<Float> INDIV_SCALE = EntityDataManager.func_187226_a(EntitySightwalker.class, DataSerializers.field_187193_c);
    public EntitySightwalker(World worldIn) {
        super(worldIn);
        func_70105_a(0.5f, 2.25f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(INDIV_SCALE, Float.valueOf(0.0f));
    }
    public float getMyScale() {
        return ((Float) this.field_70180_af.func_187225_a(INDIV_SCALE)).floatValue();
    }
    private void setAScale() {
        float myscl = 1.0f + (this.field_70146_Z.nextFloat() * 3.0f);
        this.field_70180_af.func_187227_b(INDIV_SCALE, Float.valueOf(myscl));
        func_70105_a(0.75f * myscl, 2.25f * myscl);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealMaxHealth(this, func_70638_az(), 1).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                return true;
            }
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa <= 2 && getMyScale() == 0.0f) {
                setAScale();
                return;
            }
            return;
        }
        if (this.field_70130_N == 0.5f) {
            float myscl = getMyScale();
            if (myscl != 0.0f) {
                func_70105_a(0.75f * myscl, 2.25f * myscl);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.SIGHTWALKER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.SIGHTWALKER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.SIGHTWALKER_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 4;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_SIGHTWALKER;
    }
}
