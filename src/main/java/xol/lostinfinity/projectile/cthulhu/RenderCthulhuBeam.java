package xol.lostinfinity.projectile.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/cthulhu/RenderCthulhuBeam.class */
public class RenderCthulhuBeam extends Render<EntityCthulhuBeam> {
    private static final ResourceLocation TEXTURE_BEAM = new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/beam.png");
    private static final ResourceLocation TEXTURE_PORTAL = new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/portal.png");

    public RenderCthulhuBeam(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityCthulhuBeam entity, double x, double y, double z, float yaw, float partialTicks) {
        float size;
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        enableMaxLighting();
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        GlStateManager.func_179114_b(entity.yaw, 0.0f, -1.0f, 0.0f);
        GlStateManager.func_179114_b(entity.pitch, 1.0f, 0.0f, 0.0f);
        if (entity.field_70173_aa >= 15 && entity.field_70173_aa < 30) {
            float size2 = 1.0f - (((entity.field_70173_aa + partialTicks) - 15.0f) / 15.0f);
            func_110776_a(TEXTURE_BEAM);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferbuilder.func_181662_b(0.0d, (-0.5d) * ((double) size2), 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, (-0.5d) * ((double) size2), 128.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, 0.5d * ((double) size2), 128.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, 0.5d * ((double) size2), 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b((-0.5d) * ((double) size2), 0.0d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b((-0.5d) * ((double) size2), 0.0d, 128.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.5d * ((double) size2), 0.0d, 128.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.5d * ((double) size2), 0.0d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
            tessellator.func_78381_a();
        }
        GlStateManager.func_179114_b((entity.field_70173_aa + partialTicks) * 3.0f, 0.0f, 0.0f, 1.0f);
        func_110776_a(TEXTURE_PORTAL);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        if (entity.field_70173_aa < 15) {
            size = (entity.field_70173_aa + partialTicks) / 15.0f;
        } else if (entity.field_70173_aa >= 30) {
            size = 1.0f - (((entity.field_70173_aa + partialTicks) - 30.0f) / 15.0f);
        } else {
            size = 1.0f;
        }
        bufferbuilder.func_181662_b(1.5d * ((double) size), (-1.5d) * ((double) size), 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-1.5d) * ((double) size), (-1.5d) * ((double) size), 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-1.5d) * ((double) size), 1.5d * ((double) size), 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(1.5d * ((double) size), 1.5d * ((double) size), 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuBeam entity) {
        return TEXTURE_BEAM;
    }

    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
