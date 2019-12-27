/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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

import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

public class StringMessageClientProcessor implements MessageProcessor<String> {
	
	private AioSession<String> session;

	@Override
	public void process(AioSession<String> session, String msg) {
		System.out.println("接受到服务端响应数据：" + msg);
	}

	@Override
	public void stateEvent(AioSession<String> session, StateMachineEnum stateMachineEnum, Throwable throwable) {
		switch (stateMachineEnum) {
		case NEW_SESSION:
			this.session = session;
			break;
		default:
			System.out.println("other state:" + stateMachineEnum);
		}

	}

	public AioSession<String> getSession() {
		return session;
	}
	
}
