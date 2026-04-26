package xol.lostinfinity.mob.render.mount;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.mount.EntityXScreacher;
import xol.lostinfinity.mob.model.ModelXScreacher;
public class RenderXScreacher extends RenderLiving<EntityXScreacher> {
    private static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/xscreacher.png");
    public RenderXScreacher(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelXScreacher(), 0.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityXScreacher entity) {
        return TEXTURES;
    }
    public void func_77041_b(EntityXScreacher entitylivingbaseIn, float partialTickTime) {
        GlStateManager.func_179152_a(2.5f, 2.5f, 2.5f);
        if (Minecraft.func_71410_x().field_71474_y.field_74320_O == 0 && Minecraft.func_71410_x().field_71439_g.func_184208_bv() == entitylivingbaseIn) {
            GlStateManager.func_179152_a(0.5f, 0.4f, 0.4f);
        } else {
            GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        }
    }
}
