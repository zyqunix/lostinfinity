package xol.lostinfinity.mob.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCaveSpider;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantSpider;
import xol.lostinfinity.projectile.entity.EntityGloomSpell;
import xol.lostinfinity.projectile.entity.EntitySpiderBlast;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityVycellia.class */
public class EntityVycellia extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Boolean> EASY_FORM = EntityDataManager.func_187226_a(EntityVycellia.class, DataSerializers.field_187198_h);
    private static final DataParameter<Integer> SUBSTAGE = EntityDataManager.func_187226_a(EntityVycellia.class, DataSerializers.field_187192_b);
    private float secondFormScale;

    public EntityVycellia(World worldIn) {
        super(worldIn);
        this.secondFormScale = 0.0f;
        func_70105_a(3.5f, 4.5f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(EASY_FORM, true);
        this.field_70180_af.func_187214_a(SUBSTAGE, 0);
    }

    public boolean isEasy() {
        return ((Boolean) this.field_70180_af.func_187225_a(EASY_FORM)).booleanValue();
    }

    public void setEasy(boolean f) {
        this.field_70180_af.func_187227_b(EASY_FORM, Boolean.valueOf(f));
    }

    public int getSubstage() {
        return ((Integer) this.field_70180_af.func_187225_a(SUBSTAGE)).intValue();
    }

    public void setSubstage(int f) {
        this.field_70180_af.func_187227_b(SUBSTAGE, Integer.valueOf(f));
    }

    public float getFormScale() {
        return this.secondFormScale;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("EasyForm", isEasy());
        tag.func_74768_a("Substage", getSubstage());
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        setEasy(tag.func_74767_n("EasyForm"));
        setSubstage(tag.func_74762_e("Substage"));
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            return true;
        }
        return false;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }

    private void fireBlast(float speed) {
        boolean fired = false;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            if (!near_pl.func_184812_l_()) {
                fired = true;
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = near_pl.field_70165_t - makeX;
                double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                if (!isEasy()) {
                    d3 -= 3.0d;
                }
                double d4 = near_pl.field_70161_v - makeZ;
                EntitySpiderBlast shot = new EntitySpiderBlast(this.field_70170_p, this);
                shot.func_70186_c(d2, d3, d4, speed, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
        }
        if (fired) {
            soundPlayers(SoundInit.MAGIC_WEAPON_6);
        }
    }

    private void fireGloom(float speed, float ydir) {
        EntityGloomSpell shot = new EntityGloomSpell(this.field_70170_p, this);
        shot.func_70186_c(randomVeloDouble(), ydir, randomVeloDouble(), speed, 0.0f);
        this.field_70170_p.func_72838_d(shot);
        soundPlayers(SoundInit.MAGIC_WEAPON_8);
    }

    private double randomVeloDouble() {
        return this.field_70146_Z.nextDouble() - 0.5d;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 10 == 0) {
            immunityCheck();
        }
        if (!isEasy()) {
            if (this.secondFormScale < 5.0f) {
                this.secondFormScale += 0.05f;
            } else if (!this.field_70170_p.field_72995_K && getSubstage() == 0) {
                setSubstage(1);
            }
            func_70105_a((7.5f * this.secondFormScale) / 5.0f, (11.0f * this.secondFormScale) / 5.0f);
            if (!this.field_70170_p.field_72995_K) {
                int form = getSubstage();
                if (form == 0) {
                    if (this.field_70173_aa == 8) {
                        AxisAlignedBB aabb = getArenaAABB();
                        double xSpawn = (aabb.field_72340_a + aabb.field_72336_d) / 2.0d;
                        double ySpawn = aabb.field_72338_b + 5.0d;
                        double zSpawn = (aabb.field_72339_c + aabb.field_72334_f) / 2.0d;
                        for (int i = 0; i < 15; i++) {
                            EntitySpider spider = new EntitySpider(this.field_70170_p);
                            spider.func_70107_b((xSpawn - 2.0d) + (this.field_70146_Z.nextDouble() * 4.0d), ySpawn, (zSpawn - 2.0d) + (this.field_70146_Z.nextDouble() * 4.0d));
                            this.field_70170_p.func_72838_d(spider);
                        }
                    }
                    if (this.field_70173_aa % 5 == 0) {
                        popSpider();
                        return;
                    }
                    return;
                }
                if (this.field_70173_aa % 400 == 150) {
                    spawnSpiders(true, true);
                }
                if (this.field_70173_aa % 150 == 0) {
                    if (form == 1) {
                        setSubstage(2);
                        return;
                    } else {
                        setSubstage(1);
                        return;
                    }
                }
                if (form == 1) {
                    if (this.field_70173_aa % 15 == 0) {
                        fireBlast(2.5f);
                        return;
                    }
                    return;
                } else {
                    if (this.field_70173_aa % 3 == 0) {
                        fireGloom(1.0f, 0.0f);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (!this.field_70170_p.field_72995_K) {
            int form2 = getSubstage();
            if (this.field_70173_aa % 500 == 250) {
                spawnSpiders(false, true);
            }
            if (this.field_70173_aa % 350 == 0) {
                if (form2 == 0) {
                    setSubstage(1);
                    func_189654_d(true);
                    return;
                } else {
                    setSubstage(0);
                    func_189654_d(false);
                    return;
                }
            }
            if (form2 == 0) {
                if (this.field_70173_aa % 30 == 0) {
                    fireBlast(1.5f);
                    return;
                }
                return;
            }
            if (this.field_70163_u < 70.0d) {
                this.field_70181_x = 0.1d;
            } else {
                this.field_70181_x = 0.0d;
            }
            this.field_70160_al = true;
            this.field_70133_I = true;
            if (this.field_70173_aa % 4 == 0) {
                fireGloom(1.0f, -0.1f);
            }
        }
    }

    private void spawnSpiders(boolean cave, boolean sound) {
        if (spiderCount() < 15) {
            int repeats = 2 + this.field_70146_Z.nextInt(3);
            for (int i = 0; i < repeats; i++) {
                if (cave && this.field_70146_Z.nextBoolean()) {
                    EntityDeviantCaveSpider devspider = new EntityDeviantCaveSpider(this.field_70170_p);
                    devspider.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(devspider);
                } else {
                    EntityDeviantSpider devspider2 = new EntityDeviantSpider(this.field_70170_p);
                    devspider2.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    this.field_70170_p.func_72838_d(devspider2);
                }
            }
            if (sound) {
                soundPlayers(SoundInit.SPIDER_UNLEASH);
            }
            messagePlayers(TextFmt.Dark_Aqua + "Vycellia: While my spiders live, I cannot die!");
        }
    }

    private int spiderCount() {
        int count = 0;
        for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, getArenaAABB())) {
            if ((entity instanceof EntityDeviantSpider) || (entity instanceof EntityDeviantCaveSpider)) {
                count++;
            }
        }
        return count;
    }

    private void immunityCheck() {
        if (spiderCount() > 0) {
            func_70690_d(new PotionEffect(PotionInit.PROTECTED, 11));
        }
    }

    private void popSpider() {
        for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, getArenaAABB())) {
            if (entity instanceof EntitySpider) {
                entity.func_70106_y();
                soundPlayers(SoundInit.MAGIC_WEAPON_7);
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.createInstance().setParticle(ParticleInit.GLOOM_BURST).setSpread(1.0d, 1.0d, 1.0d).setCount(3).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(entity.field_70170_p, config1, entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
                return;
            }
        }
    }

    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187819_fL;
    }

    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187821_fM;
    }

    protected SoundEvent func_184639_G() {
        return SoundEvents.field_187817_fK;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 100;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "Vycellia is at " + lifePercent + "% health.");
    }

    protected void messagePlayers(String message) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(message));
        }
    }

    protected void soundPlayers(SoundEvent sound) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, 0.75f, 0.9f + (this.field_70146_Z.nextFloat() * 0.2f));
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            if (isEasy()) {
                EntityVycellia rematch = new EntityVycellia(this.field_70170_p);
                AxisAlignedBB aabb = getArenaAABB();
                rematch.func_70107_b((aabb.field_72340_a + aabb.field_72336_d) / 2.0d, aabb.field_72338_b + 5.0d, (aabb.field_72339_c + aabb.field_72334_f) / 2.0d);
                rematch.setEasy(false);
                this.field_70170_p.func_72838_d(rematch);
                messagePlayers(TextFmt.Light_Purple + "Vycellia: I told you, my army is Eteneral!");
                return;
            }
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 10);
            stone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(stone);
            func_145779_a(ItemInit.arenaCard, 1);
        }
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
}
