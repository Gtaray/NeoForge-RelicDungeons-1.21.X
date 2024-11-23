package net.saagael.relic_dungeons.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.registers.RelicDungeonBlocks;

public class ModBlockStateProvider extends BlockStateProvider
{

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RelicDungeons.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerOnlyState(RelicDungeonBlocks.RELIC_ALTAR_BLOCK.get(), "relic_altar");
        registerOnlyState(RelicDungeonBlocks.RELIC_LOCATOR_BLOCK.get(), "relic_locator");
    }

    private void registerOnlyState(Block block, String registry) {
        simpleBlock(block, getUncheckedModel(registry));
    }

    public static ModelFile getUncheckedModel(String registry) {
        return new ModelFile.UncheckedModelFile(RelicDungeons.MOD_ID + ":block/" + registry);
    }
}
