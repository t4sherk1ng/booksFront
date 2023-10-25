package com.samsung.libraryandroid.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class ChangeBookFragment extends Fragment {

    private EditText etBookName;
    private AppCompatSpinner sp_author;
    private AppCompatSpinner sp_genre;

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

        View view = inflater.inflate(R.layout.fragment_change_book, container, false);

        etBookName = view.findViewById(R.id.et_bookName);

        Book book = (Book) (getArguments().getSerializable(MainActivity.MSG_NAME));

        etBookName.setText(book.getName());

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


                    new LibraryApiImpl(getActivity())
                            .updateBook(
                                    book.getId(),
                                    etBookName.getText().toString(),
                                    ((Author) sp_author.getSelectedItem()).getName(),
                                    ((Genre) sp_genre.getSelectedItem()).getName()
                            );


                    getActivity().getSupportFragmentManager().beginTransaction().remove(ChangeBookFragment.this).commit();

                }
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