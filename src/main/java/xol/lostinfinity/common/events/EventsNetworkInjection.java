package xol.lostinfinity.common.events;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import xol.lostinfinity.common.packets.LostInfinityPacketListener;
public class EventsNetworkInjection {
    public static final String LISTENER = "lost_infinity_packet_listener";
    @SubscribeEvent
    public void onClientDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        NetHandlerPlayServer connection;
        if (!(event.player instanceof EntityPlayerMP) || (connection = event.player.field_71135_a) == null) {
            return;
        }
        Channel channel = connection.field_147371_a.channel();
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(LISTENER);
            return null;
        });
    }
    @SubscribeEvent
    public void onClientConnect(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            NetHandlerPlayServer connection = event.player.field_71135_a;
            ChannelPipeline pipeline = connection.field_147371_a.channel().pipeline();
            LostInfinityPacketListener listener = new LostInfinityPacketListener(connection);
            for (String name : pipeline.toMap().keySet()) {
                if (pipeline.get(name) instanceof NetworkManager) {
                    pipeline.addBefore(name, LISTENER, listener);
                    return;
                }
            }
        }
    }
}
