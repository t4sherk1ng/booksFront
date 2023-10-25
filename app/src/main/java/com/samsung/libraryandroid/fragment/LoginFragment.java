package com.samsung.libraryandroid.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.samsung.libraryandroid.R;

public class LoginFragment extends Fragment {

    private AppCompatButton signIn;

    private FragmentTransaction transaction;

    public LoginFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        signIn = view.findViewById(R.id.btn_login);
        signIn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           transaction = getFragmentManager().beginTransaction();
                                           transaction.replace(R.id.fl_main, new ProfileFragment());
                                           transaction.commit();
                                       }
                                   }
        );

        return view;
    }
}