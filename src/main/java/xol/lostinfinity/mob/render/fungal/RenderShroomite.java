package xol.lostinfinity.mob.render.fungal;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityShroomite;
import xol.lostinfinity.mob.model.fungal.ModelShroomite;
public class RenderShroomite extends RenderLiving<EntityShroomite> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/shroomite.png");
    public RenderShroomite(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelShroomite(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityShroomite entity) {
        return TEXTURES;
    }
}
