/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EncDec;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import modelo.mensaje;

/**
 *
 * @author PC
 */
public class EncoderMensaje implements Encoder.TextStream<mensaje> {

    @Override
    public void encode(mensaje sms, Writer writer) throws
            EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("nombre", sms.getNombre())
                .add("mensaje", sms.getMensaje()).build();
        JsonWriter jWriter = Json.createWriter(writer);
        jWriter.writeObject(json);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
