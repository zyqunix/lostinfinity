package xol.lostinfinity.mob.entity.boss;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityBlightedComet;
import xol.lostinfinity.projectile.entity.EntityBossPortalEffect;
import xol.lostinfinity.projectile.entity.EntityHomingBlight;
import xol.lostinfinity.projectile.entity.EntityTentacleSeed;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityTempTentacle extends EntityMultipleLives implements IMaxAttack {
    private int phase;
    private int nextFormTimer;
    private Vec3d homePos;
    private ArrayList<EntityLivingBase> targets;
    private EntityLivingBase swoopTarget;
    private static final double radius = 50.0d;
    private static final double circleSpeed = 2.0d;
    private double theta;
    private boolean swooped;
    private static final double swoopHeight = 1.0d;
    private static final double swoopSpeed = 3.0d;
    private ArrayList<EntityLivingBase> offsetMobs;
    private boolean pulled;
    public EntityTempTentacle(World worldIn) {
        super(worldIn);
        this.nextFormTimer = 80;
        this.homePos = null;
        this.targets = null;
        this.swoopTarget = null;
        this.theta = 0.0d;
        this.swooped = false;
        this.offsetMobs = null;
        this.pulled = false;
        func_70105_a(2.0f, 7.0f);
    }
    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_()) {
                IMaxAttack.dealMaxHealth(this, play, 5);
                play.func_70024_g(this.field_70159_w * 1.5d, circleSpeed, this.field_70179_y * 1.5d);
                play.field_70133_I = true;
            }
        }
        entityIn.func_70108_f(this);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(swoopHeight);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(swoopHeight);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15000.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    public void func_70636_d() {
        super.func_70636_d();
        if (getLivesCount() >= numberOfLives() / 2) {
        }
        if (this.field_70173_aa > 5) {
            circle();
        }
        if (this.phase == 0) {
            charge();
            launchSeeds();
        } else if (this.phase == 1) {
            rainComets();
            summonPortals();
        } else if (this.phase == 2) {
            launchSeeds();
            rainComets();
        } else if (this.phase == 3) {
            charge();
            warpReality();
        } else if (this.phase == 4) {
            rainComets();
            groupPlayers();
        } else if (this.phase == 5) {
            fireHomingBlights();
            groupPlayers();
        }
        updatePhase(this.phase);
    }
    private void fireHomingBlights() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 80 == 20) {
            targetPlayers();
            if (this.targets != null) {
                for (EntityLivingBase entity : this.targets) {
                    EntityHomingBlight homer = new EntityHomingBlight(this.field_70170_p, this);
                    homer.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    homer.setTarget(entity);
                    homer.func_70186_c(0.0d, 0.0d, 0.0d, 0.0f, 0.0f);
                    this.field_70170_p.func_72838_d(homer);
                }
            }
        }
    }
    private void groupPlayers() {
        if (!this.field_70170_p.field_72995_K && !this.pulled) {
            targetPlayers();
            if (this.targets != null) {
                double minDist = 99999.0d;
                EntityLivingBase closest = null;
                for (EntityLivingBase entity : this.targets) {
                    double dist = func_174791_d().func_72438_d(entity.func_174791_d());
                    if (dist < minDist) {
                        minDist = dist;
                        closest = entity;
                    }
                }
                for (EntityLivingBase target : this.targets) {
                    target.func_70634_a(closest.field_70165_t, closest.field_70163_u, closest.field_70161_v);
                }
                this.pulled = true;
            }
        }
    }
    private void warpReality() {
        targetMobs();
        if (this.offsetMobs == null && this.targets != null) {
            this.offsetMobs = new ArrayList<>();
            for (EntityLivingBase target : this.targets) {
                if (!target.equals(this)) {
                    double x = (this.field_70170_p.field_73012_v.nextDouble() * 5.0d) - 2.5d;
                    double y = (this.field_70170_p.field_73012_v.nextDouble() * 5.0d) - 2.5d;
                    double z = (this.field_70170_p.field_73012_v.nextDouble() * 5.0d) - 2.5d;
                    target.func_174826_a(target.func_174813_aQ().func_72317_d(x, y, z));
                    this.offsetMobs.add(target);
                }
            }
            this.targets = null;
        } else if (this.field_70170_p.field_72995_K && this.offsetMobs != null && this.field_70173_aa % 10 == 0) {
            for (EntityLivingBase offsetMob : this.offsetMobs) {
                AxisAlignedBB entityBox = offsetMob.func_174813_aQ();
                double d = entityBox.field_72340_a;
                while (true) {
                    double i = d;
                    if (i <= entityBox.field_72336_d) {
                        double d2 = entityBox.field_72338_b;
                        while (true) {
                            double j = d2;
                            if (j <= entityBox.field_72337_e) {
                                double d3 = entityBox.field_72339_c;
                                while (true) {
                                    double k = d3;
                                    if (k <= entityBox.field_72334_f) {
                                        this.field_70170_p.func_175688_a(ParticleInit.CLAW_MARKS, i, j, k, ((-0.5d) + this.field_70146_Z.nextDouble()) * swoopSpeed, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * swoopSpeed, new int[0]);
                                        d3 = k + 0.5d;
                                    }
                                }
                                d2 = j + 0.5d;
                            }
                        }
                        d = i + 0.5d;
                    }
                }
            }
        }
        this.targets = null;
    }
    private void charge() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.swoopTarget == null) {
                double minDist = 99999.0d;
                EntityLivingBase swoop = null;
                if (this.targets == null) {
                    targetPlayers();
                }
                for (EntityLivingBase entity : this.targets) {
                    double dist = func_174791_d().func_72438_d(entity.func_174791_d());
                    if (dist < minDist) {
                        minDist = dist;
                        swoop = entity;
                    }
                }
                this.swoopTarget = swoop;
                this.swooped = false;
                return;
            }
            if (this.swooped || this.swoopTarget.field_70128_L) {
                if (this.field_70173_aa % 140 == 20) {
                    this.swoopTarget = null;
                    this.swooped = false;
                    return;
                }
                return;
            }
            Vec3d dest = this.swoopTarget.func_174791_d().func_72441_c(0.0d, swoopHeight, 0.0d);
            Vec3d dir = dest.func_178788_d(func_174791_d()).func_72432_b();
            double dist2 = func_174791_d().func_72438_d(dest);
            if (dist2 < 0.5d) {
                this.swooped = true;
                return;
            }
            if (dist2 < 5.0d) {
                calculateVelocity((dir.field_72450_a * swoopSpeed) / swoopSpeed, (dir.field_72448_b * swoopSpeed) / swoopSpeed, (dir.field_72449_c * swoopSpeed) / swoopSpeed);
            } else {
                calculateVelocity(dir.field_72450_a * swoopSpeed, dir.field_72448_b * swoopSpeed, dir.field_72449_c * swoopSpeed);
            }
            this.field_70133_I = true;
        }
    }
    private void launchSeeds() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 40 == 10) {
            EntityTentacleSeed seed = new EntityTentacleSeed(this.field_70170_p);
            seed.setThrower(this);
            seed.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            seed.func_70186_c((this.field_70170_p.field_73012_v.nextDouble() * 0.2d) - 0.1d, (this.field_70170_p.field_73012_v.nextDouble() * 0.05d) - 0.1d, (this.field_70170_p.field_73012_v.nextDouble() * 0.2d) - 0.1d, 1.0f, 4.0f);
            this.field_70170_p.func_72838_d(seed);
        }
    }
    private void rainComets() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.targets == null) {
                targetPlayers();
            }
            for (EntityLivingBase entity : this.targets) {
                if (this.field_70173_aa % 10 == 5) {
                    EntityBlightedComet comet = new EntityBlightedComet(this.field_70170_p, this);
                    comet.func_70107_b((entity.field_70165_t + (this.field_70170_p.field_73012_v.nextDouble() * 5.0d)) - 2.5d, entity.field_70163_u + 40.0d, (entity.field_70161_v + (this.field_70170_p.field_73012_v.nextDouble() * 5.0d)) - 2.5d);
                    comet.func_70186_c(0.0d, -0.5d, 0.0d, 2.0f, 4.0f);
                    this.field_70170_p.func_72838_d(comet);
                }
            }
        }
    }
    private void summonPortals() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.targets == null) {
                targetPlayers();
            }
            for (EntityLivingBase entity : this.targets) {
                if (this.field_70173_aa % 80 == 20) {
                    EntityBossPortalEffect portal = new EntityBossPortalEffect(this.field_70170_p);
                    portal.func_70107_b(entity.field_70165_t, entity.field_70163_u + ((double) entity.field_70131_O) + circleSpeed, entity.field_70161_v);
                    portal.setCreator(this);
                    this.field_70170_p.func_72838_d(portal);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, entity.func_180425_c(), SoundInit.PORTAL_OPEN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            }
        }
    }
    private void targetMobs() {
        this.targets = new ArrayList<>();
        for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_72314_b(9.0d, 9.0d, 9.0d))) {
            if (!(entity instanceof EntityPlayer) && !entity.equals(this)) {
                this.targets.add(entity);
            }
        }
    }
    private void targetPlayers() {
        this.targets = new ArrayList<>();
        for (EntityLivingBase entityLivingBase : this.field_70170_p.func_73046_m().func_184103_al().func_181057_v()) {
            if (entityLivingBase.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) < 100.0d) {
                this.targets.add(entityLivingBase);
            }
        }
    }
    private void circle() {
        if (!this.field_70170_p.field_72995_K && this.swoopTarget == null) {
            if (this.homePos == null) {
                this.homePos = new Vec3d(-120.0d, 108.0d, 746.0d);
                this.theta = 1.5707963267948966d;
            }
            double yDiff = this.field_70163_u - this.homePos.field_72448_b;
            double xDiff = this.field_70165_t - this.homePos.field_72450_a;
            double zDiff = this.field_70161_v - this.homePos.field_72449_c;
            double distToCircle = Math.sqrt(Math.pow(xDiff, circleSpeed) + Math.pow(zDiff, circleSpeed)) - radius;
            if (Math.abs(yDiff) > swoopHeight) {
                Vec3d YVec = new Vec3d(0.0d, -yDiff, 0.0d).func_72432_b();
                calculateVelocity(YVec.field_72450_a, YVec.field_72448_b, YVec.field_72449_c);
                this.field_70133_I = true;
            } else {
                if (Math.abs(distToCircle) > radius) {
                    Vec3d toHome = new Vec3d((-xDiff) + radius, 0.0d, -zDiff).func_72432_b();
                    calculateVelocity(toHome.field_72450_a, toHome.field_72448_b, toHome.field_72449_c);
                    this.field_70133_I = true;
                    this.theta = 1.5707963705062866d;
                    return;
                }
                this.theta += 0.06283185307179587d;
                if (this.theta >= 6.283185307179586d) {
                    this.theta -= 6.283185307179586d;
                }
                double x1 = (radius * ((double) MathHelper.func_76126_a((float) this.theta))) + this.homePos.field_72450_a;
                double z1 = (radius * ((double) MathHelper.func_76134_b((float) this.theta))) + this.homePos.field_72449_c;
                Vec3d dir = new Vec3d(x1 - this.field_70165_t, 0.0d, z1 - this.field_70161_v).func_72432_b();
                calculateVelocity(dir.field_72450_a * circleSpeed, dir.field_72448_b * circleSpeed, dir.field_72449_c * circleSpeed);
                this.field_70133_I = true;
            }
        }
    }
    private void calculateVelocity(double x, double y, double z) {
        this.field_70159_w = x;
        this.field_70181_x = y;
        this.field_70179_y = z;
    }
    private void updatePhase(int phase) {
        this.nextFormTimer--;
        if (this.nextFormTimer == 0) {
            this.nextFormTimer = 80;
            this.swoopTarget = null;
            this.swooped = false;
            this.pulled = false;
            if (phase < 5) {
                this.phase++;
            } else {
                this.phase = 0;
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.GENERIC_STYLE3_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.GENERIC_STYLE3_HURT;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 2000;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
    }
    protected boolean func_70692_ba() {
        return false;
    }
}
