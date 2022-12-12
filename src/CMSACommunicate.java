import java.io.Closeable;
import java.io.IOException;
//Xử lí việc giao tiếp giữa các máy dùng CSMA

public class CMSACommunicate implements Closeable {
    private CsmaReceiver csmaReceiver;
    private CsmaTransmitter csmaTransmitter;

    //Khởi tạo máy truyền và máy nhận
    public CMSACommunicate(CsmaReceiver csmaReceiver, CsmaTransmitter csmaTransmitter) {
        this.csmaReceiver = csmaReceiver;
        this.csmaReceiver.addObserver(csmaTransmitter);
        this.csmaTransmitter = csmaTransmitter;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> close())); //đóng kết nối khi tắt
    }

    @Override
    public void close() {
        csmaReceiver.removeObserver(csmaTransmitter);
    }//đóng kết nối sau khi truyền xong data
}
