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
import org.smartboot.socket.extension.decoder.DelimiterFrameDecoder;
import org.smartboot.socket.transport.AioSession;

/**
 * http://smartsocket.mydoc.io/?t=281156
 */
public class DelimiterProtocol implements Protocol<String> {

    //结束符\r\n
    private static final byte[] DELIMITER_BYTES = new byte[]{'\r', '\n'};

    @Override
    public String decode(ByteBuffer buffer, AioSession<String> session, boolean eof) {
        DelimiterFrameDecoder delimiterFrameDecoder;
        if (session.getAttachment() == null) {//构造指定结束符的临时缓冲区
            delimiterFrameDecoder = new DelimiterFrameDecoder(DELIMITER_BYTES, 64);
            session.setAttachment(delimiterFrameDecoder);//缓存解码器已应对半包情况
        } else {
            delimiterFrameDecoder = (DelimiterFrameDecoder) session.getAttachment();
        }

        //未解析到DELIMITER_BYTES则返回null
        if (!delimiterFrameDecoder.decode(buffer)) {
            return null;
        }
        //解码成功
        ByteBuffer byteBuffer = delimiterFrameDecoder.getBuffer();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        session.setAttachment(null);//释放临时缓冲区
        return new String(bytes);
    }

    @Override
    public ByteBuffer encode(String msg, AioSession<String> session) {
        byte[] bytes = msg.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length + DELIMITER_BYTES.length);
        buffer.put(bytes).put(DELIMITER_BYTES);
        buffer.flip();
        return buffer;
    }
}