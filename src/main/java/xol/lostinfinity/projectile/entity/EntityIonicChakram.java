package xol.lostinfinity.projectile.entity;
import java.util.Arrays;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityIonicChakram extends EntityBaseThrowable {
    public EntityIonicChakram(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityIonicChakram(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }
    public EntityIonicChakram(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null) {
                EntityLivingBase thrower = func_85052_h();
                if ((result.field_72308_g instanceof EntityLivingBase) && !result.field_72308_g.func_110124_au().equals(thrower.func_110124_au())) {
                    IMaxAttack.dealTrueDamage(thrower, result.field_72308_g, result.field_72308_g.func_110138_aP() * 0.33f, Arrays.asList("Darkborn"));
                    split();
                    return;
                }
            }
            if (this.field_70192_c == null) {
                func_70106_y();
            }
            if (result.field_72308_g != null || this.field_70170_p.func_180495_p(result.func_178782_a()).func_185904_a().func_76230_c()) {
                split();
            }
        }
    }
    private void split() {
        Vec3d dir = new Vec3d(1.0d, 0.0d, 1.0d);
        for (int i = 0; i < 8; i++) {
            Vec3d rotated = rotVecAboutY(dir, (3.141592653589793d * ((double) i)) / 4.0d);
            EntityMiniChakram shot = new EntityMiniChakram(this.field_70170_p, this.field_70192_c);
            shot.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if (i == 0) {
                shot.setMaster();
            }
            shot.func_70186_c(rotated.field_72450_a, rotated.field_72448_b, rotated.field_72449_c, 0.9f, 0.0f);
            this.field_70170_p.func_72838_d(shot);
        }
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_19, SoundCategory.PLAYERS, 1.5f, 0.5f);
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GENERIC_WEAPON_20, SoundCategory.PLAYERS, 1.5f, 1.0f);
        func_70106_y();
    }
    private Vec3d rotVecAboutY(Vec3d vec, double rad) {
        double x = (vec.field_72450_a * Math.cos(rad)) + (vec.field_72449_c * Math.sin(rad));
        double y = vec.field_72448_b;
        double z = ((-vec.field_72450_a) * Math.sin(rad)) + (vec.field_72449_c * Math.cos(rad));
        return new Vec3d(x, y, z);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            this.field_70159_w *= 0.96d;
            this.field_70181_x *= 0.96d;
            this.field_70179_y *= 0.96d;
            this.field_70133_I = true;
            if (this.field_70173_aa > 40 && func_85052_h() != null) {
                split();
                return;
            }
            return;
        }
        this.field_70170_p.func_175688_a(ParticleInit.GENERIC_DOT_ORANGE, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), 0.0d, 0.25d * ((-0.5d) + this.field_70146_Z.nextDouble()), new int[0]);
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
