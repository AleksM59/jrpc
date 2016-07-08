/*
 * Copyright (C) 2012~2014 dinstone<dinstone@163.com>
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

import com.dinstone.jrpc.transport.AbstractConnectionFactory;
import com.dinstone.jrpc.transport.Connection;

public class NettyConnectionFactory extends AbstractConnectionFactory {

    @Override
    public String getSchema() {
        return "netty5";
    }

    @Override
    protected Connection createConnection(InetSocketAddress sa) {
        return new NettyConnection(sa, transportConfig);
    }

}