package xol.lostinfinity.client.screen;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xol.lostinfinity.mob.entity.cthulhu.EntityCthulhu;
import xol.lostinfinity.util.Reference;
public class CthulhuBossBar {
    private static final int totalNameWidth = 114;
    private static final int animationTime = 300;
    private int tick_count = 0;
    private int ticks = 0;
    Minecraft mc = Minecraft.func_71410_x();
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            onTickRender();
            this.tick_count++;
            if (this.tick_count == 100) {
                this.tick_count = 0;
            }
        }
    }
    private void onTickRender() {
        int width;
        if (this.mc.field_71462_r == null) {
            this.ticks++;
            EntityCthulhu cthulhu = null;
            Iterator it = this.mc.field_71441_e.field_72996_f.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EntityCthulhu entityCthulhu = (Entity) it.next();
                if (entityCthulhu instanceof EntityCthulhu) {
                    cthulhu = entityCthulhu;
                    break;
                }
            }
            if (cthulhu == null) {
                return;
            }
            GlStateManager.func_179124_c(1.0f, 1.0f, 1.0f);
            GuiIngame gig = this.mc.field_71456_v;
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            this.mc.func_110434_K().func_110577_a(new ResourceLocation(Reference.MODID, "textures/gui/boss_hp_bar.png"));
            int max = cthulhu.numberOfLives();
            int cur = cthulhu.remainingLives();
            int parts = (int) (((double) (cur * 160)) / ((double) max));
            int x = (scaledresolution.func_78326_a() / 2) - 84;
            int y = (int) (((double) scaledresolution.func_78328_b()) / 50.0d);
            gig.func_73729_b(x, y, 0, 0, 167, 25);
            gig.func_73729_b(x + 4, y + 16, 0, 28, parts, 5);
            int offsetX = 0;
            int mod = this.ticks % animationTime;
            if (mod < 150) {
                width = (int) (114.0d * (((double) (mod * 2)) / 300.0d));
            } else {
                int k = mod - 150;
                width = (int) (114.0d * (((double) (animationTime - (k * 2))) / 300.0d));
                offsetX = (int) (114.0d * (((double) (k * 2)) / 300.0d));
            }
            gig.func_73729_b(x + 27 + offsetX, y + 2, offsetX, 36, width, 9);
        }
    }
}
