package com.codegym.service.impl;

import java.util.List;

public interface GeneralService<E> {
    Iterable<E> findAllHaveBusiness();

    E findByIdHaveBusiness(long id);

    void saveHaveBusiness(E e);

    void removeHaveBusiness(long id);


}

