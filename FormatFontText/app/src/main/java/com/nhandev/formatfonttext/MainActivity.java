package com.nhandev.formatfonttext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import Model.Align;
import Model.Color;
public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,CompoundButton.OnCheckedChangeListener{
    EditText edInput;
    TextView tvResult;
    RadioGroup rGNumberType;
    RadioButton rdOdd,rdEven,rdBoth;
    CheckBox cbBackground,cbTextColor,cbAlign;
    Button btnShow;
    List<Color> listColor;
    List<Align> listALign;
    Color colorBackground;
    Color colorText;
    Align align;
    private boolean validateInput(EditText edInput)
    {
        String strInput = edInput.getText().toString();
        if(strInput.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"The String Input haven't empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Anh Xa
        edInput = findViewById(R.id.edInput);
        tvResult = findViewById(R.id.result);
        rGNumberType = findViewById(R.id.rGNumberType);
        rdOdd = findViewById(R.id.Odd);
        rdEven = findViewById(R.id.Even);
        rdBoth = findViewById(R.id.Both);
        cbBackground = findViewById(R.id.cbBackground);
        cbTextColor = findViewById(R.id.cbTextColor);
        cbAlign = findViewById(R.id.cbAlign);
        btnShow = findViewById(R.id.btnShow);
        listColor = new ArrayList<>();
        listALign = new ArrayList<>();
        // Init Data
        InitDataArray();
        // Event
        btnShow.setOnClickListener(this);
        cbBackground.setOnCheckedChangeListener(this);
        cbTextColor.setOnCheckedChangeListener(this);
        cbAlign.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {

        // Handle
        if(validateInput(edInput))
        {
            rGNumberType.setOnCheckedChangeListener(this);
            if(colorBackground!=null)
            {
                tvResult.setBackgroundColor(android.graphics.Color.parseColor(colorBackground.getKeyColor()));
            }
            if(colorText!=null)
            {
               tvResult.setTextColor(android.graphics.Color.parseColor(colorText.getKeyColor()));
            }
            if(align!=null)
            {
                tvResult.setGravity(align.getKeyAlign());
            }
            tvResult.setText(edInput.getText().toString());
        }
    }
    // get Value radioCheck
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int number = Integer.parseInt(edInput.getText().toString());
        switch (checkedId)
        {
            case R.id.Odd :
                if(number%2!=1)
                {
                   tvResult.setText(edInput.getText().toString());
                }
                else
                {
                    tvResult.setText("");
                    Toast.makeText(getApplicationContext(),"The Input Number invalid",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Even:
                if(number%2==0)
                {
                    tvResult.setText(edInput.getText().toString());
                }
                else
                {
                    tvResult.setText("");
                    Toast.makeText(getApplicationContext(),"The Input Number invalid",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Both:
                tvResult.setText(edInput.getText().toString());
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int idCheckBox = buttonView.getId();
        switch (idCheckBox)
        {
            case R.id.cbBackground:
                // Show Modal List Color and get value
                if(isChecked)
                {
                    colorBackground = openModal();
                }
                else
                {
                    cbBackground.setText("Background");
                    tvResult.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"));

                }
                break;
            case R.id.cbTextColor:
                // Show Modal List Color and get value
                if(isChecked) {
                    colorText = openModalTextolor();
                }
                else
                {
                    cbTextColor.setText("Text Color");
                    tvResult.setTextColor(android.graphics.Color.parseColor("#000000"));
                }
                break;
            case R.id.cbAlign:
                // Show Modal List Align and get value
                if(isChecked) {
                    align = openModalAlign();
                }
                else
                {
                    cbAlign.setText("Align");
                    tvResult.setGravity(Gravity.LEFT);
                }
                break;
        }
    }
    private Color openModalTextolor()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int positionStart = 0;
        final int[] positionChoose = {0};
        builder.setTitle("Choose a color ");
        List<String> strColorValues = new ArrayList<>(listColor.size());
        for (Color color : listColor) {
            strColorValues.add(color.getValueColor());
        }
        builder.setSingleChoiceItems(strColorValues.toArray(strColorValues.toArray(new String[strColorValues.size()])), positionStart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positionChoose[0] = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                colorText=listColor.get(positionChoose[0]);
                cbTextColor.setText(colorText.getValueColor());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
        return listColor.get(positionChoose[0]);
    }
    private Color openModal()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int positionStart = 0;
        final int[] positionChoose = {0};
        builder.setTitle("Choose a color ");
        List<String> strColorValues = new ArrayList<>(listColor.size());
        for (Color color : listColor) {
            strColorValues.add(color.getValueColor());
        }
        builder.setSingleChoiceItems(strColorValues.toArray(strColorValues.toArray(new String[strColorValues.size()])), positionStart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positionChoose[0] = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                    colorBackground=listColor.get(positionChoose[0]);
                    cbBackground.setText(colorBackground.getValueColor());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
        return listColor.get(positionChoose[0]);
    }
    private Align openModalAlign()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int[] positionChoose = {0};
        int positionStart = 0;
        builder.setTitle("Choose align type  ");
        List<String> strAligns = new ArrayList<>(listALign.size());
        for (Align align : listALign) {
            strAligns.add(align.getValueAlign());
        }
        builder.setSingleChoiceItems(strAligns.toArray(new String[strAligns.size()]), positionStart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positionChoose[0] = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                align = listALign.get(positionChoose[0]);
                cbAlign.setText(align.getValueAlign());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
        return listALign.get(positionChoose[0]);
    }
    private void InitDataArray()
    {
        // Init List Color
        Color redColor = new Color("#ff0000","Red");
        Color orangeColor = new Color("#ff8000","Orange");
        Color yelloewColor = new Color("#ffbf00","Yellow");
        Color greenColor = new Color("#bfff00","Green");
        Color blueColor = new Color("#0000ff","Blue");
        Color pinkColor = new Color("#ff00ff","Pink");
        Color purpleColor = new Color("#bf00ff","Purple");
        Color blackColor = new Color ("#000000","Black");
        listColor.add(redColor);
        listColor.add(orangeColor);
        listColor.add(yelloewColor);
        listColor.add(greenColor);
        listColor.add(blueColor);
        listColor.add(pinkColor);
        listColor.add(purpleColor);
        listColor.add(blackColor);
        // Init Align
        Align leftAlign = new Align(Gravity.LEFT,"LEFT");
        Align centerAlign = new Align(Gravity.CENTER,"CENTER");
        Align rightAlign = new Align(Gravity.RIGHT,"RIGHT");
        listALign.add(leftAlign);
        listALign.add(centerAlign);
        listALign.add(rightAlign);
    }
}
