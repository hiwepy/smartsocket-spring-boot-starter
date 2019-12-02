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

import java.nio.ByteBuffer;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

public class StringProtocol implements Protocol<String> {

	@Override
	public String decode(ByteBuffer buffer, AioSession<String> session, boolean eof) {
		return buffer.asCharBuffer().toString();
	}
	
	@Override
	public ByteBuffer encode(String msg, AioSession<String> session) {

		ByteBuffer buffer = ByteBuffer.allocate(msg.length());
		buffer.put(msg.getBytes());
		buffer.flip();

		return buffer;
	}

}
