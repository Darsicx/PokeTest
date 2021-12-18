package com.android.testclip.data.remote.retrofit.models.pokemon_info


import com.google.gson.annotations.SerializedName

data class PokemonInfoResponseDto(
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("color")
    val colorDto: ColorDto,
    @SerializedName("egg_groups")
    val eggGroupDtos: List<EggGroupDto>,
    @SerializedName("evolution_chain")
    val evolutionChainDto: EvolutionChainDto,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: Any?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntryDtos: List<FlavorTextEntryDto>,
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerializedName("gender_rate")
    val genderRate: Int,
    @SerializedName("genera")
    val genera: List<GeneraDto>,
    @SerializedName("generation")
    val generationDto: GenerationDto,
    @SerializedName("growth_rate")
    val growthRateDto: GrowthRateDto,
    @SerializedName("habitat")
    val habitatDto: HabitatDto,
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @SerializedName("hatch_counter")
    val hatchCounter: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val nameDtos: List<NameDto>,
    @SerializedName("order")
    val order: Int,
    @SerializedName("pal_park_encounters")
    val palParkEncounterDtos: List<PalParkEncounterDto>,
    @SerializedName("pokedex_numbers")
    val pokedexNumberDtos: List<PokedexNumberDto>,
    @SerializedName("shape")
    val shapeDto: ShapeDto,
    @SerializedName("varieties")
    val varietyDtos: List<VarietyDto>
)