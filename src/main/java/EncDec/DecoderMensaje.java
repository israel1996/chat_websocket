/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EncDec;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import modelo.mensaje;

/**
 *
 * @author PC
 */
public class DecoderMensaje implements Decoder.TextStream<mensaje> {

    @Override
    public mensaje decode(Reader reader) throws DecodeException,
            IOException {
        mensaje sms = new mensaje();
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject json = jsonReader.readObject();
        sms.setNombre(json.getString("nombre"));
        sms.setMensaje(json.getString("mensaje"));
        return sms;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
