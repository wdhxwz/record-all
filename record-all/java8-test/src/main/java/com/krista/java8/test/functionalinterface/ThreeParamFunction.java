package com.krista.java8.test.functionalinterface;

/**
 * ThreeParamFunctionalInterface
 *
 * @author dw_wangdonghong
 * @date 2018/10/18 9:44
 */
public interface ThreeParamFunction<T, U, P, R> {
    /**
     * apply
     *
     * @param t
     * @param u
     * @param p
     * @return R
     * @author dw_wangdonghong
     * @date 2018/10/18 9:52
     */
    R apply(T t, U u, P p);
}
