package xol.lostinfinity.mob.model.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/boss/ModelWitherSkullling.class */
public class ModelWitherSkullling extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer BackConnector_r1;
    private final ModelRenderer bb_main;

    public ModelWitherSkullling() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Body = new ModelRenderer(this);
        this.Body.func_78793_a(0.0f, 5.0f, 0.0f);
        this.BackConnector_r1 = new ModelRenderer(this);
        this.BackConnector_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.BackConnector_r1);
        setRotationAngle(this.BackConnector_r1, 0.6109f, 0.0f, 0.0f);
        this.BackConnector_r1.field_78804_l.add(new ModelBox(this.BackConnector_r1, 0, 16, 1.5f, -1.0f, -0.5f, 6, 12, 1, 0.0f, true));
        this.BackConnector_r1.field_78804_l.add(new ModelBox(this.BackConnector_r1, 0, 16, -7.5f, -1.0f, -0.5f, 6, 12, 1, 0.0f, false));
        this.BackConnector_r1.field_78804_l.add(new ModelBox(this.BackConnector_r1, 17, 16, -1.5f, -1.0f, -1.5f, 3, 12, 3, 0.0f, false));
        this.bb_main = new ModelRenderer(this);
        this.bb_main.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -4.0f, -27.0f, -4.0f, 8, 8, 8, 0.0f, false));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.func_78785_a(f5);
        this.bb_main.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.Body.field_78795_f = (float) (0.10000000149011612d + (0.20000000298023224d * Math.sin(ageInTicks * 0.2f)));
    }
}
