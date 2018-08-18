package com.luck.picture.lib.observable;

public interface SubjectListener {
    void add(ObserverListener observerListener);

    void remove(ObserverListener observerListener);
}
