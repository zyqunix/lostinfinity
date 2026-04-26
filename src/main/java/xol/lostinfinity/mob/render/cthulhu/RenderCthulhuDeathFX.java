package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuDeathFX;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuDeathFX.class */
public class RenderCthulhuDeathFX extends Render<EntityCthulhuDeathFX> {
    private static final ResourceLocation TEXTURE_CORE = new ResourceLocation(Reference.MODID, "textures/particles/cthulhu/death_explosion.png");

    public RenderCthulhuDeathFX(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuDeathFX entity) {
        return null;
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityCthulhuDeathFX entity, double x, double y, double z, float entityYaw, float partialTicks) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.15f);
        GlStateManager.func_179112_b(770, 1);
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        func_110776_a(TEXTURE_CORE);
        float coreSize = Math.min((entity.field_70173_aa + partialTicks) / 40.0f, 1.0f) * 360.0f;
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, entity.field_70173_aa < 320 ? 1.0f : 1.0f - (((entity.field_70173_aa + partialTicks) - 320.0f) / 20.0f));
        if (coreSize > 0.0f) {
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferbuilder.func_181662_b(-coreSize, 0.0d, coreSize).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(-coreSize, 0.0d, -coreSize).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(coreSize, 0.0d, -coreSize).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(coreSize, 0.0d, coreSize).func_187315_a(1.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(-coreSize, coreSize, 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(-coreSize, -coreSize, 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(coreSize, -coreSize, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(coreSize, coreSize, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, -coreSize, coreSize).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, -coreSize, -coreSize).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, coreSize, -coreSize).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.0d, coreSize, coreSize).func_187315_a(1.0d, 1.0d).func_181675_d();
            tessellator.func_78381_a();
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179118_c();
        GlStateManager.func_179145_e();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }
}
