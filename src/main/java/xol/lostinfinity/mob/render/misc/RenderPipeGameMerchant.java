package xol.lostinfinity.mob.render.misc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.misc.EntityPipeGameMerchant;
import xol.lostinfinity.mob.model.ModelDimensionalMerchant;
public class RenderPipeGameMerchant extends RenderLiving<EntityPipeGameMerchant> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/dim_merchant/dimensionalmerchant_pipegame.png");
    public RenderPipeGameMerchant(RenderManager manager) {
        super(manager, new ModelDimensionalMerchant(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityPipeGameMerchant entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityPipeGameMerchant entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
