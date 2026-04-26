package xol.lostinfinity.mob.render.minion;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityAbyssalCrabulon;
import xol.lostinfinity.mob.model.sea.ModelCrabulon;
public class RenderAbyssalCrabulon extends RenderMinion<EntityAbyssalCrabulon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/abyssal_crabulon.png");
    public RenderAbyssalCrabulon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCrabulon(), 0.0f);
    }
    public void func_77041_b(EntityAbyssalCrabulon entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.3f, 0.3f, 0.3f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityAbyssalCrabulon entity) {
        return TEXTURES;
    }
}
