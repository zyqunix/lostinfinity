package xol.lostinfinity.projectile.entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntityExplosiveGoo extends EntityBaseThrowable {
    private int size;
    private int denom;
    public EntityExplosiveGoo(World par1World) {
        super(par1World);
        this.size = 4;
        this.denom = 4;
        func_70105_a(0.75f, 0.75f);
    }
    public EntityExplosiveGoo(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.size = 4;
        this.denom = 4;
    }
    public EntityExplosiveGoo(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.size = 4;
        this.denom = 4;
    }
    public void setDenomAndSize(int s, int d) {
        this.size = s;
        this.denom = d;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72308_g != null && (result.field_72308_g instanceof EntityPlayer)) {
                EntityPlayer player = result.field_72308_g;
                ItemStack held = player.func_184614_ca();
                if (held.func_77973_b() == Items.field_151069_bo) {
                    player.func_191521_c(new ItemStack(ItemInit.explosiveGooSample));
                    held.func_190918_g(1);
                }
            }
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setSpread(2.0d, 1.0d, 2.0d).setCount(3).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(this.size, this.size, this.size))) {
                IMaxAttack.dealMaxHealth(this, target, this.denom);
            }
            func_70106_y();
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
}
