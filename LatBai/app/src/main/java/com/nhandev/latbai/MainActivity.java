package com.nhandev.latbai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<Bai> listBai;
    Button btnPlay;
    ImageView imOne,imTwo,imThree;
    TextView tvResult;
    boolean checkOne,checkTwo,checkThree;
    private List<Bai> listTemp = new ArrayList<>();
    private Random randomGenerator = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Anh Xa
        btnPlay = findViewById(R.id.btnPlay);
        imOne = findViewById(R.id.imOne);
        imTwo = findViewById(R.id.imTwo);
        imThree = findViewById(R.id.imThree);
        tvResult = findViewById(R.id.result);
        listBai = new ArrayList<>();
        InitData();
        imOne.setOnClickListener(this);
        imTwo.setOnClickListener(this);
        imThree.setOnClickListener(this);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("Scope: 0");
                imOne.setBackgroundResource(R.drawable.matsau);
                imTwo.setBackgroundResource(R.drawable.matsau);
                imThree.setBackgroundResource(R.drawable.matsau);
                imOne.setEnabled(true);
                imTwo.setEnabled(true);
                imThree.setEnabled(true);
                listTemp.clear();
            }
        });
    }
    private Bai getObjectRandom()
    {
            int index = randomGenerator.nextInt(listBai.size());
            return listBai.get(index);
    }
    private void InitData()
    {
        listBai.add(new Bai(R.drawable.mot,1));
        listBai.add(new Bai(R.drawable.hai,2));
        listBai.add(new Bai(R.drawable.ba,3));
        listBai.add(new Bai(R.drawable.bon,4));
        listBai.add(new Bai(R.drawable.nam,5));
        listBai.add(new Bai(R.drawable.sau,6));
        listBai.add(new Bai(R.drawable.bay,7));
        listBai.add(new Bai(R.drawable.tam,8));
        listBai.add(new Bai(R.drawable.chin,9));
        listBai.add(new Bai(R.drawable.muoi,0));
        listBai.add(new Bai(R.drawable.j,0));
        listBai.add(new Bai(R.drawable.q,0));
        listBai.add(new Bai(R.drawable.k,0));
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        Bai b = null;
           if(!checkOne && !checkTwo && !checkThree)
           {
               listTemp.clear();
               b = getObjectRandom();
               listTemp.add(b);
               switch (id)
               {
                   case R.id.imOne:
                       checkOne = true;
                       imOne.setBackgroundResource(b.getId());
                       imOne.setEnabled(false);
                       break;
                   case  R.id.imTwo:
                       imTwo.setBackgroundResource(b.getId());
                       checkTwo = true;
                       imTwo.setEnabled(false);
                       break;
                   case R.id.imThree:
                       checkTwo = true;
                       imThree.setBackgroundResource(b.getId());
                       imThree.setEnabled(false);
                       break;
               }
           }
           else
           {
                   boolean check = true;
                   while(listTemp.size()<3)
                   {
                       b = getObjectRandom();
                       if(!listTemp.contains(b))
                       {
                           listTemp.add(b);
                           break;
                       }
                   }

                   switch (id)
                   {
                       case R.id.imOne:
                           checkOne = true;
                           imOne.setBackgroundResource(b.getId());
                           imOne.setEnabled(false);
                           break;
                       case  R.id.imTwo:
                           imTwo.setBackgroundResource(b.getId());
                           imTwo.setEnabled(false);
                           checkTwo = true;
                           break;
                       case R.id.imThree:
                           checkTwo = true;
                           imThree.setEnabled(false);
                           imThree.setBackgroundResource(b.getId());
                           break;
                   }
                if(listTemp.size()==3)
                {
                    imOne.setEnabled(false);
                    imTwo.setEnabled(false);
                    imThree.setEnabled(false);
                    int kq = 0;
                    for(Bai resultBai : listTemp)
                    {
                        kq += resultBai.getPoint();
                    }
                    if(kq<=9)
                    {
                        tvResult.setText("Scope: "+kq);
                    }
                    else
                    {
                        kq=kq%10;
                        tvResult.setText("Scope: "+kq);
                    }
                }


           }



    }
}
