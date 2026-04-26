package xol.lostinfinity.mob.entity.boss;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityBarulChain;
import xol.lostinfinity.stone.EntityInfinityStone;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/boss/EntityBarul.class */
public class EntityBarul extends EntityMultipleLives implements IMaxAttack {
    private boolean amAttackingNext;
    private BlockPos goalPos;
    private EntityBarulChain chain;
    private boolean hasPulled;
    private boolean hasMessaged;

    public EntityBarul(World worldIn) {
        super(worldIn);
        this.amAttackingNext = false;
        this.goalPos = null;
        this.chain = null;
        this.hasPulled = false;
        this.hasMessaged = false;
        func_70105_a(3.5f, 8.0f);
    }

    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(30.0d);
    }

    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            if (this.field_70170_p.field_73012_v.nextInt(5) == 0 && !this.field_70170_p.field_72995_K) {
                IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
                entity.func_70024_g(0.0d, 2.0d, 0.0d);
                entity.field_70133_I = true;
                messagePlayers("Barul: UPPERCUT!");
                return false;
            }
            CustomDamageResult result = IMaxAttack.dealMaxHealth(this, func_70638_az(), 3);
            boolean stillAlive = !result.wasTargetKilled();
            if (stillAlive && result.didSuccessfulHit()) {
                int debuff = this.field_70146_Z.nextInt(5);
                switch (debuff) {
                    case 0:
                        func_70638_az().func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 100));
                        return true;
                    case 1:
                        func_70638_az().func_70690_d(new PotionEffect(PotionInit.BLIGHTED, 100));
                        return true;
                    case 2:
                        func_70638_az().func_70690_d(new PotionEffect(PotionInit.INTANGIBLE, 100));
                        return true;
                    case 3:
                        func_70638_az().func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 100));
                        return true;
                    case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                        func_70638_az().func_70690_d(new PotionEffect(PotionInit.ULTRAHEAVY, 100));
                        return true;
                    default:
                        return true;
                }
            }
            return true;
        }
        return false;
    }

    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(-3.0d, 60.0d, -149.0d), new BlockPos(52.0d, 85.0d, -36.0d));
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
                if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball)) {
                    proj.func_70106_y();
                    proj.func_184185_a(SoundInit.ITEM_AXIOMAVORUM, 1.0f, 0.5f + this.field_70146_Z.nextFloat());
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                }
            }
            if (this.chain != null && !this.hasPulled && this.chain.getGrowth() >= 1.0f) {
                if (!this.hasMessaged) {
                    messagePlayers("Barul: GET OVER HERE!");
                    this.hasMessaged = true;
                }
                EntityPlayerMP target = this.chain.getTarget();
                if (target != null && this.chain.getStopPos() == null) {
                    double dist = target.func_174791_d().func_72438_d(this.chain.func_174791_d());
                    if (dist < 4.0d) {
                        this.hasPulled = true;
                        this.hasMessaged = false;
                        this.chain.func_70106_y();
                        this.chain = null;
                        IMaxAttack.dealMaxHealth(this, target, 3);
                        return;
                    }
                    Vec3d pullDir = this.chain.func_174791_d().func_178788_d(target.func_174791_d()).func_72432_b();
                    if (dist < 10.0d) {
                        target.func_70024_g(pullDir.field_72450_a / 10.0d, (pullDir.field_72448_b / 10.0d) + 0.009999999776482582d, pullDir.field_72449_c / 10.0d);
                    } else {
                        target.func_70024_g(pullDir.field_72450_a / 3.0d, (pullDir.field_72448_b / 3.0d) + 0.10000000149011612d, pullDir.field_72449_c / 3.0d);
                    }
                    target.field_71135_a.func_147359_a(new SPacketEntityVelocity(target));
                }
            }
            if (this.field_70173_aa % 150 == 70) {
                this.hasPulled = false;
                if (this.chain != null) {
                    this.chain.func_70106_y();
                    this.chain = null;
                }
                EntityPlayer target2 = null;
                Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB()).iterator();
                if (it.hasNext()) {
                    EntityPlayer near_pl = (EntityPlayer) it.next();
                    target2 = near_pl;
                }
                if (target2 != null) {
                    this.chain = new EntityBarulChain(this.field_70170_p);
                    this.chain.setTarget(target2);
                    this.chain.setOwner(this);
                    this.chain.func_70107_b(this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
                    this.field_70170_p.func_72838_d(this.chain);
                }
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
        return 200;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "Barul is at " + lifePercent + "% health.");
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.chain != null) {
                this.chain.func_70106_y();
                this.chain = null;
            }
            EntityInfinityStone stone = new EntityInfinityStone(this.field_70170_p);
            stone.setStoneNum((byte) 14);
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
