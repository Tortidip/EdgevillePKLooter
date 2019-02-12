package EdgevillePKLooter;

import EdgevillePKLooter.tasks.Bank;
import EdgevillePKLooter.tasks.Loot;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;


@Script.Manifest(name="EdgevillePKLooter", description = "Loots PK drops and banks at Edgeville. Starts at Edgeville.", properties = "author=Tortidip; topic=999; client=4;")
public class Looter  extends PollingScript<ClientContext> {

    public ArrayList<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        System.out.println("Starting script");
        Task bank = new Bank(ctx);
        Task loot = new Loot(ctx);

        taskList.add(bank);
        taskList.add(loot);
    }

    @Override
    public void stop(){
        System.out.println("Stopping script");
    }

    @Override
    public void poll() {
        //main loop
        for(Task task : taskList){
            if(task.activate()){
                task.execute();
                break;
            }
        }
    }
}
