package xol.lostinfinity.util.compatibility.jei.trades.zirconia.oyster;

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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/trades/zirconia/oyster/OysterTradeCategory.class */
public class OysterTradeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.zirconia_oyster";
    private final String title = "Lightbridge Trades";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/jei_traders.png");

    public OysterTradeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 32, 30, 114, 34).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.screenLightbridge));
    }

    public String getUid() {
        return "lostinfinity.zirconia_oyster";
    }

    public String getTitle() {
        return "Lightbridge Trades";
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
