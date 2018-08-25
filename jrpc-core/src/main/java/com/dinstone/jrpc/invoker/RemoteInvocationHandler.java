/*
 * Copyright (C) 2014~2017 dinstone<dinstone@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dinstone.jrpc.invoker;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import com.dinstone.jrpc.protocol.Call;
import com.dinstone.jrpc.transport.Connection;
import com.dinstone.jrpc.transport.ConnectionManager;

public class RemoteInvocationHandler implements InvocationHandler {

    private ConnectionManager connectionManager;

    public RemoteInvocationHandler(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public <T> Object handle(Invocation<T> invocation) throws Throwable {
        String group = invocation.getGroup();
        int timeout = invocation.getTimeout();
        Class<?> service = invocation.getService();

        Method method = invocation.getMethod();
        Object[] args = invocation.getParams();

        Call call = new Call(service.getName(), group, timeout, method.getName(), args, method.getParameterTypes());
        Connection connection = connectionManager.getConnection(invocation.getServiceAddress());
        return connection.call(call).get(timeout, TimeUnit.MILLISECONDS);
    }

}
