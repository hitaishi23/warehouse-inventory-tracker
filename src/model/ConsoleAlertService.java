public class ConsoleAlertService implements AlertService {

    @Override
    public void sendAlert(String message) {
        System.out.println("⚠️ Restock Alert: " + message);
    }
}
