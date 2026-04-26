package xol.lostinfinity.mob.render.contest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.controller.EntityControllerRedlight;
import xol.lostinfinity.mob.model.contest.ModelGrandmaster;
public class RenderControllerRedlight extends RenderLiving<EntityControllerRedlight> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/grandmaster.png");
    public RenderControllerRedlight(RenderManager manager) {
        super(manager, new ModelGrandmaster(), 1.0f);
    }
    public void func_77041_b(EntityControllerRedlight entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(4.0f, 4.0f, 4.0f);
    }
    public void func_77043_a(EntityControllerRedlight entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
    public ResourceLocation func_110775_a(EntityControllerRedlight entity) {
        return TEXTURES;
    }
}
