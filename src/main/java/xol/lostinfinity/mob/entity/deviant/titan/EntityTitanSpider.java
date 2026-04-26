package xol.lostinfinity.mob.entity.deviant.titan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantTitan;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityTitanSpider extends EntityDeviantTitan implements IMaxAttack {
    public EntityTitanSpider(World worldIn) {
        super(worldIn);
        func_70105_a(7.0f, 4.5f);
    }
    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.43200000000000005d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
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
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % 60 == 0 && func_70638_az() != null && (func_70638_az() instanceof EntityPlayer)) {
            EntityPlayer pl = func_70638_az();
            func_70024_g((pl.field_70165_t - this.field_70165_t) * 0.22d, 1.0d, (pl.field_70161_v - this.field_70161_v) * 0.22d);
            this.field_70133_I = true;
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187819_fL;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187821_fM;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187817_fK;
    }
    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_TITAN_SPIDER;
        }
        return null;
    }
}
