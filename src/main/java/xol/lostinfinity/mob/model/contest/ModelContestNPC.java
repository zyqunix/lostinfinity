package xol.lostinfinity.mob.model.contest;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/contest/ModelContestNPC.class */
public class ModelContestNPC extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer armr;
    public ModelRenderer arml;
    public ModelRenderer legl;
    public ModelRenderer bodytop;
    public ModelRenderer coat;
    public ModelRenderer legr;

    public ModelContestNPC() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.legl = new ModelRenderer(this, 0, 22);
        this.legl.field_78809_i = true;
        this.legl.func_78793_a(2.0f, 12.0f, 0.0f);
        this.legl.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.arml = new ModelRenderer(this, 44, 22);
        this.arml.func_78793_a(0.0f, 3.0f, 0.0f);
        this.arml.func_78790_a(4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.coat = new ModelRenderer(this, 0, 38);
        this.coat.func_78793_a(0.0f, 0.0f, 0.0f);
        this.coat.func_78790_a(-4.0f, 0.0f, -3.0f, 8, 18, 6, 0.5f);
        this.legr = new ModelRenderer(this, 0, 22);
        this.legr.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.legr.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.armr = new ModelRenderer(this, 44, 22);
        this.armr.func_78793_a(0.0f, 3.0f, 0.0f);
        this.armr.func_78790_a(-8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.bodytop = new ModelRenderer(this, 16, 20);
        this.bodytop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bodytop.func_78790_a(-4.0f, 0.0f, -3.0f, 8, 12, 6, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.legl.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.arml.func_78785_a(f5);
        this.coat.func_78785_a(f5);
        this.legr.func_78785_a(f5);
        this.armr.func_78785_a(f5);
        this.bodytop.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.legr.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        this.legl.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 1.4f * limbSwingAmount * 0.5f;
        this.armr.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
        this.arml.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
    }
}
