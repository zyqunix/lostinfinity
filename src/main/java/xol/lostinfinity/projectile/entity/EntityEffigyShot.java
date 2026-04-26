package xol.lostinfinity.projectile.entity;

import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.mob.entity.misc.EntityEffigyEffect;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityEffigyShot.class */
public class EntityEffigyShot extends EntityBaseThrowable {
    private ItemStack stack;

    public EntityEffigyShot(World par1World) {
        super(par1World);
        this.stack = null;
    }

    public EntityEffigyShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.stack = null;
    }

    public EntityEffigyShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.stack = null;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && this.stack != null) {
            if (result.field_72313_a == RayTraceResult.Type.BLOCK && this.field_70170_p.func_180495_p(result.func_178782_a().func_177984_a()).func_185904_a().equals(Material.field_151587_i) && this.stack.func_77942_o() && this.stack.func_77978_p().func_186855_b("targetID")) {
                UUID targetID = this.stack.func_77978_p().func_186857_a("targetID");
                EntityEffigyEffect effect = new EntityEffigyEffect(this.field_70170_p);
                EntityPlayer target = this.field_70170_p.func_152378_a(targetID);
                if (target != null) {
                    effect.func_70634_a(target.field_70165_t, target.field_70163_u, target.field_70161_v);
                }
                if (this.field_70192_c != null && (this.field_70192_c instanceof EntityPlayer)) {
                    effect.setCaster((EntityPlayer) this.field_70192_c);
                }
                effect.setTarget(targetID);
                this.field_70170_p.func_72838_d(effect);
            }
            if (0 == 0) {
                EntityItem effigy = new EntityItem(this.field_70170_p, result.func_178782_a().func_177958_n(), result.func_178782_a().func_177956_o(), result.func_178782_a().func_177952_p(), this.stack);
                this.field_70170_p.func_72838_d(effigy);
            }
            func_70106_y();
        }
    }

    public void setStack(ItemStack stack) {
        this.stack = stack.func_77946_l();
    }

    protected float func_70185_h() {
        return 0.05f;
    }
}
