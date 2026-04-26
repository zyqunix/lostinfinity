package xol.lostinfinity.util.compatibility.jei.modulecreator;
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
public class ModuleCreatorRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.modulecreator";
    private final String title = "Module Creator";
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/module_creator.png");
    public ModuleCreatorRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 14, 10, 140, 66).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.moduleCreator));
    }
    public String getUid() {
        return "lostinfinity.modulecreator";
    }
    public String getTitle() {
        return "Module Creator";
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
        guiItemStackGroup.init(0, true, 2, 6);
        guiItemStackGroup.init(1, true, 28, 6);
        guiItemStackGroup.init(2, true, 54, 6);
        guiItemStackGroup.init(3, true, 28, 43);
        guiItemStackGroup.init(4, false, 119, 24);
        guiItemStackGroup.set(ingredients);
    }
}
