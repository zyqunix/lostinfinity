package xol.lostinfinity.block.tileentity.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.block.tileentity.TileEntityKillerVine;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/tileentity/render/RenderKillerVine.class */
public class RenderKillerVine extends TileEntitySpecialRenderer<TileEntityKillerVine> {
    public static final ResourceLocation TEXTURE_VINE = new ResourceLocation("lostinfinity:textures/particles/killer_vine.png");

    /* JADX INFO: renamed from: render, reason: merged with bridge method [inline-methods] */
    public void func_192841_a(TileEntityKillerVine te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (!te.isKiller()) {
            return;
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179129_p();
        enableMaxLighting();
        func_147499_a(TEXTURE_VINE);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        for (TileEntityKillerVine.VineNode node : te.getNodes()) {
            EntityLivingBase entity = node.getEntity();
            Vec3d pos = new Vec3d(MathHelper.func_151238_b(entity.field_70169_q, entity.field_70165_t, partialTicks), MathHelper.func_151238_b(entity.field_70167_r, entity.field_70163_u, partialTicks), MathHelper.func_151238_b(entity.field_70166_s, entity.field_70161_v, partialTicks)).func_72441_c(-0.5d, ((double) (entity.field_70131_O / 2.0f)) - 0.5d, -0.5d);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            Vec3d tePos = new Vec3d(te.func_174877_v().func_177958_n(), te.func_174877_v().func_177956_o(), te.func_174877_v().func_177952_p());
            double dist = pos.func_72438_d(tePos);
            Vec3d dir = pos.func_178788_d(tePos).func_72432_b();
            for (int i = 0; i < ((int) Math.floor((dist * Math.min(((double) node.getGrowth()) / 20.0d, 1.0d)) / ((double) 1.0f))); i++) {
                double xPos = x + 0.5d + (dir.field_72450_a * ((double) i) * ((double) 1.0f));
                double yPos = y + (dir.field_72448_b * ((double) i) * ((double) 1.0f)) + 0.5d;
                double zPos = z + 0.5d + (dir.field_72449_c * ((double) i) * ((double) 1.0f));
                bufferbuilder.func_181662_b(xPos, yPos + ((double) 0.5f), zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (((double) 1.0f) * dir.field_72450_a), yPos + ((double) 0.5f) + (((double) 1.0f) * dir.field_72448_b), zPos + (((double) 1.0f) * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
            }
            tessellator.func_78381_a();
        }
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179089_o();
    }

    /* JADX INFO: renamed from: isGlobalRenderer, reason: merged with bridge method [inline-methods] */
    public boolean func_188185_a(TileEntityKillerVine te) {
        return true;
    }

    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
