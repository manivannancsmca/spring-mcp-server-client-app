package com.mcp_server.app.config;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.ToolDefinition;

public class HelloTool implements ToolCallback {

	@Override
	public ToolDefinition getToolDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String call(String toolInput) {
		return "Hello from MCP Server!";
	}

    
}