package com.krista.java8.test.methodreference;

import com.krista.java8.test.functionalinterface.ThreeParamFunction;
import com.krista.java8.test.model.Person;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * lambda 方法引用 App
 * 方法引用
 * 使用lambda表达式来创建匿名方法;
 * 但是有时,lambda表达式只会调用一次现有方法（而不做其他事）,在这些情况下，通过名称引用现有方法通常更清楚;
 * @author dw_wangdonghong
 * @date 2018/10/18 9:37
 */
public class App {

    public static void main(String[] args) {
        // Supplier<T> : () -> T
        // 等价于 () -> new Person()
        Supplier<Person> personSupplier = Person::new;

        // Function<T, R> : (T) -> R
        // 等价于 (String name) -> new Person(name)
        Function<String, Person> personFunction = Person::new;

        // BiFunction<T,U,R> : (T,U) -> R
        // 等价于 (Integer age, Integer sex) -> new Person(age,sex)
        BiFunction<Integer, Integer, Person> personBiFunction = Person::new;

        // ThreeParamFunctiona<T,U,P,R> : (T,U,P) -> R
        // 等价于 (Integer age, Integer sex, String name) -> new Person(age,sex,name)
        ThreeParamFunction<Integer, Integer, String, Person> personThreeParamFunctiona = Person::new;

        System.out.println(personSupplier.get().toString());
        System.out.println(personFunction.apply("wangdh").toString());
        System.out.println(personBiFunction.apply(27, 1).toString());
        System.out.println(personThreeParamFunctiona.apply(27, 1 ,"wangdh").toString());
    }
}
