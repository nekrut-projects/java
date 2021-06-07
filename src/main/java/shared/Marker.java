package shared;

public enum Marker {
    END_OPERATION(Byte.parseByte("10")), UPLOAD_FILE(Byte.parseByte("33")),
    DOWNLOAD_FILE(Byte.parseByte("44")), SEND_LIST_FILES(Byte.parseByte("11"));
    private byte value;

    private Marker(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
