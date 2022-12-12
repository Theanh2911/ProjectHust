import java.util.Set;
public class CsmaReceiver {
    private Set<ICsmaTransmitter> csmaTransmitterObservers;
    private boolean isFree = true;

    public void addObserver(ICsmaTransmitter observer) {
        csmaTransmitterObservers.add(observer);
    }

    public void removeObserver(ICsmaTransmitter observer) {
        csmaTransmitterObservers.remove(observer);
    }

    public void notify(String message) {
        for (ICsmaTransmitter client : csmaTransmitterObservers) {
            client.processMessage(message);
        }
    }

    public void notify(String clientID, String message) {
        for (ICsmaTransmitter client : csmaTransmitterObservers) {
            if (((CsmaTransmitter) client).getTransmitterInfo().getId().equals(clientID)) {
                client.processMessage(message);
            }
        }
    }

    public Set<ICsmaTransmitter> getCsmaTransmitterObservers() {
        return csmaTransmitterObservers;
    }

    public void setCsmaTransmitterObservers(Set<ICsmaTransmitter> csmaTransmitterObservers) {
        this.csmaTransmitterObservers = csmaTransmitterObservers;
    }


    public boolean process(Message message) {
        if (message.getMessage().equals("FREE_CHECK")) {
            return isFree();
        } else {
            System.out.println(
                    String.format("Truyền thành công từ [%s] - tín hiệu [%s]",
                            message.getTransmitterID(),
                            message.getMessage()));
            return true;
        }
    }

    public void makeBusy() {
        setFree(false);
    }

    public void makeFree() {
        setFree(true);
    }

    private void setFree(boolean free) {
        synchronized (this) {
            isFree = free;
        }
    }

    public boolean isFree() {
        return isFree;
    }
}
