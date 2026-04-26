package xol.lostinfinity.util.compatibility.jei.pickchargingtable;

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

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/pickchargingtable/PickChargingTableRecipeCategory.class */
public class PickChargingTableRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.pickchargingtable";
    private final String title = "Pick Charging Table";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/pick_charging_table.png");

    public PickChargingTableRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 24, 42, 130, 30).addPadding(6, 0, 0, 0).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.pickaxeChargingTable));
    }

    public String getUid() {
        return "lostinfinity.pickchargingtable";
    }

    public String getTitle() {
        return "Pick Charging Table";
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
        guiItemStackGroup.init(0, true, 2, 10);
        guiItemStackGroup.init(1, true, 51, 10);
        guiItemStackGroup.init(2, false, 109, 10);
        guiItemStackGroup.set(ingredients);
    }
}
