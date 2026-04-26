package xol.lostinfinity.gui.guis;
import java.util.AbstractMap;
import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.block.tileentity.TileEntityGrinder;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.serverbound.PacketGrinder;
import xol.lostinfinity.gui.containers.ContainerGrinder;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.util.Reference;
public class GuiGrinder extends GuiContainer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/grinder.png");
    private final InventoryPlayer player;
    private final TileEntityGrinder tileentity;
    private static final int materialTextureX = 0;
    private static final int materialTextureY = 166;
    private static final int materialWidth = 35;
    private static final int materialHeight = 33;
    private static final int materialX = 71;
    private static final int materialY = 21;
    private static final int pointTexX = 8;
    private static final int pointTexY = 138;
    private static final int debrisTexX = 35;
    private static final int debrisTexY = 166;
    ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> points;
    ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> debris;
    public GuiGrinder(InventoryPlayer player, TileEntityGrinder tileentity) {
        super(new ContainerGrinder(player, tileentity));
        this.points = new ArrayList<>();
        this.debris = new ArrayList<>();
        this.player = player;
        this.tileentity = tileentity;
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
        func_73729_b(this.field_147003_i, this.field_147009_r, materialTextureX, materialTextureX, this.field_146999_f, this.field_147000_g);
        if (this.debris.isEmpty()) {
            resetDebris();
        }
        if (this.tileentity.func_145831_w().func_180495_p(this.tileentity.func_174877_v()) == BlockInit.grinder.func_176203_a(1)) {
            func_73729_b(this.field_147003_i + materialX, this.field_147009_r + materialY, materialTextureX, 166, 35, materialHeight);
            for (AbstractMap.SimpleEntry<Integer, Integer> simpleEntry : this.points) {
            }
            for (AbstractMap.SimpleEntry<Integer, Integer> point : this.debris) {
                func_73729_b(point.getKey().intValue() - 1, point.getValue().intValue() - 1, 35, 166, 1, 1);
            }
        }
    }
    protected void func_146979_b(int mouseX, int mouseY) {
        this.field_146289_q.func_78276_b("Grinder", 70, 6, materialTextureX);
    }
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + xSize && mouseY >= y && mouseY <= y + ySize;
    }
    protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (isInRect(this.field_147003_i + materialX + 2, this.field_147009_r + materialY + 2, 30, 28, mouseX, mouseY)) {
            this.points.add(new AbstractMap.SimpleEntry<>(Integer.valueOf(mouseX), Integer.valueOf(mouseY)));
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> toRemove = new ArrayList<>();
            for (AbstractMap.SimpleEntry<Integer, Integer> debrisPoint : this.debris) {
                int xDiff = Math.abs(debrisPoint.getKey().intValue() - mouseX);
                int yDiff = Math.abs(debrisPoint.getValue().intValue() - mouseY);
                if (xDiff <= 2 && yDiff <= 2) {
                    toRemove.add(debrisPoint);
                }
            }
            this.debris.removeAll(toRemove);
            if (this.debris.isEmpty()) {
                lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketGrinder(this.tileentity.func_174877_v(), true));
            } else if (isInRect(this.field_147003_i + materialX + pointTexX, this.field_147009_r + materialY + 7, 17, 20, mouseX, mouseY)) {
                lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketGrinder(this.tileentity.func_174877_v(), false));
                this.points.clear();
                this.debris.clear();
                resetDebris();
            }
        }
    }
    private void resetDebris() {
        int leftBound = this.field_147003_i + materialX + 2;
        int topBound = this.field_147009_r + materialY + 2;
        int innerBoundLeft = this.field_147003_i + materialX + 7;
        int innerBoundTop = this.field_147009_r + materialY + 5;
        int innerBoundRight = (innerBoundLeft + 35) - 14;
        int innerBoundBottom = (innerBoundTop + materialHeight) - pointTexX;
        for (int i = leftBound; i < (leftBound + 35) - 3; i++) {
            for (int j = topBound; j < (topBound + materialHeight) - 3; j++) {
                if ((i <= innerBoundLeft || i >= innerBoundRight || j <= innerBoundTop || j >= innerBoundBottom) && this.tileentity.func_145831_w().field_73012_v.nextInt(3) == 0) {
                    this.debris.add(new AbstractMap.SimpleEntry<>(Integer.valueOf(i), Integer.valueOf(j)));
                }
            }
        }
    }
}
