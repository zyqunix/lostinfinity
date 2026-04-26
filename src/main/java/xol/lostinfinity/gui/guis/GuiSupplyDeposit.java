package xol.lostinfinity.gui.guis;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.gui.containers.ContainerSupplyDeposit;
import xol.lostinfinity.mob.entity.misc.EntitySupplyTrader;
import xol.lostinfinity.util.Reference;
public class GuiSupplyDeposit extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/deposit_box.png");
    private final ContainerSupplyDeposit container;
    private final EntitySupplyTrader trader;
    public GuiSupplyDeposit(InventoryPlayer invPlayer) {
        super(new ContainerSupplyDeposit(invPlayer));
        this.container = (ContainerSupplyDeposit) this.field_147002_h;
        this.trader = this.container.getTrader();
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
        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("Supply Deposit", 51, 6, 0);
    }
}
