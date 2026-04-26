package xol.lostinfinity.util.compatibility.jei.fabricationstation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.minecraft.item.ItemStack;
import xol.lostinfinity.init.ArmorInit;
import xol.lostinfinity.init.ItemInit;
public class FabricationStationRecipeJEI {
    private static final FabricationStationRecipeJEI INSTANCE = new FabricationStationRecipeJEI();
    private final Map<List<ItemStack>, ItemStack> fabricationStationList = new HashMap();
    public FabricationStationRecipeJEI() {
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
        List<ItemStack> vampH = new ArrayList<>(Arrays.asList(inc, inc, emb, ItemStack.field_190927_a, emb, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(vampH, new ItemStack(ArmorInit.vampyreonSet.helmet));
        List<ItemStack> vampC = new ArrayList<>(Arrays.asList(inc, inc, emb, nox, emb, emb, emb));
        addFabricationStationRecipe(vampC, new ItemStack(ArmorInit.vampyreonSet.chestplate));
        List<ItemStack> vampL = new ArrayList<>(Arrays.asList(emb, emb, emb, ItemStack.field_190927_a, emb, emb, emb));
        addFabricationStationRecipe(vampL, new ItemStack(ArmorInit.vampyreonSet.leggings));
        List<ItemStack> vampB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, emb, ItemStack.field_190927_a, emb, nox, nox));
        addFabricationStationRecipe(vampB, new ItemStack(ArmorInit.vampyreonSet.boots));
        List<ItemStack> plasH = new ArrayList<>(Arrays.asList(cry, cry, kyl, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(plasH, new ItemStack(ArmorInit.plasmythicSet.helmet));
        List<ItemStack> plasC = new ArrayList<>(Arrays.asList(cry, cry, kyl, phy, kyl, kyl, kyl));
        addFabricationStationRecipe(plasC, new ItemStack(ArmorInit.plasmythicSet.chestplate));
        List<ItemStack> plasL = new ArrayList<>(Arrays.asList(kyl, kyl, cry, ItemStack.field_190927_a, cry, kyl, kyl));
        addFabricationStationRecipe(plasL, new ItemStack(ArmorInit.plasmythicSet.leggings));
        List<ItemStack> plasB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, kyl, phy, phy));
        addFabricationStationRecipe(plasB, new ItemStack(ArmorInit.plasmythicSet.boots));
        List<ItemStack> specH = new ArrayList<>(Arrays.asList(oly, oly, det, ItemStack.field_190927_a, det, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(specH, new ItemStack(ArmorInit.spectrosSet.helmet));
        List<ItemStack> specC = new ArrayList<>(Arrays.asList(oly, oly, det, xer, det, det, det));
        addFabricationStationRecipe(specC, new ItemStack(ArmorInit.spectrosSet.chestplate));
        List<ItemStack> specL = new ArrayList<>(Arrays.asList(det, det, det, ItemStack.field_190927_a, det, det, det));
        addFabricationStationRecipe(specL, new ItemStack(ArmorInit.spectrosSet.leggings));
        List<ItemStack> specB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, det, ItemStack.field_190927_a, det, xer, xer));
        addFabricationStationRecipe(specB, new ItemStack(ArmorInit.spectrosSet.boots));
        List<ItemStack> vitrH = new ArrayList<>(Arrays.asList(phy, phy, xer, ItemStack.field_190927_a, xer, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(vitrH, new ItemStack(ArmorInit.vitralitonSet.helmet));
        List<ItemStack> vitrC = new ArrayList<>(Arrays.asList(xer, xer, phy, hex, phy, xer, xer));
        addFabricationStationRecipe(vitrC, new ItemStack(ArmorInit.vitralitonSet.chestplate));
        List<ItemStack> vitrL = new ArrayList<>(Arrays.asList(xer, xer, hex, ItemStack.field_190927_a, hex, xer, xer));
        addFabricationStationRecipe(vitrL, new ItemStack(ArmorInit.vitralitonSet.leggings));
        List<ItemStack> vitrB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, phy, ItemStack.field_190927_a, phy, xer, xer));
        addFabricationStationRecipe(vitrB, new ItemStack(ArmorInit.vitralitonSet.boots));
        List<ItemStack> bligH = new ArrayList<>(Arrays.asList(vel, vel, kyl, ItemStack.field_190927_a, kyl, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(bligH, new ItemStack(ArmorInit.blightcystSet.helmet));
        List<ItemStack> bligC = new ArrayList<>(Arrays.asList(vel, vel, vel, kyl, vel, hex, hex));
        addFabricationStationRecipe(bligC, new ItemStack(ArmorInit.blightcystSet.chestplate));
        List<ItemStack> bligL = new ArrayList<>(Arrays.asList(vel, vel, hex, ItemStack.field_190927_a, vel, vel, vel));
        addFabricationStationRecipe(bligL, new ItemStack(ArmorInit.blightcystSet.leggings));
        List<ItemStack> bligB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, hex, ItemStack.field_190927_a, hex, vel, vel));
        addFabricationStationRecipe(bligB, new ItemStack(ArmorInit.blightcystSet.boots));
        List<ItemStack> veggH = new ArrayList<>(Arrays.asList(bio, bio, bio, ItemStack.field_190927_a, bio, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(veggH, new ItemStack(ArmorInit.bionicveggitronSet.helmet));
        List<ItemStack> veggC = new ArrayList<>(Arrays.asList(bio, bio, bio, bio, bio, bio, bio));
        addFabricationStationRecipe(veggC, new ItemStack(ArmorInit.bionicveggitronSet.chestplate));
        List<ItemStack> veggL = new ArrayList<>(Arrays.asList(bio, bio, bio, ItemStack.field_190927_a, bio, bio, bio));
        addFabricationStationRecipe(veggL, new ItemStack(ArmorInit.bionicveggitronSet.leggings));
        List<ItemStack> veggB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, bio, ItemStack.field_190927_a, bio, bio, bio));
        addFabricationStationRecipe(veggB, new ItemStack(ArmorInit.bionicveggitronSet.boots));
        List<ItemStack> gravH = new ArrayList<>(Arrays.asList(det, det, ast, ItemStack.field_190927_a, ast, ItemStack.field_190927_a, ItemStack.field_190927_a));
        addFabricationStationRecipe(gravH, new ItemStack(ArmorInit.graviteriumSet.helmet));
        List<ItemStack> gravC = new ArrayList<>(Arrays.asList(det, det, ast, emb, ast, ast, ast));
        addFabricationStationRecipe(gravC, new ItemStack(ArmorInit.graviteriumSet.chestplate));
        List<ItemStack> gravL = new ArrayList<>(Arrays.asList(ast, ast, det, ItemStack.field_190927_a, det, ast, ast));
        addFabricationStationRecipe(gravL, new ItemStack(ArmorInit.graviteriumSet.leggings));
        List<ItemStack> gravB = new ArrayList<>(Arrays.asList(ItemStack.field_190927_a, ItemStack.field_190927_a, ast, ItemStack.field_190927_a, ast, emb, emb));
        addFabricationStationRecipe(gravB, new ItemStack(ArmorInit.graviteriumSet.boots));
    }
    public static FabricationStationRecipeJEI getInstance() {
        return INSTANCE;
    }
    public void addFabricationStationRecipe(List<ItemStack> inputs, ItemStack result) {
        if (getFabricationStationResult(inputs) != ItemStack.field_190927_a) {
            return;
        }
        this.fabricationStationList.put(inputs, result);
    }
    public ItemStack getFabricationStationResult(List<ItemStack> inputs) {
        for (Map.Entry<List<ItemStack>, ItemStack> entry : this.fabricationStationList.entrySet()) {
            if (Objects.equals(inputs, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.field_190927_a;
    }
    public Map<List<ItemStack>, ItemStack> getFabricationStationList() {
        return this.fabricationStationList;
    }
}
