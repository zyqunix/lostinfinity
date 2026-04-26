package xol.lostinfinity.mob.render.contest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.contest.trader.EntityContraderHolodeck;
import xol.lostinfinity.mob.model.contest.ModelContestNPC;
public class RenderContraderHolodeck extends RenderLiving<EntityContraderHolodeck> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/contest_npc_trader.png");
    public RenderContraderHolodeck(RenderManager manager) {
        super(manager, new ModelContestNPC(), 1.0f);
    }
    public void func_77043_a(EntityContraderHolodeck entityLiving, float p1, float rotationYaw, float partialTicks) {
        super.func_77043_a(entityLiving, p1, rotationYaw, partialTicks);
    }
    public ResourceLocation func_110775_a(EntityContraderHolodeck entity) {
        return TEXTURES;
    }
}
