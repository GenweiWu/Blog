import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
*@Component才会触发setApplicationContext呗调用
* 但是为了避免循环依赖等问题，调用的时候用静态方法SpringUtil.getBean
*
*/
@Component
public class SpringUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
    private synchronized static void setContext(ApplicationContext context)
    {
        SpringUtil.applicationContext = context;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.
     * ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0)
        throws BeansException
    {
        setContext(arg0);
    }
    
    // 获取applicationContext
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    // 通过name获取 Bean.
    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }
    
    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }
    
    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz)
    {
        return getApplicationContext().getBean(name, clazz);
    }
}
