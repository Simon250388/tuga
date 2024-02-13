package com.ecarx.systemui.plugin.utils;

import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class KtList<E> extends ArrayList<E> {
    public KtList(E... eArr) {
        super(eArr.length);
        addAll(Arrays.asList(eArr));
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return get(size() - 1);
    }

    public E indexOfNext(E e) {
        int i;
        int indexOf = indexOf(e);
        if (indexOf != -1 && (i = indexOf + 1) <= size() - 1) {
            return get(i);
        }
        return null;
    }

    public E indexOfNextOrFirst(E e) {
        int indexOf = indexOf(e);
        if (indexOf == -1) {
            return null;
        }
        int i = indexOf + 1;
        if (i > size() - 1) {
            return first();
        }
        return get(i);
    }

    public E indexOfPrevious(E e) {
        int i = 0;
        int indexOf = indexOf(e);
        if (indexOf != -1 && indexOf - 1 >= 0) {
            return get(i);
        }
        return null;
    }

    public E indexOfPreviousOrLast(E e) {
        int indexOf = indexOf(e);
        if (indexOf == -1) {
            return null;
        }
        int i = indexOf - 1;
        if (i < 0) {
            return last();
        }
        return get(i);
    }
}
