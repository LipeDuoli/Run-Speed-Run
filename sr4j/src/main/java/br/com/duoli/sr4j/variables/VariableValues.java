package br.com.duoli.sr4j.variables;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

class VariableValues {

    @SerializedName("default")
    private String _default;
    private Map<String, VariableValue> values;

    public String getDefault() {
        return _default;
    }

    public Map<String, VariableValue> getValues() {
        return values;
    }
}
