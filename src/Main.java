import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        CsmaReceiver csmaReceiver = new CsmaReceiver();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hãy nhập số lượng máy cần truyền: ");
        int numberNode = scanner.nextInt();

        //xử lí cùng lúc nhiều máy truyền
        ExecutorService executorService = Executors.newFixedThreadPool(numberNode);

        //khởi tạo nhiều máy truyền 1 lúc
        for (int i = 0; i < numberNode; i++) {
            final CsmaTransmitter client = new CsmaTransmitter(new TransmitterInfo("Máy thứ " + (i + 1), "Máy thứ " + (i + 1)));
            executorService.submit(() -> {

                new ApplicationPanel(csmaReceiver, client).setVisible(true);

            });
        }
        executorService.shutdown();
    }
}
