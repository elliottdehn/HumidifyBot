package scripts.glassblower;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Skills;

public class Antiban extends ABCUtil {

    private long nextItemInteraction;
    private long nextObjectInteraction;

    public Antiban() {
        General.println("ABCL 10 initiated");
    }

    public void start() {
        if (!Banking.isBankScreenOpen()) {
            this.checkQuests();
            this.checkCombat();
            this.checkEquipment();
            this.checkMusic();
            this.leaveGame();
            this.checkXp();
            this.checkFl();
            this.ranRightClick();
            this.examineObj();
            this.rotateCam();
            this.ranMouseMoves();
            this.pickUpMouse();
        }
    }

    private void checkQuests() {
        if (this.TIME_TRACKER.CHECK_QUESTS.next() < Timing.currentTimeMillis()) {
            this.performQuestsCheck();
            this.TIME_TRACKER.CHECK_QUESTS.reset();
        }
    }

    private void checkCombat() {
        if (this.TIME_TRACKER.CHECK_COMBAT.next() < Timing.currentTimeMillis()) {
            this.performCombatCheck();
            this.TIME_TRACKER.CHECK_COMBAT.reset();
        }
    }

    public final void rotateCam() {
        if (this.TIME_TRACKER.ROTATE_CAMERA.next() <= Timing
                .currentTimeMillis()) {
            this.performRotateCamera();
            this.TIME_TRACKER.ROTATE_CAMERA.reset();
        }
    }

    public void checkXp() {
        if (this.TIME_TRACKER.CHECK_XP.next() <= Timing.currentTimeMillis()) {
            this.performXPCheck(Skills.SKILLS.MAGIC);
            this.TIME_TRACKER.CHECK_XP.reset();
        }
    }

    public final void pickUpMouse() {
        if (this.TIME_TRACKER.PICKUP_MOUSE.next() <= Timing.currentTimeMillis()) {
            this.performPickupMouse();
            this.TIME_TRACKER.PICKUP_MOUSE.reset();
        }
    }

    public void leaveGame() {
        if (this.TIME_TRACKER.LEAVE_GAME.next() <= Timing.currentTimeMillis()) {
            this.performLeaveGame();
            this.TIME_TRACKER.LEAVE_GAME.reset();
        }
    }

    public void examineObj() {
        if (this.TIME_TRACKER.EXAMINE_OBJECT.next() <= Timing
                .currentTimeMillis()) {
            this.performExamineObject();
            this.TIME_TRACKER.EXAMINE_OBJECT.reset();
        }
    }

    public void ranRightClick() {
        if (this.TIME_TRACKER.RANDOM_RIGHT_CLICK.next() <= Timing
                .currentTimeMillis()) {
            this.performRandomRightClick();
            this.TIME_TRACKER.RANDOM_RIGHT_CLICK.reset();
        }
    }

    public void ranMouseMoves() {
        if (this.TIME_TRACKER.RANDOM_MOUSE_MOVEMENT.next() <= Timing
                .currentTimeMillis()) {
            this.performRandomMouseMovement();
            this.TIME_TRACKER.RANDOM_MOUSE_MOVEMENT.reset();
        }
    }

    public void checkEquipment() {
        if (this.TIME_TRACKER.CHECK_EQUIPMENT.next() <= Timing
                .currentTimeMillis()) {
            this.performEquipmentCheck();
            this.TIME_TRACKER.CHECK_EQUIPMENT.reset();
        }
    }

    public final void checkFl() {
        if (this.TIME_TRACKER.CHECK_FRIENDS.next() <= Timing
                .currentTimeMillis()) {
            this.performFriendsCheck();
            this.TIME_TRACKER.CHECK_FRIENDS.reset();
        }
    }

    public final void checkMusic() {
        if (this.TIME_TRACKER.CHECK_MUSIC.next() <= Timing.currentTimeMillis()) {
            this.performMusicCheck();
            this.TIME_TRACKER.CHECK_MUSIC.reset();
        }
    }

    public void resetItem() {
        this.DELAY_TRACKER.ITEM_INTERACTION.reset();
    }

    public void resetObject() {
        this.DELAY_TRACKER.NEW_OBJECT.reset();
    }

    public void resetEat() {
        this.INT_TRACKER.NEXT_EAT_AT.reset();
    }

    public void resetRun() {
        this.INT_TRACKER.NEXT_RUN_AT.reset();
    }

    public boolean canInteractItem() {
        return System.currentTimeMillis() > this.nextItemInteraction;
    }

    public boolean canInteractObject() {
        return System.currentTimeMillis() > this.nextObjectInteraction;
    }
}
