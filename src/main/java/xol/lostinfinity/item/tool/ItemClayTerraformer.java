package xol.lostinfinity.item.tool;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xol.lostinfinity.block.basic.BlockBasicBoolState;
import xol.lostinfinity.block.harvest.BlockSeaClay;
import xol.lostinfinity.client.TextFmt;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ParticleInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;
import xol.lostinfinity.item.classify.ICustomHoldPose;
import xol.lostinfinity.item.classify.ICustomRaytrace;
import xol.lostinfinity.item.classify.IHeldTick;
import xol.lostinfinity.item.classify.IModeSelect;
import xol.lostinfinity.item.classify.ISwitchModels;
import xol.lostinfinity.util.data.CustomParticleConfig;
import xol.lostinfinity.util.data.CustomRayTraceResult;
import xol.lostinfinity.util.fx.IParticleSpawner;
public class ItemClayTerraformer extends ItemBasic implements IModeSelect, ISwitchModels, ICustomRaytrace, IHeldTick, ICustomHoldPose {
    private static final TextComponentString FAILED_HARVEST = new TextComponentString(TextFmt.Red + "You can't harvest anything here.");
    private static final TextComponentString FAILED_DEPLOY_INSUFFICIENT = new TextComponentString(TextFmt.Red + "You don't have enough clay stored to form a molded block.");
    private static final String STORED_CLAY_AMOUNT = TextFmt.Green + "You have %d clay stored.";
    public ItemClayTerraformer(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }
    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack heldItem = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.shadowSea) {
            if (heldItem.func_77978_p().func_74762_e("mode") == 0) {
                CustomRayTraceResult trace = simpleBlockTrace(worldIn, playerIn, 15);
                BlockPos startPos = playerIn.func_180425_c();
                if (trace != null) {
                    BlockPos resultPos = new BlockPos(trace.getGrabbedVector());
                    if (!(worldIn.func_180495_p(resultPos).func_177230_c() instanceof BlockSeaClay)) {
                        worldIn.func_184133_a((EntityPlayer) null, startPos, SoundInit.WEAPON_ERROR, SoundCategory.PLAYERS, 1.0f, (worldIn.field_73012_v.nextFloat() * 0.2f) + 0.9f);
                        playerIn.func_145747_a(FAILED_HARVEST);
                        return super.func_77659_a(worldIn, playerIn, handIn);
                    }
                    IBlockState state = worldIn.func_180495_p(resultPos);
                    boolean active = ((Boolean) state.func_177229_b(BlockBasicBoolState.ACTIVE)).booleanValue();
                    if (active) {
                        worldIn.func_175656_a(resultPos, BlockInit.seaClay.func_176203_a(0));
                        worldIn.func_184133_a((EntityPlayer) null, startPos, SoundInit.CLAY_SUCK, SoundCategory.PLAYERS, 1.0f, (worldIn.field_73012_v.nextFloat() * 0.2f) + 0.9f);
                        CustomParticleConfig config1 = new CustomParticleConfig();
                        config1.createInstance().setParticle(ParticleInit.CLAY_BUBBLE).setSpread(0.5d, 0.5d, 0.5d).setCount(5).setIgnoreRange(true);
                        IParticleSpawner.spawnParticle(worldIn, config1, resultPos.func_177958_n(), resultPos.func_177956_o(), resultPos.func_177952_p());
                        int clayCount = heldItem.func_77978_p().func_74762_e("stored_clay") + worldIn.field_73012_v.nextInt(5) + 4;
                        if (clayCount > 100) {
                            clayCount = 100;
                        }
                        heldItem.func_77978_p().func_74768_a("stored_clay", clayCount);
                        String storedClay = STORED_CLAY_AMOUNT;
                        playerIn.func_145747_a(new TextComponentString(storedClay.replace("%d", String.valueOf(clayCount))));
                    }
                } else {
                    playerIn.func_145747_a(FAILED_HARVEST);
                }
            } else if (heldItem.func_77978_p().func_74762_e("mode") == 1 && heldItem.func_77978_p().func_74764_b("PreviewX")) {
                BlockPos resultPos2 = new BlockPos(heldItem.func_77978_p().func_74769_h("PreviewX"), heldItem.func_77978_p().func_74769_h("PreviewY"), heldItem.func_77978_p().func_74769_h("PreviewZ"));
                int clayCount2 = heldItem.func_77978_p().func_74762_e("stored_clay");
                if (clayCount2 >= 4) {
                    int clayCount3 = clayCount2 - 4;
                    heldItem.func_77978_p().func_74768_a("stored_clay", clayCount3);
                    worldIn.func_175656_a(resultPos2, BlockInit.moldedSeaClay.func_176223_P());
                    worldIn.func_184133_a((EntityPlayer) null, resultPos2, SoundInit.CLAY_PLACE, SoundCategory.PLAYERS, 1.0f, (worldIn.field_73012_v.nextFloat() * 0.2f) + 0.9f);
                    CustomParticleConfig config12 = new CustomParticleConfig();
                    config12.createInstance().setParticle(ParticleInit.CLAY_BLOCK).setSpread(2.0d, 2.0d, 2.0d).setCount(10).setIgnoreRange(true);
                    IParticleSpawner.spawnParticle(worldIn, config12, resultPos2.func_177958_n(), resultPos2.func_177956_o(), resultPos2.func_177952_p());
                    checkForStructure(resultPos2, worldIn, playerIn);
                    String storedClay2 = STORED_CLAY_AMOUNT;
                    playerIn.func_145747_a(new TextComponentString(storedClay2.replace("%d", String.valueOf(clayCount3))));
                } else {
                    playerIn.func_145747_a(FAILED_DEPLOY_INSUFFICIENT);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.WEAPON_ERROR, SoundCategory.PLAYERS, 1.0f, (worldIn.field_73012_v.nextFloat() * 0.2f) + 0.9f);
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkForStructure(net.minecraft.util.math.BlockPos r15, net.minecraft.world.World r16, net.minecraft.entity.player.EntityPlayer r17) {
        /*
            Method dump skipped, instruction units count: 2030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: xol.lostinfinity.item.tool.ItemClayTerraformer.checkForStructure(net.minecraft.util.math.BlockPos, net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer):void");
    }
    @Override // xol.lostinfinity.item.classify.IHeldTick
    public void heldTick(EntityPlayer player, EnumHand hand, ItemStack stack) {
        CustomRayTraceResult trace;
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        World world = player.field_70170_p;
        if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 5 == 0) {
            if (stack.func_77978_p().func_74764_b("PreviewX")) {
                BlockPos pos = new BlockPos(stack.func_77978_p().func_74769_h("PreviewX"), stack.func_77978_p().func_74769_h("PreviewY"), stack.func_77978_p().func_74769_h("PreviewZ"));
                if (world.func_180495_p(pos).func_177230_c() == BlockInit.selectionPreview) {
                    world.func_175698_g(pos);
                }
                stack.func_77978_p().func_82580_o("PreviewX");
                stack.func_77978_p().func_82580_o("PreviewY");
                stack.func_77978_p().func_82580_o("PreviewZ");
            }
            if (stack.func_77978_p().func_74762_e("mode") == 1 && (trace = simpleBlockTrace(world, player, 15)) != null) {
                BlockPos result = trace.getResultPos();
                world.func_175656_a(result, BlockInit.selectionPreview.func_176223_P());
                stack.func_77978_p().func_74780_a("PreviewX", result.func_177958_n());
                stack.func_77978_p().func_74780_a("PreviewY", result.func_177956_o());
                stack.func_77978_p().func_74780_a("PreviewZ", result.func_177952_p());
            }
        }
    }
    @Override // xol.lostinfinity.item.classify.IModeSelect
    public void modeUpdate(ItemStack stack, EntityPlayer player) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        player.field_70170_p.func_184133_a((EntityPlayer) null, player.func_180425_c(), SoundEvents.field_187750_dc, SoundCategory.PLAYERS, 1.0f, 1.0f);
        int mode = stack.func_77978_p().func_74762_e("mode");
        if (mode == 0) {
            player.func_145747_a(new TextComponentString(TextFmt.Aqua + "Deploy mode active!"));
            stack.func_77978_p().func_74768_a("mode", 1);
        } else {
            player.func_145747_a(new TextComponentString(TextFmt.Red + "Harvest Mode active!"));
            stack.func_77978_p().func_74768_a("mode", 0);
        }
    }
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        tooltip.add(TextFmt.Red + "Harvest Mode: Collects clay from active sea clay nodes.");
        tooltip.add(TextFmt.Aqua + "Deploy Mode: Places sea clay at targeted location.");
        tooltip.add("");
        tooltip.add(TextFmt.Gold + "Right Click: Mode Action");
        tooltip.add(TextFmt.Gold + "Shift-Right Click: Change Mode");
        tooltip.add("");
        tooltip.add(TextFmt.Gray + "Stored Clay: " + stack.func_77978_p().func_74762_e("stored_clay"));
    }
}
