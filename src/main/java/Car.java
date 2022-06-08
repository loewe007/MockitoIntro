import java.util.ArrayList;
import java.util.List;

public class Car {
    private String manufactrer;
    private String number;
    private String owner;
    private List<String> owners = new ArrayList<String>();

    public List<String> getOwners() {
        return owners;
    }

    public String getManufactrer() {
        return manufactrer;
    }

    public void setManufactrer(String manufactrer) {
        this.manufactrer = manufactrer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car(String manufactrer, String number, String owner) {
        if(manufactrer == null)
            throw new IllegalArgumentException("manufactrer can not by null");
        if(number == null)
            throw new IllegalArgumentException("number can not by null");
        if(owner == null)
            throw new IllegalArgumentException("owner can not by null");

        this.manufactrer = manufactrer;
        this.number = number;
        this.owner = owner;

        addOwner(owner);
    }

    private void addOwner(String owner){
        if(!owners.contains(owner))
            owners.add(owner);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        addOwner(owner);

        this.owner = owner;
    }
}
