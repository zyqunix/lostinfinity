package xol.lostinfinity.util.execute;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/execute/JsonParser.class */
public class JsonParser {
    private PrintWriter pw;

    public JsonParser(String path, String name) {
        try {
            File parentDirectory = new File(path);
            File file = new File(parentDirectory, name);
            FileWriter fw = new FileWriter(file, false);
            this.pw = new PrintWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteKeyValue(JsonNode node, Boolean comma, Integer level, boolean compress) {
        if (node.hasChildren()) {
            Integer.valueOf(level.intValue() + 1);
            WriteNode(node, comma, level, compress);
            return;
        }
        if (node.hasSubNodes()) {
            node.setHasSubNodes(false);
            this.pw.print(String.format("\t\"%s\":", node.getKey()));
            List<JsonNode> subNodes = node.getSubNodes();
            this.pw.println("[");
            for (JsonNode subNode : subNodes) {
                String key = subNode.getKey();
                String value = subNode.getValue();
                String subKey = subNode.getSubKey();
                String subValue = subNode.getSubValue();
                String line = String.format("\t\t{ \"%s\": \"%s\", \"%s\": %s }", key, value, subKey, subValue);
                if (subNodes.indexOf(subNode) != subNodes.size() - 1) {
                    line = line + ",";
                }
                this.pw.println(line);
            }
            if (comma.booleanValue()) {
                this.pw.println("\t\t],");
                return;
            } else {
                this.pw.println("\t\t]");
                return;
            }
        }
        String key2 = node.getKey();
        String value2 = node.getValue();
        if (value2 == null) {
            String.format("\t\"%s\":", key2);
        }
        String line2 = String.format("\t\"%s\": \"%s\"", key2, value2);
        if (comma.booleanValue()) {
            line2 = line2 + ",";
        }
        this.pw.println(line2);
    }

    public void WriteNode(JsonNode node, Boolean comma, Integer level, boolean compress) {
        if (!node.hasChildren()) {
            WriteKeyValue(node, comma, level, compress);
            return;
        }
        String key = node.getKey();
        if (level.intValue() > 0 && compress) {
            String line = String.format("\"%s\": {", key);
            this.pw.print(line);
        } else {
            String line2 = String.format("\t\"%s\": {", key);
            this.pw.println(line2);
        }
        List<JsonNode> nodeList = node.getNodes();
        for (int i = 0; i < nodeList.size(); i++) {
            JsonNode node2 = nodeList.get(i);
            if (i == nodeList.size() - 1) {
                this.pw.print("\t");
                WriteKeyValue(node2, false, level, compress);
            } else {
                this.pw.print("\t");
                WriteKeyValue(node2, true, level, compress);
            }
        }
        if (level.intValue() > 0 && compress) {
            if (comma.booleanValue()) {
                this.pw.print(" },");
                return;
            } else {
                this.pw.print(" }");
                return;
            }
        }
        if (comma.booleanValue()) {
            this.pw.println("\t},");
        } else {
            this.pw.println("\t}");
        }
    }

    public void WriteJson(JsonObject json) {
        List<JsonNode> nodes = json.getNodes();
        this.pw.println("{");
        for (int i = 0; i < nodes.size(); i++) {
            JsonNode node = nodes.get(i);
            if (i == nodes.size() - 1) {
                WriteNode(node, false, 0, false);
            } else {
                WriteNode(node, true, 0, false);
            }
        }
        this.pw.print("}");
        this.pw.close();
    }

    public void WriteJson(JsonObject json, boolean compress) {
        List<JsonNode> nodes = json.getNodes();
        this.pw.println("{");
        for (int i = 0; i < nodes.size(); i++) {
            JsonNode node = nodes.get(i);
            if (i == nodes.size() - 1) {
                WriteNode(node, false, 0, compress);
            } else {
                WriteNode(node, true, 0, compress);
            }
        }
        this.pw.print("}");
        this.pw.close();
    }
}
