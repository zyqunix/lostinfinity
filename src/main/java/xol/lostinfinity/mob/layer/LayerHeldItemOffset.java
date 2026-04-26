package xol.lostinfinity.mob.layer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
public class LayerHeldItemOffset extends LayerHeldItem {
    public LayerHeldItemOffset(RenderLivingBase<?> livingEntityRendererIn) {
        super(livingEntityRendererIn);
    }
    public void func_177141_a(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        boolean flag = entitylivingbaseIn.func_184591_cq() == EnumHandSide.RIGHT;
        ItemStack left = flag ? entitylivingbaseIn.func_184592_cb() : entitylivingbaseIn.func_184614_ca();
        ItemStack right = flag ? entitylivingbaseIn.func_184614_ca() : entitylivingbaseIn.func_184592_cb();
        if (left.func_190926_b() && right.func_190926_b()) {
            return;
        }
        GlStateManager.func_179094_E();
        if (this.field_177206_a.func_177087_b().field_78091_s) {
            GlStateManager.func_179109_b(0.0f, 0.75f, 0.0f);
            GlStateManager.func_179152_a(0.5f, 0.5f, 0.5f);
        }
        renderHeldItem(entitylivingbaseIn, right, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
        renderHeldItem(entitylivingbaseIn, left, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
        GlStateManager.func_179121_F();
    }
    private void renderHeldItem(EntityLivingBase p_188358_1_, ItemStack p_188358_2_, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide handSide) {
        if (!p_188358_2_.func_190926_b()) {
            GlStateManager.func_179094_E();
            if (p_188358_1_.func_70093_af()) {
                GlStateManager.func_179109_b(0.0f, 0.2f, 0.0f);
            }
            func_191361_a(handSide);
            GlStateManager.func_179114_b(-90.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.func_179114_b(180.0f, 0.0f, 1.0f, 0.0f);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.func_179109_b((flag ? -1 : 1) / 16.0f, 0.125f, -0.625f);
            offsetItem(p_188358_1_, p_188358_2_, p_188358_3_, handSide);
            Minecraft.func_71410_x().func_175597_ag().func_187462_a(p_188358_1_, p_188358_2_, p_188358_3_, flag);
            GlStateManager.func_179121_F();
        }
    }
    protected void offsetItem(EntityLivingBase entityLivingBase, ItemStack itemStack, ItemCameraTransforms.TransformType transformType, EnumHandSide handSide) {
    }
}
