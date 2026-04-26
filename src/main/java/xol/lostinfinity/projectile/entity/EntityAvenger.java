package xol.lostinfinity.projectile.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.PotionBasic;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityAvenger.class */
public class EntityAvenger extends EntityBaseThrowable {
    private boolean rebound;
    private double speed;
    private ArrayList<Entity> entitiesHit;
    private boolean returned;
    private static final double bounceRadius = 8.0d;

    public EntityAvenger(World par1World) {
        super(par1World);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityAvenger(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityAvenger(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
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
                    EntityLivingBase target = result.field_72308_g;
                    target.func_110143_aJ();
                    CustomDamageResult dr = IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.5f, Arrays.asList("Aquatic"));
                    float damageDealt = dr.getDamageDealt();
                    if (dr.didSuccessfulHit()) {
                        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.SWING_HIT, SoundCategory.PLAYERS, 1.5f, 0.6f + (this.field_70146_Z.nextFloat() * 0.4f));
                        boolean bounced = false;
                        for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c()).func_186662_g(bounceRadius))) {
                            if (!entity.func_110124_au().equals(entityPlayerFunc_85052_h.func_110124_au()) && !entity.func_110124_au().equals(target.func_110124_au()) && !(entity instanceof EntityImmaterial)) {
                                EntityAvengerLightning lightning = new EntityAvengerLightning(this.field_70170_p);
                                lightning.func_70107_b(((EntityLivingBase) entityPlayerFunc_85052_h).field_70165_t, target.field_70163_u, target.field_70161_v);
                                lightning.setOrigin(target);
                                lightning.setTarget(entity);
                                this.field_70170_p.func_72838_d(lightning);
                                bounced = true;
                            }
                        }
                        if (bounced) {
                            this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.ELECTRIC_SHOCK, SoundCategory.PLAYERS, 1.5f, 0.6f + (this.field_70146_Z.nextFloat() * 0.4f));
                        }
                    }
                    if (dr.wasTargetKilled()) {
                        killReward(func_85052_h());
                    } else if (dr.didSuccessfulHit()) {
                        func_85052_h().func_70691_i(damageDealt);
                    }
                    this.entitiesHit.add(target);
                    IParticleSpawner.spawnParticle(this.field_70170_p, 28, 0, target.field_70165_t, target.field_70163_u + ((double) (target.field_70131_O / 2.0f)), target.field_70161_v);
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d, (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                } else if (result.field_72308_g.equals(entityPlayerFunc_85052_h) && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.returned) {
                    EntityPlayer player = entityPlayerFunc_85052_h;
                    boolean found = false;
                    int i = 0;
                    while (true) {
                        if (i >= player.field_71071_by.func_70302_i_()) {
                            break;
                        }
                        ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                        if (playerStack.func_77973_b() != ItemInit.avenger) {
                            i++;
                        } else {
                            playerStack.func_77978_p().func_74768_a("empty_data", 0);
                            player.field_71071_by.func_70299_a(i, playerStack);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        player.field_71071_by.func_70441_a(new ItemStack(ItemInit.avenger));
                    }
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
                EntityPlayer entityPlayerFunc_85052_h = func_85052_h();
                if (entityPlayerFunc_85052_h != null) {
                    Vec3d dir = entityPlayerFunc_85052_h.func_174791_d().func_178787_e(new Vec3d(0.0d, ((double) ((EntityLivingBase) entityPlayerFunc_85052_h).field_70131_O) / 1.5d, 0.0d)).func_178788_d(func_174791_d());
                    if (dir.func_72433_c() < 3.5d && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.field_70128_L && !this.returned) {
                        boolean found = false;
                        EntityPlayer player = entityPlayerFunc_85052_h;
                        int i = 0;
                        while (true) {
                            if (i >= player.field_71071_by.func_70302_i_()) {
                                break;
                            }
                            ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                            if (playerStack.func_77973_b() != ItemInit.avenger) {
                                i++;
                            } else {
                                playerStack.func_77978_p().func_74768_a("empty_data", 0);
                                player.field_71071_by.func_70299_a(i, playerStack);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            player.field_71071_by.func_70441_a(new ItemStack(ItemInit.avenger));
                        }
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
        this.field_70170_p.func_175688_a(ParticleInit.SPECTRAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), 0.0d, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), new int[0]);
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    private void killReward(EntityLivingBase player) {
        List<PotionEffect> potionsToAdd = new ArrayList<>();
        List<Potion> potionList = (List) player.func_70651_bq().stream().map((v0) -> {
            return v0.func_188419_a();
        }).collect(Collectors.toList());
        for (Potion potion : potionList) {
            if (potion instanceof PotionBasic) {
                PotionBasic lost_potion = (PotionBasic) potion;
                PotionEffect scaledEffect = scalePotionEffect(player.func_70660_b(potion), lost_potion.negativeLostEffect());
                if (scaledEffect != null) {
                    potionsToAdd.add(scaledEffect);
                }
            } else {
                PotionEffect scaledEffect2 = scalePotionEffect(player.func_70660_b(potion), potion.func_76398_f());
                if (scaledEffect2 != null) {
                    potionsToAdd.add(scaledEffect2);
                }
            }
        }
        player.func_70674_bp();
        for (PotionEffect pe : potionsToAdd) {
            player.func_70690_d(pe);
        }
        player.func_70691_i(player.func_110138_aP());
    }

    private PotionEffect scalePotionEffect(PotionEffect effect, boolean bad) {
        if (!bad) {
            return effect;
        }
        int level = effect.func_76458_c() - 1;
        int duration = effect.func_76459_b();
        if (level >= 0) {
            return new PotionEffect(effect.func_188419_a(), duration, level);
        }
        return null;
    }
}
