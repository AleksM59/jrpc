/*
 * Copyright (C) 2012~2016 dinstone<dinstone@163.com>
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

package com.dinstone.jrpc.server;

import com.dinstone.jrpc.Configuration;
import com.dinstone.jrpc.service.DefaultServiceHandler;
import com.dinstone.jrpc.service.ServiceHandler;

public abstract class AbstractServer implements Server {

    protected Configuration config = new Configuration();

    protected ServiceHandler handler = new DefaultServiceHandler();

    public AbstractServer() {
        this(null);
    }

    public AbstractServer(ServiceHandler handler) {
        if (handler != null) {
            this.handler = handler;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     * @see com.dinstone.jrpc.server.Server#regist(java.lang.Class, java.lang.Object)
     */
    public <T> Server regist(Class<T> serviceInterface, T serviceObject) {
        handler.regist(serviceInterface, serviceObject);

        return this;
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }

    public String getServiceHost() {
        return config.getServiceHost();
    }

    public int getServicePort() {
        return config.getServicePort();
    }

}