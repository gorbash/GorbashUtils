package com.gorbash.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Gorbash on 2014-12-22.
 *
 * CyclicContainer provides functionality of cyclic iterating over provided collection.
 * Class is not thread-safe.
 */
public class CyclicContainer<E> {

    private List<E> list;
    private int index;

    public CyclicContainer(Collection<E> inputCollection) {
        list = new ArrayList<E>();
        list.addAll(inputCollection);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E getNext() {
        if (list.isEmpty())
            throw new EmptyContainerException();
        else {
            E el = list.get(index);
            index++;
            if (index == list.size())
                index = 0;
            return el;
        }
    }

    public int size() {
        return list.size();
    }
}
