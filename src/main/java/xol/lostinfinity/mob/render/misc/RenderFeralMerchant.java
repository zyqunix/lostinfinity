package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityFeralMerchant;
import xol.lostinfinity.mob.model.ModelFeralMerchant;
public class RenderFeralMerchant extends RenderLiving<EntityFeralMerchant> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/feralmerchant.png");
    public RenderFeralMerchant(RenderManager manager) {
        super(manager, new ModelFeralMerchant(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityFeralMerchant entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityFeralMerchant entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
