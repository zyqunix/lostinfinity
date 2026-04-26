package xol.lostinfinity.mob.entity.starforge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityTetherBall;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityTetherbug.class */
public class EntityTetherbug extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.func_187226_a(EntityTetherbug.class, DataSerializers.field_187198_h);

    public EntityTetherbug(World worldIn) {
        super(worldIn);
        func_70105_a(2.7f, 1.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ATTACKING, false);
    }

    public boolean getAngry() {
        return ((Boolean) this.field_70180_af.func_187225_a(ATTACKING)).booleanValue();
    }

    public void setAngry(boolean f) {
        this.field_70180_af.func_187227_b(ATTACKING, Boolean.valueOf(f));
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 1);
            return true;
        }
        return false;
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            setAngry(func_70638_az() != null);
            if (func_70638_az() == null) {
                this.field_70181_x = 0.2d;
                this.field_70133_I = true;
            } else if (this.field_70173_aa % 60 == 0) {
                func_184185_a(SoundInit.TETHERBUG_ATTACK, 1.0f, 1.0f);
                EntityTetherBall shot = new EntityTetherBall(this.field_70170_p, this);
                double d0 = func_70638_az().field_70165_t - this.field_70165_t;
                double d1 = (func_70638_az().func_174813_aQ().field_72338_b + ((double) (func_70638_az().field_70131_O / 6.0f))) - shot.field_70163_u;
                double d2 = func_70638_az().field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
        }
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        ItemStack held = player.func_184586_b(hand);
        if (held.func_77973_b().equals(ItemInit.darksteelSheets) && this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.nonexistence) {
            if (!this.field_70170_p.field_72995_K) {
                EntityItem item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemInit.stickyDarksteel));
                item.field_70159_w = 0.0d;
                item.field_70181_x = 0.0d;
                item.field_70179_y = 0.0d;
                this.field_70170_p.func_72838_d(item);
            }
            func_184185_a(SoundInit.CREATURE_FEED, 1.5f, 0.7f);
            held.func_190918_g(1);
            return true;
        }
        return true;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 3;
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.TETHERBUG_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.TETHERBUG_HURT;
    }

    protected SoundEvent func_184639_G() {
        return SoundInit.TETHERBUG_AMBIENT;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70814_o() {
        return true;
    }

    public int func_70641_bl() {
        return 1;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }

    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_TETHERBUG;
    }
}
