package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDeviantFireball.class */
public class EntityDeviantFireball extends EntityFireball implements IMaxAttack {
    private int denomDamage;
    private float explosWidth;

    public EntityDeviantFireball(World world, EntityLivingBase entity, double i, double j, double k) {
        super(world, entity, i, j, k);
        this.denomDamage = 4;
        this.explosWidth = 2.0f;
    }

    public EntityDeviantFireball(World world, EntityLivingBase entity, double i, double j, double k, int newDenom, float newExplos) {
        this(world, entity, i, j, k);
        this.denomDamage = newDenom;
        this.explosWidth = newExplos;
    }

    public EntityDeviantFireball(World world) {
        super(world);
        this.denomDamage = 4;
        this.explosWidth = 2.0f;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null) {
                result.field_72308_g.func_70097_a(DamageSource.func_76362_a(this, this.field_70235_a), 0.5f);
                if (result.field_72308_g instanceof EntityLivingBase) {
                    IMaxAttack.dealMaxHealth(this, result.field_72308_g, this.denomDamage);
                }
            } else if (result.field_72313_a == RayTraceResult.Type.BLOCK && this.field_70170_p.func_180495_p(result.func_178782_a()).func_177230_c() == Blocks.field_150366_p) {
                EntityItem fbdropentity = new EntityItem(this.field_70170_p, result.func_178782_a().func_177958_n(), result.func_178782_a().func_177956_o(), result.func_178782_a().func_177952_p(), new ItemStack(ItemInit.superheatedIngot));
                fbdropentity.func_184224_h(true);
                this.field_70170_p.func_72838_d(fbdropentity);
                this.field_70170_p.func_175698_g(result.func_178782_a());
            }
            if (this.explosWidth != 0.0f) {
                this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explosWidth, false);
            }
            func_70106_y();
        }
    }

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.func_76133_a((x * x) + (y * y) + (z * z));
        double x2 = x / ((double) f);
        double y2 = y / ((double) f);
        double z2 = z / ((double) f);
        double x3 = x2 + (this.field_70146_Z.nextGaussian() * 0.007499999832361937d * ((double) inaccuracy));
        double y3 = y2 + (this.field_70146_Z.nextGaussian() * 0.007499999832361937d * ((double) inaccuracy));
        double z3 = z2 + (this.field_70146_Z.nextGaussian() * 0.007499999832361937d * ((double) inaccuracy));
        double x4 = x3 * ((double) velocity);
        double y4 = y3 * ((double) velocity);
        double z4 = z3 * ((double) velocity);
        this.field_70159_w = x4;
        this.field_70181_x = y4;
        this.field_70179_y = z4;
        float f1 = MathHelper.func_76133_a((x4 * x4) + (z4 * z4));
        this.field_70177_z = (float) (MathHelper.func_181159_b(x4, z4) * 57.29577951308232d);
        this.field_70125_A = (float) (MathHelper.func_181159_b(y4, f1) * 57.29577951308232d);
        this.field_70126_B = this.field_70177_z;
        this.field_70127_C = this.field_70125_A;
    }
}
