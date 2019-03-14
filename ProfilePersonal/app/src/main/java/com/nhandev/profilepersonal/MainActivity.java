package com.nhandev.profilepersonal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edName, edCMND, edInfo;
    RadioGroup rdGr;
    Button btnSend;
    RadioButton rdAcademy, rdCollege, rdUnivercity;
    CheckBox cbMagazine, cbNewspaper, cbWatchTV;
    private boolean ValidateInput(EditText edName, EditText edCMND) {
        String strName = edName.getText().toString();
        String strCMND = edCMND.getText().toString();
        if (strName.isEmpty()) {
            Toast.makeText(getApplicationContext(), this.getString(R.string.errNameNull), Toast.LENGTH_SHORT).show();
            edName.requestFocus();
            return false;
        }
        if (strCMND.isEmpty()) {
            edCMND.requestFocus();
            Toast.makeText(getApplicationContext(), this.getString(R.string.errCMNDNull), Toast.LENGTH_SHORT).show();
            return false;
        }
        // check The Name
        if (strName.trim().length() < 3) {
            Toast.makeText(getApplicationContext(), this.getString(R.string.hintName), Toast.LENGTH_SHORT).show();
            return false;
        }
        // check input number
        if (strCMND.trim().length() > 9 || strCMND.trim().length() < 9) {
            Toast.makeText(getApplicationContext(), this.getString(R.string.hintCMND), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private String getValueRadio() {
        int idCheck = rdGr.getCheckedRadioButtonId();
        String result = "";
        switch (idCheck) {
            case R.id.academy:
                result = rdAcademy.getText().toString();
                break;
            case R.id.college:
                result = rdCollege.getText().toString();
                break;
            case R.id.univercity:
                result = rdUnivercity.getText().toString();
                break;
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Anh Xa
        edName = findViewById(R.id.fullName);
        edCMND = findViewById(R.id.cmd);
        rdGr = findViewById(R.id.groupQualification);
        btnSend = findViewById(R.id.btnSendInfo);
        rdAcademy = findViewById(R.id.academy);
        rdCollege = findViewById(R.id.college);
        rdUnivercity = findViewById(R.id.univercity);
        cbMagazine = findViewById(R.id.magazine);
        cbNewspaper = findViewById(R.id.newspaper);
        cbWatchTV = findViewById(R.id.watchtv);
        edInfo = findViewById(R.id.edInfo);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Event click into button send info
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check input
                if (ValidateInput(edName, edCMND)) {
                    // get Value Radio
                    String valueRadioCheck = getValueRadio();
                    // get Value CheckBox
                    String resultCheckBox = "";
                    if (cbMagazine.isChecked()) {
                        resultCheckBox += cbMagazine.getText().toString()+"\n";
                    }
                    if (cbNewspaper.isChecked()) {
                        resultCheckBox += cbNewspaper.getText().toString()+"\n";
                    }
                    if (cbWatchTV.isChecked()) {
                        resultCheckBox += cbWatchTV.getText().toString()+"\n";
                    }
                    // get Value Other Information
                    String infoOther = edInfo.getText().toString();

                    builder.setTitle(R.string.information_personal);
                    String result = edName.getText().toString() + "\n" + edCMND.getText().toString() +
                            "\n" + valueRadioCheck + "\n" + resultCheckBox;
                    if(!infoOther.isEmpty())
                    {
                        result += getResources().getString(R.string.infomation_other)+"" +
                                "\n___________\n"+
                                "\n" + infoOther + "" +
                                "\n___________";
                    }
                    builder.setMessage(result);
                    builder.setPositiveButton(R.string.cancer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                   builder.create().show();
                }

            }
        });

    }
}
