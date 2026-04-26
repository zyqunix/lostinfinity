package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityOctobrella;
import xol.lostinfinity.mob.model.sea.ModelOctobrella;
public class RenderOctobrella extends RenderLiving<EntityOctobrella> {
    public RenderOctobrella(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelOctobrella(), 0.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityOctobrella entity) {
        int variant = entity.getVisualStyle();
        switch (variant) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/octobrella1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/octobrella2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/octobrella3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/octobrella4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/octobrella1.png");
        }
    }
}
