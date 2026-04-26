package xol.lostinfinity.mob.render.minion;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityAuraOfAllegiance;
import xol.lostinfinity.mob.model.minion.ModelAuraOfAllegiance;
public class RenderAuraOfAllegiance extends RenderMinion<EntityAuraOfAllegiance> {
    public static final ResourceLocation[] STONES = {new ResourceLocation("lostinfinity:textures/stone/cube_resolve.png"), new ResourceLocation("lostinfinity:textures/stone/cube_dread.png"), new ResourceLocation("lostinfinity:textures/stone/cube_ingenuity.png"), new ResourceLocation("lostinfinity:textures/stone/cube_aspiration.png"), new ResourceLocation("lostinfinity:textures/stone/cube_misdirection.png"), new ResourceLocation("lostinfinity:textures/stone/cube_ambition.png")};
    public RenderAuraOfAllegiance(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAuraOfAllegiance(), 0.0f);
    }
    public void func_77041_b(EntityAuraOfAllegiance entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.25f, 0.25f, 0.25f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityAuraOfAllegiance entity) {
        return STONES[0];
    }
}
