package com.steele.swainston.ancestoreventsimporter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.steele.swainston.ancestoreventsimporter.R;
import com.steele.swainston.ancestoreventsimporter.schema.Ancestry;
import com.steele.swainston.ancestoreventsimporter.schema.CurrentPerson;
import com.steele.swainston.ancestoreventsimporter.services.Callback;
import com.steele.swainston.ancestoreventsimporter.services.FamilySearchClient;

public class ManageFragment extends Fragment {
    public static final String TAG = "ManageFragment";

    private TextView testText;
    private EditText usernameField;
    private EditText passwordField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.content_manage, container, false);
        testText = mainView.findViewById(R.id.test_text);
        usernameField = mainView.findViewById(R.id.username);
        passwordField = mainView.findViewById(R.id.password);

        return mainView;
    }

    public void onTestClick(View v) {
        final FamilySearchClient fs = new FamilySearchClient(usernameField.getText().toString(), passwordField.getText().toString());

        fs.getCurrentPerson(new Callback<CurrentPerson>() {
            @Override
            public void onComplete(CurrentPerson person) {
                fs.getAncestry(person.getPersons().get(0).getId(), new Callback<Ancestry>() {
                    @Override
                    public void onComplete(Ancestry result) {
                        testText.setText(result.getPersons().get(0).getDisplay().getName());
                    }
                });
            }
        });
    }
}
