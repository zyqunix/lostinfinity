package xol.lostinfinity.mob.render.mount;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.mount.EntityJetMount;
import xol.lostinfinity.mob.model.ModelRocketMount;
public class RenderJetMount extends RenderLiving<EntityJetMount> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/rocket_mount.png");
    public RenderJetMount(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRocketMount(), 1.5f);
    }
    public void func_77041_b(EntityJetMount entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityJetMount entity) {
        return TEXTURES;
    }
}
