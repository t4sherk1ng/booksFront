package com.samsung.libraryandroid.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.samsung.libraryandroid.MainActivity;
import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.adapter.AuthorSpinnerAdapter;
import com.samsung.libraryandroid.adapter.GenreSpinnerAdapter;
import com.samsung.libraryandroid.domain.Author;
import com.samsung.libraryandroid.domain.Book;
import com.samsung.libraryandroid.domain.Genre;
import com.samsung.libraryandroid.fakedb.LibraryFakeDb;
import com.samsung.libraryandroid.rest.LibraryApiImpl;

public class AddBookFragment extends Fragment {

    private EditText etBookName;

    private AppCompatSpinner sp_author;
    private AppCompatSpinner sp_genre;

    private Book book;

    @Override
    public void onResume() {
        super.onResume();

        if (etBookName != null) {

            etBookName.setText("");
        }
    }

    private boolean checkEmpty() {
        boolean problem = false;

        if (TextUtils.isEmpty(etBookName.getText().toString())) {
            etBookName.setError("Обязательное поле");
            problem = true;
        }

        return problem;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        etBookName = view.findViewById(R.id.et_bookName);

        sp_author = view.findViewById(R.id.sp_author);
        AuthorSpinnerAdapter authorSpinnerAdapter =
                new AuthorSpinnerAdapter(
                        getActivity(),
                        R.layout.spinner_item,
                        LibraryFakeDb.AUTHOR_LIST
                );
        sp_author.setAdapter(authorSpinnerAdapter);

        sp_genre = view.findViewById(R.id.sp_genre);
        GenreSpinnerAdapter genreSpinnerAdapter =
                new GenreSpinnerAdapter(
                        getActivity(),
                        R.layout.spinner_item,
                        LibraryFakeDb.GENRE_LIST
                );
        sp_genre.setAdapter(genreSpinnerAdapter);

        AppCompatButton btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!checkEmpty()) {

                    book = new Book(
                            0,
                            etBookName.getText().toString(),
                            (Author) sp_author.getSelectedItem(),
                            (Genre) sp_genre.getSelectedItem(),
                            null
                    );
                    new LibraryApiImpl(getActivity()).newBook(book);

                    getActivity().getSupportFragmentManager().beginTransaction().remove(AddBookFragment.this).commit();
                }

                //Toast.makeText(getActivity(), sp_author.getSelectedItem() + "", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        ((MainActivity) getActivity()).update();
    }
}