package xol.lostinfinity.mob.render.fungal;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.fungal.EntityMushmerraClone;
import xol.lostinfinity.mob.model.fungal.ModelMushmerraClone;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/mob/render/fungal/RenderMushmerraClone.class */
public class RenderMushmerraClone extends RenderLiving<EntityMushmerraClone> {
    public static final ResourceLocation TEXTURES = new ResourceLocation("lostinfinity:textures/entity/fungal/mushmerra.png");

    public RenderMushmerraClone(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMushmerraClone(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    /* JADX INFO: renamed from: getEntityTexture, reason: merged with bridge method [inline-methods] */
    public ResourceLocation func_110775_a(EntityMushmerraClone entity) {
        return TEXTURES;
    }
}
