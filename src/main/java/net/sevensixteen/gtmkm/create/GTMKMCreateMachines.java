package net.sevensixteen.gtmkm.create;

import com.gregtechceu.gtceu.common.data.GTCreateMachines;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.machine.KineticMachineDefinition;
import net.sevensixteen.gtmkm.recipe.GTMKMRecipeTypes;

public class GTMKMCreateMachines {
    public static final KineticMachineDefinition[] KINETIC_CHEMICAL_REACTOR = GTCreateMachines.registerSimpleKineticElectricMachine("kinetic_chemical_reactor", GTMKMRecipeTypes.CREATE_CHEMICAL_RECIPES, GTMachines.LOW_TIERS);
    public static final KineticMachineDefinition[] KINETIC_BENDER = GTCreateMachines.registerSimpleKineticElectricMachine("kinetic_bender", GTMKMRecipeTypes.CREATE_BENDER_RECIPES, GTMachines.LOW_TIERS);
    public static final KineticMachineDefinition[] KINETIC_MACERATOR = GTCreateMachines.registerSimpleKineticElectricMachine("kinetic_macerator", GTMKMRecipeTypes.CREATE_MACERATOR_RECIPES, GTMachines.LOW_TIERS);
    public static final KineticMachineDefinition[] KINETIC_FORGE_HAMMER = GTCreateMachines.registerSimpleKineticElectricMachine("kinetic_forge_hammer", GTMKMRecipeTypes.CREATE_FORGE_HAMMER_RECIPES, GTMachines.LOW_TIERS);

    public static void init() {

    }
}
