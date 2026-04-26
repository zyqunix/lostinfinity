package xol.lostinfinity.util.execute;
import java.util.ArrayList;
import java.util.List;
public class JsonObject {
    private List<JsonNode> nodes = new ArrayList();
    public JsonObject(String key, String value) {
        JsonNode node = new JsonNode(key, value);
        this.nodes.add(node);
    }
    public JsonObject() {
    }
    public JsonNode add(JsonNode node) {
        this.nodes.add(node);
        return node;
    }
    public List<JsonNode> getNodes() {
        return this.nodes;
    }
}
