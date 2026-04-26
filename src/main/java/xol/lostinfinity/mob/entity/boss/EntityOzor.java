package xol.lostinfinity.mob.entity.boss;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityLaserBlast;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityOzor extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Integer> ILLUSION_PHASE = EntityDataManager.func_187226_a(EntityOzor.class, DataSerializers.field_187192_b);
    private BlockPos hoverTo;
    private int fireMode;
    private int nextFormTimer;
    private int illusionCooldown;
    private float ozorAlpha;
    public EntityOzor(World worldIn) {
        super(worldIn);
        this.hoverTo = null;
        this.fireMode = 0;
        this.nextFormTimer = 200;
        this.illusionCooldown = 300;
        this.ozorAlpha = 1.0f;
        func_70105_a(7.5f, 7.5f);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(ILLUSION_PHASE, 0);
    }
    public int getIllusionPhase() {
        return ((Integer) this.field_70180_af.func_187225_a(ILLUSION_PHASE)).intValue();
    }
    public void setIllusionPhase(int f) {
        this.field_70180_af.func_187227_b(ILLUSION_PHASE, Integer.valueOf(f));
    }
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
    public float getAlpha() {
        return this.ozorAlpha;
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }
    private void fireLaser(int attackType) {
        if (!this.field_70170_p.field_72995_K) {
            boolean fired = false;
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
            if (it.hasNext()) {
                EntityPlayer near_pl = (EntityPlayer) it.next();
                if (!near_pl.func_184812_l_()) {
                    fired = true;
                    func_70676_i(1.0f);
                    double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                    double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                    double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                    double d2 = near_pl.field_70165_t - makeX;
                    double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                    double d4 = near_pl.field_70161_v - makeZ;
                    EntityLaserBlast shot = new EntityLaserBlast(this.field_70170_p, this);
                    float speed = 2.0f;
                    if (attackType == 1) {
                        speed = 3.0f;
                    } else if (attackType == 2) {
                        speed = 2.5f;
                    }
                    shot.func_70186_c(d2, d3, d4, speed, attackType == 2 ? 8.0f : 0.0f);
                    shot.setForm(attackType);
                    shot.setThrower(this);
                    this.field_70170_p.func_72838_d(shot);
                    this.field_70170_p.func_175739_a(EnumParticleTypes.LAVA, shot.field_70165_t, shot.field_70163_u, shot.field_70161_v, 2, this.field_70146_Z.nextDouble() * 3.0d, 0.3d, this.field_70146_Z.nextDouble() * 3.0d, 0.15000000596046448d, new int[0]);
                }
            }
            if (fired) {
                SoundEvent wepSound = SoundInit.LASER_WEAPON_5;
                if (attackType == 1) {
                    wepSound = SoundInit.LASER_WEAPON_4;
                } else if (attackType == 2) {
                    wepSound = SoundInit.LASER_WEAPON_3;
                }
                soundPlayers(wepSound, attackType == 2 ? 0.25f : 0.75f);
            }
        }
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            int phase = getIllusionPhase();
            if (phase == 0) {
                if (this.hoverTo == null) {
                    findNewMove();
                } else if (func_70011_f(this.hoverTo.func_177958_n(), this.hoverTo.func_177956_o(), this.hoverTo.func_177952_p()) > 1.0d && this.field_70159_w > -0.699999988079071d && this.field_70159_w < 0.699999988079071d && this.field_70179_y > -0.699999988079071d && this.field_70179_y < 0.699999988079071d) {
                    func_70024_g((((double) this.hoverTo.func_177958_n()) - this.field_70165_t) * 0.03d, (((double) this.hoverTo.func_177956_o()) - this.field_70163_u) * 0.02d, (((double) this.hoverTo.func_177952_p()) - this.field_70161_v) * 0.03d);
                    this.field_70133_I = true;
                }
                if (this.field_70173_aa % 70 == 0) {
                    findNewMove();
                }
                this.nextFormTimer--;
                switch (this.fireMode) {
                    case 0:
                        if (this.field_70173_aa % 30 == 0) {
                            fireLaser(0);
                        }
                        if (this.nextFormTimer == 0) {
                            this.fireMode = 1;
                            this.nextFormTimer = 300;
                        } else if (this.nextFormTimer == 20) {
                            messagePlayers(TextFmt.Red + "Ozor: High Powdered Lasers!");
                            soundPlayers(SoundInit.OZOR_MESSAGE, 1.0f);
                        }
                        break;
                    case 1:
                        if (this.field_70173_aa % 50 == 0) {
                            fireLaser(1);
                        }
                        if (this.nextFormTimer == 0) {
                            this.fireMode = 2;
                            this.nextFormTimer = 120;
                        } else if (this.nextFormTimer == 20) {
                            messagePlayers(TextFmt.Red + "Ozor: Rapid Fire!");
                            soundPlayers(SoundInit.OZOR_MESSAGE, 1.0f);
                        }
                        break;
                    case 2:
                        if (this.field_70173_aa % 2 == 0) {
                            fireLaser(2);
                        }
                        if (this.nextFormTimer == 0) {
                            this.fireMode = 0;
                            this.nextFormTimer = 200;
                        } else if (this.nextFormTimer == 20) {
                            messagePlayers(TextFmt.Red + "Ozor: Standard Fire!");
                            soundPlayers(SoundInit.OZOR_MESSAGE, 1.0f);
                        }
                        break;
                }
                this.illusionCooldown--;
                if (this.illusionCooldown == 0) {
                    setIllusionPhase(1);
                    this.illusionCooldown = 100;
                    messagePlayers(TextFmt.Aqua + "Ozor: Embrace Illusion...");
                    soundPlayers(SoundInit.OZOR_VANISH, 1.5f);
                    this.field_70714_bg.field_75782_a.clear();
                    this.field_70715_bh.field_75782_a.clear();
                    return;
                }
                return;
            }
            if (phase == 1) {
                this.illusionCooldown--;
                if (this.illusionCooldown == 0) {
                    setIllusionPhase(2);
                    performIllusion();
                }
                func_70690_d(new PotionEffect(PotionInit.PROTECTED, 10));
                return;
            }
            if (phase == 2) {
            }
            return;
        }
        int phase2 = getIllusionPhase();
        if (phase2 == 0) {
            this.ozorAlpha = 1.0f;
        } else if (phase2 != 1) {
            this.ozorAlpha += 0.025f;
        } else if (this.ozorAlpha > 0.0f) {
            this.ozorAlpha -= 0.025f;
        }
    }
    private void findNewMove() {
        AxisAlignedBB aabb = getArenaAABB();
        int length = ((int) Math.round(aabb.field_72336_d - aabb.field_72340_a)) - 6;
        int width = ((int) Math.round(aabb.field_72334_f - aabb.field_72339_c)) - 6;
        this.hoverTo = new BlockPos(aabb.field_72340_a + 3.0d + ((double) this.field_70146_Z.nextInt(length)), aabb.field_72338_b + ((double) this.field_70146_Z.nextInt(getMaxFly())), aabb.field_72339_c + 3.0d + ((double) this.field_70146_Z.nextInt(width)));
    }
    private static int getMaxFly() {
        return 10;
    }
    private void clearDecoys() {
        for (EntityOzorDecoy decoy : this.field_70170_p.func_72872_a(EntityOzorDecoy.class, getArenaAABB())) {
            decoy.setFading(true);
        }
    }
    private void performIllusion() {
        func_184589_d(PotionInit.PROTECTED);
        List<BlockPos> locations = new ArrayList<>();
        AxisAlignedBB aabb = getArenaAABB();
        int length = ((int) Math.round(aabb.field_72336_d - aabb.field_72340_a)) - 16;
        int width = ((int) Math.round(aabb.field_72334_f - aabb.field_72339_c)) - 16;
        for (int i = 0; i < 12; i++) {
            locations.add(new BlockPos(aabb.field_72340_a + 8.0d + ((double) this.field_70146_Z.nextInt(length)), aabb.field_72338_b + 2.0d, aabb.field_72339_c + 8.0d + ((double) this.field_70146_Z.nextInt(width))));
        }
        BlockPos first = locations.get(0);
        func_70634_a(first.func_177958_n(), first.func_177956_o(), first.func_177952_p());
        for (int i2 = 1; i2 < 12; i2++) {
            BlockPos current = locations.get(i2);
            EntityOzorDecoy decoy = new EntityOzorDecoy(this.field_70170_p);
            decoy.func_70107_b(current.func_177958_n(), current.func_177956_o(), current.func_177952_p());
            this.field_70170_p.func_72838_d(decoy);
        }
        soundPlayers(SoundInit.OZOR_REAPPEAR, 1.0f);
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
        return 150;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "Ozor is at " + lifePercent + "% health.");
        if (getIllusionPhase() == 2) {
            setIllusionPhase(0);
            clearDecoys();
            func_184651_r();
            this.illusionCooldown = 500;
        }
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
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 11);
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
