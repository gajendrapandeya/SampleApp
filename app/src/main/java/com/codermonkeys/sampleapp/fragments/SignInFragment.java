package com.codermonkeys.sampleapp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codermonkeys.sampleapp.MainActivity;
import com.codermonkeys.sampleapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static com.codermonkeys.sampleapp.RegisterActivity.onResetPasswordFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    //Ui component's
    private TextView dontHaveAnAccount;
    private EditText email, password;
    private ImageButton closeBtn;
    private Button signInBtn;
    private ProgressBar progressBar;
    private TextView forgotPassword;


    //Var's
    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        initWidgets(view);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    private void initWidgets(View view) {

        dontHaveAnAccount = view.findViewById(R.id.tv_dont_have_an_account);
        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        closeBtn = view.findViewById(R.id.sign_in_close_btn);
        signInBtn = view.findViewById(R.id.sign_in_btn);
        progressBar = view.findViewById(R.id.sign_in_progressbar);
        forgotPassword = view.findViewById(R.id.sign_in_forgot_password);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setFragment(new SignUpFragment());
           }
       });

       forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               onResetPasswordFragment = true;
               setFragment(new ResetPasswordFragment());
           }
       });
       
       email.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               checkInputs();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       password.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               checkInputs();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

       signInBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               checkEmailAndPassword();
           }
       });

       closeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent mainIntent = new Intent(getActivity(), MainActivity.class);
               startActivity(mainIntent);
               requireActivity().finish();
           }
       });
    }

    private void checkEmailAndPassword() {

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.custom_error_icon);
        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());

        if(email.getText().toString().matches(emailPattern)) {
            if(password.length() >= 8) {

                progressBar.setVisibility(View.VISIBLE);
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));


                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful()) {

                            Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainIntent);
                            requireActivity().finish();

                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            signInBtn.setEnabled(true);
                            signInBtn.setTextColor(Color.rgb(255, 255, 255));
                            Toast.makeText(getActivity(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {

                Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }

    private void checkInputs() {

        if(!TextUtils.isEmpty(email.getText())) {
            if(!TextUtils.isEmpty(password.getText())) {

                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255, 255, 255));
            } else {

                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
            }

        } else {

            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {

            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_left);
            fragmentTransaction.replace(R.id.register_frame_layout, fragment);
            fragmentTransaction.commit();
        }

}
