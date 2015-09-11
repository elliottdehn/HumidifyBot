package scripts.glassblower.nodes.actions;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.glassblower.GlassBlower;
import scripts.glassblower.nodes.Node;

public class WithdrawItems extends Node {

    @Override
    public boolean isValid() {
        //If we are out of molten glass, or glassblower
        return Banking.isBankScreenOpen()
                && (Inventory.find("Clay").length < 1 || Inventory
                        .find("Astral rune").length < 1)
                        && GlassBlower.antiban.canInteractItem();
    }

    @Override
    public void execute() {
        //Get the required item
        if (Inventory.find("Clay").length < 1) {
            if (Banking.withdraw(0, "Clay")) {
                GlassBlower.antiban.resetItem();
            }
        } else if (Banking.withdraw(0, "Astral rune")) {
            GlassBlower.antiban.resetItem();
        }
    }

    @Override
    public String getStatus() {
        return "Withdrawing items...";
    }
}