package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityImmaterial;
import xol.lostinfinity.util.data.IMaxAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityKillerVine.class */
public class TileEntityKillerVine extends TileEntity implements ITickable, IMaxAttack {
    private int ticks = 0;
    private boolean killer = false;
    private UUID placer = null;
    private ArrayList<VineNode> nodes = new ArrayList<>();

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityKillerVine$VineNode.class */
    public class VineNode {
        private EntityLivingBase entity;
        private int growth = 0;

        public VineNode(EntityLivingBase entity) {
            this.entity = entity;
        }

        public EntityLivingBase getEntity() {
            return this.entity;
        }

        public int getGrowth() {
            return this.growth;
        }

        public void grow() {
            this.growth++;
        }
    }

    public void func_73660_a() {
        EntityPlayer placerEntity;
        this.ticks++;
        if (this.killer) {
            for (IEntityOwnable iEntityOwnable : this.field_145850_b.func_72872_a(EntityLivingBase.class, new AxisAlignedBB(this.field_174879_c.func_177982_a(-15, -1, -15), this.field_174879_c.func_177982_a(15, 15, 15)))) {
                if (iEntityOwnable != null && !((EntityLivingBase) iEntityOwnable).field_70128_L && !(iEntityOwnable instanceof EntityImmaterial) && (this.placer == null || !this.placer.equals(iEntityOwnable.func_110124_au()))) {
                    if (!(iEntityOwnable instanceof IEntityOwnable) || iEntityOwnable.func_184753_b() == null || !iEntityOwnable.func_184753_b().equals(this.placer)) {
                        ArrayList<VineNode> toRemove = new ArrayList<>();
                        boolean found = false;
                        int growth = 0;
                        Iterator<VineNode> it = this.nodes.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            VineNode node = it.next();
                            if (node.getEntity() == null || node.getEntity().field_70128_L) {
                                toRemove.add(node);
                            } else if (node.getEntity() == iEntityOwnable) {
                                node.grow();
                                growth = node.getGrowth();
                                found = true;
                                break;
                            }
                        }
                        this.nodes.removeAll(toRemove);
                        if (!found) {
                            this.nodes.add(new VineNode(iEntityOwnable));
                        }
                        if (!this.field_145850_b.field_72995_K && growth >= 20) {
                            double xDiff = (-((EntityLivingBase) iEntityOwnable).field_70165_t) + ((double) this.field_174879_c.func_177958_n()) + 0.5d;
                            double yDiff = (-((EntityLivingBase) iEntityOwnable).field_70163_u) + ((double) this.field_174879_c.func_177956_o()) + 1.0d;
                            double zDiff = (-((EntityLivingBase) iEntityOwnable).field_70161_v) + ((double) this.field_174879_c.func_177952_p()) + 0.5d;
                            Vec3d pullDir = new Vec3d(xDiff, yDiff, zDiff).func_72432_b();
                            double dist = iEntityOwnable.func_174791_d().func_72438_d(new Vec3d(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p()));
                            if (dist > 10.0d) {
                                iEntityOwnable.func_70024_g(pullDir.field_72450_a / 3.0d, (pullDir.field_72448_b / 3.0d) + 0.05d, pullDir.field_72449_c / 3.0d);
                            } else {
                                iEntityOwnable.func_70024_g(pullDir.field_72450_a / 10.0d, (pullDir.field_72448_b / 10.0d) + 0.05d, pullDir.field_72449_c / 10.0d);
                                if (dist < 3.0d) {
                                    if (this.placer != null) {
                                        EntityPlayer placerPlayer = this.field_145850_b.func_152378_a(this.placer);
                                        if (placerPlayer != null) {
                                            boolean killed = IMaxAttack.dealTrueDamage(placerPlayer, iEntityOwnable, iEntityOwnable.func_110138_aP() * 0.1f).wasTargetKilled();
                                            if (killed && (iEntityOwnable instanceof EntityPlayer)) {
                                                placerPlayer.func_191521_c(new ItemStack(ItemInit.entangledHeart));
                                            }
                                        }
                                    } else {
                                        IMaxAttack.dealPotionDamage(iEntityOwnable, iEntityOwnable.func_110138_aP() * 0.1f);
                                    }
                                }
                            }
                            ((EntityLivingBase) iEntityOwnable).field_70133_I = true;
                        }
                    }
                }
            }
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.placer == null && (placerEntity = this.field_145850_b.func_184137_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 10.0d, false)) != null) {
                this.placer = placerEntity.func_110124_au();
            }
            if (this.ticks % 10 == 0) {
                doBlockUpdate();
                if (!this.killer && BlockInit.killerVine.getCropAge(this.field_145850_b.func_180495_p(this.field_174879_c)) == 7) {
                    this.killer = true;
                }
            }
        }
    }

    public boolean isKiller() {
        return this.killer;
    }

    public ArrayList<VineNode> getNodes() {
        return this.nodes;
    }

    public UUID getPlacer() {
        return getPlacer();
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        super.func_189515_b(compound);
        compound.func_74757_a("killer", this.killer);
        if (this.placer != null) {
            compound.func_186854_a("Placer", this.placer);
        }
        return compound;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.killer = compound.func_74767_n("killer");
        this.placer = compound.func_186857_a("Placer");
    }

    @Nullable
    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(func_174877_v(), 0, func_189517_E_());
    }

    public NBTTagCompound func_189517_E_() {
        return func_189515_b(new NBTTagCompound());
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.func_148857_g());
    }

    protected void doBlockUpdate() {
        IBlockState state = this.field_145850_b.func_180495_p(func_174877_v());
        this.field_145850_b.func_184138_a(func_174877_v(), state, state, 3);
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        if (world.func_180495_p(pos).func_177230_c() == BlockInit.killerVine) {
            return false;
        }
        return true;
    }

    public void setPlacer(EntityPlayer placer) {
        this.placer = placer.func_110124_au();
    }
}
