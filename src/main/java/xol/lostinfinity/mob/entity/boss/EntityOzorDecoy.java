package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityOzorDecoy extends EntityMob implements IMaxAttack {
    private float ozorAlpha;
    private float growAlphaSpeed;
    private int fadeTimer;
    private static final DataParameter<Boolean> FADING = EntityDataManager.func_187226_a(EntityOzor.class, DataSerializers.field_187198_h);
    public EntityOzorDecoy(World worldIn) {
        super(worldIn);
        this.ozorAlpha = 0.0f;
        this.growAlphaSpeed = 0.05f * (1 + this.field_70146_Z.nextInt(9));
        this.fadeTimer = 30;
        func_70105_a(7.5f, 7.5f);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(FADING, false);
    }
    public boolean getFading() {
        return ((Boolean) this.field_70180_af.func_187225_a(FADING)).booleanValue();
    }
    public void setFading(boolean fade) {
        this.field_70180_af.func_187227_b(FADING, Boolean.valueOf(fade));
    }
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("FadingStage", getFading());
    }
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setFading(tag.func_74767_n("FadingStage"));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15000.0d);
    }
    public float getAlpha() {
        return this.ozorAlpha;
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.field_70170_p.field_72995_K) {
            boolean fading = getFading();
            if (fading) {
                if (this.ozorAlpha > 0.0f) {
                    this.ozorAlpha = (float) (((double) this.ozorAlpha) - 0.05d);
                    return;
                }
                return;
            } else {
                if (this.ozorAlpha < 1.0f) {
                    this.ozorAlpha += this.growAlphaSpeed;
                    return;
                }
                return;
            }
        }
        if (getFading()) {
            if (this.fadeTimer == 0) {
                func_70106_y();
            }
            this.fadeTimer--;
        }
    }
    public void func_70645_a(DamageSource cause) {
        if (!this.field_70170_p.field_72995_K) {
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_184185_a(SoundInit.GENERIC_STYLE3_DEATH, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
                IMaxAttack.dealMaxHealth((Entity) this, (EntityLivingBase) target, 3, 2.0f);
                target.func_145747_a(new TextComponentString(TextFmt.Yellow + "The decoy releases a toxic fume into the air."));
            }
        }
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
    protected void messagePlayers(String message) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(message));
        }
    }
    protected void soundPlayers(SoundEvent sound, float vol) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
        }
    }
    protected boolean func_70692_ba() {
        return false;
    }
    public boolean func_70814_o() {
        return true;
    }
}
