package xol.lostinfinity.gui.guis;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.gui.containers.ContainerItemCharger;
import xol.lostinfinity.util.Reference;
public class GuiItemCharger extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/item_charger.png");
    private final ContainerItemCharger charger;
    private final ItemStack chargeable;
    private GuiTextField nameField;
    public GuiItemCharger(InventoryPlayer invPlayer, World worldIn) {
        super(new ContainerItemCharger(invPlayer, worldIn, invPlayer.func_70448_g()));
        this.chargeable = invPlayer.func_70448_g();
        this.charger = (ContainerItemCharger) this.field_147002_h;
    }
    public void func_73866_w_() {
        super.func_73866_w_();
        Keyboard.enableRepeatEvents(true);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.nameField = new GuiTextField(0, this.field_146289_q, i + 45, j + 63, 103, 12);
        this.nameField.func_146193_g(-1);
        this.nameField.func_146204_h(-1);
        this.nameField.func_146185_a(false);
        this.nameField.func_146203_f(35);
    }
    public void func_146281_b() {
        super.func_146281_b();
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
        this.field_146289_q.func_78276_b("Item Charger", 55, 2, 4210752);
        switch (AnonymousClass1.$SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[this.charger.status.ordinal()]) {
            case 1:
            case 2:
            case 3:
                if (this.charger.charger.func_77942_o()) {
                    this.nameField.func_146180_a("Cur Charge: " + ((int) ((((double) this.charger.charger.func_77978_p().func_74762_e("Charge")) / ((double) this.charger.getMaxChargeForItem())) * 100.0d)) + "%");
                } else {
                    this.nameField.func_146180_a("Cur Charge: 0%");
                }
                break;
            case TileEntityFusionTable.BOARD_ROWS :
                this.nameField.func_146180_a("Fully charged.");
                break;
        }
        this.nameField.func_146193_g(this.charger.status.getColor());
        GlStateManager.func_179145_e();
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus = new int[ContainerItemCharger.ItemChargerStatus.values().length];
        static {
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ContainerItemCharger.ItemChargerStatus.AWAITING_INPUT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ContainerItemCharger.ItemChargerStatus.PARTIAL_INPUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ContainerItemCharger.ItemChargerStatus.VALID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$xol$lostinfinity$gui$containers$ContainerItemCharger$ItemChargerStatus[ContainerItemCharger.ItemChargerStatus.FULLY_CHARGED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        func_146276_q_();
        super.func_73863_a(mouseX, mouseY, partialTicks);
        func_191948_b(mouseX, mouseY);
        GlStateManager.func_179140_f();
        GlStateManager.func_179084_k();
        this.nameField.func_146194_f();
    }
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        func_73729_b(i + 33, j + 60, 0, this.field_147000_g, 110, 16);
    }
}
