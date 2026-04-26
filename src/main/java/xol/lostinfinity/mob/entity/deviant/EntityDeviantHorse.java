package xol.lostinfinity.mob.entity.deviant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.mob.entity.base.EntityDeviantMob;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantHorse extends EntityDeviantMob implements IMaxAttack {
    public EntityDeviantHorse(World worldIn) {
        super(worldIn);
        func_70105_a(2.3f, 3.5f);
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
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % (120 - (getMutation() * 20)) == 0) {
            EntityPlayer nearest = null;
            float neardist = 9999.0f;
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(25.0d))) {
                float checkdist = near_pl.func_70032_d(this);
                if (checkdist < neardist) {
                    nearest = near_pl;
                    neardist = checkdist;
                }
            }
            if (nearest != null) {
                func_184185_a(SoundEvents.field_187936_hj, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                for (EntityMob near_mob : this.field_70170_p.func_72872_a(EntityMob.class, func_174813_aQ().func_186662_g(25.0d))) {
                    if (near_mob.func_70032_d(nearest) > 3.0f) {
                        near_mob.func_70634_a(nearest.field_70165_t, nearest.field_70163_u, nearest.field_70161_v);
                        near_mob.func_70624_b(nearest);
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.MIASMA).setSpread(3.0d, 1.0d, 3.0d).setCount(5).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config1, near_mob.field_70165_t, near_mob.field_70163_u + ((double) (near_mob.field_70131_O / 2.0f)), near_mob.field_70161_v);
                    }
                }
            }
        }
        super.func_70636_d();
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187708_co;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187717_cr;
    }
    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187696_ck;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation deviantDrop() {
        return LootTableRegistry.ENTITIES_DEVIANTHORSE;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityDeviantMob
    protected ResourceLocation superMutatedDrop() {
        return LootTableRegistry.ENTITIES_SUPERMUTANT_HORSE;
    }
}
