
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CosRetrofitProxyManager implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private ConfigurableListableBeanFactory beanFactory;

    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        this.injectRetrofitService();
    }

    private void injectRetrofitService() {
        Reflections reflections = this.initReflections();
        Set<Class<?>> retrofitServiceInterfaces = reflections.getTypesAnnotatedWith(CosEndpoint.class);
        if (retrofitServiceInterfaces.size() > 0) {
            this.processInjectRetrofitService(retrofitServiceInterfaces);
        }
    }

    private void processInjectRetrofitService(Set<Class<?>> retrofitServiceInterfaces) {
        for (Class<?> clazz : retrofitServiceInterfaces) {

            CosHandlerContext cosHandlerContext = new CosHandlerContext();
            cosHandlerContext.setTargetInterface(clazz);
            cosHandlerContext.setEnvironment(environment);

            List<Class<?>> clazzList = new ArrayList<>();
            clazzList.add(clazz);
            Object targetInterface = Proxy.newProxyInstance(clazz.getClassLoader(), clazzList.toArray(new Class[0]), new CosInvocationHandler(cosHandlerContext));
            this.beanFactory.registerSingleton(clazz.getName(), targetInterface);
        }
    }

    private Reflections initReflections() {
        ConfigurationBuilder reflectionCfg = new ConfigurationBuilder();

        reflectionCfg.addUrls(ClasspathHelper.forPackage("com.njust", new ClassLoader[0]));

        FilterBuilder filterBuilder = new FilterBuilder();
        filterBuilder.include(FilterBuilder.prefix("com.njust"));
        reflectionCfg.filterInputsBy(filterBuilder);

        Reflections reflections = new Reflections(reflectionCfg);
        return reflections;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
