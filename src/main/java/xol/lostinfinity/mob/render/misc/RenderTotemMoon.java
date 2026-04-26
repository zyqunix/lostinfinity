package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityTotemMoon;
import xol.lostinfinity.mob.model.ModelTotem;
public class RenderTotemMoon extends RenderLiving<EntityTotemMoon> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/totem/totem_moon.png");
    public RenderTotemMoon(RenderManager manager) {
        super(manager, new ModelTotem(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityTotemMoon entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityTotemMoon entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
