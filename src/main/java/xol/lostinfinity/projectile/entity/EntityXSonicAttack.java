package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityXSonicAttack extends EntityBaseThrowable {
    public EntityXSonicAttack(World worldIn) {
        super(worldIn);
        func_70105_a(1.0f, 1.0f);
    }
    public EntityXSonicAttack(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
        func_70105_a(1.0f, 1.0f);
    }
    public EntityXSonicAttack(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        func_70105_a(1.0f, 1.0f);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72308_g != null && func_85052_h() != null && result.field_72308_g != func_85052_h() && result.field_72308_g != getSecondaryThrower() && (result.field_72308_g instanceof EntityLivingBase)) {
            IMaxAttack.dealTrueDamage(this, result.field_72308_g, result.field_72308_g.func_110138_aP());
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
