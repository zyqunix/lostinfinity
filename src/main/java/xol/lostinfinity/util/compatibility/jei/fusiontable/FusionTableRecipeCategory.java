package xol.lostinfinity.util.compatibility.jei.fusiontable;

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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/fusiontable/FusionTableRecipeCategory.class */
public class FusionTableRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.fusiontable";
    private final String title = "Fusion Table";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/fusion_table.png");

    public FusionTableRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 16, 6, 146, 76).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.fusionTable));
    }

    public String getUid() {
        return "lostinfinity.fusiontable";
    }

    public String getTitle() {
        return "Fusion Table";
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
        guiItemStackGroup.init(0, true, 2, 3);
        guiItemStackGroup.init(1, true, 2, 23);
        guiItemStackGroup.init(2, false, 125, 8);
        guiItemStackGroup.set(ingredients);
    }
}
