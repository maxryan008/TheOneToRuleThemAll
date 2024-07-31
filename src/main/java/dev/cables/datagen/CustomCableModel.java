package dev.cables.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;

import java.util.Map;
import java.util.Optional;

public class CustomCableModel extends Model {
    private final JsonArray elements;

    public CustomCableModel(Optional<Identifier> parent, Optional<String> suffix, JsonArray elements, TextureKey... requiredTextures) {
        super(parent, suffix, requiredTextures);
        this.elements = elements;
    }

    public CustomCableModel elements(JsonArray elements) {
        this.elements.addAll(elements);
        return this;
    }

    @Override
    public JsonObject createJson(Identifier id, Map<TextureKey, Identifier> textures) {
        JsonObject model = new JsonObject();
        JsonObject tex = new JsonObject();
        tex.addProperty("particle", "#texture");
        model.add("textures", tex);
        model.add("elements", elements);
        return model;
    }

    public static JsonArray createCenterElements(int size) {
        JsonArray elements = new JsonArray();
        JsonObject model = new JsonObject();
        Vec3i from = new Vec3i(8 - (size / 2), 8 - (size / 2), 8 - (size / 2));
        Vec3i to = new Vec3i(8 + (size / 2), 8 + (size / 2), 8 + (size / 2));
        model.add("faces", createFacesJson(from, to, size, true));
        model.add("to", createJsonArray(to.getX(), to.getY(), to.getZ()));
        model.add("from", createJsonArray(from.getX(), from.getY(), from.getZ()));
        model.addProperty("name", "center");

        elements.add(model);
        return elements;
    }

    public static JsonArray createDirectionalElements(Vec3i corner1, Vec3i corner2) {
        JsonArray elements = new JsonArray();

        JsonObject part = new JsonObject();
        part.addProperty("name", "part");
        part.add("from", createJsonArray(corner1.getX(), corner1.getY(), corner1.getZ()));
        part.add("to", createJsonArray(corner2.getX(), corner2.getY(), corner2.getZ()));
        part.add("faces", createFacesJson(corner1, corner2, 4, false));
        elements.add(part);

        return elements;
    }

    public static JsonArray createInventoryElements() {
        JsonArray elements = new JsonArray();

        JsonObject inventory = new JsonObject();
        inventory.addProperty("name", "inventory");
        inventory.add("from", createJsonArray(6, 6, 0));
        inventory.add("to", createJsonArray(10, 10, 16));
        inventory.add("faces", createFacesJson(new Vec3i(6, 6, 0), new Vec3i(10, 10, 16), 4, false));
        elements.add(inventory);

        return elements;
    }


    private static JsonArray createJsonArray(int... values) {
        JsonArray array = new JsonArray();
        for (int value : values) {
            array.add(value);
        }
        return array;
    }

    private static JsonObject createFacesJson(Vec3i corner1, Vec3i corner2, int size, boolean center) {
        JsonObject faces = new JsonObject();
        int x1 = corner1.getX();
        int y1 = corner1.getY();
        int z1 = corner1.getZ();
        int x2 = corner2.getX();
        int y2 = corner2.getY();
        int z2 = corner2.getZ();

        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        int depth = Math.abs(z2 - z1);

        if (center)
        {
            faces.add("north", createFaceJson(0, 0, width, height, "north", true));
            faces.add("east", createFaceJson(0, 0, depth, height, "east", true));
            faces.add("south", createFaceJson(0, 0, width, height, "south", true));
            faces.add("west", createFaceJson(0, 0, depth, height, "west", true));
            faces.add("up", createFaceJson(0, 0, width, depth, "up", true));
            faces.add("down", createFaceJson(0, 0, width, depth, "down", true));
        }else
        {
            if (z1 != 8 + (size / 2)) faces.add("north", createFaceJson(0, 0, width, height, "north", z1 == 0));
            if (x2 != 8 - (size / 2)) faces.add("east", createFaceJson(0, 0, depth, height, "east", x2 == 16));
            if (z2 != 8 - (size / 2)) faces.add("south", createFaceJson(0, 0, width, height, "south", z2 == 16));
            if (x1 != 8 + (size / 2)) faces.add("west", createFaceJson(0, 0, depth, height, "west", x1 == 0));
            if (y2 != 8 - (size / 2)) faces.add("up", createFaceJson(0, 0, width, depth, "up", y2 == 16));
            if (y1 != 8 + (size / 2)) faces.add("down", createFaceJson(0, 0, width, depth, "down", y1 == 0));
        }

        return faces;
    }

    private static JsonObject createFaceJson(int uv1, int uv2, int uv3, int uv4, String face, boolean cull) {
        JsonObject jsonFace = new JsonObject();

        JsonArray uv = new JsonArray();
        uv.add(uv1);
        uv.add(uv2);
        uv.add(uv1 + uv3);
        uv.add(uv2 + uv4);
        jsonFace.add("uv", uv);
        jsonFace.addProperty("texture", "#texture");
        if (cull) {
            jsonFace.addProperty("cullface", face);
        }
        return jsonFace;
    }
}
