package xol.lostinfinity.projectile.entity;
import com.google.common.base.Optional;
import java.util.Arrays;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.item.basics.ItemChanneling;
import xol.lostinfinity.mob.entity.base.EntityMultipleLives;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;
import xol.lostinfinity.util.math.LMath;
public class EntityEmberShot extends EntityBaseThrowable {
    private static final DataParameter<ItemStack> ITEM_STACK = EntityDataManager.func_187226_a(EntityEmberShot.class, DataSerializers.field_187196_f);
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(EntityEmberShot.class, DataSerializers.field_187203_m);
    private static final DataParameter<Boolean> RELEASED = EntityDataManager.func_187226_a(EntityEmberShot.class, DataSerializers.field_187198_h);
    private int releaseTick;
    public EntityEmberShot(World worldIn) {
        super(worldIn);
        this.releaseTick = -1;
        func_70105_a(0.25f, 0.25f);
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(ITEM_STACK, ItemStack.field_190927_a);
        this.field_70180_af.func_187214_a(OWNER, Optional.absent());
        this.field_70180_af.func_187214_a(RELEASED, false);
    }
    public void setItemStack(ItemStack stack) {
        this.field_70180_af.func_187227_b(ITEM_STACK, stack);
    }
    public ItemStack getItemStack() {
        return (ItemStack) this.field_70180_af.func_187225_a(ITEM_STACK);
    }
    public void setReleased() {
        this.field_70180_af.func_187227_b(RELEASED, true);
    }
    public boolean isReleased() {
        return ((Boolean) this.field_70180_af.func_187225_a(RELEASED)).booleanValue();
    }
    @Nullable
    public EntityLivingBase func_85052_h() {
        if (this.field_70192_c != null) {
            return this.field_70192_c;
        }
        Optional<UUID> uuidOptional = (Optional) this.field_70180_af.func_187225_a(OWNER);
        if (uuidOptional.isPresent()) {
            this.field_70192_c = this.field_70170_p.func_152378_a((UUID) uuidOptional.get());
        }
        return this.field_70192_c;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void setThrower(EntityLivingBase throwset) {
        super.setThrower(throwset);
        this.field_70180_af.func_187227_b(OWNER, Optional.of(throwset.func_110124_au()));
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected void func_70184_a(RayTraceResult result) {
        if (isReleased() && !this.field_70170_p.field_72995_K && result.field_72313_a == RayTraceResult.Type.ENTITY && (result.field_72308_g instanceof EntityLivingBase) && result.field_72308_g != this.field_70192_c) {
            EntityMultipleLives entityMultipleLives = (EntityLivingBase) result.field_72308_g;
            double mX = ((EntityLivingBase) entityMultipleLives).field_70159_w;
            double mY = ((EntityLivingBase) entityMultipleLives).field_70181_x;
            double mZ = ((EntityLivingBase) entityMultipleLives).field_70179_y;
            if (!IMaxAttack.dealTrueDamage(this.field_70192_c, entityMultipleLives, entityMultipleLives.func_110138_aP() * 0.25f, Arrays.asList("Darkborn")).wasTargetKilled() && (entityMultipleLives instanceof EntityMultipleLives)) {
                entityMultipleLives.takeawayNumLives(3);
            }
            ((EntityLivingBase) entityMultipleLives).field_70159_w = mX;
            ((EntityLivingBase) entityMultipleLives).field_70181_x = mY;
            ((EntityLivingBase) entityMultipleLives).field_70179_y = mZ;
            CustomParticleConfig config = new CustomParticleConfig();
            config.createInstance().setParticle(ParticleInit.EXPLOSION_RING).setIgnoreRange(true);
            IParticleSpawner.spawnParticle(this.field_70170_p, config, func_174791_d());
            func_70106_y();
        }
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    public void func_70071_h_() {
        super.func_70071_h_();
        EntityLivingBase thrower = func_85052_h();
        if (thrower == null) {
            func_70106_y();
            return;
        }
        boolean released = isReleased();
        if (released && this.field_70173_aa - this.releaseTick >= 300) {
            func_70106_y();
            return;
        }
        boolean channeling = ItemChanneling.isChanneling(thrower, getItemStack());
        if (!channeling && !released) {
            setReleased();
            Vec3d dir = LMath.fastNormalize(new Vec3d(((double) getItemStack().func_77978_p().func_74760_g("targetX")) - this.field_70165_t, ((double) getItemStack().func_77978_p().func_74760_g("targetY")) - this.field_70163_u, ((double) getItemStack().func_77978_p().func_74760_g("targetZ")) - this.field_70161_v)).func_186678_a(4.0d);
            this.field_70159_w = dir.field_72450_a;
            this.field_70181_x = dir.field_72448_b;
            this.field_70179_y = dir.field_72449_c;
            this.field_70133_I = true;
            this.releaseTick = this.field_70173_aa;
        }
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_175682_a(ParticleInit.FLAME_MEDIUM, true, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0d, 0.0d, 0.0d, new int[0]);
        }
    }
    protected float func_70185_h() {
        return 0.0f;
    }
    @Override // xol.lostinfinity.projectile.entity.EntityBaseThrowable
    protected boolean willDespawn() {
        return false;
    }
}
