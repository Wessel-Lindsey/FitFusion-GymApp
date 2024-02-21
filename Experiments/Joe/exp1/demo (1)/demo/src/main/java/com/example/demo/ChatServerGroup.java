package com.example.demo;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/chat/{username}/groupchat/{groupname}")  // this is Websocket url
public class ChatServerGroup {

    // Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();

    //This is where the different groupchats are stored. Given a group name it will give you a list of all the usernames(keys)
    //of users
    private Map<String, List<String>> groupchats = new Hashtable<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("groupname") String groupname)
            throws IOException {

        // Handle the case of a duplicate username
        if (usernameSessionMap.containsKey(username)) {
            session.getBasicRemote().sendText("Username already exists");
            session.close();
        }
        else {
            // map current session with username
            sessionUsernameMap.put(session, username);

            // map current username with session
            usernameSessionMap.put(username, session);

            List groupMembers = groupchats.get(groupname);

            // checking to see if list of group needs to be initalized
            if(groupMembers == null)
            {
                groupchats.put(groupname, new ArrayList<>());
                groupMembers = groupchats.get(groupname);
            }

            //Put user in respective group
            groupMembers.add(username);

            // send to the user joining in
            sendMessageToParticularUser(username, "Welcome to the chat server, "+username);

            // send to everyone in the chat

            sendMessageToGroup(groupname, "User: " + username + " has Joined the Chat");

        }
    }

    @OnMessage
    public void onMessage(Session session, @PathParam("groupname") String groupname, String message) throws IOException {

        // get the username by session
        String username = sessionUsernameMap.get(session);

        sendMessageToGroup(groupname, username + ": " + message);

    }

    /**
     * Handles the closure of a WebSocket connection.
     *
     * @param session The WebSocket session that is being closed.
     */
    @OnClose
    public void onClose(Session session, @PathParam("groupname") String groupname) throws IOException {

        // get the username from session-username mapping
        String username = sessionUsernameMap.get(session);

        // remove user from memory mappings
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        List<String> group = groupchats.get(groupname);

        if(group == null)
        {
            //name wrong foo
        }

        group.remove(username);

        // send the message to chat
        sendMessageToGroup(groupname, username + " disconnected");

    }

    /**
     * Handles WebSocket errors that occur during the connection.
     *
     * @param session   The WebSocket session where the error occurred.
     * @param throwable The Throwable representing the error condition.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {

        // get the username from session-username mapping
        String username = sessionUsernameMap.get(session);

        // do error handling here
        //logger.info("[onError]" + username + ": " + throwable.getMessage());
    }

    private void sendMessageToGroup(String groupname, String message)
    {
        List<String> group = groupchats.get(groupname);

        if(group == null) return;

        group.forEach((username) -> {
            sendMessageToParticularUser(username, message);
        });

    }

    /**
     * Sends a message to a specific user in the chat (DM).
     *
     * @param username The username of the recipient.
     * @param message  The message to be sent.
     */
    private void sendMessageToParticularUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
            //logger.info("[DM Exception] " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all users in the chat.
     *
     * @param message The message to be broadcasted to all users.
     */
    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                //logger.info("[Broadcast Exception] " + e.getMessage());
            }
        });
    }


}
