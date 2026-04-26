package xol.lostinfinity.mob.entity.nebula;
import com.google.common.base.Optional;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xol.lostinfinity.block.misc.BlockNebulousBeacon;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.ai.EntityAIFloatAttack;
import xol.lostinfinity.mob.entity.base.EntityFloatingBase;
import xol.lostinfinity.projectile.entity.EntityNebulaSpell;
import xol.lostinfinity.util.data.IMaxAttack;
public class EntityNebulaWizard extends EntityFloatingBase implements IMaxAttack {
    private float modelAlpha;
    private static final DataParameter<Optional<BlockPos>> HOME_POS = EntityDataManager.func_187226_a(EntityNebulaWizard.class, DataSerializers.field_187201_k);
    private static final double SPELL_RANGE = 28.0d;
    public EntityNebulaWizard(World worldIn) {
        super(worldIn);
        this.modelAlpha = 0.0f;
        func_70105_a(2.0f, 2.3f);
        this.field_70765_h = new AIMoveControl(this);
        this.field_70145_X = true;
    }
    public EntityNebulaWizard(World worldIn, BlockPos parentBlock) {
        super(worldIn);
        this.modelAlpha = 0.0f;
        func_70105_a(2.0f, 2.3f);
        func_184212_Q().func_187227_b(HOME_POS, Optional.of(parentBlock));
        this.field_70765_h = new AIMoveControl(this);
        this.field_70145_X = true;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0d);
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2d);
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(800.0d);
        this.field_70765_h = new AIMoveControl(this);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected int numberOfLives() {
        return 7;
    }
    public float getModelAlpha() {
        return this.modelAlpha;
    }
    public boolean func_70652_k(Entity entity) {
        super.func_70652_k(entity);
        if (func_70638_az() != null) {
            IMaxAttack.dealMaxHealth(this, func_70638_az(), 2);
            return true;
        }
        return false;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase, xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(HOME_POS, Optional.absent());
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    public void func_70636_d() {
        super.func_70636_d();
        if (this.field_70170_p.field_72995_K) {
            if (this.modelAlpha < 1.0f) {
                this.modelAlpha += 0.04f;
                return;
            }
            return;
        }
        if (getHomePos().isPresent()) {
            IBlockState blockState = this.field_70170_p.func_180495_p(((BlockPos) getHomePos().get()).func_177982_a(0, -1, 0));
            if (!(blockState.func_177230_c() instanceof BlockNebulousBeacon)) {
                setHomePos(Optional.absent());
            }
        }
        if (((Optional) func_184212_Q().func_187225_a(HOME_POS)).isPresent()) {
            BlockPos homePos = (BlockPos) ((Optional) func_184212_Q().func_187225_a(HOME_POS)).get();
            if (homePos.func_177951_i(func_180425_c()) > 9.0d) {
                func_70605_aq().func_75642_a(homePos.func_177958_n(), homePos.func_177956_o(), homePos.func_177952_p(), 0.3d);
            }
        }
        if (this.field_70173_aa % 60 == 0) {
            List<EntityPlayer> list = this.field_70170_p.func_72872_a(EntityPlayer.class, func_174813_aQ().func_186662_g(SPELL_RANGE));
            for (EntityPlayer player : list) {
                if (!player.field_70128_L && !player.func_175149_v() && !player.func_184812_l_()) {
                    attackEntityWithRangedAttack(player);
                }
            }
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected void func_82167_n(Entity entityIn) {
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityFloatingBase
    protected EntityAIFloatAttack createShootAI() {
        return null;
    }
    protected SoundEvent func_184601_bQ(DamageSource damageSourceIn) {
        return SoundInit.NEBULA_WIZARD_HURT;
    }
    protected SoundEvent func_184639_G() {
        return SoundInit.NEBULA_WIZARD_AMBIENT;
    }
    protected SoundEvent func_184615_bR() {
        return SoundInit.NEBULA_WIZARD_DEATH;
    }
    private void attackEntityWithRangedAttack(EntityPlayer target) {
        EntityNebulaSpell darkSpell = new EntityNebulaSpell(this.field_70170_p, this);
        double d0 = target.field_70165_t - this.field_70165_t;
        double d1 = (target.func_174813_aQ().field_72338_b + ((double) (target.field_70131_O / 3.0f))) - darkSpell.field_70163_u;
        double d2 = target.field_70161_v - this.field_70161_v;
        darkSpell.func_70186_c(d0, d1, d2, 1.25f, 0.0f);
        this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.NEBULA_WIZARD_SPELL, SoundCategory.HOSTILE, 1.5f, 1.0f / ((func_70681_au().nextFloat() * 0.4f) + 0.8f));
        this.field_70170_p.func_72838_d(darkSpell);
    }
    public Optional<BlockPos> getHomePos() {
        return (Optional) func_184212_Q().func_187225_a(HOME_POS);
    }
    public void setHomePos(Optional<BlockPos> value) {
        func_184212_Q().func_187227_b(HOME_POS, value);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        Optional<BlockPos> home = getHomePos();
        if (home.isPresent()) {
            compound.func_74780_a("x", ((BlockPos) home.get()).func_177958_n());
            compound.func_74780_a("y", ((BlockPos) home.get()).func_177956_o());
            compound.func_74780_a("z", ((BlockPos) home.get()).func_177952_p());
        }
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        double x = compound.func_74769_h("x");
        double y = compound.func_74769_h("y");
        double z = compound.func_74769_h("z");
        BlockPos pos = new BlockPos(x, y, z);
        Optional<BlockPos> optPos = Optional.of(pos);
        setHomePos(optPos);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void trueDeathAction() {
        func_70106_y();
    }
    class AIMoveControl extends EntityMoveHelper {
        private final EntityLiving entityLiving;
        public AIMoveControl(EntityLiving vex) {
            super(vex);
            this.entityLiving = vex;
        }
        public void func_75641_c() {
            if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.field_75646_b - this.entityLiving.field_70165_t;
                double d1 = this.field_75647_c - this.entityLiving.field_70163_u;
                double d2 = this.field_75644_d - this.entityLiving.field_70161_v;
                double d3 = MathHelper.func_76133_a((d0 * d0) + (d1 * d1) + (d2 * d2));
                if (d3 < this.entityLiving.func_174813_aQ().func_72320_b()) {
                    this.field_188491_h = EntityMoveHelper.Action.WAIT;
                    this.entityLiving.field_70159_w *= 0.5d;
                    this.entityLiving.field_70181_x *= 0.5d;
                    this.entityLiving.field_70179_y *= 0.5d;
                    return;
                }
                this.entityLiving.field_70159_w += (d0 / d3) * 0.05d * this.field_75645_e;
                this.entityLiving.field_70181_x += (d1 / d3) * 0.05d * this.field_75645_e;
                this.entityLiving.field_70179_y += (d2 / d3) * 0.05d * this.field_75645_e;
                if (this.entityLiving.func_70638_az() == null) {
                    this.entityLiving.field_70177_z = (-((float) MathHelper.func_181159_b(this.entityLiving.field_70159_w, this.entityLiving.field_70179_y))) * 57.295776f;
                    this.entityLiving.field_70761_aq = this.entityLiving.field_70177_z;
                    return;
                }
                double d4 = this.entityLiving.func_70638_az().field_70165_t - this.entityLiving.field_70165_t;
                double d5 = this.entityLiving.func_70638_az().field_70161_v - this.entityLiving.field_70161_v;
                this.entityLiving.field_70177_z = (-((float) MathHelper.func_181159_b(d4, d5))) * 57.295776f;
                this.entityLiving.field_70761_aq = this.entityLiving.field_70177_z;
            }
        }
    }
}
