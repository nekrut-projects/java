public class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    protected void fillPlateFood(Plate plate) {
        plate.setFood(plate.getVolume());
    }
}
