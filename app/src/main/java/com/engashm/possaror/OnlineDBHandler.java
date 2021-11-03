package com.engashm.possaror;

public abstract class OnlineDBHandler<T> {

    private IBuilder iBuilder;

    public OnlineDBHandler(IBuilder<T> iBuilder){}




    interface IBuilder<T>{
        T build();
    }

}
