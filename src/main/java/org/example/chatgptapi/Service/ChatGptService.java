package org.example.chatgptapi.Service;

import org.example.chatgptapi.model.ChatGptRequest;
import org.example.chatgptapi.model.ChatGptResponse;
import org.example.chatgptapi.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGptService {

    private final RestClient restClient;

    public ChatGptService(@Value("${OPEN_AI_API_KEY}") String apiKey, RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public String categorizeIngredient(String ingredient) {
        String prompt = """
                Classify the following ingredient into one of these three categories: 
                - vegan (contains no animal products)  
                - vegetarian (contains no meat or fish, but may include dairy or eggs)  
                - regular (contains meat or fish) 
                - not eatable (contains everything that humans can not digest) 
                
                Only respond with one of these exact words. No explanation.  
                
                Ingredient: """ + ingredient;

        Message userMessage = new Message("user", prompt);
        ChatGptRequest request = ChatGptRequest.builder()
                .model("gpt-4o-mini")
                .messages(List.of(userMessage))
                .temperature(0.7)
                .build();

        ChatGptResponse response = restClient.post()
                .body(request)
                .retrieve()
                .body(ChatGptResponse.class);

        return response.retrieveResponse();
    }

    public String recipes(String ingredient) {
        String prompt = "Give me a tasty recipe for the following Ingredient: " + ingredient;

        Message userMessage = new Message("user", prompt);
        ChatGptRequest request = ChatGptRequest.builder()
                .model("gpt-4o-mini")
                .messages(List.of(userMessage))
                .temperature(0.7)
                .build();

        ChatGptResponse response = restClient.post()
                .body(request)
                .retrieve()
                .body(ChatGptResponse.class);

        return response.retrieveResponse();
    }

}
