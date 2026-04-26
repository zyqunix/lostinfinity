package xol.lostinfinity.mob.render.minion.andromeda;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.andromeda.EntityAndromedaSegment;
import xol.lostinfinity.mob.model.minion.ModelAndromeda;
import xol.lostinfinity.mob.render.minion.RenderMinion;
public class RenderAndromedaSegment extends RenderMinion<EntityAndromedaSegment> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/entity/andromeda.png");
    public RenderAndromedaSegment(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAndromeda(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityAndromedaSegment entity) {
        return TEXTURE;
    }
    public void func_77041_b(EntityAndromedaSegment entitylivingbaseIn, float partialTickTime) {
        double scale = entitylivingbaseIn.func_70603_bj();
        GlStateManager.func_179139_a(scale, scale, scale);
    }
}
