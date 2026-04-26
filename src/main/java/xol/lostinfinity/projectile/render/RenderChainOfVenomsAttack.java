package xol.lostinfinity.projectile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.projectile.entity.EntityChainOfVenomsAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderChainOfVenomsAttack.class */
public class RenderChainOfVenomsAttack<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_VENOM_CHAIN = new ResourceLocation("lostinfinity:textures/particles/venom_chain.png");

    public RenderChainOfVenomsAttack(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float alpha;
        EntityChainOfVenomsAttack attackEntity = (EntityChainOfVenomsAttack) entity;
        Vec3d playerPos = null;
        if (Minecraft.func_71410_x().field_71439_g != null) {
            playerPos = Minecraft.func_71410_x().field_71439_g.func_174791_d();
        }
        Vec3d target = attackEntity.getOriginPos();
        Vec3d target2 = attackEntity.getTargetPos();
        double targetHeight = attackEntity.getOriginHeight();
        double target2Height = attackEntity.getTargetHeight();
        if (target != null && target2 != null && playerPos != null) {
            Vec3d targetVec = new Vec3d(target.field_72450_a, target.field_72448_b + (targetHeight / 2.0d), target.field_72449_c);
            Vec3d target2Vec = new Vec3d(target2.field_72450_a, target2.field_72448_b + (target2Height / 2.0d), target2.field_72449_c);
            Vec3d dir = target2Vec.func_178788_d(targetVec).func_72432_b();
            double dist = Math.sqrt(Math.pow(targetVec.field_72450_a - target2Vec.field_72450_a, 2.0d) + Math.pow(targetVec.field_72448_b - target2Vec.field_72448_b, 2.0d) + Math.pow(targetVec.field_72449_c - target2Vec.field_72449_c, 2.0d));
            func_110776_a(TEXTURE_VENOM_CHAIN);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            float brightness = 1.0f;
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.4f);
            if (((Entity) entity).field_70173_aa % 20 < 8) {
                brightness = 0.5f + ((((Entity) entity).field_70173_aa % 20) * 0.0625f);
                alpha = 0.3f + ((((Entity) entity).field_70173_aa % 20) * 0.2f);
            } else {
                alpha = 1.0f - (((((Entity) entity).field_70173_aa % 20) - 8) * 0.1f);
            }
            GlStateManager.func_179131_c(brightness, brightness, brightness, alpha);
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
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
        }
    }

    protected ResourceLocation func_110775_a(T entity) {
        return null;
    }
}
