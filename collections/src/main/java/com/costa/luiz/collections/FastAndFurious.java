package com.costa.luiz.collections;

// Based on RebelLabs sheet
public class FastAndFurious {

    public void constantTime() {
                       //Access by index Search Insert
        //ArrayList            o(1)     o(n)   o(n)
        //HashSet              o(1)     o(1)   o(1)
        //HashMap              o(1)     o(1)   o(1)
        //TreeMap              o(log(n))o(log(n))o(log(n))

        //O1 - Constant time, really fast, doesnt depend on the size of your collection
        //O(log(n)) - Pretty fast, your collection size has to be extreme to notice a performance impact
        //O(n) - Linear to your collection size: The larger your collection is, the slower your operation will be

    }

    public void prettyFast() {
        //TreeMap              o(log(n))o(log(n))o(log(n))
    }

    public void linear() {
        //ArrayList            o(1)     o(n)   o(n)
    }
}
