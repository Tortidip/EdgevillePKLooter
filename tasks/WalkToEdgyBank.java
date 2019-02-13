package EdgevillePKLooter.tasks;

import EdgevillePKLooter.Task;
import EdgevillePKLooter.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Path;

import java.util.concurrent.Callable;

public class WalkToEdgyBank extends Task {

    public Walker walker;
    public Tile bankTile;

    public WalkToEdgyBank(ClientContext ctx, Tile bankTile) {
        super(ctx);
        walker = new Walker(ctx);
        this.bankTile = bankTile;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 && ctx.players.local().tile().distanceTo(bankTile)>6;
    }

    @Override
    public void execute() {
        Tile [] path = new Tile[]{bankTile};
        walker.walkPath(path);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().tile().distanceTo(bankTile)<6;
            }
        },500,40);
    }
}
