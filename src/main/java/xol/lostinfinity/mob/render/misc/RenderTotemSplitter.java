package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityTotemSplitter;
import xol.lostinfinity.mob.model.ModelTotem;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderTotemSplitter.class */
public class RenderTotemSplitter extends RenderLiving<EntityTotemSplitter> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/totem/totem_splitter.png");
    public static final ResourceLocation TEXTURE_NICRONIUM_RING = new ResourceLocation("lostinfinity:textures/particles/nicronium_ring.png");

    public RenderTotemSplitter(RenderManager manager) {
        super(manager, new ModelTotem(), 0.5f);
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityTotemSplitter entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
        func_110776_a(TEXTURE_NICRONIUM_RING);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.2f);
        float growth = 0.1f + ((entity.field_70173_aa % 40) * 0.3f);
        float a = 1.0f - ((entity.field_70173_aa % 40) * 0.022224f);
        if (a <= 0.0f) {
            a = 0.0f;
        }
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, a);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        double xPos = x + 0.0d;
        double yPos = y + 0.5d;
        double zPos = z + 0.0d;
        bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) ((-0.5f) * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) ((-0.5f) * growth)) + zPos).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(((double) (0.5f * growth)) + xPos, 1.0d + yPos, ((double) (0.5f * growth)) + zPos).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityTotemSplitter entity) {
        return TEXTURES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: applyRotations, reason: merged with bridge method [inline-methods] */
    public void func_77043_a(EntityTotemSplitter entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
