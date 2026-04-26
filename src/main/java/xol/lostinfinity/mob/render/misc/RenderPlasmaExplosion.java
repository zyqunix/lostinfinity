package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaExplosion;
import xol.lostinfinity.mob.model.ModelPlasmaExplosion;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderPlasmaExplosion.class */
public class RenderPlasmaExplosion extends RenderLiving<EntityPlasmaExplosion> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/plasma_explosion.png");

    public RenderPlasmaExplosion(RenderManager manager) {
        super(manager, new ModelPlasmaExplosion(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityPlasmaExplosion entitylivingbaseIn, float partialTickTime) {
        float scale = 4 + entitylivingbaseIn.getExplScale();
        GlStateManager.func_179152_a(scale, scale, scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityPlasmaExplosion entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityPlasmaExplosion entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
