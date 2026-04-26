package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;
public class EntityDeathShot extends EntityBaseThrowable implements IMaxAttack {
    private static final int MARK_RANGE = 15;
    private static final float GRAVITY = 0.01f;
    private static final SoundEvent DAMAGE_SOUND = SoundInit.MISSILE_EXPLOSION;
    private static final EnumParticleTypes MARK_PARTICLE = ParticleInit.RED_SKULL;
    private static final EnumParticleTypes DAMAGE_PARTICLE = ParticleInit.EXPLOSION;
    private List<EntityLivingBase> markedEntities;
    public EntityDeathShot(World par1World) {
        super(par1World);
        this.markedEntities = new ArrayList();
        func_70105_a(0.75f, 0.75f);
    }
    public EntityDeathShot(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
        this.markedEntities = new ArrayList();
        func_70105_a(0.75f, 0.75f);
        setThrower(entityIn);
        double spawnX = entityIn.field_70165_t;
        double spawnY = entityIn.field_70163_u + ((double) entityIn.func_70047_e());
        double spawnZ = entityIn.field_70161_v;
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[entityIn.func_174811_aO().ordinal()]) {
            case 1:
                spawnZ -= 1.0d;
                break;
            case 2:
                spawnZ += 1.0d;
                break;
            case 3:
                spawnX += 1.0d;
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                spawnX -= 1.0d;
                break;
        }
        func_70634_a(spawnX, spawnY, spawnZ);
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];
        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    public EntityDeathShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.markedEntities = new ArrayList();
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.field_70173_aa % 10 == 1 && func_85052_h() != null) {
            for (EntityTameable entityTameable : this.field_70170_p.func_72872_a(EntityLivingBase.class, func_174813_aQ().func_186662_g(15.0d))) {
                if (!this.markedEntities.contains(entityTameable) && !entityTameable.equals(func_85052_h())) {
                    if (entityTameable instanceof EntityTameable) {
                        EntityTameable tameable = entityTameable;
                        if (!tameable.func_70909_n() || !tameable.func_70902_q().equals(func_85052_h())) {
                        }
                    }
                    if (entityTameable instanceof EntityMinion) {
                        EntityMinion minion = (EntityMinion) entityTameable;
                        if (minion.func_184753_b().equals(func_85052_h().func_110124_au())) {
                        }
                    }
                    this.markedEntities.add(entityTameable);
                }
            }
        }
        if (this.field_70173_aa % 10 == 1) {
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setParticle(MARK_PARTICLE).setSpread(0.0d, 0.0d, 0.0d).setCount(1).setIgnoreRange(true);
            for (EntityLivingBase markedEntity : this.markedEntities) {
                if (!markedEntity.field_70128_L) {
                    IParticleSpawner.spawnParticle(this.field_70170_p, config, markedEntity.field_70165_t, markedEntity.field_70163_u + ((double) markedEntity.field_70131_O) + 0.6499999761581421d, markedEntity.field_70161_v);
                }
            }
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if ((result.field_72308_g == null || func_85052_h() == null || !result.field_72308_g.equals(func_85052_h())) && !this.field_70170_p.field_72995_K) {
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setParticle(ParticleInit.GENERIC_DOT_RED).setSpread(0.0d, 0.0d, 0.0d).setCount(1).setSpeed(0.0d, 0.0d, 0.0d).setIgnoreRange(true);
            Vec3d impactPos = func_174791_d();
            for (EntityLivingBase entity : this.markedEntities) {
                if (!entity.field_70128_L) {
                    IMaxAttack.dealTrueDamage(this, entity, entity.func_110138_aP() * 0.08f * this.markedEntities.size(), Arrays.asList("Darkborn", "Aquatic"));
                    IParticleSpawner.spawnParticle(this.field_70170_p, config, entity.field_70165_t, entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)), entity.field_70161_v);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, entity.func_180425_c(), DAMAGE_SOUND, SoundCategory.PLAYERS, 1.5f, 0.6f + (0.4f * this.field_70146_Z.nextFloat()));
                    Vec3d beamDir = LMath.fastNormalize(impactPos.func_178788_d(entity.func_174791_d().func_72441_c(0.0d, entity.field_70131_O / 2.0f, 0.0d)));
                    double dist = entity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    double d = 0.0d;
                    while (true) {
                        double i = d;
                        if (i < dist) {
                            IParticleSpawner.spawnParticle(this.field_70170_p, config, entity.field_70165_t + (beamDir.field_72450_a * i), entity.field_70163_u + ((double) (entity.field_70131_O / 2.0f)) + (beamDir.field_72448_b * i), entity.field_70161_v + (beamDir.field_72449_c * i));
                            d = i + 0.5d;
                        }
                    }
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return GRAVITY;
    }
}
