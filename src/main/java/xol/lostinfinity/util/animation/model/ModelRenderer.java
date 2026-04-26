package xol.lostinfinity.util.animation.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/animation/model/ModelRenderer.class */
public class ModelRenderer extends net.minecraft.client.model.ModelRenderer {
    public float defRotX;
    public float defRotY;
    public float defRotZ;
    public float scaleX;
    public float scaleY;
    public float scaleZ;
    private boolean compiled;
    private int displayList;

    public ModelRenderer(ModelBase model, String boxNameIn) {
        super(model, boxNameIn);
    }

    public ModelRenderer(ModelBase model) {
        this(model, null);
    }

    public ModelRenderer(ModelBase model, int texOffX, int texOffY) {
        this(model);
        func_78784_a(texOffX, texOffY);
    }

    @SideOnly(Side.CLIENT)
    public void func_78785_a(float scale) {
        if (this.field_78807_k || !this.field_78806_j) {
            return;
        }
        if (!this.compiled) {
            compileDisplayList(scale);
        }
        GlStateManager.func_179109_b(this.field_82906_o, this.field_82908_p, this.field_82907_q);
        if (this.field_78795_f != 0.0f || this.field_78796_g != 0.0f || this.field_78808_h != 0.0f) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(this.field_78800_c * scale, this.field_78797_d * scale, this.field_78798_e * scale);
            GlStateManager.func_179152_a(this.scaleX, this.scaleY, this.scaleZ);
            if (this.field_78808_h != 0.0f) {
                GlStateManager.func_179114_b(this.field_78808_h * 57.29578f, 0.0f, 0.0f, 1.0f);
            }
            if (this.field_78796_g != 0.0f) {
                GlStateManager.func_179114_b(this.field_78796_g * 57.29578f, 0.0f, 1.0f, 0.0f);
            }
            if (this.field_78795_f != 0.0f) {
                GlStateManager.func_179114_b(this.field_78795_f * 57.29578f, 1.0f, 0.0f, 0.0f);
            }
            GlStateManager.func_179148_o(this.displayList);
            if (this.field_78805_m != null) {
                for (net.minecraft.client.model.ModelRenderer childModel : this.field_78805_m) {
                    childModel.func_78785_a(scale);
                }
            }
            GlStateManager.func_179121_F();
        } else if (this.field_78800_c == 0.0f && this.field_78797_d == 0.0f && this.field_78798_e == 0.0f) {
            GlStateManager.func_179148_o(this.displayList);
            if (this.field_78805_m != null) {
                for (net.minecraft.client.model.ModelRenderer childModel2 : this.field_78805_m) {
                    childModel2.func_78785_a(scale);
                }
            }
        } else {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(this.field_78800_c * scale, this.field_78797_d * scale, this.field_78798_e * scale);
            GlStateManager.func_179152_a(this.scaleX, this.scaleY, this.scaleZ);
            GlStateManager.func_179148_o(this.displayList);
            if (this.field_78805_m != null) {
                for (net.minecraft.client.model.ModelRenderer childModel3 : this.field_78805_m) {
                    childModel3.func_78785_a(scale);
                }
            }
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179109_b(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
    }

    public void lockDefaultRotation() {
        this.defRotX = this.field_78795_f;
        this.defRotY = this.field_78796_g;
        this.defRotZ = this.field_78808_h;
    }

    public void reset() {
        resetPosition();
        resetRotation();
        resetScale();
    }

    public void resetPosition() {
        this.field_82906_o = 0.0f;
        this.field_82908_p = 0.0f;
        this.field_82907_q = 0.0f;
    }

    public void resetRotation() {
        this.field_78795_f = this.defRotX;
        this.field_78796_g = this.defRotY;
        this.field_78808_h = this.defRotZ;
    }

    public void resetScale() {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.scaleZ = 1.0f;
    }

    @SideOnly(Side.CLIENT)
    private void compileDisplayList(float scale) {
        this.displayList = GLAllocation.func_74526_a(1);
        GlStateManager.func_187423_f(this.displayList, 4864);
        BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
        for (ModelBox modelBox : this.field_78804_l) {
            modelBox.func_178780_a(bufferbuilder, scale);
        }
        GlStateManager.func_187415_K();
        this.compiled = true;
    }
}
