package xol.lostinfinity.mob.render.murk;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntityCaveTerror;
import xol.lostinfinity.mob.model.murk.ModelCaveTerror;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/murk/RenderCaveTerror.class */
public class RenderCaveTerror extends RenderLiving<EntityCaveTerror> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/caveterror.png");
    public static final ResourceLocation TEXTURES_GLOW = new ResourceLocation("lostinfinity:textures/entity/murk/caveterror_lit.png");

    public RenderCaveTerror(RenderManager manager) {
        super(manager, new ModelCaveTerror(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityCaveTerror entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCaveTerror entity) {
        if (entity.field_70173_aa % 12 < 6) {
            return TEXTURES_GLOW;
        }
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityCaveTerror entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
