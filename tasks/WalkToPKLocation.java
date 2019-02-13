package EdgevillePKLooter.tasks;

import EdgevillePKLooter.Task;
import EdgevillePKLooter.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class WalkToPKLocation extends Task {

    public Tile pkLocation;
    public Walker walker;

    public WalkToPKLocation(ClientContext ctx, Tile pkLocation) {
        super(ctx);
        this.pkLocation = pkLocation;
        this.walker = new Walker(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()<27&&ctx.players.local().tile().distanceTo(pkLocation)>20;
    }

    @Override
    public void execute() {
        Tile[] path = new Tile[]{pkLocation};
        walker.walkPath(path);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().tile().distanceTo(pkLocation)<6;
            }
        },500,40);
    }
}
