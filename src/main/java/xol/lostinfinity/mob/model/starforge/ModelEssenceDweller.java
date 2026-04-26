package xol.lostinfinity.mob.model.starforge;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelEssenceDweller extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer Body_r1;
    private final ModelRenderer head;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftArm;
    public ModelEssenceDweller() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer(this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body_r1 = new ModelRenderer(this);
        this.Body_r1.func_78793_a(0.0f, 6.0f, 0.0f);
        this.Body.func_78792_a(this.Body_r1);
        setRotationAngle(this.Body_r1, 0.3491f, 0.0f, 0.0f);
        this.Body_r1.field_78804_l.add(new ModelBox(this.Body_r1, 16, 16, -4.0f, -6.0f, -2.0f, 8, 12, 4, 0.1f, false));
        this.head = new ModelRenderer(this);
        this.head.func_78793_a(0.0f, 1.0f, -2.0f);
        this.head.field_78804_l.add(new ModelBox(this.head, 0, 0, -4.0f, -8.0f, -2.0f, 8, 8, 6, 0.0f, false));
        this.head.field_78804_l.add(new ModelBox(this.head, 29, 0, -4.0f, -8.0f, -8.0f, 8, 8, 6, 0.0f, false));
        this.RightArm = new ModelRenderer(this);
        this.RightArm.func_78793_a(-5.0f, 3.0f, -2.0f);
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 40, 33, -3.0f, -2.0f, -2.0f, 4, 8, 4, 0.0f, false));
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 40, 24, -3.0f, 5.0f, -2.0f, 4, 4, 4, -0.25f, false));
        this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 40, 14, -3.0f, 8.0f, -2.0f, 4, 5, 4, -0.5f, false));
        this.RightLeg = new ModelRenderer(this);
        this.RightLeg.func_78793_a(-1.9f, 11.0f, 2.0f);
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f, false));
        this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 0, 33, -2.0f, 10.0f, -8.0f, 4, 2, 10, 0.1f, false));
        this.LeftLeg = new ModelRenderer(this);
        this.LeftLeg.func_78793_a(1.9f, 11.0f, 2.0f);
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 0, 33, -2.0f, 10.0f, -8.0f, 4, 2, 10, 0.1f, true));
        this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f, true));
        this.LeftArm = new ModelRenderer(this);
        this.LeftArm.func_78793_a(5.0f, 3.0f, -2.0f);
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 40, 33, -1.0f, -2.0f, -2.0f, 4, 8, 4, 0.0f, true));
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 40, 24, -1.0f, 5.0f, -2.0f, 4, 4, 4, -0.25f, true));
        this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 40, 14, -1.0f, 8.0f, -2.0f, 4, 5, 4, -0.5f, true));
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.RightArm.func_78785_a(f5);
        this.RightLeg.func_78785_a(f5);
        this.LeftLeg.func_78785_a(f5);
        this.LeftArm.func_78785_a(f5);
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.field_78796_g = netHeadYaw * 0.017453292f;
        this.head.field_78795_f = headPitch * 0.017453292f;
        this.RightLeg.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.5f;
        this.LeftLeg.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 1.4f * limbSwingAmount * 0.5f;
        this.LeftArm.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.RightArm.field_78795_f = MathHelper.func_76134_b((limbSwing * 0.6662f) + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
    }
}
