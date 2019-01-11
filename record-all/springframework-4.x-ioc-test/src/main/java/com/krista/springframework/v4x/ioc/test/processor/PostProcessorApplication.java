package com.krista.springframework.v4x.ioc.test.processor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * PostProcessorApplication：
 * <p>
 *     ApplicationContextAwareProcessor设置Aware相关接口
 *     CommonAnnotationBeanPostProcessor
 *     AutowiredAnnotationBeanPostProcessor
 *     RequiredAnnotationBeanPostProcessor
 *     PersistenceAnnotationBeanPostProcessor
 *     AbstractAutoProxyCreator
 *     BeanValidationPostProcessor
 *     MethodValidationPostProcessor
 *     ScheduledAnnotationBeanPostProcessor
 *     AsyncAnnotationBeanPostProcessor
 *     ServletContextAwareProcessor
 *     https://jinnianshilongnian.iteye.com/blog/1492424
 * </p>
 * <p>
 * ApplicationContext Bean生命周期(初始化)
 * 1)BeanFactoryPostProcessor.postProcessBeanFactory(未实例化前,可以修改BeanFactory中的BeanDefination)
 * 2)
 * 3)
 * 4)BeanNameAware.setBeanName（实现BeanNameAware接口的Bean）
 * 5)BeanFactoryAware.setBeanFactory(实现BeanFactoryAware接口的Bean)
 * 6)BeanPostProcessor.postProcessBeforeInitialization
 * 7)Initailization.afterPropertiesSet(实现Initailization接口的Bean)
 * 8)Bean指定的init方法
 * 9)BeanPostProcessor.postProcessAfterInitialization
 * </p>
 * <p>
 * <p>
 * 实例化之前
 * InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation 执行标志着一个对象生命周期的开始
 * <p>
 * InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation processorObject
 * SmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors processorObject
 * 实例化与实例化之后
 * 执行构造方法，实例化对象 ProcessorObject structure（）
 * <p>
 * 执行MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition processorObject
 * <p>
 * 执行MergedBeanDefinitionPostProcessor.postProcessAfterInstantiation 标识对象实例化操作结束 InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation processorObject
 * <p>
 * 参数注入 6. InstantiationAwareBeanPostProcessor.postProcessPropertyValues processorObject pvs : PropertyValues: length=0 pds : 参数注入
 * <p>
 * 基本Awate对象注入，详情请看关于Awate深入解读章节 BeanNameAware : processorObject
 * 初始化
 * 先执行@PostConstruct
 * <p>
 * 在执行BeanPostProcessor.postProcessBeforeInitialization BeanPostProcessor.postProcessBeforeInitialization
 * <p>
 * 然后执行InitializingBean.afterPropertiesSet
 * <p>
 * 在执行BeanPostProcessor.postProcessAfterInitialization BeanPostProcessor.postProcessAfterInitialization processorObject
 * <p>
 * 最后执行DestructionAwareBeanPostProcessor.requiresDestruction ， 执行之后，才能使用。 DestructionAwareBeanPostProcessor.requiresDestruction com.niaocaia.blog.spring.connet.beanLife.ProcessorObject@769e7ee8
 * <p>
 * 销毁
 * 先执行执行@preDestroy javax.annotation.preDestroy com.niaocaia.blog.spring.connet.beanLife.ProcessorObject
 * <p>
 * 在执行DestructionAwareBeanPostProcessor 对象的postProcessBeforeDestruction DestructionAwareBeanPostProcessor.postProcessBeforeDestruction processorObject
 * <p>
 * 最后执行 DisposableBean接口的destroy实现方法，整个baen生命周期结束 DisposableBean destroy com.niaocaia.blog.spring.connet.beanLife.ProcessorObject
 * <p>
 * 总结
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @PostConstruct与@preDestroy的执行优先DisposableBean与InitializingBean
 * @PostConstruct在BeanPostProcessor.postProcessBeforeInitialization之前执行，而InitializingBean在BeanPostProcessor.postProcessBeforeInitialization之后执行
 * @preDestroy在DestructionAwareBeanPostProcessor.postProcessBeforeDestruction之前执行，而DisposableBean在DestructionAwareBeanPostProcessor.postProcessBeforeDestruction之后执行
 * @PostConstruct与@preDestroy在一个类里面，可以标识在多个方法上。 </p>
 * @since 2019/1/11 9:57
 */
public class PostProcessorApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostProcessorConfiguration.class);
        Person person = applicationContext.getBean(Person.class);

        System.out.println(person.toString());
    }
}
