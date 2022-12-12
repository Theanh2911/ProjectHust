public class Message {
    //truyền dữ liệu giữa các máy
    private String transmitterID;//ID của máy cần truyền
    private String message;//thông điệp cần truyền

    public Message(String transmitterID, String message) {
        this.transmitterID = transmitterID;
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }

    public String getTransmitterID() {
        return transmitterID;
    }

    public void setTransmitterID(String transmitterID) {
        this.transmitterID = transmitterID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
