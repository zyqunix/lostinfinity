package xol.lostinfinity.item.activate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.DimensionInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
import xol.lostinfinity.init.TabsInit;
import xol.lostinfinity.item.basics.ItemBasic;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/item/activate/ItemFossilFinder.class */
public class ItemFossilFinder extends ItemBasic {
    public ItemFossilFinder(String regName) {
        super(regName, TabsInit.TAB_AUXMATS);
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        BlockPos pos;
        BlockPos trackPos;
        ItemStack stack = playerIn.func_184586_b(handIn);
        if (!worldIn.field_72995_K && worldIn.field_73011_w.func_186058_p() == DimensionInit.shadowSea) {
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74762_e("progress");
            if (!stack.func_77978_p().func_74764_b("trackLength")) {
                if (stack.func_77978_p().func_74764_b("avoidX")) {
                    int avoidX = stack.func_77978_p().func_74762_e("avoidX");
                    int avoidZ = stack.func_77978_p().func_74762_e("avoidZ");
                    int xDiff = Math.abs(((int) playerIn.field_70165_t) - avoidX);
                    int zDiff = Math.abs(((int) playerIn.field_70161_v) - avoidZ);
                    if (xDiff < 20 || zDiff < 20) {
                        playerIn.func_145747_a(new TextComponentString("Too close to last sample, find a new location!"));
                        return super.func_77659_a(worldIn, playerIn, handIn);
                    }
                }
                int randX = playerIn.func_180425_c().func_177958_n() + worldIn.field_73012_v.nextInt(15) + 18;
                int randZ = playerIn.func_180425_c().func_177952_p() + worldIn.field_73012_v.nextInt(15) + 18;
                int curX = playerIn.func_180425_c().func_177958_n();
                int curZ = playerIn.func_180425_c().func_177952_p();
                ArrayList<BlockPos> tracks = new ArrayList<>();
                BlockPos blockPos = new BlockPos(curX, worldIn.func_189649_b(curX, curZ), curZ);
                while (true) {
                    pos = blockPos;
                    if (worldIn.func_175623_d(pos)) {
                        break;
                    }
                    blockPos = pos.func_177984_a();
                }
                tracks.add(pos);
                while (true) {
                    if (curX == randX && curZ == randZ) {
                        break;
                    }
                    int zDiff2 = randZ - curZ;
                    int xDiff2 = randX - curX;
                    if (xDiff2 == 0) {
                        curZ = (int) (curZ + Math.signum(zDiff2));
                    } else if (zDiff2 != 0 && worldIn.field_73012_v.nextBoolean()) {
                        curZ = (int) (curZ + Math.signum(zDiff2));
                    } else {
                        curX = (int) (curX + Math.signum(xDiff2));
                    }
                    BlockPos blockPos2 = new BlockPos(curX, worldIn.func_189649_b(curX, curZ), curZ);
                    while (true) {
                        trackPos = blockPos2;
                        if (!worldIn.func_175623_d(trackPos)) {
                            blockPos2 = trackPos.func_177984_a();
                        }
                    }
                    tracks.add(trackPos);
                }
                worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.GENERIC_UI_1, SoundCategory.PLAYERS, 1.0f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                worldIn.func_175656_a(pos, BlockInit.fossilTrack.func_176223_P());
                stack.func_77978_p().func_74768_a("trackLength", tracks.size());
                playerIn.func_145747_a(new TextComponentString("Discovered fossil evidence, follow the trail!"));
                for (int i = 0; i < tracks.size(); i++) {
                    stack.func_77978_p().func_74768_a("track" + i + "X", tracks.get(i).func_177958_n());
                    stack.func_77978_p().func_74768_a("track" + i + "Y", tracks.get(i).func_177956_o());
                    stack.func_77978_p().func_74768_a("track" + i + "Z", tracks.get(i).func_177952_p());
                }
            } else if (!stack.func_77978_p().func_74764_b("finalX")) {
                int trackLength = stack.func_77978_p().func_74762_e("trackLength");
                ArrayList<BlockPos> tracks2 = new ArrayList<>();
                for (int i2 = 0; i2 < trackLength; i2++) {
                    int x = stack.func_77978_p().func_74762_e("track" + i2 + "X");
                    int y = stack.func_77978_p().func_74762_e("track" + i2 + "Y");
                    int z = stack.func_77978_p().func_74762_e("track" + i2 + "Z");
                    tracks2.add(new BlockPos(x, y, z));
                }
                ArrayList<BlockPos> nearPlayerPositions = new ArrayList<>();
                for (BlockPos nearPlayerPos : BlockPos.func_177980_a(playerIn.func_180425_c().func_177982_a(-2, 0, -2), playerIn.func_180425_c().func_177982_a(2, 1, 2))) {
                    nearPlayerPositions.add(nearPlayerPos);
                }
                Iterator<BlockPos> it = nearPlayerPositions.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BlockPos nearPos = it.next();
                    Iterator<BlockPos> it2 = tracks2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            BlockPos track = it2.next();
                            if (track.func_177958_n() == nearPos.func_177958_n() && track.func_177952_p() == nearPos.func_177952_p()) {
                                IBlockState state = worldIn.func_180495_p(track);
                                if (state.func_177230_c() != BlockInit.fossilTrack) {
                                    int lastRevealedIndex = 0;
                                    for (int i3 = 0; i3 < tracks2.size(); i3++) {
                                        if (worldIn.func_180495_p(tracks2.get(i3)).func_177230_c() == BlockInit.fossilTrack) {
                                            lastRevealedIndex = i3;
                                        }
                                    }
                                    int index = tracks2.indexOf(track);
                                    int numToReveal = 1;
                                    if (index > lastRevealedIndex) {
                                        int indDiff = index - lastRevealedIndex;
                                        if (indDiff > 0) {
                                            worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.WATER_MUD, SoundCategory.PLAYERS, 1.0f, 0.5f + worldIn.field_73012_v.nextFloat());
                                        }
                                        numToReveal = (indDiff / 2) + 1;
                                    }
                                    int bound = index + numToReveal < tracks2.size() ? index + numToReveal : tracks2.size();
                                    for (int i4 = 0; i4 < bound; i4++) {
                                        worldIn.func_175656_a(tracks2.get(i4), BlockInit.fossilTrack.func_176223_P());
                                    }
                                    if (bound == tracks2.size()) {
                                        playerIn.func_145747_a(new TextComponentString("You have reached the end of the trail, search your surroundings for the fossil"));
                                        ArrayList<BlockPos> positions = new ArrayList<>();
                                        for (BlockPos position : BlockPos.func_177980_a(playerIn.func_180425_c().func_177982_a(-15, 0, -15), playerIn.func_180425_c().func_177982_a(15, 15, 15))) {
                                            positions.add(position);
                                        }
                                        Collections.shuffle(positions);
                                        Iterator<BlockPos> it3 = positions.iterator();
                                        while (true) {
                                            if (!it3.hasNext()) {
                                                break;
                                            }
                                            BlockPos pos2 = it3.next();
                                            if (!worldIn.func_175623_d(pos2) && worldIn.func_175623_d(pos2.func_177984_a())) {
                                                stack.func_77978_p().func_74768_a("finalX", pos2.func_177958_n());
                                                stack.func_77978_p().func_74768_a("finalY", pos2.func_177956_o());
                                                stack.func_77978_p().func_74768_a("finalZ", pos2.func_177952_p());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (stack.func_77978_p().func_74764_b("finalX")) {
                int xDiff3 = Math.abs(playerIn.func_180425_c().func_177958_n() - stack.func_77978_p().func_74762_e("finalX"));
                int yDiff = Math.abs(playerIn.func_180425_c().func_177956_o() - stack.func_77978_p().func_74762_e("finalY"));
                int zDiff3 = Math.abs(playerIn.func_180425_c().func_177952_p() - stack.func_77978_p().func_74762_e("finalZ"));
                if (xDiff3 + yDiff + zDiff3 < 3) {
                    stack.func_77978_p().func_82580_o("finalX");
                    stack.func_77978_p().func_82580_o("finalY");
                    stack.func_77978_p().func_82580_o("finalZ");
                    int trackLength2 = stack.func_77978_p().func_74762_e("trackLength");
                    for (int i5 = 0; i5 < trackLength2; i5++) {
                        int x2 = stack.func_77978_p().func_74762_e("track" + i5 + "X");
                        int y2 = stack.func_77978_p().func_74762_e("track" + i5 + "Y");
                        int z2 = stack.func_77978_p().func_74762_e("track" + i5 + "Z");
                        worldIn.func_175698_g(new BlockPos(x2, y2, z2));
                    }
                    stack.func_77982_d(new NBTTagCompound());
                    playerIn.func_145747_a(new TextComponentString("Fossil found!"));
                    EntityItem fossil = new EntityItem(worldIn, playerIn.field_70165_t, playerIn.field_70163_u + 0.4d, playerIn.field_70161_v, new ItemStack(randomFossil(worldIn.field_73012_v)));
                    fossil.field_70159_w = 0.0d;
                    fossil.field_70181_x = 0.0d;
                    fossil.field_70179_y = 0.0d;
                    worldIn.func_72838_d(fossil);
                    worldIn.func_184133_a((EntityPlayer) null, playerIn.func_180425_c(), SoundInit.WATER_REVEAL, SoundCategory.PLAYERS, 1.5f, 0.7f + (worldIn.field_73012_v.nextFloat() * 0.6f));
                } else if (xDiff3 + yDiff + zDiff3 < 7) {
                    playerIn.func_145747_a(new TextComponentString("You are very close to the fossil!"));
                } else if (xDiff3 + yDiff + zDiff3 < 15) {
                    playerIn.func_145747_a(new TextComponentString("You are close to the fossil!"));
                } else if (xDiff3 + yDiff + zDiff3 < 45) {
                    playerIn.func_145747_a(new TextComponentString("The fossil is in this area, but you aren't that close!"));
                } else {
                    playerIn.func_145747_a(new TextComponentString("You are too far! Look in the area around the end of the track!"));
                }
            }
        }
        return super.func_77659_a(worldIn, playerIn, handIn);
    }

    private Item randomFossil(Random rand) {
        switch (rand.nextInt(6)) {
            case 0:
                return ItemInit.fossilEelBotjaw;
            case 1:
                return ItemInit.fossilRibbedTail;
            case 2:
                return ItemInit.fossilEelTopjaw;
            case 3:
                return ItemInit.fossilSmallRibs;
            case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                return ItemInit.fossilSmallRibs;
            case 5:
                return ItemInit.fossilRibbedTail;
            default:
                return ItemInit.fossilSmallRibs;
        }
    }
}
