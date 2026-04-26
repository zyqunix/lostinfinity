package xol.lostinfinity.projectile.entity;

import java.util.regex.Pattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntitySelectionLaser.class */
public class EntitySelectionLaser extends EntityBaseThrowable {
    private int selectionType;
    static Pattern pattern_ah = Pattern.compile("[a-h]");
    static Pattern pattern_io = Pattern.compile("[i-o]");
    static Pattern pattern_pz = Pattern.compile("[p-z]");
    static Pattern pattern_09 = Pattern.compile("[0-9]");

    public EntitySelectionLaser(World par1World) {
        super(par1World);
        this.selectionType = 0;
        func_70105_a(0.75f, 0.75f);
    }

    public EntitySelectionLaser(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.selectionType = 0;
        func_70105_a(0.75f, 0.75f);
    }

    public EntitySelectionLaser(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.selectionType = 0;
        func_70105_a(0.75f, 0.75f);
    }

    public void setSelectionType(int type) {
        this.selectionType = type;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && !result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase)) {
                EntityLivingBase target = result.field_72308_g;
                String hit_name = target.func_70005_c_().toLowerCase();
                if (!hit_name.isEmpty() && regexMatch(this.selectionType, hit_name.substring(0, 1))) {
                    IMaxAttack.dealMaxHealth((Entity) this, target, 2, 3.0f);
                }
            }
            func_70106_y();
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < 4; k++) {
                this.field_70170_p.func_175688_a(ParticleInit.LASER_FIZZLE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    private boolean regexMatch(int selection, String ch) {
        switch (selection) {
            case 0:
                return pattern_ah.matcher(ch).matches();
            case 1:
                return pattern_io.matcher(ch).matches();
            case 2:
                return pattern_pz.matcher(ch).matches();
            case 3:
                return pattern_09.matcher(ch).matches();
            default:
                return false;
        }
    }
}
