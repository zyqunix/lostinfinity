package xol.lostinfinity.block.tileentity;

import java.util.ArrayList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/IMachine.class */
public interface IMachine {
    default ArrayList<IMachine> getConnectedMachines(TileEntity te, ArrayList<IMachine> machines) {
        IMachine iMachineFunc_175625_s;
        if (machines == null) {
            machines = new ArrayList<>();
        }
        if (machines.contains(this)) {
            return machines;
        }
        machines.add(this);
        for (BlockPos checkPos : BlockPos.func_177980_a(te.func_174877_v().func_177982_a(-1, -1, -1), te.func_174877_v().func_177982_a(1, 1, 1))) {
            if (!checkPos.equals(te.func_174877_v()) && (iMachineFunc_175625_s = te.func_145831_w().func_175625_s(checkPos)) != null && (iMachineFunc_175625_s instanceof IMachine)) {
                IMachine newMachine = iMachineFunc_175625_s;
                if (!machines.contains(newMachine)) {
                    machines.addAll(newMachine.getConnectedMachines(iMachineFunc_175625_s, machines));
                }
            }
        }
        return machines;
    }

    default boolean getPowered() {
        return false;
    }
}
