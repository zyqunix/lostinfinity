package xol.lostinfinity.mob.render.labyrinth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.labyrinth.EntityNat;
import xol.lostinfinity.mob.model.labyrinth.ModelNat;
public class RenderNat extends RenderLiving<EntityNat> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/labyrinth/nat.png");
    public RenderNat(RenderManager manager) {
        super(manager, new ModelNat(), 0.5f);
    }
    public void func_77041_b(EntityNat entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(0.5f, 0.5f, 0.5f);
    }
    public ResourceLocation func_110775_a(EntityNat entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityNat entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
