package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL11;
import xol.lostinfinity.projectile.entity.EntityWandAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderWandAttack.class */
public class RenderWandAttack<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");

    public RenderWandAttack(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityWandAttack attackEntity = (EntityWandAttack) entity;
        BlockPos aimBlockReal = attackEntity.getAimBlock();
        BlockPos playerPos = attackEntity.getPlayerPos();
        Vec2f pitchYaw = attackEntity.func_189653_aC();
        new BlockPos(aimBlockReal.func_177958_n() - playerPos.func_177958_n(), aimBlockReal.func_177956_o() - playerPos.func_177956_o(), aimBlockReal.func_177952_p() - playerPos.func_177952_p());
        double dist = aimBlockReal.func_185332_f(playerPos.func_177958_n(), playerPos.func_177956_o(), playerPos.func_177952_p());
        GL11.glRotatef(pitchYaw.field_189982_i, 0.0f, -1.0f, 0.0f);
        GL11.glRotatef(pitchYaw.field_189983_j, 1.0f, 0.0f, 0.0f);
        float[] colours = {1.0f, 0.9f, 0.2f};
        func_110776_a(TEXTURE_BEACON_BEAM);
        entity.func_70080_a(playerPos.func_177958_n(), playerPos.func_177956_o(), playerPos.func_177952_p(), 0.0f, 0.0f);
        renderBeamSegment(x, y + 1.0d, z, partialTicks, 1.0d, 10000.0d, 0, (int) dist, colours, 0.1d, 0.2d);
    }

    public static void renderBeamSegment(double x, double y, double z, double partialTicks, double textureScale, double totalWorldTime, int yOffset, double height, float[] colors, double beamRadius, double glowRadius) {
        int i = (int) (((double) yOffset) + height);
        GlStateManager.func_187421_b(3553, 10242, 10497);
        GlStateManager.func_187421_b(3553, 10243, 10497);
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179084_k();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        double d0 = totalWorldTime + partialTicks;
        double d1 = height < 0.0d ? d0 : -d0;
        double d2 = MathHelper.func_181162_h((d1 * 0.2d) - ((double) MathHelper.func_76128_c(d1 * 0.1d)));
        float f = colors[0];
        float f1 = colors[1];
        float f2 = colors[2];
        double d3 = d0 * 0.025d * (-1.5d);
        double d4 = 0.5d + (Math.cos(d3 + 2.356194490192345d) * beamRadius);
        double d5 = 0.5d + (Math.sin(d3 + 2.356194490192345d) * beamRadius);
        double d6 = 0.5d + (Math.cos(d3 + 0.7853981633974483d) * beamRadius);
        double d7 = 0.5d + (Math.sin(d3 + 0.7853981633974483d) * beamRadius);
        double d8 = 0.5d + (Math.cos(d3 + 3.9269908169872414d) * beamRadius);
        double d9 = 0.5d + (Math.sin(d3 + 3.9269908169872414d) * beamRadius);
        double d10 = 0.5d + (Math.cos(d3 + 5.497787143782138d) * beamRadius);
        double d11 = 0.5d + (Math.sin(d3 + 5.497787143782138d) * beamRadius);
        double d14 = (-1.0d) + d2;
        double d15 = (height * textureScale * (0.5d / beamRadius)) + d14;
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        bufferbuilder.func_181662_b(x + d4, y + ((double) i), z + d5).func_187315_a(1.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d4, y + ((double) yOffset), z + d5).func_187315_a(1.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d6, y + ((double) yOffset), z + d7).func_187315_a(0.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d6, y + ((double) i), z + d7).func_187315_a(0.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d10, y + ((double) i), z + d11).func_187315_a(1.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d10, y + ((double) yOffset), z + d11).func_187315_a(1.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d8, y + ((double) yOffset), z + d9).func_187315_a(0.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d8, y + ((double) i), z + d9).func_187315_a(0.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d6, y + ((double) i), z + d7).func_187315_a(1.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d6, y + ((double) yOffset), z + d7).func_187315_a(1.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d10, y + ((double) yOffset), z + d11).func_187315_a(0.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d10, y + ((double) i), z + d11).func_187315_a(0.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d8, y + ((double) i), z + d9).func_187315_a(1.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d8, y + ((double) yOffset), z + d9).func_187315_a(1.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d4, y + ((double) yOffset), z + d5).func_187315_a(0.0d, d14).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        bufferbuilder.func_181662_b(x + d4, y + ((double) i), z + d5).func_187315_a(0.0d, d15).func_181666_a(f, f1, f2, 1.0f).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179132_a(false);
        double d32 = 0.5d - glowRadius;
        double d42 = 0.5d - glowRadius;
        double d52 = 0.5d + glowRadius;
        double d62 = 0.5d - glowRadius;
        double d72 = 0.5d - glowRadius;
        double d82 = 0.5d + glowRadius;
        double d92 = 0.5d + glowRadius;
        double d102 = 0.5d + glowRadius;
        double d13 = (-1.0d) + d2;
        double d142 = (height * textureScale) + d13;
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        bufferbuilder.func_181662_b(x + d32, y + ((double) i), z + d42).func_187315_a(1.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d32, y + ((double) yOffset), z + d42).func_187315_a(1.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d52, y + ((double) yOffset), z + d62).func_187315_a(0.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d52, y + ((double) i), z + d62).func_187315_a(0.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d92, y + ((double) i), z + d102).func_187315_a(1.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d92, y + ((double) yOffset), z + d102).func_187315_a(1.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d72, y + ((double) yOffset), z + d82).func_187315_a(0.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d72, y + ((double) i), z + d82).func_187315_a(0.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d52, y + ((double) i), z + d62).func_187315_a(1.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d52, y + ((double) yOffset), z + d62).func_187315_a(1.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d92, y + ((double) yOffset), z + d102).func_187315_a(0.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d92, y + ((double) i), z + d102).func_187315_a(0.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d72, y + ((double) i), z + d82).func_187315_a(1.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d72, y + ((double) yOffset), z + d82).func_187315_a(1.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d32, y + ((double) yOffset), z + d42).func_187315_a(0.0d, d13).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        bufferbuilder.func_181662_b(x + d32, y + ((double) i), z + d42).func_187315_a(0.0d, d142).func_181666_a(f, f1, f2, 0.125f).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179145_e();
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a(true);
    }

    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
