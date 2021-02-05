public class Cat {
    private String name;
    private int appetite;
    private boolean full;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        full = false;
    }

    public void eat(Plate p) {
        if (isCanEat(p)) {
            p.decreaseFood(appetite);
            full = true;
            System.out.printf("Кот %s поел и наелся!\n", name);
        } else if (isCatFull()){
            System.out.printf("Кот %s сытый и не хочет есть!\n", name);
        } else {
            System.out.printf("Кот %s голоден!\n", name);
        }
    }

    public boolean isCanEat(Plate plate) {
        return plate.getFood() >= appetite && !isCatFull();
    }

    public boolean isCatFull() {
        return full;
    }
}
