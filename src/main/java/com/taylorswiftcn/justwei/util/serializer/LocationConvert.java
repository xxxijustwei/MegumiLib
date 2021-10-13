package com.taylorswiftcn.justwei.util.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationConvert {

    private static final Gson gson = new Gson();

    public static String get(Location location) {
        JsonObject obj = new JsonObject();
        obj.addProperty("world", location.getWorld().getName());
        obj.addProperty("x", location.getX());
        obj.addProperty("y", location.getY());
        obj.addProperty("z", location.getZ());
        obj.addProperty("yaw", location.getYaw());
        obj.addProperty("pitch", location.getPitch());

        return gson.toJson(obj);
    }

    public static Location get(String s) {
        JsonObject obj = gson.fromJson(s, JsonObject.class);
        World world = Bukkit.getWorld(obj.get("world").toString());
        double x = obj.get("x").getAsDouble();
        double y = obj.get("y").getAsDouble();
        double z = obj.get("z").getAsDouble();
        float yaw = obj.get("yaw").getAsFloat();
        float pitch = obj.get("pitch").getAsFloat();

        return new Location(world, x, y, z, yaw, pitch);
    }
}
