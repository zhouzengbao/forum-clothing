package com.forum.clothing.util;

import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpClient {

    public static void upload(String url, File file, Callback callback) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("media", file.getName(), fileBody).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

    public static Object upload(String url, File file, Response_Type responseType) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("media", file.getName(), fileBody).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            ResponseBody response = client.newCall(request).execute().body();
            return getResponse(response, responseType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getBytes(String url) {
        return (byte[]) get(url, Response_Type.Byte);
    }

    public static String get(String url, String encoding) {
        String result = get(url);
        try {
            result = new String(result.getBytes("ISO8859-1"), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static ConnectionPool pool;
    private static OkHttpClient client;

    static {

        pool = new ConnectionPool();

        client = new OkHttpClient.Builder().connectionPool(pool).build();

    }

    public enum Response_Type {
        // 字符
        String,
        // 字节流
        ByteStream,
        // 字节
        Byte,
        // 字符流
        CharStream;
    }

    public enum Request_Type {
        String, File, Json;
    }

    public static Object get(String url, Response_Type responseType) {
        Request request = new Request.Builder().url(url).build();
        ResponseBody responseBody = async(request);
        return getResponse(responseBody, responseType);

    }

    public static String get(String url) {
        return get(url, Response_Type.String).toString();
    }

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        sync(request, callback);
    }

    public static Object post(String url, MediaType mediaType, Object requestBody, Request_Type reqType,
                              Response_Type responseType, Map<String, String> header) {

        RequestBody body = getRequest(reqType, requestBody, mediaType);

        Builder builder = new Request.Builder().url(url).post(body);
        if (header != null) {
            header.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        Request request = builder.build();

        ResponseBody responseBody = async(request);
        return getResponse(responseBody, responseType);

    }

    public static String post(String url, String requestBody) {
        return post(url, MediaType.parse("application/json; charset=utf-8"), requestBody, Request_Type.String,
                Response_Type.String, null).toString();
    }

    public static void post(String url, MediaType mediaType, Object requestBody, Request_Type reqType,
                            Response_Type responseType, Map<String, String> header, Callback callback) {

        RequestBody body = getRequest(reqType, requestBody, mediaType);

        Builder builder = new Request.Builder().url(url).post(body);
        if (header != null) {
            header.forEach((k, v) -> {
                builder.addHeader(k, v);
            });
        }
        Request request = builder.build();

        sync(request, callback);

    }

    public static void post(String url, Object requestBody, Callback callback) {
        RequestBody body = getRequest(Request_Type.String, requestBody,
                MediaType.parse("application/json; charset=utf-8"));
        Builder builder = new Request.Builder().url(url).post(body);
        Request request = builder.build();
        sync(request, callback);
    }

    public static Object form(String url, Map<String, String> requestForm, Response_Type responseType,
                              Map<String, String> header) {

        okhttp3.FormBody.Builder builder = new FormBody.Builder();
        requestForm.forEach((k, v) -> {
            builder.add(k, v);
        });
        FormBody formBody = builder.build();

        Builder reqBuilder = new Request.Builder().url(url).post(formBody);
        if (header != null) {
            header.forEach((k, v) -> {
                reqBuilder.addHeader(k, v);
            });
        }

        Request request = reqBuilder.build();

        ResponseBody responseBody = async(request);

        return getResponse(responseBody, responseType);

    }

    public static String form(String url, Map<String, String> requestForm, Map<String, String> header) {

        okhttp3.FormBody.Builder builder = new FormBody.Builder();
        requestForm.forEach((k, v) -> {
            builder.add(k, v);
        });
        FormBody formBody = builder.build();

        Builder reqBuilder = new Request.Builder().url(url).post(formBody);
        if (header != null) {
            header.forEach((k, v) -> {
                reqBuilder.addHeader(k, v);
            });
        }

        Request request = reqBuilder.build();

        ResponseBody responseBody = async(request);

        return getResponse(responseBody, Response_Type.String).toString();

    }

    public static Response form(String url, Map<String, String> requestForm) throws IOException {
        okhttp3.FormBody.Builder builder = new FormBody.Builder();
        requestForm.forEach((k, v) -> {
            builder.add(k, v);
        });
        FormBody formBody = builder.build();

        Builder reqBuilder = new Request.Builder().url(url).post(formBody);

        Request request = reqBuilder.build();

        return client.newCall(request).execute();
    }

    public static void form(String url, Map<String, String> requestForm, Map<String, String> header,
                            Callback callback) {
        okhttp3.FormBody.Builder builder = new FormBody.Builder();

        requestForm.forEach((k, v) -> {
            builder.add(k, v);
        });
        FormBody formBody = builder.build();

        Builder reqBuilder = new Request.Builder().url(url).post(formBody);
        if (header != null) {
            header.forEach((k, v) -> {
                reqBuilder.addHeader(k, v);
            });
        }

        Request request = reqBuilder.build();

        sync(request, callback);

    }

    public static ResponseBody async(Request request) {
        try {
            return client.newCall(request).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sync(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }

    private static RequestBody getRequest(Request_Type reqType, Object requestBody, MediaType mediaType) {
        RequestBody body = null;
        switch (reqType) {
            case String:
                body = RequestBody.create(mediaType, (String) requestBody);
                break;
            case Json:
                body = RequestBody.create(mediaType, (String) requestBody);
                break;
            case File:
                body = RequestBody.create(mediaType, (File) requestBody);
                break;
            default:
                break;
        }

        return body;
    }

    public static Object getResponse(ResponseBody responseBody, Response_Type responseType) {
        Object result = null;
        switch (responseType) {
            case String:
                try {
                    result = responseBody.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case Byte:
                try {
                    result = responseBody.bytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ByteStream:
                result = responseBody.byteStream();
                break;
            case CharStream:
                result = responseBody.charStream();
                break;
            default:
                break;
        }
        return result;
    }

}
