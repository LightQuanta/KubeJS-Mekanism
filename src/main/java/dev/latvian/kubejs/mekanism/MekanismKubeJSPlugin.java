package dev.latvian.kubejs.mekanism;

import dev.latvian.kubejs.mekanism.custom.KubeJSGasBuilder;
import dev.latvian.kubejs.mekanism.custom.KubeJSInfuseTypeBuilder;
import dev.latvian.kubejs.mekanism.custom.KubeJSPigmentBuilder;
import dev.latvian.kubejs.mekanism.custom.KubeJSSlurryBuilder;
import dev.latvian.kubejs.mekanism.recipe.*;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.pigment.Pigment;
import mekanism.api.chemical.slurry.Slurry;

public class MekanismKubeJSPlugin extends KubeJSPlugin {
    // registry builders for all mekanism chemical subtypes
    public static final RegistryInfo GAS = RegistryInfo.of(MekanismAPI.gasRegistry().getRegistryKey(), Gas.class);
    public static final RegistryInfo INFUSE_TYPE = RegistryInfo.of(MekanismAPI.infuseTypeRegistry().getRegistryKey(), Slurry.class);
    public static final RegistryInfo PIGMENT = RegistryInfo.of(MekanismAPI.pigmentRegistry().getRegistryKey(), Pigment.class);
    public static final RegistryInfo SLURRY = RegistryInfo.of(MekanismAPI.slurryRegistry().getRegistryKey(), Slurry.class);

    @Override
    public void init() {
        GAS.addType("basic", KubeJSGasBuilder.class, KubeJSGasBuilder::new);
        INFUSE_TYPE.addType("basic", KubeJSInfuseTypeBuilder.class, KubeJSInfuseTypeBuilder::new);
        PIGMENT.addType("basic", KubeJSPigmentBuilder.class, KubeJSPigmentBuilder::new);
        SLURRY.addType("basic", KubeJSSlurryBuilder.Basic.class, KubeJSSlurryBuilder.Basic::new);
        SLURRY.addType("dirty", KubeJSSlurryBuilder.Dirty.class, KubeJSSlurryBuilder.Dirty::new);
        SLURRY.addType("clean", KubeJSSlurryBuilder.Clean.class, KubeJSSlurryBuilder.Clean::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace(MekanismAPI.MEKANISM_MODID)
                .register("crushing", ItemToItemRecipeSchema.SCHEMA)
                .register("enriching", ItemToItemRecipeSchema.SCHEMA)
                .register("smelting", ItemToItemRecipeSchema.SCHEMA)
                .register("chemical_infusing", ChemicalInfusingRecipeSchema.SCHEMA)
                .register("combining", CombiningRecipeSchema.SCHEMA)
                // separating = new ElectrolysisRecipeSerializer(ElectrolysisIRecipe.SCHEMA)
                // washing = new FluidSlurryToSlurryRecipeSerializer(FluidSlurryToSlurryIRecipe.SCHEMA)
                // evaporating = new FluidToFluidRecipeSerializer(FluidToFluidIRecipe.SCHEMA)
                // activating = new GasToGasRecipeSerializer(ActivatingIRecipe.SCHEMA)
                // centrifuging = new GasToGasRecipeSerializer(CentrifugingIRecipe.SCHEMA)
                .register("crystallizing", CrystallizingRecipeSchema.SCHEMA)
                .register("dissolution", ChemicalDissolutionRecipeSchema.SCHEMA)
                .register("compressing", ItemAndGasToItemRecipeSchema.SCHEMA)
                .register("purifying", ItemAndGasToItemRecipeSchema.SCHEMA)
                .register("injecting", ItemAndGasToItemRecipeSchema.SCHEMA)
                // nucleosynthesizing = new NucleosynthesizingRecipeSerializer(NucleosynthesizingIRecipe.SCHEMA)
                .register("energy_conversion", EnergyConversionRecipeSchema.SCHEMA)
                .register("gas_conversion", GasConversionRecipeSchema.SCHEMA)
                .register("oxidizing", OxidizingRecipeSchema.SCHEMA)
                // infusion_conversion = new ItemStackToInfuseTypeRecipeSerializer(InfusionConversionIRecipe.SCHEMA)
                .register("metallurgic_infusing", MetallurgicInfusingRecipeSchema.SCHEMA)
                .register("reaction", PressurizedReactionRecipeSchema.SCHEMA)
                // rotary = new RotaryRecipeSerializer(new RotaryIRecipe.Factory())
                .register("sawing", SawingRecipeSchema.SCHEMA)

        ;
    }
}
