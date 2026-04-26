package xol.lostinfinity.mob.model.deviant;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/model/deviant/ModelDeviantEvokerFangs.class */
@SideOnly(Side.CLIENT)
public class ModelDeviantEvokerFangs extends ModelBase {
    private final ModelRenderer base = new ModelRenderer(this, 0, 0);
    private final ModelRenderer upperJaw;
    private final ModelRenderer lowerJaw;

    public ModelDeviantEvokerFangs() {
        this.base.func_78793_a(-5.0f, 22.0f, -5.0f);
        this.base.func_78789_a(0.0f, 0.0f, 0.0f, 10, 12, 10);
        this.upperJaw = new ModelRenderer(this, 40, 0);
        this.upperJaw.func_78793_a(1.5f, 22.0f, -4.0f);
        this.upperJaw.func_78789_a(0.0f, 0.0f, 0.0f, 4, 14, 8);
        this.lowerJaw = new ModelRenderer(this, 40, 0);
        this.lowerJaw.func_78793_a(-1.5f, 22.0f, 4.0f);
        this.lowerJaw.func_78789_a(0.0f, 0.0f, 0.0f, 4, 14, 8);
    }

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        float f = limbSwing * 2.0f;
        if (f > 1.0f) {
            f = 1.0f;
        }
        float f2 = 1.0f - ((f * f) * f);
        this.upperJaw.field_78808_h = 3.1415927f - ((f2 * 0.35f) * 3.1415927f);
        this.lowerJaw.field_78808_h = 3.1415927f + (f2 * 0.35f * 3.1415927f);
        this.lowerJaw.field_78796_g = 3.1415927f;
        float f1 = (limbSwing + MathHelper.func_76126_a(limbSwing * 2.7f)) * 0.6f * 12.0f;
        this.upperJaw.field_78797_d = 24.0f - f1;
        this.lowerJaw.field_78797_d = this.upperJaw.field_78797_d;
        this.base.field_78797_d = this.upperJaw.field_78797_d;
        this.base.func_78785_a(scale);
        this.upperJaw.func_78785_a(scale);
        this.lowerJaw.func_78785_a(scale);
    }
}
