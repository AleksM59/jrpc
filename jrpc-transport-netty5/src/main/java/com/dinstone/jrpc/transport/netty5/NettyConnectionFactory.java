/*
 * Copyright (C) 2013~2017 dinstone<dinstone@163.com>
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
package com.dinstone.jrpc.transport.netty5;

import java.net.InetSocketAddress;

import com.dinstone.jrpc.transport.Connection;
import com.dinstone.jrpc.transport.ConnectionFactory;
import com.dinstone.jrpc.transport.TransportConfig;

public class NettyConnectionFactory implements ConnectionFactory {

    private NettyConnector connector;

    @Override
    public String getSchema() {
        return "netty5";
    }

    @Override
    public Connection create(TransportConfig transportConfig, InetSocketAddress sa) {
        if (connector == null) {
            connector = new NettyConnector(transportConfig);
        }
        return new NettyConnection(connector.createSession(sa), transportConfig);
    }

    @Override
    public void destroy() {
        if (connector != null) {
            connector.dispose();
            connector = null;
        }
    }

}
