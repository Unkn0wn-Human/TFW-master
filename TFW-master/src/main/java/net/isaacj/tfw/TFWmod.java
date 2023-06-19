package net.isaacj.tfw;

import net.fabricmc.api.ModInitializer;
import net.isaacj.tfw.block.ModBlocks;
import net.isaacj.tfw.block.entity.ModBlockEntity;
import net.isaacj.tfw.effect.ModEffects;
import net.isaacj.tfw.item.ModItems;
import net.isaacj.tfw.mixin.ModFoliagePlacerMixin;
import net.isaacj.tfw.mixin.ModTrunkPlacerMixin;
import net.isaacj.tfw.recipe.ModRecipes;
import net.isaacj.tfw.tag.CustomBlockTags;
import net.isaacj.tfw.util.ModRegistries;
import net.isaacj.tfw.world.dimension.ModDimension;
import net.isaacj.tfw.world.dimension.ModPortals;
import net.isaacj.tfw.world.feature.ModConfiguredFeatures;

import net.isaacj.tfw.world.feature.features.ModFeatures;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModBurntTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModLargeTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModTadaciaFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModBranchingTrunkPlacer;
import net.isaacj.tfw.world.feature.tree.placer.foliage.ModSyphFoliagePlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModLargeTadaciaTrunkPlacer;
import net.isaacj.tfw.world.feature.tree.placer.trunk.ModTadaciaTrunkPlacer;
import net.isaacj.tfw.world.gen.ModWorldGen;
import net.isaacj.tfw.world.structures.ModStructures;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TFWmod implements ModInitializer{


	//mod id
	public static final String MOD_ID = "tfw";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}


	public static final TrunkPlacerType<ModBranchingTrunkPlacer> BRANCHING_TRUNK_PLACER =
			ModTrunkPlacerMixin.TrunkPlacerTypeInvoker.callRegister("mod_branching_trunk_placer", ModBranchingTrunkPlacer.CODEC);

	public static final TrunkPlacerType<ModTadaciaTrunkPlacer> TADACIA_TRUNK_PLACER =
			ModTrunkPlacerMixin.TrunkPlacerTypeInvoker.callRegister("mod_tadacia_trunk_placer", ModTadaciaTrunkPlacer.CODEC);

	public static final TrunkPlacerType<ModLargeTadaciaTrunkPlacer> LARGE_TADACIA_TRUNK_PLACER =
			ModTrunkPlacerMixin.TrunkPlacerTypeInvoker.callRegister("mod_large_tadacia_trunk_placer", ModLargeTadaciaTrunkPlacer.CODEC);


	public static final FoliagePlacerType<ModSyphFoliagePlacer> MOD_SYPH_FOLIAGE_PLACER = ModFoliagePlacerMixin.FoliagePlacerTypeInvoker.callRegister
			("mod_syph_foliage_placer", ModSyphFoliagePlacer.CODEC);

	public static final FoliagePlacerType<ModBurntTadaciaFoliagePlacer> MOD_BURNT_TADACIA_FOLIAGE_PLACER = ModFoliagePlacerMixin.FoliagePlacerTypeInvoker.callRegister
			("mod_burnt_tadacia_foliage_placer", ModBurntTadaciaFoliagePlacer.CODEC);

	public static final FoliagePlacerType<ModTadaciaFoliagePlacer> MOD_TADACIA_FOLIAGE_PLACER = ModFoliagePlacerMixin.FoliagePlacerTypeInvoker.callRegister
			("mod_tadacia_foliage_placer", ModTadaciaFoliagePlacer.CODEC);

	public static final FoliagePlacerType<ModLargeTadaciaFoliagePlacer> MOD_LARGE_TADACIA_FOLIAGE_PLACER = ModFoliagePlacerMixin.FoliagePlacerTypeInvoker.callRegister
			("mod_lareg_tadacia_foliage_placer", ModLargeTadaciaFoliagePlacer.CODEC);





	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();
		ModFeatures.registerFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntity.registerAllEntities();
		ModRecipes.register();

		ModRegistries.registerModStuffs();

		ModPortals.registerPortals();

		ModDimension.register();

		ModWorldGen.generateModWorldGen();

		ModStructures.registerStructureFeatures();

		ModEffects.registerEffects();


	}


}
