package xol.lostinfinity.block.tileentity;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.damagesource.DeathMessage;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class TileEntityVoidVacuum extends TileEntity implements ITickable {
    boolean active = false;
    EntityLivingBase target = null;
    boolean hasPulled = false;
    public void resetPulled() {
        this.hasPulled = false;
    }
    public void setTarget(EntityLivingBase target) {
        this.target = target;
    }
    public EntityLivingBase getTarget() {
        return this.target;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean getActive() {
        return this.active;
    }
    public boolean shouldRenderInPass(int pass) {
        return true;
    }
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
    @SideOnly(Side.CLIENT)
    public double func_145833_n() {
        return 65536.0d;
    }
    public void func_73660_a() {
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_184138_a(func_174877_v(), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 2);
            if (this.active && !this.hasPulled && this.target != null) {
                Vec3d vecPos = new Vec3d(func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p());
                double dist = this.target.func_174791_d().func_72438_d(vecPos);
                if (dist < 1.5d) {
                    this.hasPulled = true;
                    this.active = false;
                    this.target.func_70606_j(0.0f);
                    DeathMessage.broadcastDeathMessage(this.target.func_184102_h(), TextFmt.Red + this.target.func_70005_c_() + " was sucked into the void.");
                    this.target = null;
                    EntityItem bone = new EntityItem(this.field_145850_b, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p(), new ItemStack(ItemInit.voidsplitBone));
                    bone.field_70159_w = 0.0d;
                    bone.field_70181_x = 0.0d;
                    bone.field_70179_y = 0.0d;
                    this.field_145850_b.func_72838_d(bone);
                    CustomParticleConfig config1 = new CustomParticleConfig();
                    config1.createInstance().setParticle(ParticleInit.CORRUPTION_MAGIC).setSpread(4.0d, 1.0d, 4.0d).setCount(10).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(this.field_145850_b, config1, this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o() + 1, this.field_174879_c.func_177952_p());
                    this.field_145850_b.func_184133_a((EntityPlayer) null, this.field_174879_c, SoundInit.ELECTRIC_WOOSH, SoundCategory.BLOCKS, 1.5f, 1.0f);
                    return;
                }
                Vec3d pullDir = vecPos.func_178788_d(this.target.func_174791_d()).func_72432_b();
                this.target.func_70024_g(pullDir.field_72450_a / 5.0d, (pullDir.field_72448_b / 5.0d) + 0.10000000149011612d, pullDir.field_72449_c / 5.0d);
                this.target.field_70133_I = true;
            }
        }
    }
    public NBTTagCompound func_189517_E_() {
        NBTTagCompound compound = super.func_189517_E_();
        if (this.target != null) {
            compound.func_186854_a("PlayerID", this.target.func_110124_au());
        } else {
            compound.func_82580_o("PlayerID");
        }
        return compound;
    }
    public void handleUpdateTag(NBTTagCompound tag) {
        if (tag.func_186855_b("PlayerID") && tag.func_186857_a("PlayerID") != null) {
            this.target = entityByID(tag.func_186857_a("PlayerID"));
            if (this.target != null && !this.target.field_70128_L) {
                this.active = true;
            } else {
                System.out.println("failed");
                this.active = false;
            }
        } else {
            this.active = false;
        }
        super.handleUpdateTag(tag);
    }
    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(func_174877_v(), 1, func_189517_E_());
    }
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.func_148857_g());
    }
    private EntityLivingBase entityByID(UUID id) {
        List<Entity> entityList = this.field_145850_b.func_72910_y();
        for (Entity entity : entityList) {
            if (entity.func_110124_au().equals(id) && (entity instanceof EntityLivingBase)) {
                return (EntityLivingBase) entity;
            }
        }
        return null;
    }
}
