package com.taylorswiftcn.justwei.util.special;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.taylorswiftcn.justwei.MegumiLib;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class RGBParticle {

    private static final MegumiLib plugin = MegumiLib.getInstance();
    private static final Random random = new Random();

    public static void send(Player p, Location location) {
        float R = random.nextInt(255) + 1;
        float G = random.nextInt(255) + 1;
        float B = random.nextInt(255) + 1;
        send(p, (float) location.getX(), (float) location.getY(), (float) location.getZ(), R, G, B);
    }

    public static void send(Player player, Location location, Float R, Float G, Float B) {
        send(player, (float) location.getX(), (float) location.getY(), (float) location.getZ(), R, G, B);
    }

    public static void send(Player p, Float x, Float y, Float z) {
        float R = random.nextInt(255) + 1;
        float G = random.nextInt(255) + 1;
        float B = random.nextInt(255) + 1;
        send(p, x, y, z, R, G, B);
    }

    private static void send(Player p, Float x, Float y, Float z, Float R, Float G, Float B) {
        PacketContainer packet = getPacket(x, y, z, R, G, B);
        try {
            plugin.getProtocolManager().sendServerPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(Location location) {
        float R = random.nextInt(255) + 1;
        float G = random.nextInt(255) + 1;
        float B = random.nextInt(255) + 1;
        send((float) location.getX(), (float) location.getY(), (float) location.getZ(), R, G, B);
    }

    public static void send(Float x, Float y, Float z) {
        float R = random.nextInt(255) + 1;
        float G = random.nextInt(255) + 1;
        float B = random.nextInt(255) + 1;
        send(x, y, z, R, G, B);
    }

    private static void send(Float x, Float y, Float z, Float R, Float G, Float B) {
        PacketContainer packet = getPacket(x, y, z, R, G, B);
        try {
            plugin.getProtocolManager().broadcastServerPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PacketContainer getPacket(Float x, Float y, Float z, Float R, Float G, Float B) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getParticles().write(0, EnumWrappers.Particle.REDSTONE);
        packet.getBooleans().write(0, true);
        packet.getFloat()
                .write(0, x)
                .write(1, y)
                .write(2, z)
                .write(3, R / 255)
                .write(4, G / 255)
                .write(5, B / 255)
                .write(6, 1f);
        packet.getIntegers().write(0, 0);

        return packet;
    }
}
