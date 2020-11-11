package com.springbootes001.es001;

import org.springframework.lang.Nullable;

public interface Persistable<ID> {
    @Nullable
    ID getId();

    boolean isNew();
}