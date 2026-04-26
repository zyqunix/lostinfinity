package xol.lostinfinity.util.compatibility.jei.trades.zirconia.malachite;
import javax.annotation.Nullable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.util.Reference;
public class MalachiteTradeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.zirconia_malachite";
    private final String title = "Red Light Trades";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/jei_traders.png");
    public MalachiteTradeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 32, 30, 114, 34).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.screenRedlight));
    }
    public String getUid() {
        return "lostinfinity.zirconia_malachite";
    }
    public String getTitle() {
        return "Red Light Trades";
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
        guiItemStackGroup.init(1, true, 4, 8);
        guiItemStackGroup.init(0, true, 32, 8);
        guiItemStackGroup.init(2, false, 90, 8);
        guiItemStackGroup.set(ingredients);
    }
}
