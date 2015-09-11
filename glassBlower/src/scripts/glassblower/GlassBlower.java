package scripts.glassblower;

import java.util.ArrayList;

import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import scripts.glassblower.nodes.Node;
import scripts.glassblower.nodes.actions.DepositItems;
import scripts.glassblower.nodes.actions.OpenBank;
import scripts.glassblower.nodes.actions.WithdrawItems;
import scripts.glassblower.nodes.actions.clickHumidify;

@ScriptManifest(authors = { "JoeDezzy" }, category = "Crafting", name = "Glassblowing")
public class GlassBlower extends Script {

    private ArrayList<Node> nodes = new ArrayList<Node>();
    //our list of nodes to cycle through

    public static Antiban antiban = new Antiban();
    //Initializes a new antiban object

    public static boolean running = true;

    //*a public static boolean
    /*
     * to be accessed anywhere incase w something goes wrong and we need our
     * script to stop
     */

    @Override
    public void run() {
        this.initializeNodes();

        while (running) {
            antiban.start();
            /*
             * While we want to run cycle through our nodes
             */
            for (Node node : this.nodes) {
                /*
                 * If our node condition returns true, Execute our wanted action
                 */
                if (node.isValid()) {
                    this.println(node.getStatus());
                    node.execute();
                    break;
                }
            }
            /*
             * It is important to place sleeps in while loops, as it is running
             * through this piece of code at very fast rates, thus hogging up
             * the CPU Usage a ton. You do not have to sleep long, just limit
             * the times it runs through the code
             */
            this.sleep(200);
        }
    }

    private void initializeNodes() {
        /*
         * Filling our node arrayList with all of our nodes note, the order you
         * do this in can play a role in how your script is going to function
         */
        this.nodes.add(new OpenBank());
        this.nodes.add(new WithdrawItems());
        this.nodes.add(new clickHumidify()); //also closes bank interface
        this.nodes.add(new DepositItems());

    }
}