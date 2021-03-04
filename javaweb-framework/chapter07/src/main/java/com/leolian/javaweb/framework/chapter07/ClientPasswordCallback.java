package com.leolian.javaweb.framework.chapter07;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

@Component("clientPasswordCallback")
public class ClientPasswordCallback implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
        callback.setPassword("clientpass");
    }

}