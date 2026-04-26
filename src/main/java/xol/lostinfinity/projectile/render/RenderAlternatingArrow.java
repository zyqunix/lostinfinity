package xol.lostinfinity.projectile.render;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.projectile.entity.EntityBaseThrowable;
public class RenderAlternatingArrow<T extends EntityBaseThrowable> extends Render<T> {
    private final ResourceLocation texture;
    private final ResourceLocation texture2;
    private int switch_timer;
    public RenderAlternatingArrow(RenderManager renderManager, ResourceLocation location, ResourceLocation loc2) {
        super(renderManager);
        this.switch_timer = 5;
        this.texture = location;
        this.texture2 = loc2;
    }
    public RenderAlternatingArrow(RenderManager renderManager, ResourceLocation location, ResourceLocation loc2, int timer) {
        super(renderManager);
        this.switch_timer = 5;
        this.texture = location;
        this.texture2 = loc2;
        this.switch_timer = timer;
    }
    public void func_76986_a(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        func_180548_c(entity);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179109_b((float) x, (float) y, (float) z);
        GlStateManager.func_179114_b((((EntityBaseThrowable) entity).field_70126_B + ((((EntityBaseThrowable) entity).field_70177_z - ((EntityBaseThrowable) entity).field_70126_B) * partialTicks)) - 90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.func_179114_b(((EntityBaseThrowable) entity).field_70127_C + ((((EntityBaseThrowable) entity).field_70125_A - ((EntityBaseThrowable) entity).field_70127_C) * partialTicks), 0.0f, 0.0f, 1.0f);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179091_B();
        if (0.0f > 0.0f) {
            float f10 = (-MathHelper.func_76126_a(0.0f * 3.0f)) * 0.0f;
            GlStateManager.func_179114_b(f10, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.func_179114_b(45.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.func_179152_a(0.05625f, 0.05625f, 0.05625f);
        GlStateManager.func_179109_b(-4.0f, 0.0f, 0.0f);
        if (this.field_188301_f) {
            GlStateManager.func_179142_g();
            GlStateManager.func_187431_e(func_188298_c(entity));
        }
        GlStateManager.func_187432_a(0.05625f, 0.0f, 0.0f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(-7.0d, -2.0d, -2.0d).func_187315_a(0.0d, 0.15625d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, -2.0d, 2.0d).func_187315_a(0.15625d, 0.15625d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, 2.0d, 2.0d).func_187315_a(0.15625d, 0.3125d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, 2.0d, -2.0d).func_187315_a(0.0d, 0.3125d).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_187432_a(-0.05625f, 0.0f, 0.0f);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(-7.0d, 2.0d, -2.0d).func_187315_a(0.0d, 0.15625d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, 2.0d, 2.0d).func_187315_a(0.15625d, 0.15625d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, -2.0d, 2.0d).func_187315_a(0.15625d, 0.3125d).func_181675_d();
        bufferbuilder.func_181662_b(-7.0d, -2.0d, -2.0d).func_187315_a(0.0d, 0.3125d).func_181675_d();
        tessellator.func_78381_a();
        for (int j = 0; j < 4; j++) {
            GlStateManager.func_179114_b(90.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.func_187432_a(0.0f, 0.0f, 0.05625f);
            bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            bufferbuilder.func_181662_b(-8.0d, -2.0d, 0.0d).func_187315_a(0.0d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(8.0d, -2.0d, 0.0d).func_187315_a(0.5d, 0.0d).func_181675_d();
            bufferbuilder.func_181662_b(8.0d, 2.0d, 0.0d).func_187315_a(0.5d, 0.15625d).func_181675_d();
            bufferbuilder.func_181662_b(-8.0d, 2.0d, 0.0d).func_187315_a(0.0d, 0.15625d).func_181675_d();
            tessellator.func_78381_a();
        }
        if (this.field_188301_f) {
            GlStateManager.func_187417_n();
            GlStateManager.func_179119_h();
        }
        GlStateManager.func_179101_C();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
        super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    }
    @Nullable
    public ResourceLocation func_110775_a(T entity) {
        if ((((EntityBaseThrowable) entity).field_70173_aa % this.switch_timer) * 2 < this.switch_timer) {
            return this.texture;
        }
        return this.texture2;
    }
}
