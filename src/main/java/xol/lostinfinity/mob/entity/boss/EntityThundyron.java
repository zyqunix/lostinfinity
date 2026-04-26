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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.misc.EntityThunderBomb;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityThundyron.class */
public class EntityThundyron extends EntityMultipleLives implements IMaxAttack {
    private int thunderOrb;
    private float thunderAlph;
    private boolean amAttackingNext;
    private BlockPos goalPos;
    private int graceHit;

    public EntityThundyron(World worldIn) {
        super(worldIn);
        this.thunderOrb = 0;
        this.thunderAlph = 0.0f;
        this.amAttackingNext = false;
        this.goalPos = null;
        this.graceHit = 0;
        func_70105_a(3.5f, 6.0f);
        func_189654_d(true);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer play = (EntityPlayer) entityIn;
            if (!play.func_184812_l_() && this.graceHit == 0) {
                IMaxAttack.dealMaxHealth(this, play, 3);
                this.graceHit = 10;
                this.field_70170_p.func_184133_a((EntityPlayer) null, play.func_180425_c(), SoundInit.ELECTRIC_SHOCK, SoundCategory.HOSTILE, 0.75f, 0.75f + (0.5f * this.field_70146_Z.nextFloat()));
            }
        }
        entityIn.func_70108_f(this);
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.graceHit > 0) {
                this.graceHit--;
            }
            if (this.field_70173_aa % 80 == 0) {
                findNewMove();
            } else if (this.goalPos != null && func_70011_f(this.goalPos.func_177958_n(), this.goalPos.func_177956_o(), this.goalPos.func_177952_p()) > 1.0d && this.field_70159_w > -0.699999988079071d && this.field_70159_w < 0.699999988079071d && this.field_70179_y > -0.699999988079071d && this.field_70179_y < 0.699999988079071d) {
                func_70024_g((((double) this.goalPos.func_177958_n()) - this.field_70165_t) * 0.03d, (((double) this.goalPos.func_177956_o()) - this.field_70163_u) * 0.02d, (((double) this.goalPos.func_177952_p()) - this.field_70161_v) * 0.03d);
                this.field_70133_I = true;
            }
            int bombTimer = this.field_70173_aa % 600;
            boolean lowHealth = getLivesCount() > 75;
            if (bombTimer == 160) {
                messagePlayers(TextFmt.Aqua + "RAIN... OF... BOMBS...");
                return;
            }
            if (bombTimer >= 200) {
                if (bombTimer < (lowHealth ? 560 : 400)) {
                    if (this.field_70173_aa % (lowHealth ? 2 : 4) == 0) {
                        dropBomb();
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        int ticksRemainder = this.field_70173_aa % 15;
        if (ticksRemainder == 0) {
            this.thunderOrb = this.field_70146_Z.nextInt(3);
        }
        if (ticksRemainder < 5) {
            this.thunderAlph += 0.18f;
        } else if (ticksRemainder < 10) {
            this.thunderAlph -= 0.18f;
        }
    }

    private void dropBomb() {
        BlockPos bombPos = posInArena();
        if (this.field_70170_p.func_175623_d(bombPos)) {
            EntityThunderBomb bomb = new EntityThunderBomb(this.field_70170_p);
            bomb.func_70107_b(bombPos.func_177958_n(), bombPos.func_177956_o(), bombPos.func_177952_p());
            this.field_70170_p.func_72838_d(bomb);
        }
    }

    private BlockPos posInArena() {
        AxisAlignedBB aabb = getArenaAABB();
        int length = ((int) Math.round(aabb.field_72336_d - aabb.field_72340_a)) - 6;
        int width = ((int) Math.round(aabb.field_72334_f - aabb.field_72339_c)) - 6;
        return new BlockPos(aabb.field_72340_a + 3.0d + ((double) this.field_70146_Z.nextInt(length)), aabb.field_72338_b + ((double) this.field_70146_Z.nextInt(20)), aabb.field_72339_c + 3.0d + ((double) this.field_70146_Z.nextInt(width)));
    }

    private void findNewMove() {
        if (this.amAttackingNext) {
            Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
            if (it.hasNext()) {
                EntityPlayer near_pl = (EntityPlayer) it.next();
                this.goalPos = near_pl.func_180425_c();
            }
            this.amAttackingNext = false;
            return;
        }
        this.goalPos = posInArena();
        this.amAttackingNext = true;
    }

    public float getThunderAlpha() {
        return this.thunderAlph;
    }

    public int getThunderOrb() {
        return this.thunderOrb;
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
        messagePlayers(TextFmt.Gold + "Thundyron is at " + lifePercent + "% health.");
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 12);
            stone.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_72838_d(stone);
            func_145779_a(ItemInit.arenaCard, 1);
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

    protected boolean func_70692_ba() {
        return false;
    }
}
