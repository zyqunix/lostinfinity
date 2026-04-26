package xol.lostinfinity.block.tileentity;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import xol.lostinfinity.item.basics.ItemRemoteControl;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityRemoteControl.class */
public class TileEntityRemoteControl extends TileEntity implements ITickable {
    boolean active = false;
    private ItemRemoteControl controller = null;
    private int ticksExited = 0;
    private UUID placer = null;

    public void setController(ItemRemoteControl controller) {
        this.controller = controller;
    }

    public ItemRemoteControl getController() {
        return this.controller;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return this.active;
    }

    public int getExisted() {
        return this.ticksExited;
    }

    public void setPlacer(EntityPlayer placer) {
        this.placer = placer.func_110124_au();
        func_70296_d();
    }

    public UUID getPlacerID() {
        return this.placer;
    }

    public void func_73660_a() {
        EntityPlayer play;
        this.ticksExited++;
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_184138_a(func_174877_v(), this.field_145850_b.func_180495_p(func_174877_v()), this.field_145850_b.func_180495_p(func_174877_v()), 2);
            if (this.active && this.controller != null && this.placer != null && (play = this.field_145850_b.func_152378_a(this.placer)) != null) {
                this.controller.tickEffect(this, this.field_145850_b, func_174877_v(), play);
            }
        }
    }

    public NBTTagCompound func_189517_E_() {
        NBTTagCompound compound = super.func_189517_E_();
        compound.func_74757_a("Active", this.active);
        return compound;
    }

    public void handleUpdateTag(NBTTagCompound tag) {
        if (tag.func_74764_b("Active")) {
            this.active = tag.func_74767_n("Active");
        }
        super.handleUpdateTag(tag);
    }

    public void func_145839_a(NBTTagCompound compound) {
        if (compound != null && compound.func_186855_b("teplace")) {
            this.placer = compound.func_186857_a("teplace");
        }
        if (compound != null && compound.func_74764_b("isactive")) {
            this.active = compound.func_74767_n("isactive");
            compound.func_82580_o("isactive");
        }
        super.func_145839_a(compound);
    }

    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        compound.func_74757_a("isactive", this.active);
        UUID uuid = this.placer == null ? UUID.fromString("00000000-0000-0000-0000-000000000000") : this.placer;
        compound.func_186854_a("teplace", uuid);
        return super.func_189515_b(compound);
    }

    public SPacketUpdateTileEntity func_189518_D_() {
        return new SPacketUpdateTileEntity(func_174877_v(), 1, func_189517_E_());
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.func_148857_g());
    }
}
