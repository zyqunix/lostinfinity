package xol.lostinfinity.mob.render.sea;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityCrabulon;
import xol.lostinfinity.mob.model.sea.ModelCrabulon;
public class RenderCrabulon extends RenderLiving<EntityCrabulon> {
    public RenderCrabulon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCrabulon(), 0.0f);
    }
    public void func_77041_b(EntityCrabulon entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(5.0f, 5.0f, 5.0f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityCrabulon entity) {
        switch (entity.getVisualStyle()) {
            case 0:
                return new ResourceLocation("lostinfinity:textures/entity/sea/crabulon1.png");
            case 1:
                return new ResourceLocation("lostinfinity:textures/entity/sea/crabulon2.png");
            case 2:
                return new ResourceLocation("lostinfinity:textures/entity/sea/crabulon3.png");
            case 3:
                return new ResourceLocation("lostinfinity:textures/entity/sea/crabulon4.png");
            default:
                return new ResourceLocation("lostinfinity:textures/entity/sea/crabulon1.png");
        }
    }
}
