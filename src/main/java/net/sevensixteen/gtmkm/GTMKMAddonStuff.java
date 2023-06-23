package net.sevensixteen.gtmkm;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import net.sevensixteen.gtmkm.create.GTMKMCreateMachines;
import net.sevensixteen.gtmkm.recipe.GTMKMRecipeTypes;

public class GTMKMAddonStuff implements IGTAddon {

    @Override
    public void initializeAddon() {

    }

    @Override
    public void registerTagPrefixes() {
        IGTAddon.super.registerTagPrefixes();
    }

    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
    }

    @Override
    public void registerMaterials() {
        IGTAddon.super.registerMaterials();
    }

    @Override
    public void registerSounds() {
        IGTAddon.super.registerSounds();
    }

    @Override
    public void registerCovers() {
        IGTAddon.super.registerCovers();
    }

    @Override
    public void registerRecipeTypes() {
        GTMKMRecipeTypes.init();
        IGTAddon.super.registerRecipeTypes();
    }

    @Override
    public void registerMachines() {
        GTMKMCreateMachines.init();
        IGTAddon.super.registerMachines();
    }

    @Override
    public void registerWorldgenLayers() {
        IGTAddon.super.registerWorldgenLayers();
    }

    @Override
    public void registerVeinGenerators() {
        IGTAddon.super.registerVeinGenerators();
    }
}
