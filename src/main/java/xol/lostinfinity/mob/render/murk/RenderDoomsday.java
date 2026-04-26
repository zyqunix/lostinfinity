package xol.lostinfinity.mob.render.murk;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntityDoomsday;
import xol.lostinfinity.mob.model.murk.ModelDoomsday;
public class RenderDoomsday extends RenderLiving<EntityDoomsday> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/doomsday.png");
    public RenderDoomsday(RenderManager manager) {
        super(manager, new ModelDoomsday(), 0.5f);
    }
    public void func_77041_b(EntityDoomsday entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
    }
    public ResourceLocation func_110775_a(EntityDoomsday entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityDoomsday entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
