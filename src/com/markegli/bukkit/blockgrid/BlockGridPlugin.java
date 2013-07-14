package com.markegli.bukkit.blockgrid;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockGridPlugin extends JavaPlugin {
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		if (id != null && !id.isEmpty()) try {
			int size = 256;
			size = Integer.parseInt(id);
			if (size <= 0) size = 256;
			return new BlockGridGenerator(size);
		} catch (NumberFormatException e) {}
		return new BlockGridGenerator();
	}
	
	public static int maxHeight(World world, int size) {
		if (world.getMaxHeight() < size)
			return world.getMaxHeight();
		else return size;
	}
	
}
