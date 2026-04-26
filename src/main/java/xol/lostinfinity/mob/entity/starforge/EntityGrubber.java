package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityGrubber.class */
public class EntityGrubber extends EntityMultipleLives implements IMaxAttack, IConditionalDamage {
    private static final DataParameter<Boolean> AWAKE = EntityDataManager.func_187226_a(EntityGrubber.class, DataSerializers.field_187198_h);
    private int sleepTimer;
    private float eyeCoverAng;

    public EntityGrubber(World worldIn) {
        super(worldIn);
        this.sleepTimer = 0;
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(AWAKE, false);
    }

    public boolean isAwake() {
        return ((Boolean) this.field_70180_af.func_187225_a(AWAKE)).booleanValue();
    }

    public void setAwake(boolean awoke) {
        this.field_70180_af.func_187227_b(AWAKE, Boolean.valueOf(awoke));
        if (!awoke) {
            this.field_70714_bg.field_75782_a.clear();
            this.field_70715_bh.field_75782_a.clear();
        } else {
            initBasicTasks(this);
        }
        this.sleepTimer = 0;
    }

    public float getEyeCoverRot() {
        return this.eyeCoverAng;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (isAwake()) {
                if (this.sleepTimer == 100) {
                    setAwake(false);
                }
                this.sleepTimer++;
                if (this.field_70173_aa % 80 == 0 && func_70638_az() != null) {
                    EntityLivingBase target = func_70638_az();
                    func_70024_g((target.field_70165_t - this.field_70165_t) * 0.145d, 0.5d, (target.field_70161_v - this.field_70161_v) * 0.145d);
                    this.field_70133_I = true;
                    return;
                }
                return;
            }
            return;
        }
        if (isAwake()) {
            if (this.eyeCoverAng < 2.4f) {
                this.eyeCoverAng += 0.1f;
            }
        } else if (this.eyeCoverAng > 0.0f) {
            this.eyeCoverAng -= 0.1f;
        }
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_77973_b().equals(ItemInit.jarOfMurkySyrup) && this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk) {
            if (!this.field_70170_p.field_72995_K) {
                if (isAwake()) {
                    player.func_70690_d(new PotionEffect(PotionInit.DISTORTION, 300));
                    func_184185_a(SoundEvents.field_191255_dF, 1.5f, 0.9f + (this.field_70146_Z.nextFloat() * 0.1f));
                    EntityItem item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + 2.0d, this.field_70161_v, new ItemStack(ItemInit.murkyClay, 2));
                    item.field_70159_w = 0.0d;
                    item.field_70181_x = 0.0d;
                    item.field_70179_y = 0.0d;
                    this.field_70170_p.func_72838_d(item);
                } else {
                    setAwake(true);
                }
            }
            held.func_190918_g(1);
            return true;
        }
        return true;
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (IMaxAttack.dealMaxHealth(this, func_70638_az(), 2).didSuccessfulHit()) {
                func_70638_az().func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200));
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.GRUBBER_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GRUBBER_HURT;
    }

    protected SoundEvent func_184639_G() {
        if (!isAwake()) {
            return SoundInit.GRUBBER_AMBIENT_SLEEPING;
        }
        return SoundInit.GRUBBER_AMBIENT;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_GRUBBER;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return isAwake();
    }
}
