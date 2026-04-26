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
import xol.lostinfinity.item.weapon.data.IonizerNode;
import xol.lostinfinity.projectile.entity.EntitySelectorAttack;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderSelectorAttack.class */
public class RenderSelectorAttack<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_YELLOW = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt.png");
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_BRIGHT = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt_bright.png");
    public static final ResourceLocation TEXTURE_LIGHTNING_BOLT_BLUE = new ResourceLocation("lostinfinity:textures/particles/lightning_bolt_blue.png");

    public RenderSelectorAttack(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntitySelectorAttack attackEntity = (EntitySelectorAttack) entity;
        Vec3d playerPos = null;
        if (Minecraft.func_71410_x().field_71439_g != null) {
            playerPos = Minecraft.func_71410_x().field_71439_g.func_174791_d();
        }
        IonizerNode targetsNode = attackEntity.getTargets();
        if (targetsNode != null) {
            traverseRenderNodes(targetsNode, attackEntity, playerPos, 0);
        }
    }

    private void traverseRenderNodes(IonizerNode targetsNode, EntitySelectorAttack entity, Vec3d playerPos, int count) {
        if (targetsNode.getTargets() == null || targetsNode == null) {
            return;
        }
        for (IonizerNode node : targetsNode.getTargets()) {
            if (node != null) {
                targetsNode.updatePos();
                node.updatePos();
                if (targetsNode.getOriginPos() != null && node.getOriginPos() != null) {
                    Vec3d originVec = targetsNode.getOriginPos().func_178787_e(new Vec3d(0.0d, targetsNode.getHeight() / 2.0d, 0.0d));
                    Vec3d targetVec = node.getOriginPos().func_178787_e(new Vec3d(0.0d, node.getHeight() / 2.0d, 0.0d));
                    if (targetsNode.isActive()) {
                        renderBolt(entity, originVec, targetVec, playerPos, count);
                    }
                    int i = count;
                    count++;
                    traverseRenderNodes(node, entity, playerPos, i);
                }
            }
        }
    }

    private void renderBolt(EntitySelectorAttack entity, Vec3d target, Vec3d target2, Vec3d playerPos, int count) {
        float alpha;
        if (target != null && target2 != null && playerPos != null) {
            Vec3d targetVec = new Vec3d(target.field_72450_a, target.field_72448_b, target.field_72449_c);
            Vec3d target2Vec = new Vec3d(target2.field_72450_a, target2.field_72448_b, target2.field_72449_c);
            Vec3d dir = target2Vec.func_178788_d(targetVec).func_72432_b();
            double dist = Math.sqrt(Math.pow(targetVec.field_72450_a - target2Vec.field_72450_a, 2.0d) + Math.pow(targetVec.field_72448_b - target2Vec.field_72448_b, 2.0d) + Math.pow(targetVec.field_72449_c - target2Vec.field_72449_c, 2.0d));
            int remainder = entity.field_70173_aa % 7;
            if (remainder == 0) {
                func_110776_a(TEXTURE_LIGHTNING_BOLT_BRIGHT);
            } else if (remainder == 1) {
                func_110776_a(TEXTURE_LIGHTNING_BOLT_BLUE);
            } else {
                func_110776_a(TEXTURE_LIGHTNING_BOLT_YELLOW);
            }
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.2f);
            if ((entity.field_70173_aa - count) % 10 < 5) {
                alpha = 0.3f + (((entity.field_70173_aa - count) % 10) * 0.14f);
            } else {
                alpha = 1.0f - ((((entity.field_70173_aa - count) % 10) - 5) * 0.14f);
            }
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, alpha);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            for (int i = 0; i < ((int) Math.floor(dist / 0.4d)); i++) {
                double xPos = (targetVec.field_72450_a - playerPos.field_72450_a) + (dir.field_72450_a * ((double) i) * 0.4d);
                double yPos = (targetVec.field_72448_b - playerPos.field_72448_b) + (dir.field_72448_b * ((double) i) * 0.4d);
                double zPos = (targetVec.field_72449_c - playerPos.field_72449_c) + (dir.field_72449_c * ((double) i) * 0.4d);
                bufferbuilder.func_181662_b(xPos, yPos + 0.4d, zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos, yPos, zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.4d * dir.field_72450_a), yPos + (0.4d * dir.field_72448_b), zPos + (0.4d * dir.field_72449_c)).func_187315_a(1.0d, 0.0d).func_181675_d();
                bufferbuilder.func_181662_b(xPos + (0.4d * dir.field_72450_a), yPos + 0.4d + (0.4d * dir.field_72448_b), zPos + (0.4d * dir.field_72449_c)).func_187315_a(1.0d, 1.0d).func_181675_d();
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
