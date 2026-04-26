package xol.lostinfinity.mob.model.starforge;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
public class ModelEyeSlug extends ModelBase {
    public ModelRenderer eye;
    public ModelRenderer eyesocket;
    public ModelRenderer shell;
    public ModelRenderer bottom;
    public ModelRenderer neck;
    public ModelRenderer bottom2;
    public ModelRenderer bottom3;
    public ModelEyeSlug() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.neck = new ModelRenderer(this, 40, 12);
        this.neck.func_78793_a(0.0f, 12.0f, -8.0f);
        this.neck.func_78790_a(-3.0f, 5.5f, -3.0f, 6, 6, 6, 0.0f);
        setRotateAngle(this.neck, 0.7853982f, 0.0f, 0.0f);
        this.eyesocket = new ModelRenderer(this, 0, 12);
        this.eyesocket.func_78793_a(0.0f, 12.0f, -8.0f);
        this.eyesocket.func_78790_a(-4.5f, 2.0f, -4.5f, 9, 4, 9, 0.0f);
        setRotateAngle(this.eyesocket, 0.7853982f, 0.0f, 0.0f);
        this.bottom = new ModelRenderer(this, 32, 42);
        this.bottom.func_78793_a(0.0f, 21.0f, -4.0f);
        this.bottom.func_78790_a(-5.0f, 0.0f, 0.0f, 10, 3, 18, 0.0f);
        this.bottom3 = new ModelRenderer(this, 32, 29);
        this.bottom3.func_78793_a(0.0f, 21.0f, -4.0f);
        this.bottom3.func_78790_a(1.5f, -5.0f, 14.5f, 3, 5, 3, 0.0f);
        this.eye = new ModelRenderer(this, 0, 0);
        this.eye.func_78793_a(0.0f, 12.0f, -8.0f);
        this.eye.func_78790_a(-4.0f, 0.0f, -4.0f, 8, 3, 8, 0.0f);
        setRotateAngle(this.eye, 0.7853982f, 0.0f, 0.0f);
        this.shell = new ModelRenderer(this, 0, 28);
        this.shell.func_78793_a(0.0f, 7.0f, -1.5f);
        this.shell.func_78790_a(-4.0f, 0.0f, 0.0f, 8, 14, 14, 0.0f);
        this.bottom2 = new ModelRenderer(this, 32, 29);
        this.bottom2.func_78793_a(0.0f, 21.0f, -4.0f);
        this.bottom2.func_78790_a(-4.5f, -5.0f, 14.5f, 3, 5, 3, 0.0f);
    }
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.neck.func_78785_a(f5);
        this.eyesocket.func_78785_a(f5);
        this.bottom.func_78785_a(f5);
        this.bottom3.func_78785_a(f5);
        this.eye.func_78785_a(f5);
        this.shell.func_78785_a(f5);
        this.bottom2.func_78785_a(f5);
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}
