package com.samsung.libraryandroid.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.domain.Author;
import com.samsung.libraryandroid.domain.Book;
import com.samsung.libraryandroid.domain.Genre;
import com.samsung.libraryandroid.domain.mapper.AuthorMapper;
import com.samsung.libraryandroid.domain.mapper.BookMapper;
import com.samsung.libraryandroid.domain.mapper.GenreMapper;
import com.samsung.libraryandroid.fakedb.LibraryFakeDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LibraryApiImpl implements LibraryApi {

    public static final String BASE_URL = "http://192.168.1.71:65535"; //http://192.168.1.12:8080
    private final Context context;

    private Response.ErrorListener errorListener;

    public LibraryApiImpl(Context context) {

        this.context = context;
        errorListener = new ErrorListenerImpl();
    }

    @Override
    public void fillBook() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            LibraryFakeDb.BOOK_LIST.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Genre genre = new GenreMapper().genreFromBookJsonArray(jsonObject);

                                Author author = new AuthorMapper().authorFromBookJsonArray(jsonObject);

                                Book book = new BookMapper().bookFromJsonArray(jsonObject, author, genre);
                                LibraryFakeDb.BOOK_LIST.add(book);
                            }
                            Log.d("BOOK_LIST", LibraryFakeDb.BOOK_LIST.toString());
                            ((MainActivity) context).update();
                        } catch (JSONException e) {

                            Log.d("BOOK_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillGenre() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/genre";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Genre genre = new GenreMapper().genreFromJsonArray(jsonObject);

                                LibraryFakeDb.GENRE_LIST.add(genre);
                            }
                            Log.d("GENRE_LIST", LibraryFakeDb.GENRE_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("GENRE_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillAuthor() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/author";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Author author = new AuthorMapper().authorFromJsonArray(jsonObject);

                                LibraryFakeDb.AUTHOR_LIST.add(author);
                            }
                            Log.d("AUTHOR_LIST", LibraryFakeDb.AUTHOR_LIST.toString());
                        } catch (JSONException e) {

                            Log.d("AUTHOR_LIST", e.getMessage());
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void newBook(Book book) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        // выгрузка заново плохо?
                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("nameBook", book.getName());
                params.put("nameAuthor", book.getAuthor().getName());
                params.put("nameGenre", book.getGenre().getName());

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void updateBook(int id, String newBookName, String newAuthorName, String newGenreName) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book/" + id + "/";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response);

                        //стоит обновлять локально
                        //но пока так
                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("newBookName", newBookName);
                params.put("newAuthorName", newAuthorName);
                params.put("newGenreName", newGenreName);
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(postRequest);
    }

    @Override
    public void deleteBook(int id) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/book/" + id;

        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fillBook();
                    }
                },

                errorListener
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));

                return params;
            }
        };

        queue.add(dr);
    }

    private class ErrorListenerImpl implements Response.ErrorListener {


        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }
    }

}
