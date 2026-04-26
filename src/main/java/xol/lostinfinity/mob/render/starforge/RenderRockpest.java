package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityRockpest;
import xol.lostinfinity.mob.model.starforge.ModelRockpest;
public class RenderRockpest extends RenderLiving<EntityRockpest> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/rockpest.png");
    public RenderRockpest(RenderManager manager) {
        super(manager, new ModelRockpest(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityRockpest entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityRockpest entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
