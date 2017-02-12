package Services;


import ServiceImpl.ConfigDB;

public class ExecuteAutoCancelService implements Runnable {
    private AutoCancellationService autoCancellationService;
    private ConfigDB configDB;
    private Thread t;

    public ExecuteAutoCancelService(ConfigDB configDB) {
        this.configDB = configDB;
        autoCancellationService = new AutoCancellationService(this.configDB);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000 * 5);
                autoCancellationService.autoCancelOrder();
            } catch (InterruptedException e) {
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
