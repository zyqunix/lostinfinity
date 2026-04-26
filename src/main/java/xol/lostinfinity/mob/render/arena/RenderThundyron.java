package xol.lostinfinity.mob.render.arena;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.boss.EntityThundyron;
import xol.lostinfinity.mob.model.boss.ModelThundyron;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/arena/RenderThundyron.class */
public class RenderThundyron extends RenderLiving<EntityThundyron> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/thundyron.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/thundyron2.png");
    public static final ResourceLocation TEXTURES3 = new ResourceLocation("lostinfinity:textures/entity/thundyron3.png");

    public RenderThundyron(RenderManager manager) {
        super(manager, new ModelThundyron(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityThundyron entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityThundyron entity) {
        int textureSwitch = MathHelper.func_76141_d((entity.field_70173_aa % 15) / 5);
        switch (textureSwitch) {
            case 0:
                return TEXTURES;
            case 1:
                return TEXTURES2;
            case 2:
                return TEXTURES3;
            default:
                return TEXTURES;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityThundyron entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
