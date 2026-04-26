package xol.lostinfinity.mob.render.starforge;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityMinimite;
import xol.lostinfinity.mob.model.starforge.ModelMinimite;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/starforge/RenderMinimite.class */
public class RenderMinimite extends RenderLiving<EntityMinimite> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/minimite.png");
    public static final ResourceLocation TEXTURES_ALTERNATE = new ResourceLocation("lostinfinity:textures/entity/starforge/minimite_charging.png");

    public RenderMinimite(RenderManager manager) {
        super(manager, new ModelMinimite(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityMinimite entitylivingbaseIn, float partialTickTime) {
        float scale = entitylivingbaseIn.myScale();
        GlStateManager.func_179152_a(scale, scale, scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityMinimite entity) {
        if (entity.isGrowing()) {
            if (entity.field_70173_aa % 6 <= 3) {
                return TEXTURES;
            }
            return TEXTURES_ALTERNATE;
        }
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityMinimite entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
