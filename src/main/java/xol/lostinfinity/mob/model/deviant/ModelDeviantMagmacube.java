package xol.lostinfinity.mob.model.deviant;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import xol.lostinfinity.mob.entity.deviant.EntityDeviantMagmacube;
import xol.lostinfinity.mob.entity.deviant.titan.EntityTitanMagmacube;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/deviant/ModelDeviantMagmacube.class */
public class ModelDeviantMagmacube extends ModelBase {
    public ModelRenderer outer;
    public ModelRenderer middle;
    public ModelRenderer inner;
    public ModelRenderer eye;
    public ModelRenderer extr1;
    public ModelRenderer extr2;
    public ModelRenderer extl1;
    public ModelRenderer extl2;
    public ModelRenderer mouth;

    public ModelDeviantMagmacube() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.inner = new ModelRenderer(this, 0, 46);
        this.inner.func_78793_a(0.0f, 18.0f, 0.0f);
        this.inner.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.middle = new ModelRenderer(this, 0, 25);
        this.middle.func_78793_a(0.0f, 18.0f, 0.0f);
        this.middle.func_78790_a(-5.0f, -5.0f, -5.0f, 10, 10, 10, 0.0f);
        this.extr1 = new ModelRenderer(this, 35, 38);
        this.extr1.func_78793_a(0.0f, 18.0f, 0.0f);
        this.extr1.func_78790_a(-7.0f, -6.0f, -6.0f, 1, 12, 12, 0.0f);
        this.extl2 = new ModelRenderer(this, 35, 38);
        this.extl2.func_78793_a(0.0f, 18.0f, 0.0f);
        this.extl2.func_78790_a(7.0f, -6.0f, -6.0f, 1, 12, 12, 0.0f);
        this.mouth = new ModelRenderer(this, 42, 3);
        this.mouth.func_78793_a(0.0f, 18.0f, 0.0f);
        this.mouth.func_78790_a(-3.0f, 0.7f, -4.5f, 6, 3, 2, 0.0f);
        this.extr2 = new ModelRenderer(this, 35, 38);
        this.extr2.func_78793_a(0.0f, 18.0f, 0.0f);
        this.extr2.func_78790_a(-8.0f, -6.0f, -6.0f, 1, 12, 12, 0.0f);
        this.outer = new ModelRenderer(this, 0, 0);
        this.outer.func_78793_a(0.0f, 18.0f, 0.0f);
        this.outer.func_78790_a(-6.0f, -6.0f, -6.0f, 12, 12, 12, 0.0f);
        this.eye = new ModelRenderer(this, 42, 27);
        this.eye.func_78793_a(0.0f, 18.0f, 0.0f);
        this.eye.func_78790_a(-2.0f, -3.5f, -4.5f, 4, 4, 2, 0.0f);
        this.extl1 = new ModelRenderer(this, 35, 38);
        this.extl1.func_78793_a(0.0f, 18.0f, 0.0f);
        this.extl1.func_78790_a(6.0f, -6.0f, -6.0f, 1, 12, 12, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.eye.func_78785_a(f5);
        this.mouth.func_78785_a(f5);
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.65f);
        this.middle.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.45f);
        this.extr1.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.45f);
        this.outer.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.45f);
        this.extr2.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.45f);
        this.extl2.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.45f);
        this.extl1.func_78785_a(f5);
        GlStateManager.func_179084_k();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 0.8f);
        this.inner.func_78785_a(f5);
        GlStateManager.func_179084_k();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        int counter = 1;
        if (entityIn instanceof EntityDeviantMagmacube) {
            EntityDeviantMagmacube devMagma = (EntityDeviantMagmacube) entityIn;
            counter = 40 - (8 * devMagma.getMutation());
        } else if (entityIn instanceof EntityTitanMagmacube) {
            counter = 30;
        }
        if (ageInTicks % counter <= counter / 2) {
            this.outer.field_78795_f = ageInTicks;
        } else {
            this.outer.field_78795_f = 0.0f;
        }
        this.middle.field_78795_f = this.outer.field_78795_f;
        this.inner.field_78795_f = this.outer.field_78795_f;
        this.eye.field_78795_f = this.outer.field_78795_f;
        this.mouth.field_78795_f = this.outer.field_78795_f;
        this.extl1.field_78795_f = this.outer.field_78795_f;
        this.extl2.field_78795_f = this.outer.field_78795_f;
        this.extr1.field_78795_f = this.outer.field_78795_f;
        this.extr2.field_78795_f = this.outer.field_78795_f;
        this.extl1.field_82906_o = 0.2f * ((float) Math.abs(Math.sin(ageInTicks * 0.1f)));
        this.extl2.field_82906_o = 0.4f * ((float) Math.abs(Math.sin(ageInTicks * 0.1f)));
        this.extr1.field_82906_o = -this.extl1.field_82906_o;
        this.extr2.field_82906_o = -this.extl2.field_82906_o;
    }
}
