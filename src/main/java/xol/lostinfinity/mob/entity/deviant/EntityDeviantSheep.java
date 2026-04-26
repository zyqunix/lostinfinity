package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantSheep extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantSheep(World worldIn) {
        super(worldIn);
        func_70105_a(2.3f, 2.5f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1200.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 4 - getMutation());
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % (80 - (getMutation() * 8)) == 0) {
            func_70690_d(new PotionEffect(PotionInit.FEARED, 50));
            func_184185_a(SoundEvents.field_187761_eI, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(7 + (getMutation() * 5)))) {
                near_pl.func_70690_d(new PotionEffect(PotionInit.TERRIFIED, 50));
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.GENERIC_DOT_BLACK).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, near_pl.field_70165_t, near_pl.field_70163_u + 1.0d, near_pl.field_70161_v);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187759_eH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187761_eI;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187757_eG;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTSHEEP;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_SHEEP;
    }
}
