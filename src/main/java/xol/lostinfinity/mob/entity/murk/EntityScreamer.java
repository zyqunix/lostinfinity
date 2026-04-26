package xol.lostinfinity.mob.entity.murk;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.projectile.entity.EntityScreamerBlast;
import xol.lostinfinity.projectile.entity.EntityScreamerPortalEffect;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityScreamer extends EntityFloatingBase implements IConditionalDamage {
    private static final DataParameter<Boolean> PINCERS_MOVING = EntityDataManager.func_187226_a(EntityScreamer.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> MOVE_PINCER_UP = EntityDataManager.func_187226_a(EntityScreamer.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> IN_OCEAN = EntityDataManager.func_187226_a(EntityScreamer.class, DataSerializers.field_187198_h);
    private boolean hasPortal;
    public EntityScreamer(World worldIn) {
        super(worldIn);
        this.hasPortal = false;
        func_70105_a(5.0f, 8.0f);
        this.rawFlySpeed = 0.94f;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(PINCERS_MOVING, false);
        func_184212_Q().func_187214_a(MOVE_PINCER_UP, false);
        func_184212_Q().func_187214_a(IN_OCEAN, false);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 1, (List<String>) Arrays.asList("Darkborn", "Aquatic"));
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    @Nullable
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
    public boolean isPincerMovingUp() {
        return ((Boolean) func_184212_Q().func_187225_a(MOVE_PINCER_UP)).booleanValue();
    }
    public boolean isPincerMoving() {
        return ((Boolean) func_184212_Q().func_187225_a(PINCERS_MOVING)).booleanValue();
    }
    public void setPincerMoving(boolean moving) {
        func_184212_Q().func_187227_b(PINCERS_MOVING, Boolean.valueOf(moving));
    }
    public void setPincerMovingUp(boolean moving) {
        func_184212_Q().func_187227_b(MOVE_PINCER_UP, Boolean.valueOf(moving));
    }
    public boolean getInOcean() {
        return ((Boolean) this.field_70180_af.func_187225_a(IN_OCEAN)).booleanValue();
    }
    public void setInOcean(boolean inOcean) {
        func_184212_Q().func_187227_b(IN_OCEAN, false);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.WHISPER_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.WHISPER_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.WHISPER_AMBIENT;
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        EntityPlayer closest;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.hasPortal) {
                for (EntityScreamerPortalEffect portal : this.field_70170_p.func_72872_a(EntityScreamerPortalEffect.class, new AxisAlignedBB(func_180425_c()).func_72314_b(2.0d, 2.0d, 2.0d))) {
                    double dist = portal.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    if (dist < 1.2d && (closest = this.field_70170_p.func_72890_a(this, 200.0d)) != null) {
                        ItemStack stack = new ItemStack(ItemInit.scrollOfRedirection, 1);
                        closest.func_191521_c(stack);
                        closest.func_145747_a(new TextComponentString(TextFmt.Green + String.format("The screamer is trying to escape to the Shadow Sea %s. It thinks you cannot find it there. I have given you a scroll to it's location.", closest.func_70005_c_())));
                        func_70106_y();
                        portal.func_70106_y();
                        func_184185_a(SoundInit.RAPID_TELEPORT, 2.0f, 1.0f);
                        this.field_70170_p.func_184133_a((EntityPlayer) null, closest.func_180425_c(), SoundInit.RAPID_TELEPORT, SoundCategory.HOSTILE, 1.0f, 1.0f);
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.setCount(3);
                        config1.createInstance().setParticle(ParticleInit.EXPLOSION_BLUE).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                        config1.createInstance().setParticle(ParticleInit.EXPLOSION_TEAL).setSpread(5.0d, 1.0d, 5.0d).setIgnoreRange(true);
                        CustomParticleConfig config2 = new CustomParticleConfig();
                        config2.createInstance().setParticle(ParticleInit.MURK).setSpread(10.0d, 2.0d, 10.0d).setCount(7).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u + 0.25d, this.field_70161_v);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config2, this.field_70165_t, this.field_70163_u + 0.25d, this.field_70161_v);
                    }
                }
                return;
            }
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
            }
            if (this.field_70173_aa % 400 == 0) {
                for (EntityPlayer player : this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_180425_c()).func_186662_g(40.0d))) {
                    player.func_70690_d(new PotionEffect(PotionInit.DISTORTION, 120, 1));
                    player.func_145747_a(new TextComponentString(TextFmt.Dark_Aqua + String.format("Your head is rattled by the scream.", new Object[0])));
                    this.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundInit.WHISPER_AMBIENT, SoundCategory.MASTER, 1.3f, 0.5f);
                }
            }
            if (this.field_70146_Z.nextInt(50) == 0) {
                soundPlayers(randomWhisper(this.field_70146_Z.nextInt(5)), 1.0f, 0.4f + (this.field_70146_Z.nextFloat() * 0.5f));
            }
            boolean ocean = getInOcean();
            if (this.field_70173_aa % (ocean ? 100 : 200) <= 40) {
                if (ocean) {
                    this.rawFlySpeed = 1.2f;
                } else {
                    this.rawFlySpeed = 0.98f;
                }
            } else if (ocean) {
                this.rawFlySpeed = 0.97f;
            } else {
                this.rawFlySpeed = 0.94f;
            }
            if ((ocean && this.field_70173_aa % 10 == 0) || (!ocean && this.field_70173_aa % 30 == 0)) {
                EntityScreamerBlast shot = new EntityScreamerBlast(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                shot.setThrower(this);
                shot.func_70186_c((-0.1f) + (this.field_70146_Z.nextFloat() * 0.2f), this.field_70146_Z.nextBoolean() ? -2.0d : 2.0d, (-0.1f) + (this.field_70146_Z.nextFloat() * 0.2f), 0.7f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
                soundPlayers(SoundInit.LASER_WEAPON_1, 0.5f, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
            }
        }
    }
    protected void soundPlayers(SoundEvent sound, float vol, float pitch) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_180425_c()).func_186662_g(25.0d))) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol, pitch);
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 100;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        if (!this.field_70170_p.field_72995_K) {
            int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
            AxisAlignedBB pullBox = new AxisAlignedBB(func_180425_c()).func_186662_g(45.0d);
            for (EntityPlayer entity : this.field_70170_p.func_72872_a(EntityPlayer.class, pullBox)) {
                entity.func_145747_a(new TextComponentString(TextFmt.Gold + "The Screamer is at " + lifePercent + "% health."));
            }
            if (this.field_70170_p.field_73011_w.func_186058_p() == DimensionInit.infiniteMurk && remainingLives() <= numberOfLives() / 2 && !this.hasPortal) {
                func_184185_a(SoundInit.LARGE_TELEPORT, 2.0f, 1.0f);
                this.hasPortal = true;
                EntityScreamerPortalEffect portal = new EntityScreamerPortalEffect(this.field_70170_p);
                portal.func_70107_b(this.field_70165_t, this.field_70163_u + 8.0d, this.field_70161_v);
                func_70605_aq().func_75642_a(this.field_70165_t, this.field_70163_u + 8.0d, this.field_70161_v, 0.75d);
                this.field_70170_p.func_72838_d(portal);
                func_70606_j(func_110138_aP());
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            func_145779_a(ItemInit.astralOrgan, 1);
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        return !this.hasPortal;
    }
    private SoundEvent randomWhisper(int i) {
        switch (i) {
            case 0:
                return SoundInit.WHISPER_1;
            case 1:
                return SoundInit.WHISPER_2;
            case 2:
                return SoundInit.WHISPER_3;
            case 3:
                return SoundInit.WHISPER_4;
            case TileEntityFusionTable.BOARD_ROWS :
                return SoundInit.WHISPER_5;
            default:
                return SoundInit.WHISPER_5;
        }
    }
}
