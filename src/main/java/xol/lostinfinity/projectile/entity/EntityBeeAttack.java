package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityBeeAttack.class */
public class EntityBeeAttack extends EntityBaseThrowable {
    public EntityBeeAttack(World par1World) {
        super(par1World);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityBeeAttack(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        func_70105_a(0.75f, 0.75f);
    }

    public EntityBeeAttack(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        func_70105_a(0.75f, 0.75f);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null) {
                if (result.field_72308_g instanceof EntityPlayer) {
                    ItemStack stack = result.field_72308_g.func_184614_ca();
                    Item item = stack.func_77973_b();
                    if (item.equals(ItemInit.honeyJarEmpty)) {
                        stack.func_190918_g(1);
                        func_145779_a(ItemInit.honeyJar, 1);
                        return;
                    }
                }
                if (!result.field_72308_g.equals(func_85052_h()) && (result.field_72308_g instanceof EntityLivingBase) && IMaxAttack.dealMaxHealth(this, result.field_72308_g, 5).didSuccessfulHit()) {
                    int level = 0;
                    if (result.field_72308_g.func_70644_a(PotionInit.VULNERABILITY)) {
                        level = result.field_72308_g.func_70660_b(PotionInit.VULNERABILITY).func_76458_c();
                    }
                    result.field_72308_g.func_70690_d(new PotionEffect(PotionInit.VULNERABILITY, 150, level));
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
            for (int k = 0; k < 2; k++) {
                this.field_70170_p.func_175688_a(ParticleInit.HONEY_BUBBLE, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
