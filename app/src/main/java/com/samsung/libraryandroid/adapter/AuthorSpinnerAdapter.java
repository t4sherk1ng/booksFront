package com.samsung.libraryandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samsung.libraryandroid.R;
import com.samsung.libraryandroid.domain.Author;
import com.samsung.libraryandroid.fakedb.LibraryFakeDb;

import java.util.List;

public class AuthorSpinnerAdapter extends ArrayAdapter<Author> {

    public AuthorSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Author> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_spinner_item))
                .setText(LibraryFakeDb.AUTHOR_LIST.get(position).getName());

        return convertView;
    }


}
