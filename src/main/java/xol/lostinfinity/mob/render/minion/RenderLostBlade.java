package xol.lostinfinity.mob.render.minion;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityLostBlade;
import xol.lostinfinity.mob.model.minion.ModelLostBlade;
public class RenderLostBlade extends RenderMinion<EntityLostBlade> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/lost_blade.png");
    public RenderLostBlade(RenderManager renderManager) {
        super(renderManager, new ModelLostBlade(), 0.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityLostBlade entity) {
        return TEXTURES;
    }
    public void func_77041_b(EntityLostBlade entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179139_a(0.25d, 0.25d, 0.25d);
    }
}
