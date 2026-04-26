package xol.lostinfinity.mob.model.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/boss/ModelNuxuro.class */
public class ModelNuxuro extends ModelBase {
    public ModelRenderer armr;
    public ModelRenderer legr;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer arml;
    public ModelRenderer legl;
    public ModelRenderer armr2;
    public ModelRenderer armr3;
    public ModelRenderer armr4;
    public ModelRenderer headrot;

    public ModelNuxuro() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.legr = new ModelRenderer(this, 0, 16);
        this.legr.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.legr.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.armr2 = new ModelRenderer(this, 56, 16);
        this.armr2.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.armr2.func_78790_a(-2.0f, -18.0f, -7.0f, 2, 38, 2, 0.0f);
        setRotateAngle(this.armr2, 0.5235988f, 0.0f, 0.0f);
        this.armr4 = new ModelRenderer(this, 13, 35);
        this.armr4.func_78793_a(-6.5f, -9.5f, -13.5f);
        this.armr4.func_78790_a(-3.0f, 20.0f, -3.0f, 6, 6, 6, 0.0f);
        this.arml = new ModelRenderer(this, 40, 16);
        this.arml.field_78809_i = true;
        this.arml.func_78793_a(5.0f, 2.0f, 0.0f);
        this.arml.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.legl = new ModelRenderer(this, 0, 16);
        this.legl.field_78809_i = true;
        this.legl.func_78793_a(1.9f, 12.0f, 0.1f);
        this.legl.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.armr3 = new ModelRenderer(this, 39, 33);
        this.armr3.func_78793_a(-6.5f, -9.5f, -13.5f);
        this.armr3.func_78790_a(-0.5f, 0.0f, -3.0f, 1, 20, 6, 0.0f);
        this.armr = new ModelRenderer(this, 40, 16);
        this.armr.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.armr.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.headrot = new ModelRenderer(this, 0, 49);
        this.headrot.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headrot.func_78790_a(-6.0f, -2.5f, -6.0f, 12, 2, 12, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.legr.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.armr2.func_78785_a(f5);
        this.armr4.func_78785_a(f5);
        this.arml.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.legl.func_78785_a(f5);
        this.armr3.func_78785_a(f5);
        this.armr.func_78785_a(f5);
        this.headrot.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.headrot.field_78796_g = ageInTicks * (-0.1f);
        this.legr.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        this.legl.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 1.4f * limbSwingAmount * 0.5f;
        this.arml.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.armr4.field_78795_f = MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
        this.armr3.field_78795_f = MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
    }
}
