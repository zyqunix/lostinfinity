package xol.lostinfinity.block.tileentity.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityCthulhuSpawner;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/render/RenderCthulhuSpawner.class */
public class RenderCthulhuSpawner extends TileEntitySpecialRenderer<TileEntityCthulhuSpawner> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/particles/cthulhu/spawner_beam.png");

    /* JADX INFO: renamed from: render, reason: merged with bridge method [inline-methods] */
    public void func_192841_a(TileEntityCthulhuSpawner te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!te.isBurning()) {
            return;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179137_b(x + 0.5d, y + 0.5d, z + 0.5d);
        enableMaxLighting();
        func_147499_a(TEXTURE);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        for (int i = 1; i <= 30; i++) {
            switch (AnonymousClass1.$SwitchMap$net$minecraft$util$EnumFacing[te.getFacing().ordinal()]) {
                case 1:
                    bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                    bufferbuilder.func_181662_b(0.0d, -0.5d, -(i - 1)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, -0.5d, -i).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, 0.5d, -i).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, 0.5d, -(i - 1)).func_187315_a(1.0d, 1.0d).func_181675_d();
                    tessellator.func_78381_a();
                    break;
                case 2:
                    bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                    bufferbuilder.func_181662_b(i - 1, -0.5d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(i, -0.5d, 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(i, 0.5d, 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(i - 1, 0.5d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
                    tessellator.func_78381_a();
                    break;
                case 3:
                    bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                    bufferbuilder.func_181662_b(0.0d, -0.5d, i - 1).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, -0.5d, i).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, 0.5d, i).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(0.0d, 0.5d, i - 1).func_187315_a(1.0d, 1.0d).func_181675_d();
                    tessellator.func_78381_a();
                    break;
                case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                    bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                    bufferbuilder.func_181662_b(-(i - 1), -0.5d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(-i, -0.5d, 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder.func_181662_b(-i, 0.5d, 0.0d).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder.func_181662_b(-(i - 1), 0.5d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
                    tessellator.func_78381_a();
                    break;
            }
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179145_e();
        GlStateManager.func_179084_k();
        GlStateManager.func_179089_o();
    }

    /* JADX INFO: renamed from: xol.lostinfinity.block.tileentity.render.RenderCthulhuSpawner$1, reason: invalid class name */
    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/render/RenderCthulhuSpawner$1.class */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[EnumFacing.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* JADX INFO: renamed from: isGlobalRenderer, reason: merged with bridge method [inline-methods] */
    public boolean func_188185_a(TileEntityCthulhuSpawner te) {
        return true;
    }

    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
