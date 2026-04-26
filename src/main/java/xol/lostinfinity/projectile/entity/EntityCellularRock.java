package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityCellularRock.class */
public class EntityCellularRock extends EntityBaseThrowable {
    public EntityCellularRock(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityCellularRock(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityCellularRock(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g == null) {
                BlockPos resultPos = result.func_178782_a();
                if (this.field_70170_p.func_180495_p(resultPos).func_177230_c() != BlockInit.cellularOre) {
                    BlockPos prev = func_180425_c();
                    if (this.field_70170_p.func_175623_d(prev)) {
                        this.field_70170_p.func_175656_a(prev, BlockInit.cellularOre.func_176223_P());
                    }
                }
            } else if (result.field_72308_g instanceof EntityLivingBase) {
                EntityLivingBase target = result.field_72308_g;
                IMaxAttack.dealMaxHealth(this, target, 1);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
