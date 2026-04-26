package xol.lostinfinity.mob.render.mount;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.minion.EntityBombDrone;
import xol.lostinfinity.mob.model.minion.ModelBombDrone;
public class RenderBombDrone extends RenderLiving<EntityBombDrone> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/bomb_drone.png");
    private static final ResourceLocation TEXTURES_ALT = new ResourceLocation("lostinfinity:textures/entity/bomb_drone_alt.png");
    public RenderBombDrone(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBombDrone(), 0.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityBombDrone entity) {
        if (entity.field_70173_aa % 10 < 5) {
            return TEXTURES_ALT;
        }
        return TEXTURES;
    }
    public boolean func_177071_a(EntityBombDrone livingEntity, ICamera camera, double camX, double camY, double camZ) {
        boolean flag1 = livingEntity.func_70902_q() != Minecraft.func_71410_x().field_71439_g;
        boolean flag2 = Minecraft.func_71410_x().field_71474_y.field_74320_O != 0;
        return flag1 || flag2;
    }
}
