package xol.lostinfinity.util.compatibility.jei.chemistrynegametric;

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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/chemistrynegametric/ChemistryNegametricRecipeCategory.class */
public class ChemistryNegametricRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.chemistrynegametric";
    private final String title = "Chemistry - Negametric";
    private final IDrawable purpleline;
    private final IDrawable greenline;
    private final IDrawable orangeline;
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/chemistry_table.png");

    public ChemistryNegametricRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 6, 5, 160, 74).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(ItemInit.negametricIsotope));
        this.purpleline = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 0, 194, 56, 5).build();
        this.greenline = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 0, 189, 56, 5).build();
        this.orangeline = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 0, 184, 56, 5).build();
    }

    public String getUid() {
        return "lostinfinity.chemistrynegametric";
    }

    public String getTitle() {
        return "Chemistry - Negametric";
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
        guiItemStackGroup.init(0, true, 11, 6);
        guiItemStackGroup.init(1, true, 11, 28);
        guiItemStackGroup.init(2, true, 11, 50);
        guiItemStackGroup.init(3, false, 135, 29);
        guiItemStackGroup.set(ingredients);
    }

    public void drawExtras(Minecraft minecraft) {
        this.purpleline.draw(minecraft, 66, 63);
        this.orangeline.draw(minecraft, 66, 56);
        this.greenline.draw(minecraft, 66, 49);
        this.orangeline.draw(minecraft, 66, 42);
        this.orangeline.draw(minecraft, 66, 35);
        this.greenline.draw(minecraft, 66, 28);
        this.orangeline.draw(minecraft, 66, 21);
        this.greenline.draw(minecraft, 66, 14);
        this.purpleline.draw(minecraft, 66, 7);
    }
}
