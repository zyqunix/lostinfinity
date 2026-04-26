package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntitySpectre;
import xol.lostinfinity.mob.model.ModelSpectre;
public class RenderSpectre extends RenderLiving<EntitySpectre> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/spectre.png");
    public static final ResourceLocation TEXTURES_PRIME = new ResourceLocation("lostinfinity:textures/entity/spectre_prime.png");
    public RenderSpectre(RenderManager manager) {
        super(manager, new ModelSpectre(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntitySpectre entity) {
        if (entity.isPrime()) {
            return TEXTURES_PRIME;
        }
        return TEXTURES;
    }
    public void func_77043_a(EntitySpectre entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
