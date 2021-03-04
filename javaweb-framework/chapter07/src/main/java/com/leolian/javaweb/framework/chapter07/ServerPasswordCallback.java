package com.leolian.javaweb.framework.chapter07;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

@Component("serverPasswordCallback")
public class ServerPasswordCallback implements CallbackHandler {

    private static final Map<String, String> userMap = new HashMap<String, String>();

    static {
        userMap.put("client", "clientpass");
        userMap.put("server", "serverpass");
    }

    @Override
    public void handle(Callback[] callbacks) {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
        String clientUsername = callback.getIdentifier();
        String serverPassword = userMap.get(clientUsername);
        if (serverPassword != null) {
            callback.setPassword(serverPassword);
        }
    }

}