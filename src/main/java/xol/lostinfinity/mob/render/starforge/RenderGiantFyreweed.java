package xol.lostinfinity.mob.render.starforge;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGiantFyreweed;
import xol.lostinfinity.mob.model.starforge.ModelGiantFyreweed;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/starforge/RenderGiantFyreweed.class */
public class RenderGiantFyreweed extends RenderLiving<EntityGiantFyreweed> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/giantfyreweed.png");

    public RenderGiantFyreweed(RenderManager manager) {
        super(manager, new ModelGiantFyreweed(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityGiantFyreweed fyreweed, float partialTickTime) {
        if (fyreweed.field_70173_aa > 40) {
            GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
        } else {
            float scale = fyreweed.field_70173_aa * 0.075f;
            GlStateManager.func_179152_a(scale, scale, scale);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGiantFyreweed entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityGiantFyreweed entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
