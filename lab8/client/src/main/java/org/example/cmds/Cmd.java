package org.example.cmds;

import org.example.responses.Response;

public class Cmd {

    private String name;

    public Cmd(String name) {
        this.name = name;
    }

    public Response execute(String[] args) {
        return new Response("blank");
    }

}
