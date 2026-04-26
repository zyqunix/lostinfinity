package xol.lostinfinity.dimension.data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
public class CustomWorldSavedData extends WorldSavedData {
    private static final String DATA_NAME = "lostinfinity_custom_data";
    private HashMap<Integer, ArrayList<BlockData>> dataMap;
    private ArrayList<Integer> tileIDs;
    private HashMap<Integer, BlockPos> tilePositions;
    public CustomWorldSavedData() {
        super(DATA_NAME);
        this.dataMap = new HashMap<>();
        this.tileIDs = new ArrayList<>();
        this.tilePositions = new HashMap<>();
    }
    public CustomWorldSavedData(String s) {
        super(s);
        this.dataMap = new HashMap<>();
        this.tileIDs = new ArrayList<>();
        this.tilePositions = new HashMap<>();
    }
    public void func_76184_a(NBTTagCompound nbt) {
        if (this.tileIDs.size() > 0) {
            this.tileIDs.clear();
        }
        if (this.tilePositions.size() > 0) {
            this.tilePositions.clear();
        }
        if (this.dataMap.size() > 0) {
            this.dataMap.clear();
        }
        if (nbt.func_74764_b("tileIDs")) {
            int[] tempids = nbt.func_74759_k("tileIDs");
            for (int i : tempids) {
                this.tileIDs.add(Integer.valueOf(i));
            }
        }
        Iterator<Integer> it = this.tileIDs.iterator();
        while (it.hasNext()) {
            int id = it.next().intValue();
            String id_str = String.valueOf(id);
            if (nbt.func_74764_b("tileX" + id_str) && nbt.func_74764_b("tileY" + id_str) && nbt.func_74764_b("tileZ" + id_str)) {
                int tileX = nbt.func_74762_e("tileX" + id_str);
                int tileY = nbt.func_74762_e("tileY" + id_str);
                int tileZ = nbt.func_74762_e("tileZ" + id_str);
                BlockPos tilePos = new BlockPos(tileX, tileY, tileZ);
                this.tilePositions.put(Integer.valueOf(id), tilePos);
                if (nbt.func_74764_b("xPos" + id_str) && nbt.func_74764_b("yPos" + id_str) && nbt.func_74764_b("zPos" + id_str) && nbt.func_74764_b("meta" + id_str) && nbt.func_74764_b("ids" + id_str)) {
                    int[] xPos = nbt.func_74759_k("xPos" + id_str);
                    int[] yPos = nbt.func_74759_k("yPos" + id_str);
                    int[] zPos = nbt.func_74759_k("zPos" + id_str);
                    int[] meta = nbt.func_74759_k("meta" + id_str);
                    int[] ids = nbt.func_74759_k("ids" + id_str);
                    if (xPos.length > 0 && yPos.length > 0 && zPos.length > 0 && meta.length > 0) {
                        ArrayList<BlockData> newData = new ArrayList<>();
                        for (int i2 = 0; i2 < xPos.length; i2++) {
                            if (i2 < yPos.length && i2 < zPos.length && i2 < meta.length) {
                                BlockPos pos = new BlockPos(xPos[i2], yPos[i2], zPos[i2]);
                                BlockData data = new BlockData(pos, meta[i2], ids[i2]);
                                newData.add(data);
                            } else {
                                return;
                            }
                        }
                        if (newData.size() > 1) {
                            this.dataMap.put(Integer.valueOf(id), newData);
                        }
                    }
                }
            }
        }
    }
    public NBTTagCompound func_189551_b(NBTTagCompound compound) {
        int[] tileids = new int[this.tileIDs.size()];
        for (int j = 0; j < this.tileIDs.size(); j++) {
            int id = this.tileIDs.get(j).intValue();
            tileids[j] = id;
            String id_str = String.valueOf(id);
            ArrayList<BlockData> block_data = this.dataMap.get(Integer.valueOf(id));
            if (this.tilePositions.containsKey(Integer.valueOf(id))) {
                BlockPos tilePos = this.tilePositions.get(Integer.valueOf(id));
                compound.func_74768_a("tileX" + id_str, tilePos.func_177958_n());
                compound.func_74768_a("tileY" + id_str, tilePos.func_177956_o());
                compound.func_74768_a("tileZ" + id_str, tilePos.func_177952_p());
                compound.func_74783_a("tileIDs", tileids);
                if (block_data != null && block_data.size() > 0) {
                    int[] xPos = new int[block_data.size()];
                    int[] yPos = new int[block_data.size()];
                    int[] zPos = new int[block_data.size()];
                    int[] meta = new int[block_data.size()];
                    int[] ids = new int[block_data.size()];
                    for (int i = 0; i < block_data.size(); i++) {
                        BlockData data = block_data.get(i);
                        if (data != null) {
                            BlockPos pos = data.getPos();
                            int blockid = data.getId();
                            int metaNum = data.getMeta();
                            if (pos != null) {
                                xPos[i] = pos.func_177958_n();
                                yPos[i] = pos.func_177956_o();
                                zPos[i] = pos.func_177952_p();
                                meta[i] = metaNum;
                                ids[i] = blockid;
                            }
                        }
                    }
                    compound.func_74783_a("xPos" + id_str, xPos);
                    compound.func_74783_a("yPos" + id_str, yPos);
                    compound.func_74783_a("zPos" + id_str, zPos);
                    compound.func_74783_a("meta" + id_str, meta);
                    compound.func_74783_a("ids" + id_str, ids);
                }
            }
        }
        return compound;
    }
    public static CustomWorldSavedData get(World world) {
        MapStorage storage = world.getPerWorldStorage();
        CustomWorldSavedData instance = (CustomWorldSavedData) storage.func_75742_a(CustomWorldSavedData.class, DATA_NAME);
        if (instance == null) {
            instance = new CustomWorldSavedData();
            storage.func_75745_a(DATA_NAME, instance);
        }
        return instance;
    }
    public void setBlockData(ArrayList<BlockData> block_data, BlockPos pos) {
        int randId;
        if (this.tilePositions.containsValue(pos)) {
            Iterator<Integer> it = this.tileIDs.iterator();
            while (it.hasNext()) {
                int id = it.next().intValue();
                BlockPos tilePos = this.tilePositions.get(Integer.valueOf(id));
                if (tilePos != null && tilePos.equals(pos)) {
                    this.dataMap.put(Integer.valueOf(id), block_data);
                }
            }
        } else {
            Random rand = new Random();
            int iNextInt = rand.nextInt();
            while (true) {
                randId = iNextInt;
                if (!this.tileIDs.contains(Integer.valueOf(randId)) && randId != -1) {
                    break;
                } else {
                    iNextInt = rand.nextInt();
                }
            }
            this.tileIDs.add(Integer.valueOf(randId));
            this.tilePositions.put(Integer.valueOf(randId), pos);
            this.dataMap.put(Integer.valueOf(randId), block_data);
        }
        func_76185_a();
    }
    public ArrayList<BlockData> getBlockData(BlockPos pos) {
        if (this.tilePositions.containsValue(pos)) {
            Iterator<Integer> it = this.tileIDs.iterator();
            while (it.hasNext()) {
                int id = it.next().intValue();
                BlockPos tilePos = this.tilePositions.get(Integer.valueOf(id));
                if (tilePos != null && tilePos.equals(pos)) {
                    return this.dataMap.get(Integer.valueOf(id));
                }
            }
            return null;
        }
        return null;
    }
    public void clearBlockData(BlockPos pos) {
        if (this.tilePositions.containsValue(pos)) {
            int toRemove = -1;
            Iterator<Integer> it = this.tileIDs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int id = it.next().intValue();
                BlockPos tilePos = this.tilePositions.get(Integer.valueOf(id));
                if (tilePos != null && tilePos.equals(pos)) {
                    toRemove = id;
                    break;
                }
            }
            if (toRemove != -1) {
                this.dataMap.remove(Integer.valueOf(toRemove));
                this.tilePositions.remove(Integer.valueOf(toRemove));
                this.tileIDs.remove(Integer.valueOf(toRemove));
            }
        }
    }
}
