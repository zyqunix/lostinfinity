package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentController;
public class RenderSeaSerpentController extends Render<EntitySeaSerpentController> {
    public RenderSeaSerpentController(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntitySeaSerpentController entity) {
        return null;
    }
}
