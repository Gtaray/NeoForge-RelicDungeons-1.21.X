package net.saagael.relic_dungeons.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.saagael.relic_dungeons.RelicDungeons;

import java.util.function.Supplier;

public class RelicDungeonCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CREATE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RelicDungeons.MOD_ID);

    public static final Supplier<CreativeModeTab> RELIC_DUNGEONS_TAB = CREATE_MODE_TABS.register(
                    "relic_dungeons_creative_mode_tab",
                    () -> CreativeModeTab
                            .builder()
                            .title(Component.translatable("itemGroup.relic_dungeons.relic_dungeons_creative_mode_tab"))
                            .icon(() -> new ItemStack(RelicDungeonBlocks.RELIC_ALTAR_BLOCK.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(RelicDungeonBlocks.RELIC_ALTAR_BLOCK);
                                output.accept(RelicDungeonBlocks.RELIC_LOCATOR_BLOCK);
                            })
                            .build()
    );

    public static void register(IEventBus eventBus)
    {
        CREATE_MODE_TABS.register(eventBus);
    }
}
