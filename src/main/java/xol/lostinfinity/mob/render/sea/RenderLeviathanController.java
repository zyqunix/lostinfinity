package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanController;
public class RenderLeviathanController extends Render<EntityLeviathanController> {
    public RenderLeviathanController(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityLeviathanController entity) {
        return null;
    }
}
