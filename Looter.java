package EdgevillePKLooter;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name="EdgevillePKLooter", description = "Loots PK drops and banks at Edgeville", properties = "author=Tortidip; topic=999; client=4;")
public class Looter  extends PollingScript<ClientContext> {

    @Override
    public void start(){
        System.out.println("Starting script");
    }

    @Override
    public void stop(){
        System.out.println("Stopping script");
    }

    @Override
    public void poll() {
        //main loop

    }
}
