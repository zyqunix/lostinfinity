package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityEelShark;
import xol.lostinfinity.mob.model.sea.ModelEelshark;
public class RenderEelShark extends RenderLiving<EntityEelShark> {
    public RenderEelShark(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelEelshark(), 0.5f);
    }
    public void func_77041_b(EntityEelShark entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityEelShark entity) {
        switch (entity.getVisualStyle()) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/eelshark1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/eelshark2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/eelshark3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/eelshark4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/eelshark1.png");
        }
    }
}
