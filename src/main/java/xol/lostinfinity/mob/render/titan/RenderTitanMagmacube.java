package xol.lostinfinity.mob.render.titan;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanMagmacube;
import xol.lostinfinity.mob.model.deviant.ModelDeviantMagmacube;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/titan/RenderTitanMagmacube.class */
public class RenderTitanMagmacube extends RenderLiving<EntityTitanMagmacube> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/titan/deviantmagmacube_titan.png");

    public RenderTitanMagmacube(RenderManager manager) {
        super(manager, new ModelDeviantMagmacube(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityTitanMagmacube entitylivingbaseIn, float partialTickTime) {
        float scl = 5.5f + MathHelper.func_76126_a(entitylivingbaseIn.field_70173_aa * 0.1f);
        GlStateManager.func_179152_a(scl, scl, scl);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityTitanMagmacube entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityTitanMagmacube entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
