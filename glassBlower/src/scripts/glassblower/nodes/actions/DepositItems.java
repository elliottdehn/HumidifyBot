package scripts.glassblower.nodes.actions;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.glassblower.GlassBlower;
import scripts.glassblower.nodes.Node;

public class DepositItems extends Node {

    @Override
    public boolean isValid() {
        /*
         * If all of the items in our inventory minus our glass blower (that is
         * the 27, otherwise it would be 28) is > or = to the amount of orbs
         */
        return 27 - Inventory.getAll().length <= Inventory.find("Soft clay").length
                && GlassBlower.antiban.canInteractItem();
    }

    @Override
    public void execute() {
        if (Banking.depositAllExcept("Astral rune") > 0) {
            GlassBlower.antiban.resetItem();
        }
    }

    @Override
    public String getStatus() {
        return "Depositing items...";
    }
}