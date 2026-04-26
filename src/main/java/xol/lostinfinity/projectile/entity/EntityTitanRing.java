package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityTitanRing extends EntityBaseThrowable {
    private boolean rebound;
    private double speed;
    private ArrayList<Entity> entitiesHit;
    private boolean returned;
    private boolean highVelo;
    private float alpha;
    public EntityTitanRing(World par1World) {
        super(par1World);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.highVelo = false;
        this.alpha = 0.0f;
        func_70105_a(1.25f, 0.75f);
    }
    public EntityTitanRing(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.highVelo = false;
        this.alpha = 0.0f;
        func_70105_a(1.25f, 0.75f);
    }
    public EntityTitanRing(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.highVelo = false;
        this.alpha = 0.0f;
        func_70105_a(1.25f, 0.75f);
    }
    public void setHighVelo() {
        this.highVelo = true;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.entitiesHit == null) {
                this.entitiesHit = new ArrayList<>();
            }
            if (result.field_72308_g != null && func_85052_h() != null) {
                EntityPlayer entityPlayerFunc_85052_h = func_85052_h();
                if (!result.field_72308_g.equals(entityPlayerFunc_85052_h) && (result.field_72308_g instanceof EntityLivingBase) && !this.entitiesHit.contains(result.field_72308_g)) {
                    EntityLivingBase hitEntity = result.field_72308_g;
                    int level = 0;
                    if (hitEntity.func_70644_a(PotionInit.SHOCKED)) {
                        level = hitEntity.func_70660_b(PotionInit.SHOCKED).func_76458_c();
                    }
                    if (IMaxAttack.dealTrueDamage(this, hitEntity, hitEntity.func_110138_aP() * (0.5f + (0.25f * level)) * (this.highVelo ? 0.7f : 1.0f), Arrays.asList("Darkborn", "Aquatic")).didSuccessfulHit()) {
                        hitEntity.func_70690_d(new PotionEffect(PotionInit.SHOCKED, 200, level + 1));
                    }
                    this.entitiesHit.add(result.field_72308_g);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.setCount(3);
                    config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_BLUE).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                    config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_YELLOW).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_70170_p, config1, hitEntity.field_70165_t, hitEntity.field_70163_u + ((double) (hitEntity.field_70131_O / 2.0f)), hitEntity.field_70161_v);
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d, (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, hitEntity.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.PLAYERS, 0.2f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                    this.field_70170_p.func_184133_a((EntityPlayer) null, hitEntity.func_180425_c(), SoundInit.GENERIC_SLICE, SoundCategory.PLAYERS, 0.2f, 0.5f + (0.4f * this.field_70146_Z.nextFloat()));
                } else if (result.field_72308_g.equals(entityPlayerFunc_85052_h) && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.returned) {
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_21, SoundCategory.PLAYERS, 0.2f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
                    this.returned = true;
                    func_70106_y();
                }
            }
            if (this.field_70192_c == null) {
                func_70106_y();
            }
            if (result.field_72308_g != null || this.field_70170_p.func_180495_p(result.func_178782_a()).func_185904_a().func_76230_c()) {
                this.rebound = true;
            }
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.rebound) {
                EntityLivingBase thrower = func_85052_h();
                if (thrower != null) {
                    Vec3d dir = thrower.func_174791_d().func_178787_e(new Vec3d(0.0d, 0.2d, 0.0d)).func_178788_d(func_174791_d());
                    if (dir.func_72433_c() < 3.5d && (thrower instanceof EntityPlayer) && !this.field_70128_L && !this.returned) {
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_21, SoundCategory.PLAYERS, 0.6f, 0.7f + (this.field_70146_Z.nextFloat() * 0.6f));
                        this.returned = true;
                        func_70106_y();
                    }
                    Vec3d dir2 = dir.func_72432_b();
                    this.field_70159_w = dir2.field_72450_a * this.speed;
                    this.field_70181_x = dir2.field_72448_b * this.speed;
                    this.field_70179_y = dir2.field_72449_c * this.speed;
                    if (this.speed < 1.5d) {
                        this.speed += 0.1d;
                    }
                    this.field_70133_I = true;
                    return;
                }
                return;
            }
            this.field_70159_w *= 0.96d;
            this.field_70181_x *= 0.96d;
            this.field_70179_y *= 0.96d;
            this.field_70133_I = true;
            if (this.field_70173_aa > 40) {
                this.rebound = true;
                return;
            }
            return;
        }
        if (this.alpha < 1.0f) {
            this.alpha += 0.1f;
        }
        this.field_70170_p.func_175688_a(this.field_70146_Z.nextBoolean() ? ParticleInit.SPECTRAL : ParticleInit.SMALL_SPARK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), 0.0d, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), new int[0]);
    }
    public float getAlpha() {
        return this.alpha;
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
