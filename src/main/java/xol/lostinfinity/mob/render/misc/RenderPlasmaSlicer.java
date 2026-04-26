package xol.lostinfinity.mob.render.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.mob.entity.misc.EntityPlasmaSlicer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderPlasmaSlicer.class */
public class RenderPlasmaSlicer<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_STABLE_PLASMA = new ResourceLocation("lostinfinity:textures/particles/stable_plasma.png");
    public static final ResourceLocation TEXTURE_UNSTABLE_PLASMA = new ResourceLocation("lostinfinity:textures/particles/unstable_plasma.png");

    public RenderPlasmaSlicer(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityPlasmaSlicer attackEntity = (EntityPlasmaSlicer) entity;
        Vec3d playerPos = null;
        if (Minecraft.func_71410_x().field_71439_g != null) {
            playerPos = Minecraft.func_71410_x().field_71439_g.func_174791_d();
        }
        EntityPlayer emitter = attackEntity.getEmitter();
        EntityPlayer receiver = attackEntity.getReceiver();
        if (emitter == null || receiver == null) {
            return;
        }
        Vec3d target = emitter.func_174791_d();
        Vec3d target2 = receiver.func_174791_d();
        double targetHeight = emitter.field_70131_O;
        double target2Height = receiver.field_70131_O;
        if (target != null && target2 != null && playerPos != null) {
            Vec3d targetVec = new Vec3d(target.field_72450_a, target.field_72448_b + (targetHeight / 2.0d), target.field_72449_c);
            Vec3d target2Vec = new Vec3d(target2.field_72450_a, target2.field_72448_b + (target2Height / 2.0d), target2.field_72449_c);
            double curDist = targetVec.func_72438_d(target2Vec);
            double stableDist = attackEntity.getStableDist();
            if (Math.abs(curDist - stableDist) > attackEntity.getCollapseDist()) {
                func_110776_a(TEXTURE_UNSTABLE_PLASMA);
            } else {
                func_110776_a(TEXTURE_STABLE_PLASMA);
            }
            Vec3d dir = target2Vec.func_178788_d(targetVec).func_72432_b();
            double dist = Math.sqrt(Math.pow(targetVec.field_72450_a - target2Vec.field_72450_a, 2.0d) + Math.pow(targetVec.field_72448_b - target2Vec.field_72448_b, 2.0d) + Math.pow(targetVec.field_72449_c - target2Vec.field_72449_c, 2.0d));
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.4f);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            for (int i = 0; i < ((int) Math.floor(dist / 0.8d)); i++) {
                double xPos = (targetVec.field_72450_a - playerPos.field_72450_a) + (dir.field_72450_a * ((double) i) * 0.8d);
                double yPos = (targetVec.field_72448_b - playerPos.field_72448_b) + (dir.field_72448_b * ((double) i) * 0.8d);
                double zPos = (targetVec.field_72449_c - playerPos.field_72449_c) + (dir.field_72449_c * ((double) i) * 0.8d);
                bufferbuilder.func_181662_b(xPos, yPos + 0.8d, zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.8d * dir.field_72450_a), yPos + (0.8d * dir.field_72448_b), zPos + (0.8d * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.8d * dir.field_72450_a), yPos + 0.8d + (0.8d * dir.field_72448_b), zPos + (0.8d * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }

    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
