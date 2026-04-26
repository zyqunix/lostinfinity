package xol.lostinfinity.mob.entity.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityEidolonMist.class */
public class EntityEidolonMist extends EntityBaseThrowable {
    private double speed;
    private EntityLivingBase target;
    private static final int homingRadius = 30;
    private ArrayList<UUID> hitEntities;
    int chaseTime;
    double accel;

    public EntityEidolonMist(World par1World) {
        super(par1World);
        this.speed = 0.3d;
        this.target = null;
        this.hitEntities = new ArrayList<>();
        this.chaseTime = 600;
        this.accel = 0.0d;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityEidolonMist(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.speed = 0.3d;
        this.target = null;
        this.hitEntities = new ArrayList<>();
        this.chaseTime = 600;
        this.accel = 0.0d;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityEidolonMist(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.speed = 0.3d;
        this.target = null;
        this.hitEntities = new ArrayList<>();
        this.chaseTime = 600;
        this.accel = 0.0d;
        func_70105_a(0.75f, 0.75f);
    }

    protected void func_145775_I() {
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null) {
                boolean hit = false;
                for (EntityMinion entityMinion : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(3.0d))) {
                    if (!this.hitEntities.contains(entityMinion.func_110124_au()) && !entityMinion.func_110124_au().equals(func_85052_h().func_110124_au()) && (!(entityMinion instanceof IEntityOwnable) || entityMinion.func_184753_b() != func_85052_h().func_110124_au())) {
                        if (!(entityMinion instanceof EntityMinion) || entityMinion.func_70902_q() != func_85052_h()) {
                            if (IMaxAttack.dealTrueDamage(this, entityMinion, entityMinion.func_110138_aP() * 3.0f, Arrays.asList("Darkborn")).didSuccessfulHit()) {
                                this.hitEntities.add(entityMinion.func_110124_au());
                                hit = true;
                            }
                        }
                    }
                }
                if (hit) {
                    this.chaseTime = 600;
                    this.accel = 0.0d;
                    if (this.field_70146_Z.nextInt(6) == 0) {
                        int numNew = this.field_70146_Z.nextInt(3);
                        for (int i = 0; i < numNew; i++) {
                            EntityEidolonMist newMist = new EntityEidolonMist(this.field_70170_p);
                            newMist.func_70634_a(this.target.field_70165_t, this.target.field_70163_u + ((double) (this.target.field_70131_O / 2.0f)), this.target.field_70161_v);
                            newMist.addInitialTarget(this.target);
                            newMist.setThrower(func_85052_h());
                            this.field_70170_p.func_72838_d(newMist);
                        }
                    }
                }
            }
            this.target = null;
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GHOSTLY_CLOUDS, SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa > 2400) {
                func_70106_y();
            }
            if (this.field_70173_aa % 4 == 0 && func_85052_h() != null && (func_85052_h() instanceof EntityPlayer)) {
                EntityRisingPhantom phantom = new EntityRisingPhantom(this.field_70170_p, func_85052_h());
                Vec3d loc = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                phantom.func_70080_a(loc.field_72450_a, loc.field_72448_b - ((double) phantom.field_70131_O), loc.field_72449_c, this.field_70170_p.field_73012_v.nextFloat() * 360.0f, 0.0f);
                phantom.setVelocity((float) MathHelper.func_151238_b(0.10000000149011612d, MathHelper.func_151238_b(0.10000000149011612d, 0.5d, this.field_70173_aa), this.field_70170_p.field_73012_v.nextFloat()));
                phantom.setLivesTaken(MathHelper.func_76128_c(MathHelper.func_151238_b(0.0d, 10.0d, this.field_70173_aa)));
                this.field_70170_p.func_72838_d(phantom);
            }
            this.chaseTime--;
            if (this.accel < 2.5d) {
                this.accel += 0.04d;
            }
            if (this.chaseTime <= 0) {
                func_70106_y();
            }
            if (this.field_70173_aa % 20 == 0) {
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), randomWhisper(this.field_70146_Z.nextInt(5)), SoundCategory.PLAYERS, 1.25f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
            }
            if (this.field_70173_aa > 2) {
                if (this.target == null || this.target.field_70128_L) {
                    EntityMinion entityMinion = null;
                    double minDist = 9999.0d;
                    for (EntityMinion entityMinion2 : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c().func_177982_a(-30, -30, -30), func_180425_c().func_177982_a(30, 30, 30)))) {
                        if (!this.hitEntities.contains(entityMinion2.func_110124_au()) && (func_85052_h() == null || !func_85052_h().func_110124_au().equals(entityMinion2.func_110124_au()))) {
                            if (!(entityMinion2 instanceof EntityRisingPhantom) && entityMinion2 != null && func_85052_h() != null && (!(entityMinion2 instanceof IEntityOwnable) || entityMinion2.func_184753_b() != func_85052_h().func_110124_au())) {
                                if (!(entityMinion2 instanceof EntityMinion) || entityMinion2.func_70902_q() != func_85052_h()) {
                                    double dist = func_70032_d(entityMinion2);
                                    if (dist < minDist) {
                                        minDist = dist;
                                        entityMinion = entityMinion2;
                                    }
                                }
                            }
                        }
                    }
                    if (entityMinion == null) {
                        func_70106_y();
                        return;
                    }
                    this.target = entityMinion;
                }
                if (this.target != null) {
                    Vec3d dir = this.target.func_174791_d().func_178787_e(new Vec3d(0.0d, ((double) this.target.field_70131_O) / 3.0d, 0.0d)).func_178788_d(func_174791_d());
                    Vec3d dir2 = dir.func_72432_b();
                    Vec3d motionVec = new Vec3d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                    Vec3d diff = dir2.func_178788_d(motionVec.func_72432_b());
                    this.field_70159_w += diff.field_72450_a * (this.speed + this.accel);
                    this.field_70181_x += diff.field_72448_b * (this.speed + this.accel);
                    this.field_70179_y += diff.field_72449_c * (this.speed + this.accel);
                    this.field_70133_I = true;
                    return;
                }
                return;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            this.field_70170_p.func_175682_a(ParticleInit.MURK, true, (this.field_70165_t + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, (this.field_70163_u + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, (this.field_70161_v + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, new int[0]);
            this.field_70170_p.func_175682_a(ParticleInit.MURKY_MIST, true, (this.field_70165_t + (this.field_70146_Z.nextDouble() * 1.0d)) - 0.5d, (this.field_70163_u + (this.field_70146_Z.nextDouble() * 1.0d)) - 0.5d, (this.field_70161_v + (this.field_70146_Z.nextDouble() * 1.0d)) - 0.5d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, (this.field_70146_Z.nextDouble() * 0.25d) - 0.125d, new int[0]);
        }
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
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return SoundInit.WHISPER_5;
            default:
                return SoundInit.WHISPER_5;
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    public void addInitialTarget(EntityLivingBase target) {
        if (target != null) {
            this.hitEntities.add(target.func_110124_au());
        }
    }
}
