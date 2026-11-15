package com.xynsue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class XynsueConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("xynsue.json");

    public static boolean moonEnabled = true;
    public static boolean fireEnabled = true;
    public static String moonHex = "#7A00FF";
    public static String fireHex = "#7A00FF";
    public static int pearlThrow = 100;
    public static int pearlLanding = 100;

    private XynsueConfig() {}

    public static void load() {
        if (!Files.exists(FILE)) return;
        try (Reader reader = Files.newBufferedReader(FILE)) {
            JsonObject j = GSON.fromJson(reader, JsonObject.class);
            if (j == null) return;
            moonEnabled = bool(j, "moonEnabled", moonEnabled);
            fireEnabled = bool(j, "fireEnabled", fireEnabled);
            moonHex = string(j, "moonHex", moonHex);
            fireHex = string(j, "fireHex", fireHex);
            pearlThrow = clamp(integer(j, "pearlThrow", pearlThrow));
            pearlLanding = clamp(integer(j, "pearlLanding", pearlLanding));
        } catch (Exception ignored) {}
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            JsonObject j = new JsonObject();
            j.addProperty("moonEnabled", moonEnabled);
            j.addProperty("fireEnabled", fireEnabled);
            j.addProperty("moonHex", validHex(moonHex, "#7A00FF"));
            j.addProperty("fireHex", validHex(fireHex, "#7A00FF"));
            j.addProperty("pearlThrow", clamp(pearlThrow));
            j.addProperty("pearlLanding", clamp(pearlLanding));
            try (Writer writer = Files.newBufferedWriter(FILE)) {
                GSON.toJson(j, writer);
            }
        } catch (Exception ignored) {}
    }

    public static String validHex(String value, String fallback) {
        return value != null && value.matches("#[0-9a-fA-F]{6}") ? value.toUpperCase() : fallback;
    }
    private static int clamp(int v) { return Math.max(0, Math.min(100, v)); }
    private static boolean bool(JsonObject j, String k, boolean d) { return j.has(k) ? j.get(k).getAsBoolean() : d; }
    private static int integer(JsonObject j, String k, int d) { return j.has(k) ? j.get(k).getAsInt() : d; }
    private static String string(JsonObject j, String k, String d) { return j.has(k) ? j.get(k).getAsString() : d; }
}
