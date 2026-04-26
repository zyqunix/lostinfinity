package xol.lostinfinity.mob.render.deviant;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantEvoker;
import xol.lostinfinity.mob.model.deviant.ModelDeviantEvoker;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/deviant/RenderDeviantEvoker.class */
public class RenderDeviantEvoker extends RenderLiving<EntityDeviantEvoker> {
    private float scale;

    public RenderDeviantEvoker(RenderManager manager) {
        super(manager, new ModelDeviantEvoker(), 0.5f);
        this.scale = 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityDeviantEvoker entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityDeviantEvoker entity) {
        return entity.getCurTexture();
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityDeviantEvoker entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.field_77045_g = entity.getCurModel();
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityDeviantEvoker entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
