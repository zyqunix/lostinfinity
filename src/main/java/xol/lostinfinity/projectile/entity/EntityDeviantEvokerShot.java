package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantEvokerShot extends EntityBaseThrowable {
    public EntityDeviantEvokerShot(World par1World) {
        super(par1World);
    }
    public EntityDeviantEvokerShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntityDeviantEvokerShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    public EntityDeviantEvokerShot(World par1World, double par2, double par4, double par6, float size) {
        super(par1World, par2, par4, par6);
        func_70105_a(size, size);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 2);
                if (result.field_72308_g instanceof EntityPlayer) {
                    result.field_72308_g.func_70690_d(new PotionEffect(PotionInit.NULLIFIED, 300));
                }
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
