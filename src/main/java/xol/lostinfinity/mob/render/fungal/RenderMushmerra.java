package xol.lostinfinity.mob.render.fungal;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerra;
import xol.lostinfinity.mob.model.fungal.ModelMushmerra;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/fungal/RenderMushmerra.class */
public class RenderMushmerra extends RenderLiving<EntityMushmerra> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/mushmerra.png");

    public RenderMushmerra(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMushmerra(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityMushmerra entity) {
        return TEXTURES;
    }
}
