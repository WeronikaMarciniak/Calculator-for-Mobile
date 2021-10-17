package com.example.myapplication;

public enum CalcOperation {
    ADD("ADD"),
    SUB("SUB"),
    DIV("DIV"),
    MUL("MUL"),
    NONE("NONE"),
    SIN("SIN"),
    COS("COS"),
    TAN("TAN"),
    LOGN("LOGN"),
    LOG("LOG"),
    SQRT("SQRT"),
    POW2("POW2"),
    POWY("POWY");

    private final String operation;

    CalcOperation (String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation;
    }
}