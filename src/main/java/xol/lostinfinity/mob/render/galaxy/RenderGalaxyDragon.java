package xol.lostinfinity.mob.render.galaxy;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.galaxy.EntityGalaxyDragon;
import xol.lostinfinity.mob.model.galaxy.ModelGalaxyDragon;
public class RenderGalaxyDragon extends RenderLiving<EntityGalaxyDragon> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/galaxy_dragon.png");
    public RenderGalaxyDragon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGalaxyDragon(), 1.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityGalaxyDragon entity) {
        return TEXTURES;
    }
    public void func_77041_b(EntityGalaxyDragon entitylivingbaseIn, float partialTickTime) {
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0 && Minecraft.func_71410_x().field_71439_g.func_184208_bv() == entitylivingbaseIn) {
            GlStateManager.func_179152_a(0.5f, 0.4f, 0.4f);
        } else {
            GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        }
    }
}
