package xol.lostinfinity.mob.render.arena;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.boss.EntityArenaEvent;
import xol.lostinfinity.mob.model.boss.ModelArenaEvent;
public class RenderArenaEvent extends RenderLiving<EntityArenaEvent> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/arenaevent.png");
    private float scale;
    public RenderArenaEvent(RenderManager manager) {
        super(manager, new ModelArenaEvent(), 0.5f);
        this.scale = 3.0f;
    }
    public void func_77041_b(EntityArenaEvent entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(this.scale, this.scale, this.scale);
    }
    public ResourceLocation func_110775_a(EntityArenaEvent entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityArenaEvent entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
