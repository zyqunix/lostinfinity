package xol.lostinfinity.mob.model.labyrinth;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelAspect extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer headinside;
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer horn3;
    public ModelRenderer horn4;
    public ModelRenderer horn5;
    public ModelRenderer horn6;
    public ModelRenderer backfin;
    public ModelAspect() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.horn3 = new ModelRenderer(this, 0, 55);
        this.horn3.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn3.func_78790_a(-5.0f, -5.5f, -9.5f, 2, 2, 4, 0.0f);
        setRotateAngle(this.horn3, -0.5235988f, 0.0f, 0.0f);
        this.horn5 = new ModelRenderer(this, 15, 55);
        this.horn5.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn5.func_78790_a(-4.5f, -8.5f, -9.5f, 1, 1, 3, 0.0f);
        setRotateAngle(this.horn5, -0.12217305f, 0.0f, 0.0f);
        this.backfin = new ModelRenderer(this, 31, 37);
        this.backfin.func_78793_a(0.0f, 5.0f, 0.0f);
        this.backfin.func_78790_a(-3.5f, -0.5f, 3.0f, 7, 1, 8, 0.0f);
        setRotateAngle(this.backfin, -0.5235988f, 0.0f, 0.0f);
        this.headinside = new ModelRenderer(this, 0, 26);
        this.headinside.func_78793_a(0.0f, 5.0f, 0.0f);
        this.headinside.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.horn1 = new ModelRenderer(this, 0, 43);
        this.horn1.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn1.func_78790_a(-5.5f, -4.5f, -7.0f, 3, 3, 8, 0.0f);
        setRotateAngle(this.horn1, -0.7853982f, 0.0f, 0.0f);
        this.horn4 = new ModelRenderer(this, 0, 55);
        this.horn4.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn4.func_78790_a(3.0f, -5.5f, -9.5f, 2, 2, 4, 0.0f);
        setRotateAngle(this.horn4, -0.5235988f, 0.0f, 0.0f);
        this.horn2 = new ModelRenderer(this, 0, 43);
        this.horn2.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn2.func_78790_a(2.5f, -4.5f, -7.0f, 3, 3, 8, 0.0f);
        setRotateAngle(this.horn2, -0.7853982f, 0.0f, 0.0f);
        this.horn6 = new ModelRenderer(this, 15, 55);
        this.horn6.func_78793_a(0.0f, 5.0f, 0.0f);
        this.horn6.func_78790_a(3.5f, -8.5f, -9.5f, 1, 1, 3, 0.0f);
        setRotateAngle(this.horn6, -0.12217305f, 0.0f, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0f, 5.0f, 0.0f);
        this.head.func_78790_a(-6.0f, -6.0f, -6.0f, 12, 12, 12, 0.0f);
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.horn3.func_78785_a(f5);
        this.horn5.func_78785_a(f5);
        this.backfin.func_78785_a(f5);
        this.headinside.func_78785_a(f5);
        this.horn1.func_78785_a(f5);
        this.horn4.func_78785_a(f5);
        this.horn2.func_78785_a(f5);
        this.horn6.func_78785_a(f5);
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.3f + (0.5f * MathHelper.func_76126_a(f2 * 0.05f)));
        this.head.func_78785_a(f5);
        GlStateManager.func_179084_k();
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.headinside.field_78796_g = netHeadYaw * 0.017453292f;
        this.headinside.field_78795_f = headPitch * 0.017453292f;
        this.horn1.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn1.field_78795_f = (headPitch * 0.017453292f) - 0.7853982f;
        this.horn2.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn2.field_78795_f = (headPitch * 0.017453292f) - 0.7853982f;
        this.horn3.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn3.field_78795_f = (headPitch * 0.017453292f) - 0.5235988f;
        this.horn4.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn4.field_78795_f = (headPitch * 0.017453292f) - 0.5235988f;
        this.horn5.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn5.field_78795_f = (headPitch * 0.017453292f) - 0.12217305f;
        this.horn6.field_78796_g = netHeadYaw * 0.017453292f;
        this.horn6.field_78795_f = (headPitch * 0.017453292f) - 0.12217305f;
        this.backfin.field_78795_f = ((MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f) * 0.15f) - 0.5235988f;
        this.backfin.field_78796_g = netHeadYaw * 0.017453292f;
    }
}
