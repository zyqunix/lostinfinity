package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityRibshark;
import xol.lostinfinity.mob.model.sea.ModelRibshark;
public class RenderRibshark extends RenderLiving<EntityRibshark> {
    public RenderRibshark(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRibshark(), 0.0f);
    }
    public void func_77041_b(EntityRibshark entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.0f, 2.0f, 2.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityRibshark entity) {
        switch (entity.getVisualStyle()) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/ribshark1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/ribshark2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/ribshark3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/ribshark4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/ribshark1.png");
        }
    }
}
