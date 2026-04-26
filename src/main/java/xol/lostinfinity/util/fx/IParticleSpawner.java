package xol.lostinfinity.util.fx;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import xol.lostinfinity.common.lostinfinity;
import xol.lostinfinity.common.packets.clientbound.PacketModParticle;
import xol.lostinfinity.util.data.CustomParticleConfig;
public interface IParticleSpawner {
    static void spawnParticle(World world, int id, int extra, Vec3d pos) {
        spawnParticle(world, id, extra, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
    }
    static void spawnParticle(World world, int id, int extra, double x, double y, double z) {
        lostinfinity.instance.packetHandler.sendToAllAround(new NetworkRegistry.TargetPoint(world.field_73011_w.getDimension(), x, y, z, 90.0d), new PacketModParticle(id, extra, x, y, z));
    }
    static void spawnParticle(EntityPlayer player, int id, int extra, Vec3d pos) {
        spawnParticle(player, id, extra, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
    }
    static void spawnParticle(EntityPlayer player, int id, int extra, double x, double y, double z) {
        if (player instanceof EntityPlayerMP) {
            lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) player, new PacketModParticle(id, extra, x, y, z));
        }
    }
    static void spawnParticle(World world, CustomParticleConfig config, Vec3d pos) {
        spawnParticle(world, config, pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
    }
    static void spawnParticle(World world, CustomParticleConfig config, double x, double y, double z) {
        config.setOrigin(x, y, z);
        lostinfinity.instance.packetHandler.sendToAllAround(new NetworkRegistry.TargetPoint(world.field_73011_w.getDimension(), x, y, z, 90.0d), new PacketModParticle(config));
    }
    static void spawnParticle(EntityPlayer player, CustomParticleConfig config, double x, double y, double z) {
        if (player instanceof EntityPlayerMP) {
            config.setOrigin(x, y, z);
            lostinfinity.instance.packetHandler.sendToPlayer((EntityPlayerMP) player, new PacketModParticle(config));
        }
    }
}
