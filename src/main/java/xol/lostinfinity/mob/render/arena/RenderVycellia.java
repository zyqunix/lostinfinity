package xol.lostinfinity.mob.render.arena;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityVycellia;
import xol.lostinfinity.mob.model.boss.ModelVycellia;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/arena/RenderVycellia.class */
public class RenderVycellia extends RenderLiving<EntityVycellia> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/vycellia.png");

    public RenderVycellia(RenderManager manager) {
        super(manager, new ModelVycellia(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityVycellia vycellia, float partialTickTime) {
        if (vycellia.isEasy()) {
            GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
        } else {
            float scale = vycellia.getFormScale();
            GlStateManager.func_179152_a(scale, scale, scale);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityVycellia entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityVycellia entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
