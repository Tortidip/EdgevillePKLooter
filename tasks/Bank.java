package EdgevillePKLooter.tasks;

import EdgevillePKLooter.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;


import java.util.concurrent.Callable;

public class Bank extends Task {
    public Tile bankTile = new Tile(3094,3491,0);
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 && ctx.players.local().tile().distanceTo(bankTile)< 6;
    }

    @Override
    public void execute() {
        if(ctx.bank.opened()){
            if(ctx.bank.depositInventory()){
                final int inventCount = ctx.inventory.select().count();
                Condition.wait(new Callable<Boolean>(){
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() != inventCount;
                    }
                }, 250, 20);
                ctx.bank.close();
            }
        } else {
            if(ctx.bank.inViewport()) {
                if(ctx.bank.open()){
                    Condition.wait(new Callable<Boolean>(){
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }

    }
}

