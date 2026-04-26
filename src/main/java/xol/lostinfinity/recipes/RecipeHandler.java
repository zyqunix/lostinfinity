package xol.lostinfinity.recipes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;
public class RecipeHandler {
    private static HashMap<Item, Recipe> recipes = new HashMap<>();
    private static void init() {
        ItemStack emb = new ItemStack(ItemInit.emberiumCondensed);
        ItemStack inc = new ItemStack(ItemInit.incadiumCondensed);
        ItemStack nox = new ItemStack(ItemInit.noxeriumCondensed);
        ItemStack kyl = new ItemStack(ItemInit.kylaxiumCondensed);
        ItemStack phy = new ItemStack(ItemInit.phytrosiumCondensed);
        ItemStack cry = new ItemStack(ItemInit.crystoniumCondensed);
        ItemStack det = new ItemStack(ItemInit.detheriumCondensed);
        ItemStack oly = new ItemStack(ItemInit.olysiumCondensed);
        ItemStack xer = new ItemStack(ItemInit.xeroviumCondensed);
        ItemStack ast = new ItemStack(ItemInit.astralliumCondensed);
        ItemStack vel = new ItemStack(ItemInit.velloriumCondensed);
        ItemStack hex = new ItemStack(ItemInit.hextoriumCondensed);
        ItemStack bio = new ItemStack(ItemInit.biosynthiumCondensed);
        Arrays.stream(new Recipe[]{new Recipe(ArmorInit.vampyreonSet.helmet, inc, inc, emb, ItemStack.field_190927_a, emb, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.vampyreonSet.chestplate, inc, inc, emb, nox, emb, emb, emb), new Recipe(ArmorInit.vampyreonSet.leggings, emb, emb, emb, ItemStack.field_190927_a, emb, emb, emb), new Recipe(ArmorInit.vampyreonSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, emb, ItemStack.field_190927_a, emb, nox, nox), new Recipe(ArmorInit.plasmythicSet.helmet, cry, cry, kyl, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.plasmythicSet.chestplate, cry, cry, kyl, phy, kyl, kyl, kyl), new Recipe(ArmorInit.plasmythicSet.leggings, kyl, kyl, cry, ItemStack.field_190927_a, cry, kyl, kyl), new Recipe(ArmorInit.plasmythicSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, kyl, phy, phy), new Recipe(ArmorInit.spectrosSet.helmet, oly, oly, det, ItemStack.field_190927_a, det, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.spectrosSet.chestplate, oly, oly, det, xer, det, det, det), new Recipe(ArmorInit.spectrosSet.leggings, det, det, det, ItemStack.field_190927_a, det, det, det), new Recipe(ArmorInit.spectrosSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, det, ItemStack.field_190927_a, det, xer, xer), new Recipe(ArmorInit.vitralitonSet.helmet, phy, phy, xer, ItemStack.field_190927_a, xer, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.vitralitonSet.chestplate, xer, xer, phy, hex, phy, xer, xer), new Recipe(ArmorInit.vitralitonSet.leggings, xer, xer, hex, ItemStack.field_190927_a, hex, xer, xer), new Recipe(ArmorInit.vitralitonSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, phy, ItemStack.field_190927_a, phy, xer, xer), new Recipe(ArmorInit.blightcystSet.helmet, vel, vel, kyl, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.blightcystSet.chestplate, vel, vel, vel, kyl, vel, hex, hex), new Recipe(ArmorInit.blightcystSet.leggings, vel, vel, hex, ItemStack.field_190927_a, vel, vel, vel), new Recipe(ArmorInit.blightcystSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, hex, ItemStack.field_190927_a, hex, vel, vel), new Recipe(ArmorInit.bionicveggitronSet.helmet, bio, bio, bio, ItemStack.field_190927_a, bio, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.bionicveggitronSet.chestplate, bio, bio, bio, bio, bio, bio, bio), new Recipe(ArmorInit.bionicveggitronSet.leggings, bio, bio, bio, ItemStack.field_190927_a, bio, bio, bio), new Recipe(ArmorInit.bionicveggitronSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, bio, ItemStack.field_190927_a, bio, bio, bio), new Recipe(ArmorInit.graviteriumSet.helmet, det, det, ast, ItemStack.field_190927_a, ast, ItemStack.field_190927_a, ItemStack.field_190927_a), new Recipe(ArmorInit.graviteriumSet.chestplate, det, det, ast, emb, ast, ast, ast), new Recipe(ArmorInit.graviteriumSet.leggings, ast, ast, det, ItemStack.field_190927_a, det, ast, ast), new Recipe(ArmorInit.graviteriumSet.boots, ItemStack.field_190927_a, ItemStack.field_190927_a, ast, ItemStack.field_190927_a, ast, emb, emb)}).forEach(recipe -> {
            recipes.put(recipe.result, recipe);
        });
    }
    public static Optional<Recipe> getRecipeFor(ItemStack... items) {
        if (recipes.isEmpty()) {
            init();
        }
        return recipes.values().stream().filter(it -> {
            return it.recipeMatches(items);
        }).findFirst();
    }
}
