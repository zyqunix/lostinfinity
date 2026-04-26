package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
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
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityCrystalShard;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntitySpyker extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private static final DataParameter<Boolean> ENLARGED = EntityDataManager.func_187226_a(EntitySpyker.class, DataSerializers.field_187198_h);
    private float ultrascale;
    public EntitySpyker(World worldIn) {
        super(worldIn);
        this.ultrascale = 1.0f;
        func_70105_a(1.0f, 1.5f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ENLARGED, false);
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
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (isEnlarged() && this.ultrascale < 3.0f) {
            this.ultrascale += 0.02f;
            func_70105_a(1.0f * this.ultrascale, 1.5f * this.ultrascale);
        }
    }
    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if (!this.field_70170_p.field_72995_K) {
            double x0 = this.field_70165_t;
            double y0 = (func_174813_aQ().field_72338_b + func_174813_aQ().field_72337_e) / 2.0d;
            double z0 = this.field_70161_v;
            int repeats = isEnlarged() ? 16 : 8;
            float f = 0.0f;
            while (true) {
                float angle = f;
                if (angle <= 6.283185307179586d) {
                    EntityCrystalShard shot = new EntityCrystalShard(this.field_70170_p, this);
                    shot.func_70107_b(x0, y0, z0);
                    double velocity_x = 5.0d * Math.cos(angle);
                    double velocity_z = 5.0d * Math.sin(angle);
                    shot.setThrower(this);
                    shot.calculateVelocity(velocity_x, 0.5d, velocity_z);
                    shot.func_184538_a(this, shot.field_70125_A, shot.field_70177_z, 0.0f, 0.5f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                    f = (float) (((double) angle) + (3.141592653589793d / ((double) repeats)));
                } else {
                    return;
                }
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_SPYKER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_SPYKER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_SPYKER_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return isEnlarged() ? LootTableRegistry.ENTITIES_STARFORGE_BLUE_SPYKER : LootTableRegistry.ENTITIES_STARFORGE_SPYKER;
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
        return isEnlarged() ? 5 : 1;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
