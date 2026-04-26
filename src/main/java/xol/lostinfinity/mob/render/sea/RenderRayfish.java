package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityRayfish;
import xol.lostinfinity.mob.model.sea.ModelRayfish;
public class RenderRayfish extends RenderLiving<EntityRayfish> {
    public RenderRayfish(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRayfish(), 0.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityRayfish entity) {
        int variant = entity.getVisualStyle();
        switch (variant) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/rayfish1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/rayfish2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/rayfish3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/rayfish4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/rayfish1.png");
        }
    }
}
