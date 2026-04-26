package xol.lostinfinity.mob.entity.sea.seaserpent;
public class EntitySeaSerpentTail extends EntitySeaSerpentSegment {
    public EntitySeaSerpentTail(EntitySeaSerpentController parent, EntitySeaSerpentSegment parentSegment) {
        super(parent, parentSegment, 1.375f, 1.0f);
        this.dSocketOffset = 0.9375f;
        this.dOriginDistance = 0.9375f;
    }
}
