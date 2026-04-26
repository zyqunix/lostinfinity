package xol.lostinfinity.mob.entity.labyrinth;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityGloop extends EntityMob implements IMaxAttack, IBasicAI {
    public EntityGloop(World worldIn) {
        super(worldIn);
        func_70105_a(0.7f, 0.85f);
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
    public void func_70636_d() {
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 40 < 20 && func_70638_az() != null && func_70638_az().field_70163_u > this.field_70163_u) {
            func_70634_a(this.field_70165_t, this.field_70163_u + 0.7d, this.field_70161_v);
        }
        super.func_70636_d();
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187886_fs;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187880_fp;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187874_fm;
    }
    public boolean func_70814_o() {
        return true;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
