package xol.lostinfinity.mob.render.cthulhu;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuPart;
public class RenderCthulhuPart extends Render<EntityCthulhuPart> {
    public RenderCthulhuPart(RenderManager renderManager) {
        super(renderManager);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityCthulhuPart entity) {
        return null;
    }
}
