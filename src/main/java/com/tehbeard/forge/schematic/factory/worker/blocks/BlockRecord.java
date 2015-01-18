package com.tehbeard.forge.schematic.factory.worker.blocks;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;

/**
 * Class Name - BlockRecord
 * Package - com.tehbeard.forge.schematic.factory.worker.blocks
 * Desc of Class - This class represents a single block and all the data
 *                 associated with it (i.e. colour, rotation, etc.)
 * Author(s) - M. D. Ball
 */
public final class BlockRecord {

    /**
     * The namespace is the designation of the block (i.e. "minecraft:stone")
     */
    public final String namespace;

    /**
     * The id is the id of the object within the game registry and should probably
     * not exist in here in favour of a method elsewhere which just gets the id of
     * a given namespace
     */
    public final int _id;

    /**
     * The metadata is all the surrounding data about a block, including its colour,
     * rotation, etc.
     */
    public final byte metadata;

    /**
     * Construct the record with the namespace of a block and the specified
     * metadata that goes along with it
     *
     * @param namespace The designation of the block we're recording
     * @param metadata The metadata associated with the block we're recording
     */
    public BlockRecord(String namespace, byte metadata) {
        this.namespace = namespace;
        this._id = Block.getIdFromBlock(Block.getBlockFromName(namespace));
        this.metadata = metadata;
    }

    /**
     * Construct the record with the id of a block within the registry and the
     * specified metadata that goes along with it
     *
     * @param id The id of the block we're recording (as found in the registry)
     * @param metadata The metadata associated with the block we're recording
     */
    public BlockRecord(int id, byte metadata) {
        this.namespace = GameData.getBlockRegistry().getNameForObject(Block.getBlockById(id));
        this._id = id;
        this.metadata = metadata;
    }

    /**
     * Construct the record with the namespace of a block, assuming that there
     * is no metadata to go along with it
     *
     * @param namespace The metadata associated with the block we're recording
     */
    public BlockRecord(String namespace) {
        this(namespace, (byte) 0);
    }

    /**
     * Construct the record with the id of a block within the registry, assuming
     * that there is no metadata to go along with it
     *
     * @param id The id of the block we're recording (as found in the registry)
     */
    public BlockRecord(int id) {
        this(id, (byte) 0);
    }

    /**
     * Overriding these methods to ensure we're unique and that we only compare
     * on the important items
     *
     * @param obj The {@link BlockRecord} that we're comparing to
     * @return Whether or not these two objects are equal to one another
     */
    @Override
    public boolean equals(Object obj) {
        if (obj!=null && obj instanceof BlockRecord) {

            BlockRecord record = (BlockRecord) obj;

            return
                    this.namespace.equals(record.namespace)
                    && this.metadata == record.metadata;

        }
        return false;
    }

    /**
     * Since we overrode the equals method, it follows that we should do the same
     * to the hashcode, again only including those items which are important to
     * us
     *
     * @return The integer value of the contents of this record
     */
    @Override
    public int hashCode(){
        int hash = 13;
        hash *= (27 * this.namespace.hashCode());
        hash *= (27 * this.metadata);
        return hash;
    }

}