package xol.lostinfinity.util;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class PotionBasic extends Potion {
    private boolean negativeLostEffect;
    private int potionPage;
    public PotionBasic(String name, boolean isLostBadEffect, int liquidColorIn, int x, int y) {
        super(false, liquidColorIn);
        this.negativeLostEffect = false;
        this.potionPage = 0;
        this.negativeLostEffect = isLostBadEffect;
        this.potionPage = Math.floorDiv(x, 24);
        func_76390_b("effect." + name);
        func_76399_b(x - (this.potionPage * 24), y);
        setRegistryName(new ResourceLocation("lostinfinity:" + name));
    }
    public boolean negativeLostEffect() {
        return this.negativeLostEffect;
    }
    @SideOnly(Side.CLIENT)
    public boolean func_76400_d() {
        switch (this.potionPage) {
            case 0:
                Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation(Reference.MODID, "textures/gui/potion_icons.png"));
                break;
            case 1:
                Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation(Reference.MODID, "textures/gui/potion_icons2.png"));
                break;
        }
        return true;
    }
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        return ret;
    }
}
