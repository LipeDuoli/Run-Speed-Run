package br.com.duoli.sr4j.variables;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.duoli.sr4j.common.Link;

public class Variable {

    private String id;
    private String name;
    private String category;
    private VariableScope scope;
    private boolean mandatory;
    @SerializedName("user-defined")
    private boolean userDefined;
    private boolean obsoletes;
    private VariableValues values;
    @SerializedName("is-subcategory")
    private boolean subcategory;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public VariableScope getScope() {
        return scope;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isUserDefined() {
        return userDefined;
    }

    public boolean isObsoletes() {
        return obsoletes;
    }

    public VariableValues getValues() {
        return values;
    }

    public boolean isSubcategory() {
        return subcategory;
    }

    public List<Link> getLinks() {
        return links;
    }
}
