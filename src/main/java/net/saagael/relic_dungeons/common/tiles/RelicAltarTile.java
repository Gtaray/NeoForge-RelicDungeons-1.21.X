package net.saagael.relic_dungeons.common.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.saagael.relic_dungeons.registers.RelicDungeonTiles;
import org.jetbrains.annotations.Nullable;

public class RelicAltarTile extends BlockEntity implements Container
{
    // How fast the item on the altar rotates
    public static final float ROTATIONS_PER_SECOND = 0.15f;
    // Vertical distance the item travels when bobbing up and down
    public static final float BOB_HEIGHT = 0.07f;

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private float timer = 0;

    public RelicAltarTile(BlockPos pos, BlockState blockState)
    {
        super(RelicDungeonTiles.RELIC_ALTAR_TILE.get(), pos, blockState);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, RelicAltarTile tile) {
        // Only tick if there's an item in the inventory
        if (tile.isEmpty())
            return;

        // The 18f is because we need to rotate 18 degrees every tick to reach 360 degrees every 20 ticks
        // This allows us to specify Rotation speed in rotations per second, rather than some other scalar.
        tile.timer += 18f * RelicAltarTile.ROTATIONS_PER_SECOND;
        tile.timer %= 360; // Always lock rotation between 0 and 359
    }

    public float getRenderingHeight() {
        return Mth.sin((float)Math.toRadians(timer)) * BOB_HEIGHT;
    }

    public float getRenderingRotation() {
        // Timer goes from 0 to 359, which is directly used as the rotation value
        return timer;
    }

    @Override
    public int getContainerSize()
    {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < getContainerSize(); i++)
        {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        setChanged();
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount)
    {
        setChanged();
        ItemStack stack = inventory.get(slot);
        stack.shrink(amount);
        return inventory.set(slot, stack);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot)
    {
        setChanged();
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        // When adding an item, force the timer to 0
        // so it starts its animation cycle from 0
        timer = 0;

        setChanged();
        inventory.set(slot, stack.copyWithCount(1));
    }

    @Override
    public boolean stillValid(Player player)
    {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent()
    {
        inventory.clear();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, inventory, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        ContainerHelper.loadAllItems(tag, inventory, registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
