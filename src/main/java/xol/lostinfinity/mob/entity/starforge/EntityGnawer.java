package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
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
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityGnawer extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityGnawer.class, DataSerializers.field_187198_h);
    public EntityGnawer(World worldIn) {
        super(worldIn);
        func_70105_a(1.25f, 1.0f);
    }
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
    protected void func_184651_r() {
        initBasicTasks(this);
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
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            setAngry(func_70638_az() != null);
        }
        if (func_70638_az() != null && this.field_70173_aa % 10 == 0) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_GNAWER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_GNAWER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_GNAWER_AMBIENT;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_GNAWER;
    }
}
