package xol.lostinfinity.item.weapon;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.tileentity.TileEntityTeslaTower;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemCooldown;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.data.RayTraceBuilder;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemTeslaTowers extends ItemCooldown implements IModeSelect {
    private static final int MAX_TOWER = 6;
    public ItemTeslaTowers(String regName) {
        super(regName);
        func_77637_a(TabsInit.TAB_AUXWEP);
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFmt.Gold + "A scythe imbued with electric magic.");
        tooltip.add(TextFmt.Aqua + "Places Tesla Towers that Deal True Damage");
        tooltip.add(TextFmt.Italic + "Towers can be turned on to damage entities between them.");
        tooltip.add(TextFmt.Red + "Towers last 60 seconds.");
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        CustomRayTraceResult result;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!showDurabilityBar(stack)) {
            if (!worldIn.field_72995_K && (result = RayTraceBuilder.force(60).raySize(0.1f).trace(playerIn, true)) != null) {
                TileEntityTeslaTower placed = placeTower(stack, worldIn, result.getResultPos());
                boolean mode = getMode(stack);
                placed.setOwner(playerIn.func_110124_au());
                placed.setActive(mode);
                if (mode) {
                    iterateTowers(worldIn, stack, (tile, posSet) -> {
                        tile.setOwner(playerIn.func_110124_au());
                        tile.setOthers(posSet);
                        tile.doBlockUpdate();
                    });
                }
                CustomParticleConfig config1 = new CustomParticleConfig();
                config1.setCount(3);
                config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_BLUE).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                config1.createInstance().setParticle(ParticleInit.ELECTRIC_EXPLOSION_YELLOW).setSpread(2.0d, 1.0d, 2.0d).setIgnoreRange(true);
                IParticleSpawner.spawnParticle(worldIn, config1, result.getResultVector().field_72450_a, result.getResultVector().field_72448_b, result.getResultVector().field_72449_c);
                worldIn.func_184133_a((EntityPlayer) null, result.getResultPos(), SoundInit.ELECTRIC_WOOSH, SoundCategory.PLAYERS, 1.5f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                worldIn.func_184133_a((EntityPlayer) null, result.getResultPos(), SoundEvents.field_187845_fY, SoundCategory.PLAYERS, 1.5f, 1.0f);
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.ELECTRIC_WOOSH, SoundCategory.PLAYERS, 1.5f, 0.8f + (worldIn.field_73012_v.nextFloat() * 0.4f));
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundEvents.field_187845_fY, SoundCategory.PLAYERS, 1.5f, 1.0f);
            }
            startCooldown(stack);
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        boolean mode = !getMode(stack);
        setMode(stack, mode);
        World world = player.field_70170_p;
        if (world.field_72995_K) {
            return;
        }
        iterateTowers(world, stack, (tile, posSet) -> {
            tile.setActive(mode);
            tile.setOthers(posSet);
            tile.doBlockUpdate();
        });
    }
    @Override // xol.lostinfinity.item.basics.ItemCooldown
    protected int getCooldown() {
        return 150;
    }
    private TileEntityTeslaTower placeTower(ItemStack stack, World world, BlockPos pos) {
        int id = getPlaceAt(stack);
        BlockPos spot = getTowerPos(stack, id);
        if (spot != null && world.func_180495_p(spot) == BlockInit.teslaTower.func_176223_P()) {
            TileEntityTeslaTower oldTower = (TileEntityTeslaTower) world.func_175625_s(spot);
            if (oldTower.getTowerId() == id) {
                world.func_175698_g(spot);
            }
        }
        stack.func_77978_p().func_74768_a("tower_x_" + id, pos.func_177958_n());
        stack.func_77978_p().func_74768_a("tower_y_" + id, pos.func_177956_o());
        stack.func_77978_p().func_74768_a("tower_z_" + id, pos.func_177952_p());
        world.func_175656_a(pos, BlockInit.teslaTower.func_176223_P());
        TileEntityTeslaTower tower = (TileEntityTeslaTower) world.func_175625_s(pos);
        tower.setTowerId(id);
        return tower;
    }
    private int getPlaceAt(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int next = stack.func_77978_p().func_74762_e("next");
        stack.func_77978_p().func_74768_a("next", (next + 1) % 6);
        return next;
    }
    private void setMode(ItemStack stack, boolean flag) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74757_a("mode", flag);
    }
    private boolean getMode(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74767_n("mode");
    }
    private BlockPos getTowerPos(ItemStack stack, int id) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!stack.func_77978_p().func_74764_b("tower_x_" + id)) {
            return null;
        }
        return new BlockPos(stack.func_77978_p().func_74762_e("tower_x_" + id), stack.func_77978_p().func_74762_e("tower_y_" + id), stack.func_77978_p().func_74762_e("tower_z_" + id));
    }
    private void iterateTowers(World world, ItemStack stack, BiConsumer<TileEntityTeslaTower, Set<BlockPos>> towerConsumer) {
        Set<BlockPos> poses = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            BlockPos spot = getTowerPos(stack, i);
            if (spot != null) {
                poses.add(spot);
            }
        }
        for (BlockPos pos : poses) {
            TileEntity tile = world.func_175625_s(pos);
            if (tile instanceof TileEntityTeslaTower) {
                towerConsumer.accept((TileEntityTeslaTower) tile, poses);
            }
        }
    }
}
