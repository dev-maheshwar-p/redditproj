package reddit.constants;

public enum PROJECT_TYPE {

    REDDIT("REDDIT"), AMAZON("AMAZON"), REDDIT_DOWN_STREAM("REDDIT_DOWN_STREAM");

    String projectType;

    PROJECT_TYPE(String projectType){
        this.projectType = projectType;
    }
}
