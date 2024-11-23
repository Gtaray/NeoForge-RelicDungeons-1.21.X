package net.saagael.relic_dungeons.common.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.saagael.relic_dungeons.RelicDungeons;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RelicDungeons.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("relic_altar").parent(ModBlockStateProvider.getUncheckedModel("relic_altar"));
        getBuilder("relic_locator").parent(ModBlockStateProvider.getUncheckedModel("relic_locator"));
    }
}
