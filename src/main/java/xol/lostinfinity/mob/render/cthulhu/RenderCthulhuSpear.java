package xol.lostinfinity.mob.render.cthulhu;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuSpear;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/cthulhu/RenderCthulhuSpear.class */
public class RenderCthulhuSpear extends Render<EntityCthulhuSpear> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/projectiles/cthulhu/beam.png");

    public RenderCthulhuSpear(RenderManager renderManager) {
        super(renderManager);
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityCthulhuSpear entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        enableMaxLighting();
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179137_b(x, y + ((double) entity.func_70047_e()), z);
        double dist = entity.getLaserDistance();
        EntityPlayer player = entity.getOwner();
        if (player != null) {
            GlStateManager.func_179114_b(player.field_70177_z, 0.0f, -1.0f, 0.0f);
            GlStateManager.func_179114_b(player.field_70125_A, 1.0f, 0.0f, 0.0f);
        }
        func_110776_a(TEXTURE);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(0.0d, -0.5d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, -0.5d, dist).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, 0.5d, dist).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.0d, 0.5d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(-0.5d, 0.0d, 0.0d).func_187315_a(1.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(-0.5d, 0.0d, dist).func_187315_a(0.0d, 0.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5d, 0.0d, dist).func_187315_a(0.0d, 1.0d).func_181675_d();
        bufferbuilder.func_181662_b(0.5d, 0.0d, 0.0d).func_187315_a(1.0d, 1.0d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        GlStateManager.func_179089_o();
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityCthulhuSpear entity) {
        return TEXTURE;
    }

    private void enableMaxLighting() {
        GlStateManager.func_179140_f();
        OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0f, 240.0f);
    }
}
