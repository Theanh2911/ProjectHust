public interface ICsmaTransmitter{
    public boolean getSignalFree(CsmaReceiver csmaReceiver);

    public void sendMessage(CsmaReceiver csmaReceiver, Message message);

    public void processMessage(String message);
}
