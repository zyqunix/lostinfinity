package xol.lostinfinity.mob.model.starforge;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.starforge.EntityFlurky;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/starforge/ModelFlurky.class */
public class ModelFlurky extends ModelBase {
    public ModelRenderer armr;
    public ModelRenderer legr;
    public ModelRenderer body;
    public ModelRenderer arml;
    public ModelRenderer legl;
    public ModelRenderer headbottom;
    public ModelRenderer tail;
    public ModelRenderer tail2;
    public ModelRenderer headtop;
    public ModelRenderer headtop2;
    public ModelRenderer headtop3;
    public ModelRenderer headbottom2;
    public ModelRenderer headbottom3;
    public ModelRenderer headbottom4;

    public ModelFlurky() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.tail = new ModelRenderer(this, 2, 46);
        this.tail.func_78793_a(0.0f, 10.0f, 1.0f);
        this.tail.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 12, 2, 0.0f);
        setRotateAngle(this.tail, 0.5235988f, 0.0f, 0.0f);
        this.legr = new ModelRenderer(this, 0, 16);
        this.legr.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.legr.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.legl = new ModelRenderer(this, 0, 16);
        this.legl.field_78809_i = true;
        this.legl.func_78793_a(1.9f, 12.0f, 0.1f);
        this.legl.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.headbottom2 = new ModelRenderer(this, 34, 34);
        this.headbottom2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headbottom2.func_78790_a(-3.5f, -3.0f, -3.5f, 7, 1, 7, 0.0f);
        this.headbottom4 = new ModelRenderer(this, 26, 34);
        this.headbottom4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headbottom4.func_78790_a(0.3f, -5.0f, -2.5f, 3, 3, 2, 0.0f);
        this.headtop3 = new ModelRenderer(this, 25, 0);
        this.headtop3.func_78793_a(0.0f, -2.0f, 4.0f);
        this.headtop3.func_78790_a(-4.0f, -13.0f, -1.0f, 3, 5, 1, 0.0f);
        this.armr = new ModelRenderer(this, 40, 16);
        this.armr.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.armr.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.headbottom = new ModelRenderer(this, 0, 34);
        this.headbottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headbottom.func_78790_a(-4.0f, -2.0f, -4.0f, 8, 2, 8, 0.0f);
        this.tail2 = new ModelRenderer(this, 12, 46);
        this.tail2.func_78793_a(0.0f, 10.0f, 1.0f);
        this.tail2.func_78790_a(-1.5f, 12.0f, -1.5f, 3, 3, 3, 0.0f);
        setRotateAngle(this.tail2, 0.5235988f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.headbottom3 = new ModelRenderer(this, 26, 34);
        this.headbottom3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headbottom3.func_78790_a(-3.3f, -5.0f, -2.5f, 3, 3, 2, 0.0f);
        this.headtop = new ModelRenderer(this, 0, 0);
        this.headtop.func_78793_a(0.0f, -2.0f, 4.0f);
        this.headtop.func_78790_a(-4.0f, -8.0f, -8.0f, 8, 8, 8, 0.0f);
        this.arml = new ModelRenderer(this, 40, 16);
        this.arml.field_78809_i = true;
        this.arml.func_78793_a(5.0f, 2.0f, 0.0f);
        this.arml.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.headtop2 = new ModelRenderer(this, 25, 0);
        this.headtop2.func_78793_a(0.0f, -2.0f, 4.0f);
        this.headtop2.func_78790_a(1.0f, -13.0f, -1.0f, 3, 5, 1, 0.0f);
        this.headbottom.func_78792_a(this.headbottom2);
        this.headbottom.func_78792_a(this.headbottom4);
        this.headbottom.func_78792_a(this.headtop3);
        this.headbottom.func_78792_a(this.headbottom3);
        this.headbottom.func_78792_a(this.headtop);
        this.headbottom.func_78792_a(this.headtop2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tail.func_78785_a(f5);
        this.legr.func_78785_a(f5);
        this.legl.func_78785_a(f5);
        this.armr.func_78785_a(f5);
        this.headbottom.func_78785_a(f5);
        this.tail2.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.arml.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.headbottom.field_78796_g = netHeadYaw * 0.017453292f;
        this.headbottom.field_78795_f = headPitch * 0.017453292f;
        EntityFlurky flurky = (EntityFlurky) entityIn;
        if (flurky.getAttackTime() != -1) {
            int newCount = flurky.field_70173_aa - flurky.getAttackTime();
            this.headtop.field_78795_f = (-0.5235988f) + (MathHelper.func_76134_b(newCount * 0.1f) * 0.5f);
            this.headtop2.field_78795_f = this.headtop.field_78795_f;
            this.headtop3.field_78795_f = this.headtop.field_78795_f;
        } else {
            this.headtop.field_78795_f = 0.0f;
            this.headtop2.field_78795_f = 0.0f;
            this.headtop3.field_78795_f = 0.0f;
        }
        this.legr.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        this.legl.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 1.4f * limbSwingAmount * 0.5f;
        this.arml.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.armr.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
    }
}
