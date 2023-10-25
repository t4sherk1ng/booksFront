package com.samsung.libraryandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.samsung.libraryandroid.R;


public class ProfileFragment extends Fragment {

    private AppCompatButton signout;

    private FragmentTransaction transaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

//        signout = view.findViewById(R.id.sign_out);
//         signout.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            transaction = getFragmentManager().beginTransaction();
//                                            transaction.replace(R.id.fragment_a, new LoginFragment());
//                                            transaction.commit();
//                                        }
//                                    }
//         );
        return view;
    }
}
