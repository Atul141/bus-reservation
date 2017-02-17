package Services;

import ServiceImpl.ConfigDB;

import java.io.IOException;

public class ExecuteReminderMessageService implements Runnable {

    private ConfigDB configDB;
    private Thread t;
    private ReminderMessageService reminderMessageService;

    public ExecuteReminderMessageService(ConfigDB configDB) {
        this.configDB = configDB;
        reminderMessageService =new ReminderMessageService(configDB);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000 * 30);
                reminderMessageService.sendReminderMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
