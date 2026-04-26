package xol.lostinfinity.mob.render.cthulhu;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhuTurret;
import xol.lostinfinity.mob.model.boss.ModelRestorationCrystal;
import xol.lostinfinity.util.Reference;
public class RenderCthulhuTurret extends RenderLiving<EntityCthulhuTurret> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/cthulhu/turret.png");
    public RenderCthulhuTurret(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRestorationCrystal(), 2.5f);
    }
    @Nullable
    public ResourceLocation func_110775_a(EntityCthulhuTurret entity) {
        return TEXTURE;
    }
}
