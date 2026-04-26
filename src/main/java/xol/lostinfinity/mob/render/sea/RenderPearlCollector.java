package xol.lostinfinity.mob.render.sea;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.sea.EntityPearlCollector;
import xol.lostinfinity.mob.model.sea.ModelTreasureHunter;
public class RenderPearlCollector extends RenderLiving<EntityPearlCollector> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/sea/treasure_hunter.png");
    public RenderPearlCollector(RenderManager manager) {
        super(manager, new ModelTreasureHunter(), 0.5f);
    }
    public ResourceLocation func_110775_a(EntityPearlCollector entity) {
        return TEXTURES;
    }
    public void func_77043_a(EntityPearlCollector entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
}
