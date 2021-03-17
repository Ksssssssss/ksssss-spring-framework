package github.ksssss.context;

import com.sun.xml.internal.ws.transport.http.ResourceLoader;
import github.ksssss.beans.factory.AutowireCapableBeanFactory;

/**
 * @author ksssss
 * @since 2021/3/9 23:37
 */
public interface ApplicationContext extends AutowireCapableBeanFactory,
        ResourceLoader, ApplicationEventPublisher {
}
