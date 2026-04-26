package xol.lostinfinity.block.tileentity;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
public class TileEntityGearbox extends TileEntity implements IMachine, ITickable {
    private int[] pegPositions = new int[6];
    private int[] gears = new int[6];
    private int[] placedGears = new int[6];
    private boolean active = false;
    private int ticks = 0;
    @Override // xol.lostinfinity.block.tileentity.IMachine
    public boolean getPowered() {
        ArrayList<IMachine> connectedMachines = getConnectedMachines(this, null);
        for (IMachine machine : connectedMachines) {
            if (machine instanceof TileEntityCombustionEngine) {
                return machine.getPowered();
            }
        }
        return false;
    }
    public void setActive(boolean active) {
        if (!this.active && active) {
            this.field_145850_b.func_184133_a((EntityPlayer) null, func_174877_v(), SoundEvents.field_187692_g, SoundCategory.BLOCKS, 1.5f, 1.0f);
        }
        this.active = active;
    }
    public int[] getPlacedGears() {
        return this.placedGears;
    }
    public void setPlacedGears(int[] placedGears) {
        this.placedGears = placedGears;
    }
    public void func_73660_a() {
        if (this.field_145850_b.field_72995_K && this.pegPositions[1] == 0) {
            this.pegPositions[0] = 0;
            int curPos = 0;
            int curGear = 0;
            for (int i = 0; i < this.gears.length; i++) {
                if (curGear == 0) {
                    curGear = this.field_145850_b.field_73012_v.nextInt(5) + 2;
                    this.gears[0] = curGear;
                } else {
                    int newGear = this.field_145850_b.field_73012_v.nextInt(5) + 2;
                    this.gears[i] = newGear;
                    curPos += newGear + curGear + 1;
                    curGear = newGear;
                    this.pegPositions[i] = curPos;
                }
            }
        }
        if (!this.field_145850_b.field_72995_K) {
            this.ticks++;
            if (this.ticks % 10 == 0) {
                doBlockUpdate();
            }
        }
    }
    public int[] getGears() {
        return this.gears;
    }
    public int[] getPegPositions() {
        return this.pegPositions;
    }
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.active = compound.func_74767_n("active");
    }
    public NBTTagCompound func_189515_b(NBTTagCompound compound) {
        compound.func_74757_a("active", this.active);
        return super.func_189515_b(compound);
    }
    public boolean getActive() {
        return this.active;
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
    private void doBlockUpdate() {
        IBlockState state = this.field_145850_b.func_180495_p(func_174877_v());
        this.field_145850_b.func_184138_a(func_174877_v(), state, state, 3);
    }
}
