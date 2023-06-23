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

import javax.annotation.Nullable;

public class GTMKMRecipeTypes {
    public static @Nullable GTRecipeType CREATE_CHEMICAL_RECIPES;

    public static void init() {
        GTRecipeType CREATE_CHEMICAL_RECIPES = GTRecipeTypes.register("chemical_reactor", GTRecipeTypes.ELECTRIC).setMaxIOSize(2, 2, 3, 2).setEUIO(IO.IN)
                .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(GTValues.VA[GTValues.LV]))
                .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
                .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
                .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
                .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
                .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
                .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
                .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
                .setMaxConditions(1);
                //.setUiBuilder((recipe, group) -> {
                //    if (recipe.conditions.size() > 0 && recipe.conditions.get(0) instanceof RPMCondition rpmCondition) {
                //        var transfer = new ItemStackTransfer(AllBlocks.SHAFT.getSha);
                 //       group.addWidget(new SlotWidget(transfer, 0, group.getSize().width - 30, group.getSize().height - 30, false, false));
                 //   }
                //});
        GTRecipeTypes.MIXER_RECIPES.onRecipeBuild((builder, provider) -> {
            assert CREATE_CHEMICAL_RECIPES != null;
            CREATE_CHEMICAL_RECIPES.copyFrom(builder)
                    .duration(Math.max((builder.duration / 2), 1))
                    .rpm(64)
                    .save(provider);
        });

    }
}
