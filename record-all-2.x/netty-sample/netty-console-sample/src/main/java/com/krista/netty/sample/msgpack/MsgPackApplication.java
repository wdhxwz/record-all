package com.krista.netty.sample.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MsgPackApplication
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/22 18:19
 */
public class MsgPackApplication {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("json");

        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(src);

        List<String> dest = messagePack.read(bytes, Templates.tList(Templates.TString));
        dest.forEach(System.out::println);
    }
}
