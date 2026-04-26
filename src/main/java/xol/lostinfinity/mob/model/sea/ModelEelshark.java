package xol.lostinfinity.mob.model.sea;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelEelshark extends ModelBase {
    private final ModelRenderer BodyFront;
    private final ModelRenderer BodyMiddle;
    private final ModelRenderer FinLeft;
    private final ModelRenderer FinRight;
    private final ModelRenderer BodyEnd;
    private final ModelRenderer Tail;
    private final ModelRenderer TailfinLeft;
    private final ModelRenderer TailfinRight;
    public ModelEelshark() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.BodyFront = new ModelRenderer(this);
        this.BodyFront.func_78793_a(0.0f, 18.0f, -7.0f);
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 12, -6.0f, 4.0f, -1.0f, 12, 2, 5, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 35, 4, 4.0f, -4.0f, -1.0f, 2, 8, 5, 0.0f, false));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 35, 4, -6.0f, -4.0f, -1.0f, 2, 8, 5, 0.0f, true));
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 4, -6.0f, -6.0f, -1.0f, 12, 2, 5, 0.0f, false));
        this.BodyMiddle = new ModelRenderer(this);
        this.BodyMiddle.func_78793_a(0.0f, -1.0f, 4.0f);
        this.BodyFront.func_78792_a(this.BodyMiddle);
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 58, 29, -6.0f, -5.0f, 0.0f, 12, 12, 23, 0.0f, false));
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
        this.BodyEnd.field_78804_l.add(new ModelBox(this.BodyEnd, 51, 0, -1.0f, -9.0f, 1.0f, 1, 4, 14, 0.0f, false));
        this.Tail = new ModelRenderer(this);
        this.Tail.func_78793_a(-3.0f, -2.0f, 16.0f);
        this.BodyEnd.func_78792_a(this.Tail);
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 0, 22, 1.0f, -5.0f, 0.0f, 4, 10, 8, 0.0f, false));
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 60, 31, 1.0f, -13.0f, 2.0f, 4, 8, 6, 0.0f, false));
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 38, 23, 1.0f, 5.0f, 3.0f, 4, 5, 5, 0.0f, false));
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 24, 20, 1.0f, 10.0f, 5.0f, 4, 5, 3, 0.0f, false));
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 25, 30, 1.0f, -19.0f, 4.0f, 4, 6, 4, 0.0f, false));
        this.TailfinLeft = new ModelRenderer(this);
        this.TailfinLeft.func_78793_a(5.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinLeft);
        this.TailfinLeft.field_78804_l.add(new ModelBox(this.TailfinLeft, 0, 41, 0.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, false));
        this.TailfinRight = new ModelRenderer(this);
        this.TailfinRight.func_78793_a(-5.0f, -0.5f, 8.0f);
        this.BodyEnd.func_78792_a(this.TailfinRight);
        this.TailfinRight.field_78804_l.add(new ModelBox(this.TailfinRight, 0, 41, -10.0f, -0.5f, -4.0f, 10, 1, 8, 0.0f, true));
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
        float bodySpeed = 0.1f;
        float bodyRange = 0.1f;
        if (limbSwingAmount > 0.0f) {
            bodySpeed = 0.28f;
            bodyRange = 0.2f;
        }
        this.BodyMiddle.field_78796_g = MathHelper.func_76134_b((ageInTicks + 2) * bodySpeed) * bodyRange;
        this.BodyEnd.field_78796_g = MathHelper.func_76126_a(ageInTicks * bodySpeed) * bodyRange;
        this.Tail.field_78796_g = MathHelper.func_76126_a(ageInTicks * bodySpeed) * bodyRange;
        if (entityIn.field_70181_x > 0.0d) {
            if (this.BodyFront.field_78795_f > -0.4f) {
                this.BodyFront.field_78795_f -= 0.02f;
                return;
            }
            return;
        }
        if (entityIn.field_70181_x < 0.0d) {
            if (this.BodyFront.field_78795_f < 0.4f) {
                this.BodyFront.field_78795_f += 0.02f;
                return;
            }
            return;
        }
        if (this.BodyFront.field_78795_f > 0.0f) {
            this.BodyFront.field_78795_f -= 0.02f;
        } else if (this.BodyFront.field_78795_f < 0.0f) {
            this.BodyFront.field_78795_f += 0.02f;
        }
    }
}
