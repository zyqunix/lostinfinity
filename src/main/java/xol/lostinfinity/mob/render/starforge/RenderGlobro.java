package xol.lostinfinity.mob.render.starforge;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.starforge.EntityGlobro;
import xol.lostinfinity.mob.model.starforge.ModelGlobro;
public class RenderGlobro extends RenderLiving<EntityGlobro> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/starforge/globro.png");
    public RenderGlobro(RenderManager manager) {
        super(manager, new ModelGlobro(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityGlobro entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityGlobro entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
