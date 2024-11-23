package net.saagael.relic_dungeons.registers;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.saagael.relic_dungeons.RelicDungeons;

public class RelicDungeonItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RelicDungeons.MOD_ID);

    //public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem("test_item");

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
