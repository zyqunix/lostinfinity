package xol.lostinfinity.mob.entity.misc;

import com.google.common.base.Optional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.mob.model.ModelMultiverseGhost;
import xol.lostinfinity.util.data.IMaxAttack;
import xol.lostinfinity.util.fx.IParticleSpawner;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityMultiverseGhost.class */
public class EntityMultiverseGhost extends EntityImmaterial {
    protected static final DataParameter<Optional<UUID>> COPIED_ID = EntityDataManager.func_187226_a(EntityMultiverseGhost.class, DataSerializers.field_187203_m);
    protected static final DataParameter<Byte> POSE = EntityDataManager.func_187226_a(EntityMultiverseGhost.class, DataSerializers.field_187191_a);
    protected static final ItemStack MULTIVERSAL_BLADE = new ItemStack(ItemInit.multiversalBlade);
    private Vec3d motion;
    private final Set<Entity> collided;
    private EntityPlayer owner;

    public EntityMultiverseGhost(World worldIn) {
        this(worldIn, Vec3d.field_186680_a);
    }

    public EntityMultiverseGhost(World worldIn, Vec3d motion) {
        super(worldIn);
        this.collided = new HashSet();
        func_70105_a(2.0f, 2.0f);
        func_184224_h(true);
        func_184611_a(EnumHand.MAIN_HAND, MULTIVERSAL_BLADE);
        this.field_70145_X = true;
        this.motion = motion;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getSkinForMyCopy() {
        AbstractClientPlayer copiedPlayer = getCopiedPlayer();
        if (copiedPlayer != null) {
            AbstractClientPlayer abPlayer = copiedPlayer;
            return abPlayer.func_110306_p();
        }
        return null;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(COPIED_ID, Optional.absent());
        this.field_70180_af.func_187214_a(POSE, Byte.valueOf((byte) this.field_70146_Z.nextInt(Pose.values().length)));
    }

    public void setCopiedPlay(EntityPlayer player) {
        this.owner = player;
        this.field_70180_af.func_187227_b(COPIED_ID, Optional.fromNullable(player.func_110124_au()));
    }

    public void setPose(Pose pose) {
        this.field_70180_af.func_187227_b(POSE, Byte.valueOf((byte) pose.ordinal()));
    }

    public Pose getPose() {
        byte b = ((Byte) this.field_70180_af.func_187225_a(POSE)).byteValue();
        if (Pose.values().length <= b) {
            return Pose.STRAIGHT;
        }
        return Pose.values()[b];
    }

    private EntityPlayer getCopiedPlayer() {
        if (((Optional) this.field_70180_af.func_187225_a(COPIED_ID)).orNull() != null) {
            return this.field_70170_p.func_152378_a((UUID) ((Optional) this.field_70180_af.func_187225_a(COPIED_ID)).get());
        }
        return null;
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityImmaterial
    public void func_70636_d() {
        this.field_70759_as = this.field_70177_z;
        this.field_70761_aq = this.field_70177_z;
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70173_aa >= 30) {
                func_70106_y();
                IParticleSpawner.spawnParticle(this.field_70170_p, 55, 0, this.field_70165_t, this.field_70163_u + ((double) (this.field_70131_O / 2.0f)), this.field_70161_v);
                return;
            }
            this.field_70159_w = this.motion.field_72450_a;
            this.field_70181_x = this.motion.field_72448_b;
            this.field_70179_y = this.motion.field_72449_c;
            this.field_70133_I = true;
            this.motion = this.motion.func_186678_a(1.149999976158142d);
        }
    }

    protected void func_82167_n(Entity entityIn) {
        if (entityIn != this.owner && !(entityIn instanceof EntityMultiverseGhost) && !this.collided.contains(entityIn) && (entityIn instanceof EntityLivingBase)) {
            Entity entity = (EntityLivingBase) entityIn;
            this.collided.add(entity);
            IMaxAttack.dealTrueDamage(this.owner, entity, entity.func_110138_aP() * 0.7f, Arrays.asList("Darkborn"));
        }
    }

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/misc/EntityMultiverseGhost$Pose.class */
    public enum Pose {
        STRAIGHT(new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(0.0d, 0.0d, 0.0d), new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(-22.5d, 0.0d, 0.0d), new Vec3d(5.0d, 22.0d, 0.0d), new Vec3d(37.0d, 21.6d, -28.0d), new Vec3d(-5.0d, 22.0d, 0.0d), new Vec3d(45.0d, -20.0d, 18.5d), new Vec3d(1.9d, 12.0d, -2.0d), new Vec3d(-45.0d, 0.0d, 0.0d), new Vec3d(-1.9d, 12.0d, 5.0d), new Vec3d(-47.5d, 0.0d, 0.0d), 0.0f, 0.0f, 0.0f, 0.0f),
        OVERHEAD(new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(0.0d, 0.0d, 0.0d), new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(37.5d, 0.0d, 0.0d), new Vec3d(5.0d, 22.0d, 0.0d), new Vec3d(-125.6867d, -22.9193d, 15.6263d), new Vec3d(-5.0d, 22.0d, 0.0d), new Vec3d(-125.6867d, 22.9193d, -15.6263d), new Vec3d(1.9d, 19.0d, -10.0d), new Vec3d(27.5d, 0.0d, 0.0d), new Vec3d(-1.9d, 14.0d, -7.0d), new Vec3d(-30.0d, 0.0d, 0.0d), 0.0f, 0.0f, 0.0f, 0.0f),
        THRUST(new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(-2.5534d, 27.4558d, -2.235d), new Vec3d(0.0d, 24.0d, 0.0d), new Vec3d(-32.8166d, 59.8098d, -36.7252d), new Vec3d(1.7611d, 20.589d, -3.7767d), new Vec3d(92.5d, 0.0d, 90.0d), new Vec3d(-2.2695d, 23.5961d, 4.8669d), new Vec3d(-101.5084d, 29.4987d, -95.7251d), new Vec3d(-5.0097d, 14.2438d, -3.4539d), new Vec3d(-58.2783d, 73.5874d, -21.0242d), new Vec3d(-2.2908d, 13.1267d, 4.9127d), new Vec3d(-65.0663d, 38.1243d, -46.1556d), -90.0f, 1.0f, 0.0f, 0.0f);

        public final Vec3d headOrigin;
        public final Vec3d bodyOrigin;
        public final Vec3d rightArmOrigin;
        public final Vec3d leftArmOrigin;
        public final Vec3d rightLegOrigin;
        public final Vec3d leftLegOrigin;
        public final Rotations headRot;
        public final Rotations bodyRot;
        public final Rotations rightArmRot;
        public final Rotations leftArmRot;
        public final Rotations rightLegRot;
        public final Rotations leftLegRot;
        public final float itemAngle;
        public final float itemAxisX;
        public final float itemAxisY;
        public final float itemAxisZ;

        Pose(Vec3d headPos, Vec3d headRot, Vec3d bodyPos, Vec3d bodyRot, Vec3d rightArmPos, Vec3d rightArmRot, Vec3d leftArmPos, Vec3d leftArmRot, Vec3d rightLegPos, Vec3d rightLegRot, Vec3d leftLegPos, Vec3d leftLegRot, float itemAngle, float itemAxisX, float itemAxisY, float itemAxisZ) {
            Vec3d ORIGIN = new Vec3d(0.0d, 24.0d, 0.0d);
            this.headOrigin = ORIGIN.func_178788_d(headPos);
            this.bodyOrigin = ORIGIN.func_178788_d(bodyPos);
            this.rightArmOrigin = ORIGIN.func_178788_d(rightArmPos);
            this.leftArmOrigin = ORIGIN.func_178788_d(leftArmPos);
            this.rightLegOrigin = ORIGIN.func_178788_d(rightLegPos);
            this.leftLegOrigin = ORIGIN.func_178788_d(leftLegPos);
            this.headRot = convert(headRot);
            this.bodyRot = convert(bodyRot);
            this.rightArmRot = convert(rightArmRot);
            this.leftArmRot = convert(leftArmRot);
            this.rightLegRot = convert(rightLegRot);
            this.leftLegRot = convert(leftLegRot);
            this.itemAngle = itemAngle;
            this.itemAxisX = itemAxisX;
            this.itemAxisY = itemAxisY;
            this.itemAxisZ = itemAxisZ;
        }

        public void applyPose(ModelMultiverseGhost model) {
            apply(model.field_78116_c, this.headOrigin, this.headRot);
            apply(model.field_78115_e, this.bodyOrigin, this.bodyRot);
            apply(model.field_178723_h, this.rightArmOrigin, this.rightArmRot);
            apply(model.field_178724_i, this.leftArmOrigin, this.leftArmRot);
            apply(model.field_178721_j, this.rightLegOrigin, this.rightLegRot);
            apply(model.field_178722_k, this.leftLegOrigin, this.leftLegRot);
        }

        public void offsetItem() {
            GlStateManager.func_179109_b(0.0f, 0.0f, 0.25f);
            GlStateManager.func_179114_b(this.itemAngle, this.itemAxisX, this.itemAxisY, this.itemAxisZ);
            GlStateManager.func_179109_b(0.0f, 0.0f, -0.25f);
        }

        private void apply(ModelRenderer renderer, Vec3d pos, Rotations rot) {
            renderer.field_78800_c = (float) pos.field_72450_a;
            renderer.field_78797_d = (float) pos.field_72448_b;
            renderer.field_78798_e = (float) (-pos.field_72449_c);
            renderer.field_78795_f = -rot.func_179415_b();
            renderer.field_78796_g = -rot.func_179416_c();
            renderer.field_78808_h = rot.func_179413_d();
        }

        private static Rotations convert(Vec3d vec3d) {
            return new Rotations((float) Math.toRadians(vec3d.field_72450_a), (float) Math.toRadians(vec3d.field_72448_b), (float) Math.toRadians(vec3d.field_72449_c));
        }
    }
}
