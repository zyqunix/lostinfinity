package xol.lostinfinity.block.tileentity.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.block.tileentity.TileEntityTeslaTower;
import xol.lostinfinity.util.math.LMath;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/render/RenderTeslaTower.class */
public class RenderTeslaTower extends TileEntitySpecialRenderer<TileEntityTeslaTower> {
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_YELLOW = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt.png");
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_BRIGHT = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt_bright.png");
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_BLUE = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt_blue.png");

    /* JADX INFO: renamed from: render, reason: merged with bridge method [inline-methods] */
    public void func_192841_a(TileEntityTeslaTower te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!te.isActive()) {
            return;
        }
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        enableMaxLighting();
        if (te.getTickExisted() % (te.getRandom1() + 1) == 0) {
            if (te.getRandom1() > 10) {
                GlStateManager.func_179112_b(770, 1);
            } else {
                GlStateManager.func_179084_k();
                return;
            }
        }
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        bindTextureForTE(te);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179094_E();
        GlStateManager.func_179137_b(x + 0.5d, y + 0.5d, z + 0.5d);
        for (BlockPos pos : te.getConnected()) {
            GlStateManager.func_179094_E();
            float offset = te.getTickExisted() % (te.getRandom1() + 1) == 0 ? te.getRandom3() - 0.5f : 0.0f;
            double targetX = pos.func_177958_n() - te.func_174877_v().func_177958_n();
            double targetY = pos.func_177956_o() - te.func_174877_v().func_177956_o();
            double targetZ = pos.func_177952_p() - te.func_174877_v().func_177952_p();
            Vec3d delta = new Vec3d(targetX, targetY, targetZ);
            Rotations rotations = LMath.toPitchYaw(delta);
            GlStateManager.func_179114_b(rotations.func_179416_c(), 0.0f, -1.0f, 0.0f);
            GlStateManager.func_179114_b(rotations.func_179415_b(), 1.0f, 0.0f, 0.0f);
            double length = LMath.fastLength(delta);
            for (int i = 0; i < length - 1.0d; i++) {
                bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                bufferbuilder.func_181662_b(0.01d, -0.5d, offset + i).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(0.01d, -0.5d, offset + i + 1.0f).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(0.01d, 0.5d, offset + i + 1.0f).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(0.01d, 0.5d, offset + i).func_187315_a(1.0d, 1.0d).func_181675_d();
                tessellator.func_78381_a();
            }
            double fraction = length % 1.0d;
            double last = length - fraction;
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferbuilder.func_181662_b(0.01d, -0.5d, ((double) offset) + last).func_187315_a(fraction, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.01d, -0.5d, ((double) offset) + last + fraction).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.01d, 0.5d, ((double) offset) + last + fraction).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(0.01d, 0.5d, ((double) offset) + last).func_187315_a(fraction, 1.0d).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179084_k();
    }

    /* JADX INFO: renamed from: isGlobalRenderer, reason: merged with bridge method [inline-methods] */
    public boolean func_188185_a(TileEntityTeslaTower te) {
        return true;
    }

    private void bindTextureForTE(TileEntityTeslaTower te) {
        int remainder = te.getTickExisted() % (te.getRandom2() + 1);
        switch (remainder) {
            case 0:
                func_147499_a(TEXTURE_LIGHTNING_BOLT_BRIGHT);
                break;
            case 1:
                func_147499_a(TEXTURE_LIGHTNING_BOLT_BLUE);
                break;
            default:
                func_147499_a(TEXTURE_LIGHTNING_BOLT_YELLOW);
                break;
        }
    }

    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
