package xol.lostinfinity.block.tileentity;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
public class TileEntityGenerator extends TileEntity {
    private UUID myPlacer = null;
    public void func_145839_a(NBTTagCompound compound) {
        if (compound != null && compound.func_186855_b("teplace")) {
            this.myPlacer = compound.func_186857_a("teplace");
        }
        super.func_145839_a(compound);
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        UUID uuid = this.myPlacer == null ? UUID.fromString("00000000-0000-0000-0000-000000000000") : this.myPlacer;
        compound.func_186854_a("teplace", uuid);
        return super.func_189515_b(compound);
    }
    public void setMyPlacer(UUID the_id) {
        this.myPlacer = the_id;
        func_70296_d();
    }
    public UUID getMyPlacer() {
        return this.myPlacer;
    }
}
