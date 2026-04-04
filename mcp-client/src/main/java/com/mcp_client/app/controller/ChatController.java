package com.mcp_client.app.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder,
                          ObjectProvider<ToolCallbackProvider> toolsProvider) {
        
        var builder = chatClientBuilder
            .defaultSystem("""
                You are a helpful assistant with access to specific tools.
                
                STRICT RULES:
                1. You CANNOT delete, remove, or destroy any data.
                2. If a user asks you to delete something, politely inform them you lack delete permissions.
                3. Do not guess tool names. Only use tools explicitly provided to you.
                
                Prioritize context information. Give short, concise answers.
                """);

        // ✅ Register tools eagerly at startup, not per-request
        toolsProvider.ifAvailable(tools -> {
            builder.defaultToolCallbacks(tools.getToolCallbacks()); // ✅ resolve here
        });

        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public Mono<String> chat(@RequestParam String query) {
        return Mono.fromCallable(() ->
                chatClient.prompt(query).call().content()
        ).subscribeOn(Schedulers.boundedElastic());
    }
}