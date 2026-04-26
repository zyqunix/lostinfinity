package xol.lostinfinity.mob.entity.murk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityDoomBlast;
import xol.lostinfinity.projectile.entity.EntityDoomShot;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDoomsday extends EntityFloatingBase implements IMaxAttack {
    private ArrayList<EntityPlayer> targetList;
    private Map<EntityPlayer, Vec3d> rootPositions;
    public EntityDoomsday(World worldIn) {
        super(worldIn);
        this.targetList = new ArrayList<>();
        this.rootPositions = new HashMap();
        func_70105_a(3.0f, 7.0f);
        this.rawFlySpeed = 0.95f;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth((Entity) this, func_70638_az(), 2, (List<String>) Arrays.asList("Darkborn"));
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70163_u > 110.0d) {
                this.field_70181_x = -2.0d;
                this.field_70133_I = true;
            }
            int meteorRemain = this.field_70173_aa % 800;
            if (this.field_70173_aa % 5 == 0) {
                this.targetList.clear();
                boolean fired = false;
                AxisAlignedBB pullBox = new AxisAlignedBB(func_180425_c()).func_186662_g(55);
                for (EntityPlayer entity : this.field_70170_p.func_72872_a(EntityPlayer.class, pullBox)) {
                    if (!entity.func_184812_l_()) {
                        this.targetList.add(entity);
                        if (entity.func_70032_d(this) > 55 - 5) {
                            Vec3d dir = func_174791_d().func_178788_d(entity.func_174791_d()).func_72432_b();
                            entity.func_70024_g(dir.field_72450_a / 3.0d, (dir.field_72448_b / 3.0d) + 0.10000000149011612d, dir.field_72449_c / 3.0d);
                            entity.field_70133_I = true;
                        } else {
                            if (this.field_70173_aa % 400 == 200) {
                                entity.func_70690_d(new PotionEffect(PotionInit.ULTRAHEAVY, 100, 2));
                            }
                            if (this.field_70173_aa % 20 == 0 || (meteorRemain > 700 && this.field_70173_aa % 5 == 0)) {
                                fired = true;
                                double makeX = (func_174813_aQ().field_72340_a + func_174813_aQ().field_72336_d) / 2.0d;
                                double makeY = this.field_70163_u + ((double) (this.field_70131_O / 2.0f)) + 0.5d;
                                double makeZ = (func_174813_aQ().field_72339_c + func_174813_aQ().field_72334_f) / 2.0d;
                                double d2 = entity.field_70165_t - makeX;
                                double d3 = entity.field_70163_u - makeY;
                                double d4 = entity.field_70161_v - makeZ;
                                EntityDoomBlast shot = new EntityDoomBlast(this.field_70170_p, this);
                                shot.func_70186_c(d2, d3, d4, 1.5f, 1.0f);
                                shot.setThrower(this);
                                this.field_70170_p.func_72838_d(shot);
                            }
                        }
                    }
                }
                if (fired) {
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.DOOMSDAY_SPELL_2, SoundCategory.HOSTILE, 1.5f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                }
            }
            if (meteorRemain > 100 && meteorRemain < 500 && this.field_70173_aa % 2 == 0) {
                EntityDoomShot shot2 = new EntityDoomShot(this.field_70170_p, this.field_70165_t + getROD(60), this.field_70163_u + 50.0d, this.field_70161_v + getROD(60));
                shot2.setThrower(this);
                shot2.func_70186_c(0.0d, -0.15d, 0.0d, 0.7f, 0.0f);
                this.field_70170_p.func_72838_d(shot2);
                if (this.field_70146_Z.nextInt(10) == 0) {
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.DOOMSDAY_SPELL_1, SoundCategory.HOSTILE, 1.5f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                }
            }
            if (this.targetList.size() > 0) {
                int rootRemain = this.field_70173_aa % 600;
                if (rootRemain == 80) {
                    this.rootPositions.clear();
                    for (EntityPlayer targetPlayer : this.targetList) {
                        this.rootPositions.put(targetPlayer, targetPlayer.func_174791_d());
                        targetPlayer.func_145747_a(new TextComponentString(TextFmt.Light_Purple + "Stay put. Your efforts are futile!"));
                    }
                }
                if (rootRemain > 80 && rootRemain < 200 && this.rootPositions.size() > 0) {
                    for (Map.Entry<EntityPlayer, Vec3d> entry : this.rootPositions.entrySet()) {
                        EntityPlayer targetPlayer2 = entry.getKey();
                        Vec3d rootPos = entry.getValue();
                        double x = targetPlayer2.field_70165_t;
                        double y = targetPlayer2.field_70163_u;
                        double z = targetPlayer2.field_70161_v;
                        double dist = rootPos.func_72438_d(targetPlayer2.func_174791_d());
                        if (dist > 0.0d) {
                            double diffX = rootPos.field_72450_a - x;
                            double diffY = rootPos.field_72448_b - y;
                            double diffZ = rootPos.field_72449_c - z;
                            Vec3d dir2 = new Vec3d(diffX, diffY, diffZ);
                            dir2.func_72432_b();
                            targetPlayer2.field_70159_w = dir2.field_72450_a * 0.4d;
                            targetPlayer2.field_70181_x = dir2.field_72448_b * 0.4d;
                            targetPlayer2.field_70179_y = dir2.field_72449_c * 0.4d;
                            targetPlayer2.field_70133_I = true;
                        }
                    }
                }
            }
            if (func_70638_az() != null) {
                EntityLivingBase target = func_70638_az();
                func_70605_aq().func_75642_a(target.field_70165_t, target.field_70163_u, target.field_70161_v, 1.0d);
                if ((target instanceof EntityPlayer) && this.field_70173_aa % 200 == 130) {
                    func_70634_a(target.field_70165_t, target.field_70163_u + 1.0d, target.field_70161_v);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.DOOMSDAY_TELEPORT, SoundCategory.HOSTILE, 1.5f, 1.0f);
                    target.func_145747_a(new TextComponentString(TextFmt.Dark_Blue + "You cannot escape the darkness."));
                }
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0d);
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25d);
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0d);
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.DOOMSDAY_DEATH;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.DOOMSDAY_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.DOOMSDAY_AMBIENT;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 100;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        AxisAlignedBB pullBox = new AxisAlignedBB(func_180425_c()).func_186662_g(45.0d);
        for (EntityPlayer entity : this.field_70170_p.func_72872_a(EntityPlayer.class, pullBox)) {
            entity.func_145747_a(new TextComponentString(TextFmt.Gold + "Doomsday is at " + lifePercent + "% health."));
        }
    }
    protected boolean func_70692_ba() {
        return false;
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_DOOMSDAY;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
    private double getROD(int multi) {
        return ((-0.5d) + this.field_70146_Z.nextDouble()) * ((double) multi);
    }
}
