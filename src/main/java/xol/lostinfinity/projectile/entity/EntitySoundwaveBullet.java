package xol.lostinfinity.projectile.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntitySoundwaveBullet.class */
public class EntitySoundwaveBullet extends EntityBaseThrowable implements IMaxAttack {
    private static final long HIT_COOLDOWN = 10;
    private static final int MAX_BOUNCE_CHECK = 4;
    private boolean hasBouncedBefore;
    private final Map<Entity, Long> damageLog;

    public EntitySoundwaveBullet(World worldIn) {
        super(worldIn);
        this.hasBouncedBefore = false;
        this.damageLog = new ConcurrentHashMap();
        func_70105_a(0.5f, 0.5f);
    }

    public EntitySoundwaveBullet(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
        this.hasBouncedBefore = false;
        this.damageLog = new ConcurrentHashMap();
        func_70105_a(0.5f, 0.5f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K) {
        }
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type[result.field_72313_a.ordinal()]) {
            case 1:
                EntityLivingBase entityLivingBase = result.field_72308_g;
                if ((entityLivingBase instanceof EntityLivingBase) && !entityLivingBase.equals(func_85052_h())) {
                    Long lastHitTime = this.damageLog.get(entityLivingBase);
                    Long currentTime = Long.valueOf(System.currentTimeMillis());
                    if (lastHitTime == null || currentTime.longValue() - lastHitTime.longValue() >= HIT_COOLDOWN) {
                        if (this.hasBouncedBefore) {
                            IMaxAttack.dealTrueDamage(this, entityLivingBase, entityLivingBase.func_110138_aP() * 0.33f);
                        } else {
                            IMaxAttack.dealMaxHealth(this, entityLivingBase, 3);
                        }
                        this.damageLog.put(entityLivingBase, currentTime);
                    }
                }
                checkBounce();
                break;
            case 2:
                EnumFacing facing = result.field_178784_b;
                for (int i = 0; i < 4; i++) {
                    bounce(facing);
                    facing = raytraceFace();
                    if (facing == null) {
                        this.hasBouncedBefore = true;
                        IParticleSpawner.spawnParticle(this.field_70170_p, 62, 0, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.SOUND_BOUNCE, SoundCategory.NEUTRAL, 0.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                    }
                    break;
                }
                this.hasBouncedBefore = true;
                IParticleSpawner.spawnParticle(this.field_70170_p, 62, 0, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.SOUND_BOUNCE, SoundCategory.NEUTRAL, 0.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
                break;
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        this.field_70122_E = false;
        super.func_70071_h_();
        this.field_184539_c = null;
        if (this.field_70173_aa >= 140) {
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    private void checkBounce() {
        EnumFacing facing;
        boolean hasBounced = false;
        for (int i = 0; i < 4 && (facing = raytraceFace()) != null; i++) {
            bounce(facing);
            hasBounced = true;
        }
        if (hasBounced) {
            this.hasBouncedBefore = true;
            IParticleSpawner.spawnParticle(this.field_70170_p, 62, 0, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.SOUND_BOUNCE, SoundCategory.NEUTRAL, 0.5f, 0.8f + (this.field_70170_p.field_73012_v.nextFloat() * 0.4f));
        }
    }

    /* JADX INFO: renamed from: xol.lostinfinity.projectile.entity.EntitySoundwaveBullet$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntitySoundwaveBullet$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$math$RayTraceResult$Type;
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.UP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SwitchMap$net$minecraft$util$math$RayTraceResult$Type = new int[RayTraceResult.Type.values().length];
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.ENTITY.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$net$minecraft$util$math$RayTraceResult$Type[RayTraceResult.Type.BLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    private void bounce(EnumFacing facing) {
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
            case 1:
                this.field_70181_x = -Math.abs(this.field_70181_x);
                break;
            case 2:
                this.field_70181_x = Math.abs(this.field_70181_x);
                break;
            case 3:
                this.field_70179_y = -Math.abs(this.field_70179_y);
                break;
            case 4:
                this.field_70179_y = Math.abs(this.field_70179_y);
                break;
            case 5:
                this.field_70159_w = -Math.abs(this.field_70159_w);
                break;
            case TileEntityFusionTable.BOARD_COLUMNS /* 6 */:
                this.field_70159_w = Math.abs(this.field_70159_w);
                break;
        }
        this.field_70133_I = true;
    }

    private EnumFacing raytraceFace() {
        Vec3d currPos = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        Vec3d nextPos = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
        RayTraceResult raytraceresult = this.field_70170_p.func_72933_a(currPos, nextPos);
        if (raytraceresult != null) {
            return raytraceresult.field_178784_b;
        }
        return null;
    }
}
