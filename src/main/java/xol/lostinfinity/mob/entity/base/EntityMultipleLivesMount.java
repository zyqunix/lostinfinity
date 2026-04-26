package xol.lostinfinity.mob.entity.base;

import com.google.common.base.Optional;
import java.lang.reflect.Field;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xol.lostinfinity.util.data.CustomDamageResult;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/base/EntityMultipleLivesMount.class */
public class EntityMultipleLivesMount extends EntityMultipleLives implements IEntityOwnable {
    protected float ascendSpeed;
    protected boolean isActuallyOnGround;
    protected float prevSpeed;
    protected float speed;
    protected EntityPlayer owner;
    private static final DataParameter<Optional<UUID>> OWNER_ID = EntityDataManager.func_187226_a(EntityMultipleLivesMount.class, DataSerializers.field_187203_m);
    private static final Field VEHICLE_FLOATING_TICK_COUNT = ObfuscationReflectionHelper.findField(NetHandlerPlayServer.class, "field_184346_E");

    static {
        VEHICLE_FLOATING_TICK_COUNT.setAccessible(true);
    }

    public EntityMultipleLivesMount(World worldIn) {
        super(worldIn);
        this.ascendSpeed = 1.0f;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(OWNER_ID, Optional.absent());
    }

    public void func_70071_h_() {
        if (this.field_70173_aa % 20 == 0) {
            resetFloatTime();
        }
        super.func_70071_h_();
    }

    public void setOwner(EntityPlayer player) {
        this.owner = player;
        this.field_70180_af.func_187227_b(OWNER_ID, Optional.of(player.func_110124_au()));
    }

    public void onDriverCommand(EntityPlayer driver) {
    }

    public void onDriverDamaged(Entity driver, CustomDamageResult result) {
    }

    protected void func_184231_a(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    public void func_180430_e(float distance, float damageMultiplier) {
    }

    public boolean func_82171_bF() {
        return func_184179_bs() instanceof EntityLivingBase;
    }

    public double func_70042_X() {
        return super.func_70042_X();
    }

    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Nullable
    public Entity func_184179_bs() {
        if (func_184188_bt().isEmpty()) {
            return null;
        }
        return (Entity) func_184188_bt().get(0);
    }

    public void func_191986_a(float strafe, float vertical, float forward) {
        if (func_184207_aI() && func_82171_bF() && !this.field_70128_L) {
            EntityPlayerSP entityPlayerSP = (EntityLivingBase) func_184179_bs();
            if (entityPlayerSP != null) {
                func_70101_b(((EntityLivingBase) entityPlayerSP).field_70177_z, ((EntityLivingBase) entityPlayerSP).field_70125_A * 0.5f);
                this.field_70126_B = this.field_70177_z;
                this.field_70761_aq = this.field_70177_z;
                this.field_70759_as = this.field_70761_aq;
                float strafe2 = ((EntityLivingBase) entityPlayerSP).field_70702_br * 0.5f;
                float forward2 = ((EntityLivingBase) entityPlayerSP).field_191988_bg;
                if (this.field_70170_p.field_72995_K && entityPlayerSP == Minecraft.func_71410_x().field_71439_g) {
                    MovementInput input = Minecraft.func_71410_x().field_71439_g.field_71158_b;
                    this.field_70181_x = input.field_78901_c ? this.ascendSpeed : input.field_78899_d ? -this.ascendSpeed : 0.0d;
                } else {
                    this.field_70181_x = 0.0d;
                }
                func_70659_e((float) func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
                boolean prevGround = this.field_70122_E;
                this.field_70122_E = true;
                originalTravel(strafe2, vertical, forward2);
                this.field_70122_E = prevGround;
                this.prevSpeed = this.speed;
                this.speed = (float) LMath.fastLength(this.field_70159_w, 0.0d, this.field_70179_y);
            }
        } else {
            originalTravel(strafe, vertical, forward);
        }
        updateOnGroundState();
    }

    protected void originalTravel(float strafe, float vertical, float forward) {
        super.func_191986_a(strafe, vertical, forward);
    }

    protected void resetFloatTime() {
        EntityPlayerMP entityPlayerMPFunc_184179_bs = func_184179_bs();
        if (entityPlayerMPFunc_184179_bs instanceof EntityPlayerMP) {
            try {
                VEHICLE_FLOATING_TICK_COUNT.set(entityPlayerMPFunc_184179_bs.field_71135_a, 0);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to reset vehicle floating tick!");
            }
        }
    }

    protected void updateOnGroundState() {
        this.isActuallyOnGround = this.field_70170_p.func_180495_p(func_180425_c().func_177977_b()).func_185904_a() != Material.field_151579_a;
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (this.field_70170_p.field_72995_K) {
            return !player.func_70093_af();
        }
        if (!player.func_70093_af()) {
            player.func_184220_m(this);
            return true;
        }
        return false;
    }

    public boolean canDismount() {
        return this.field_70128_L || this.field_70122_E || func_110143_aJ() <= 0.0f;
    }

    public boolean isActuallyOnGround() {
        return this.isActuallyOnGround;
    }

    public float getPrevSpeed() {
        return this.prevSpeed;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Nullable
    public UUID func_184753_b() {
        return (UUID) ((Optional) this.field_70180_af.func_187225_a(OWNER_ID)).orNull();
    }

    @Nullable
    /* JADX INFO: renamed from: getOwner, reason: merged with bridge method [inline-methods] */
    public EntityPlayer func_70902_q() {
        if (this.owner == null) {
            Optional<UUID> ownerId = (Optional) this.field_70180_af.func_187225_a(OWNER_ID);
            if (ownerId.isPresent()) {
                this.owner = this.field_70170_p.func_152378_a((UUID) ownerId.get());
            }
        }
        return this.owner;
    }
}
