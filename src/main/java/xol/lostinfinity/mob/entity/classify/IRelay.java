package xol.lostinfinity.mob.entity.classify;

import net.minecraft.entity.Entity;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/entity/classify/IRelay.class */
public interface IRelay<T extends Entity> {
    /* JADX INFO: renamed from: getRelay */
    T mo307getRelay();

    void setId(int i);

    int getId();

    void setPos(double d, double d2, double d3, float f, float f2);

    double getX();

    double getY();

    double getZ();

    float getYaw();

    float getPitch();
}
