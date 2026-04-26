package xol.lostinfinity.mob.model.sea;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/sea/ModelDoublerang.class */
public class ModelDoublerang extends ModelBase {
    private final ModelRenderer BodyFront;
    private final ModelRenderer HeadFinTop;
    private final ModelRenderer HeadFinBottom;
    private final ModelRenderer BodyMiddle;
    private final ModelRenderer BodyBack;
    private final ModelRenderer BackTop1;
    private final ModelRenderer cube_r1;
    private final ModelRenderer BackTop2;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer BackBot1;
    private final ModelRenderer cube_r4;
    private final ModelRenderer BackBot2;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;

    public ModelDoublerang() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.BodyFront = new ModelRenderer(this);
        this.BodyFront.func_78793_a(0.0f, 7.0f, -4.0f);
        this.BodyFront.field_78804_l.add(new ModelBox(this.BodyFront, 0, 0, -2.0f, -5.0f, -4.0f, 4, 10, 8, 0.0f, false));
        this.HeadFinTop = new ModelRenderer(this);
        this.HeadFinTop.func_78793_a(-0.5f, -4.0f, -0.5f);
        this.BodyFront.func_78792_a(this.HeadFinTop);
        setRotationAngle(this.HeadFinTop, -0.4363f, 0.0f, 0.0f);
        this.HeadFinTop.field_78804_l.add(new ModelBox(this.HeadFinTop, 52, 24, -0.5f, -16.0f, -2.5f, 1, 16, 5, 0.0f, false));
        this.HeadFinBottom = new ModelRenderer(this);
        this.HeadFinBottom.func_78793_a(-0.5f, 4.0f, -0.5f);
        this.BodyFront.func_78792_a(this.HeadFinBottom);
        setRotationAngle(this.HeadFinBottom, -2.7053f, 0.0f, 0.0f);
        this.HeadFinBottom.field_78804_l.add(new ModelBox(this.HeadFinBottom, 52, 24, -0.5f, -16.0f, -2.5f, 1, 16, 5, 0.0f, false));
        this.BodyMiddle = new ModelRenderer(this);
        this.BodyMiddle.func_78793_a(0.0f, 0.0f, 4.0f);
        this.BodyFront.func_78792_a(this.BodyMiddle);
        this.BodyMiddle.field_78804_l.add(new ModelBox(this.BodyMiddle, 24, 6, -2.0f, -4.0f, 0.0f, 4, 8, 12, 0.0f, false));
        this.BodyBack = new ModelRenderer(this);
        this.BodyBack.func_78793_a(0.0f, -1.0f, 12.0f);
        this.BodyMiddle.func_78792_a(this.BodyBack);
        this.BodyBack.field_78804_l.add(new ModelBox(this.BodyBack, 19, 26, -3.0f, -6.0f, 0.0f, 6, 12, 8, 0.0f, false));
        this.BackTop1 = new ModelRenderer(this);
        this.BackTop1.func_78793_a(0.0f, -6.0f, 4.0f);
        this.BodyBack.func_78792_a(this.BackTop1);
        this.cube_r1 = new ModelRenderer(this);
        this.cube_r1.func_78793_a(0.0f, -6.0f, 2.0f);
        this.BackTop1.func_78792_a(this.cube_r1);
        setRotationAngle(this.cube_r1, 0.1745f, 0.0f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 18, -2.5f, -3.0f, -6.0f, 5, 10, 6, 0.0f, false));
        this.BackTop2 = new ModelRenderer(this);
        this.BackTop2.func_78793_a(0.0f, -8.0f, -2.0f);
        this.BackTop1.func_78792_a(this.BackTop2);
        this.cube_r2 = new ModelRenderer(this);
        this.cube_r2.func_78793_a(0.0f, 2.0f, 4.0f);
        this.BackTop2.func_78792_a(this.cube_r2);
        setRotationAngle(this.cube_r2, 0.1745f, 0.0f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 37, 34, -1.0f, -11.0f, -4.0f, 1, 18, 12, 0.0f, false));
        this.cube_r3 = new ModelRenderer(this);
        this.cube_r3.func_78793_a(0.0f, 2.0f, 4.0f);
        this.BackTop2.func_78792_a(this.cube_r3);
        setRotationAngle(this.cube_r3, 0.5236f, 0.0f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 44, 1, -2.0f, -12.5f, -5.0f, 4, 10, 6, 0.0f, false));
        this.BackBot1 = new ModelRenderer(this);
        this.BackBot1.func_78793_a(0.0f, 6.0f, 4.0f);
        this.BodyBack.func_78792_a(this.BackBot1);
        this.cube_r4 = new ModelRenderer(this);
        this.cube_r4.func_78793_a(0.0f, -18.0f, 2.0f);
        this.BackBot1.func_78792_a(this.cube_r4);
        setRotationAngle(this.cube_r4, -0.1745f, 0.0f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 0, 18, -2.5f, 17.0f, -2.0f, 5, 10, 6, 0.0f, false));
        this.BackBot2 = new ModelRenderer(this);
        this.BackBot2.func_78793_a(0.0f, 8.0f, -2.0f);
        this.BackBot1.func_78792_a(this.BackBot2);
        this.cube_r5 = new ModelRenderer(this);
        this.cube_r5.func_78793_a(0.0f, -26.0f, 4.0f);
        this.BackBot2.func_78792_a(this.cube_r5);
        setRotationAngle(this.cube_r5, -0.5236f, 0.0f, 0.0f);
        this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 44, 1, -2.0f, 23.5f, 7.0f, 4, 10, 6, 0.0f, false));
        this.cube_r6 = new ModelRenderer(this);
        this.cube_r6.func_78793_a(0.0f, -26.0f, 4.0f);
        this.BackBot2.func_78792_a(this.cube_r6);
        setRotationAngle(this.cube_r6, -0.1745f, 0.0f, 0.0f);
        this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 0, 34, -1.0f, 17.0f, 1.0f, 1, 18, 12, 0.0f, false));
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
        float tailSpeed = 0.2f;
        float tendrilSpeed = 0.1f;
        if (limbSwing > 0.0f) {
            tailSpeed = 0.2f + (limbSwing * 2.0E-4f);
            tendrilSpeed = 0.1f + (limbSwing * 2.0E-4f);
        }
        this.BodyBack.field_78796_g = MathHelper.func_76126_a(ageInTicks * tailSpeed) * 0.35f;
        this.BodyMiddle.field_78796_g = (-MathHelper.func_76126_a(ageInTicks * tailSpeed)) * 0.35f;
        this.BackTop1.field_78795_f = MathHelper.func_76126_a(ageInTicks * tendrilSpeed) * 0.6f;
        this.BackBot1.field_78795_f = (-MathHelper.func_76126_a((ageInTicks + 4) * tendrilSpeed)) * 0.6f;
        this.BackTop2.field_78795_f = MathHelper.func_76126_a(ageInTicks * tendrilSpeed) * 0.29f;
        this.BackBot2.field_78795_f = (-MathHelper.func_76126_a((ageInTicks + 4) * tendrilSpeed)) * 0.29f;
        this.HeadFinTop.field_78796_g = MathHelper.func_76126_a(ageInTicks * 0.35f) * 0.35f;
        this.HeadFinBottom.field_78796_g = MathHelper.func_76126_a((ageInTicks + 4) * 0.35f) * 0.35f;
        this.HeadFinTop.field_78808_h = MathHelper.func_76126_a(ageInTicks * 0.15f) * 0.2f;
        this.HeadFinBottom.field_78808_h = MathHelper.func_76126_a((ageInTicks + 4) * 0.15f) * 0.2f;
    }
}
