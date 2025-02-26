package org.example.chatgptapi.model;

import java.util.List;

public record ChatGptResponse(
        List<Choice> choices
) {
}
