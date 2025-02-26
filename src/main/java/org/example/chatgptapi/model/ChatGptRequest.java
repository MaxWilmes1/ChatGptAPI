package org.example.chatgptapi.model;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatGptRequest(
        String model,
        List<Message> messages,
        Double temperature
) {
}
