package xol.lostinfinity.util.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import xol.lostinfinity.block.tileentity.TileEntityFusionTable;

/* JADX INFO: loaded from: lostinfinity-1.16.4.jar:xol/lostinfinity/util/execute/GenBasicJson.class */
public class GenBasicJson {
    private static String assetPath = "src/main/resources/assets/lostinfinity/";

    /* JADX INFO: Infinite loop detected, blocks: 109, insns: 0 */
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.print("GenBasicJson >> ");
            String line = myObj.nextLine();
            String[] split = line.split("\\s+");
            if (split.length > 1 && split.length < 6) {
                String name = split[1];
                if (split[0].equals("i")) {
                    GenBasicItem(name);
                } else if (split[0].equals("b")) {
                    switch (split.length) {
                        case 2:
                            GenBasicBlock(name, 0, "cube");
                            break;
                        case 3:
                            try {
                                Integer num = Integer.valueOf(Integer.parseInt(split[2]));
                                GenBasicBlock(name, num, "cube");
                            } catch (NumberFormatException e) {
                                String shape = split[2];
                                GenBasicBlock(name, 0, shape);
                            }
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            try {
                                Integer num2 = Integer.valueOf(Integer.parseInt(split[2]));
                                String shape2 = split[3];
                                GenBasicBlock(name, num2, shape2);
                            } catch (NumberFormatException e2) {
                                Integer num3 = Integer.valueOf(Integer.parseInt(split[3]));
                                String shape3 = split[2];
                                GenBasicBlock(name, num3, shape3);
                            }
                            break;
                    }
                } else if (split[0].equals("bb")) {
                    String activeName = split[2];
                    switch (split.length) {
                        case 3:
                            GenBasicBlockBoolean(name, activeName, "cube", 0);
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            try {
                                Integer num4 = Integer.valueOf(Integer.parseInt(split[3]));
                                GenBasicBlockBoolean(name, activeName, "cube", num4);
                            } catch (NumberFormatException e3) {
                                String shape4 = split[3];
                                GenBasicBlockBoolean(name, activeName, shape4, 0);
                            }
                            break;
                        case 5:
                            try {
                                Integer num5 = Integer.valueOf(Integer.parseInt(split[3]));
                                String shape5 = split[4];
                                GenBasicBlockBoolean(name, activeName, shape5, num5);
                            } catch (NumberFormatException e4) {
                                Integer num6 = Integer.valueOf(Integer.parseInt(split[4]));
                                String shape6 = split[3];
                                GenBasicBlockBoolean(name, activeName, shape6, num6);
                            }
                            break;
                    }
                } else if (split[0].equals("ba")) {
                    switch (split.length) {
                        case 3:
                            int amount = Integer.parseInt(split[2]);
                            GenBasicBlockAmount(name, 0, "cube", amount);
                            break;
                        case TileEntityFusionTable.BOARD_ROWS /* 4 */:
                            try {
                                Integer num7 = Integer.valueOf(Integer.parseInt(split[3]));
                                int amount2 = Integer.parseInt(split[2]);
                                GenBasicBlockAmount(name, num7, "cube", amount2);
                            } catch (NumberFormatException e5) {
                                String shape7 = split[2];
                                int amount3 = Integer.parseInt(split[3]);
                                GenBasicBlockAmount(name, 0, shape7, amount3);
                            }
                            break;
                        case 5:
                            try {
                                int amount4 = Integer.parseInt(split[2]);
                                Integer num8 = Integer.valueOf(Integer.parseInt(split[4]));
                                String shape8 = split[3];
                                GenBasicBlockAmount(name, num8, shape8, amount4);
                            } catch (NumberFormatException e6) {
                                int amount5 = Integer.parseInt(split[3]);
                                Integer num9 = Integer.valueOf(Integer.parseInt(split[4]));
                                String shape9 = split[2];
                                GenBasicBlockAmount(name, num9, shape9, amount5);
                            }
                            break;
                    }
                } else if (split[0].equals("s")) {
                    if (split.length == 2) {
                        GenSound(name);
                    }
                } else if (split[0].equals("p") && split.length == 2) {
                    GenParticle(name);
                }
            } else {
                System.out.println("Incorrect num arguments");
            }
        }
    }

    private static void GenParticle(String name) {
        String capsName = name.toUpperCase();
        String spriteEntry = String.format("\tpublic static TextureAtlasSprite %s_SPRITE;", capsName);
        String particleRegEntry = String.format("\tpublic static final EnumParticleTypes %s = particleRegistry(\"%s\");", capsName, name);
        String spriteRegEntry = String.format("\t\t%s_SPRITE = ev.getMap().registerSprite(new ResourceLocation(Reference.MODID, \"particles/%s\"));", capsName, name);
        String particleClassName = firstCase(name);
        String particleRenderRegEntry = String.format("\t\tMinecraft.getMinecraft().effectRenderer.registerParticle(%s.getParticleID(), new Particle%s.Factory());", capsName, particleClassName);
        try {
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/ParticleInit.java", spriteEntry, "public static TextureAtlasSprite FIREGOO_SPRITE;");
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/ParticleInit.java", particleRegEntry, "public static final EnumParticleTypes VENOM = particleRegistry(\"venom\");");
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/ParticleInit.java", spriteRegEntry, "FIREGOO_SPRITE = ev.getMap().registerSprite(new ResourceLocation(Reference.MODID, \"particles/firegoo\"));");
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/ParticleInit.java", particleRenderRegEntry, "Minecraft.getMinecraft().effectRenderer.registerParticle(FIREGOO.getParticleID(), new ParticleFiregoo.Factory());");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void GenSound(String name) {
        String soundEntry = String.format("\t\"%s\":{ \"category\":\"master\", \"sounds\":[ \"lostinfinity:%s\" ] },", name, name);
        writeBeforeLineNumber("src/main/resources/assets/lostinfinity/sounds.json", 2, soundEntry);
        String capsName = name.toUpperCase();
        String soundInit = String.format("\t public static final SoundEvent %s = registerCustomSound(\"%s\");", capsName, name);
        writeBeforeLineNumber("src/main/java/xol/lostinfinity/init/SoundInit.java", 18, soundInit);
    }

    private static void writeBeforeLineNumber(String filename, int lineNum, String text) {
        try {
            File file = new File(filename);
            File temp = File.createTempFile("temp-file-name", ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(temp));
            int num = 0;
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    num++;
                    if (num == lineNum) {
                        pw.println(text);
                    }
                    pw.println(line);
                } else {
                    br.close();
                    pw.close();
                    file.delete();
                    temp.renameTo(file);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void GenBasicBlock(String name, Integer num, String shape) {
        String numStr;
        String string;
        String blockPath = assetPath + "models/block/";
        String itemPath = assetPath + "models/item/";
        String blockStatePath = assetPath + "blockstates/";
        JsonParser itemJsonParser = new JsonParser(itemPath, name + ".json");
        String itemTexture = String.format("lostinfinity:block/%s", name);
        JsonObject itemJson = new JsonObject("parent", itemTexture);
        itemJsonParser.WriteJson(itemJson);
        JsonParser blockStateJsonParser = new JsonParser(blockStatePath, name + ".json");
        JsonObject blockStateJson = new JsonObject();
        List<JsonNode> variantSubNodes = new ArrayList<>();
        if (num.intValue() == 0) {
            num = 1;
        }
        for (int i = 1; i <= num.intValue(); i++) {
            if (i == 1) {
                string = "";
            } else {
                string = Integer.toString(i);
            }
            String numStr2 = string;
            String blockModel = String.format("lostinfinity:%s", name + numStr2);
            JsonNode subNode = new JsonNode("model", blockModel);
            subNode.setSubKey("weight");
            subNode.setSubValue("100");
            variantSubNodes.add(subNode);
        }
        JsonNode variants = new JsonNode("variants");
        JsonNode normalVariant = new JsonNode("normal");
        if (variantSubNodes.size() > 1) {
            normalVariant.setSubNodes(variantSubNodes);
        } else {
            normalVariant.addNode(variantSubNodes.get(0));
        }
        variants.addNode(normalVariant);
        blockStateJson.add(variants);
        blockStateJsonParser.WriteJson(blockStateJson);
        if (num.intValue() == 0) {
            num = 1;
        }
        for (int i2 = 1; i2 <= num.intValue(); i2++) {
            if (i2 == 1) {
                numStr = "";
            } else {
                numStr = Integer.toString(i2);
            }
            JsonParser blockJsonParser = new JsonParser(blockPath, name + numStr + ".json");
            String shapeName = shape;
            String shapeAlias = shape;
            if (shape.equals("cube")) {
                shapeName = "cube_all";
                shapeAlias = "all";
            }
            JsonObject blockJson = new JsonObject("parent", "block/" + shapeName);
            JsonNode node = new JsonNode("textures");
            String blockTexture = String.format("lostinfinity:blocks/%s", name + numStr);
            JsonNode child = new JsonNode(shapeAlias, blockTexture);
            node.addNode(child);
            blockJson.add(node);
            blockJsonParser.WriteJson(blockJson);
        }
        String camelName = camelCase(name);
        System.out.println(camelName);
        String blockInit = String.format("\tpublic static final Block %s = new BlockBasic(\"%s\");", camelName, name);
        try {
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/BlockInit.java", blockInit, "Append basic blocks here");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void GenBasicBlockAmount(String name, Integer num, String shape, int amount) {
        String numStr;
        String str;
        String blockPath = assetPath + "models/block/";
        String itemPath = assetPath + "models/item/";
        String blockStatePath = assetPath + "blockstates/";
        JsonParser itemJsonParser = new JsonParser(itemPath, name + ".json");
        String itemTexture = String.format("lostinfinity:block/%s_0", name);
        JsonObject itemJson = new JsonObject("parent", itemTexture);
        itemJsonParser.WriteJson(itemJson);
        JsonParser blockStateJsonParser = new JsonParser(blockStatePath, name + ".json");
        JsonObject blockStateJson = new JsonObject();
        JsonNode variants = new JsonNode("variants");
        for (int i = 0; i <= amount; i++) {
            String amStr = Integer.toString(i);
            JsonNode varHeader = new JsonNode(String.format("amount=%s", amStr));
            if (num.intValue() <= 1) {
                String blockModel = String.format("lostinfinity:%s", name + "_" + amStr);
                varHeader.addNode(new JsonNode("model", blockModel));
            } else {
                List<JsonNode> variantSubNodes = new ArrayList<>();
                if (num.intValue() == 0) {
                    num = 1;
                }
                for (int j = 1; j <= num.intValue(); j++) {
                    if (j == 1) {
                        str = "";
                    } else {
                        str = "_" + Integer.toString(j);
                    }
                    String numStr2 = str;
                    String blockModel2 = String.format("lostinfinity:%s", name + "_" + amStr + numStr2);
                    JsonNode subNode = new JsonNode("model", blockModel2);
                    subNode.setSubKey("weight");
                    subNode.setSubValue("100");
                    variantSubNodes.add(subNode);
                }
                varHeader.setSubNodes(variantSubNodes);
            }
            variants.addNode(varHeader);
        }
        blockStateJson.add(variants);
        blockStateJsonParser.WriteJson(blockStateJson);
        for (int i2 = 0; i2 <= amount; i2++) {
            if (num.intValue() <= 1) {
                String amStr2 = Integer.toString(i2);
                JsonParser blockJsonParser = new JsonParser(blockPath, name + "_" + amStr2 + ".json");
                String shapeName = shape;
                String shapeAlias = shape;
                if (shape.equals("cube")) {
                    shapeName = "cube_all";
                    shapeAlias = "all";
                }
                JsonObject blockJson = new JsonObject("parent", "block/" + shapeName);
                JsonNode node = new JsonNode("textures");
                String blockTexture = String.format("lostinfinity:blocks/%s", name + "_" + amStr2);
                JsonNode child = new JsonNode(shapeAlias, blockTexture);
                node.addNode(child);
                blockJson.add(node);
                blockJsonParser.WriteJson(blockJson);
            } else {
                if (num.intValue() == 0) {
                    num = 1;
                }
                for (int j2 = 1; j2 <= num.intValue(); j2++) {
                    if (j2 == 1) {
                        numStr = "";
                    } else {
                        numStr = "_" + Integer.toString(j2);
                    }
                    String amStr3 = Integer.toString(i2);
                    JsonParser blockJsonParser2 = new JsonParser(blockPath, name + "_" + amStr3 + numStr + ".json");
                    String shapeName2 = shape;
                    String shapeAlias2 = shape;
                    if (shape.equals("cube")) {
                        shapeName2 = "cube_all";
                        shapeAlias2 = "all";
                    }
                    JsonObject blockJson2 = new JsonObject("parent", "block/" + shapeName2);
                    JsonNode node2 = new JsonNode("textures");
                    String blockTexture2 = String.format("lostinfinity:blocks/%s", name + "_" + amStr3 + numStr);
                    JsonNode child2 = new JsonNode(shapeAlias2, blockTexture2);
                    node2.addNode(child2);
                    blockJson2.add(node2);
                    blockJsonParser2.WriteJson(blockJson2);
                }
            }
        }
        String camelName = camelCase(name);
        System.out.println(camelName);
        String blockInit = String.format("\tpublic static final Block %s = new BlockBasic(\"%s\");", camelName, name);
        try {
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/BlockInit.java", blockInit, "Append basic blocks here");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void GenBasicBlockBoolean(String name, String activeName, String shape, Integer num) {
        String numStr;
        String string;
        String blockPath = assetPath + "models/block/";
        String itemPath = assetPath + "models/item/";
        String blockStatePath = assetPath + "blockstates/";
        JsonParser itemJsonParser = new JsonParser(itemPath, name + ".json");
        String itemTexture = String.format("lostinfinity:block/%s", name);
        JsonObject itemJson = new JsonObject("parent", itemTexture);
        itemJsonParser.WriteJson(itemJson);
        JsonParser blockStateJsonParser = new JsonParser(blockStatePath, name + ".json");
        JsonObject blockStateJson = new JsonObject();
        List<JsonNode> trueVariantSubNodes = new ArrayList<>();
        List<JsonNode> falseVariantSubNodes = new ArrayList<>();
        JsonNode variants = new JsonNode("variants");
        JsonNode trueHeader = new JsonNode("active=true");
        JsonNode falseHeader = new JsonNode("active=false");
        JsonNode trueBody = new JsonNode("model", String.format("lostinfinity:%s_%s", name, activeName));
        JsonNode falseBody = new JsonNode("model", String.format("lostinfinity:%s", name));
        if (num.intValue() == 0) {
            num = 1;
        }
        for (int i = 1; i <= num.intValue(); i++) {
            if (i == 1) {
                string = "";
            } else {
                string = Integer.toString(i);
            }
            String numStr2 = string;
            String falseBlockModel = String.format("lostinfinity:%s", name + numStr2);
            JsonNode falseSubNode = new JsonNode("model", falseBlockModel);
            falseSubNode.setSubKey("weight");
            falseSubNode.setSubValue("100");
            falseVariantSubNodes.add(falseSubNode);
            String trueBlockModel = String.format("lostinfinity:%s", name + "_" + activeName + numStr2);
            JsonNode trueSubNode = new JsonNode("model", trueBlockModel);
            trueSubNode.setSubKey("weight");
            trueSubNode.setSubValue("100");
            trueVariantSubNodes.add(trueSubNode);
        }
        if (trueVariantSubNodes.size() > 1) {
            trueHeader.setSubNodes(trueVariantSubNodes);
        } else {
            trueHeader.addNode(trueBody);
        }
        if (falseVariantSubNodes.size() > 1) {
            falseHeader.setSubNodes(falseVariantSubNodes);
        } else {
            falseHeader.addNode(falseBody);
        }
        variants.addNode(trueHeader);
        variants.addNode(falseHeader);
        blockStateJson.add(variants);
        blockStateJsonParser.WriteJson(blockStateJson, true);
        if (num.intValue() == 0) {
            num = 1;
        }
        for (int i2 = 1; i2 <= num.intValue(); i2++) {
            if (i2 == 1) {
                numStr = "";
            } else {
                numStr = Integer.toString(i2);
            }
            JsonParser blockJsonParserTrue = new JsonParser(blockPath, name + "_" + activeName + numStr + ".json");
            String shapeName = shape;
            String shapeAlias = shape;
            if (shape.equals("cube")) {
                shapeName = "cube_all";
                shapeAlias = "all";
            }
            JsonObject blockJson = new JsonObject("parent", "block/" + shapeName);
            JsonNode node = new JsonNode("textures");
            String blockTexture = String.format("lostinfinity:blocks/%s", name + "_" + activeName + numStr);
            JsonNode child = new JsonNode(shapeAlias, blockTexture);
            node.addNode(child);
            blockJson.add(node);
            blockJsonParserTrue.WriteJson(blockJson);
            JsonParser blockJsonParserFalse = new JsonParser(blockPath, name + numStr + ".json");
            if (shape.equals("cube")) {
                shapeName = "cube_all";
                shapeAlias = "all";
            }
            JsonObject blockJson2 = new JsonObject("parent", "block/" + shapeName);
            JsonNode node2 = new JsonNode("textures");
            String blockTexture2 = String.format("lostinfinity:blocks/%s", name + numStr);
            JsonNode child2 = new JsonNode(shapeAlias, blockTexture2);
            node2.addNode(child2);
            blockJson2.add(node2);
            blockJsonParserFalse.WriteJson(blockJson2);
        }
        String camelName = camelCase(name);
        System.out.println(camelName);
        String blockInit = String.format("\tpublic static final Block %s = new BlockBasicBoolState(\"%s\");", camelName, name);
        try {
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/BlockInit.java", blockInit, "Append basic blocks here");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void GenBasicItem(String name) {
        String itemPath = assetPath + "models/item/";
        JsonParser jsonParser = new JsonParser(itemPath, name + ".json");
        JsonObject json = new JsonObject("parent", "item/generated");
        JsonNode node = new JsonNode("textures");
        String itemTexture = String.format("lostinfinity:items/%s", name);
        JsonNode child = new JsonNode("layer0", itemTexture);
        node.addNode(child);
        json.add(node);
        jsonParser.WriteJson(json);
        String camelName = camelCase(name);
        System.out.println(camelName);
        String itemInit = String.format("\tpublic static final Item %s = new ItemBasic(\"%s\", TabsInit.TAB_AUXMATS);", camelName, name);
        try {
            writeBeforeAnnotatedLine("src/main/java/xol/lostinfinity/init/ItemInit.java", itemInit, "Append basic items here");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String camelCase(String name) {
        String str;
        String camel = "";
        int i = 0;
        while (i < name.length()) {
            if (i == 0) {
                str = camel + name.charAt(0);
            } else if (name.charAt(i) == '_') {
                i++;
                str = camel + Character.toUpperCase(name.charAt(i));
            } else {
                str = camel + name.charAt(i);
            }
            camel = str;
            i++;
        }
        return camel;
    }

    public static String firstCase(String name) {
        String str;
        String cased = "";
        int i = 0;
        while (i < name.length()) {
            if (i == 0) {
                str = cased + Character.toUpperCase(name.charAt(0));
            } else if (name.charAt(i) == '_') {
                i++;
                str = cased + Character.toUpperCase(name.charAt(i));
            } else {
                str = cased + name.charAt(i);
            }
            cased = str;
            i++;
        }
        return cased;
    }

    public static void writeBeforeAnnotatedLine(String filename, String text, String annotation) throws IOException {
        try {
            File file = new File(filename);
            File temp = File.createTempFile("temp-file-name", ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(temp));
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    if (line.contains(annotation)) {
                        pw.println(text);
                    }
                    pw.println(line);
                } else {
                    br.close();
                    pw.close();
                    file.delete();
                    temp.renameTo(file);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
