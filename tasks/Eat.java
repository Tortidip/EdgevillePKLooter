package EdgevillePKLooter.tasks;

import EdgevillePKLooter.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;


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
        Item[] inventory = ctx.inventory.items();
        final int health = ctx.combat.health();
        if(inventory!=null){
            for(Item i : inventory){
                for(int j=0;j<foodIdList.length;j++){
                    if(i.id()==foodIdList[j]){
                        i.interact("Eat");
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return ctx.combat.health()> health;
                            }
                        },300,10);
                        break;
                    }
                }
            }
        }
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
