package xol.lostinfinity.mob.render.starforge;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityClyster;
import xol.lostinfinity.mob.model.starforge.ModelClyster;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/starforge/RenderClyster.class */
public class RenderClyster extends RenderLiving<EntityClyster> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/clyster.png");
    public static final ResourceLocation TEXTURES_BLUE = new ResourceLocation("lostinfinity:textures/entity/starforge/clyster_blue.png");

    public RenderClyster(RenderManager manager) {
        super(manager, new ModelClyster(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityClyster entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.isEnlarged()) {
            GlStateManager.func_179152_a(2.5f * entitylivingbaseIn.getUltraScl(), 2.5f * entitylivingbaseIn.getUltraScl(), 2.5f * entitylivingbaseIn.getUltraScl());
        } else {
            GlStateManager.func_179152_a(2.5f, 2.5f, 2.5f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityClyster entity) {
        if (entity.isEnlarged()) {
            return TEXTURES_BLUE;
        }
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityClyster entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
