package com.markegli.bukkit.blockgrid;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockGridPlugin extends JavaPlugin {
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		if (id != null && !id.isEmpty()) {
			String[] arguments = id.split("[:. \\t]+");
			int size = arguments[0].matches("[0-9]+") ? Integer.parseInt(arguments[0]) : 256;
			if (size <= 0) size = 256;
			
			byte blocktype = arguments.length > 1 && arguments[1].matches("[0-9]+") ? Byte.parseByte(arguments[1]) : -1;
			
			return new BlockGridGenerator(size, blocktype);
		}
		return new BlockGridGenerator();
	}
	
	public static int maxHeight(World world, int size) {
		return Math.min(world.getMaxHeight(), size);
	}
	
}
