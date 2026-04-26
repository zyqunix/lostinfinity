package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityPlayerLimb;
import xol.lostinfinity.mob.model.ModelPlayerLimb;
public class RenderPlayerLimb extends RenderBiped<EntityPlayerLimb> {
    public RenderPlayerLimb(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelPlayerLimb(1.0f, true), 0.0f);
    }
    public ResourceLocation func_110775_a(EntityPlayerLimb entity) {
        return entity.getSkin();
    }
}
