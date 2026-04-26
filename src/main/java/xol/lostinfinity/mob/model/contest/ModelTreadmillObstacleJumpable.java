package xol.lostinfinity.mob.model.contest;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/contest/ModelTreadmillObstacleJumpable.class */
public class ModelTreadmillObstacleJumpable extends ModelBase {
    private final ModelRenderer bb_main;
    private final ModelRenderer cube_r1;

    public ModelTreadmillObstacleJumpable() {
        this.field_78090_t = 128;
        this.field_78089_u = 16;
        this.bb_main = new ModelRenderer(this);
        this.bb_main.func_78793_a(0.0f, 24.0f, 0.0f);
        this.cube_r1 = new ModelRenderer(this);
        this.cube_r1.func_78793_a(0.0f, -2.0f, -4.0f);
        this.bb_main.func_78792_a(this.cube_r1);
        setRotationAngle(this.cube_r1, 0.0f, -1.5708f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -4.0f, -2.0f, -4.0f, 48, 4, 8, 0.0f, false));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bb_main.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}
