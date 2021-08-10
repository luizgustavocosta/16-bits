package com.costa.luiz.generics.basic;

import java.util.Collections;
import java.util.List;

public class Wildcard {

    public List<?> myComplexQuery() {
        //Returns a heterogeneous object
        return Collections.emptyList();
    }

    public List<String> myComplexQueryButExpectedReturn() {
        //Returns a heterogeneous object
        return Collections.emptyList();
    }
}
