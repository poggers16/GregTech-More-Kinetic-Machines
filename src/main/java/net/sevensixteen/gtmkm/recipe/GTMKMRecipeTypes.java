package net.sevensixteen.gtmkm.recipe;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.recipe.RPMCondition;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget;
import com.lowdragmc.lowdraglib.misc.ItemStackTransfer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class GTMKMRecipeTypes {
    public static GTRecipeType CREATE_CHEMICAL_RECIPES = GTRecipeTypes.register("kinetic_chemical_reactor", GTRecipeTypes.KINETIC).setMaxIOSize(2, 2, 3, 2).setEUIO(IO.IN)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (recipe.conditions.size() > 0 && recipe.conditions.get(0) instanceof RPMCondition rpmCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                   group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30, group.getSize().height - 30, false, false));
               }
            });
    public static GTRecipeType CREATE_BENDER_RECIPES = GTRecipeTypes.register("kinetic_bender", GTRecipeTypes.KINETIC).setMaxIOSize(2, 1, 0, 0).setEUIO(IO.IN)
            .setSlotOverlay(false, false, false, GuiTextures.BENDER_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MOTOR)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (recipe.conditions.size() > 0 && recipe.conditions.get(0) instanceof RPMCondition rpmCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30, group.getSize().height - 30, false, false));
                }
            });
    public static GTRecipeType CREATE_MACERATOR_RECIPES = GTRecipeTypes.register("kinetic_macerator", GTRecipeTypes.KINETIC).setMaxIOSize(1, 4, 0, 0).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MACERATOR)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (recipe.conditions.size() > 0 && recipe.conditions.get(0) instanceof RPMCondition rpmCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30, group.getSize().height - 30, false, false));
                }
            });
    public static GTRecipeType CREATE_FORGE_HAMMER_RECIPES = GTRecipeTypes.register("kinetic_forge_hammer", GTRecipeTypes.KINETIC).setMaxIOSize(1, 1, 0, 0).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.HAMMER_OVERLAY)
            .setSpecialTexture(78, 42, 20, 6, GuiTextures.PROGRESS_BAR_HAMMER_BASE)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressTexture.FillDirection.UP_TO_DOWN)
            .setSound(GTSoundEntries.FORGE_HAMMER)
            .setMaxTooltips(4)
            .setUiBuilder((recipe, group) -> {
                if (recipe.conditions.size() > 0 && recipe.conditions.get(0) instanceof RPMCondition rpmCondition) {
                    var transfer = new ItemStackTransfer(AllBlocks.SHAFT.asStack());
                    group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30, group.getSize().height - 30, false, false));
                }
            });

    public static void init() {
        GTRecipeTypes.CHEMICAL_RECIPES.onRecipeBuild((builder, provider) -> {
            assert CREATE_CHEMICAL_RECIPES != null;
            CREATE_CHEMICAL_RECIPES.copyFrom(builder)
                    .duration(Math.max((builder.duration / 2), 1))
                    .rpm(64)
                    .save(provider);
        });
        GTRecipeTypes.BENDER_RECIPES.onRecipeBuild((builder, provider) -> {
            assert CREATE_BENDER_RECIPES != null;
            CREATE_BENDER_RECIPES.copyFrom(builder)
                    .duration(Math.max((builder.duration / 2), 1))
                    .rpm(128)
                    .save(provider);
        });
        GTRecipeTypes.MACERATOR_RECIPES.onRecipeBuild((builder, provider) -> {
            assert CREATE_MACERATOR_RECIPES != null;
            CREATE_MACERATOR_RECIPES.copyFrom(builder)
                    .duration(Math.max((builder.duration / 2), 1))
                    .rpm(64)
                    .save(provider);
        });
        GTRecipeTypes.FORGE_HAMMER_RECIPES.onRecipeBuild((builder, provider) -> {
            assert CREATE_FORGE_HAMMER_RECIPES != null;
            CREATE_FORGE_HAMMER_RECIPES.copyFrom(builder)
                    .duration(Math.max((builder.duration / 2), 1))
                    .rpm(16)
                    .save(provider);
        });
    }
}
