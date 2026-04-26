package xol.lostinfinity.mob.model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelLostDeviant extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer bodycage;
    public ModelRenderer wingr;
    public ModelRenderer wingl;
    public ModelLostDeviant() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.body = new ModelRenderer(this, 32, 0);
        this.body.func_78793_a(0.0f, 3.0f, 0.0f);
        this.body.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 12, 6, 0.0f);
        this.wingl = new ModelRenderer(this, 37, 37);
        this.wingl.field_78809_i = true;
        this.wingl.func_78793_a(2.0f, 3.0f, 6.0f);
        this.wingl.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 14, 12, 0.0f);
        this.bodycage = new ModelRenderer(this, 0, 18);
        this.bodycage.func_78793_a(0.0f, 3.0f, 0.0f);
        this.bodycage.func_78790_a(-6.0f, -1.0f, -6.0f, 12, 14, 12, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.wingr = new ModelRenderer(this, 37, 37);
        this.wingr.func_78793_a(-2.0f, 3.0f, 6.0f);
        this.wingr.func_78790_a(-0.5f, -1.0f, 0.0f, 1, 14, 12, 0.0f);
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.3f + (0.5f * MathHelper.func_76126_a(f2 * 0.3f)));
        this.body.func_78785_a(f5);
        this.wingl.func_78785_a(f5);
        this.bodycage.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.wingr.func_78785_a(f5);
        GlStateManager.func_179084_k();
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.wingl.field_78796_g = (MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f * 0.17f) + 0.3f;
        this.wingr.field_78796_g = ((MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f) * (-0.17f)) - 0.3f;
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.bodycage.field_78796_g = ageInTicks * (-0.15f);
    }
}
