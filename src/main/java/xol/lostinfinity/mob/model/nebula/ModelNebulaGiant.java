package xol.lostinfinity.mob.model.nebula;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.nebula.EntityNebulaGiant;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/nebula/ModelNebulaGiant.class */
public class ModelNebulaGiant extends ModelBase {
    private final ModelRenderer BodyFront;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer BodyMiddle;
    private final ModelRenderer FinLeft;
    private final ModelRenderer FinRight;
    private final ModelRenderer BodyEnd;
    private final ModelRenderer TailfinLeft;
    private final ModelRenderer TailfinRight;
    private final ModelRenderer TailP1;
    private final ModelRenderer TailP2;
    private final ModelRenderer TailP3;

    public ModelNebulaGiant() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.BodyFront = new ModelRenderer(this);
        this.BodyFront.func_78793_a(0.0f, 18.0f, 1.0f);
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 16, 22, 2.0f, -6.0f, -12.0f, 4, 2, 16, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 19, 3, -2.0f, -1.0f, -14.0f, 4, 2, 16, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 19, 3.0f, -9.0f, -12.0f, 1, 3, 15, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 19, -5.0f, -9.0f, -12.0f, 1, 3, 15, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 16, 22, -6.0f, -6.0f, -12.0f, 4, 2, 16, 0.0f, true));
        this.cube_r1 = new ModelRenderer(this);
        this.cube_r1.func_78793_a(3.5f, -7.5f, -4.5f);
        this.BodyFront.func_78792_a(this.cube_r1);
        setRotationAngle(this.cube_r1, 0.0f, 0.0f, -3.1416f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 19, 2.5f, -11.5f, -7.5f, 1, 3, 15, 0.0f, false));
        this.cube_r2 = new ModelRenderer(this);
        this.cube_r2.func_78793_a(-5.5f, -2.0f, -5.0f);
        this.BodyFront.func_78792_a(this.cube_r2);
        setRotationAngle(this.cube_r2, 0.0f, 0.0f, -0.5236f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, 1.5f, -2.0f, -6.0f, 1, 5, 14, 0.0f, true));
        this.cube_r3 = new ModelRenderer(this);
        this.cube_r3.func_78793_a(5.5f, -2.0f, -5.0f);
        this.BodyFront.func_78792_a(this.cube_r3);
        setRotationAngle(this.cube_r3, 0.0f, 0.0f, 0.5236f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 0, -2.5f, -2.0f, -6.0f, 1, 5, 14, 0.0f, false));
        this.BodyMiddle = new ModelRenderer(this);
        this.BodyMiddle.func_78793_a(0.0f, -1.0f, 4.0f);
        this.BodyFront.func_78792_a(this.BodyMiddle);
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 58, 29, -6.0f, -5.0f, 0.0f, 12, 12, 23, 0.0f, false));
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 60, 34, -4.0f, -1.0f, -2.0f, 8, 8, 2, 0.0f, false));
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 36, 25, -1.0f, -10.0f, 1.0f, 1, 5, 21, 0.0f, false));
        this.FinLeft = new ModelRenderer(this);
        this.FinLeft.func_78793_a(6.0f, 0.5f, 8.0f);
        this.BodyMiddle.func_78792_a(this.FinLeft);
        this.FinLeft.field_78804_l.add(new ModelBox(this.FinLeft, 0, 51, 0.0f, -0.5f, -6.0f, 14, 1, 12, 0.0f, false));
        this.FinRight = new ModelRenderer(this);
        this.FinRight.func_78793_a(-6.0f, 0.5f, 8.0f);
        this.BodyMiddle.func_78792_a(this.FinRight);
        this.FinRight.field_78804_l.add(new ModelBox(this.FinRight, 0, 51, -14.0f, -0.5f, -6.0f, 14, 1, 12, 0.0f, true));
        this.BodyEnd = new ModelRenderer(this);
        this.BodyEnd.func_78793_a(0.0f, 1.0f, 23.0f);
        this.BodyMiddle.func_78792_a(this.BodyEnd);
        this.BodyEnd.field_78804_l.add(new ModelBox(this.BodyEnd, 76, 2, -5.0f, -5.0f, 0.0f, 10, 10, 16, 0.0f, false));
        this.BodyEnd.field_78804_l.add(new ModelBox(this.BodyEnd, 43, 32, -1.0f, -9.0f, 1.0f, 1, 4, 14, 0.0f, false));
        this.TailfinLeft = new ModelRenderer(this);
        this.TailfinLeft.func_78793_a(5.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinLeft);
        this.TailfinLeft.field_78804_l.add(new ModelBox(this.TailfinLeft, 0, 41, 0.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, false));
        this.TailfinRight = new ModelRenderer(this);
        this.TailfinRight.func_78793_a(-5.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinRight);
        this.TailfinRight.field_78804_l.add(new ModelBox(this.TailfinRight, 0, 41, -10.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, true));
        this.TailP1 = new ModelRenderer(this);
        this.TailP1.func_78793_a(0.0f, -3.0f, 15.0f);
        this.BodyEnd.func_78792_a(this.TailP1);
        this.TailP1.field_78804_l.add(new ModelBox(this.TailP1, 44, 0, -3.0f, -1.0f, 0.0f, 6, 2, 16, 0.0f, false));
        this.TailP2 = new ModelRenderer(this);
        this.TailP2.func_78793_a(3.0f, 2.0f, 15.0f);
        this.BodyEnd.func_78792_a(this.TailP2);
        setRotationAngle(this.TailP2, 0.0f, 0.0f, 2.3562f);
        this.TailP2.field_78804_l.add(new ModelBox(this.TailP2, 44, 0, -3.0f, -1.0f, 0.0f, 6, 2, 16, 0.0f, false));
        this.TailP3 = new ModelRenderer(this);
        this.TailP3.func_78793_a(-4.0f, 2.0f, 15.0f);
        this.BodyEnd.func_78792_a(this.TailP3);
        setRotationAngle(this.TailP3, 0.0f, 0.0f, -2.3562f);
        this.TailP3.field_78804_l.add(new ModelBox(this.TailP3, 44, 0, -3.0f, -1.0f, 0.0f, 6, 2, 16, 0.0f, false));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        EntityNebulaGiant nebula = (EntityNebulaGiant) entity;
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b(770, 771);
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, nebula.getModelAlpha());
        this.BodyFront.func_78785_a(f5);
        GlStateManager.func_179084_k();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.FinLeft.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.1f) * 0.6f;
        this.FinRight.field_78808_h = (-MathHelper.func_76134_b(ageInTicks * 0.1f)) * 0.6f;
        this.TailfinLeft.field_78808_h = MathHelper.func_76134_b(ageInTicks * 1.1f) * 0.15f;
        this.TailfinRight.field_78808_h = (-MathHelper.func_76134_b(ageInTicks * 1.1f)) * 0.15f;
        this.BodyMiddle.field_78796_g = MathHelper.func_76134_b(ageInTicks * 0.1f) * 0.3f;
        this.BodyEnd.field_78796_g = MathHelper.func_76126_a(ageInTicks * 0.1f) * 0.3f;
        this.TailP1.field_78795_f = (float) (((double) (MathHelper.func_76134_b(ageInTicks * 0.15f) * 0.3f)) + 0.39269908169872414d);
        this.TailP2.field_78795_f = (float) (((double) (MathHelper.func_76134_b(ageInTicks * 0.15f) * 0.3f)) + 0.39269908169872414d);
        this.TailP3.field_78795_f = (float) (((double) (MathHelper.func_76134_b(ageInTicks * 0.15f) * 0.3f)) + 0.39269908169872414d);
    }
}
