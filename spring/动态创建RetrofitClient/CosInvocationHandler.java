package com.zte.iot.snapshot.rpc.config;

import com.zte.iot.common.util.EmptyJudge;
import com.zte.iot.model.retrofit.RetrofitClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class CosInvocationHandler implements InvocationHandler {

    private final CosHandlerContext cosHandlerContext;

    public CosInvocationHandler(CosHandlerContext cosHandlerContext) {
        this.cosHandlerContext = cosHandlerContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        Class<?> targetInterface = cosHandlerContext.getTargetInterface();
        CosEndpoint cosEndpoint = targetInterface.getAnnotation(CosEndpoint.class);
        String ipKey = cosEndpoint.ipKeyInEnv();
        String portKey = cosEndpoint.portKeyInEnv();

        Environment environment = cosHandlerContext.getEnvironment();
        String ip = environment.getProperty(ipKey);
        String port = environment.getProperty(portKey);

        String url = constructCosUrl(ip, port);

        Retrofit retrofit = RetrofitClient.getClient().getRetrofit(url);
        Object target = retrofit.create(targetInterface);

        Object resultObject = method.invoke(target, args);
        return resultObject;
    }

    private String constructCosUrl(String cosIp, String cosPort) {
        if (!EmptyJudge.isEmpty(cosIp) && cosIp.contains(":")) {
            cosIp = "[" + cosIp + "]";
        }
        log.info("cos ip and port are {}, {}", cosIp, cosPort);
        return String.format("https://%s:%s", cosIp, cosPort);
    }
}
