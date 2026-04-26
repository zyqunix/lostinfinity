package xol.lostinfinity.block.activator;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.util.coordinates.GalaxyCoordinates;
import xol.lostinfinity.util.damagesource.DeathMessage;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockMetaMaterializer.class */
public class BlockMetaMaterializer extends BlockBasic {

    /* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/block/activator/BlockMetaMaterializer$MetaRecipe.class */
    public class MetaRecipe {
        private ArrayList<Item> ingots;
        private ArrayList<Item> organics;
        private Item result;

        private MetaRecipe(Item ingot1, Item ingot2, Item ingot3, Item organic1, Item organic2, Item organic3, Item result) {
            this.ingots = null;
            this.organics = null;
            this.result = null;
            this.ingots = new ArrayList<>();
            this.ingots.add(ingot1);
            this.ingots.add(ingot2);
            this.ingots.add(ingot3);
            this.organics = new ArrayList<>();
            this.organics.add(organic1);
            this.organics.add(organic2);
            this.organics.add(organic3);
            this.result = result;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ArrayList<Item> getIngots() {
            return this.ingots;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ArrayList<Item> getOrganics() {
            return this.organics;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Item getResult() {
            return this.result;
        }
    }

    public BlockMetaMaterializer(String name) {
        super(name);
    }

    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        MetaRecipe rec;
        if (!playerIn.func_70093_af() && !worldIn.field_72995_K && (rec = getRecipe(playerIn.func_184586_b(hand))) != null) {
            ArrayList<Item> ingots = rec.getIngots();
            ArrayList<Item> organics = rec.getOrganics();
            Item result = rec.getResult();
            if (ingots != null && organics != null && result != null) {
                AxisAlignedBB ingotBox = GalaxyCoordinates.materializerOreAABB();
                AxisAlignedBB organicsBox = GalaxyCoordinates.materializerOrganicsAABB();
                AxisAlignedBB sacrificeBox = GalaxyCoordinates.materializerSacrificeAABB();
                ArrayList<EntityItem> IngotEntities = new ArrayList<>();
                ArrayList<Item> ingotsFound = new ArrayList<>();
                for (EntityItem itemEntity : worldIn.func_72872_a(EntityItem.class, ingotBox)) {
                    Item testItem = itemEntity.func_92059_d().func_77973_b();
                    for (Item ingot : ingots) {
                        if (testItem.equals(ingot)) {
                            ingotsFound.add(testItem);
                            IngotEntities.add(itemEntity);
                        }
                    }
                }
                for (Item toRemove : ingotsFound) {
                    ingots.remove(toRemove);
                }
                ArrayList<EntityItem> OrganicEntities = new ArrayList<>();
                ArrayList<Item> organicsFound = new ArrayList<>();
                for (EntityItem itemEntity2 : worldIn.func_72872_a(EntityItem.class, organicsBox)) {
                    Item testItem2 = itemEntity2.func_92059_d().func_77973_b();
                    for (Item organic : organics) {
                        if (testItem2.equals(organic)) {
                            organicsFound.add(testItem2);
                            OrganicEntities.add(itemEntity2);
                        }
                    }
                }
                for (Item toRemove2 : organicsFound) {
                    organics.remove(toRemove2);
                }
                int count = 0;
                ArrayList<EntityLivingBase> sacrifices = new ArrayList<>();
                for (EntityLivingBase sacrifice : worldIn.func_72872_a(EntityLivingBase.class, sacrificeBox)) {
                    count++;
                    sacrifices.add(sacrifice);
                    if (sacrifice instanceof EntityPlayer) {
                        count = 3;
                    }
                }
                int minNum = 99;
                Iterator<EntityItem> it = IngotEntities.iterator();
                while (it.hasNext()) {
                    ItemStack stack = it.next().func_92059_d();
                    if (stack.func_190916_E() < minNum) {
                        minNum = stack.func_190916_E();
                    }
                }
                Iterator<EntityItem> it2 = OrganicEntities.iterator();
                while (it2.hasNext()) {
                    ItemStack stack2 = it2.next().func_92059_d();
                    if (stack2.func_190916_E() < minNum) {
                        minNum = stack2.func_190916_E();
                    }
                }
                if (ingots.isEmpty() && organics.isEmpty() && count >= 3) {
                    Iterator<EntityItem> it3 = IngotEntities.iterator();
                    while (it3.hasNext()) {
                        it3.next().func_92059_d().func_190918_g(minNum);
                    }
                    Iterator<EntityItem> it4 = OrganicEntities.iterator();
                    while (it4.hasNext()) {
                        it4.next().func_92059_d().func_190918_g(minNum);
                    }
                    for (EntityLivingBase entity : sacrifices) {
                        entity.func_70606_j(0.0f);
                        if (entity instanceof EntityPlayer) {
                            DeathMessage.broadcastDeathMessage(entity.func_184102_h(), TextFmt.Red + entity.func_70005_c_() + " was sacrificed to the Meta Materializer.");
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        EntityItem resultItem = new EntityItem(worldIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(result, minNum));
                        resultItem.field_70159_w = 0.0d;
                        resultItem.field_70181_x = 0.0d;
                        resultItem.field_70179_y = 0.0d;
                        worldIn.func_72838_d(resultItem);
                        worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MACHINE_CRAFT, SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                    return true;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    private MetaRecipe getRecipe(ItemStack heldItem) {
        if (heldItem.func_77973_b().equals(ItemInit.clovinitePowerBank)) {
            return new MetaRecipe(ItemInit.velloriumIngot, ItemInit.kylaxiumIngot, ItemInit.xeroviumIngot, ItemInit.organicPlate, ItemInit.corruptedRoot, ItemInit.giantTentacle, ItemInit.kyvoriumIngot);
        }
        if (heldItem.func_77973_b().equals(ItemInit.adhesiveFibre)) {
            return new MetaRecipe(ItemInit.olysiumIngot, ItemInit.detheriumIngot, ItemInit.phytrosiumIngot, ItemInit.gloomBulb, ItemInit.pickle, ItemInit.anthocite, ItemInit.biosynthiumIngot);
        }
        if (heldItem.func_77973_b().equals(ItemInit.externalCloviniteBattery)) {
            return new MetaRecipe(ItemInit.noxeriumIngot, ItemInit.incadiumIngot, ItemInit.emberiumIngot, ItemInit.ghostlyHusk, ItemInit.flexibleHusk, ItemInit.corruptedRoot, ItemInit.maliciumIngot);
        }
        if (heldItem.func_77973_b().equals(ItemInit.ionCell)) {
            return new MetaRecipe(ItemInit.olysiumIngot, ItemInit.xeroviumIngot, ItemInit.detheriumIngot, ItemInit.ghostlyHusk, ItemInit.photochromicHusk, ItemInit.luminescentStingers, ItemInit.etheriumIngot);
        }
        if (heldItem.func_77973_b().equals(ItemInit.potentPolarcronite)) {
            return new MetaRecipe(ItemInit.phytrosiumIngot, ItemInit.xeroviumIngot, ItemInit.hextoriumIngot, ItemInit.volatileBlood, ItemInit.glowingGlobes, ItemInit.glowingOrgan, ItemInit.polariumIngot);
        }
        return null;
    }
}
