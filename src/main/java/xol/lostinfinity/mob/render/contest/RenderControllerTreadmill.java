package xol.lostinfinity.mob.render.contest;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerTreadmill;
import xol.lostinfinity.mob.model.contest.ModelGrandmaster;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/contest/RenderControllerTreadmill.class */
public class RenderControllerTreadmill extends RenderLiving<EntityControllerTreadmill> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/grandmaster.png");

    public RenderControllerTreadmill(RenderManager manager) {
        super(manager, new ModelGrandmaster(), 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityControllerTreadmill entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(9.0f, 9.0f, 9.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityControllerTreadmill entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityControllerTreadmill entity) {
        return TEXTURES;
    }
}
