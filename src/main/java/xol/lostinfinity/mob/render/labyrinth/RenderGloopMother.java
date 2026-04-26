package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xol.lostinfinity.mob.entity.labyrinth.EntityGloopMother;
import xol.lostinfinity.mob.model.labyrinth.ModelGloopMother;
public class RenderGloopMother extends RenderLiving<EntityGloopMother> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/gloopmother.png");
    public RenderGloopMother(RenderManager manager) {
        super(manager, new ModelGloopMother(), 0.5f);
    }
    public void func_77041_b(EntityGloopMother entitylivingbaseIn, float partialTickTime) {
        float scl = 1.0f + (0.7f * MathHelper.func_76126_a(entitylivingbaseIn.field_70173_aa * 0.1f));
        GlStateManager.func_179152_a(1.0f, scl, 1.0f);
    }
    public ResourceLocation func_110775_a(EntityGloopMother entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGloopMother entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
