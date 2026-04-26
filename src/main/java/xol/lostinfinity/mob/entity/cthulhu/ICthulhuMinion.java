package xol.lostinfinity.mob.entity.cthulhu;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/cthulhu/ICthulhuMinion.class */
public interface ICthulhuMinion {
    void setOwner(EntityCthulhu entityCthulhu);

    EntityCthulhu getOwner();

    default void write(NBTTagCompound tag) {
        if (getOwner() != null) {
            tag.func_186854_a("owner", getOwner().func_110124_au());
        }
    }

    default void read(NBTTagCompound tag, Entity minion) {
        UUID uuid;
        if (minion.field_70170_p.field_72995_K || (uuid = tag.func_186857_a("owner")) == null) {
            return;
        }
        Entity entity = minion.field_70170_p.func_73046_m().func_175576_a(uuid);
        if (entity instanceof EntityCthulhu) {
            setOwner((EntityCthulhu) entity);
            getOwner().registerMinion(minion);
        }
    }
}
