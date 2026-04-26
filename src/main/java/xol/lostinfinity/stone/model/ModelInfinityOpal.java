package xol.lostinfinity.stone.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/stone/model/ModelInfinityOpal.class */
public class ModelInfinityOpal extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;

    public ModelInfinityOpal() {
        this.field_78090_t = 128;
        this.field_78089_u = 32;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.shape1.func_78790_a(-2.0f, -9.0f, -2.0f, 4, 18, 4, 0.0f);
        this.shape3 = new ModelRenderer(this, 51, 0);
        this.shape3.func_78793_a(0.0f, 5.0f, 0.0f);
        this.shape3.func_78790_a(-4.0f, -7.0f, -4.0f, 8, 14, 8, 0.0f);
        this.shape2 = new ModelRenderer(this, 20, 0);
        this.shape2.func_78793_a(0.0f, 5.0f, 0.0f);
        this.shape2.func_78790_a(-3.0f, -8.0f, -3.0f, 6, 16, 6, 0.0f);
        this.shape4 = new ModelRenderer(this, 86, 0);
        this.shape4.func_78793_a(0.0f, 5.0f, 0.0f);
        this.shape4.func_78790_a(-5.0f, -5.0f, -5.0f, 10, 10, 10, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.7f + (0.2f * MathHelper.func_76126_a(f2 * 0.1f)));
        this.shape1.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.7f + (0.2f * MathHelper.func_76126_a(f2 * 0.1f)));
        this.shape3.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.7f + (0.2f * MathHelper.func_76126_a(f2 * 0.1f)));
        this.shape2.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.7f + (0.2f * MathHelper.func_76126_a(f2 * 0.1f)));
        this.shape4.func_78785_a(f5);
        GlStateManager.func_179084_k();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.shape1.field_78796_g = ((MathHelper.func_76134_b(ageInTicks * 0.05f) * 3.1415927f) * 0.67f) - 0.7f;
        this.shape2.field_78796_g = ((MathHelper.func_76134_b(ageInTicks * 0.05f) * 3.1415927f) * 0.67f) - 0.7f;
        this.shape3.field_78796_g = ((MathHelper.func_76134_b(ageInTicks * 0.05f) * 3.1415927f) * 0.67f) - 0.7f;
        this.shape4.field_78796_g = ((MathHelper.func_76134_b(ageInTicks * 0.05f) * 3.1415927f) * 0.67f) - 0.7f;
    }
}
