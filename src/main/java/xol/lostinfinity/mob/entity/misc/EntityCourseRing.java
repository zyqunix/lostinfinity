package xol.lostinfinity.mob.entity.misc;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.mob.entity.mount.EntityJetMount;
public class EntityCourseRing extends Entity {
    protected static final DataParameter<EnumFacing> FACING = EntityDataManager.func_187226_a(EntityCourseRing.class, DataSerializers.field_187202_l);
    protected static final DataParameter<Integer> INDEX = EntityDataManager.func_187226_a(EntityCourseRing.class, DataSerializers.field_187192_b);
    protected static final DataParameter<Boolean> NEXT = EntityDataManager.func_187226_a(EntityCourseRing.class, DataSerializers.field_187198_h);
    private UUID ownerId;
    public EntityCourseRing(World worldIn) {
        super(worldIn);
        this.ownerId = null;
        func_70105_a(1.0f, 1.0f);
    }
    public void func_70030_z() {
        Vec3d rightDir;
        super.func_70030_z();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > 10) {
            boolean found = false;
            for (EntityPlayerMP player : this.field_70170_p.func_73046_m().func_184103_al().func_181057_v()) {
                if (player.func_110124_au().equals(this.ownerId)) {
                    found = true;
                }
            }
            if (!found) {
                func_70106_y();
            }
        }
        if (this.field_70170_p.field_72995_K && this.field_70173_aa % 10 == 0) {
            switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[getFacing().ordinal()]) {
                case 1:
                    rightDir = new Vec3d(0.0d, 0.0d, -1.0d);
                    break;
                case 2:
                    rightDir = new Vec3d(1.0d, 0.0d, 0.0d);
                    break;
                case 3:
                    rightDir = new Vec3d(0.0d, 0.0d, 1.0d);
                    break;
                case TileEntityFusionTable.BOARD_ROWS :
                    rightDir = new Vec3d(-1.0d, 0.0d, 0.0d);
                    break;
                default:
                    rightDir = new Vec3d(-1.0d, 0.0d, 0.0d);
                    break;
            }
            double d = 0.0d;
            while (true) {
                double i = d;
                if (i < 6.283185307179586d) {
                    double x = rightDir.field_72450_a * Math.cos(i) * 3.0d;
                    double z = rightDir.field_72449_c * Math.cos(i) * 3.0d;
                    double y = Math.sin(i) * 3.0d;
                    if (getNext()) {
                        this.field_70170_p.func_175688_a(ParticleInit.LIGHT_FIZZLE, this.field_70165_t + x, this.field_70163_u + y, this.field_70161_v + z, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, new int[0]);
                    } else {
                        this.field_70170_p.func_175688_a(ParticleInit.DARK_FIZZLE, this.field_70165_t + x, this.field_70163_u + y, this.field_70161_v + z, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, (this.field_70146_Z.nextDouble() - 0.5d) * 0.01d, new int[0]);
                    }
                    d = i + 0.2d;
                } else {
                    return;
                }
            }
        }
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];
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
    private EnumFacing getFacing() {
        return (EnumFacing) this.field_70180_af.func_187225_a(FACING);
    }
    private int getIndex() {
        return ((Integer) this.field_70180_af.func_187225_a(INDEX)).intValue();
    }
    public void func_70100_b_(EntityPlayer entityIn) {
        super.func_70100_b_(entityIn);
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            EntityJetMount entityJetMountFunc_184208_bv = entityIn.func_184208_bv();
            if (entityJetMountFunc_184208_bv instanceof EntityJetMount) {
                EntityJetMount jetMount = entityJetMountFunc_184208_bv;
                if (getNext()) {
                    jetMount.progressCourse(entityIn);
                    this.field_70170_p.func_184133_a((EntityPlayer) null, func_180425_c(), SoundInit.GAME_DING, SoundCategory.PLAYERS, 1.0f, 0.8f + (this.field_70146_Z.nextFloat() * 0.4f));
                    func_70106_y();
                }
            }
        }
    }
    protected void func_70088_a() {
        this.field_70180_af.func_187214_a(FACING, EnumFacing.NORTH);
        this.field_70180_af.func_187214_a(INDEX, 0);
        this.field_70180_af.func_187214_a(NEXT, false);
    }
    public void setFacing(EnumFacing facing) {
        this.field_70180_af.func_187227_b(FACING, facing);
    }
    public void setIndex(int i) {
        this.field_70180_af.func_187227_b(INDEX, Integer.valueOf(i));
    }
    protected void func_70037_a(NBTTagCompound compound) {
        this.ownerId = compound.func_186857_a("ownerID");
    }
    protected void func_70014_b(NBTTagCompound compound) {
        compound.func_186854_a("ownerID", this.ownerId);
    }
    private boolean getNext() {
        return ((Boolean) this.field_70180_af.func_187225_a(NEXT)).booleanValue();
    }
    public void setNext() {
        this.field_70180_af.func_187227_b(NEXT, true);
    }
    public void setOwner(EntityPlayer owner) {
        this.ownerId = owner.func_110124_au();
    }
}
