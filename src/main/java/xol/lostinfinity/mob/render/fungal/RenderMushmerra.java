package xol.lostinfinity.mob.render.fungal;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerra;
import xol.lostinfinity.mob.model.fungal.ModelMushmerra;
public class RenderMushmerra extends RenderLiving<EntityMushmerra> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/mushmerra.png");
    public RenderMushmerra(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMushmerra(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityMushmerra entity) {
        return TEXTURES;
    }
}
