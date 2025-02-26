package org.example.chatgptapi.Controller;


import lombok.AllArgsConstructor;
import org.example.chatgptapi.Service.ChatGptService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class ChatGptController {

    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {
        return chatGptService.categorizeIngredient(ingredient);
    }

}
