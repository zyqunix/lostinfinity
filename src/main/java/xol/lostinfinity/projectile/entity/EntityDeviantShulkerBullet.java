package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityDeviantShulkerBullet extends EntityShulkerBullet implements IMaxAttack {
    public EntityDeviantShulkerBullet(World worldIn) {
        super(worldIn);
    }
    public EntityDeviantShulkerBullet(World worldIn, EntityLivingBase ownerIn, Entity targetIn) {
        super(worldIn, ownerIn, targetIn, EnumFacing.Axis.X);
    }
    protected void func_184567_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                IMaxAttack.dealMaxHealth(this, result.field_72308_g, 3);
            }
            func_70106_y();
        }
    }
}
