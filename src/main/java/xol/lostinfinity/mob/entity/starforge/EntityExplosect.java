package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityExplosect extends EntityMob implements IMaxAttack, IBasicAI {
    public EntityExplosect(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 0.75f);
    }
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
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(700.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() != null) {
                EntityLivingBase entityLivingBaseFunc_70638_az = func_70638_az();
                if (func_70032_d(entityLivingBaseFunc_70638_az) > 2.0f) {
                    func_70024_g(Math.signum(((Entity) entityLivingBaseFunc_70638_az).field_70165_t - this.field_70165_t) * 0.05d, 0.0d, Math.signum(((Entity) entityLivingBaseFunc_70638_az).field_70161_v - this.field_70161_v) * 0.05d);
                    if (((Entity) entityLivingBaseFunc_70638_az).field_70163_u > this.field_70163_u) {
                        this.field_70181_x = 0.1d;
                        return;
                    }
                    return;
                }
                this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 6.0f, false);
                for (EntityLivingBase near_pl : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(3.0d, 3.0d, 3.0d))) {
                    IMaxAttack.dealMaxHealth(this, near_pl, 2);
                }
                func_70106_y();
                return;
            }
            if (this.field_70173_aa % 100 < 50) {
                this.field_70181_x = 0.1d;
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_EXPLOSECT_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_EXPLOSECT_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_EXPLOSECT_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_EXPLOSECT;
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
