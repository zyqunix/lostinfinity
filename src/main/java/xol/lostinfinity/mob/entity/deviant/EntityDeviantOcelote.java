package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantOcelote extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantOcelote(World worldIn) {
        super(worldIn);
        func_70105_a(1.6f, 1.5f);
    }
    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.43200000000000005d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealTrueDamage(this, func_70638_az(), func_70638_az().func_110138_aP() * (0.5f + (0.25f * getMutation())));
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % (40 - (5 * getMutation())) == 0 && func_70638_az() != null && (func_70638_az() instanceof EntityPlayer)) {
            EntityPlayer pl = func_70638_az();
            func_70024_g((pl.field_70165_t - this.field_70165_t) * 0.145d, 1.0d, (pl.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187639_P;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187642_Q;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187636_O;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTOCELOTE;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_OCELOTE;
    }
}
