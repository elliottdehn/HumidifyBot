package scripts.glassblower.nodes.actions;

import java.awt.Point;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Magic;

import scripts.glassblower.nodes.Node;

public class clickHumidify extends Node {

    @Override
    public boolean isValid() {
        //If the interface to craft orbs is open
        return Inventory.find("Clay").length >= 1;
    }

    @Override
    public void execute() {
        if (Banking.isBankScreenOpen()) {
            Banking.close();
        }

        if (Magic.selectSpell("Humidify")) {
            if (Magic.isSpellSelected()) {
                Point location = Mouse.getPos();
                Mouse.click(location, 1);
                General.sleep(General.random(2300, 2400));
            }

        }
    }

    @Override
    public String getStatus() {
        return "Completing interface...";
    }
}