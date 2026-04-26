package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityLeer.class */
public class EntityLeer extends EntityMultipleLives implements IMaxAttack, IBasicAI, IConditionalDamage {
    private static final DataParameter<Boolean> REVEALED = EntityDataManager.func_187226_a(EntityLeer.class, DataSerializers.field_187198_h);
    private int revealedTimer;
    private float revealedAlph;

    public EntityLeer(World worldIn) {
        super(worldIn);
        this.revealedTimer = 200;
        this.revealedAlph = 0.0f;
        func_70105_a(1.2f, 2.2f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(REVEALED, false);
    }

    public boolean isRevealed() {
        return ((Boolean) this.field_70180_af.func_187225_a(REVEALED)).booleanValue();
    }

    public void setRevealed(boolean rev) {
        this.field_70180_af.func_187227_b(REVEALED, Boolean.valueOf(rev));
    }

    public float getAlpha() {
        return this.revealedAlph;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            if (!isRevealed() && (entity instanceof EntityPlayer)) {
                entity.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "You feel something scratch you."));
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (isRevealed()) {
                this.revealedTimer--;
                if (this.revealedTimer == 0) {
                    setRevealed(false);
                    return;
                }
                return;
            }
            this.revealedTimer = 200;
            return;
        }
        if (isRevealed()) {
            if (this.revealedAlph < 1.0f) {
                this.revealedAlph += 0.02f;
            }
        } else if (this.revealedAlph > 0.0f) {
            this.revealedAlph -= 0.02f;
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.LEER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.LEER_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.LEER_AMBIENT;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_LEER;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return isRevealed();
    }
}
