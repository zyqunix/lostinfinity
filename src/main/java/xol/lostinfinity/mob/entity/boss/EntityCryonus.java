package xol.lostinfinity.mob.entity.boss;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityCryoBeamEffect;
import xol.lostinfinity.projectile.entity.EntityCryoBolt;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityCryonus extends EntityFloatingBase implements IMaxAttack {
    private int phase;
    private int nextFormTimer;
    EntityCryoBeamEffect beam;
    EntityCryoBeamEffect blast;
    private boolean givenFrozenMessage;
    public EntityCryonus(World worldIn) {
        super(worldIn);
        this.nextFormTimer = 200;
        this.beam = null;
        this.blast = null;
        this.givenFrozenMessage = false;
        func_70105_a(2.0f, 7.0f);
        this.rawFlySpeed = 0.95f;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
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
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (getLivesCount() >= numberOfLives() / 2) {
            freeze();
        }
        if (!this.field_70170_p.field_72995_K) {
            if (this.phase == 0) {
                shoot();
            } else if (this.phase == 1) {
                if (this.beam == null) {
                    cryoBeam();
                    soundPlayers(SoundInit.CRYONUS_ICEBEAM, 1.0f);
                } else if (this.field_70173_aa % 50 == 0) {
                    soundPlayers(SoundInit.CRYONUS_ICEBEAM, 1.0f);
                }
            } else if (this.phase == 2) {
                iceSmash();
            }
            updatePhase(this.phase);
        }
    }
    private void freeze() {
        AxisAlignedBB arena = getArenaAABB();
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 10; i++) {
                double randX = (this.field_70170_p.field_73012_v.nextDouble() * (arena.field_72336_d - arena.field_72340_a)) + arena.field_72340_a;
                double randY = (this.field_70170_p.field_73012_v.nextDouble() * (arena.field_72337_e - arena.field_72338_b)) + arena.field_72338_b;
                double randZ = (this.field_70170_p.field_73012_v.nextDouble() * (arena.field_72334_f - arena.field_72339_c)) + arena.field_72339_c;
                this.field_70170_p.func_175688_a(ParticleInit.SNOW_BUBBLE, randX, randY, randZ, 0.0d, 0.0d, 0.0d, new int[0]);
            }
            return;
        }
        if (!this.givenFrozenMessage) {
            soundPlayers(SoundInit.WIND_GUST, 1.0f);
            messagePlayers(TextFmt.Aqua + "Cryonus: The world freezes... I am the only warmth.");
            this.givenFrozenMessage = true;
        }
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            double dist = Math.sqrt(((this.field_70165_t - contender.field_70165_t) * (this.field_70165_t - contender.field_70165_t)) + ((this.field_70161_v - contender.field_70161_v) * (this.field_70161_v - contender.field_70161_v)));
            if (!contender.func_184812_l_() && this.field_70173_aa % 20 == 0) {
                if (dist > 20.0d) {
                    dist = 20.0d;
                }
                int denom = (int) (40.0d / dist);
                IMaxAttack.dealMaxHealth(this, contender, denom);
            }
        }
    }
    private void cryoBeam() {
        EntityPlayer target = null;
        Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
        if (it.hasNext()) {
            EntityPlayer contender = (EntityPlayer) it.next();
            target = contender;
        }
        if (target != null) {
            this.beam = new EntityCryoBeamEffect(this.field_70170_p);
            this.beam.setOwner(this);
            this.beam.setTarget(target);
            this.beam.func_70107_b(this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
            this.field_70170_p.func_72838_d(this.beam);
        }
    }
    private void iceSmash() {
        if (this.blast == null || this.blast.getGrowth() > 20.2f) {
            EntityPlayer target = null;
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
            if (it.hasNext()) {
                EntityPlayer contender = (EntityPlayer) it.next();
                target = contender;
            }
            this.blast = new EntityCryoBeamEffect(this.field_70170_p);
            this.blast.setIceBlast(true);
            this.blast.setOwner(this);
            this.blast.setTarget(target);
            this.blast.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(this.blast);
            soundPlayers(SoundInit.CRYONUS_RINGS, 0.7f);
        }
    }
    private void updatePhase(int phase) {
        this.nextFormTimer--;
        if (this.nextFormTimer == 0) {
            this.nextFormTimer = 200;
            if (this.beam != null) {
                this.beam.func_70106_y();
                this.beam = null;
            }
            if (this.blast != null) {
                this.blast.func_70106_y();
                this.blast = null;
            }
            if (phase < 2) {
                this.phase++;
            } else {
                this.phase = 0;
            }
        }
    }
    private void shoot() {
        boolean fired = false;
        Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
        if (it.hasNext()) {
            EntityPlayer near_pl = (EntityPlayer) it.next();
            if (!near_pl.func_184812_l_() && this.field_70173_aa % 10 == 0) {
                fired = true;
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = near_pl.field_70165_t - makeX;
                double d3 = near_pl.field_70163_u - makeY;
                double d4 = near_pl.field_70161_v - makeZ;
                EntityCryoBolt shot = new EntityCryoBolt(this.field_70170_p, this);
                shot.func_70186_c(d2, d3, d4, 3.0f, 0.0f);
                shot.setThrower(this);
                this.field_70170_p.func_72838_d(shot);
            }
        }
        if (fired) {
            SoundEvent wepSound = SoundInit.MAGIC_WEAPON_1;
            soundPlayers(wepSound, 0.25f);
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.CRYONUS_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.CRYONUS_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.CRYONUS_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 200;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "Cryonus is at " + lifePercent + "% health.");
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
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.beam != null) {
                this.beam.func_70106_y();
            }
            if (this.blast != null) {
                this.blast.func_70106_y();
            }
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 13);
            stone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(stone);
            func_145779_a(ItemInit.arenaCard, 1);
        }
    }
    protected boolean func_70692_ba() {
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
}
