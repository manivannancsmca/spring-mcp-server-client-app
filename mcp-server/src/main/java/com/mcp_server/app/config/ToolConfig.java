package com.mcp_server.app.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcp_server.app.service.StockService;


@Configuration
public class ToolConfig {

	@Bean
	ToolCallbackProvider stockProvide(StockService stockService) {
		return MethodToolCallbackProvider.builder().toolObjects(stockService).build();
	}
	
}
