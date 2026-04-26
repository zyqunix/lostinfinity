package xol.lostinfinity.projectile.entity;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.PotionInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/entity/EntityDryadsGripAttack.class */
public class EntityDryadsGripAttack extends EntityBaseThrowable {
    private static final int range = 15;
    private int logNum;
    private int leafNum;
    private BlockPos treePos;
    private boolean foundPos;
    private static final Vec3i[] logs = {new Vec3i(0, 1, 0), new Vec3i(0, 2, 0), new Vec3i(0, 3, 0), new Vec3i(0, 4, 0), new Vec3i(0, 5, 0)};
    private static final Vec3i[] leaves = {new Vec3i(-1, 4, 0), new Vec3i(0, 4, -1), new Vec3i(0, 4, 1), new Vec3i(1, 4, 1), new Vec3i(1, 4, 0), new Vec3i(1, 4, -1), new Vec3i(1, 4, -2), new Vec3i(1, 4, 2), new Vec3i(-1, 4, -2), new Vec3i(-1, 4, 2), new Vec3i(-1, 4, 1), new Vec3i(-1, 4, -1), new Vec3i(-2, 4, 1), new Vec3i(-2, 4, 0), new Vec3i(0, 4, 2), new Vec3i(0, 4, -2), new Vec3i(-2, 4, -1), new Vec3i(-2, 4, -2), new Vec3i(-2, 4, 2), new Vec3i(2, 4, 0), new Vec3i(2, 4, 1), new Vec3i(2, 4, -1), new Vec3i(2, 4, -2), new Vec3i(2, 4, 2), new Vec3i(0, 5, 1), new Vec3i(0, 5, -1), new Vec3i(1, 5, 0), new Vec3i(-1, 5, 0), new Vec3i(1, 5, 1), new Vec3i(-1, 5, 1), new Vec3i(1, 5, -1), new Vec3i(-1, 5, -1), new Vec3i(1, 6, 0), new Vec3i(-1, 6, 0), new Vec3i(0, 6, 1), new Vec3i(0, 6, -1), new Vec3i(0, 6, 0)};

    public EntityDryadsGripAttack(World par1World) {
        super(par1World);
        this.logNum = 0;
        this.leafNum = 0;
        this.treePos = null;
        this.foundPos = false;
    }

    public EntityDryadsGripAttack(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.logNum = 0;
        this.leafNum = 0;
        this.treePos = null;
        this.foundPos = false;
    }

    public EntityDryadsGripAttack(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.logNum = 0;
        this.leafNum = 0;
        this.treePos = null;
        this.foundPos = false;
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (!this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.BLOCK && !this.foundPos) {
            this.foundPos = true;
            this.treePos = result.func_178782_a();
            this.field_70159_w = 0.0d;
            this.field_70181_x = 0.0d;
            this.field_70179_y = 0.0d;
            func_189654_d(true);
            this.field_70133_I = true;
            CustomParticleConfig config1 = new CustomParticleConfig();
            config1.createInstance().setParticle(ParticleInit.NATURE_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config1, this.treePos.func_177958_n(), this.treePos.func_177956_o() + 3, this.treePos.func_177952_p());
            this.logNum = 0;
            this.leafNum = 0;
            AxisAlignedBB checkBox = new AxisAlignedBB(this.treePos.func_177982_a(-15, -15, -15), this.treePos.func_177982_a(range, range, range));
            for (EntityLivingBase entity : this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox)) {
                if (!entity.equals(func_85052_h())) {
                    entity.func_70690_d(new PotionEffect(PotionInit.TETHERED, 400));
                }
            }
        }
    }

    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        if (!this.field_70170_p.field_72995_K && this.treePos != null) {
            if (this.logNum < logs.length) {
                BlockPos log = this.treePos.func_177971_a(logs[this.logNum]);
                this.logNum++;
                if (this.field_70170_p.func_175623_d(log)) {
                    this.field_70170_p.func_175656_a(log, BlockInit.logsNitro.func_176223_P().func_177226_a(BlockRotatedPillar.field_176298_M, EnumFacing.Axis.Y));
                }
            } else if (this.leafNum < leaves.length) {
                BlockPos leaf = this.treePos.func_177971_a(leaves[this.leafNum]);
                this.leafNum++;
                if (this.field_70170_p.func_175623_d(leaf)) {
                    this.field_70170_p.func_175656_a(leaf, BlockInit.leavesNitro.func_176223_P());
                }
                if (this.leafNum < leaves.length) {
                    BlockPos leaf2 = this.treePos.func_177971_a(leaves[this.leafNum]);
                    this.leafNum++;
                    if (this.field_70170_p.func_175623_d(leaf2)) {
                        this.field_70170_p.func_175656_a(leaf2, BlockInit.leavesNitro.func_176223_P());
                    }
                }
                func_184185_a(SoundInit.GENERIC_WEAPON_2, 0.5f, 0.7f + (this.field_70146_Z.nextFloat() * 1.3f));
            } else {
                this.leafNum = 0;
                this.logNum = 0;
                func_70106_y();
            }
        }
        super.func_70071_h_();
    }

    protected float func_70185_h() {
        return 0.0f;
    }
}
