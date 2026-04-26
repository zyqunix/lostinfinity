package xol.lostinfinity.mob.render.labyrinth;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.labyrinth.EntityGloop;
import xol.lostinfinity.mob.model.labyrinth.ModelGloop;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/labyrinth/RenderGloop.class */
public class RenderGloop extends RenderLiving<EntityGloop> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/gloop.png");

    public RenderGloop(RenderManager manager) {
        super(manager, new ModelGloop(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityGloop entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityGloop entitylivingbaseIn, float partialTickTime) {
        float scl = 1.0f + (0.3f * MathHelper.func_76126_a(entitylivingbaseIn.field_70173_aa * 0.2f));
        GlStateManager.func_179152_a(scl, scl, scl);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityGloop entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
