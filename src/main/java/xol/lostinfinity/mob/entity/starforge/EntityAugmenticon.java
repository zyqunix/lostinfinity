package xol.lostinfinity.mob.entity.starforge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.IBasicAI;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityClusterBlast;
import xol.lostinfinity.projectile.entity.EntityExplosiveGoo;
import xol.lostinfinity.projectile.entity.EntityTetherBall;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.load.LootTableRegistry;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/starforge/EntityAugmenticon.class */
public class EntityAugmenticon extends EntityMultipleLives implements IMaxAttack, IBasicAI {
    private List<String> abilities;
    private String lastAbility;
    private boolean lastTextBlue;

    public EntityAugmenticon(World worldIn) {
        super(worldIn);
        this.abilities = new ArrayList();
        this.lastAbility = "none";
        this.lastTextBlue = false;
        func_70105_a(3.0f, 3.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        initBasicTasks(this);
    }

    private int getAbilityTimer() {
        return Math.max(80 - (5 * this.abilities.size()), 20);
    }

    public void addAbility(String ability) {
        this.abilities.add(ability);
    }

    private String getRandomAbility() {
        int pick = this.field_70146_Z.nextInt(this.abilities.size());
        return this.abilities.get(pick);
    }

    private AxisAlignedBB getArenaAABB() {
        return GalaxyCoordinates.getAugmenticonAABB();
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        AxisAlignedBB pullBox = new AxisAlignedBB(func_180425_c()).func_186662_g(45.0d);
        for (EntityPlayer entity : this.field_70170_p.func_72872_a(EntityPlayer.class, pullBox)) {
            entity.func_145747_a(new TextComponentString(TextFmt.Gold + "The Augmenticon is at " + lifePercent + "% health."));
        }
    }

    private void doAbility(String abilityName) {
        this.lastAbility = abilityName;
        switch (abilityName) {
            case "dash":
                abilityDash();
                break;
            case "slam":
                abilitySlam();
                break;
            case "invisibility":
                abilityInvisibility();
                break;
            case "forcefield":
                abilityForcefield();
                break;
            case "teleport":
                abilityTeleport();
                break;
            case "heal":
                abilityHeal();
                break;
            case "hurt":
                abilityHurt();
                break;
            case "summon":
                abilitySummon();
                break;
            case "blight":
                abilityBlight();
                break;
            case "plague":
                abilityPlague();
                break;
            case "emp":
                abilityEMP();
                break;
            case "shatter":
                abilityShatter();
                break;
            case "tether":
                abilityTether();
                break;
            case "unleash":
                abilityUnleash();
                break;
            case "gravity":
                abilityGravity();
                break;
            case "nightmares":
                abilityNightmares();
                break;
            case "explosive":
                abilityExplosive();
                break;
            case "trailing":
                abilityTrailing();
                break;
            case "destructive":
                abilityDestructive();
                break;
            case "regenerative":
                abilityRegenerative();
                break;
        }
    }

    private void abilityRegenerative() {
        messagePlayers("REGENERATIVE");
        int maxLives = numberOfLives();
        int curLives = remainingLives();
        if ((maxLives / 10) + curLives < maxLives) {
            setLivesCount((maxLives / 10) + curLives);
        } else {
            setLivesCount(maxLives);
        }
    }

    private void abilityDestructive() {
        messagePlayers("DESTRUCTIVE");
    }

    private void abilityTrailing() {
        messagePlayers("TRAILING");
    }

    private void abilityExplosive() {
        messagePlayers("EXPLOSIVE");
        if (func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70024_g((target.field_70165_t - this.field_70165_t) * 0.145d, 0.6d, (target.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
    }

    private void abilityNightmares() {
        messagePlayers("NIGHTMARES");
        BlockPos spawn1 = new BlockPos(1575, 10, -540);
        BlockPos spawn2 = new BlockPos(1565, 10, -556);
        BlockPos spawn3 = new BlockPos(1578, 10, -563);
        EntitySightwalker walker1 = new EntitySightwalker(this.field_70170_p);
        walker1.func_70634_a(spawn1.func_177958_n(), spawn1.func_177956_o(), spawn1.func_177952_p());
        this.field_70170_p.func_72838_d(walker1);
        EntitySightwalker walker2 = new EntitySightwalker(this.field_70170_p);
        walker2.func_70634_a(spawn2.func_177958_n(), spawn2.func_177956_o(), spawn2.func_177952_p());
        this.field_70170_p.func_72838_d(walker2);
        EntityRavager ravager = new EntityRavager(this.field_70170_p);
        ravager.func_70634_a(spawn3.func_177958_n(), spawn3.func_177956_o(), spawn3.func_177952_p());
        this.field_70170_p.func_72838_d(ravager);
    }

    private void abilityGravity() {
        messagePlayers("GRAVITY");
        func_70690_d(new PotionEffect(PotionInit.GRAVITATIONAL, 100, 4));
    }

    private void abilityUnleash() {
        messagePlayers("UNLEASH");
        func_70690_d(new PotionEffect(PotionInit.UNLEASHING, 200, 4));
    }

    private void abilityTether() {
        messagePlayers("TETHER");
    }

    private void abilityShatter() {
        messagePlayers("SHATTER");
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_70690_d(new PotionEffect(PotionInit.SHATTERED, 200, 3));
        }
    }

    private void abilityEMP() {
        messagePlayers("EMP");
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 200));
        }
    }

    private void abilityPlague() {
        messagePlayers("PLAGUE");
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_70690_d(new PotionEffect(PotionInit.PLAGUE, getAbilityTimer(), 4));
        }
    }

    private void abilityBlight() {
        messagePlayers("BLIGHT");
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            near_pl.func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 200, 5));
        }
    }

    private void abilityTeleport() {
        messagePlayers("TELEPORT");
        Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
        if (it.hasNext()) {
            EntityPlayer near_pl = (EntityPlayer) it.next();
            func_70634_a(near_pl.field_70165_t, near_pl.field_70163_u, near_pl.field_70161_v);
        }
    }

    private void abilityHeal() {
        messagePlayers("HEAL");
        setLivesCount(Math.max(getLivesCount() - 5, 0));
    }

    private void abilityHurt() {
        messagePlayers("HURT");
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            IMaxAttack.dealMaxHealth(this, near_pl, 2);
        }
    }

    private void abilitySummon() {
        messagePlayers("SUMMON");
        EntityGloboon globoon = new EntityGloboon(this.field_70170_p);
        globoon.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70170_p.func_72838_d(globoon);
    }

    private void abilityDash() {
        messagePlayers("DASH");
        if (func_70638_az() != null) {
            EntityLivingBase target = func_70638_az();
            func_70024_g((target.field_70165_t - this.field_70165_t) * 0.145d, 0.6d, (target.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
    }

    private void abilitySlam() {
        messagePlayers("GROUND SLAM");
        func_70024_g(0.0d, 2.0d, 0.0d);
        this.field_70133_I = true;
    }

    private void abilityInvisibility() {
        messagePlayers("INVISIBLE");
        func_70690_d(new PotionEffect(MobEffects.field_76441_p, getAbilityTimer()));
    }

    private void abilityForcefield() {
        messagePlayers("DESTROY PROJECTILES");
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    public void func_70636_d() {
        EntityLivingBase target;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 5) {
            if (func_70638_az() == null) {
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
                if (it.hasNext()) {
                    EntityPlayer near_pl = (EntityPlayer) it.next();
                    func_70624_b(near_pl);
                }
            }
            if (this.abilities.isEmpty()) {
                func_70106_y();
                return;
            }
            if (this.field_70173_aa % getAbilityTimer() == 0) {
                doAbility(getRandomAbility());
            }
            if (this.lastAbility.equals("forcefield")) {
                for (Entity detected : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(15.0d, 10.0d, 15.0d))) {
                    if ((detected instanceof EntityThrowable) || (detected instanceof EntityArrow)) {
                        detected.func_70106_y();
                        detected.func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
                        this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, detected.field_70165_t, detected.field_70163_u, detected.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                        Iterator it2 = this.field_70170_p.func_72872_a(EntityPlayer.class, detected.func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d)).iterator();
                        while (it2.hasNext()) {
                            IMaxAttack.dealMaxHealth(this, (EntityPlayer) it2.next(), 1);
                        }
                    }
                }
            }
            if (this.lastAbility.equals("tether") && (this.field_70173_aa % getAbilityTimer() == 0 || this.field_70173_aa % getAbilityTimer() == getAbilityTimer() / 3 || this.field_70173_aa % getAbilityTimer() == (2 * getAbilityTimer()) / 3)) {
                double d = 0.0d;
                while (true) {
                    double i = d;
                    if (i >= 6.283185307179586d) {
                        break;
                    }
                    double x = Math.cos(i);
                    double z = Math.sin(i);
                    EntityTetherBall shot = new EntityTetherBall(this.field_70170_p, (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d, this.field_70163_u + 1.0d, (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d);
                    shot.setThrower(this);
                    shot.func_70186_c(x, 0.0d, z, 1.0f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                    d = i + 0.7853981633974483d;
                }
            }
            if (this.lastAbility.equals("explosive") && this.field_70173_aa % getAbilityTimer() == getAbilityTimer() / 4) {
                for (EntityPlayer target2 : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(8.0d, 8.0d, 8.0d))) {
                    IMaxAttack.dealTrueDamage(this, target2, target2.func_110138_aP() * 0.75f);
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(7.0d, 1.0d, 7.0d).setCount(4).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_184148_a((EntityPlayer) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundInit.GENERIC_WEAPON_6, SoundCategory.HOSTILE, 1.0f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
            }
            if (this.lastAbility.equals("trailing") && this.field_70173_aa % 3 == 0 && this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_185914_p() && this.field_70170_p.func_180495_p(func_180425_c()).func_185904_a().func_76222_j()) {
                this.field_70170_p.func_175656_a(func_180425_c(), BlockInit.acidicGel.func_176223_P());
            }
            if (this.lastAbility.equals("destructive") && this.field_70173_aa % 10 == 0 && (target = func_70638_az()) != null) {
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + 0.6d;
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = target.field_70165_t - makeX;
                double d3 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 2.0f))) - makeY;
                double d4 = target.field_70161_v - makeZ;
                if (this.field_70146_Z.nextBoolean()) {
                    EntityExplosiveGoo shot2 = new EntityExplosiveGoo(this.field_70170_p, makeX, makeY, makeZ);
                    shot2.setThrower(this);
                    shot2.setDenomAndSize(5, 3);
                    shot2.func_70186_c(d2, d3, d4, 1.5f, 0.0f);
                    this.field_70170_p.func_72838_d(shot2);
                    CustomParticleConfig config12 = new CustomParticleConfig();
                    config12.createInstance().setParticle(ParticleInit.FIREGOO).setSpread(1.0d, 1.0d, 1.0d).setSpeed(1.0d, 1.0d, 1.0d).setVelSpread(1.0d, 1.0d, 1.0d).setCount(8).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config12, makeX, makeY, makeZ);
                    this.field_70170_p.func_184148_a((EntityPlayer) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundInit.GALAXYFIRE, SoundCategory.HOSTILE, 2.0f, 1.0f);
                    return;
                }
                EntityClusterBlast shot3 = new EntityClusterBlast(this.field_70170_p, makeX, makeY, makeZ);
                shot3.setThrower(this);
                shot3.func_70186_c(d2, d3 + 7.0d, d4, 1.5f, 0.0f);
                this.field_70170_p.func_72838_d(shot3);
                CustomParticleConfig config13 = new CustomParticleConfig();
                config13.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(this.field_70170_p, config13, makeX, makeY, makeZ);
                this.field_70170_p.func_184148_a((EntityPlayer) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundInit.CLUSTER_FIRE, SoundCategory.HOSTILE, 2.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
            }
        }
    }

    public void func_180430_e(float distance, float damageMultiplier) {
        if (!this.field_70170_p.field_72995_K && this.lastAbility.equals("slam")) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(6.0d, 6.0d, 6.0d))) {
                IMaxAttack.dealMaxHealth(this, near_pl, 2);
            }
            this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, (this.field_70165_t - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), this.field_70163_u + 0.5d, (this.field_70161_v - 1.0d) + (this.field_70146_Z.nextDouble() * 2.0d), 5, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
        }
    }

    private void messagePlayers(String message) {
        this.lastTextBlue = !this.lastTextBlue;
        func_184185_a(this.lastTextBlue ? SoundInit.AUGMENTICON_TALK1 : SoundInit.AUGMENTICON_TALK2, 1.0f, 0.75f + (this.field_70146_Z.nextFloat() * 0.5f));
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(30.0d))) {
            contender.func_145747_a(new TextComponentString((this.lastTextBlue ? TextFmt.Dark_Aqua : TextFmt.Gold) + "Augmenticon: " + message));
        }
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }

    protected SoundEvent func_184615_bR() {
        return SoundInit.AUGMENTICON_DEATH;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.AUGMENTICON_HURT;
    }

    protected SoundEvent func_184639_G() {
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 100;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return lootTableQuality();
        }
        return null;
    }

    private ResourceLocation lootTableQuality() {
        int abilityCount = this.abilities.size();
        if (abilityCount < 4) {
            return LootTableRegistry.ENTITIES_AUGMENTICON_0;
        }
        if (abilityCount < 8) {
            return LootTableRegistry.ENTITIES_AUGMENTICON_1;
        }
        if (abilityCount < 12) {
            return LootTableRegistry.ENTITIES_AUGMENTICON_2;
        }
        if (abilityCount < 16) {
            return LootTableRegistry.ENTITIES_AUGMENTICON_3;
        }
        if (abilityCount < 20) {
            return LootTableRegistry.ENTITIES_AUGMENTICON_4;
        }
        return LootTableRegistry.ENTITIES_AUGMENTICON_5;
    }
}
