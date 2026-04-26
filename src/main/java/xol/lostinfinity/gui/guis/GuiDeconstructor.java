package xol.lostinfinity.gui.guis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.gui.containers.ContainerDeconstructor;
import xol.lostinfinity.util.Reference;
public class GuiDeconstructor extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/infinity_deconstructor.png");
    public GuiDeconstructor(InventoryPlayer invPlayer) {
        super(new ContainerDeconstructor(invPlayer));
    }
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    }
}
