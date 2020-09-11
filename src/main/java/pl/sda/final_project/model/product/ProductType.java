package pl.sda.final_project.model.product;


public enum ProductType {
    FOOD("jadalne"),
    NOT_FOOD("nie jadalne");
    private String plName;

    ProductType(String plName) {
        this.plName = plName;
    }

    public String getPlName() {
        return plName;
    }

}
