package xol.lostinfinity.mob.entity.sea.leviathan;
public class EntityLeviathanTail extends EntityLeviathanSegment {
    public EntityLeviathanTail(EntityLeviathanController parent, EntityLeviathanSegment parentSegment) {
        super(parent, parentSegment, 1.375f, 1.0f);
        this.dSocketOffset = 0.9375f;
        this.dOriginDistance = 0.9375f;
    }
}
