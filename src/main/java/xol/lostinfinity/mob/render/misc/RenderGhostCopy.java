package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityGhostCopy;
import xol.lostinfinity.mob.model.ModelGhostCopy;
public class RenderGhostCopy extends RenderLiving<EntityGhostCopy> {
    public RenderGhostCopy(RenderManager manager) {
        super(manager, new ModelGhostCopy(), 0.5f);
    }
    public void func_77041_b(EntityGhostCopy ghost, float partialTickTime) {
        float scale = ghost.getGhostScale();
        GlStateManager.func_179152_a(scale, scale, scale);
    }
    public ResourceLocation func_110775_a(EntityGhostCopy entity) {
        return entity.getSkinForMyCopy();
    }
}
