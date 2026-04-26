package xol.lostinfinity.util.compatibility.jei.nicroniuminfuser;

import javax.annotation.Nullable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.util.Reference;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/compatibility/jei/nicroniuminfuser/NicroniumInfuserRecipeCategory.class */
public class NicroniumInfuserRecipeCategory implements IRecipeCategory {
    private final IDrawable background;
    private final IDrawable icon;
    private final String uid = "lostinfinity.nicroniuminfuser";
    private final String title = "Nicronium Infuser";
    protected final IDrawableAnimated arrow;
    protected final IDrawableAnimated fuel;
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/nicronium_infuser.png");

    public NicroniumInfuserRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 5, 14, 120, 65).build();
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockInit.nicroniumInfuser));
        this.arrow = guiHelper.drawableBuilder(new ResourceLocation("jei", "textures/gui/gui_vanilla.png"), 82, 128, 25, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        this.fuel = guiHelper.drawableBuilder(BACKGROUND_TEXTURE, 176, 1, 16, 13).buildAnimated(200, IDrawableAnimated.StartDirection.TOP, true);
    }

    public String getUid() {
        return "lostinfinity.nicroniuminfuser";
    }

    public String getTitle() {
        return "Nicronium Infuser";
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
        guiItemStackGroup.init(1, true, 11, 42);
        guiItemStackGroup.init(2, false, 92, 24);
        guiItemStackGroup.set(ingredients);
    }

    public void drawExtras(Minecraft minecraft) {
        this.arrow.draw(minecraft, 62, 25);
        this.fuel.draw(minecraft, 12, 26);
    }
}
