package org.example.chatgptapi.model;

import java.util.List;

public record ChatGptResponse(
        List<Choice> choices
) {
    public String retrieveResponse() {
        return choices.get(0).message().content();
    }
}
