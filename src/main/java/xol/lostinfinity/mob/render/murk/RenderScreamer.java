package xol.lostinfinity.mob.render.murk;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.murk.EntityScreamer;
import xol.lostinfinity.mob.model.murk.ModelScreamer;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/murk/RenderScreamer.class */
public class RenderScreamer extends RenderLiving<EntityScreamer> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/murk/screamer.png");
    public static final ResourceLocation TEXTURES2 = new ResourceLocation("lostinfinity:textures/entity/murk/screamer_flash.png");

    public RenderScreamer(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelScreamer(), 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: preRenderCallback, reason: merged with bridge method [inline-methods] */
    public void func_77041_b(EntityScreamer entity, float partialTickTime) {
        int ticks = entity.field_70173_aa;
        if (entity.field_70173_aa < 60) {
            GlStateManager.func_179152_a(ticks * 0.05f, ticks * 0.05f, ticks * 0.05f);
        } else {
            GlStateManager.func_179152_a(3.0f, 3.0f, 3.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityScreamer entity) {
        if (entity.field_70173_aa < 40) {
            if (entity.field_70173_aa % 4 < 2) {
                return TEXTURES2;
            }
            return TEXTURES;
        }
        if (entity.field_70173_aa % 40 < 5) {
            return TEXTURES2;
        }
        return TEXTURES;
    }
}
