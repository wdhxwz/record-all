package com.krista.java8.test.functionalinterface;

/**
 * 函数式接口:只有一个抽象方法的接口
 * <br/> 内置的函数式接口：java.util.function
 * <br/> 常用的函数式接口：
 * <br/> Predicate<T> : (T) -> boolean
 * <br/> Supplier<T> : () -> T
 * <br/> Function<T, R> : (T) -> R
 * <br/> Consumer<T> : (T) -> void
 *
 * <br/> BiPredicate<T, U> : (T,U) -> boolean
 * <br/> BiFunction<T, U, R> : (T,U) -> R
 * <br/> BiConsumer<T, U> : (T,U) -> void
 * @author: dw_wanghonghong
 * @Date: 2018/10/15 20:16
 */
@FunctionalInterface
public interface Functional {
    /**
     * 功能描述:
     */
    void method();

    /**
     * @FunctionalInterface注解的必要性: 起初,接口A只有一个抽象方法,满足函数式接口的定义,因此可以使用lambda表达式表示;
     * 随着功能的拓展,接口A需要新增抽象方法,这种情况下,原先使用lambda表达式的地方都需要修改,影响范围较大
     * 使用上@FunctionalInterface注解,则表明该接口是函数式接口,不能添加第二个抽象方法
     * 当需要拓展A接口功能时,可以使用接口的默认方法实现
     */
    default void defaultMethod(){
        System.out.println("I am defaultMethod");
    }
}
