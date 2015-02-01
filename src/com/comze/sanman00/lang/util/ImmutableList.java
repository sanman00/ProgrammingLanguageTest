package com.comze.sanman00.lang.util;

import java.util.ArrayList;

public final class ImmutableList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;
    
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Cannot add to immutable list");
    }
    
    
}
