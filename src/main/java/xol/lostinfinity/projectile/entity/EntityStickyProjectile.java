package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.item.weapon.ItemStickyBombLauncher;
import xol.lostinfinity.mob.entity.misc.EntityStickyBomb;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityStickyProjectile.class */
public class EntityStickyProjectile extends EntityBaseThrowable {
    private ItemStack referenceStack;

    public EntityStickyProjectile(World par1World) {
        super(par1World);
        this.referenceStack = null;
    }

    public EntityStickyProjectile(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.referenceStack = null;
    }

    public EntityStickyProjectile(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.referenceStack = null;
    }

    public void setStack(ItemStack stack) {
        this.referenceStack = stack;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (func_85052_h() != null && this.referenceStack != null) {
                EntityStickyBomb bomb = new EntityStickyBomb(this.field_70170_p);
                bomb.setCreator(func_85052_h().func_110124_au());
                ItemStickyBombLauncher launcher = (ItemStickyBombLauncher) this.referenceStack.func_77973_b();
                launcher.addBomb(bomb);
                if (result.field_72308_g != null && (result.field_72308_g instanceof EntityLivingBase)) {
                    bomb.setHoldPos((EntityLivingBase) result.field_72308_g);
                    bomb.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                } else {
                    BlockPos resultPos = result.func_178782_a();
                    bomb.setHoldPos(resultPos);
                    bomb.func_70107_b(this.field_70169_q, this.field_70167_r, this.field_70166_s);
                }
                this.field_70170_p.func_72838_d(bomb);
            }
            func_70106_y();
        }
    }

    protected float func_70185_h() {
        return 0.030000001f;
    }
}
