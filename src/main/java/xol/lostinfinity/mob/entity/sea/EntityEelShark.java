package xol.lostinfinity.mob.entity.sea;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityEelShark extends EntitySeaCreature {
    public EntityEelShark(World worldIn) {
        super(worldIn);
        func_70105_a(3.0f, 3.0f);
        this.rawFlySpeed = 0.95f;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 1, (List<String>) Arrays.asList("Aquatic"));
            return true;
        }
        return false;
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.EELSHARK_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.EELSHARK_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.EELSHARK_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            }
            if (this.field_70173_aa % 100 == 20) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.ELECTRIC_SHOCK, SoundCategory.HOSTILE, 1.75f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(8.0d, 8.0d, 8.0d))) {
                    near_pl.func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 300));
                }
                for (int i = 0; i < 3; i++) {
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.setCount(3);
                    config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_BLUE).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                    config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_YELLOW).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
                }
            }
        }
    }
    private double getROD(int multi) {
        return ((-0.5d) + this.field_70146_Z.nextDouble()) * ((double) multi);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 25;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_EELSHARK;
    }
}
