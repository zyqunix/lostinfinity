package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityUnstableMerchant;
import xol.lostinfinity.mob.model.ModelUnstableMerchant;
public class RenderUnstableMerchant extends RenderLiving<EntityUnstableMerchant> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/unstablemerchant.png");
    public RenderUnstableMerchant(RenderManager manager) {
        super(manager, new ModelUnstableMerchant(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityUnstableMerchant entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityUnstableMerchant entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
