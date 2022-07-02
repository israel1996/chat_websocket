/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webSocket;

import EncDec.DecoderMensaje;
import EncDec.EncoderMensaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import modelo.mensaje;

/**
 *
 * @author PC
 */
@ServerEndpoint(value = "/wsSms", encoders = {EncoderMensaje.class},
        decoders = {DecoderMensaje.class})
public class wsSms {

    private static final List<Session> sesiones = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sesiones.add(session);
    }

    @OnMessage
    public void onMessage(mensaje message) {
        try {
            for (Session sesion : sesiones) {
                sesion.getBasicRemote().sendObject(message);
            }
        } catch (EncodeException | IOException ex) {
            Logger.getLogger(wsSms.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @OnClose
    public void onClose(Session session) {
        sesiones.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws
            IOException {
        System.out.println(throwable.getMessage());
    }
}
