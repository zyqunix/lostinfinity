package xol.lostinfinity.mob.entity.mount;

import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.item.classify.IMovingSoundSource;
import xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount;
import xol.lostinfinity.mob.entity.misc.EntityCourseRing;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/mount/EntityJetMount.class */
public class EntityJetMount extends EntityMultipleLivesMount implements IMovingSoundSource {
    private static final double ACCELERATION = 1.0d;
    private static final double AIR_DRAG = 0.1d;
    private static final int NUM_RINGS = 15;
    private int ringCount;
    private ArrayList<UUID> ringIds;

    public EntityJetMount(World worldIn) {
        super(worldIn);
        this.ringCount = 0;
        this.ringIds = new ArrayList<>();
        func_70105_a(1.0f, 1.0f);
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    protected void func_184651_r() {
    }

    public void func_70106_y() {
        super.func_70106_y();
        if (!this.field_70170_p.field_72995_K) {
            clearRings();
        }
    }

    private void clearRings() {
        if (this.ringIds != null && !this.ringIds.isEmpty()) {
            for (UUID ringId : this.ringIds) {
                Entity entity = this.field_70170_p.func_73046_m().func_175576_a(ringId);
                if (entity != null) {
                    ((EntityCourseRing) entity).func_70106_y();
                }
            }
            this.ringIds.clear();
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLivesMount
    public void func_191986_a(float strafe, float vertical, float forward) {
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 30 == 0) {
            playSoundAround(SoundInit.DRAGON_FIRE_BREATH, SoundCategory.PLAYERS, this, 5.0f, 0.3f, false, 0);
        }
        if (func_184207_aI() && func_82171_bF() && !this.field_70128_L) {
            EntityPlayerSP entityPlayerSP = (EntityLivingBase) func_184179_bs();
            if (entityPlayerSP != null) {
                func_70101_b(((EntityLivingBase) entityPlayerSP).field_70177_z, ((EntityLivingBase) entityPlayerSP).field_70125_A * 0.5f);
                this.field_70126_B = this.field_70177_z;
                this.field_70761_aq = this.field_70177_z;
                this.field_70759_as = this.field_70761_aq;
                if (this.field_70170_p.field_72995_K && entityPlayerSP == Minecraft.func_71410_x().field_71439_g) {
                    Vec3d accel = Minecraft.func_71410_x().field_71439_g.func_70040_Z().func_186678_a(ACCELERATION);
                    Vec3d trail = LMath.fastNormalize(new Vec3d(0.0d, 0.0d, 0.0d).func_178788_d(accel));
                    if (this.field_70173_aa % 2 == 0) {
                        double d = 0.0d;
                        while (true) {
                            double i = d;
                            if (i >= 6.0d) {
                                break;
                            }
                            this.field_70170_p.func_175688_a(ParticleInit.FLAME_LARGE, ((((this.field_70146_Z.nextDouble() * 0.5d) - 0.25d) + this.field_70165_t) + (trail.field_72450_a * i)) - (func_189651_aD().field_72450_a * 2.0d), (((((this.field_70146_Z.nextDouble() * 0.5d) - 0.25d) + this.field_70163_u) + (((double) this.field_70131_O) / 2.0d)) + (trail.field_72448_b * i)) - (func_189651_aD().field_72448_b * 2.0d), ((((this.field_70146_Z.nextDouble() * 0.5d) - 0.25d) + this.field_70161_v) + trail.field_72449_c) - (func_189651_aD().field_72449_c * 2.0d), 0.0d, AIR_DRAG, 0.0d, new int[0]);
                            d = i + 0.2d;
                        }
                    }
                    this.field_70159_w += accel.field_72450_a * 0.05d;
                    this.field_70181_x += accel.field_72448_b * 0.05d;
                    this.field_70179_y += accel.field_72449_c * 0.05d;
                    this.field_70159_w += (-0.1d) * this.field_70159_w * 0.05d;
                    this.field_70181_x += (-0.1d) * this.field_70181_x * 0.05d;
                    this.field_70179_y += (-0.1d) * this.field_70179_y * 0.05d;
                } else {
                    this.field_70159_w = 0.0d;
                    this.field_70181_x = 0.0d;
                    this.field_70179_y = 0.0d;
                }
                func_70659_e((float) func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
                boolean prevGround = this.field_70122_E;
                this.field_70122_E = true;
                double velX = this.field_70159_w;
                double velY = this.field_70181_x;
                double velZ = this.field_70179_y;
                originalTravel(0.0f, 0.0f, 0.0f);
                this.field_70159_w = velX;
                this.field_70181_x = velY;
                this.field_70179_y = velZ;
                this.field_70122_E = prevGround;
                this.prevSpeed = this.speed;
                this.speed = (float) LMath.fastLength(this.field_70159_w, 0.0d, this.field_70179_y);
            }
        } else {
            originalTravel(strafe, vertical, forward);
        }
        updateOnGroundState();
    }

    public void startCourse(EntityPlayer player) {
        this.ringCount = 0;
        placeRings(player.func_174811_aO(), func_180425_c(), 0);
        Entity entity = this.field_70170_p.func_73046_m().func_175576_a(this.ringIds.get(0));
        if (entity != null) {
            ((EntityCourseRing) entity).setNext();
        }
        player.func_145747_a(new TextComponentString(TextFmt.Gold + "Navigate through the rings!"));
    }

    private void placeRings(EnumFacing facing, BlockPos pos, int i) {
        Vec3i forwardDir;
        EnumFacing nextFacing;
        if (i == NUM_RINGS) {
            return;
        }
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[facing.ordinal()]) {
            case 1:
                forwardDir = new Vec3i(-1, 0, 0);
                nextFacing = EnumFacing.NORTH;
                break;
            case 2:
                forwardDir = new Vec3i(0, 0, -1);
                nextFacing = EnumFacing.EAST;
                break;
            case 3:
                forwardDir = new Vec3i(1, 0, 0);
                nextFacing = EnumFacing.SOUTH;
                break;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                forwardDir = new Vec3i(0, 0, 1);
                nextFacing = EnumFacing.WEST;
                break;
            default:
                forwardDir = new Vec3i(0, 0, 1);
                nextFacing = EnumFacing.WEST;
                break;
        }
        int randForward = this.field_70146_Z.nextInt(20) + 40;
        int randX = this.field_70146_Z.nextInt(17) - 8;
        int randZ = this.field_70146_Z.nextInt(17) - 8;
        int randY = this.field_70146_Z.nextInt(7);
        BlockPos chunkPos = new BlockPos(pos.func_177958_n() + randX + (forwardDir.func_177958_n() * randForward), 0, pos.func_177952_p() + randZ + (forwardDir.func_177952_p() * randForward));
        if (!this.field_70170_p.func_175726_f(chunkPos).func_177410_o()) {
            this.field_70170_p.func_175726_f(chunkPos).func_76631_c();
        }
        int height = this.field_70170_p.func_189649_b(pos.func_177958_n() + randX + (forwardDir.func_177958_n() * randForward), pos.func_177952_p() + randZ + (forwardDir.func_177952_p() * randForward)) + randY + 10;
        BlockPos curPos = new BlockPos(pos.func_177958_n() + randX + (forwardDir.func_177958_n() * randForward), height, pos.func_177952_p() + randZ + (forwardDir.func_177952_p() * randForward));
        EntityCourseRing ring = new EntityCourseRing(this.field_70170_p);
        ring.setOwner((EntityPlayer) func_184179_bs());
        ring.func_70634_a(curPos.func_177958_n(), curPos.func_177956_o(), curPos.func_177952_p());
        ring.setFacing(facing);
        ring.setIndex(i);
        this.field_70170_p.func_72838_d(ring);
        if (ring != null && !ring.field_70128_L) {
            this.ringIds.add(ring.func_110124_au());
            if (this.field_70146_Z.nextInt(3) == 0) {
                placeRings(nextFacing, curPos, i + 1);
                return;
            } else {
                placeRings(facing, curPos, i + 1);
                return;
            }
        }
        placeRings(facing, pos, height);
    }

    /* JADX INFO: renamed from: xol.lostinfinity.mob.entity.mount.EntityJetMount$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/mount/EntityJetMount$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public int getRingIndex() {
        return this.ringCount;
    }

    public void progressCourse(EntityPlayer player) {
        Entity entity;
        this.ringCount++;
        if (this.ringCount == NUM_RINGS) {
            this.ringCount = 0;
            player.func_145747_a(new TextComponentString(TextFmt.Green + "Course Complete!"));
            player.func_191521_c(new ItemStack(ItemInit.navigationMechanism, 1));
            func_70106_y();
            return;
        }
        if (this.ringIds != null && this.ringIds.size() > this.ringCount && (entity = this.field_70170_p.func_73046_m().func_175576_a(this.ringIds.get(this.ringCount))) != null && !entity.field_70128_L && this.field_70170_p.field_72996_f.contains(entity)) {
            if (!this.field_70170_p.func_72964_e(entity.field_70176_ah, entity.field_70164_aj).func_177410_o()) {
                this.field_70170_p.func_72964_e(entity.field_70176_ah, entity.field_70164_aj).func_76631_c();
            }
            ((EntityCourseRing) entity).setNext();
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        for (int i = 0; i < this.ringIds.size(); i++) {
            tag.func_186854_a("ringId" + i, this.ringIds.get(i));
        }
    }

    @Override // xol.lostinfinity.mob.entity.base.EntityMultipleLives
    public void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.ringIds.clear();
        for (int i = 0; i < NUM_RINGS; i++) {
            this.ringIds.add(tag.func_186857_a("ringId" + i));
        }
    }
}
