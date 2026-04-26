package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityUltraCrystalGel;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityReflectal extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> DEFENDING = EntityDataManager.func_187226_a(EntityReflectal.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> ENLARGED = EntityDataManager.func_187226_a(EntityReflectal.class, DataSerializers.field_187198_h);
    private int target_tick;
    private float ultrascale;
    public EntityReflectal(World worldIn) {
        super(worldIn);
        this.target_tick = -1;
        this.ultrascale = 1.0f;
        func_70105_a(1.1f, 1.2f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(DEFENDING, false);
        this.field_70180_af.func_187214_a(ENLARGED, false);
    }
    public boolean getDefending() {
        return ((Boolean) this.field_70180_af.func_187225_a(DEFENDING)).booleanValue();
    }
    public void setDefending(boolean f) {
        this.field_70180_af.func_187227_b(DEFENDING, Boolean.valueOf(f));
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
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (isEnlarged() && this.ultrascale < 3.0f) {
            this.ultrascale += 0.02f;
            func_70105_a(1.1f * this.ultrascale, 1.2f * this.ultrascale);
        }
        boolean flag = false;
        for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(7.0d, 5.0d, 7.0d))) {
            if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball)) {
                if (!this.field_70170_p.field_72995_K && !(proj instanceof EntityUltraCrystalGel)) {
                    proj.func_70106_y();
                    flag = true;
                } else {
                    for (int i = 0; i < 3; i++) {
                        this.field_70170_p.func_175688_a(EnumParticleTypes.DRAGON_BREATH, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, this.field_70170_p.field_73012_v.nextDouble() - 0.5d, -this.field_70170_p.field_73012_v.nextDouble(), this.field_70170_p.field_73012_v.nextDouble() - 0.5d, new int[0]);
                    }
                }
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            if (flag) {
                func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 1.0f);
                setDefending(true);
                this.target_tick = this.field_70173_aa + 25;
            } else if (this.field_70173_aa == this.target_tick) {
                setDefending(false);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_REFLECTAL_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_REFLECTAL_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_REFLECTAL_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return isEnlarged() ? LootTableRegistry.ENTITIES_STARFORGE_BLUE_REFLECTAL : LootTableRegistry.ENTITIES_STARFORGE_REFLECTAL;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return isEnlarged() ? 6 : 3;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int size = isEnlarged() ? 15 : 5;
        for (EntityLivingBase near_pl : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(size, size, size))) {
            IMaxAttack.dealMaxHealth((Entity) this, near_pl, 3, 2.0f);
        }
    }
    protected boolean func_70692_ba() {
        return false;
    }
}
