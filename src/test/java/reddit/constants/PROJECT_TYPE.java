package reddit.constants;

public enum PROJECT_TYPE {

    REDDIT("REDDIT"), AMAZON("AMAZON");

    String projectType;

    PROJECT_TYPE(String dcType){
        this.projectType = dcType;
    }
}
