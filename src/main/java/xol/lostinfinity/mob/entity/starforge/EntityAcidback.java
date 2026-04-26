package xol.lostinfinity.mob.entity.starforge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityAcidback extends EntityMob implements IMaxAttack, IBasicAI {
    private int cooldown;
    private static final DataParameter<Boolean> IS_VOLATILE = EntityDataManager.func_187226_a(EntityAcidback.class, DataSerializers.field_187198_h);
    public EntityAcidback(World worldIn) {
        super(worldIn);
        this.cooldown = 0;
        func_70105_a(1.0f, 1.2f);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(IS_VOLATILE, false);
    }
    public boolean isVolatile() {
        return ((Boolean) this.field_70180_af.func_187225_a(IS_VOLATILE)).booleanValue();
    }
    public void setVolatility(boolean bool) {
        if (bool) {
            this.cooldown = 100;
        }
        this.field_70180_af.func_187227_b(IS_VOLATILE, Boolean.valueOf(bool));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("Volatility", isVolatile());
        tag.func_74768_a("VolCooldown", this.cooldown);
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setVolatility(tag.func_74767_n("Volatility"));
        this.cooldown = tag.func_74762_e("VolCooldown");
    }
    protected void func_184651_r() {
        initBasicTasks(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(700.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        IMaxAttack.dealMaxHealth(this, func_70638_az(), 5);
        if ((func_70638_az() instanceof EntityPlayer) && !this.field_70170_p.field_72995_K) {
            EntityPlayer target = func_70638_az();
            boolean is_vol = isVolatile();
            if (is_vol) {
                func_70606_j(0.0f);
                return true;
            }
            ItemStack held = target.func_184614_ca();
            if (held.func_77973_b().equals(Items.field_151069_bo) && target.func_70644_a(MobEffects.field_76436_u)) {
                setVolatility(true);
                held.func_190918_g(1);
                func_145779_a(ItemInit.concentratedVenom, 1);
                return true;
            }
            return true;
        }
        return true;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.cooldown > 0) {
            this.cooldown--;
            if (this.cooldown == 0) {
                setVolatility(false);
            }
        }
        if (this.field_70170_p.field_72995_K && isVolatile()) {
            for (int i = 0; i < 2; i++) {
                this.field_70170_p.func_175688_a(ParticleInit.ACID, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 10.0d, 5.0d, (this.field_70146_Z.nextDouble() - 0.5d) * 3.0d, new int[0]);
            }
        }
        if (this.field_70128_L || func_110143_aJ() < 0.0f) {
            if (this.field_70170_p.field_72995_K) {
                float partspeed = isVolatile() ? 6.0f : 3.0f;
                for (int i2 = 0; i2 < 9; i2++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.TOTEM, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * ((double) partspeed), 1.0d, (this.field_70146_Z.nextDouble() - 0.5d) * ((double) partspeed), new int[0]);
                }
                return;
            }
            double aoe = isVolatile() ? 12.0d : 5.0d;
            int denom = isVolatile() ? 8 : 4;
            for (EntityLivingBase near_pl : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(aoe, aoe, aoe))) {
                if (!near_pl.func_110124_au().equals(func_110124_au())) {
                    IMaxAttack.dealMaxHealth(this, near_pl, denom);
                }
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.STARFORGE_ACIDBACK_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.STARFORGE_ACIDBACK_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.STARFORGE_ACIDBACK_AMBIENT;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_STARFORGE_ACIDBACK;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
    public int func_70641_bl() {
        return 1;
    }
    public boolean func_70601_bi() {
        return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
    }
}
