package xol.lostinfinity.block.tileentity.render;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.block.tileentity.TileEntityEternalBeacon;
import xol.lostinfinity.util.math.LMath;
public class RenderEternalBeacon extends TileEntitySpecialRenderer<TileEntityEternalBeacon> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/particles/eternal_beacon_beam.png");
    public void func_192841_a(TileEntityEternalBeacon te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te.getTickRemaining() <= 0) {
            return;
        }
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 1);
        enableMaxLighting();
        func_147499_a(TEXTURE);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x + 0.5d, y, z + 0.5d);
        float offset = ((te.getActiveTick() - 1) + partialTicks) * 0.25f * 0.017453292f;
        float delta = (360.0f / 24.0f) * 0.017453292f;
        float radius = te.getRadius();
        double height = -1.0d;
        for (int i = 0; i < 24.0f; i++) {
            double x1 = MathHelper.func_76126_a(offset + (i * delta)) * radius;
            double z1 = MathHelper.func_76134_b(offset + (i * delta)) * radius;
            double x2 = MathHelper.func_76126_a(offset + ((i + 1) * delta)) * radius;
            double z2 = MathHelper.func_76134_b(offset + ((i + 1) * delta)) * radius;
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
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
    }
    public boolean func_188185_a(TileEntityEternalBeacon te) {
        return true;
    }
    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
