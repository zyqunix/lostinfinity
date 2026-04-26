package xol.lostinfinity.common.special;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import xol.lostinfinity.mob.entity.minion.EntityMinion;
public class CommonMinionHandler {
    private static CommonMinionHandler INSTANCE;
    private final Map<UUID, Set<EntityMinion>> minionMaps = new ConcurrentHashMap();
    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new CommonMinionHandler();
        }
    }
    public static void registerMinion(UUID player, EntityMinion minion) {
        Set<EntityMinion> minionSet = INSTANCE.minionMaps.computeIfAbsent(player, k -> {
            return new HashSet();
        });
        minionSet.add(minion);
    }
    public static void unregisterMinion(UUID player, EntityMinion minion) {
        Set<EntityMinion> minionSet = INSTANCE.minionMaps.computeIfAbsent(player, k -> {
            return new HashSet();
        });
        minionSet.remove(minion);
    }
    public static void unregisterAll(UUID player) {
        Set<EntityMinion> minionSet = INSTANCE.minionMaps.get(player);
        if (minionSet == null || minionSet.isEmpty()) {
            return;
        }
        minionSet.forEach((v0) -> {
            v0.setDeadNoTrigger();
        });
        minionSet.clear();
    }
    public static Set<EntityMinion> getMinions(UUID player) {
        return INSTANCE.minionMaps.computeIfAbsent(player, k -> {
            return new HashSet();
        });
    }
    private CommonMinionHandler() {
    }
}
