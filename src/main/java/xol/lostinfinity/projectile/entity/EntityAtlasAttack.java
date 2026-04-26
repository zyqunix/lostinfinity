package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityAtlasAttack extends EntityBaseThrowable {
    public EntityAtlasAttack(World worldIn) {
        super(worldIn);
    }
    public EntityAtlasAttack(World worldIn, EntityLivingBase entityIn) {
        super(worldIn, entityIn);
    }
    public EntityAtlasAttack(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.25f, false);
            for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(1.0d, 1.0d, 1.0d))) {
                IMaxAttack.dealMaxHealth(this, target, 4);
            }
            func_70106_y();
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
