package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityAspect;
import xol.lostinfinity.mob.model.labyrinth.ModelAspect;
public class RenderAspect extends RenderLiving<EntityAspect> {
    public static final ResourceLocation TEXTURES_RED = new ResourceLocation("lostinfinity:textures/entity/labyrinth/aspect_red.png");
    public static final ResourceLocation TEXTURES_BLUE = new ResourceLocation("lostinfinity:textures/entity/labyrinth/aspect_blue.png");
    public static final ResourceLocation TEXTURES_YELLOW = new ResourceLocation("lostinfinity:textures/entity/labyrinth/aspect_yellow.png");
    public static final ResourceLocation TEXTURES_PURPLE = new ResourceLocation("lostinfinity:textures/entity/labyrinth/aspect_purple.png");
    public RenderAspect(RenderManager manager) {
        super(manager, new ModelAspect(), 0.5f);
    }
    public void func_77041_b(EntityAspect entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(1.5f, 1.5f, 1.5f);
    }
    public ResourceLocation func_110775_a(EntityAspect entity) {
        switch (entity.getStyle()) {
            case 0:
                return TEXTURES_BLUE;
            case 1:
                return TEXTURES_YELLOW;
            case 2:
                return TEXTURES_PURPLE;
            default:
                return TEXTURES_RED;
        }
    }
    public void func_77043_a(EntityAspect entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
