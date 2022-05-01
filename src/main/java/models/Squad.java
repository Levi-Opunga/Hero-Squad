package models;

public class Squad {
    private String name;
    private Integer maxsize;
    private  String cause;

    public Squad(String name, Integer maxsize, String cause) {
        this.name = name;
        this.maxsize = maxsize;
        this.cause = cause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(Integer maxsize) {
        this.maxsize = maxsize;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
