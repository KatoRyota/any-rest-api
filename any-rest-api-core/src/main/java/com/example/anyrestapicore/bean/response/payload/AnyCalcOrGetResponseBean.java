package com.example.anyrestapicore.bean.response.payload;

public class AnyCalcOrGetResponseBean {

    private String id;
    private String name;
    private String type;
    private String partialId;
    private String partialName;
    private String partialType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPartialId() {
        return partialId;
    }

    public void setPartialId(String partialId) {
        this.partialId = partialId;
    }

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public String getPartialType() {
        return partialType;
    }

    public void setPartialType(String partialType) {
        this.partialType = partialType;
    }
}
