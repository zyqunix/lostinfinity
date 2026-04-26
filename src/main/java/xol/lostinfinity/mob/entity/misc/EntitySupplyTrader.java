package xol.lostinfinity.mob.entity.misc;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketSupplyInventoryClient;
import xol.lostinfinity.common.packets.serverbound.PacketSupplyInventoryServer;
import xol.lostinfinity.gui.GuiHandler;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.mob.entity.base.EntityBaseMerchant;
public class EntitySupplyTrader extends EntityBaseMerchant {
    private static final DataParameter<Integer> TRADE_MODE = EntityDataManager.func_187226_a(EntitySupplyTrader.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ITEM1_COUNT = EntityDataManager.func_187226_a(EntitySupplyTrader.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ITEM2_COUNT = EntityDataManager.func_187226_a(EntitySupplyTrader.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ITEM3_COUNT = EntityDataManager.func_187226_a(EntitySupplyTrader.class, DataSerializers.field_187192_b);
    private static final DataParameter<Integer> ITEM4_COUNT = EntityDataManager.func_187226_a(EntitySupplyTrader.class, DataSerializers.field_187192_b);
    private NonNullList<ItemStack> inventory;
    public int getItem1Count() {
        return ((Integer) this.field_70180_af.func_187225_a(ITEM1_COUNT)).intValue();
    }
    public int getItem2Count() {
        return ((Integer) this.field_70180_af.func_187225_a(ITEM2_COUNT)).intValue();
    }
    public int getItem3Count() {
        return ((Integer) this.field_70180_af.func_187225_a(ITEM3_COUNT)).intValue();
    }
    public int getItem4Count() {
        return ((Integer) this.field_70180_af.func_187225_a(ITEM4_COUNT)).intValue();
    }
    public void setItem1Count(int count) {
        this.field_70180_af.func_187227_b(ITEM1_COUNT, Integer.valueOf(count));
    }
    public void setItem2Count(int count) {
        this.field_70180_af.func_187227_b(ITEM2_COUNT, Integer.valueOf(count));
    }
    public void setItem3Count(int count) {
        this.field_70180_af.func_187227_b(ITEM3_COUNT, Integer.valueOf(count));
    }
    public void setItem4Count(int count) {
        this.field_70180_af.func_187227_b(ITEM4_COUNT, Integer.valueOf(count));
    }
    public EntitySupplyTrader(World worldIn) {
        super(worldIn);
        this.inventory = NonNullList.func_191197_a(100, ItemStack.field_190927_a);
    }
    protected void func_70088_a() {
        super.func_70088_a();
        func_184212_Q().func_187214_a(TRADE_MODE, 0);
        this.field_70180_af.func_187214_a(ITEM1_COUNT, 0);
        this.field_70180_af.func_187214_a(ITEM2_COUNT, 0);
        this.field_70180_af.func_187214_a(ITEM3_COUNT, 0);
        this.field_70180_af.func_187214_a(ITEM4_COUNT, 0);
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public MerchantRecipeList getRecipeList() {
        MerchantRecipeList list = new MerchantRecipeList();
        for (int i = 0; i < SupplyTraderRecipe.getTotAmountRecipes(); i++) {
            SupplyTraderRecipe recipe = SupplyTraderRecipe.getRecipeById(i);
            if (recipe != null) {
                list.add(recipe.getRecipe());
            }
        }
        return list;
    }
    @Override // xol.lostinfinity.mob.entity.base.EntityBaseMerchant
    public boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (!this.field_70170_p.field_72995_K) {
            updateInventoryToClient(player);
            if (player.func_70093_af()) {
                if (getTradeMode() == 0) {
                    setTradeMode(1);
                    player.func_145747_a(new TextComponentString(TextFmt.Gold + "Supply Trader: " + TextFmt.Reset + "Selling items!"));
                } else if (getTradeMode() == 1) {
                    setTradeMode(0);
                    player.func_145747_a(new TextComponentString(TextFmt.Gold + "Supply Trader: " + TextFmt.Reset + "Taking shipments!"));
                }
            }
        }
        if (!player.func_70093_af()) {
            if (!this.field_70170_p.field_72995_K) {
                updateInventoryToClient(player);
            }
            if (getTradeMode() == 0) {
                if (!this.field_70170_p.field_72995_K) {
                    player.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.SUPPLY_DEPOSIT.getId(), this.field_70170_p, func_180425_c().func_177958_n(), func_180425_c().func_177956_o(), func_180425_c().func_177952_p());
                    return true;
                }
                return true;
            }
            if (getTradeMode() == 1 && !this.field_70170_p.field_72995_K) {
                player.openGui(lostinfinity.instance, GuiHandler.RegisteredGuis.SUPPLY_STORE.getId(), this.field_70170_p, func_180425_c().func_177958_n(), func_180425_c().func_177956_o(), func_180425_c().func_177952_p());
                return true;
            }
            return true;
        }
        return true;
    }
    public void updateInventoryToClient(EntityPlayer player) {
        for (int i = 0; i < this.inventory.size(); i++) {
            lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) player, new PacketSupplyInventoryClient(func_145782_y(), i, (ItemStack) this.inventory.get(i)));
        }
    }
    public void updateInventoryToServer(int slot, ItemStack stack) {
        for (int i = 0; i < this.inventory.size(); i++) {
            lostinfinity.instance.packetHandler.sendServerBasicPacket(new PacketSupplyInventoryServer(func_145782_y(), i, (ItemStack) this.inventory.get(i)));
        }
    }
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);
        if (!this.field_70170_p.field_72995_K) {
            List<EntityPlayer> nearPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, new AxisAlignedBB(func_180425_c()).func_186662_g(32.0d));
            nearPlayers.forEach(this::updateInventoryToClient);
        }
    }
    public void func_70014_b(NBTTagCompound compound) {
        ItemStackHelper.func_191282_a(compound, this.inventory);
        compound.func_74768_a("trade_mode", getTradeMode());
        compound.func_74768_a("item1_count", getItem1Count());
        compound.func_74768_a("item2_count", getItem2Count());
        compound.func_74768_a("item3_count", getItem3Count());
        compound.func_74768_a("item4_count", getItem4Count());
        super.func_70014_b(compound);
    }
    public void func_70037_a(NBTTagCompound compound) {
        ItemStackHelper.func_191283_b(compound, this.inventory);
        setTradeMode(compound.func_74762_e("trade_mode"));
        setItem1Count(compound.func_74762_e("item1_count"));
        setItem2Count(compound.func_74762_e("item2_count"));
        setItem3Count(compound.func_74762_e("item3_count"));
        setItem4Count(compound.func_74762_e("item4_count"));
        super.func_70037_a(compound);
    }
    public int getTradeMode() {
        return ((Integer) func_184212_Q().func_187225_a(TRADE_MODE)).intValue();
    }
    public void setTradeMode(int tradeMode) {
        func_184212_Q().func_187227_b(TRADE_MODE, Integer.valueOf(tradeMode));
    }
    public NonNullList<ItemStack> getInventory() {
        return this.inventory;
    }
    public int stock(Item item) {
        int count = 0;
        for (ItemStack stack : this.inventory) {
            if (stack.func_77973_b() == item) {
                count += stack.func_190916_E();
            }
        }
        return count;
    }
    public ItemStack getStack(Item item) {
        for (ItemStack stack : this.inventory) {
            if (stack.func_77973_b() == item) {
                return stack;
            }
        }
        return ItemStack.field_190927_a;
    }
    public int getStackIndex(Item item) {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (((ItemStack) this.inventory.get(i)).func_77973_b() == item) {
                return i;
            }
        }
        return -1;
    }
    private void displayItems(EntityPlayer player) {
        for (int i = 0; i < this.inventory.size(); i++) {
            if (this.inventory.get(i) != ItemStack.field_190927_a) {
                player.func_145747_a(new TextComponentString(TextFmt.Gold + "Item: " + TextFmt.Reset + ((ItemStack) this.inventory.get(i)).func_82833_r()));
                player.func_145747_a(new TextComponentString(TextFmt.Gold + "Quantity: " + TextFmt.Reset + ((ItemStack) this.inventory.get(i)).func_190916_E()));
            }
        }
    }
    public enum SupplyTraderRecipe {
        AZURE_LEAF(ItemInit.azureLeaf, ItemInit.amazoniteToken, 0, 15, 1, 0, 166),
        VINES(ItemInit.constrictingVines, ItemInit.amazoniteToken, 1, 10, 1, 16, 166),
        INGOT(ItemInit.lucientIngot, ItemInit.amazoniteToken, 2, 3, 3, 32, 166),
        BLOSSOM(ItemInit.bumbleBlossom, ItemInit.amazoniteToken, 3, 4, 1, 48, 166);
        private Item item;
        private Item output;
        private int id;
        private int buyAmount;
        private int sellAmount;
        private int pixelX;
        private int pixelY;
        private MerchantRecipe recipe;
        SupplyTraderRecipe(Item item, Item output, int id, int buyAmount, int sellAmount, int pixelX, int pixelY) {
            this.item = item;
            this.output = output;
            this.id = id;
            this.buyAmount = buyAmount;
            this.sellAmount = sellAmount;
            this.pixelX = pixelX;
            this.pixelY = pixelY;
            this.recipe = new MerchantRecipe(new ItemStack(item, buyAmount), new ItemStack(output, sellAmount));
        }
        public Item getItem() {
            return this.item;
        }
        public Item getOutput() {
            return this.output;
        }
        public int getBuyAmount() {
            return this.buyAmount;
        }
        public int getSellAmount() {
            return this.sellAmount;
        }
        public int getId() {
            return this.id;
        }
        public int getPixelX() {
            return this.pixelX;
        }
        public int getPixelY() {
            return this.pixelY;
        }
        public MerchantRecipe getRecipe() {
            return this.recipe;
        }
        public static SupplyTraderRecipe getRecipeById(int id) {
            for (SupplyTraderRecipe recipe : values()) {
                if (recipe.getId() == id) {
                    return recipe;
                }
            }
            return null;
        }
        public static boolean isItemInRecipe(Item item) {
            for (SupplyTraderRecipe recipe : values()) {
                if (recipe.getItem() == item) {
                    return true;
                }
            }
            return false;
        }
        public static int getTotAmountRecipes() {
            return values().length;
        }
    }
}
