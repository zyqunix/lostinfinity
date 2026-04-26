package xol.lostinfinity.gui.guis;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import xol.lostinfinity.gui.containers.ContainerModuleCreator;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/gui/guis/GuiModuleCreator.class */
public class GuiModuleCreator extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/module_creator.png");
    private final ContainerModuleCreator sFab;
    private final InventoryPlayer playerInventory;
    private GuiTextField nameField;

    public GuiModuleCreator(InventoryPlayer invPlayer, World worldIn, BlockPos pos, Block block) {
        super(new ContainerModuleCreator(invPlayer, worldIn, pos, block));
        this.sFab = (ContainerModuleCreator) this.field_147002_h;
        this.playerInventory = invPlayer;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        Keyboard.enableRepeatEvents(true);
        int i = (this.field_146294_l - this.field_146999_f) / 2;
        int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.nameField = new GuiTextField(0, this.field_146289_q, i + 62, j + 24, 103, 12);
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
        this.field_146289_q.func_78276_b("Module Creator", 48, 3, 4210752);
        boolean hasBeenSet = false;
        if (this.sFab.func_75139_a(1).func_75216_d() && this.sFab.status == ContainerModuleCreator.ModulatorStatus.VALID) {
            hasBeenSet = true;
            this.nameField.func_146193_g(ContainerModuleCreator.ModulatorStatus.VALID.getColor());
        }
        if (!hasBeenSet) {
            this.nameField.func_146180_a("");
            this.nameField.func_146193_g(this.sFab.status.getColor());
        }
        if (this.sFab.status != ContainerModuleCreator.ModulatorStatus.AWAITING_INPUT) {
            int i = 8453920;
            boolean flag = true;
            if (!this.sFab.func_75139_a(2).func_75216_d()) {
                flag = false;
            } else if (!this.sFab.func_75139_a(2).func_82869_a(this.playerInventory.field_70458_d)) {
                i = 16736352;
            }
            if (flag) {
                int j = (-16777216) | ((i & 16579836) >> 2) | (i & (-16777216));
                int k = (this.field_146999_f - 8) - this.field_146289_q.func_78256_a("");
                if (!this.field_146289_q.func_82883_a()) {
                    this.field_146289_q.func_78276_b("", k, 68, j);
                    this.field_146289_q.func_78276_b("", k + 1, 67, j);
                    this.field_146289_q.func_78276_b("", k + 1, 68, j);
                } else {
                    func_73734_a(k - 3, 65, this.field_146999_f - 7, 77, -16777216);
                    func_73734_a(k - 2, 66, this.field_146999_f - 8, 76, -12895429);
                }
                this.field_146289_q.func_78276_b("", k, 67, i);
            }
        }
        GlStateManager.func_179145_e();
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
        boolean shouldLightUp = (this.sFab.func_75139_a(0).func_75216_d() || this.sFab.func_75139_a(1).func_75216_d()) && this.sFab.func_75139_a(2).func_75216_d() && this.sFab.func_75139_a(3).func_75216_d() && (this.sFab.status == ContainerModuleCreator.ModulatorStatus.AWAITING_INPUT || this.sFab.status == ContainerModuleCreator.ModulatorStatus.VALID);
        func_73729_b(i + 59, j + 20, 0, this.field_147000_g + (shouldLightUp ? 0 : 16), 110, 16);
        if ((this.sFab.func_75139_a(0).func_75216_d() || this.sFab.func_75139_a(1).func_75216_d()) && this.sFab.func_75139_a(3).func_75216_d() && !this.sFab.func_75139_a(4).func_75216_d()) {
            func_73729_b(i + 97, j + 33, this.field_146999_f, 0, 27, 21);
        }
    }
}
