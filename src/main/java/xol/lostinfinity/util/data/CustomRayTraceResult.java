package xol.lostinfinity.util.data;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/data/CustomRayTraceResult.class */
public class CustomRayTraceResult {
    private boolean modified;
    private Vec3d block_result_position = null;
    private Vec3d block_grabbed_position = null;
    private final Set<Entity> entity_result = new HashSet();

    public CustomRayTraceResult() {
    }

    public CustomRayTraceResult(Vec3d pos, Entity entity) {
        setResultPos(pos);
        addResultEntity(entity);
    }

    public CustomRayTraceResult(Vec3d pos, Vec3d grabbed, Entity entity) {
        setResultPos(pos);
        addResultEntity(entity);
        setGrabbedVector(grabbed);
    }

    public boolean isModified() {
        return this.modified;
    }

    public void setResultPos(Vec3d pos) {
        this.modified = true;
        this.block_result_position = pos;
    }

    public BlockPos getResultPos() {
        return new BlockPos(this.block_result_position);
    }

    public Vec3d getResultVector() {
        return this.block_result_position;
    }

    public void addResultEntity(Entity pos) {
        this.modified = true;
        this.entity_result.add(pos);
    }

    public Entity getResultEntity() {
        if (this.entity_result.isEmpty()) {
            return null;
        }
        return this.entity_result.iterator().next();
    }

    public Set<Entity> getResultEntities() {
        return this.entity_result;
    }

    public void setGrabbedVector(Vec3d pos) {
        this.modified = true;
        this.block_grabbed_position = pos;
    }

    public Vec3d getGrabbedVector() {
        return this.block_grabbed_position;
    }
}
