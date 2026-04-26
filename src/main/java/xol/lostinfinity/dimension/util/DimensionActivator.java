package xol.lostinfinity.dimension.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.DimensionType;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/dimension/util/DimensionActivator.class */
public class DimensionActivator {
    public static void transferEntity(Entity e, DimensionType dimTravel) {
        if (e == null || dimTravel == null || e.func_184102_h() == null) {
            return;
        }
        e.func_130014_f_().field_73011_w.func_186058_p();
        e.changeDimension(dimTravel.func_186068_a(), new BasicTeleporter(e.func_184102_h().func_71218_a(dimTravel.func_186068_a()), e.field_70165_t, e.field_70163_u, e.field_70161_v));
    }

    public static void transferEntityWithCoords(Entity e, DimensionType dimTravel, double xGo, double yGo, double zGo) {
        if (e == null || dimTravel == null || e.func_184102_h() == null) {
            return;
        }
        e.func_130014_f_().field_73011_w.func_186058_p();
        e.changeDimension(dimTravel.func_186068_a(), new BasicTeleporter(e.func_184102_h().func_71218_a(dimTravel.func_186068_a()), xGo, yGo, zGo, true));
    }
}
