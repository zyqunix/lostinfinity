package xol.lostinfinity.util.load;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/load/IStructure.class */
public interface IStructure {
    public static final WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0);
    public static final PlacementSettings settings = new PlacementSettings().func_186218_a((ChunkPos) null).func_186222_a(false).func_186226_b(false).func_186214_a(Mirror.NONE).func_186220_a(Rotation.NONE);
}
