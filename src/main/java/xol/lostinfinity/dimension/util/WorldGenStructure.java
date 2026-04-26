package xol.lostinfinity.dimension.util;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;
import xol.lostinfinity.util.Reference;
import xol.lostinfinity.util.load.IStructure;
public class WorldGenStructure extends WorldGenerator implements IStructure {
    public static String structureName;
    public WorldGenStructure(String name) {
        structureName = name;
    }
    public boolean func_180709_b(World worldIn, Random rand, BlockPos position) {
        generateStructure(worldIn, position, Rotation.NONE);
        return true;
    }
    public void generateWithRotation(World worldIn, Random rand, BlockPos position, Rotation rotation) {
        BlockPos new_position = posByRota(position, rotation, templateSize(worldIn));
        generateStructure(worldIn, new_position, rotation);
    }
    public void generateStructure(World world, BlockPos pos, Rotation rot) {
        Template template = loadTemplate(world);
        if (template != null) {
            settings.func_186220_a(rot);
            template.func_186260_a(world, pos, settings);
        }
    }
    public BlockPos templateSize(World world) {
        Template template = loadTemplate(world);
        if (template != null) {
            return template.func_186259_a();
        }
        return null;
    }
    static  class AnonymousClass1 {
        static final  int[] $SwitchMap$net$minecraft$util$Rotation = new int[Rotation.values().length];
        static {
            try {
                $SwitchMap$net$minecraft$util$Rotation[Rotation.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$net$minecraft$util$Rotation[Rotation.CLOCKWISE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$net$minecraft$util$Rotation[Rotation.CLOCKWISE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$net$minecraft$util$Rotation[Rotation.COUNTERCLOCKWISE_90.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }
    private BlockPos posByRota(BlockPos position, Rotation rotation, BlockPos end_pos) {
        switch (AnonymousClass1.$SwitchMap$net$minecraft$util$Rotation[rotation.ordinal()]) {
            case 1:
                return position;
            case 2:
                return position.func_177982_a(end_pos.func_177952_p() - 1, 0, 0);
            case 3:
                return position.func_177982_a(end_pos.func_177958_n() - 1, 0, end_pos.func_177952_p() - 1);
            case TileEntityFusionTable.BOARD_ROWS :
                return position.func_177982_a(0, 0, end_pos.func_177958_n() - 1);
            default:
                return position;
        }
    }
    @Nullable
    private Template loadTemplate(World world) {
        MinecraftServer mcServer = world.func_73046_m();
        TemplateManager manager = worldServer.func_184163_y();
        ResourceLocation location = new ResourceLocation(Reference.MODID, structureName);
        return manager.func_189942_b(mcServer, location);
    }
}
