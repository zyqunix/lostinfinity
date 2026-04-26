package xol.lostinfinity.mob.render.cthulhu;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTentacle;
import xol.lostinfinity.mob.model.cthulhu.ModelCthulhuTentacle;
import xol.lostinfinity.util.Reference;
public class RenderCthulhuTentacle extends RenderLiving<EntityCthulhuTentacle> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/tentacle.png");
    public RenderCthulhuTentacle(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCthulhuTentacle(), 0.0f);
    }
    public void func_77041_b(EntityCthulhuTentacle entitylivingbaseIn, float partialTickTime) {
        float size = entitylivingbaseIn.getSize();
        if (entitylivingbaseIn.isInverted()) {
            GlStateManager.func_179109_b(0.0f, -entitylivingbaseIn.field_70131_O, 0.0f);
            GlStateManager.func_179152_a(size, -size, size);
        } else {
            GlStateManager.func_179152_a(size, size, size);
        }
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityCthulhuTentacle entity) {
        return TEXTURE;
    }
}
