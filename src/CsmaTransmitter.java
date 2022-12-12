import javax.swing.*;

public class CsmaTransmitter implements ICsmaTransmitter {
    //Máy cần truyền thông điệp

    private TransmitterInfo clientInfo;

    public CsmaTransmitter(TransmitterInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    //check máy đích có rảnh rỗi không
    @Override
    public boolean getSignalFree(CsmaReceiver csmaReceiver) {
        return csmaReceiver.process(new Message(clientInfo.getId(), "FREE_CHECK"));
    }

    //Bắt đầu truyền dữ liệu
    @Override
    public void sendMessage(CsmaReceiver csmaReceiver, Message message) {
        csmaReceiver.makeBusy();
        System.out.println("Đang xử lí tín hiệu...");
        Timer timer = new Timer(5000, e -> {
            //hoãn 5s để truyền dữ liệu
            csmaReceiver.process(message);
            csmaReceiver.makeFree();
        });
        timer.start();
        timer.setRepeats(false);
    }

    @Override
    public void processMessage(String message) {

    }

    public TransmitterInfo getTransmitterInfo() {
        return clientInfo;
    }

    public void setTransmitterInfo(TransmitterInfo clientInfo) {
        this.clientInfo = clientInfo;
    }
}
