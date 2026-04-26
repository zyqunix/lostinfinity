package xol.lostinfinity.projectile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import xol.lostinfinity.projectile.entity.EntityScreamerPortalEffect;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderScreamerPortalEffect.class */
public class RenderScreamerPortalEffect<T extends Entity> extends Render<T> {
    public static final ResourceLocation TEXTURE_PORTAL_EFFECT = new ResourceLocation("lostinfinity:textures/particles/screamer_portal.png");

    public RenderScreamerPortalEffect(RenderManager renderManager) {
        super(renderManager);
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityScreamerPortalEffect attackEntity = (EntityScreamerPortalEffect) entity;
        EntityPlayerSP clientPlayer = Minecraft.func_71410_x().field_71439_g;
        Vec3d playerPos = null;
        if (clientPlayer != null) {
            playerPos = clientPlayer.func_174791_d();
        }
        Vec3d target = attackEntity.func_174791_d();
        if (target != null && playerPos != null && ((Entity) entity).field_70173_aa > 2) {
            func_110776_a(TEXTURE_PORTAL_EFFECT);
            Tessellator tessellator = Tessellator.func_178181_a();
            BufferBuilder bufferbuilder = tessellator.func_178180_c();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            GlStateManager.func_179140_f();
            float growth = attackEntity.getGrowth();
            float rotation = attackEntity.getRotation();
            float alpha = 1.0f;
            if (attackEntity.field_70173_aa >= 175) {
                alpha = 1.0f - ((attackEntity.field_70173_aa - 175) * 0.04f);
            }
            GlStateManager.func_179147_l();
            GlStateManager.func_179092_a(516, 0.2f);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, alpha);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            double xPos = target.field_72450_a - playerPos.field_72450_a;
            double yPos = target.field_72448_b - playerPos.field_72448_b;
            double zPos = target.field_72449_c - playerPos.field_72449_c;
            Vec3d dirRight = new Vec3d(1.0d, 0.0d, 0.0d).func_178785_b(rotation);
            Vec3d dirForward = new Vec3d(0.0d, 0.0d, 1.0d).func_178785_b(rotation);
            bufferbuilder.func_181662_b((((double) ((-0.5f) * growth)) * dirRight.field_72450_a) + (((double) (0.5f * growth)) * dirForward.field_72450_a) + xPos, 0.0d + yPos, (((double) ((-0.5f) * growth)) * dirRight.field_72449_c) + (((double) (0.5f * growth)) * dirForward.field_72449_c) + zPos).func_187315_a(0.0d, 1.0d).func_181675_d();
            bufferbuilder.func_181662_b(((((double) ((-0.5f) * growth)) * dirRight.field_72450_a) - (((double) (0.5f * growth)) * dirForward.field_72450_a)) + xPos, 0.0d + yPos, ((((double) ((-0.5f) * growth)) * dirRight.field_72449_c) - (((double) (0.5f * growth)) * dirForward.field_72449_c)) + zPos).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(((((double) (0.5f * growth)) * dirRight.field_72450_a) - (((double) (0.5f * growth)) * dirForward.field_72450_a)) + xPos, 0.0d + yPos, ((((double) (0.5f * growth)) * dirRight.field_72449_c) - (((double) (0.5f * growth)) * dirForward.field_72449_c)) + zPos).func_187315_a(1.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b((((double) (0.5f * growth)) * dirRight.field_72450_a) + (((double) (0.5f * growth)) * dirForward.field_72450_a) + xPos, 0.0d + yPos, (((double) (0.5f * growth)) * dirRight.field_72449_c) + (((double) (0.5f * growth)) * dirForward.field_72449_c) + zPos).func_187315_a(1.0d, 1.0d).func_181675_d();
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
