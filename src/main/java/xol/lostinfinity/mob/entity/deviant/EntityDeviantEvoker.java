package xol.lostinfinity.mob.entity.deviant;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.mob.entity.misc.EntitySandAttack;
import xol.lostinfinity.mob.model.deviant.ModelDeviantBat;
import xol.lostinfinity.mob.model.deviant.ModelDeviantCaveSpider;
import xol.lostinfinity.mob.model.deviant.ModelDeviantChicken;
import xol.lostinfinity.mob.model.deviant.ModelDeviantCreeper;
import xol.lostinfinity.mob.model.deviant.ModelDeviantEnderman;
import xol.lostinfinity.mob.model.deviant.ModelDeviantEvoker;
import xol.lostinfinity.mob.model.deviant.ModelDeviantHusk;
import xol.lostinfinity.mob.model.deviant.ModelDeviantLlama;
import xol.lostinfinity.mob.model.deviant.ModelDeviantPiglin;
import xol.lostinfinity.mob.model.deviant.ModelDeviantSkeleton;
import xol.lostinfinity.mob.model.deviant.ModelDeviantStray;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerBomb;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerFangs;
import xol.lostinfinity.projectile.entity.EntityDeviantSpit;
import xol.lostinfinity.projectile.entity.EntityEchoBlast;
import xol.lostinfinity.projectile.entity.EntityPlagueBlast;
import xol.lostinfinity.projectile.entity.EntitySkullShot;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.load.LootTableRegistry;
public class EntityDeviantEvoker extends EntityMultipleLives implements IMaxAttack {
    private static final DataParameter<Integer> INDEX = EntityDataManager.func_187226_a(EntityDeviantEvoker.class, DataSerializers.field_187192_b);
    private ResourceLocation curTexture;
    private ModelBase curModel;
    ArrayList<ModelBase> modelList;
    ArrayList<ResourceLocation> textureList;
    private boolean swapped;
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(INDEX, -1);
    }
    private int getIndex() {
        return ((Integer) this.field_70180_af.func_187225_a(INDEX)).intValue();
    }
    private void setIndex(int index) {
        this.field_70180_af.func_187227_b(INDEX, Integer.valueOf(index));
    }
    public ResourceLocation getCurTexture() {
        return this.curTexture;
    }
    private void skeleShot(EntityLivingBase target) {
        if (this.field_70173_aa % 12 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntitySkullShot shot = new EntitySkullShot(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.0f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
        }
    }
    private void huskShot() {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 25 == 0) {
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(30.0d))) {
                int yOffset = 1;
                boolean canRun = true;
                while (this.field_70170_p.func_175623_d(near_pl.func_180425_c().func_177982_a(0, yOffset, 0)) && canRun) {
                    yOffset++;
                    if (yOffset == 35) {
                        canRun = false;
                    }
                }
                EntitySandAttack attack = new EntitySandAttack(this.field_70170_p);
                attack.func_70107_b(near_pl.field_70165_t, near_pl.field_70163_u + ((double) yOffset), near_pl.field_70161_v);
                this.field_70170_p.func_72838_d(attack);
            }
        }
    }
    private void batShot(EntityLivingBase target) {
        if (this.field_70173_aa % 20 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityEchoBlast shot = new EntityEchoBlast(this.field_70170_p);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = ((target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u) - 0.5d;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 3.5f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_191255_dF, 1.0f, 1.0f);
        }
    }
    private void llamaShot(EntityLivingBase target) {
        if (this.field_70173_aa % 20 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityDeviantSpit shot = new EntityDeviantSpit(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = ((target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u) - 0.5d;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 3.5f, 0.0f);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_191255_dF, 1.0f, 1.0f);
        }
    }
    private void strayShot(EntityLivingBase target) {
        if (this.field_70173_aa % 18 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 8 + this.field_70170_p.field_73012_v.nextInt(4); i++) {
                    EntitySkullShot shot = new EntitySkullShot(this.field_70170_p, (target.field_70165_t - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d), target.field_70163_u + 15.0d, (target.field_70161_v - 4.0d) + (this.field_70170_p.field_73012_v.nextDouble() * 8.0d), 1.0f);
                    shot.setThrower(this);
                    shot.func_70186_c(0.0d, -0.05d, 0.0d, 0.7f, 0.0f);
                    this.field_70170_p.func_72838_d(shot);
                }
            }
            func_184185_a(SoundEvents.field_193784_dd, 1.0f, 1.0f);
        }
    }
    private void spiderShot(EntityLivingBase target) {
        if (this.field_70173_aa % 20 == 0 && target != null) {
            if (!this.field_70170_p.field_72995_K) {
                EntityPlagueBlast shot = new EntityPlagueBlast(this.field_70170_p, this);
                double d0 = target.field_70165_t - this.field_70165_t;
                double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 6.0f))) - shot.field_70163_u;
                double d2 = target.field_70161_v - this.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d2 * d2));
                shot.func_70186_c(d0, d1 + (d3 * 0.20000000298023224d), d2, 2.5f, 0.0f);
                shot.addPoison(2);
                this.field_70170_p.func_72838_d(shot);
            }
            func_184185_a(SoundEvents.field_191255_dF, 1.0f, 1.0f);
        }
    }
    private void chickenJump(EntityLivingBase target) {
        this.field_70143_R = -1.0f;
        if (this.field_70173_aa % 40 == 0 && target != null && (target instanceof EntityPlayer)) {
            EntityPlayer pl = (EntityPlayer) target;
            func_70024_g((pl.field_70165_t - this.field_70165_t) * 0.145d, 1.0d, (pl.field_70161_v - this.field_70161_v) * 0.145d);
            this.field_70133_I = true;
        }
    }
    private void creeperExplosion(EntityLivingBase target) {
        if (this.field_70173_aa % 50 == 0 && !this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72876_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, 7.0f, false);
            for (EntityPlayer near_pl : this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(4.0d, 4.0d, 4.0d))) {
                IMaxAttack.dealMaxHealth(this, near_pl, 3);
            }
        }
    }
    private void endermanInvis() {
        if (this.field_70173_aa % 120 == 0) {
            if (this.field_70170_p.field_72995_K) {
                for (int i = 0; i < 14; i++) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_WITCH, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N)), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
                }
            }
            this.field_70170_p.func_184134_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187534_aX, SoundCategory.HOSTILE, 1.5f, 0.5f + this.field_70170_p.field_73012_v.nextFloat(), true);
            func_70690_d(new PotionEffect(MobEffects.field_76441_p, 40));
        }
        if (func_82150_aj() && this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL, this.field_70165_t + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70163_u + (this.field_70146_Z.nextDouble() * ((double) this.field_70131_O))) - 0.25d, this.field_70161_v + ((this.field_70146_Z.nextDouble() - 0.5d) * ((double) this.field_70130_N) * 3.0d), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5d) * 2.0d, new int[0]);
        }
    }
    private void piglinJump(EntityLivingBase target) {
        this.field_70143_R = -1.0f;
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70122_E && this.field_70173_aa % 60 == 10) {
                this.field_70181_x = 1.2d;
                return;
            }
            if (this.field_70173_aa % 60 == 30) {
                EntityPlayer dashto = null;
                if (target == null || !(target instanceof EntityPlayer)) {
                    Iterator it = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_72314_b(15.0d, 15.0d, 15.0d)).iterator();
                    if (it.hasNext()) {
                        EntityPlayer near_pl = (EntityPlayer) it.next();
                        dashto = near_pl;
                        func_70624_b(near_pl);
                    }
                } else {
                    dashto = (EntityPlayer) target;
                }
                if (dashto != null) {
                    func_70024_g((dashto.field_70165_t - this.field_70165_t) * 0.245d, (dashto.field_70163_u - this.field_70163_u) * 0.145d, (dashto.field_70161_v - this.field_70161_v) * 0.245d);
                }
            }
        }
    }
    public void func_70636_d() {
        EntityLivingBase entitylivingbase;
        super.func_70636_d();
        EntityLivingBase target = func_70638_az();
        int index = getIndex();
        switch (index) {
            case 0:
                huskShot();
                break;
            case 1:
                skeleShot(target);
                break;
            case 2:
                batShot(target);
                break;
            case 3:
                strayShot(target);
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                llamaShot(target);
                break;
            case 5:
                chickenJump(target);
                break;
            case TileEntityFusionTable.BOARD_COLUMNS :
                creeperExplosion(target);
                break;
            case 7:
                endermanInvis();
                break;
            case 8:
                piglinJump(target);
                break;
            case 9:
                spiderShot(target);
                break;
        }
        if (this.field_70170_p.field_72995_K) {
            if (this.modelList.isEmpty() && this.textureList.isEmpty()) {
                this.modelList.add(new ModelDeviantHusk());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/devianthusk.png"));
                this.modelList.add(new ModelDeviantSkeleton());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantskeleton.png"));
                this.modelList.add(new ModelDeviantBat());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantbat.png"));
                this.modelList.add(new ModelDeviantStray());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantstray.png"));
                this.modelList.add(new ModelDeviantLlama());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantllama.png"));
                this.modelList.add(new ModelDeviantChicken());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantchicken.png"));
                this.modelList.add(new ModelDeviantCreeper());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantcreeper.png"));
                this.modelList.add(new ModelDeviantEnderman());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantenderman.png"));
                this.modelList.add(new ModelDeviantPiglin());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantpiglin.png"));
                this.modelList.add(new ModelDeviantCaveSpider());
                this.textureList.add(new ResourceLocation("lostinfinity:textures/entity/deviant/deviantcavespider.png"));
                return;
            }
            int i = getIndex();
            if (i >= 0 && i < this.modelList.size()) {
                this.curModel = this.modelList.get(i);
                this.curTexture = this.textureList.get(i);
                return;
            } else {
                this.curModel = new ModelDeviantEvoker();
                this.curTexture = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantevoker.png");
                return;
            }
        }
        if (this.field_70173_aa % 140 == 0) {
            if (!this.swapped) {
                setIndex(this.field_70146_Z.nextInt(9));
                this.swapped = true;
            } else {
                setIndex(-1);
                this.swapped = false;
            }
        }
        if (target != null && (target instanceof EntityDeviantEvokerVex)) {
            func_70624_b(null);
        }
        if (this.field_70173_aa % 600 == 20) {
            int count = 0;
            for (EntityDeviantEvokerVex entityDeviantEvokerVex : this.field_70170_p.func_72872_a(EntityDeviantEvokerVex.class, new AxisAlignedBB(func_180425_c()).func_186662_g(7.0d))) {
                count++;
            }
            if (count < 2) {
                for (int i2 = 0; i2 < 2; i2++) {
                    EntityDeviantEvokerVex bat = new EntityDeviantEvokerVex(this.field_70170_p);
                    bat.setTamedBy(this);
                    bat.func_70634_a((this.field_70165_t + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d, this.field_70163_u + 4.0d, (this.field_70161_v + (this.field_70146_Z.nextDouble() * 2.0d)) - 1.0d);
                    EntityLivingBase entitylivingbase2 = func_70638_az();
                    if (entitylivingbase2 != null) {
                        bat.func_70624_b(entitylivingbase2);
                    }
                    this.field_70170_p.func_72838_d(bat);
                }
            }
        }
        if (this.field_70173_aa % 500 > 400 && this.field_70173_aa % 10 == 0) {
            double angle = this.field_70146_Z.nextDouble() * 2.0d * 3.141592653589793d;
            EntityDeviantEvokerBomb shot = new EntityDeviantEvokerBomb(this.field_70170_p);
            shot.func_70107_b(this.field_70165_t, this.field_70163_u + 1.0d, this.field_70161_v);
            double velocity_x = 0.4d * Math.cos(angle);
            double velocity_z = 0.4000000059604645d * Math.sin(angle);
            shot.setThrower(this);
            shot.calculateVelocity(velocity_x, 0.5d, velocity_z);
            this.field_70170_p.func_72838_d(shot);
        }
        if (this.field_70173_aa % 100 == 0 && (entitylivingbase = func_70638_az()) != null) {
            double d0 = Math.min(entitylivingbase.field_70163_u, this.field_70163_u);
            double d1 = Math.max(entitylivingbase.field_70163_u, this.field_70163_u) + 1.0d;
            float f = (float) MathHelper.func_181159_b(entitylivingbase.field_70161_v - this.field_70161_v, entitylivingbase.field_70165_t - this.field_70165_t);
            if (func_70068_e(entitylivingbase) < 9.0d) {
                for (int i3 = 0; i3 < 5; i3++) {
                    float f1 = f + (i3 * 3.1415927f * 0.4f);
                    spawnFangs(this.field_70165_t + (((double) MathHelper.func_76134_b(f1)) * 1.5d), this.field_70161_v + (((double) MathHelper.func_76126_a(f1)) * 1.5d), d0, d1, f1, 0);
                }
                for (int k = 0; k < 8; k++) {
                    float f2 = f + (((k * 3.1415927f) * 2.0f) / 8.0f) + 1.2566371f;
                    spawnFangs(this.field_70165_t + (((double) MathHelper.func_76134_b(f2)) * 2.5d), this.field_70161_v + (((double) MathHelper.func_76126_a(f2)) * 2.5d), d0, d1, f2, 3);
                }
                return;
            }
            double d = -1.0471975511965976d;
            while (true) {
                double h = d;
                if (h <= 1.0571975511965976d) {
                    float f3 = f + ((float) h);
                    for (int l = 0; l < 16; l++) {
                        double d2 = 1.25d * ((double) (l + 1));
                        int j = 1 * l;
                        spawnFangs(this.field_70165_t + (((double) MathHelper.func_76134_b(f3)) * d2), this.field_70161_v + (((double) MathHelper.func_76126_a(f3)) * d2), d0, d1, f3, j);
                    }
                    d = h + 0.5235987755982988d;
                } else {
                    return;
                }
            }
        }
    }
    private void spawnFangs(double x, double z, double minHeight, double y, float yaw, int warmupTicks) {
        BlockPos blockpos = new BlockPos(x, y, z);
        boolean flag = false;
        double d0 = 0.0d;
        while (true) {
            if (!this.field_70170_p.func_175677_d(blockpos, true) && this.field_70170_p.func_175677_d(blockpos.func_177977_b(), true)) {
                if (!this.field_70170_p.func_175623_d(blockpos)) {
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    AxisAlignedBB axisalignedbb = iblockstate.func_185890_d(this.field_70170_p, blockpos);
                    if (axisalignedbb != null) {
                        d0 = axisalignedbb.field_72337_e;
                    }
                }
                flag = true;
            } else {
                blockpos = blockpos.func_177977_b();
                if (blockpos.func_177956_o() < MathHelper.func_76128_c(minHeight) - 1) {
                    break;
                }
            }
        }
        if (flag) {
            EntityDeviantEvokerFangs entityevokerfangs = new EntityDeviantEvokerFangs(this.field_70170_p, x, ((double) blockpos.func_177956_o()) + d0, z, yaw, warmupTicks, this);
            this.field_70170_p.func_72838_d(entityevokerfangs);
        }
    }
    public EntityDeviantEvoker(World worldIn) {
        super(worldIn);
        this.curTexture = new ResourceLocation("lostinfinity:textures/entity/deviant/deviantevoker.png");
        this.curModel = new ModelDeviantEvoker();
        this.modelList = new ArrayList<>();
        this.textureList = new ArrayList<>();
        this.swapped = false;
        func_70105_a(1.25f, 4.0f);
    }
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.32d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0d);
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 1);
            return true;
        }
        return false;
    }
    public ModelBase getCurModel() {
        return this.curModel;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 300;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void updateLifeAction() {
        int lifePercent = Math.round((100 * (numberOfLives() - getLivesCount())) / numberOfLives());
        AxisAlignedBB pullBox = new AxisAlignedBB(func_180425_c()).func_186662_g(45.0d);
        for (EntityPlayer entity : this.field_70170_p.func_72872_a(EntityPlayer.class, pullBox)) {
            entity.func_145747_a(new TextComponentString(TextFmt.Gold + "The Evoker is at " + lifePercent + "% health."));
        }
    }
    protected ResourceLocation func_184647_J() {
        return LootTableRegistry.ENTITIES_DEVIANTEVOKER;
    }
}
