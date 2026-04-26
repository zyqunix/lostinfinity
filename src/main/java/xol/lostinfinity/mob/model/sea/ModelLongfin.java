package xol.lostinfinity.mob.model.sea;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/sea/ModelLongfin.class */
public class ModelLongfin extends ModelBase {
    private final ModelRenderer BodyFront;
    private final ModelRenderer BodyMiddle;
    private final ModelRenderer FinLeft;
    private final ModelRenderer FinRight;
    private final ModelRenderer BodyEnd;
    private final ModelRenderer TailfinLeft;
    private final ModelRenderer TailfinRight;

    public ModelLongfin() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.BodyFront = new ModelRenderer(this);
        this.BodyFront.func_78793_a(0.0f, 18.0f, -4.0f);
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 0, -2.0f, -5.0f, -4.0f, 4, 10, 8, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -0.5f, -5.0f, -2.0f, 5, 5, 5, -2.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -0.5f, -4.0f, -4.0f, 5, 5, 5, -2.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -0.5f, -3.0f, -2.0f, 5, 5, 5, -2.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -4.5f, -3.0f, -2.0f, 5, 5, 5, -2.0f, true));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -4.5f, -4.0f, -4.0f, 5, 5, 5, -2.0f, true));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 0, -4.5f, -5.0f, -2.0f, 5, 5, 5, -2.0f, true));
        this.BodyMiddle = new ModelRenderer(this);
        this.BodyMiddle.func_78793_a(0.0f, -1.0f, 4.0f);
        this.BodyFront.func_78792_a(this.BodyMiddle);
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 74, 29, -2.0f, -5.0f, 0.0f, 4, 12, 23, 0.0f, false));
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 28, 13, -1.0f, -10.0f, 1.0f, 1, 5, 21, 0.0f, false));
        this.FinLeft = new ModelRenderer(this);
        this.FinLeft.func_78793_a(2.0f, 0.5f, 8.0f);
        this.BodyMiddle.func_78792_a(this.FinLeft);
        this.FinLeft.field_78804_l.add(new ModelBox(this.FinLeft, 15, 41, 0.0f, -0.5f, -6.0f, 14, 1, 12, 0.0f, false));
        this.FinRight = new ModelRenderer(this);
        this.FinRight.func_78793_a(-2.0f, 0.5f, 8.0f);
        this.BodyMiddle.func_78792_a(this.FinRight);
        this.FinRight.field_78804_l.add(new ModelBox(this.FinRight, 15, 41, -14.0f, -0.5f, -6.0f, 14, 1, 12, 0.0f, true));
        this.BodyEnd = new ModelRenderer(this);
        this.BodyEnd.func_78793_a(0.0f, 1.0f, 23.0f);
        this.BodyMiddle.func_78792_a(this.BodyEnd);
        this.BodyEnd.field_78804_l.add(new ModelBox(this.BodyEnd, 57, 25, -1.5f, -5.0f, 0.0f, 3, 10, 16, 0.0f, false));
        this.TailfinLeft = new ModelRenderer(this);
        this.TailfinLeft.func_78793_a(1.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinLeft);
        this.TailfinLeft.field_78804_l.add(new ModelBox(this.TailfinLeft, 36, 55, 0.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, false));
        this.TailfinRight = new ModelRenderer(this);
        this.TailfinRight.func_78793_a(-1.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinRight);
        this.TailfinRight.field_78804_l.add(new ModelBox(this.TailfinRight, 36, 55, -10.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, true));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BodyFront.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float tailSpeed = 0.15f;
        float frontFinSpeed = 0.15f;
        float rearFinSpeed = 0.65f;
        if (limbSwing > 0.0f) {
            tailSpeed = 0.15f + (limbSwing * 5.0E-4f);
            frontFinSpeed = 0.15f + (limbSwing * 5.0E-4f);
            rearFinSpeed = 0.65f + (limbSwing * 5.0E-4f);
        }
        this.BodyEnd.field_78796_g = MathHelper.func_76126_a(ageInTicks * tailSpeed) * 0.5f;
        this.BodyMiddle.field_78796_g = (-MathHelper.func_76126_a(ageInTicks * tailSpeed)) * 0.2f;
        this.FinLeft.field_78808_h = MathHelper.func_76126_a((ageInTicks + 4) * frontFinSpeed) * 0.4f;
        this.FinRight.field_78808_h = (-MathHelper.func_76126_a((ageInTicks + 4) * frontFinSpeed)) * 0.4f;
        this.TailfinLeft.field_78808_h = MathHelper.func_76126_a(ageInTicks * rearFinSpeed) * 0.3f;
        this.TailfinRight.field_78808_h = (-MathHelper.func_76126_a(ageInTicks * rearFinSpeed)) * 0.3f;
    }
}
