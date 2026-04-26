package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySkybooster;
import xol.lostinfinity.mob.model.ModelSkybooster;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderSkybooster.class */
public class RenderSkybooster extends RenderLiving<EntitySkybooster> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/skybooster.png");

    public RenderSkybooster(RenderManager manager) {
        super(manager, new ModelSkybooster(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntitySkybooster entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale(), entitylivingbaseIn.getMyScale());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntitySkybooster entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntitySkybooster entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
