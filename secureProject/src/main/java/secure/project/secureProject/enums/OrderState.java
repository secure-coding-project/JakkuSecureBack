package secure.project.secureProject.enums;

public enum OrderState {

    READY(1),
    REFUND(2),
    COMPLETE(3);

    private final int weight;

    OrderState(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

}
