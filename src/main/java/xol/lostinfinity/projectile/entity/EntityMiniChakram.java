package xol.lostinfinity.projectile.entity;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityMiniChakram.class */
public class EntityMiniChakram extends EntityBaseThrowable {
    private boolean rebound;
    private double speed;
    private ArrayList<Entity> entitiesHit;
    private boolean returned;
    private boolean master;

    public EntityMiniChakram(World par1World) {
        super(par1World);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.master = false;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityMiniChakram(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.master = false;
        func_70105_a(0.75f, 0.75f);
    }

    public EntityMiniChakram(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesHit = null;
        this.returned = false;
        this.master = false;
        func_70105_a(0.75f, 0.75f);
    }

    public void setMaster() {
        this.master = true;
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
                    if (!IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP() * 0.75f, Arrays.asList("Darkborn")).wasTargetKilled() && (result.field_72308_g instanceof EntityMultipleLives)) {
                        EntityMultipleLives lifer = result.field_72308_g;
                        lifer.takeawayNumLives(5);
                    }
                    this.entitiesHit.add(result.field_72308_g);
                    this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d, (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.PLAYERS, 1.5f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                    this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.GENERIC_SLICE, SoundCategory.PLAYERS, 1.5f, 0.5f + (0.4f * this.field_70146_Z.nextFloat()));
                } else if (result.field_72308_g.equals(entityPlayerFunc_85052_h) && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.returned) {
                    if (this.master) {
                        EntityPlayer player = entityPlayerFunc_85052_h;
                        boolean found = false;
                        int i = 0;
                        while (true) {
                            if (i >= player.field_71071_by.func_70302_i_()) {
                                break;
                            }
                            ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                            if (playerStack.func_77973_b() != ItemInit.ionicChakram) {
                                i++;
                            } else {
                                playerStack.func_77978_p().func_74768_a("empty_data", 0);
                                player.field_71071_by.func_70299_a(i, playerStack);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            player.field_71071_by.func_70441_a(new ItemStack(ItemInit.ionicChakram));
                        }
                        this.returned = true;
                    }
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
                    Vec3d dir = entityPlayerFunc_85052_h.func_174791_d().func_178787_e(new Vec3d(0.0d, 0.2d, 0.0d)).func_178788_d(func_174791_d());
                    if (dir.func_72433_c() < 3.5d && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.field_70128_L && !this.returned) {
                        if (this.master) {
                            EntityPlayer player = entityPlayerFunc_85052_h;
                            boolean found = false;
                            int i = 0;
                            while (true) {
                                if (i >= player.field_71071_by.func_70302_i_()) {
                                    break;
                                }
                                ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                                if (playerStack.func_77973_b() != ItemInit.ionicChakram) {
                                    i++;
                                } else {
                                    playerStack.func_77978_p().func_74768_a("empty_data", 0);
                                    player.field_71071_by.func_70299_a(i, playerStack);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                player.field_71071_by.func_70441_a(new ItemStack(ItemInit.ionicChakram));
                            }
                            this.returned = true;
                        }
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
        this.field_70170_p.func_175688_a(ParticleInit.GENERIC_DOT_YELLOW, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), 0.0d, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), new int[0]);
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
