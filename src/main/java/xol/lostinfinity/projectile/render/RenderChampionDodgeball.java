package xol.lostinfinity.projectile.render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.projectile.entity.EntityChampionDodgeball;
public class RenderChampionDodgeball extends RenderInfinityThrowable {
    public static final ResourceLocation TEXTURE = new ResourceLocation("lostinfinity:textures/projectiles/champion_dodgeball.png");
    public static final ResourceLocation TEXTURE_RAPID = new ResourceLocation("lostinfinity:textures/projectiles/champion_dodgeball_rapid.png");
    public static final ResourceLocation TEXTURE_SUPER = new ResourceLocation("lostinfinity:textures/projectiles/champion_dodgeball_super.png");
    public RenderChampionDodgeball(RenderManager renderManager, float scaleFactor, ResourceLocation texture) {
        super(renderManager, scaleFactor, texture);
    }
    public RenderChampionDodgeball(RenderManager renderManager, ResourceLocation texture) {
        super(renderManager, texture);
    }
    @Override // xol.lostinfinity.projectile.render.RenderInfinityThrowable
    public ResourceLocation func_110775_a(EntityThrowable entity) {
        if (entity instanceof EntityChampionDodgeball) {
            EntityChampionDodgeball dodgeBall = (EntityChampionDodgeball) entity;
            int type = dodgeBall.getType();
            switch (type) {
                case 0:
                    return TEXTURE;
                case 1:
                    return TEXTURE_RAPID;
                case 2:
                    return TEXTURE_SUPER;
            }
        }
        return TEXTURE;
    }
}
