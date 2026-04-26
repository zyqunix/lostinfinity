package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.seaserpent.EntitySeaSerpentSegment;
import xol.lostinfinity.mob.model.sea.ModelSeaSerpent;
public class RenderSeaSerpentSegment extends RenderLiving<EntitySeaSerpentSegment> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/entity/sea/sea_serpent.png");
    public RenderSeaSerpentSegment(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSeaSerpent(), 1.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntitySeaSerpentSegment entity) {
        return TEXTURE;
    }
    public void func_77041_b(EntitySeaSerpentSegment entitylivingbaseIn, float partialTickTime) {
        double scale = entitylivingbaseIn.func_70603_bj();
        GlStateManager.func_179139_a(scale, scale, scale);
    }
}
