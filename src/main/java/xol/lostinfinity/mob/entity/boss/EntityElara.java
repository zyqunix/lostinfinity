package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.projectile.entity.EntityElaraShot;
import xol.lostinfinity.stone.EntityInfinityStone;
public class EntityElara extends EntityMob {
    private static final DataParameter<Byte> TYPE = EntityDataManager.func_187226_a(EntityElara.class, DataSerializers.field_187191_a);
    private static final DataParameter<Float> spawnX = EntityDataManager.func_187226_a(EntityElara.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> spawnY = EntityDataManager.func_187226_a(EntityElara.class, DataSerializers.field_187193_c);
    private static final DataParameter<Float> spawnZ = EntityDataManager.func_187226_a(EntityElara.class, DataSerializers.field_187193_c);
    public EntityElara(World worldIn) {
        super(worldIn);
        func_70105_a(2.5f, 7.0f);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(TYPE, (byte) 0);
        this.field_70180_af.func_187214_a(spawnX, Float.valueOf(0.0f));
        this.field_70180_af.func_187214_a(spawnY, Float.valueOf(-10.0f));
        this.field_70180_af.func_187214_a(spawnZ, Float.valueOf(0.0f));
    }
    public byte getForm() {
        return ((Byte) this.field_70180_af.func_187225_a(TYPE)).byteValue();
    }
    public void setForm(byte f) {
        this.field_70180_af.func_187227_b(TYPE, Byte.valueOf(f));
    }
    public void resetSpawnCoords(float xp, float yp, float zp) {
        this.field_70180_af.func_187227_b(spawnX, Float.valueOf(xp));
        this.field_70180_af.func_187227_b(spawnY, Float.valueOf(yp));
        this.field_70180_af.func_187227_b(spawnZ, Float.valueOf(zp));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74774_a("BossType", getForm());
        tag.func_74776_a("SpawnX", ((Float) this.field_70180_af.func_187225_a(spawnX)).floatValue());
        tag.func_74776_a("SpawnY", ((Float) this.field_70180_af.func_187225_a(spawnY)).floatValue());
        tag.func_74776_a("SpawnZ", ((Float) this.field_70180_af.func_187225_a(spawnZ)).floatValue());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setForm(tag.func_74771_c("BossType"));
        resetSpawnCoords(tag.func_74760_g("SpawnX"), tag.func_74760_g("SpawnY"), tag.func_74760_g("SpawnZ"));
    }
    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if (!this.field_70170_p.field_72995_K) {
            switch (getForm()) {
                case 0:
                    EntityInfinityStone ocstone = new EntityInfinityStone(this.field_70170_p);
                    ocstone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(ocstone);
                    break;
                case 1:
                    EntityInfinityStone mtstone = new EntityInfinityStone(this.field_70170_p);
                    mtstone.setStoneNum((byte) 1);
                    mtstone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(mtstone);
                    break;
            }
        }
    }
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIAttackMelee(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0d));
        this.field_70714_bg.func_75776_a(7, new EntityAIWanderAvoidWater(this, 1.0d));
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        func_175456_n();
    }
    protected void func_175456_n() {
        this.field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0d, false));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(30.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (((Float) this.field_70180_af.func_187225_a(spawnY)).floatValue() == -10.0f) {
            this.field_70180_af.func_187227_b(spawnY, Float.valueOf((float) this.field_70163_u));
            this.field_70180_af.func_187227_b(spawnX, Float.valueOf((float) this.field_70165_t));
            this.field_70180_af.func_187227_b(spawnZ, Float.valueOf((float) this.field_70161_v));
        }
        EntityPlayerMP entityPlayerMPFunc_70638_az = func_70638_az();
        if (getForm() == 0) {
            if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 60 == 0 && entityPlayerMPFunc_70638_az != null) {
                EntityElaraShot shot = new EntityElaraShot(this.field_70170_p, this);
                double d0 = ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70165_t - this.field_70165_t;
                double d1 = (entityPlayerMPFunc_70638_az.func_174813_aQ().field_72338_b + ((double) (((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70131_O / 3.0f))) - shot.field_70163_u;
                double d2 = ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_184133_a(this.field_70717_bb, func_180425_c(), SoundEvents.field_187609_F, SoundCategory.HOSTILE, 1.0f, 1.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 140 == 0 && entityPlayerMPFunc_70638_az != null && (entityPlayerMPFunc_70638_az instanceof EntityPlayer) && !BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN).contains(((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70170_p.func_180494_b(entityPlayerMPFunc_70638_az.func_180425_c()))) {
                ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70165_t = ((Float) this.field_70180_af.func_187225_a(spawnX)).floatValue();
                ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70163_u = ((double) ((Float) this.field_70180_af.func_187225_a(spawnY)).floatValue()) + 10.0d;
                ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70161_v = ((Float) this.field_70180_af.func_187225_a(spawnZ)).floatValue();
                entityPlayerMPFunc_70638_az.field_71135_a.func_147359_a(new SPacketEntityTeleport(entityPlayerMPFunc_70638_az));
                entityPlayerMPFunc_70638_az.func_145747_a(new TextComponentString(TextFmt.Blue + "Elara: Back to the ocean!"));
            }
            if (this.field_70173_aa % 100 == 0 && !this.field_70170_p.field_72995_K) {
                pushPlayersAway(this.field_70170_p, this);
            }
            if (this.field_70173_aa % 250 == 0) {
                func_70024_g(0.0d, 3.0d, 0.0d);
                this.field_70133_I = true;
            }
            if (this.field_70181_x > 0.3d && !this.field_70703_bu && !this.field_70170_p.field_72995_K) {
                EntityElaraShot shot2 = new EntityElaraShot(this.field_70170_p, this, 12);
                shot2.func_70186_c((-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), -1.0d, (-1.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 2.0d), 2.0f, 0.0f);
                this.field_70170_p.func_184133_a(this.field_70717_bb, func_180425_c(), SoundEvents.field_187612_G, SoundCategory.HOSTILE, 1.0f, 1.0f);
                this.field_70170_p.func_72838_d(shot2);
            }
            if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 400 == 0) {
                for (int i = 0; i < 4 + this.field_70170_p.field_73012_v.nextInt(2); i++) {
                    EntityGuardian guardian = new EntityGuardian(this.field_70170_p);
                    guardian.func_70107_b((this.field_70165_t - 4.0d) + (8.0d * this.field_70170_p.field_73012_v.nextDouble()), this.field_70163_u + 2.0d, (this.field_70161_v - 4.0d) + (8.0d * this.field_70170_p.field_73012_v.nextDouble()));
                    this.field_70170_p.func_72838_d(guardian);
                }
                if (entityPlayerMPFunc_70638_az != null && (entityPlayerMPFunc_70638_az instanceof EntityPlayer)) {
                    entityPlayerMPFunc_70638_az.func_145747_a(new TextComponentString(TextFmt.Gray + "Elara: Guardians, to me!"));
                    return;
                }
                return;
            }
            return;
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 60 == 0 && entityPlayerMPFunc_70638_az != null) {
            EntityElaraShot shot3 = new EntityElaraShot(this.field_70170_p, this);
            double d02 = ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70165_t - this.field_70165_t;
            double d12 = (entityPlayerMPFunc_70638_az.func_174813_aQ().field_72338_b + ((double) (((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70131_O / 3.0f))) - shot3.field_70163_u;
            double d22 = ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70161_v - this.field_70161_v;
            double d32 = MathHelper.func_76133_a((d02 * d02) + (d22 * d22));
            shot3.func_70186_c(d02, d12 + (d32 * 0.20000000298023224d), d22, 2.0f, 0.0f);
            this.field_70170_p.func_184133_a(this.field_70717_bb, func_180425_c(), SoundEvents.field_187534_aX, SoundCategory.HOSTILE, 1.0f, 1.0f);
            this.field_70170_p.func_72838_d(shot3);
        }
        if (this.field_70173_aa % 100 == 0 && !this.field_70170_p.field_72995_K) {
            pushPlayersAway(this.field_70170_p, this);
        }
        if (this.field_70173_aa % 150 == 0 && func_70638_az() != null && (func_70638_az() instanceof EntityPlayer)) {
            EntityPlayer pl = func_70638_az();
            func_70024_g((pl.field_70165_t - this.field_70165_t) * 0.145d, 1.0d, (pl.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
        if (this.field_70173_aa % 120 == 0 && !this.field_70170_p.field_72995_K && entityPlayerMPFunc_70638_az != null && (entityPlayerMPFunc_70638_az instanceof EntityPlayer)) {
            for (int i2 = 0; i2 < 8 + this.field_70170_p.field_73012_v.nextInt(4); i2++) {
                EntityElaraShot shot4 = new EntityElaraShot(this.field_70170_p, (((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70165_t - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d), ((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70163_u + 20.0d, (((EntityLivingBase) entityPlayerMPFunc_70638_az).field_70161_v - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d));
                shot4.setThrower(this);
                shot4.setDenom(6);
                shot4.func_70186_c(0.0d, -0.05d, 0.0d, 0.3f, 0.0f);
                this.field_70170_p.func_184133_a(this.field_70717_bb, func_180425_c(), SoundEvents.field_187528_aR, SoundCategory.HOSTILE, 1.0f, 1.0f);
                this.field_70170_p.func_72838_d(shot4);
            }
            entityPlayerMPFunc_70638_az.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Elara: Look up!"));
        }
    }
    private void pushPlayersAway(World worldIn, EntityLiving creature) {
        for (EntityPlayerMP entityPlayerMP : worldIn.func_72872_a(EntityPlayer.class, creature.func_174813_aQ().func_72314_b(7.0d, 7.0d, 7.0d))) {
            entityPlayerMP.func_70024_g(Math.signum(creature.field_70165_t - ((EntityPlayer) entityPlayerMP).field_70165_t) * (-1.39d), 0.65d, Math.signum(creature.field_70161_v - ((EntityPlayer) entityPlayerMP).field_70161_v) * (-1.39d));
            entityPlayerMP.field_71135_a.func_147359_a(new SPacketEntityVelocity(entityPlayerMP));
            entityPlayerMP.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + "Elara: Stand back!"));
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187798_ea;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187800_eb;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
}
