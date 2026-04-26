package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.leviathan.EntityLeviathanSegment;
import xol.lostinfinity.mob.model.sea.ModelLeviathan;
public class RenderLeviathanSegment extends RenderLiving<EntityLeviathanSegment> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/entity/sea/leviathan.png");
    public RenderLeviathanSegment(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelLeviathan(), 1.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityLeviathanSegment entity) {
        return TEXTURE;
    }
    public void func_77041_b(EntityLeviathanSegment entitylivingbaseIn, float partialTickTime) {
        double scale = entitylivingbaseIn.func_70603_bj();
        GlStateManager.func_179139_a(scale, scale, scale);
    }
}
