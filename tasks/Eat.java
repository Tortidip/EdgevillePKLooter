package EdgevillePKLooter.tasks;

import EdgevillePKLooter.Task;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.List;

public class Eat extends Task {

    public int[]foodIdList={};
    public Eat(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return getHealthPercentage()<0.5 && containsEdibles();
    }

    @Override
    public void execute() {

    }

    public double getHealthPercentage(){
        return ctx.combat.health()/ctx.combat.maxHealth();
    }

    public boolean containsEdibles(){
        Item [] inventory = ctx.inventory.items();
        if(inventory!=null){
            for(Item i : inventory){
                for(int j =0 ; j<foodIdList.length; j++){
                    if(i.id()==foodIdList[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
