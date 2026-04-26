package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityClinger extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityClinger.class, DataSerializers.field_187198_h);
    public EntityClinger(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 1.5f);
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
    public boolean func_70652_k(Entity entity) {
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() != null) {
                setAngry(true);
                if (func_70032_d(func_70638_az()) < 4.0f) {
                    func_189654_d(true);
                    AxisAlignedBB box = func_70638_az().func_174813_aQ();
                    double goalY = (box.field_72337_e + box.field_72338_b) / 2.0d;
                    if (this.field_70163_u < goalY) {
                        func_70634_a(this.field_70165_t, this.field_70163_u + 0.1d, this.field_70161_v);
                    }
                    if (this.field_70173_aa % 5 == 0) {
                        IMaxAttack.dealMaxHealth(this, func_70638_az(), 8);
                        return;
                    }
                    return;
                }
                func_189654_d(false);
                return;
            }
            func_189654_d(false);
            this.field_70159_w = 0.0d;
            this.field_70181_x = 0.0d;
            this.field_70133_I = true;
            setAngry(false);
        }
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(800.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.45d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.CLINGER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CLINGER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.CLINGER_AMBIENT;
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
        return 5;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_CLINGER;
    }
}
