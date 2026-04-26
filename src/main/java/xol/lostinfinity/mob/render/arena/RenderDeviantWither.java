package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityDeviantWither;
import xol.lostinfinity.mob.model.boss.ModelDeviantWither;
public class RenderDeviantWither extends RenderLiving<EntityDeviantWither> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/deviant_wither.png");
    public RenderDeviantWither(RenderManager manager) {
        super(manager, new ModelDeviantWither(), 0.5f);
    }
    public void func_77041_b(EntityDeviantWither entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }
    public ResourceLocation func_110775_a(EntityDeviantWither entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDeviantWither entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
