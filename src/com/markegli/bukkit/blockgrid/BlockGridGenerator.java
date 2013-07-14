package com.markegli.bukkit.blockgrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class BlockGridGenerator extends ChunkGenerator {
	private final int size;
	private final byte blocktype;
	
	public BlockGridGenerator() {
		this(256);
	}
	
	public BlockGridGenerator(int size) {
		this.size = size;
		this.blocktype = -1;
	}
	
	public BlockGridGenerator(int size, byte blocktype) {
		this.size = size;
		this.blocktype = blocktype;
	}
	
	@Override
	public byte[][] generateBlockSections(World world, Random random, int chunkx, int chunkz, BiomeGrid biomes) {
		
		byte blockid;
		BlockProbability p = WorldStyles.get(world.getEnvironment()).p;
		
		int vsegs = BlockGridPlugin.maxHeight(world, size) / 16;
		byte[][] chunk = new byte[vsegs][];
		boolean b;
		byte t;
		for (int ys = 0; ys < vsegs; ys++) {
			chunk[ys] = new byte[4096];
			for (int x = 0; x < 16; x += 4) {
				for (int y = 0; y < 16; y += 4) {
					for (int z = 0; z < 16; z += 4) {
						if (y == 0) {
							if (ys != 0) t = getBlock(chunk[ys - 1], x, 13, z);
							else t = 0;
						} else {
							t = getBlock(chunk[ys], x, y - 3, z);
						}
						b = t == 81 || t == 83;
						
						blockid = blocktype > 0 ? blocktype : p.getBlock(random, y == 0 && ys == 0, b);
						
						if(blockid == 6 || blockid == 31 || blockid == 32 || blockid == 37 ||
								blockid == 38 || blockid == 39 || blockid == 40 || blockid == 83) {
							setBlock(chunk[ys], x, y, z, 3); //dirt
							setBlock(chunk[ys], x, y+1, z, blockid);
							if (blockid == 83) //reeds
								setBlock(chunk[ys], x+1, y, z, 9); //still water
						} else if (blockid == 81) { //cactus
							setBlock(chunk[ys], x, y, z, 12); //sand
							if (y == 0) setBlock(chunk[ys-1], x, y+15, z, 106); //vines
							else setBlock(chunk[ys], x, y-1, z, 106); //vines
							setBlock(chunk[ys], x, y+1, z, blockid);
						} else if (blockid == 115) { //netherwart
							setBlock(chunk[ys], x, y, z, 112); //netherbrick
							setBlock(chunk[ys], x, y+1, z, blockid);
						} else {
							setBlock(chunk[ys], x, y, z, blockid);
						}
					}
				}
			}
		}
		
		return chunk;
	}
	
	void setBlock(byte[] subchunk, int x, int y, int z, int blkid) {
		setBlock(subchunk, x, y, z, (byte) blkid);
	}
	
	void setBlock(byte[] subchunk, int x, int y, int z, byte blkid) {
		subchunk[((y) << 8) | (z << 4) | x] = blkid;
	}
	
	byte getBlock(byte[] subchunk, int x, int y, int z) {
		return subchunk[((y) << 8) | (z << 4) | x];
	}
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		List<BlockPopulator> list = new ArrayList<BlockPopulator>(1);
		if (blocktype <= 0) list.add(new BlockGridPopulator(size));
		return list;
	}
	
	@Override
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world, 0.5, (BlockGridPlugin.maxHeight(world, size) * 0.75) - 4, 0.5);
	}
	
}
