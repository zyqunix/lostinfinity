package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityTentacleTrap;
import xol.lostinfinity.mob.model.ModelTentacleTrap;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderTentacleTrap.class */
public class RenderTentacleTrap extends RenderLiving<EntityTentacleTrap> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/tentacle_trap.png");

    public RenderTentacleTrap(RenderManager manager) {
        super(manager, new ModelTentacleTrap(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityTentacleTrap entity, float partialTickTime) {
        float height = 0.5f + (entity.getTargetHeight() / 3.0f);
        float scale = height;
        if (entity.field_70173_aa < 20) {
            scale = entity.field_70173_aa * (height / 20.0f);
        }
        GlStateManager.func_179152_a(scale, scale, scale);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityTentacleTrap entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityTentacleTrap entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
