package xol.lostinfinity.mob.entity.cthulhu;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import xol.lostinfinity.common.events.EventsClientRender;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.animation.client.AnimationHandler;
import xol.lostinfinity.util.animation.client.AnimationProperty;
import xol.lostinfinity.util.animation.entity.IXolAnimated;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCthulhuTentacle.class */
public class EntityCthulhuTentacle extends AbstractCthulhuMinion implements IXolAnimated {
    private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(EntityCthulhuTentacle.class, DataSerializers.field_187193_c);
    private static final DataParameter<Boolean> INVERTED = EntityDataManager.func_187226_a(EntityCthulhuTentacle.class, DataSerializers.field_187198_h);
    private static final double ATTACK_COOLDOWN = 400.0d;
    private final AnimationHandler handler;
    private final Map<Entity, Long> attackCooldown;

    public EntityCthulhuTentacle(World worldIn) {
        super(worldIn);
        this.handler = new AnimationHandler();
        this.attackCooldown = new ConcurrentHashMap();
        func_70105_a(1.5f * getSize(), 9.75f * getSize());
        func_189654_d(true);
    }

    public void func_70636_d() {
        super.func_70636_d();
        livingUpdate();
    }

    protected void livingUpdate() {
        if (this.field_70173_aa > 280) {
            takeawayNumLives(getLivesCount() + 1);
        }
    }

    protected void func_70609_aI() {
        if (this.field_70170_p.field_72995_K) {
            this.field_70737_aN = 0;
            AnimationProperty property = getAnimationHandler().getAnimations().get("death");
            if (property == null) {
                playAnimation("death", 0.5f);
                return;
            } else {
                if (property.stopped) {
                    this.field_70725_aQ = 19;
                    super.func_70609_aI();
                    return;
                }
                return;
            }
        }
        super.func_70609_aI();
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            Long time = this.attackCooldown.get(player);
            if (time == null || System.currentTimeMillis() - time.longValue() >= ATTACK_COOLDOWN) {
                IMaxAttack.dealTrueDamage(this, player, player.func_110138_aP() * 0.5f);
                this.attackCooldown.put(entityIn, Long.valueOf(System.currentTimeMillis()));
                this.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.GENERIC_WHACK, SoundCategory.HOSTILE, 1.0f, 1.0f);
            }
        }
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70104_M() {
        return false;
    }

    public void func_184206_a(DataParameter<?> key) {
        super.func_184206_a(key);
        if (SIZE.equals(key)) {
            func_70105_a(1.5f * getSize(), 9.75f * getSize());
        }
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SIZE, Float.valueOf(3.0f));
        this.field_70180_af.func_187214_a(INVERTED, true);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void doDamageTint() {
    }

    protected void damageTint() {
        super.doDamageTint();
    }

    @Override // xol.lostinfinity.mob.entity.cthulhu.AbstractCthulhuMinion
    protected boolean func_70692_ba() {
        return false;
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        playAnimation("spawn", 0.5f);
        playAnimation("idle", 0.5f);
        if (this.field_70170_p.field_72995_K) {
            EventsClientRender.renderForce.put(Integer.valueOf(func_145782_y()), this);
        }
    }

    @Override // xol.lostinfinity.util.animation.entity.IXolAnimated
    public AnimationHandler getAnimationHandler() {
        return this.handler;
    }

    public void setSize(float size) {
        this.field_70180_af.func_187227_b(SIZE, Float.valueOf(size));
    }

    public float getSize() {
        return ((Float) this.field_70180_af.func_187225_a(SIZE)).floatValue();
    }

    public void setInverted(boolean flag) {
        this.field_70180_af.func_187227_b(INVERTED, Boolean.valueOf(flag));
    }

    public boolean isInverted() {
        return ((Boolean) this.field_70180_af.func_187225_a(INVERTED)).booleanValue();
    }
}
