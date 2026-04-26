package xol.lostinfinity.mob.model.deviant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantCreeper;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanCreeper;
public class ModelDeviantCreeper extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legbl;
    public ModelRenderer legfl;
    public ModelRenderer legfr;
    public ModelRenderer head;
    public ModelRenderer legbr;
    public ModelRenderer legblt;
    public ModelRenderer legflt;
    public ModelRenderer legfrt;
    public ModelRenderer legbrt;
    public ModelRenderer body2;
    public ModelRenderer legblm;
    public ModelRenderer legflm;
    public ModelRenderer legfrm;
    public ModelRenderer legbrm;
    public ModelRenderer head2;
    public ModelDeviantCreeper() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0f, 4.0f, 0.0f);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.legblm = new ModelRenderer(this, 45, 16);
        this.legblm.func_78793_a(8.0f, 13.0f, 6.0f);
        this.legblm.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 2, 2, 0.0f);
        this.legblt = new ModelRenderer(this, 40, 0);
        this.legblt.field_78809_i = true;
        this.legblt.func_78793_a(8.0f, 13.0f, 6.0f);
        this.legblt.func_78790_a(-6.0f, -2.0f, -2.0f, 8, 2, 4, 0.0f);
        setRotateAngle(this.legblt, 0.0f, -0.7853982f, 0.0f);
        this.legflt = new ModelRenderer(this, 40, 0);
        this.legflt.field_78809_i = true;
        this.legflt.func_78793_a(8.0f, 13.0f, -6.0f);
        this.legflt.func_78790_a(-6.0f, -2.0f, -2.0f, 8, 2, 4, 0.0f);
        setRotateAngle(this.legflt, 0.0f, 0.7853982f, 0.0f);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.func_78793_a(0.0f, 4.0f, 0.0f);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.legbrt = new ModelRenderer(this, 40, 0);
        this.legbrt.func_78793_a(-8.0f, 13.0f, 6.0f);
        this.legbrt.func_78790_a(-2.0f, -2.0f, -2.0f, 8, 2, 4, 0.0f);
        setRotateAngle(this.legbrt, 0.0f, 0.7853982f, 0.0f);
        this.body2 = new ModelRenderer(this, 2, 35);
        this.body2.func_78793_a(0.0f, 4.0f, 0.0f);
        this.body2.func_78790_a(-6.0f, 0.0f, -4.0f, 12, 18, 8, 0.0f);
        this.legfl = new ModelRenderer(this, 0, 16);
        this.legfl.func_78793_a(8.0f, 13.0f, -6.0f);
        this.legfl.func_78790_a(-2.0f, 2.0f, -2.0f, 4, 9, 4, 0.0f);
        this.legflm = new ModelRenderer(this, 45, 16);
        this.legflm.func_78793_a(8.0f, 13.0f, -6.0f);
        this.legflm.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 2, 2, 0.0f);
        this.legfrm = new ModelRenderer(this, 45, 16);
        this.legfrm.func_78793_a(-8.0f, 13.0f, -6.0f);
        this.legfrm.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 2, 2, 0.0f);
        this.legbl = new ModelRenderer(this, 0, 16);
        this.legbl.func_78793_a(8.0f, 13.0f, 6.0f);
        this.legbl.func_78790_a(-2.0f, 2.0f, -2.0f, 4, 9, 4, 0.0f);
        this.legfrt = new ModelRenderer(this, 40, 0);
        this.legfrt.func_78793_a(-8.0f, 13.0f, -6.0f);
        this.legfrt.func_78790_a(-2.0f, -2.0f, -2.0f, 8, 2, 4, 0.0f);
        setRotateAngle(this.legfrt, 0.0f, -0.7853982f, 0.0f);
        this.legfr = new ModelRenderer(this, 0, 16);
        this.legfr.func_78793_a(-8.0f, 13.0f, -6.0f);
        this.legfr.func_78790_a(-2.0f, 2.0f, -2.0f, 4, 9, 4, 0.0f);
        this.head2 = new ModelRenderer(this, 45, 23);
        this.head2.func_78793_a(0.0f, 4.0f, 0.0f);
        this.head2.func_78790_a(-4.5f, -8.5f, -4.5f, 9, 9, 9, 0.0f);
        this.legbr = new ModelRenderer(this, 0, 16);
        this.legbr.func_78793_a(-8.0f, 13.0f, 6.0f);
        this.legbr.func_78790_a(-2.0f, 2.0f, -2.0f, 4, 9, 4, 0.0f);
        this.legbrm = new ModelRenderer(this, 45, 16);
        this.legbrm.func_78793_a(-8.0f, 13.0f, 6.0f);
        this.legbrm.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 2, 2, 0.0f);
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        boolean cageVis = false;
        if (entity instanceof EntityDeviantCreeper) {
            cageVis = ((EntityDeviantCreeper) entity).getCageVisible();
        } else if (entity instanceof EntityTitanCreeper) {
            cageVis = ((EntityTitanCreeper) entity).getCageVisible();
        }
        this.head.func_78785_a(f5);
        this.legblm.func_78785_a(f5);
        this.legblt.func_78785_a(f5);
        this.legflt.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.legbrt.func_78785_a(f5);
        if (cageVis) {
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.75f);
            this.body2.func_78785_a(f5);
            GlStateManager.func_179084_k();
        }
        this.legfl.func_78785_a(f5);
        this.legflm.func_78785_a(f5);
        this.legfrm.func_78785_a(f5);
        this.legbl.func_78785_a(f5);
        this.legfrt.func_78785_a(f5);
        this.legfr.func_78785_a(f5);
        if (cageVis) {
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.75f);
            this.head2.func_78785_a(f5);
            GlStateManager.func_179084_k();
        }
        this.legbr.func_78785_a(f5);
        this.legbrm.func_78785_a(f5);
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.head2.field_78796_g = netHeadYaw * 0.017453292f;
        this.head2.field_78795_f = headPitch * 0.017453292f;
        float f8 = Math.abs(MathHelper.func_76126_a((limbSwing * 0.6662f) + 3.1415927f) * 0.4f) * limbSwingAmount;
        this.legfl.field_78808_h = (-0.58119464f) + f8;
        this.legflt.field_78808_h = (-0.58119464f) + f8;
        this.legflm.field_78808_h = (-0.58119464f) + f8;
        this.legbl.field_78808_h = (-0.58119464f) + f8;
        this.legblt.field_78808_h = (-0.58119464f) + f8;
        this.legblm.field_78808_h = (-0.58119464f) + f8;
        this.legbr.field_78808_h = -this.legbl.field_78808_h;
        this.legbrt.field_78808_h = -this.legbl.field_78808_h;
        this.legbrm.field_78808_h = -this.legbl.field_78808_h;
        this.legfr.field_78808_h = -this.legbl.field_78808_h;
        this.legfrt.field_78808_h = -this.legbl.field_78808_h;
        this.legfrm.field_78808_h = -this.legbl.field_78808_h;
        this.legfl.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legflt.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legflm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legfr.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legfrt.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legfrm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.1162f) * 0.9f * limbSwingAmount;
        this.legbr.field_78795_f = -this.legfl.field_78795_f;
        this.legbrt.field_78795_f = -this.legfl.field_78795_f;
        this.legbrm.field_78795_f = -this.legfl.field_78795_f;
        this.legbl.field_78795_f = -this.legfl.field_78795_f;
        this.legblt.field_78795_f = -this.legfl.field_78795_f;
        this.legblm.field_78795_f = -this.legfl.field_78795_f;
    }
}
