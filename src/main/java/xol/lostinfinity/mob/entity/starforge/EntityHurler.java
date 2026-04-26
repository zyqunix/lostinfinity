package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import xol.lostinfinity.projectile.entity.EntityExplosiveGoo;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityHurler extends EntityMob implements IMaxAttack, IBasicAI {
    private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.func_187226_a(EntityHurler.class, DataSerializers.field_187192_b);
    public EntityHurler(World worldIn) {
        super(worldIn);
        func_70105_a(1.5f, 3.0f);
    }
    public boolean func_180427_aV() {
        return true;
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ATTACK_TIME, -1);
    }
    public int getAttackTime() {
        return ((Integer) this.field_70180_af.func_187225_a(ATTACK_TIME)).intValue();
    }
    public void setAttackTime(int f) {
        this.field_70180_af.func_187227_b(ATTACK_TIME, Integer.valueOf(f));
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
            return true;
        }
        return false;
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        EntityLivingBase entityLivingBaseFunc_70638_az = func_70638_az();
        if (entityLivingBaseFunc_70638_az != null && !this.field_70170_p.field_72995_K && this.field_70173_aa % 60 == 0) {
            double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
            double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
            double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
            double d2 = ((Entity) entityLivingBaseFunc_70638_az).field_70165_t - makeX;
            double d3 = (entityLivingBaseFunc_70638_az.func_174813_aQ().field_72338_b + ((double) (((Entity) entityLivingBaseFunc_70638_az).field_70131_O / 4.0f))) - makeY;
            double d4 = ((Entity) entityLivingBaseFunc_70638_az).field_70161_v - makeZ;
            EntityExplosiveGoo shot = new EntityExplosiveGoo(this.field_70170_p, this);
            shot.func_70186_c(d2, d3, d4, 2.5f, 0.0f);
            shot.setDenomAndSize(6, 4);
            this.field_70170_p.func_72838_d(shot);
            func_184185_a(SoundInit.STARFORGE_HURLER_AMBIENT, 1.0f, 1.0f);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_HURLER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_HURLER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_HURLER;
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
}
