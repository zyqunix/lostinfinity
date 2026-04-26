package xol.lostinfinity.projectile.render;

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
import xol.lostinfinity.mob.entity.misc.EntityNuclearExplosion;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderNuclearExplosion.class */
public class RenderNuclearExplosion extends Render<EntityNuclearExplosion> {
    private static final ResourceLocation TEXTURE_RING = new ResourceLocation("lostinfinity:textures/particles/nuclear_ring.png");
    private static final ResourceLocation TEXTURE_CORE = new ResourceLocation("lostinfinity:textures/particles/bomber_explosion.png");

    public RenderNuclearExplosion(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: renamed from: shouldRender, reason: merged with bridge method [inline-methods] */
    public boolean func_177071_a(EntityNuclearExplosion livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityNuclearExplosion entity, double x, double y, double z, float entityYaw, float partialTicks) {
        int animationTick = entity.getAnimationTick();
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179140_f();
        GlStateManager.func_179147_l();
        GlStateManager.func_179092_a(516, 0.15f);
        GlStateManager.func_179112_b(770, 1);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        func_110776_a(TEXTURE_CORE);
        float coreSize = 0.0f;
        if (animationTick < 150) {
            coreSize = (animationTick + (6.0f * partialTicks)) / 30.0f;
        } else if (animationTick < 155) {
            coreSize = (float) MathHelper.func_151238_b(0.0d, 5.2d, ((155 - animationTick) - partialTicks) / 5.0f);
        } else if (animationTick < 200) {
            coreSize = (float) MathHelper.func_151238_b(0.0d, 360.0d, ((animationTick + partialTicks) - 160.0f) / 40.0f);
            if (animationTick > 180) {
                GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, (200 - animationTick) / 20.0f);
            }
        }
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
        func_110776_a(TEXTURE_RING);
        if (animationTick < 150) {
            float ratio = ((animationTick + partialTicks) % 5) / 5;
            float ringSize = (float) MathHelper.func_151238_b(10, 0.0d, ratio);
            int rings = Math.min(6 - ((animationTick - 120) / 5), 6);
            int startRing = Math.max((6 - 1) - (animationTick / 5), 0);
            for (int i = startRing; i < rings; i++) {
                GlStateManager.func_179094_E();
                GlStateManager.func_187444_a(entity.getRotation(i));
                if (i >= 6 - 2) {
                    GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, (((ratio + 6) - i) - 1.0f) * 0.5f);
                }
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                bufferbuilder.func_181662_b(-(ringSize + (i * 10)), 0.5d, ringSize + (i * 10)).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(-(ringSize + (i * 10)), 0.5d, -(ringSize + (i * 10))).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(ringSize + (i * 10), 0.5d, -(ringSize + (i * 10))).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(ringSize + (i * 10), 0.5d, ringSize + (i * 10)).func_187315_a(1.0d, 1.0d).func_181675_d();
                tessellator.func_78381_a();
                GlStateManager.func_179121_F();
            }
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179118_c();
        GlStateManager.func_179145_e();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityNuclearExplosion entity) {
        return null;
    }
}
