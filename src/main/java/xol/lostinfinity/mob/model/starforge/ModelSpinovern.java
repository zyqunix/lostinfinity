package xol.lostinfinity.mob.model.starforge;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
public class ModelSpinovern extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer wingl1;
    public ModelRenderer wingr1;
    public ModelRenderer body3;
    public ModelRenderer body2;
    public ModelRenderer body4;
    public ModelRenderer head;
    public ModelRenderer mouth;
    public ModelRenderer mouth2;
    public ModelRenderer spine;
    public ModelSpinovern() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.body3 = new ModelRenderer(this, 49, 49);
        this.body3.func_78793_a(0.0f, 12.5f, 20.5f);
        this.body3.func_78790_a(-3.0f, -3.0f, -0.5f, 6, 8, 6, 0.0f);
        setRotateAngle(this.body3, -0.7853982f, 0.0f, 0.0f);
        this.mouth2 = new ModelRenderer(this, 80, 0);
        this.mouth2.func_78793_a(0.0f, 12.0f, -3.0f);
        this.mouth2.func_78790_a(-4.5f, 0.0f, -9.0f, 9, 3, 10, 0.0f);
        setRotateAngle(this.mouth2, 0.2617994f, 0.0f, 0.0f);
        this.wingl1 = new ModelRenderer(this, 46, 17);
        this.wingl1.func_78793_a(4.5f, 9.0f, 5.0f);
        this.wingl1.func_78790_a(0.0f, 0.0f, -5.0f, 24, 7, 16, 0.0f);
        this.wingr1 = new ModelRenderer(this, 46, 17);
        this.wingr1.field_78809_i = true;
        this.wingr1.func_78793_a(-4.5f, 9.0f, 5.0f);
        this.wingr1.func_78790_a(-24.0f, 0.0f, -5.0f, 24, 7, 16, 0.0f);
        this.head = new ModelRenderer(this, 42, 0);
        this.head.func_78793_a(0.0f, 12.0f, -3.0f);
        this.head.func_78790_a(-4.0f, -5.0f, -8.5f, 8, 5, 10, 0.0f);
        setRotateAngle(this.head, 0.2617994f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer(this, 89, 51);
        this.mouth.func_78793_a(0.0f, 12.0f, -3.0f);
        this.mouth.func_78790_a(-4.5f, -2.0f, -9.0f, 9, 2, 10, 0.0f);
        setRotateAngle(this.mouth, 0.2617994f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer(this, 74, 41);
        this.body4.func_78793_a(0.0f, 12.5f, 20.5f);
        this.body4.func_78790_a(-3.0f, -3.0f, 5.5f, 6, 8, 6, 0.0f);
        setRotateAngle(this.body4, -0.7853982f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer(this, 23, 38);
        this.body2.func_78793_a(0.0f, 11.5f, 15.5f);
        this.body2.func_78790_a(-4.0f, -4.0f, 0.0f, 8, 9, 6, 0.0f);
        setRotateAngle(this.body2, -0.34906584f, 0.0f, 0.0f);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.func_78793_a(0.0f, 12.0f, -3.0f);
        this.body.func_78790_a(-5.0f, -5.0f, 0.0f, 10, 10, 20, 0.0f);
        this.spine = new ModelRenderer(this, 0, 35);
        this.spine.func_78793_a(0.0f, 12.0f, -3.0f);
        this.spine.func_78790_a(-1.0f, -13.0f, 0.0f, 1, 8, 20, 0.0f);
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body3.func_78785_a(f5);
        this.mouth2.func_78785_a(f5);
        this.wingl1.func_78785_a(f5);
        this.wingr1.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.mouth.func_78785_a(f5);
        this.body4.func_78785_a(f5);
        this.body2.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.spine.func_78785_a(f5);
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.wingl1.field_78808_h = MathHelper.func_76134_b(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
        this.wingr1.field_78808_h = -this.wingl1.field_78808_h;
        this.mouth.field_78795_f = (MathHelper.func_76134_b(ageInTicks * 0.1f) * 0.15f) + 0.45799387f;
        this.mouth2.field_78795_f = this.mouth.field_78795_f;
    }
}
