package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityFlashfly.class */
public class EntityFlashfly extends EntityFloatingBase implements IMaxAttack {
    public EntityFlashfly(World worldIn) {
        super(worldIn);
        func_70105_a(1.25f, 1.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.NIGHTSHYRE_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.NIGHTSHYRE_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.NIGHTSHYRE_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d))) {
            if (near_pl.func_184614_ca().func_77973_b() == ItemInit.beaconOfLight) {
                func_70605_aq().func_75642_a(near_pl.field_70165_t, near_pl.field_70163_u, near_pl.field_70161_v, 1.0d);
                return;
            }
        }
    }

    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            Iterable<BlockPos> nearblocks = BlockPos.func_177980_a(func_180425_c().func_177982_a(-3, -3, -3), func_180425_c().func_177982_a(3, 3, 3));
            for (BlockPos pos : nearblocks) {
                if (this.field_70170_p.func_180495_p(pos).func_177230_c() == BlockInit.incandesciteOre) {
                    this.field_70170_p.func_175656_a(pos, BlockInit.incandesciteOre.func_176203_a(1));
                }
            }
            func_184185_a(SoundInit.GLOW_BOMB, 1.0f, 1.0f);
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.LIGHT_FLASH).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
