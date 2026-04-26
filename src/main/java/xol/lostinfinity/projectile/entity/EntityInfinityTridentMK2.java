package xol.lostinfinity.projectile.entity;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityInfinityTridentMK2 extends EntityBaseThrowable {
    private boolean rebound;
    private double speed;
    private ArrayList<EntityLivingBase> entitiesToHit;
    int curIndex;
    private boolean returned;
    private static final int chainRadius = 15;
    public EntityInfinityTridentMK2(World par1World) {
        super(par1World);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesToHit = new ArrayList<>();
        this.curIndex = 0;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
    }
    public EntityInfinityTridentMK2(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesToHit = new ArrayList<>();
        this.curIndex = 0;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
    }
    public EntityInfinityTridentMK2(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.rebound = false;
        this.speed = 0.1d;
        this.entitiesToHit = new ArrayList<>();
        this.curIndex = 0;
        this.returned = false;
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null) {
                EntityPlayer entityPlayerFunc_85052_h = func_85052_h();
                if (this.entitiesToHit.isEmpty()) {
                    if (!result.field_72308_g.equals(entityPlayerFunc_85052_h) && (result.field_72308_g instanceof EntityLivingBase)) {
                        IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP() * 1.0f, Arrays.asList("Aquatic"));
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(this.field_70170_p, config1, result.field_72308_g.field_70165_t, result.field_72308_g.field_70163_u + ((double) (result.field_72308_g.field_70131_O / 2.0f)), result.field_72308_g.field_70161_v);
                        this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d, (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                        this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.PLAYERS, 1.5f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                        this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.GENERIC_SLICE, SoundCategory.PLAYERS, 1.5f, 0.5f + (0.4f * this.field_70146_Z.nextFloat()));
                        for (EntityLivingBase near : this.field_70170_p.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(func_180425_c()).func_186662_g(15.0d))) {
                            if (!near.func_110124_au().equals(entityPlayerFunc_85052_h.func_110124_au()) && !near.func_110124_au().equals(func_110124_au()) && !(near instanceof EntityImmaterial)) {
                                this.entitiesToHit.add(near);
                            }
                        }
                    }
                } else if (result.field_72308_g.equals(entityPlayerFunc_85052_h) && (entityPlayerFunc_85052_h instanceof EntityPlayer) && !this.returned) {
                    EntityPlayer player = entityPlayerFunc_85052_h;
                    boolean found = false;
                    int i = 0;
                    while (true) {
                        if (i >= player.field_71071_by.func_70302_i_()) {
                            break;
                        }
                        ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                        if (playerStack.func_77973_b() != ItemInit.infinityTridentMK2) {
                            i++;
                        } else {
                            playerStack.func_77978_p().func_74768_a("empty_data", 0);
                            player.field_71071_by.func_70299_a(i, playerStack);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        player.field_71071_by.func_70441_a(new ItemStack(ItemInit.infinityTridentMK2));
                    }
                    this.returned = true;
                    func_70106_y();
                } else if (!result.field_72308_g.equals(entityPlayerFunc_85052_h) && (result.field_72308_g instanceof EntityLivingBase)) {
                    EntityLivingBase entityLivingBase = this.entitiesToHit.get(this.curIndex);
                    while (true) {
                        EntityLivingBase cur = entityLivingBase;
                        if (cur == null || cur.field_70128_L) {
                            if (this.curIndex < this.entitiesToHit.size() - 1) {
                                this.curIndex++;
                                entityLivingBase = this.entitiesToHit.get(this.curIndex);
                            } else {
                                this.rebound = true;
                                return;
                            }
                        } else {
                            EntityLivingBase hitEntity = result.field_72308_g;
                            if (hitEntity.func_110124_au().equals(cur.func_110124_au())) {
                                IMaxAttack.dealTrueDamage(this, cur, cur.func_110138_aP() * 1.0f, Arrays.asList("Aquatic"));
                                CustomParticleConfig config12 = new CustomParticleConfig();
                                config12.createInstance().setParticle(ParticleInit.CRYSTAL_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                                IParticleSpawner.spawnParticle(this.field_70170_p, config12, result.field_72308_g.field_70165_t, result.field_72308_g.field_70163_u + ((double) (result.field_72308_g.field_70131_O / 2.0f)), result.field_72308_g.field_70161_v);
                                this.field_70170_p.func_175739_a(EnumParticleTypes.SWEEP_ATTACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5, (-0.5d) + this.field_70146_Z.nextDouble(), 0.3d, (-0.5d) + this.field_70146_Z.nextDouble(), 0.15000000596046448d, new int[0]);
                                this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.MAGIC_WEAPON_14, SoundCategory.PLAYERS, 1.5f, 0.8f + (0.4f * this.field_70146_Z.nextFloat()));
                                this.field_70170_p.func_184133_a((EntityPlayer) null, result.field_72308_g.func_180425_c(), SoundInit.GENERIC_SLICE, SoundCategory.PLAYERS, 1.5f, 0.5f + (0.4f * this.field_70146_Z.nextFloat()));
                                if (this.curIndex >= this.entitiesToHit.size() - 1) {
                                    this.rebound = true;
                                    return;
                                }
                                this.curIndex++;
                            }
                        }
                    }
                }
            }
            if (this.field_70192_c == null) {
                func_70106_y();
            }
            if (result.field_72308_g == null && this.field_70170_p.func_180495_p(result.func_178782_a()).func_185904_a().func_76230_c()) {
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
                        EntityPlayer player = entityPlayerFunc_85052_h;
                        boolean found = false;
                        int i = 0;
                        while (true) {
                            if (i >= player.field_71071_by.func_70302_i_()) {
                                break;
                            }
                            ItemStack playerStack = player.field_71071_by.func_70301_a(i);
                            if (playerStack.func_77973_b() != ItemInit.infinityTridentMK2) {
                                i++;
                            } else {
                                playerStack.func_77978_p().func_74768_a("empty_data", 0);
                                player.field_71071_by.func_70299_a(i, playerStack);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            player.field_71071_by.func_70441_a(new ItemStack(ItemInit.infinityTridentMK2));
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
            if (!this.entitiesToHit.isEmpty()) {
                this.speed = 1.0d;
                EntityLivingBase entityLivingBase = this.entitiesToHit.get(this.curIndex);
                while (true) {
                    EntityLivingBase cur = entityLivingBase;
                    if (cur != null && !cur.field_70128_L) {
                        if (func_70032_d(cur) < 0.5d) {
                            this.curIndex++;
                            return;
                        }
                        Vec3d dir3 = cur.func_174791_d().func_72441_c(0.0d, ((double) cur.field_70131_O) / 2.2d, 0.0d).func_178788_d(func_174791_d()).func_72432_b();
                        this.field_70159_w = dir3.field_72450_a * this.speed;
                        this.field_70181_x = dir3.field_72448_b * this.speed;
                        this.field_70179_y = dir3.field_72449_c * this.speed;
                        this.field_70133_I = true;
                        return;
                    }
                    if (this.curIndex < this.entitiesToHit.size() - 1) {
                        this.curIndex++;
                        entityLivingBase = this.entitiesToHit.get(this.curIndex);
                    } else {
                        this.rebound = true;
                        return;
                    }
                }
            }
        } else {
            this.field_70170_p.func_175688_a(ParticleInit.SPECTRAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), 0.0d, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), new int[0]);
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
