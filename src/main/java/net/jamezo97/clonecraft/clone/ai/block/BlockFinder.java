package net.jamezo97.clonecraft.clone.ai.block;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

public interface BlockFinder{
	
	public boolean hasNextBlock();
	
	/**
	 * Assumes that the clone can see the block, or isnot meant to be able to see it, etc
	 * You perform the checks before returning the block. So even if the clone can't see the block
	 * they will still try to go and break it.
	 * @return
	 */
	public ChunkCoordinates getNextBlock(EntityAIMine ai);
	
	public void onFinished(EntityAIMine entityAI);
	
	/**
	 * If returns true, then the clone will just stand in the same position, and break the blocks
	 * without moving. Good for when clearing out land or building a house etc.
	 * @return
	 */
	public boolean mustBeCloseToBreak();
	
	public void saveState(NBTTagCompound nbt);
	
	public void loadState(NBTTagCompound nbt);

	/**
	 * Called when a block provided by this finder is unbreakable by the clone.
	 * This may be because the clone lacks the required tools, or perhaps the block
	 * is just impossible to destroy. You should handle this function so that
	 * subsequent calls to 'getNextBlock' don't return the same block, otherwise the
	 * clone will be stuck in a loop forever trying to break the block.
	 * @param cc ChunkCoordinate of the block that couldn't be broken
	 * @param break_block The Block object representing the block that couldn't be broken.
	 */
	public void cantBreakBlock(ChunkCoordinates cc, Block break_block);
	
	/**
	 * Called when the clone receives a new item, and thus might be able to
	 * break a previously found block that couldn't be (and thus called 'cantBreakBlock')
	 */
	public void cloneStateChanged();
	
}