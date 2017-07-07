package com.noteshare.easymock;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResultServletTest {
    private PostServlet servlet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;

    @Before
    public void setUp() {
        servlet = new PostServlet();
        mockRequest = createMock(HttpServletRequest.class); // 加载
        mockResponse = createMock(HttpServletResponse.class);
    }

    @After
    public void tearDown() {
        verify(mockRequest); // 验证
        verify(mockResponse);
    }

    @Test
    public void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
        mockRequest.getParameter("username"); // 传入参数
        expectLastCall().andReturn("1");

        mockRequest.getParameter("password"); // 传入参数
        expectLastCall().andReturn("chevy");
        
        replay(mockRequest); // 回放
        replay(mockResponse);
        servlet.doPost(mockRequest, mockResponse); // 调用
    }

}