package xol.lostinfinity.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/TileEntityChromaGame.class */
public class TileEntityChromaGame extends TileEntity implements ITickable {
    private EntityPlayer first = null;
    private EntityPlayer second = null;

    public void func_73660_a() {
    }

    public void setPlayers(EntityPlayer first, EntityPlayer second) {
        this.first = first;
        this.second = second;
    }

    public boolean isFirst(EntityPlayer check) {
        if (this.first != null && check.equals(this.first)) {
            return true;
        }
        return false;
    }

    public boolean isSecond(EntityPlayer check) {
        if (this.second != null && check.equals(this.second)) {
            return true;
        }
        return false;
    }

    public EntityPlayer getFirst() {
        return this.first;
    }

    public EntityPlayer getSecond() {
        return this.second;
    }
}
