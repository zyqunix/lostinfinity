package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityGhostCopy;
import xol.lostinfinity.mob.model.ModelGhostCopy;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderGhostCopy.class */
public class RenderGhostCopy extends RenderLiving<EntityGhostCopy> {
    public RenderGhostCopy(RenderManager manager) {
        super(manager, new ModelGhostCopy(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityGhostCopy ghost, float partialTickTime) {
        float scale = ghost.getGhostScale();
        GlStateManager.func_179152_a(scale, scale, scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGhostCopy entity) {
        return entity.getSkinForMyCopy();
    }
}
