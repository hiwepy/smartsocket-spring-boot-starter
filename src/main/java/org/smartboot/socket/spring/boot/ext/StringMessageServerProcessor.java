/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.smartboot.socket.spring.boot.ext;

import java.io.IOException;

import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

public class StringMessageServerProcessor implements MessageProcessor<String> {

	@Override
	public void process(AioSession<String> session, String msg) {
		String respMsg = msg + 1;
		System.out.println("接受到客户端数据：" + msg + " ,响应数据:" + (respMsg));
		try {
			session.write(respMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stateEvent(AioSession<String> session, StateMachineEnum stateMachineEnum, Throwable throwable) {
		
	}

}
