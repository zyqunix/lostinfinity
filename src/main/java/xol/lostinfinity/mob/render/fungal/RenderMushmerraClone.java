package xol.lostinfinity.mob.render.fungal;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerraClone;
import xol.lostinfinity.mob.model.fungal.ModelMushmerraClone;
public class RenderMushmerraClone extends RenderLiving<EntityMushmerraClone> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/mushmerra.png");
    public RenderMushmerraClone(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMushmerraClone(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityMushmerraClone entity) {
        return TEXTURES;
    }
}
