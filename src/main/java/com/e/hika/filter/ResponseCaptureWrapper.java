package com.e.hika.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ResponseCaptureWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream catchContext = new ByteArrayOutputStream();
    private final PrintWriter writer = new PrintWriter(new OutputStreamWriter(catchContext, StandardCharsets.UTF_8));
    private final ServletOutputStream outputStream = new CachedBodyServletOutputStream(catchContext);
    private int status = 200;

    public ResponseCaptureWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return outputStream;
    }


    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        this.status = sc;
    }

    public byte[] getContentAsByteArray() {
        try {
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catchContext.toByteArray();
    }

    private static class CachedBodyServletOutputStream extends ServletOutputStream {

        private final ByteArrayOutputStream outputStream;

        private CachedBodyServletOutputStream(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }


    }
}
