package xol.lostinfinity.projectile.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityGalaxyBlast.class */
public class EntityGalaxyBlast extends EntityBaseThrowable {
    public EntityGalaxyBlast(World par1World) {
        super(par1World);
        func_70105_a(0.5f, 0.5f);
    }

    public EntityGalaxyBlast(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityGalaxyBlast(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && func_85052_h() != null) {
                if (result.field_72308_g != func_85052_h() && (result.field_72308_g instanceof EntityPlayer)) {
                    IMaxAttack.dealMaxHealth(this, result.field_72308_g, 5);
                    func_70106_y();
                    return;
                }
                return;
            }
            if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
                if (this.field_70170_p.func_180495_p(result.func_178782_a()).func_177230_c().equals(BlockInit.galDungeonTarget)) {
                    EntityItem crystal = new EntityItem(this.field_70170_p, result.func_178782_a().func_177958_n(), result.func_178782_a().func_177956_o(), result.func_178782_a().func_177952_p(), new ItemStack(ItemInit.chargedGalaxyCrystal));
                    crystal.field_70159_w = 0.0d;
                    crystal.field_70181_x = 0.0d;
                    crystal.field_70179_y = 0.0d;
                    this.field_70170_p.func_72838_d(crystal);
                    func_184185_a(SoundInit.GAME_DING, 1.0f, 1.0f);
                }
                func_70106_y();
            }
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    @SideOnly(Side.CLIENT)
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < 4; k++) {
                this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_INSTANT, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
            }
        }
    }
}
