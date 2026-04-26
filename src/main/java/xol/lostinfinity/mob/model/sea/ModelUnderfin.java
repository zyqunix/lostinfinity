package xol.lostinfinity.mob.model.sea;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelUnderfin extends ModelBase {
    private final ModelRenderer BodyFront;
    private final ModelRenderer BodyMiddle;
    private final ModelRenderer FinLeft;
    private final ModelRenderer FinLeft2;
    private final ModelRenderer FinRight1;
    private final ModelRenderer FinRight2;
    private final ModelRenderer Underfin1;
    private final ModelRenderer Underfin3;
    private final ModelRenderer Underfin2;
    private final ModelRenderer BodyEndTop;
    private final ModelRenderer BodyEndBottom;
    public ModelUnderfin() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.BodyFront = new ModelRenderer(this);
        this.BodyFront.func_78793_a(0.0f, 12.0f, -4.0f);
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 0, -2.0f, -5.0f, -4.0f, 4, 10, 8, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 2, 0.5f, -5.0f, -4.0f, 3, 8, 8, -1.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 25, 2, -3.5f, -5.0f, -4.0f, 3, 8, 8, -1.0f, true));
        this.BodyMiddle = new ModelRenderer(this);
        this.BodyMiddle.func_78793_a(0.0f, -1.0f, 4.0f);
        this.BodyFront.func_78792_a(this.BodyMiddle);
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 0, 29, -2.0f, -5.0f, 0.0f, 4, 12, 23, 0.0f, false));
        this.FinLeft = new ModelRenderer(this);
        this.FinLeft.func_78793_a(2.0f, 0.0f, 3.0f);
        this.BodyMiddle.func_78792_a(this.FinLeft);
        this.FinLeft.field_78804_l.add(new ModelBox(this.FinLeft, 0, 19, 0.0f, -4.0f, 0.0f, 18, 8, 1, 0.0f, false));
        this.FinLeft2 = new ModelRenderer(this);
        this.FinLeft2.func_78793_a(2.0f, 2.0f, 17.0f);
        this.BodyMiddle.func_78792_a(this.FinLeft2);
        this.FinLeft2.field_78804_l.add(new ModelBox(this.FinLeft2, 0, 19, 0.0f, -4.0f, 0.0f, 18, 8, 1, 0.0f, false));
        this.FinRight1 = new ModelRenderer(this);
        this.FinRight1.func_78793_a(-2.0f, 0.0f, 3.0f);
        this.BodyMiddle.func_78792_a(this.FinRight1);
        this.FinRight1.field_78804_l.add(new ModelBox(this.FinRight1, 0, 19, -18.0f, -4.0f, 0.0f, 18, 8, 1, 0.0f, true));
        this.FinRight2 = new ModelRenderer(this);
        this.FinRight2.func_78793_a(-2.0f, 2.0f, 17.0f);
        this.BodyMiddle.func_78792_a(this.FinRight2);
        this.FinRight2.field_78804_l.add(new ModelBox(this.FinRight2, 0, 19, -18.0f, -4.0f, 0.0f, 18, 8, 1, 0.0f, true));
        this.Underfin1 = new ModelRenderer(this);
        this.Underfin1.func_78793_a(-0.5f, 6.0f, 4.5f);
        this.BodyMiddle.func_78792_a(this.Underfin1);
        setRotationAngle(this.Underfin1, 0.2618f, 0.0f, 0.0f);
        this.Underfin1.field_78804_l.add(new ModelBox(this.Underfin1, 52, 25, -0.5f, 0.0f, -3.5f, 1, 12, 7, 0.0f, false));
        this.Underfin3 = new ModelRenderer(this);
        this.Underfin3.func_78793_a(-0.5f, 5.0f, 15.5f);
        this.BodyMiddle.func_78792_a(this.Underfin3);
        setRotationAngle(this.Underfin3, 0.2618f, 0.0f, 0.0f);
        this.Underfin3.field_78804_l.add(new ModelBox(this.Underfin3, 69, 24, -0.5f, 0.0f, -3.5f, 1, 12, 7, 0.0f, false));
        this.Underfin2 = new ModelRenderer(this);
        this.Underfin2.func_78793_a(-0.5f, 6.0f, 13.5f);
        this.BodyMiddle.func_78792_a(this.Underfin2);
        setRotationAngle(this.Underfin2, 0.2618f, 0.0f, 0.0f);
        this.BodyEndTop = new ModelRenderer(this);
        this.BodyEndTop.func_78793_a(0.0f, -1.5f, 22.0f);
        this.BodyMiddle.func_78792_a(this.BodyEndTop);
        this.BodyEndTop.field_78804_l.add(new ModelBox(this.BodyEndTop, 32, 34, -1.5f, -3.0f, 0.0f, 3, 5, 12, 0.0f, false));
        this.BodyEndTop.field_78804_l.add(new ModelBox(this.BodyEndTop, 69, 27, -1.0f, -8.0f, 2.0f, 1, 9, 18, 0.0f, false));
        this.BodyEndBottom = new ModelRenderer(this);
        this.BodyEndBottom.func_78793_a(0.0f, 4.5f, 22.0f);
        this.BodyMiddle.func_78792_a(this.BodyEndBottom);
        this.BodyEndBottom.field_78804_l.add(new ModelBox(this.BodyEndBottom, 49, 6, -1.5f, -3.0f, 0.0f, 3, 5, 12, 0.0f, false));
        this.BodyEndBottom.field_78804_l.add(new ModelBox(this.BodyEndBottom, 90, 37, -1.0f, -2.0f, 2.0f, 1, 9, 18, 0.0f, false));
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
        float frontFinSpeed = 0.45f;
        float rearFinSpeed = 0.25f;
        if (limbSwing > 0.0f) {
            frontFinSpeed = 0.45f + (limbSwing * 3.0E-4f);
            rearFinSpeed = 0.25f + (limbSwing * 3.0E-4f);
            if (this.Underfin1.field_78795_f < 0.75f) {
                this.Underfin1.field_78795_f += 0.01f;
            }
            if (this.Underfin3.field_78795_f < 0.75f) {
                this.Underfin3.field_78795_f += 0.01f;
            }
        } else {
            if (this.Underfin1.field_78795_f > 0.26f) {
                this.Underfin1.field_78795_f -= 0.01f;
            }
            if (this.Underfin3.field_78795_f > 0.26f) {
                this.Underfin3.field_78795_f -= 0.01f;
            }
        }
        this.FinLeft.field_78796_g = MathHelper.func_76126_a(ageInTicks * frontFinSpeed) * 0.3f;
        this.FinLeft2.field_78796_g = MathHelper.func_76126_a((ageInTicks + 4) * frontFinSpeed) * 0.3f;
        this.FinRight1.field_78796_g = (-MathHelper.func_76126_a(ageInTicks * frontFinSpeed)) * 0.3f;
        this.FinRight2.field_78796_g = (-MathHelper.func_76126_a((ageInTicks + 4) * frontFinSpeed)) * 0.3f;
        this.BodyEndTop.field_78796_g = MathHelper.func_76126_a(ageInTicks * rearFinSpeed) * 0.45f;
        this.BodyEndBottom.field_78796_g = (-MathHelper.func_76126_a((ageInTicks + 4) * rearFinSpeed)) * 0.45f;
    }
}
