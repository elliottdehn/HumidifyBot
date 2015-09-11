package scripts.glassblower.nodes.actions;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.glassblower.GlassBlower;
import scripts.glassblower.nodes.Node;

public class OpenBank extends Node {

    @Override
    public boolean isValid() {
        //These conditions are pulled from my withdraw/deposit conditions
        return (27 - Inventory.getAll().length <= Inventory.find("Soft clay").length)
                || (Inventory.find("Soft clay").length < 1 || Inventory
                        .find("Astral rune").length < 1)
                && !Banking.isBankScreenOpen()
                && GlassBlower.antiban.canInteractObject();
    }

    @Override
    public void execute() {
        if (Banking.openBank()) {
            GlassBlower.antiban.resetObject();
        }
    }

    @Override
    public String getStatus() {
        return "Opening bank...";
    }
}