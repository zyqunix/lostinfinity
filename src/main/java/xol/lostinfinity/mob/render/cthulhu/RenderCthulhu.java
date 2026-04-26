package xol.lostinfinity.mob.render.cthulhu;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.mob.model.cthulhu.ModelCthulhu;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.math.LMath;
public class RenderCthulhu extends RenderLiving<EntityCthulhu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/cthulhu.png");
    private static final ResourceLocation PORTAL = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/cthulhu_portal.png");
    private static final ResourceLocation BARRIER_1 = new ResourceLocation(Reference.MODID, "textures/particles/cthulhu/barrier_1.png");
    private static final ResourceLocation BARRIER_2 = new ResourceLocation(Reference.MODID, "textures/particles/cthulhu/barrier_2.png");
    public RenderCthulhu(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCthulhu(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityCthulhu entity) {
        return TEXTURE;
    }
    public void func_76986_a(EntityCthulhu entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        enableMaxLighting();
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x, y, z);
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((entity.field_70173_aa + partialTicks) * 3.0f, 0.0f, 1.0f, 0.0f);
        func_110776_a(PORTAL);
        float ratio = entity.isActivated() ? 1.0f : (entity.field_70173_aa + partialTicks) / 280.0f;
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(30.0f * ratio, 0.1d, (-30.0f) * ratio).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-30.0f) * ratio, 0.1d, (-30.0f) * ratio).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b((-30.0f) * ratio, 0.1d, 30.0f * ratio).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(30.0f * ratio, 0.1d, 30.0f * ratio).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        if (entity.isBarrierActive()) {
            GlStateManager.func_179112_b(770, 1);
            func_110776_a(entity.getPhase() == 2 ? BARRIER_1 : BARRIER_2);
            float offset = (entity.field_70173_aa + partialTicks) * 0.25f * 0.017453292f;
            float delta = (360.0f / 24.0f) * 0.017453292f;
            double height = -1.0d;
            for (int i = 0; i < 24.0f; i++) {
                double x1 = MathHelper.func_76126_a(offset + (i * delta)) * 48.0f;
                double z1 = MathHelper.func_76134_b(offset + (i * delta)) * 48.0f;
                double x2 = MathHelper.func_76126_a(offset + ((i + 1) * delta)) * 48.0f;
                double z2 = MathHelper.func_76134_b(offset + ((i + 1) * delta)) * 48.0f;
                if (height < 0.0d) {
                    height = LMath.fastLength(x2 - x1, 0.0d, z2 - z1);
                }
                float f = -16.0f;
                while (true) {
                    float j = f;
                    if (j < 256.0f) {
                        float r = MathHelper.func_76131_a((256.0f - j) / 256.0f, 0.0f, 1.0f);
                        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, r);
                        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                        bufferbuilder.func_181662_b(x2, j, z2).func_187315_a(1.0d, 0.0d).func_181675_d();
                        bufferbuilder.func_181662_b(x1, j, z1).func_187315_a(0.0d, 0.0d).func_181675_d();
                        bufferbuilder.func_181662_b(x1, ((double) j) + height, z1).func_187315_a(0.0d, 1.0d).func_181675_d();
                        bufferbuilder.func_181662_b(x2, ((double) j) + height, z2).func_187315_a(1.0d, 1.0d).func_181675_d();
                        tessellator.func_78381_a();
                        f = (float) (((double) j) + height);
                    }
                }
            }
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
    }
    public void func_77041_b(EntityCthulhu entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }
    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
