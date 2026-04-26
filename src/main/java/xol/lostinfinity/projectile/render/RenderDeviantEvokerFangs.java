package xol.lostinfinity.projectile.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.mob.model.deviant.ModelDeviantEvokerFangs;
import xol.lostinfinity.projectile.entity.EntityDeviantEvokerFangs;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/projectile/render/RenderDeviantEvokerFangs.class */
@SideOnly(Side.CLIENT)
public class RenderDeviantEvokerFangs extends Render<EntityDeviantEvokerFangs> {
    private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("lostinfinity:textures/projectiles/deviantevokerfangs.png");
    private final ModelDeviantEvokerFangs model;

    public RenderDeviantEvokerFangs(RenderManager p_i47208_1_) {
        super(p_i47208_1_);
        this.model = new ModelDeviantEvokerFangs();
    }

    /* JADX INFO: renamed from: doRender, reason: merged with bridge method [inline-methods] */
    public void func_76986_a(EntityDeviantEvokerFangs entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float f = entity.getAnimationProgress(partialTicks);
        if (f != 0.0f) {
            float f1 = 2.0f;
            if (f > 0.9f) {
                f1 = (float) (((double) 2.0f) * ((1.0d - ((double) f)) / 0.10000000149011612d));
            }
            GlStateManager.func_179094_E();
            GlStateManager.func_179129_p();
            GlStateManager.func_179141_d();
            func_180548_c(entity);
            GlStateManager.func_179109_b((float) x, (float) y, (float) z);
            GlStateManager.func_179114_b(90.0f - entity.field_70177_z, 0.0f, 1.0f, 0.0f);
            GlStateManager.func_179152_a(-f1, -f1, f1);
            GlStateManager.func_179109_b(0.0f, -0.626f, 0.0f);
            this.model.func_78088_a(entity, f, 0.0f, 0.0f, entity.field_70177_z, entity.field_70125_A, 0.03125f);
            GlStateManager.func_179121_F();
            GlStateManager.func_179089_o();
            super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityDeviantEvokerFangs entity) {
        return EVOKER_ILLAGER_FANGS;
    }
}
