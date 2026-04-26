package xol.lostinfinity.mob.entity.deviant.titan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityTitanVex extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanVex(World worldIn) {
        super(worldIn);
        func_70105_a(2.0f, 3.8f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (func_70638_az() != null && !this.field_70170_p.field_72995_K && func_70638_az().field_70163_u > this.field_70163_u + 2.0d) {
            this.field_70181_x = 0.45d;
            func_70024_g(Math.signum(func_70638_az().field_70165_t - this.field_70165_t) * 0.08d, 0.0d, Math.signum(func_70638_az().field_70161_v - this.field_70161_v) * 0.08d);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_191266_he;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_191267_hf;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_191264_hc;
    }
    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_VEX;
        }
        return null;
    }
}
