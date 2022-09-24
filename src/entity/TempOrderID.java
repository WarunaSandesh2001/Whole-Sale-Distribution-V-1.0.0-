package entity;

public class TempOrderID {

    private String oId;

    public TempOrderID() {
    }

    public TempOrderID(String oId) {
        this.oId = oId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    @Override
    public String toString() {
        return "TempOrderID{" +
                "oId='" + oId + '\'' +
                '}';
    }
}
