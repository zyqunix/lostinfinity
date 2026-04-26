package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityClyster.class */
public class EntityClyster extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityClyster.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> ENLARGED = EntityDataManager.func_187226_a(EntityClyster.class, DataSerializers.field_187198_h);
    private float ultrascale;

    public EntityClyster(World worldIn) {
        super(worldIn);
        this.ultrascale = 1.0f;
        func_70105_a(2.2f, 3.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ATTACKING, false);
        this.field_70180_af.func_187214_a(ENLARGED, false);
    }

    public boolean getAngry() {
        return ((Boolean) this.field_70180_af.func_187225_a(ATTACKING)).booleanValue();
    }

    public void setAngry(boolean f) {
        this.field_70180_af.func_187227_b(ATTACKING, Boolean.valueOf(f));
    }

    public void setEnlarged(boolean f) {
        this.field_70180_af.func_187227_b(ENLARGED, Boolean.valueOf(f));
    }

    public boolean isEnlarged() {
        return ((Boolean) this.field_70180_af.func_187225_a(ENLARGED)).booleanValue();
    }

    public float getUltraScl() {
        return this.ultrascale;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("EnlargedState", isEnlarged());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setEnlarged(tag.func_74767_n("EnlargedState"));
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
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (isEnlarged() && this.ultrascale < 2.0f) {
            this.ultrascale += 0.02f;
            func_70105_a(2.2f * this.ultrascale, 3.5f * this.ultrascale);
        }
        if (!this.field_70170_p.field_72995_K) {
            setAngry(func_70638_az() != null);
        }
        if (func_70638_az() != null) {
            EntityLivingBase entityLivingBaseFunc_70638_az = func_70638_az();
            double moveSpeed = isEnlarged() ? 0.035d : 0.02d;
            entityLivingBaseFunc_70638_az.func_70024_g(Math.signum(this.field_70165_t - ((Entity) entityLivingBaseFunc_70638_az).field_70165_t) * moveSpeed, 0.0d, Math.signum(this.field_70161_v - ((Entity) entityLivingBaseFunc_70638_az).field_70161_v) * moveSpeed);
            ((Entity) entityLivingBaseFunc_70638_az).field_70133_I = true;
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_CLYSTER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_CLYSTER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_CLYSTER_AMBIENT;
    }

    protected ResourceLocation func_184647_J() {
        return isEnlarged() ? LootTableRegistry.ENTITIES_STARFORGE_BLUE_CLYSTER : LootTableRegistry.ENTITIES_STARFORGE_CLYSTER;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return isEnlarged() ? 8 : 2;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
