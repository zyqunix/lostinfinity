package xol.lostinfinity.mob.render.arena;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityElara;
import xol.lostinfinity.mob.model.boss.ModelElara;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/arena/RenderElara.class */
public class RenderElara extends RenderLiving<EntityElara> {
    public static final ResourceLocation TEXTURES_SIREN = new ResourceLocation("lostinfinity:textures/entity/elarasiren.png");
    public static final ResourceLocation TEXTURES_BOUNDLESS = new ResourceLocation("lostinfinity:textures/entity/elaraboundless.png");
    private float scale;

    public RenderElara(RenderManager manager) {
        super(manager, new ModelElara(), 0.5f);
        this.scale = 3.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityElara entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityElara entity) {
        switch (entity.getForm()) {
            case 0:
                return TEXTURES_SIREN;
            case 1:
                return TEXTURES_BOUNDLESS;
            default:
                return TEXTURES_SIREN;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityElara entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
