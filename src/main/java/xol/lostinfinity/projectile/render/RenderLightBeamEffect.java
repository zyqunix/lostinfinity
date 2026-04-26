package xol.lostinfinity.projectile.render;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import xol.lostinfinity.block.tileentity.TileEntityLightEmitter;
import xol.lostinfinity.init.BlockInit;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderLightBeamEffect.class */
public class RenderLightBeamEffect extends TileEntitySpecialRenderer<TileEntityLightEmitter> {
    public static final ResourceLocation TEXTURE_LIGHT_BEAM = new ResourceLocation("lostinfinity:textures/particles/light_beam.png");

    /* JADX INFO: renamed from: render, reason: merged with bridge method [inline-methods] */
    public void func_192841_a(TileEntityLightEmitter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_LIGHT_BEAM);
        Vec3d playerPos = null;
        if (Minecraft.func_71410_x().field_71439_g != null) {
            playerPos = Minecraft.func_71410_x().field_71439_g.func_174791_d();
        }
        Vec3d offset = new Vec3d(0.5d, 0.3d, 0.5d);
        Vec3d target = te.getStartPos();
        Vec3d target2 = te.getStopPos();
        ArrayList<Vec3d> reflectors = te.getReflectors();
        if (target != null && target2 != null && playerPos != null && reflectors.size() < 2) {
            Vec3d targetVec = new Vec3d(target.field_72450_a, target.field_72448_b, target.field_72449_c).func_178787_e(offset);
            Vec3d target2Vec = new Vec3d(target2.field_72450_a, target2.field_72448_b, target2.field_72449_c).func_178787_e(offset);
            Vec3d dir = target2Vec.func_178788_d(targetVec).func_72432_b();
            double dist = Math.sqrt(Math.pow(targetVec.field_72450_a - target2Vec.field_72450_a, 2.0d) + Math.pow(targetVec.field_72448_b - target2Vec.field_72448_b, 2.0d) + Math.pow(targetVec.field_72449_c - target2Vec.field_72449_c, 2.0d));
            func_147499_a(TEXTURE_LIGHT_BEAM);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179140_f();
            int brightX = 240 % 65536;
            int brightY = 240 / 65536;
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, brightX, brightY);
            GL11.glEnable(3008);
            GlStateManager.func_179141_d();
            GlStateManager.func_179129_p();
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            for (int i = 0; i < ((int) Math.floor(dist / 0.4d)); i++) {
                double xPos = (targetVec.field_72450_a - playerPos.field_72450_a) + (dir.field_72450_a * ((double) i) * 0.4d);
                double yPos = (targetVec.field_72448_b - playerPos.field_72448_b) + (dir.field_72448_b * ((double) i) * 0.4d);
                double zPos = (targetVec.field_72449_c - playerPos.field_72449_c) + (dir.field_72449_c * ((double) i) * 0.4d);
                bufferbuilder.func_181662_b(xPos, yPos + 0.4d, zPos).func_187315_a(0.0d, 1.0d).func_181666_a(1.0f, 1.0f, 1.0f, 1.0f).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181666_a(1.0f, 1.0f, 1.0f, 1.0f).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.4d * dir.field_72450_a), yPos + (0.4d * dir.field_72448_b), zPos + (0.4d * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181666_a(1.0f, 1.0f, 1.0f, 1.0f).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.4d * dir.field_72450_a), yPos + 0.4d + (0.4d * dir.field_72448_b), zPos + (0.4d * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181666_a(1.0f, 1.0f, 1.0f, 1.0f).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179118_c();
            GlStateManager.func_179089_o();
            return;
        }
        for (int k = 0; k < reflectors.size() - 1; k++) {
            Vec3d offset2 = new Vec3d(0.5d, 0.3d, 0.5d);
            Vec3d target3 = reflectors.get(k);
            Vec3d target22 = reflectors.get(k + 1);
            if (target3 != null && target22 != null && playerPos != null) {
                Vec3d targetVec2 = new Vec3d(target3.field_72450_a, target3.field_72448_b, target3.field_72449_c).func_178787_e(offset2);
                Vec3d target2Vec2 = new Vec3d(target22.field_72450_a, target22.field_72448_b, target22.field_72449_c).func_178787_e(offset2);
                Vec3d dir2 = target2Vec2.func_178788_d(targetVec2).func_72432_b();
                if (Math.abs(dir2.field_72450_a) > 0.0d && Math.abs(dir2.field_72449_c) > 0.0d) {
                    return;
                }
                double dist2 = Math.sqrt(Math.pow(targetVec2.field_72450_a - target2Vec2.field_72450_a, 2.0d) + Math.pow(targetVec2.field_72448_b - target2Vec2.field_72448_b, 2.0d) + Math.pow(targetVec2.field_72449_c - target2Vec2.field_72449_c, 2.0d));
                func_147499_a(TEXTURE_LIGHT_BEAM);
                Tessellator tessellator2 = Tessellator.func_178181_a();
                BufferBuilder bufferbuilder2 = tessellator2.func_178180_c();
                GlStateManager.func_179140_f();
                int brightX2 = 240 % 65536;
                int brightY2 = 240 / 65536;
                OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, brightX2, brightY2);
                GL11.glEnable(3008);
                GlStateManager.func_179141_d();
                GlStateManager.func_179129_p();
                bufferbuilder2.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                for (int i2 = 0; i2 < ((int) Math.floor(dist2 / 0.4d)); i2++) {
                    double xPos2 = (targetVec2.field_72450_a - playerPos.field_72450_a) + (dir2.field_72450_a * ((double) i2) * 0.4d);
                    double yPos2 = (targetVec2.field_72448_b - playerPos.field_72448_b) + (dir2.field_72448_b * ((double) i2) * 0.4d);
                    double zPos2 = (targetVec2.field_72449_c - playerPos.field_72449_c) + (dir2.field_72449_c * ((double) i2) * 0.4d);
                    bufferbuilder2.func_181662_b(xPos2, yPos2 + 0.4d, zPos2).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2, yPos2, zPos2).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2 + (0.4d * dir2.field_72450_a), yPos2 + (0.4d * dir2.field_72448_b), zPos2 + (0.4d * dir2.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder2.func_181662_b(xPos2 + (0.4d * dir2.field_72450_a), yPos2 + 0.4d + (0.4d * dir2.field_72448_b), zPos2 + (0.4d * dir2.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
                tessellator2.func_78381_a();
                GlStateManager.func_179145_e();
                GlStateManager.func_179118_c();
                GlStateManager.func_179089_o();
            }
        }
        if (te.isComplete()) {
            Vec3d offset3 = new Vec3d(0.5d, 0.3d, 0.5d);
            Vec3d target4 = reflectors.get(reflectors.size() - 1);
            Vec3d target23 = null;
            for (int i3 = -20; i3 < 20; i3++) {
                for (int j = -20; j < 20; j++) {
                    int k2 = -20;
                    while (true) {
                        if (k2 < 20) {
                            BlockPos checkPos = new BlockPos(((int) target4.field_72450_a) + i3, ((int) target4.field_72448_b) + j, ((int) target4.field_72449_c) + k2);
                            Block checkBlock = te.func_145831_w().func_180495_p(checkPos).func_177230_c();
                            if (!checkBlock.equals(BlockInit.lightReceiver)) {
                                k2++;
                            } else {
                                target23 = new Vec3d(checkPos.func_177958_n(), checkPos.func_177956_o(), checkPos.func_177952_p());
                                break;
                            }
                        }
                    }
                }
            }
            if (target4 != null && target23 != null && playerPos != null) {
                Vec3d targetVec3 = new Vec3d(target4.field_72450_a, target4.field_72448_b, target4.field_72449_c).func_178787_e(offset3);
                Vec3d target2Vec3 = new Vec3d(target23.field_72450_a, target23.field_72448_b, target23.field_72449_c).func_178787_e(offset3);
                Vec3d dir3 = target2Vec3.func_178788_d(targetVec3).func_72432_b();
                double dist3 = Math.sqrt(Math.pow(targetVec3.field_72450_a - target2Vec3.field_72450_a, 2.0d) + Math.pow(targetVec3.field_72448_b - target2Vec3.field_72448_b, 2.0d) + Math.pow(targetVec3.field_72449_c - target2Vec3.field_72449_c, 2.0d));
                func_147499_a(TEXTURE_LIGHT_BEAM);
                Tessellator tessellator3 = Tessellator.func_178181_a();
                BufferBuilder bufferbuilder3 = tessellator3.func_178180_c();
                GlStateManager.func_179140_f();
                int brightX3 = 240 % 65536;
                int brightY3 = 240 / 65536;
                OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, brightX3, brightY3);
                GL11.glEnable(3008);
                GlStateManager.func_179141_d();
                GlStateManager.func_179129_p();
                bufferbuilder3.func_181668_a(7, DefaultVertexFormats.field_181707_g);
                for (int i4 = 0; i4 < ((int) Math.floor(dist3 / 0.4d)); i4++) {
                    double xPos3 = (targetVec3.field_72450_a - playerPos.field_72450_a) + (dir3.field_72450_a * ((double) i4) * 0.4d);
                    double yPos3 = (targetVec3.field_72448_b - playerPos.field_72448_b) + (dir3.field_72448_b * ((double) i4) * 0.4d);
                    double zPos3 = (targetVec3.field_72449_c - playerPos.field_72449_c) + (dir3.field_72449_c * ((double) i4) * 0.4d);
                    bufferbuilder3.func_181662_b(xPos3, yPos3 + 0.4d, zPos3).func_187315_a(0.0d, 1.0d).func_181675_d();
                    bufferbuilder3.func_181662_b(xPos3, yPos3, zPos3).func_187315_a(0.0d, 0.0d).func_181675_d();
                    bufferbuilder3.func_181662_b(xPos3 + (0.4d * dir3.field_72450_a), yPos3 + (0.4d * dir3.field_72448_b), zPos3 + (0.4d * dir3.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                    bufferbuilder3.func_181662_b(xPos3 + (0.4d * dir3.field_72450_a), yPos3 + 0.4d + (0.4d * dir3.field_72448_b), zPos3 + (0.4d * dir3.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
                }
                tessellator3.func_78381_a();
                GlStateManager.func_179145_e();
                GlStateManager.func_179118_c();
                GlStateManager.func_179089_o();
            }
        }
    }

    /* JADX INFO: renamed from: isGlobalRenderer, reason: merged with bridge method [inline-methods] */
    public boolean func_188185_a(TileEntityLightEmitter emitter) {
        return true;
    }
}
