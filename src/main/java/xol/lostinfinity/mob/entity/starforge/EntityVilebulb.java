package xol.lostinfinity.mob.entity.starforge;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityVilebulb.class */
public class EntityVilebulb extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Integer> GMOVE = EntityDataManager.func_187226_a(EntityVilebulb.class, DataSerializers.field_187192_b);
    private static final DataParameter<Boolean> VOLATILE = EntityDataManager.func_187226_a(EntityVilebulb.class, DataSerializers.field_187198_h);
    private boolean randomziedSpeed;
    private int explodeTimer;
    private boolean exploded;

    public EntityVilebulb(World worldIn) {
        super(worldIn);
        this.randomziedSpeed = false;
        this.explodeTimer = 0;
        this.exploded = false;
        func_70105_a(0.5f, 0.5f);
        func_189654_d(true);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(GMOVE, 0);
        this.field_70180_af.func_187214_a(VOLATILE, false);
    }

    public int getMovement() {
        return ((Integer) this.field_70180_af.func_187225_a(GMOVE)).intValue();
    }

    private void randomizeMovement() {
        int pick = this.field_70146_Z.nextInt(6);
        this.field_70180_af.func_187227_b(GMOVE, Integer.valueOf(pick));
        this.randomziedSpeed = true;
    }

    public boolean isVolatile() {
        return ((Boolean) this.field_70180_af.func_187225_a(VOLATILE)).booleanValue();
    }

    private void setVolatile(boolean vol) {
        this.field_70180_af.func_187227_b(VOLATILE, Boolean.valueOf(vol));
    }

    protected void func_82167_n(Entity entityIn) {
        if ((entityIn instanceof EntityPlayer) && this.field_70173_aa % 5 == 0) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                explode();
            }
        }
        entityIn.func_70108_f(this);
    }

    private void explode() {
        if (!this.exploded) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(4.0d))) {
                if (IMaxAttack.dealMaxHealth(this, near_pl, 1).didSuccessfulHit()) {
                    near_pl.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                }
            }
            func_184185_a(SoundInit.HOT_POD, 1.5f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
        }
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.VENOM).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
        CustomParticleConfig config2 = new CustomParticleConfig();
        config2.createInstance().setParticle(ParticleInit.VENOM_RING).setSpread(1.0d, 1.0d, 1.0d).setCount(5).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
        IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
        func_70106_y();
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (!this.randomziedSpeed) {
                randomizeMovement();
            }
            if (this.field_70173_aa % 10 == 0) {
                boolean found = false;
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(6.0d)).iterator();
                if (it.hasNext()) {
                    found = true;
                }
                setVolatile(found);
            }
            if (isVolatile()) {
                if (this.explodeTimer < 40) {
                    this.explodeTimer++;
                } else {
                    explode();
                }
            } else if (this.explodeTimer > 0) {
                this.explodeTimer--;
            }
        }
        switch (getMovement()) {
            case 0:
                this.field_70159_w = -0.5d;
                break;
            case 1:
                this.field_70159_w = 0.5d;
                break;
            case 2:
                this.field_70181_x = -0.5d;
                break;
            case 3:
                this.field_70181_x = 0.5d;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                this.field_70179_y = -0.5d;
                break;
            case 5:
                this.field_70179_y = 0.5d;
                break;
        }
        this.field_70133_I = true;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return null;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_VILEBULB;
    }
}
