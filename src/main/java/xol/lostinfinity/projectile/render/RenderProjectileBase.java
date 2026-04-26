package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderProjectileBase.class */
public abstract class RenderProjectileBase<T extends Entity> extends Render<T> {
    private final float scale;

    public RenderProjectileBase(RenderManager manager, float scaleIn) {
        super(manager);
        this.scale = scaleIn;
    }

    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.func_179094_E();
        func_180548_c(entity);
        GlStateManager.func_179109_b((float) x, (float) y, (float) z);
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a(this.scale * 0.5f, this.scale * 0.5f, this.scale * 0.5f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179114_b(180.0f - this.field_76990_c.field_78735_i, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b((this.field_76990_c.field_78733_k.field_74320_O == 2 ? -1 : 1) * (-this.field_76990_c.field_78732_j), 1.0f, 0.0f, 0.0f);
        if (this.field_188301_f) {
            GlStateManager.func_179142_g();
            GlStateManager.func_187431_e(func_188298_c(entity));
        }
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181710_j);
        bufferbuilder.func_181662_b(-0.5d, -0.25d, 0.0d).func_187315_a(0.0d, 1.0d).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(0.5d, -0.25d, 0.0d).func_187315_a(1.0d, 1.0d).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(0.5d, 0.75d, 0.0d).func_187315_a(1.0d, 0.0d).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        bufferbuilder.func_181662_b(-0.5d, 0.75d, 0.0d).func_187315_a(0.0d, 0.0d).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        tessellator.func_78381_a();
        if (this.field_188301_f) {
            GlStateManager.func_187417_n();
            GlStateManager.func_179119_h();
        }
        GlStateManager.func_179101_C();
        GlStateManager.func_179121_F();
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }
}
