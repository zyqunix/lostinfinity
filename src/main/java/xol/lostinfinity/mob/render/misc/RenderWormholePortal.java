package xol.lostinfinity.mob.render.misc;

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
import xol.lostinfinity.mob.entity.misc.EntityWormholePortal;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/misc/RenderWormholePortal.class */
public class RenderWormholePortal<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_WORMHOLE_PORTAL = new ResourceLocation("lostinfinity:textures/particles/wormhole_portal.png");

    public RenderWormholePortal(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityPlayer caster;
        EntityWormholePortal attackEntity = (EntityWormholePortal) entity;
        Vec3d target = attackEntity.func_174791_d();
        if (target != null && !attackEntity.field_70128_L && (caster = attackEntity.getCaster()) != null) {
            func_110776_a(TEXTURE_WORMHOLE_PORTAL);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.2f);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.7f);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            Vec3d targetLook = caster.func_70040_Z().func_72432_b();
            Vec3d targetLook2 = targetLook.func_178786_a(0.0d, targetLook.field_72448_b, 0.0d);
            Vec3d dir = rotVec(targetLook2, 1.5707963267948966d);
            Vec3d upDir = new Vec3d(0.0d, 1.0d, 0.0d);
            double radians = ((double) attackEntity.field_70173_aa) / 10.0d;
            Vec3d dir2 = dir.func_186678_a(Math.cos(radians)).func_178787_e(crossProduct(targetLook2, dir).func_186678_a(Math.sin(radians))).func_178787_e(targetLook2.func_186678_a(dotProduct(targetLook2, dir) * (1.0d - Math.cos(radians))));
            Vec3d upDir2 = upDir.func_186678_a(Math.cos(radians)).func_178787_e(crossProduct(targetLook2, upDir).func_186678_a(Math.sin(radians))).func_178787_e(targetLook2.func_186678_a(dotProduct(targetLook2, upDir) * (1.0d - Math.cos(radians))));
            bufferbuilder.func_181662_b((((double) ((-0.5f) * 0.5f)) * dir2.field_72450_a) + (0.5d * ((double) 0.5f) * upDir2.field_72450_a) + x, (((0.5d * ((double) 0.5f)) * upDir2.field_72448_b) + y) - (((double) (0.5f * 0.5f)) * dir2.field_72448_b), (((double) ((-0.5f) * 0.5f)) * dir2.field_72449_c) + (0.5d * ((double) 0.5f) * upDir2.field_72449_c) + z).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(((((double) ((-0.5f) * 0.5f)) * dir2.field_72450_a) - ((0.5d * ((double) 0.5f)) * upDir2.field_72450_a)) + x, ((((-0.5d) * ((double) 0.5f)) * upDir2.field_72448_b) + y) - (((double) (0.5f * 0.5f)) * dir2.field_72448_b), ((((double) ((-0.5f) * 0.5f)) * dir2.field_72449_c) - ((0.5d * ((double) 0.5f)) * upDir2.field_72449_c)) + z).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(((((double) (0.5f * 0.5f)) * dir2.field_72450_a) - ((0.5d * ((double) 0.5f)) * upDir2.field_72450_a)) + x, ((-0.5d) * ((double) 0.5f) * upDir2.field_72448_b) + y + (((double) (0.5f * 0.5f)) * dir2.field_72448_b), ((((double) (0.5f * 0.5f)) * dir2.field_72449_c) - ((0.5d * ((double) 0.5f)) * upDir2.field_72449_c)) + z).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b((((double) (0.5f * 0.5f)) * dir2.field_72450_a) + (0.5d * ((double) 0.5f) * upDir2.field_72450_a) + x, (0.5d * ((double) 0.5f) * upDir2.field_72448_b) + y + (((double) (0.5f * 0.5f)) * dir2.field_72448_b), (((double) (0.5f * 0.5f)) * dir2.field_72449_c) + (0.5d * ((double) 0.5f) * upDir2.field_72449_c) + z).func_187315_a(1.0d, 1.0d).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }

    private double dotProduct(Vec3d vec1, Vec3d vec2) {
        return (vec1.field_72450_a * vec2.field_72450_a) + (vec1.field_72448_b * vec2.field_72448_b) + (vec1.field_72449_c * vec2.field_72449_c);
    }

    private Vec3d crossProduct(Vec3d vec1, Vec3d vec2) {
        double x = (vec1.field_72448_b * vec2.field_72449_c) - (vec1.field_72449_c * vec2.field_72448_b);
        double y = (vec1.field_72449_c * vec2.field_72450_a) - (vec1.field_72450_a * vec2.field_72449_c);
        double z = (vec1.field_72450_a * vec2.field_72448_b) - (vec1.field_72448_b * vec2.field_72450_a);
        return new Vec3d(x, y, z);
    }

    private static Vec3d rotVec(Vec3d vec, double radians) {
        double x = (vec.field_72450_a * Math.cos(radians)) + (vec.field_72449_c * Math.sin(radians));
        double y = vec.field_72448_b;
        double z = ((-vec.field_72450_a) * Math.sin(radians)) + (vec.field_72449_c * Math.cos(radians));
        return new Vec3d(x, y, z);
    }

    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
