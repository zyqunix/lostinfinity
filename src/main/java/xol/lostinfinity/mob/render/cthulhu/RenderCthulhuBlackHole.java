package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuBlackHole;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuBlackHole.class */
public class RenderCthulhuBlackHole extends Render<EntityCthulhuBlackHole> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/particles/blackhole_portal.png");
    private static final ResourceLocation TEXTURE_RING = new ResourceLocation("lostinfinity:textures/particles/blackhole_ring.png");

    public RenderCthulhuBlackHole(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityCthulhuBlackHole livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityCthulhuBlackHole entity, double x, double y, double z, float entityYaw, float partialTicks) {
        int animationTick = entity.getAnimationTick();
        func_110776_a(TEXTURE);
        GlStateManager.func_179129_p();
        GlStateManager.func_179140_f();
        float growth = (float) MathHelper.func_151238_b(entity.getLastGrowth(), entity.getGrowth(), partialTicks);
        float rotation = (float) MathHelper.func_151238_b(entity.getLastRotation(), entity.getRotation(), partialTicks);
        float alpha = 1.0f;
        if (entity.field_70173_aa >= 175) {
            alpha = 1.0f - ((entity.field_70173_aa - 175) * 0.04f);
        }
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.2f);
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, alpha);
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b(rotation * 57.29578f, 0.0f, -1.0f, 0.0f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b((-0.5f) * growth, 0.0d, 0.5f * growth).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b((-0.5f) * growth, 0.0d, (-0.5f) * growth).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5f * growth, 0.0d, (-0.5f) * growth).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5f * growth, 0.0d, 0.5f * growth).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        func_110776_a(TEXTURE_RING);
        if (animationTick < 150) {
            float ratio = ((animationTick + partialTicks) % 5) / 5;
            float ringSize = (float) MathHelper.func_151238_b(4, 0.0d, ratio);
            int rings = Math.min(6 - ((animationTick - 120) / 5), 6);
            int startRing = Math.max((6 - 1) - (animationTick / 5), 0);
            for (int i = startRing; i < rings; i++) {
                GlStateManager.func_179094_E();
                GlStateManager.func_187444_a(entity.getRotation(i));
                if (i >= 6 - 2) {
                    GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, (((ratio + 6) - i) - 1.0f) * 0.5f);
                }
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                bufferbuilder.func_181662_b(-(ringSize + (i * 4)), 0.5d, ringSize + (i * 4)).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(-(ringSize + (i * 4)), 0.5d, -(ringSize + (i * 4))).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(ringSize + (i * 4), 0.5d, -(ringSize + (i * 4))).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(ringSize + (i * 4), 0.5d, ringSize + (i * 4)).func_187315_a(1.0d, 1.0d).func_181675_d();
                tessellator.func_78381_a();
                GlStateManager.func_179121_F();
            }
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179145_e();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuBlackHole entity) {
        return null;
    }
}
