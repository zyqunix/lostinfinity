package xol.lostinfinity.block.activator;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import xol.lostinfinity.block.basic.BlockBasic;
import xol.lostinfinity.dimension.data.TetrisMap;
import xol.lostinfinity.init.BlockInit;
import xol.lostinfinity.init.ItemInit;
import xol.lostinfinity.init.SoundInit;
public class BlockNeocraftingStation extends BlockBasic {
    private static int[][][] linePiece = {new int[]{new int[]{0, 1, 2, 3}, new int[]{0, 0, 0, 0}}, new int[]{new int[]{0, 0, 0, 0}, new int[]{0, 1, 2, 3}}};
    private static int[][][] lPiece = {new int[]{new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, 1}}, new int[]{new int[]{0, 0, 0, 1}, new int[]{0, 1, 2, 2}}, new int[]{new int[]{0, 0, 0, -1}, new int[]{0, 1, 2, 2}}, new int[]{new int[]{-1, 0, 0, 0}, new int[]{0, 0, 1, 2}}, new int[]{new int[]{1, 0, 0, 0}, new int[]{0, 0, 1, 2}}, new int[]{new int[]{0, 1, 2, 2}, new int[]{0, 0, 0, -1}}, new int[]{new int[]{0, 0, 1, 2}, new int[]{-1, 0, 0, 0}}, new int[]{new int[]{0, 0, 1, 2}, new int[]{1, 0, 0, 0}}};
    private static int[][][] tPiece = {new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, 0}}, new int[]{new int[]{0, 1, 1, 1}, new int[]{0, 1, 0, -1}}, new int[]{new int[]{0, 0, 0, 1}, new int[]{1, 0, -1, 0}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 0}}};
    private static int[][][] zigPiece = {new int[]{new int[]{0, 0, 1, 1}, new int[]{-1, 0, 0, 1}}, new int[]{new int[]{1, 1, 0, 0}, new int[]{1, 0, 0, -1}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1}}, new int[]{new int[]{0, 1, 1, 2}, new int[]{0, 0, -1, -1}}};
    private static int[][][] squarePiece = {new int[]{new int[]{0, 0, 1, 1}, new int[]{0, 1, 0, 1}}};
    public BlockNeocraftingStation(String name) {
        super(name);
    }
    public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos checkPos;
        BlockPos checkPos2;
        ItemStack stack = playerIn.func_184586_b(hand);
        if (stack.func_77973_b() == ItemInit.polychargeSolution) {
            if (!worldIn.field_72995_K) {
                int randN = worldIn.field_73012_v.nextInt(3) + 1;
                int randM = worldIn.field_73012_v.nextInt(3) + 1;
                TetrisMap map = new TetrisMap(randN * 4, randM * 4);
                playerIn.func_145747_a(new TextComponentString("Fill in an " + (randN * 4) + " by " + (randM * 4) + " area with the pieces shown."));
                int[] pieceList = map.getPieceList();
                int linePieces = pieceList[0];
                int lPieces = pieceList[1];
                int tPieces = pieceList[2];
                int zigPieces = pieceList[3];
                int squarePieces = pieceList[4];
                Vec3i upDir = new Vec3i(0, 0, 0);
                Vec3i rightDir = new Vec3i(0, 0, 0);
                Vec3i offset = new Vec3i(-7, -2, 13);
                BlockPos ref = pos.func_177971_a(offset);
                if (worldIn.func_180495_p(ref.func_177982_a(-1, 0, 0)).func_177230_c() instanceof BlockNeocraft) {
                    upDir = new Vec3i(1, 0, 0);
                    rightDir = new Vec3i(0, 0, 1);
                } else if (worldIn.func_180495_p(ref.func_177982_a(1, 0, 0)).func_177230_c() instanceof BlockNeocraft) {
                    upDir = new Vec3i(-1, 0, 0);
                    rightDir = new Vec3i(0, 0, -1);
                } else if (worldIn.func_180495_p(ref.func_177982_a(0, 0, 1)).func_177230_c() instanceof BlockNeocraft) {
                    upDir = new Vec3i(0, 0, -1);
                    rightDir = new Vec3i(1, 0, 0);
                } else if (worldIn.func_180495_p(ref.func_177982_a(0, 0, -1)).func_177230_c() instanceof BlockNeocraft) {
                    upDir = new Vec3i(0, 0, 1);
                    rightDir = new Vec3i(-1, 0, 0);
                }
                BlockPos linePos = ref.func_177982_a(rightDir.func_177958_n() * 1, 1, rightDir.func_177952_p() * 1);
                BlockPos lPos = ref.func_177982_a(rightDir.func_177958_n() * 3, 1, rightDir.func_177952_p() * 3);
                BlockPos tPos = ref.func_177982_a(rightDir.func_177958_n() * 5, 1, rightDir.func_177952_p() * 5);
                BlockPos zigPos = ref.func_177982_a(rightDir.func_177958_n() * 7, 1, rightDir.func_177952_p() * 7);
                BlockPos squarePos = ref.func_177982_a(rightDir.func_177958_n() * 9, 1, rightDir.func_177952_p() * 9);
                reset(upDir, rightDir, worldIn, ref);
                for (int i = 0; i < linePieces; i++) {
                    worldIn.func_175656_a(linePos.func_177982_a(0, i, 0), BlockInit.neocraftGoalBlue.func_176223_P());
                }
                for (int i2 = 0; i2 < lPieces; i2++) {
                    worldIn.func_175656_a(lPos.func_177982_a(0, i2, 0), BlockInit.neocraftGoalOrange.func_176223_P());
                }
                for (int i3 = 0; i3 < tPieces; i3++) {
                    worldIn.func_175656_a(tPos.func_177982_a(0, i3, 0), BlockInit.neocraftGoalPink.func_176223_P());
                }
                for (int i4 = 0; i4 < zigPieces; i4++) {
                    worldIn.func_175656_a(zigPos.func_177982_a(0, i4, 0), BlockInit.neocraftGoalGreen.func_176223_P());
                }
                for (int i5 = 0; i5 < squarePieces; i5++) {
                    worldIn.func_175656_a(squarePos.func_177982_a(0, i5, 0), BlockInit.neocraftGoalYellow.func_176223_P());
                }
            }
            stack.func_190918_g(1);
            return true;
        }
        if (!worldIn.field_72995_K) {
            new ArrayList();
            Vec3i upDir2 = new Vec3i(0, 0, 0);
            Vec3i rightDir2 = new Vec3i(0, 0, 0);
            Vec3i offset2 = new Vec3i(-7, -2, 13);
            BlockPos ref2 = pos.func_177971_a(offset2);
            if (worldIn.func_180495_p(ref2.func_177982_a(-1, 0, 0)).func_177230_c() instanceof BlockNeocraft) {
                upDir2 = new Vec3i(1, 0, 0);
                rightDir2 = new Vec3i(0, 0, 1);
            } else if (worldIn.func_180495_p(ref2.func_177982_a(1, 0, 0)).func_177230_c() instanceof BlockNeocraft) {
                upDir2 = new Vec3i(-1, 0, 0);
                rightDir2 = new Vec3i(0, 0, -1);
            } else if (worldIn.func_180495_p(ref2.func_177982_a(0, 0, 1)).func_177230_c() instanceof BlockNeocraft) {
                upDir2 = new Vec3i(0, 0, -1);
                rightDir2 = new Vec3i(1, 0, 0);
            } else if (worldIn.func_180495_p(ref2.func_177982_a(0, 0, -1)).func_177230_c() instanceof BlockNeocraft) {
                upDir2 = new Vec3i(0, 0, 1);
                rightDir2 = new Vec3i(-1, 0, 0);
            }
            BlockPos linePos2 = ref2.func_177982_a(rightDir2.func_177958_n() * 1, 1, rightDir2.func_177952_p() * 1);
            BlockPos lPos2 = ref2.func_177982_a(rightDir2.func_177958_n() * 3, 1, rightDir2.func_177952_p() * 3);
            BlockPos tPos2 = ref2.func_177982_a(rightDir2.func_177958_n() * 5, 1, rightDir2.func_177952_p() * 5);
            BlockPos zigPos2 = ref2.func_177982_a(rightDir2.func_177958_n() * 7, 1, rightDir2.func_177952_p() * 7);
            BlockPos squarePos2 = ref2.func_177982_a(rightDir2.func_177958_n() * 9, 1, rightDir2.func_177952_p() * 9);
            int lineCount = 0;
            int lCount = 0;
            int tCount = 0;
            int zigCount = 0;
            int squareCount = 0;
            BlockPos blockPos = new BlockPos(linePos2);
            while (true) {
                BlockPos checkPos3 = blockPos;
                if (worldIn.func_180495_p(checkPos3).func_177230_c() != BlockInit.neocraftGoalBlue) {
                    break;
                }
                lineCount++;
                blockPos = checkPos3.func_177982_a(0, 1, 0);
            }
            BlockPos blockPos2 = new BlockPos(lPos2);
            while (true) {
                BlockPos checkPos4 = blockPos2;
                if (worldIn.func_180495_p(checkPos4).func_177230_c() != BlockInit.neocraftGoalOrange) {
                    break;
                }
                lCount++;
                blockPos2 = checkPos4.func_177982_a(0, 1, 0);
            }
            BlockPos blockPos3 = new BlockPos(tPos2);
            while (true) {
                BlockPos checkPos5 = blockPos3;
                if (worldIn.func_180495_p(checkPos5).func_177230_c() != BlockInit.neocraftGoalPink) {
                    break;
                }
                tCount++;
                blockPos3 = checkPos5.func_177982_a(0, 1, 0);
            }
            BlockPos blockPos4 = new BlockPos(zigPos2);
            while (true) {
                BlockPos checkPos6 = blockPos4;
                if (worldIn.func_180495_p(checkPos6).func_177230_c() != BlockInit.neocraftGoalGreen) {
                    break;
                }
                zigCount++;
                blockPos4 = checkPos6.func_177982_a(0, 1, 0);
            }
            BlockPos blockPos5 = new BlockPos(squarePos2);
            while (true) {
                BlockPos checkPos7 = blockPos5;
                if (worldIn.func_180495_p(checkPos7).func_177230_c() != BlockInit.neocraftGoalYellow) {
                    break;
                }
                squareCount++;
                blockPos5 = checkPos7.func_177982_a(0, 1, 0);
            }
            if (lineCount + squareCount + zigCount + lCount + tCount == 0) {
                playerIn.func_145747_a(new TextComponentString("No Goal Blocks Present. Cannot craft"));
                return true;
            }
            BlockPos blockPosFunc_177973_b = ref2.func_177973_b(upDir2);
            while (true) {
                checkPos = blockPosFunc_177973_b;
                if (!(worldIn.func_180495_p(checkPos).func_177230_c() instanceof BlockNeocraft)) {
                    break;
                }
                blockPosFunc_177973_b = checkPos.func_177973_b(upDir2);
            }
            BlockPos blockPosFunc_177971_a = checkPos.func_177971_a(upDir2);
            while (true) {
                checkPos2 = blockPosFunc_177971_a;
                if (!(worldIn.func_180495_p(checkPos2).func_177230_c() instanceof BlockNeocraft)) {
                    break;
                }
                blockPosFunc_177971_a = checkPos2.func_177973_b(rightDir2);
            }
            BlockPos checkPos8 = checkPos2.func_177971_a(rightDir2);
            int blueCount = 0;
            int greenCount = 0;
            int orangeCount = 0;
            int pinkCount = 0;
            int yellowCount = 0;
            boolean notFound = false;
            loop12: for (int i6 = 0; i6 < 12; i6++) {
                for (int j = 0; j < 12; j++) {
                    BlockPos countPos = checkPos8.func_177982_a((rightDir2.func_177958_n() * i6) + (upDir2.func_177958_n() * j), 0, (rightDir2.func_177952_p() * i6) + (upDir2.func_177952_p() * j));
                    Block block = worldIn.func_180495_p(countPos).func_177230_c();
                    if (block == BlockInit.neocraftBlue) {
                        blueCount++;
                        notFound = !findPiece(worldIn, countPos, "line");
                    } else if (block == BlockInit.neocraftGreen) {
                        greenCount++;
                        notFound = !findPiece(worldIn, countPos, "zig");
                    } else if (block == BlockInit.neocraftPink) {
                        pinkCount++;
                        notFound = !findPiece(worldIn, countPos, "t");
                    } else if (block == BlockInit.neocraftYellow) {
                        yellowCount++;
                        notFound = !findPiece(worldIn, countPos, "square");
                    } else if (block == BlockInit.neocraftOrange) {
                        orangeCount++;
                        notFound = !findPiece(worldIn, countPos, "l");
                    }
                    if (notFound) {
                        break loop12;
                    }
                }
            }
            if (blueCount / 4 < lineCount || pinkCount / 4 < tCount || greenCount / 4 < zigCount || yellowCount / 4 < squareCount || orangeCount / 4 < lCount) {
                notFound = true;
            }
            if (notFound) {
                playerIn.func_145747_a(new TextComponentString("You have failed to input the correct configuration."));
                return true;
            }
            playerIn.func_145747_a(new TextComponentString("Craft Successful!"));
            playerIn.func_191521_c(new ItemStack(ItemInit.solarumVial, 1));
            worldIn.func_184133_a((EntityPlayer) null, pos, SoundInit.MACHINE_CRAFT, SoundCategory.MASTER, 1.0f, 1.0f);
            reset(upDir2, rightDir2, worldIn, ref2);
            return true;
        }
        return true;
    }
    private void reset(Vec3i upDir, Vec3i rightDir, World worldIn, BlockPos ref) {
        BlockPos checkPos;
        BlockPos checkPos2;
        int count = 0;
        for (int i = 0; i <= 5; i++) {
            BlockPos blockPosFunc_177982_a = ref.func_177982_a(rightDir.func_177958_n() * ((i * 2) + 1), 1, rightDir.func_177952_p() * ((i * 2) + 1));
            while (true) {
                BlockPos clearPos = blockPosFunc_177982_a;
                if (worldIn.func_180495_p(clearPos).func_177230_c() == BlockInit.neocraftGoalEmpty || count > 9) {
                    break;
                }
                count++;
                worldIn.func_175656_a(clearPos, BlockInit.neocraftGoalEmpty.func_176223_P());
                blockPosFunc_177982_a = clearPos.func_177982_a(0, 1, 0);
            }
            count = 0;
        }
        BlockPos blockPosFunc_177973_b = ref.func_177973_b(upDir);
        while (true) {
            checkPos = blockPosFunc_177973_b;
            if (!(worldIn.func_180495_p(checkPos).func_177230_c() instanceof BlockNeocraft)) {
                break;
            } else {
                blockPosFunc_177973_b = checkPos.func_177973_b(upDir);
            }
        }
        BlockPos blockPosFunc_177971_a = checkPos.func_177971_a(upDir);
        while (true) {
            checkPos2 = blockPosFunc_177971_a;
            if (!(worldIn.func_180495_p(checkPos2).func_177230_c() instanceof BlockNeocraft)) {
                break;
            } else {
                blockPosFunc_177971_a = checkPos2.func_177973_b(rightDir);
            }
        }
        BlockPos checkPos3 = checkPos2.func_177971_a(rightDir);
        for (int i2 = 0; i2 < 12; i2++) {
            for (int j = 0; j < 12; j++) {
                BlockPos countPos = checkPos3.func_177982_a((rightDir.func_177958_n() * i2) + (upDir.func_177958_n() * j), 0, (rightDir.func_177952_p() * i2) + (upDir.func_177952_p() * j));
                Block block = worldIn.func_180495_p(countPos).func_177230_c();
                if (block instanceof BlockNeocraft) {
                    worldIn.func_175656_a(countPos, BlockInit.neocraftUnpowered.func_176223_P());
                }
            }
        }
    }
    private boolean findPiece(World worldIn, BlockPos pos, String pieceName) {
        int[][][] orientations;
        Block block;
        orientations = (int[][][]) null;
        block = null;
        switch (pieceName) {
            case "line":
                orientations = linePiece;
                block = BlockInit.neocraftBlue;
                break;
            case "l":
                orientations = lPiece;
                block = BlockInit.neocraftOrange;
                break;
            case "t":
                orientations = tPiece;
                block = BlockInit.neocraftPink;
                break;
            case "square":
                orientations = squarePiece;
                block = BlockInit.neocraftYellow;
                break;
            case "zig":
                orientations = zigPiece;
                block = BlockInit.neocraftGreen;
                break;
        }
        if (orientations == null || block == null) {
            return false;
        }
        for (int[][] orientation : orientations) {
            for (int n = 0; n < orientation[0].length; n++) {
                int startX = orientation[0][n];
                int startZ = orientation[1][n];
                boolean orientationFits = true;
                for (int k = 0; k < orientation[0].length; k++) {
                    int relX = orientation[0][k] - startX;
                    int relZ = orientation[1][k] - startZ;
                    BlockPos checkPos = pos.func_177982_a(relX, 0, relZ);
                    if (worldIn.func_180495_p(checkPos).func_177230_c() != block) {
                        orientationFits = false;
                    }
                }
                if (orientationFits) {
                    return true;
                }
            }
        }
        return false;
    }
}
