package xol.lostinfinity.mob.entity.boss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
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
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.projectile.entity.EntityWitherBomb;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantWither extends EntityMultipleLives implements IMaxAttack {
    private BlockPos move_towards;
    public EntityDeviantWither(World worldIn) {
        super(worldIn);
        this.move_towards = null;
        func_70105_a(5.0f, 7.5f);
        func_189654_d(true);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    public void func_70636_d() {
        super.func_70636_d();
        this.field_70143_R = -1.0f;
        if (this.move_towards != null && func_70011_f(this.move_towards.func_177958_n(), this.move_towards.func_177956_o(), this.move_towards.func_177952_p()) > 1.0d && this.field_70159_w > -0.699999988079071d && this.field_70159_w < 0.699999988079071d && this.field_70179_y > -0.699999988079071d && this.field_70179_y < 0.699999988079071d) {
            func_70024_g((((double) this.move_towards.func_177958_n()) - this.field_70165_t) * 0.03d, (((double) this.move_towards.func_177956_o()) - this.field_70163_u) * 0.02d, (((double) this.move_towards.func_177952_p()) - this.field_70161_v) * 0.03d);
            this.field_70133_I = true;
        }
        this.field_70177_z += 2.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa % 3 == 0) {
                scanArenaProjectiles();
            }
            int remainder = this.field_70173_aa % getTickOffset();
            if (remainder % 50 == 0) {
                findNewMove();
            }
            if (remainder == 30) {
                releaseWitherlings();
            }
            if (remainder >= 75 && remainder <= 100) {
                if (remainder % 5 == 0) {
                    fireAttack(2.0f);
                }
            } else if (remainder == 50) {
                func_184185_a(SoundInit.DEVIANT_WITHER_ROAR, 1.0f, 0.7f + (0.6f * this.field_70146_Z.nextFloat()));
                messagePlayers(TextFmt.getFormatting(TextFmt.Italic, TextFmt.Red) + "ROAAAAAAR", SoundInit.DEVIANT_WITHER_ROAR);
            } else if (remainder % 40 == 0) {
                fireAttack(1.0f);
            }
        }
    }
    private void scanArenaProjectiles() {
        for (Entity proj : this.field_70170_p.func_72872_a(Entity.class, getArenaAABB())) {
            if ((proj instanceof EntityThrowable) || (proj instanceof EntityArrow) || (proj instanceof EntityFireball)) {
                if (!(proj instanceof EntityWitherBomb)) {
                    this.field_70170_p.func_72876_a((Entity) null, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, 2.0f, false);
                    for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(10.0d, 10.0d, 10.0d))) {
                        IMaxAttack.dealMaxHealth(this, target, 4);
                    }
                    proj.func_70106_y();
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, proj.field_70165_t, proj.field_70163_u, proj.field_70161_v, 2, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.3d, ((-0.5d) + this.field_70146_Z.nextDouble()) * 3.0d, 0.15000000596046448d, new int[0]);
                }
            }
        }
    }
    private void findNewMove() {
        AxisAlignedBB aabb = getArenaAABB();
        int length = ((int) Math.round(aabb.field_72336_d - aabb.field_72340_a)) - 6;
        int width = ((int) Math.round(aabb.field_72334_f - aabb.field_72339_c)) - 6;
        this.move_towards = new BlockPos(aabb.field_72340_a + 3.0d + ((double) this.field_70146_Z.nextInt(length)), aabb.field_72338_b + ((double) this.field_70146_Z.nextInt(getMaxFly())), aabb.field_72339_c + 3.0d + ((double) this.field_70146_Z.nextInt(width)));
    }
    private void releaseWitherlings() {
        int skullnum = 0;
        for (EntityWitherSkullling entityWitherSkullling : this.field_70170_p.func_72872_a(EntityWitherSkullling.class, getArenaAABB())) {
            skullnum++;
        }
        if (skullnum < 8) {
            int count = 1 + this.field_70146_Z.nextInt(2);
            for (int i = 0; i < count; i++) {
                EntityWitherSkullling skull = new EntityWitherSkullling(this.field_70170_p);
                skull.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_72838_d(skull);
            }
        }
    }
    private void fireAttack(float velo) {
        boolean did_shot = false;
        for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            if (!near_pl.func_184812_l_() && !this.field_70170_p.field_72995_K) {
                func_70676_i(1.0f);
                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                double d2 = near_pl.field_70165_t - makeX;
                double d3 = (near_pl.func_174813_aQ().field_72338_b + ((double) (near_pl.field_70131_O / 2.0f))) - makeY;
                double d4 = near_pl.field_70161_v - makeZ;
                EntityWitherBomb shot = new EntityWitherBomb(this.field_70170_p);
                shot.field_70165_t = makeX;
                shot.field_70163_u = makeY;
                shot.field_70161_v = makeZ;
                shot.func_70186_c(d2, d3, d4, velo, 0.0f);
                shot.setThrower(this);
                this.field_70170_p.func_72838_d(shot);
                did_shot = true;
            }
        }
        if (did_shot) {
            func_184185_a(SoundEvents.field_187606_E, 2.0f, 0.5f + this.field_70146_Z.nextFloat());
        }
    }
    private AxisAlignedBB getArenaAABB() {
        return new AxisAlignedBB(new BlockPos(958.0d, 60.0d, 877.0d), new BlockPos(1012.0d, 82.0d, 924.0d));
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        messagePlayers(TextFmt.Gold + "The Deviant Wither is at " + lifePercent + "% health.", null);
    }
    private static int getMaxFly() {
        return 15;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 40;
    }
    private int getTickOffset() {
        if (getLivesCount() < 15) {
            return 300;
        }
        if (getLivesCount() < 30) {
            return 200;
        }
        return 100;
    }
    protected void messagePlayers(String message, SoundEvent sound) {
        for (EntityPlayer contender : this.field_70170_p.func_72872_a(EntityPlayer.class, getArenaAABB())) {
            contender.func_145747_a(new TextComponentString(message));
            if (sound != null) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, contender.func_180425_c(), sound, SoundCategory.MASTER, 1.5f, 1.0f);
            }
        }
    }
    protected SoundEvent func_184615_bR() {
        return SoundEvents.field_187849_gA;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundEvents.field_187851_gB;
    }
    protected SoundEvent func_184639_G() {
        return null;
    }
    protected ResourceLocation func_184647_J() {
        if (onFinalLife()) {
            return LootTableRegistry.ENTITIES_DEVIANT_WITHER;
        }
        return null;
    }
}
