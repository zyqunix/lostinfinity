package xol.lostinfinity.mob.entity.cthulhu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.common.events.EventsClientRender;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.base.IConditionalDamage;
import xol.lostinfinity.mob.entity.classify.ILostMultiPart;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuBeam;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuMeteor;
import xol.lostinfinity.projectile.cthulhu.EntityCthulhuMissile;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.animation.client.AnimationHandler;
import xol.lostinfinity.util.animation.entity.IXolAnimated;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/EntityCthulhu.class */
public class EntityCthulhu extends EntityMultipleLives implements IConditionalDamage, IMaxAttack, IXolAnimated, ILostMultiPart {
    public static final Map<Integer, EntityCthulhu> CTHULHUS = new HashMap();
    private static final DataParameter<Boolean> BARRIER = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187198_h);
    private static final DataParameter<Integer> BARRIER_CD_1 = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> BARRIER_CD_2 = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> BARRIER_CD_3 = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> BARRIER_CD_4 = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> PHASE = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187192_b);
    private static final DataParameter<Boolean> FIRST_SPAWN = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187198_h);
    private static final DataParameter<Boolean> ACTIVATED = EntityDataManager.func_187226_a(EntityCthulhu.class, DataSerializers.field_187198_h);
    private static final double AGGRO_RANGE = 9216.0d;
    private static final double DAMAGEABLE_RANGE = 4096.0d;
    private static final double PUNISH_RANGE = 4096.0d;
    private static final double MAX_PUNISH_RANGE = 10000.0d;
    private final EntityCthulhuPart[] parts;
    private final List<List<Runnable>> attacks;
    private final AnimationHandler handler;
    private int currentAttack;
    private boolean canChangeAttack;
    private int attackTimer;
    private final List<EntityPlayer> inRangePlayers;
    private final List<EntityCthulhuRift> rifts;
    private final List<EntityCthulhuBlackHole> blackHoles;
    private final List<EntityCthulhuTurret> turrets;
    private final List<EntityCthulhuMissile> missiles;
    private final List<EntityCthulhuCloud> clouds;
    private final List<EntityCthulhuHealingOrb> healingOrbs;
    private final List<EntityCthulhuTentacle> tentacles;
    private final List<EntityCthulhuTentaclePersist> tentaclePersists;
    private final List<EntityCelestialStatue> statues;
    private final List<BlockPos> strikeLocations;
    private boolean essenceCharged;

    public EntityCthulhu(World worldIn) {
        super(worldIn);
        this.parts = new EntityCthulhuPart[5];
        this.attacks = new ArrayList();
        this.handler = new AnimationHandler();
        this.currentAttack = 0;
        this.canChangeAttack = false;
        this.attackTimer = 0;
        this.inRangePlayers = new ArrayList();
        this.rifts = new ArrayList();
        this.blackHoles = new ArrayList();
        this.turrets = new ArrayList();
        this.missiles = new ArrayList();
        this.clouds = new ArrayList();
        this.healingOrbs = new ArrayList();
        this.tentacles = new ArrayList();
        this.tentaclePersists = new ArrayList();
        this.statues = new ArrayList();
        this.strikeLocations = new ArrayList();
        this.essenceCharged = false;
        this.attacks.add(new ArrayList());
        this.attacks.add(new ArrayList());
        this.attacks.add(new ArrayList());
        this.attacks.get(0).add(this::knockbackWave);
        this.attacks.get(0).add(this::tentacleWave);
        this.attacks.get(0).add(this::summonTurret);
        this.attacks.get(0).add(this::missileAttack);
        this.attacks.get(1).add(this::lightningStrike);
        this.attacks.get(1).add(this::openRifts);
        this.attacks.get(1).add(this::summonHealingOrb);
        this.attacks.get(1).add(this::beamAttack);
        this.attacks.get(2).add(this::toxicCloud);
        this.attacks.get(2).add(this::blackHole);
        this.attacks.get(2).add(this::summonLargeTentacle);
        this.attacks.get(2).add(this::meteorShower);
        func_70105_a(15.0f, 15.0f);
        for (int i = 0; i < this.parts.length; i++) {
            this.parts[i] = new EntityCthulhuPart(this, 24.0f, 5.0f, (i * 5) + 15);
            this.parts[i].updatePosition();
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(BARRIER, false);
        this.field_70180_af.func_187214_a(BARRIER_CD_1, 0);
        this.field_70180_af.func_187214_a(BARRIER_CD_2, 0);
        this.field_70180_af.func_187214_a(BARRIER_CD_3, 0);
        this.field_70180_af.func_187214_a(BARRIER_CD_4, 0);
        this.field_70180_af.func_187214_a(PHASE, 1);
        this.field_70180_af.func_187214_a(FIRST_SPAWN, true);
        this.field_70180_af.func_187214_a(ACTIVATED, false);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        if (isBarrierActive()) {
            return;
        }
        float ratio = remainingLives() / numberOfLives();
        if (ratio < 0.6666f && getPhase() == 1) {
            setPhase(2);
            setBarrierActive(true);
        } else if (ratio < 0.3333f && getPhase() == 2) {
            setPhase(3);
            setBarrierActive(true);
        }
    }

    public void func_70071_h_() {
        double x = this.field_70165_t;
        double y = this.field_70163_u;
        double z = this.field_70161_v;
        super.func_70071_h_();
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        for (Entity entity : this.parts) {
            this.field_70170_p.func_72866_a(entity, true);
            entity.updatePosition();
        }
    }

    protected void soundPlayers(SoundEvent sound, float vol, float pitch) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(64.0d))) {
            contender.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol + 0.3f, pitch);
        }
    }

    protected void soundClosePlayers(SoundEvent sound, float vol, float pitch, BlockPos pos) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(pos).func_186662_g(15.0d))) {
            contender.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, vol + 0.3f, pitch);
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

    private SoundEvent randomAmbient() {
        switch (this.field_70146_Z.nextInt(5)) {
            case 0:
                return SoundInit.TT_AMBIENT_1;
            case 1:
                return SoundInit.TT_AMBIENT_2;
            case 2:
                return SoundInit.TT_AMBIENT_3;
            case 3:
                return SoundInit.TT_AMBIENT_4;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return SoundInit.TT_AMBIENT_5;
            default:
                return SoundInit.TT_AMBIENT_1;
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K || func_110143_aJ() <= 0.0f) {
            return;
        }
        checkBarrierDisable();
        decrementBarrierTimers();
        if (!isBarrierActive() && isActivated() && this.field_70173_aa % 140 == 0) {
            soundPlayers(randomAmbient(), 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.4f));
        }
        if (isBarrierActive()) {
            barrierKnockback();
            return;
        }
        if (this.field_70173_aa == 190 && !isActivated()) {
            soundPlayers(SoundInit.TT_RISEUP, 1.75f, 1.0f);
        }
        if (this.field_70173_aa < 280 && !isActivated()) {
            return;
        }
        setActivated(true);
        this.inRangePlayers.clear();
        for (EntityPlayer player : this.field_70170_p.field_73010_i) {
            if (player.func_70068_e(this) <= AGGRO_RANGE) {
                this.inRangePlayers.add(player);
            }
        }
        if (this.field_70173_aa % 4 == 1) {
            punishRange();
        }
        this.canChangeAttack = true;
        this.attackTimer++;
        for (int i = 0; i < getPhase(); i++) {
            this.attacks.get(i).get(this.currentAttack).run();
        }
        if (this.canChangeAttack) {
            sendMessage("attack change");
            playAnimation("cast_" + (this.field_70146_Z.nextInt(2) + 1), 1.0f);
            this.canChangeAttack = false;
            this.currentAttack = (this.currentAttack + 1) % 4;
            this.attackTimer = 0;
        }
        this.rifts.removeIf(rift -> {
            return rift.field_70128_L;
        });
        this.blackHoles.removeIf(blackHole -> {
            return blackHole.field_70128_L;
        });
        this.turrets.removeIf(turret -> {
            return turret.field_70128_L;
        });
        this.missiles.removeIf(missile -> {
            return missile.field_70128_L;
        });
        this.clouds.removeIf(cloud -> {
            return cloud.field_70128_L;
        });
        this.healingOrbs.removeIf(orb -> {
            return orb.field_70128_L;
        });
        this.tentacles.removeIf(tentacle -> {
            return tentacle.field_70128_L;
        });
        this.tentaclePersists.removeIf(tentaclePersist -> {
            return tentaclePersist.field_70128_L;
        });
        this.statues.removeIf(statue -> {
            return statue.field_70128_L;
        });
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        return super.func_70097_a(source, amount);
    }

    protected void func_82167_n(Entity entityIn) {
    }

    public boolean func_70104_M() {
        return false;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    private void punishRange() {
        for (EntityPlayer player : this.field_70170_p.field_73010_i) {
            if (player.func_70068_e(this) > 4096.0d && player.func_70068_e(this) <= MAX_PUNISH_RANGE) {
                IMaxAttack.dealTrueDamage(this, player, player.func_110138_aP() * 0.05f);
            }
        }
    }

    private void knockbackWave() {
        setAttackEnded(this.attackTimer >= 60);
        if (this.attackTimer == 1) {
            if (this.tentaclePersists.size() == 0) {
                sendMessage("knockbackWave");
            } else {
                sendMessage("knockbackWave + tentacle buffed");
            }
            this.tentaclePersists.forEach((v0) -> {
                v0.cast();
            });
            for (EntityPlayer player : this.inRangePlayers) {
                double x = player.field_70165_t - this.field_70165_t;
                double y = player.field_70163_u - this.field_70163_u;
                double z = player.field_70161_v - this.field_70161_v;
                double distance = MathHelper.func_181161_i((x * x) + (y * y) + (z * z));
                player.field_70159_w = x * distance * 8.0d * ((double) (this.tentaclePersists.size() + 1));
                player.field_70181_x = 4.5f * (this.tentaclePersists.size() + 1);
                player.field_70179_y = z * distance * 8.0d * ((double) (this.tentaclePersists.size() + 1));
                player.field_70133_I = true;
                if (this.field_70170_p.field_72995_K) {
                    soundPlayers(SoundInit.ELECTRIC_WOOSH, 1.0f, 1.0f);
                }
            }
        }
    }

    private void barrierKnockback() {
        for (EntityPlayerMP player : (List) this.field_70170_p.func_73046_m().func_184103_al().func_181057_v().stream().filter(player2 -> {
            return func_70011_f(player2.field_70165_t, this.field_70163_u, player2.field_70161_v) <= 47.8d;
        }).collect(Collectors.toList())) {
            double x = player.field_70165_t - this.field_70165_t;
            double y = player.field_70163_u - this.field_70163_u;
            double z = player.field_70161_v - this.field_70161_v;
            double distance = MathHelper.func_181161_i((x * x) + (y * y) + (z * z));
            player.field_70159_w = x * distance * 2.0d;
            player.field_70181_x = 1.5d;
            player.field_70179_y = z * distance * 2.0d;
            player.field_70133_I = true;
        }
    }

    private void tentacleWave() {
        if (this.attackTimer == 1) {
            sendMessage("tentacleWave");
        }
        setAttackEnded(this.attackTimer >= 150);
        if (this.attackTimer % 5 == 1 && this.attackTimer <= 101) {
            EntityCthulhuTentacle tentacle = new EntityCthulhuTentacle(this.field_70170_p);
            tentacle.setSize(3.0f + (this.field_70170_p.field_73012_v.nextFloat() * (4.0f + (getPhase() * 0.75f))));
            tentacle.setOwner(this);
            tentacle.field_70177_z = this.field_70170_p.field_73012_v.nextFloat() * 360.0f;
            tentacle.field_70759_as = tentacle.field_70177_z;
            tentacle.func_70107_b(this.field_70165_t + ((double) getRandom(64.0f)), this.field_70163_u + ((double) func_70047_e()) + ((double) getRandom(10.0f)), this.field_70161_v + ((double) getRandom(64.0f)));
            soundClosePlayers(SoundInit.APPARITION_4, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f), tentacle.func_180425_c());
            this.field_70170_p.func_72838_d(tentacle);
            this.tentacles.add(tentacle);
        }
    }

    private void summonTurret() {
        if (this.attackTimer == 1) {
            sendMessage("summonTurret");
        }
        setAttackEnded(this.attackTimer >= 100);
        if (this.attackTimer % 15 == 0 && this.attackTimer <= 45) {
            this.turrets.removeIf(entityCthulhuTurret -> {
                return entityCthulhuTurret.field_70128_L;
            });
            if (this.turrets.size() < 5) {
                EntityCthulhuTurret turret = new EntityCthulhuTurret(this.field_70170_p);
                turret.setOwner(this);
                turret.func_70107_b(this.field_70165_t, this.field_70163_u + ((double) func_70047_e()), this.field_70161_v);
                Vec3d mot = LMath.fastNormalize(new Vec3d(getRandom(1.0f), 0.0d, getRandom(1.0f))).func_186678_a(5.0d);
                turret.field_70159_w = mot.field_72450_a;
                turret.field_70181_x = mot.field_72448_b;
                turret.field_70179_y = mot.field_72449_c;
                turret.field_70133_I = true;
                this.field_70170_p.func_72838_d(turret);
                this.turrets.add(turret);
                soundClosePlayers(SoundInit.EXECUTE_EFFECT, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.5f), turret.func_180425_c());
            }
        }
    }

    private void missileAttack() {
        if (this.attackTimer == 1) {
            if (this.tentaclePersists.size() == 0) {
                sendMessage("missileAttack");
            } else {
                sendMessage("missileAttack + tentacle buffed");
            }
            this.tentaclePersists.forEach((v0) -> {
                v0.cast();
            });
        }
        setAttackEnded(this.attackTimer >= 400);
        if (this.attackTimer % 10 == 0) {
            this.missiles.removeIf(missile -> {
                return missile.field_70128_L;
            });
            if (this.missiles.size() < 50 && !this.inRangePlayers.isEmpty()) {
                spawnMissileAt(this.field_70165_t + ((double) getRandom(24.0f)), this.field_70163_u, this.field_70161_v + ((double) getRandom(24.0f)));
                this.tentaclePersists.forEach(ent -> {
                    spawnMissileAt(ent.field_70165_t + ((double) getRandom(9.0f)), ent.field_70163_u, ent.field_70161_v + ((double) getRandom(9.0f)));
                });
            }
        }
    }

    private void spawnMissileAt(double x, double y, double z) {
        EntityCthulhuMissile missile = new EntityCthulhuMissile(this.field_70170_p);
        missile.setThrower(this);
        missile.setTarget(getRandomPlayer());
        missile.func_70107_b(x, y, z);
        missile.func_70186_c(0.0d, 1.0d, 0.0d, 2.0f, 0.0f);
        this.field_70170_p.func_72838_d(missile);
        soundClosePlayers(SoundInit.LASER_WEAPON_2, 1.0f, 0.6f + (this.field_70146_Z.nextFloat() * 0.8f), missile.func_180425_c());
        this.missiles.add(missile);
    }

    private void lightningStrike() {
        if (this.attackTimer == 1) {
            sendMessage("lightningStrike");
        }
        setAttackEnded(this.attackTimer >= 200);
        if (this.field_70170_p.func_73046_m() == null) {
            return;
        }
        if (this.attackTimer % 40 > 0 && this.attackTimer % 40 <= 25) {
            if (this.strikeLocations.isEmpty()) {
                List<EntityPlayerMP> nearbyPlayers = (List) this.field_70170_p.func_73046_m().func_184103_al().func_181057_v().stream().filter(player -> {
                    return player.func_70068_e(this) <= 12288.0d;
                }).collect(Collectors.toList());
                if (nearbyPlayers.isEmpty()) {
                    int strikeCount = this.field_70170_p.field_73012_v.nextInt(4) + 3;
                    for (int i = 0; i < strikeCount; i++) {
                        double x = (this.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
                        double z = (this.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
                        this.strikeLocations.add(new BlockPos(x, this.field_70170_p.func_175672_r(new BlockPos(x, 0.0d, z)).func_177956_o() + this.field_70170_p.field_73012_v.nextInt(20) + 5, z));
                        for (int j = 0; j < 3; j++) {
                            double x2 = (x + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d;
                            double z2 = (z + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d;
                            double y2 = this.field_70170_p.func_175672_r(new BlockPos(x2, 0.0d, z2)).func_177956_o() + this.field_70170_p.field_73012_v.nextInt(20) + 5;
                            this.strikeLocations.add(new BlockPos(x2, y2, z2));
                        }
                    }
                    return;
                }
                int strikeCount1 = this.field_70170_p.field_73012_v.nextInt(nearbyPlayers.size()) + 1;
                for (int i2 = 0; i2 < strikeCount1; i2++) {
                    EntityPlayer player2 = (EntityPlayer) nearbyPlayers.get(i2);
                    if (!player2.field_70128_L && !player2.func_175149_v()) {
                        this.strikeLocations.add(new BlockPos((player2.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(11))) - 5.0d, player2.field_70163_u, (player2.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(11))) - 5.0d));
                        for (int j2 = 0; j2 < 3; j2++) {
                            this.strikeLocations.add(new BlockPos((player2.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d, this.field_70170_p.func_175672_r(new BlockPos(r0, 0.0d, r0)).func_177956_o() + this.field_70170_p.field_73012_v.nextInt(20) + 5, (player2.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d));
                        }
                    }
                }
                for (int i3 = 0; i3 < 3; i3++) {
                    double x3 = (this.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
                    double z3 = (this.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
                    this.strikeLocations.add(new BlockPos(x3, this.field_70170_p.func_175672_r(new BlockPos(x3, 0.0d, z3)).func_177956_o() + this.field_70170_p.field_73012_v.nextInt(20) + 5, z3));
                    for (int j3 = 0; j3 < 3; j3++) {
                        double x22 = (x3 + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d;
                        double z22 = (z3 + ((double) this.field_70170_p.field_73012_v.nextInt(20))) - 10.0d;
                        double y22 = this.field_70170_p.func_175672_r(new BlockPos(x22, 0.0d, z22)).func_177956_o() + this.field_70170_p.field_73012_v.nextInt(20) + 5;
                        this.strikeLocations.add(new BlockPos(x22, y22, z22));
                    }
                }
                return;
            }
            if (this.attackTimer % 4 == 1) {
                for (BlockPos pos : this.strikeLocations) {
                    CustomParticleConfig config = new CustomParticleConfig();
                    config.createInstance().setParticle(ParticleInit.LIGHTNING_INDICATOR).setCount(1).setSpread(0.5d, 0.0d, 0.5d).setIgnoreRange(true);
                    for (int i4 = 0; i4 <= 5; i4++) {
                        IParticleSpawner.spawnParticle(this.field_70170_p, config, ((double) pos.func_177958_n()) + 0.5d, pos.func_177956_o() + i4, ((double) pos.func_177952_p()) + 0.5d);
                    }
                }
                return;
            }
            return;
        }
        if (this.attackTimer % 40 == 31) {
            for (BlockPos pos2 : this.strikeLocations) {
                int x4 = pos2.func_177958_n();
                int y = pos2.func_177956_o();
                int z4 = pos2.func_177952_p();
                this.field_70170_p.func_72942_c(new EntityLightningBolt(this.field_70170_p, x4, y, z4, true));
                List<EntityLivingBase> entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(x4 - 5, y - 5, z4 - 5, x4 + 5, y + 5, z4 + 5));
                for (EntityLivingBase entity : entities) {
                    if (!entity.equals(this) && !(entity instanceof ICthulhuMinion)) {
                        IMaxAttack.dealTrueDamage(this, entity, entity.func_110138_aP() * 0.3f);
                        entity.func_70015_d(10);
                    }
                }
            }
            this.strikeLocations.clear();
        }
    }

    private void openRifts() {
        double y;
        if (this.attackTimer == 1) {
            sendMessage("openRifts");
        }
        setAttackEnded(this.attackTimer >= 200);
        if (this.attackTimer != 1 || this.rifts.size() >= 10) {
            return;
        }
        int spawnCount = this.field_70170_p.field_73012_v.nextInt(5) + 4;
        int i = 0;
        while (i < spawnCount && this.rifts.size() < 10) {
            double x = (this.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
            double z = (this.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
            double dFunc_177956_o = this.field_70170_p.func_175672_r(new BlockPos(x, 0.0d, z)).func_177956_o();
            while (true) {
                y = dFunc_177956_o;
                if (y > 256.0d || this.field_70170_p.func_180495_p(new BlockPos(x, y, z)).func_177230_c() == Blocks.field_150350_a) {
                    break;
                } else {
                    dFunc_177956_o = y + 1.0d;
                }
            }
            double y2 = y + ((double) (this.field_70170_p.field_73012_v.nextInt(25) + 5));
            EntityCthulhuRift rift = new EntityCthulhuRift(this.field_70170_p);
            rift.setOwner(this);
            rift.func_70107_b(x, y2, z);
            if (rift.func_174813_aQ().func_72326_a(func_174813_aQ())) {
                i--;
            } else {
                boolean shouldContinue = false;
                Iterator<EntityCthulhuRift> it = this.rifts.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    EntityCthulhuRift other = it.next();
                    if (rift.func_174813_aQ().func_72326_a(other.func_174813_aQ())) {
                        i--;
                        shouldContinue = true;
                        break;
                    }
                }
                if (!shouldContinue) {
                    this.field_70170_p.func_72838_d(rift);
                    soundClosePlayers(SoundInit.RIFT_CREATE, 1.0f, 0.6f + (this.field_70146_Z.nextFloat() * 0.7f), rift.func_180425_c());
                    this.rifts.add(rift);
                }
            }
            i++;
        }
    }

    private void summonHealingOrb() {
        if (this.attackTimer == 1) {
            sendMessage("summonHealingOrb");
        }
        setAttackEnded(this.attackTimer >= 100);
        if (this.attackTimer == 1 && this.healingOrbs.size() < 3) {
            int amountToSpawn = this.field_70170_p.field_73012_v.nextInt(2) + 2;
            for (int i = 0; i < amountToSpawn && this.healingOrbs.size() < 3; i++) {
                EntityCthulhuHealingOrb orb = new EntityCthulhuHealingOrb(this.field_70170_p, this);
                orb.updatePos();
                this.field_70170_p.func_72838_d(orb);
                this.healingOrbs.add(orb);
            }
            soundPlayers(SoundInit.MAGIC_WEAPON_3, 1.0f, 0.5f + (this.field_70146_Z.nextFloat() * 0.6f));
        }
    }

    private void beamAttack() {
        EntityPlayer player;
        if (this.attackTimer == 1) {
            sendMessage("beamAttack");
        }
        setAttackEnded(this.attackTimer >= 200);
        if (this.attackTimer >= 200 || (player = getRandomPlayer()) == null) {
            return;
        }
        EntityCthulhuBeam beam = new EntityCthulhuBeam(this.field_70170_p);
        double x = getRandom(24.0f);
        double z = getRandom(24.0f);
        beam.func_70107_b(this.field_70165_t + x + (Math.signum(x) * 24.0d), this.field_70163_u + (((double) this.field_70131_O) * 0.5d) + ((double) getRandom(this.field_70131_O * 0.5f)), this.field_70161_v + z + (Math.signum(z) * 24.0d));
        beam.setDirection((float) ((((double) getRandom(10.0f)) + player.field_70165_t) - beam.field_70165_t), (float) ((((double) getRandom(10.0f)) + player.field_70163_u) - beam.field_70163_u), (float) ((((double) getRandom(10.0f)) + player.field_70161_v) - beam.field_70161_v));
        this.field_70170_p.func_72838_d(beam);
        if (this.field_70146_Z.nextBoolean()) {
            soundClosePlayers(SoundInit.LASER_WEAPON_9, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f), beam.func_180425_c());
        }
    }

    private void toxicCloud() {
        if (this.attackTimer == 1) {
            sendMessage("toxicCloud");
        }
        setAttackEnded(this.attackTimer >= 300);
        if (this.clouds.size() >= 5 || this.field_70170_p.func_73046_m() == null) {
            return;
        }
        List<EntityPlayerMP> nearbyPlayers = (List) this.field_70170_p.func_73046_m().func_184103_al().func_181057_v().stream().filter(player -> {
            return player.func_70068_e(this) <= 12288.0d;
        }).collect(Collectors.toList());
        if (nearbyPlayers.isEmpty()) {
            for (int i = 0; i < 3 && this.clouds.size() < 5; i++) {
                EntityCthulhuCloud cloud = new EntityCthulhuCloud(this.field_70170_p);
                cloud.setOwner(this);
                cloud.func_70107_b(this.field_70165_t + (func_70040_Z().field_72450_a * 2.0d), this.field_70163_u + ((double) func_70047_e()), this.field_70161_v + (func_70040_Z().field_72449_c * 2.0d));
                this.field_70170_p.func_72838_d(cloud);
                this.clouds.add(cloud);
                this.field_70170_p.func_184133_a((EntityPlayer) null, cloud.func_180425_c(), SoundInit.MAGIC_WEAPON_13, SoundCategory.HOSTILE, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
            }
            return;
        }
        Iterator<EntityPlayerMP> it = nearbyPlayers.iterator();
        while (it.hasNext()) {
            EntityLivingBase entityLivingBase = (EntityPlayer) it.next();
            boolean shouldContinue = false;
            Iterator<EntityCthulhuCloud> it2 = this.clouds.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                EntityCthulhuCloud cloud2 = it2.next();
                if (cloud2.func_70638_az() != null && cloud2.func_70638_az().equals(entityLivingBase)) {
                    shouldContinue = true;
                    break;
                }
            }
            if (!shouldContinue) {
                EntityCthulhuCloud cloud3 = new EntityCthulhuCloud(this.field_70170_p);
                cloud3.func_70624_b(entityLivingBase);
                cloud3.func_70107_b(this.field_70165_t + (func_70040_Z().field_72450_a * 2.0d), this.field_70163_u + ((double) func_70047_e()), this.field_70161_v + (func_70040_Z().field_72449_c * 2.0d));
                cloud3.setOwner(this);
                this.field_70170_p.func_72838_d(cloud3);
                this.clouds.add(cloud3);
                this.field_70170_p.func_184133_a((EntityPlayer) null, cloud3.func_180425_c(), SoundInit.MAGIC_WEAPON_13, SoundCategory.HOSTILE, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
            }
        }
    }

    private void blackHole() {
        double y;
        if (this.attackTimer == 1) {
            if (this.tentaclePersists.size() == 0) {
                sendMessage("blackHole");
            } else {
                sendMessage("blackHole + tentacle buffed");
            }
            this.tentaclePersists.forEach((v0) -> {
                v0.cast();
            });
        }
        setAttackEnded(this.attackTimer >= 300);
        if (this.attackTimer % 40 != 1 || this.blackHoles.size() >= 8 + (this.tentaclePersists.size() * 2)) {
            return;
        }
        int spawnCount = this.field_70170_p.field_73012_v.nextInt(3) + 1 + (this.tentaclePersists.size() * 2);
        int i = 0;
        while (i < spawnCount && this.blackHoles.size() < 8 + (this.tentaclePersists.size() * 2)) {
            double x = (this.field_70165_t + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
            double z = (this.field_70161_v + ((double) this.field_70170_p.field_73012_v.nextInt(100))) - 50.0d;
            double dFunc_177956_o = this.field_70170_p.func_175672_r(new BlockPos(x, 0.0d, z)).func_177956_o();
            while (true) {
                y = dFunc_177956_o;
                if (y > 256.0d || this.field_70170_p.func_180495_p(new BlockPos(x, y, z)).func_177230_c() == Blocks.field_150350_a) {
                    break;
                } else {
                    dFunc_177956_o = y + 1.0d;
                }
            }
            double y2 = y + ((double) (this.field_70170_p.field_73012_v.nextInt(25) + 5));
            EntityCthulhuBlackHole whirlpool = new EntityCthulhuBlackHole(this.field_70170_p);
            whirlpool.setOwner(this);
            whirlpool.func_70107_b(x, y2, z);
            if (whirlpool.func_174813_aQ().func_72326_a(func_174813_aQ())) {
                i--;
            } else {
                boolean shouldContinue = false;
                Iterator<EntityCthulhuBlackHole> it = this.blackHoles.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    EntityCthulhuBlackHole other = it.next();
                    if (whirlpool.func_174813_aQ().func_72326_a(other.func_174813_aQ())) {
                        i--;
                        shouldContinue = true;
                        break;
                    }
                }
                if (!shouldContinue) {
                    this.field_70170_p.func_72838_d(whirlpool);
                    soundClosePlayers(SoundInit.PORTAL_OPEN, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.3f), whirlpool.func_180425_c());
                    this.blackHoles.add(whirlpool);
                }
            }
            i++;
        }
    }

    private void summonLargeTentacle() {
        if (this.attackTimer == 1) {
            sendMessage("summonLargeTentacle");
            for (int i = 0; i < 2 && this.tentaclePersists.size() < 2; i++) {
                EntityCthulhuTentaclePersist tentacle = new EntityCthulhuTentaclePersist(this.field_70170_p);
                tentacle.setOwner(this);
                tentacle.teleportRandom();
                this.tentaclePersists.add(tentacle);
                this.field_70170_p.func_72838_d(tentacle);
                soundClosePlayers(SoundInit.APPARITION_4, 1.0f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f), tentacle.func_180425_c());
            }
        }
        setAttackEnded(this.attackTimer >= 100);
    }

    private void meteorShower() {
        if (this.attackTimer == 1) {
            sendMessage("meteorShower");
        }
        setAttackEnded(this.attackTimer >= 200);
        if (this.attackTimer % 20 == 1 && this.attackTimer <= 101) {
            for (int i = 0; i < 3; i++) {
                double tx = getRandom(1.0f);
                double tz = getRandom(1.0f);
                double s = MathHelper.func_181161_i((tx * tx) + (tz * tz)) * ((double) getRandom(48.0f));
                double tx2 = tx * s;
                double tz2 = tz * s;
                double tx3 = tx2 + this.field_70165_t;
                double tz3 = tz2 + this.field_70161_v;
                double ty = this.field_70170_p.func_189649_b((int) tx3, (int) tz3);
                EntityCthulhuMeteor meteor = new EntityCthulhuMeteor(this.field_70170_p);
                meteor.setThrower(this);
                meteor.func_70107_b(tx3 + 64.0d, ty + 128.0d, tz3 + 64.0d);
                meteor.func_70186_c(-1.0d, -2.0d, -1.0d, 3.0f, 0.0f);
                this.field_70170_p.func_72838_d(meteor);
            }
        }
    }

    private void sendMessage(String string) {
    }

    private void setAttackEnded(boolean flag) {
        this.canChangeAttack &= flag;
    }

    @Override // xol.lostinfinity.mob.entity.base.IConditionalDamage
    public boolean canBeDamaged(Entity attacker) {
        if (isBarrierActive()) {
            return false;
        }
        if (!(attacker instanceof EntityThrowable)) {
            return LMath.getDistanceSquaredToAABB(attacker.func_174791_d(), func_174813_aQ()) < 4096.0d;
        }
        Entity thrower = ((EntityThrowable) attacker).func_85052_h();
        if (thrower != null) {
            return LMath.getDistanceSquaredToAABB(thrower.func_174791_d(), func_174813_aQ()) < 4096.0d;
        }
        if (attacker instanceof EntityBaseThrowable) {
            thrower = ((EntityBaseThrowable) attacker).getSecondaryThrower();
        }
        return thrower != null ? LMath.getDistanceSquaredToAABB(thrower.func_174791_d(), func_174813_aQ()) < 4096.0d : LMath.getDistanceSquaredToAABB(attacker.func_174791_d(), func_174813_aQ()) < 4096.0d;
    }

    public void func_70106_y() {
        this.turrets.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.rifts.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.blackHoles.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.missiles.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.clouds.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.healingOrbs.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.tentacles.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.tentaclePersists.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.statues.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.turrets.clear();
        this.missiles.clear();
        this.rifts.clear();
        this.blackHoles.clear();
        this.clouds.clear();
        this.healingOrbs.clear();
        this.tentacles.clear();
        this.tentaclePersists.clear();
        this.statues.clear();
        for (Entity entity : this.parts) {
            this.field_70170_p.func_72973_f(entity);
        }
        if (!this.field_70170_p.field_72995_K) {
            EntityCthulhuDeathFX fx = new EntityCthulhuDeathFX(this.field_70170_p);
            fx.func_70107_b(this.field_70165_t, this.field_70163_u + (((double) this.field_70131_O) * 0.5d), this.field_70161_v);
            this.field_70170_p.func_72838_d(fx);
            soundPlayers(SoundInit.TT_DEATH, 1.0f, 1.0f);
        }
        super.func_70106_y();
    }

    public void essenceKill() {
        this.essenceCharged = true;
        func_70106_y();
    }

    public boolean isEssenceCharged() {
        return this.essenceCharged;
    }

    private void decrementBarrierTimers() {
        if (getBarrierCd1() > 0) {
            setBarrierCd1(getBarrierCd1() - 1);
        }
        if (getBarrierCd2() > 0) {
            setBarrierCd2(getBarrierCd2() - 1);
        }
        if (getBarrierCd3() > 0) {
            setBarrierCd3(getBarrierCd3() - 1);
        }
        if (getBarrierCd4() > 0) {
            setBarrierCd4(getBarrierCd4() - 1);
        }
    }

    public void checkBarrierDisable() {
        if (isBarrierActive() && getBarrierCd1() != 0 && getBarrierCd2() != 0 && getBarrierCd3() != 0 && getBarrierCd4() != 0) {
            setBarrierActive(false);
            setAllBarrierCd(0);
        }
    }

    public void setBarrierActive(boolean flag) {
        this.field_70180_af.func_187227_b(BARRIER, Boolean.valueOf(flag));
        if (flag) {
            func_70690_d(new PotionEffect(PotionInit.PROTECTED, Integer.MAX_VALUE, 0, false, false));
            knockbackWave();
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(60.0d))) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl.func_180425_c(), SoundInit.MAGIC_WEAPON_12, SoundCategory.HOSTILE, 1.0f, 1.0f);
            }
            EntityCelestialStatue statue = new EntityCelestialStatue(this.field_70170_p);
            statue.setOwner(this);
            statue.setType(getPhase() == 2 ? 0 : 2);
            statue.func_70634_a(this.field_70165_t + 80.0d, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(statue);
            this.statues.add(statue);
            EntityCelestialStatue statue2 = new EntityCelestialStatue(this.field_70170_p);
            statue2.setOwner(this);
            statue2.setType(getPhase() == 2 ? 1 : 3);
            statue2.func_70634_a(this.field_70165_t - 80.0d, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(statue2);
            this.statues.add(statue2);
            playAnimation("break", 0.33f);
            playAnimation("break_idle", 1.0f);
            return;
        }
        func_184589_d(PotionInit.PROTECTED);
        for (EntityPlayer near_pl2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(60.0d))) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, near_pl2.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.HOSTILE, 1.0f, 1.0f);
        }
        this.statues.forEach((v0) -> {
            v0.func_70106_y();
        });
        this.statues.clear();
        sendMessage("Barrier deactivated!");
        stopAnimation("break");
        stopAnimation("break_idle");
        playAnimation("recover", 1.0f);
    }

    public boolean isBarrierActive() {
        return ((Boolean) this.field_70180_af.func_187225_a(BARRIER)).booleanValue();
    }

    public void setBarrierCd1(int cd) {
        this.field_70180_af.func_187227_b(BARRIER_CD_1, Integer.valueOf(cd));
    }

    public int getBarrierCd1() {
        return ((Integer) this.field_70180_af.func_187225_a(BARRIER_CD_1)).intValue();
    }

    public void setBarrierCd2(int cd) {
        this.field_70180_af.func_187227_b(BARRIER_CD_2, Integer.valueOf(cd));
    }

    public int getBarrierCd2() {
        return ((Integer) this.field_70180_af.func_187225_a(BARRIER_CD_2)).intValue();
    }

    public void setBarrierCd3(int cd) {
        this.field_70180_af.func_187227_b(BARRIER_CD_3, Integer.valueOf(cd));
    }

    public int getBarrierCd3() {
        return ((Integer) this.field_70180_af.func_187225_a(BARRIER_CD_3)).intValue();
    }

    public void setBarrierCd4(int cd) {
        this.field_70180_af.func_187227_b(BARRIER_CD_4, Integer.valueOf(cd));
    }

    public int getBarrierCd4() {
        return ((Integer) this.field_70180_af.func_187225_a(BARRIER_CD_4)).intValue();
    }

    public void setAllBarrierCd(int cd) {
        setBarrierCd1(cd);
        setBarrierCd2(cd);
        setBarrierCd3(cd);
        setBarrierCd4(cd);
    }

    private float getRandom(float mul) {
        return (this.field_70170_p.field_73012_v.nextFloat() - 0.5f) * 2.0f * mul;
    }

    private EntityPlayer getRandomPlayer() {
        if (this.inRangePlayers.isEmpty()) {
            return null;
        }
        return this.inRangePlayers.get(this.field_70146_Z.nextInt(this.inRangePlayers.size()));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74768_a("phase", getPhase());
        tag.func_74757_a("barrier", isBarrierActive());
        tag.func_74757_a("activated", isActivated());
        tag.func_74768_a("currentAttack", this.currentAttack);
        tag.func_74768_a("attackTimer", this.attackTimer);
        NBTTagList list = new NBTTagList();
        this.rifts.forEach(entity -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity.func_110124_au()));
        });
        this.blackHoles.forEach(entity2 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity2.func_110124_au()));
        });
        this.turrets.forEach(entity3 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity3.func_110124_au()));
        });
        this.missiles.forEach(entity4 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity4.func_110124_au()));
        });
        this.clouds.forEach(entity5 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity5.func_110124_au()));
        });
        this.healingOrbs.forEach(entity6 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity6.func_110124_au()));
        });
        this.tentacles.forEach(entity7 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity7.func_110124_au()));
        });
        this.tentaclePersists.forEach(entity8 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity8.func_110124_au()));
        });
        this.statues.forEach(entity9 -> {
            list.func_74742_a(NBTUtil.func_186862_a(entity9.func_110124_au()));
        });
        tag.func_74782_a("minions", list);
    }

    @Nullable
    public Entity[] func_70021_al() {
        return this.parts;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        func_70106_y();
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        CTHULHUS.put(Integer.valueOf(func_145782_y()), this);
        if (this.field_70170_p.field_72995_K) {
            EventsClientRender.renderForce.put(Integer.valueOf(func_145782_y()), this);
            if (((Boolean) this.field_70180_af.func_187225_a(FIRST_SPAWN)).booleanValue()) {
                playAnimation("spawn", 1.0f);
            }
            playAnimation("idle", 1.0f);
            if (isBarrierActive()) {
                playAnimation("break_idle", 1.0f);
            }
        } else {
            func_70690_d(new PotionEffect(PotionInit.PROTECTED, 320, 0, false, false));
        }
        if (((Boolean) this.field_70180_af.func_187225_a(FIRST_SPAWN)).booleanValue()) {
            this.field_70180_af.func_187227_b(FIRST_SPAWN, false);
        }
    }

    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        CTHULHUS.remove(Integer.valueOf(func_145782_y()));
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setPhase(tag.func_74762_e("phase"));
        setBarrierActive(tag.func_74767_n("barrier"));
        setActivated(tag.func_74767_n("activated"));
        this.currentAttack = tag.func_74762_e("currentAttack");
        this.attackTimer = tag.func_74762_e("attackTimer");
        if (!this.field_70170_p.field_72995_K) {
            NBTTagList<NBTTagCompound> list = tag.func_150295_c("minions", 10);
            for (NBTTagCompound nBTTagCompound : list) {
                if (nBTTagCompound instanceof NBTTagCompound) {
                    UUID uuid = NBTUtil.func_186860_b(nBTTagCompound);
                    Entity entity = this.field_70170_p.func_73046_m().func_175576_a(uuid);
                    registerMinion(entity);
                }
            }
        }
    }

    @Override // xol.lostinfinity.util.animation.entity.IXolAnimated
    public AnimationHandler getAnimationHandler() {
        return this.handler;
    }

    public void registerMinion(Entity entity) {
        if (entity instanceof EntityCthulhuRift) {
            this.rifts.add((EntityCthulhuRift) entity);
            return;
        }
        if (entity instanceof EntityCthulhuBlackHole) {
            this.blackHoles.add((EntityCthulhuBlackHole) entity);
            return;
        }
        if (entity instanceof EntityCthulhuTurret) {
            this.turrets.add((EntityCthulhuTurret) entity);
            return;
        }
        if (entity instanceof EntityCthulhuMissile) {
            this.missiles.add((EntityCthulhuMissile) entity);
            return;
        }
        if (entity instanceof EntityCthulhuCloud) {
            this.clouds.add((EntityCthulhuCloud) entity);
            return;
        }
        if (entity instanceof EntityCthulhuHealingOrb) {
            this.healingOrbs.add((EntityCthulhuHealingOrb) entity);
            return;
        }
        if (entity instanceof EntityCthulhuTentaclePersist) {
            this.tentaclePersists.add((EntityCthulhuTentaclePersist) entity);
        } else if (entity instanceof EntityCthulhuTentacle) {
            this.tentacles.add((EntityCthulhuTentacle) entity);
        } else if (entity instanceof EntityCelestialStatue) {
            this.statues.add((EntityCelestialStatue) entity);
        }
    }

    public void setPhase(int phase) {
        this.field_70180_af.func_187227_b(PHASE, Integer.valueOf(phase));
    }

    public int getPhase() {
        return ((Integer) this.field_70180_af.func_187225_a(PHASE)).intValue();
    }

    public void setActivated(boolean flag) {
        this.field_70180_af.func_187227_b(ACTIVATED, Boolean.valueOf(flag));
    }

    public boolean isActivated() {
        return ((Boolean) this.field_70180_af.func_187225_a(ACTIVATED)).booleanValue();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public int numberOfLives() {
        return 15000;
    }

    @Override // xol.lostinfinity.mob.entity.classify.ILostMultiPart
    public boolean attackEntityFromPart(EntityLivingBase part, DamageSource source, float damage) {
        return func_70097_a(source, damage);
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }
}
