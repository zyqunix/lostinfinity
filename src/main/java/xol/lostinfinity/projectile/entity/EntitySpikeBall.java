package xol.lostinfinity.projectile.entity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class EntitySpikeBall extends EntityBaseThrowable {
    public EntitySpikeBall(World par1World) {
        super(par1World);
    }
    public EntitySpikeBall(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }
    public EntitySpikeBall(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K) {
            if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
                Block hitBlock = this.field_70170_p.func_180495_p(result.func_178782_a()).func_177230_c();
                if (hitBlock.equals(BlockInit.blueTentacleEye)) {
                    hitEye(result.func_178782_a(), ItemInit.watchfulEyeBlue, BlockInit.blueTentacleEyeEmpty);
                } else if (hitBlock.equals(BlockInit.pinkTentacleEye)) {
                    hitEye(result.func_178782_a(), ItemInit.watchfulEyePink, BlockInit.pinkTentacleEyeEmpty);
                } else if (hitBlock.equals(BlockInit.purpleTentacleEye)) {
                    hitEye(result.func_178782_a(), ItemInit.watchfulEyePurple, BlockInit.purpleTentacleEyeEmpty);
                } else if (hitBlock.equals(BlockInit.galaxySporeBlue) || hitBlock.equals(BlockInit.galaxySporePink) || hitBlock.equals(BlockInit.galaxySporeYellow) || hitBlock.equals(BlockInit.galaxySporeGreen)) {
                    spawnItem(ItemInit.toxicSporeSample, result.func_178782_a(), 1 + this.field_70146_Z.nextInt(3));
                    poisonCloud(result.func_178782_a());
                }
            }
            func_70106_y();
        }
    }
    private void hitEye(BlockPos pos, Item drop, Block replace) {
        Block old_block = this.field_70170_p.func_180495_p(pos).func_177230_c();
        this.field_70170_p.func_175656_a(pos, replace.func_176203_a(old_block.func_176201_c(this.field_70170_p.func_180495_p(pos))));
        spawnItem(drop, pos, 1);
    }
    private void spawnItem(Item drop, BlockPos place, int numDrop) {
        EntityItem eyedrop = new EntityItem(this.field_70170_p, place.func_177958_n(), place.func_177956_o(), place.func_177952_p(), new ItemStack(drop, numDrop));
        eyedrop.field_70159_w = 0.0d;
        eyedrop.field_70181_x = 0.0d;
        eyedrop.field_70179_y = 0.0d;
        this.field_70170_p.func_72838_d(eyedrop);
    }
    private void poisonCloud(BlockPos place) {
        for (EntityPlayer target : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(5.0d, 5.0d, 5.0d))) {
            IMaxAttack.dealTrueDamage(this, target, target.func_110138_aP() * 0.9f);
        }
        CustomParticleConfig config1 = new CustomParticleConfig();
        config1.createInstance().setParticle(ParticleInit.POISON_BUBBLE).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
        IParticleSpawner.spawnParticle(this.field_70170_p, config1, place.func_177958_n(), place.func_177956_o(), place.func_177952_p());
    }
    protected float func_70185_h() {
        return 0.05f;
    }
}
