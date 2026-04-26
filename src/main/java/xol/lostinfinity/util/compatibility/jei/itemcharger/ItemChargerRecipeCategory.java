package xol.lostinfinity.util.compatibility.jei.itemcharger;
import java.awt.Color;
import javax.annotation.Nullable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.util.Reference;
public class ItemChargerRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.itemcharger";
    private final String title = "Item Charger";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/item_charger.png");
    public ItemChargerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 30, 10, 115, 65).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(ItemInit.headCollector));
    }
    public String getUid() {
        return "lostinfinity.itemcharger";
    }
    public String getTitle() {
        return "Item Charger";
    }
    public String getModName() {
        return Reference.NAME;
    }
    public IDrawable getBackground() {
        return this.background;
    }
    @Nullable
    public IDrawable getIcon() {
        return this.icon;
    }
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStackGroup = recipeLayout.getItemStacks();
        guiItemStackGroup.init(0, true, 35, 2);
        guiItemStackGroup.init(1, true, 35, 30);
        guiItemStackGroup.init(2, true, 63, 2);
        guiItemStackGroup.init(3, true, 63, 30);
        guiItemStackGroup.set(ingredients);
    }
    public void drawExtras(Minecraft minecraft) {
        minecraft.field_71466_p.func_78276_b("Cur Charge: 100%", 15, 54, Color.yellow.getRGB());
    }
}
