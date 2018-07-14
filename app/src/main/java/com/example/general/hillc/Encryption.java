package com.example.general.hillc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Encryption extends AppCompatActivity {

    Button btEncript,btClean,btBack;
    TextView tbPlain2,tbCipher2,tbx1,tbx2,tby1,tby2;

    String finalWord="";
    String word ;
    int[] key = new int[4];
    int[] wordValu = new int[4];
    int signal = 1;
    int sine =0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        btEncript = (Button) findViewById(R.id.btEncrypt);
        btClean = (Button) findViewById(R.id.btClean2);
        btBack = (Button) findViewById(R.id.btBack2);



        tbPlain2 =  (TextView) findViewById(R.id.tbPlain2);
        tbCipher2 = (TextView) findViewById(R.id.tbCipher2);
        tbx1 =  (TextView) findViewById(R.id.tbx1);
        tbx2 =  (TextView) findViewById(R.id.tbx2);
        tby1 =  (TextView) findViewById(R.id.tby1);
        tby2 =  (TextView) findViewById(R.id.tby2);

        btEncript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    encript();
                sine = check();

                if (sine==0 || sine==2) {
                    if (sine==0) {
                        Toast.makeText(getApplicationContext(), "Wrong Input",
                                Toast.LENGTH_SHORT).show();

                    }
                    else if (sine == 2){
                        Toast.makeText(getApplicationContext(), "Input length Must be 4",
                                Toast.LENGTH_SHORT).show();

                    }
                }
                else if(signal==600) {
                    Toast.makeText(getApplicationContext(), "Fill Up all input correctly",
                            Toast.LENGTH_SHORT).show();
                            finalWord = "";
                            signal = 0;
                }

                else{
                    tbCipher2.setText(finalWord);
                    finalWord = "";
                }
            }
        });

        btClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Encryption.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void clean(){
        tbx1.setText("");
        tbx2.setText("");
        tby1.setText("");
        tby2.setText("");
        tbPlain2.setText("");
        tbCipher2.setText("");
    }


    public void encript() {
        signal = 0;
        try{
                    String word = tbPlain2.getText().toString();
                    key[0] = Integer.valueOf(tbx1.getText().toString());
                    key[1] = Integer.valueOf(tby1.getText().toString());
                    key[2] = Integer.valueOf(tbx2.getText().toString());
                    key[3] = Integer.valueOf(tby2.getText().toString());


                    for (int i = 0; i < 4; i++) {
                        wordValu[i] = (int) word.toUpperCase().charAt(i) - 65;
                    }


                    finalWord = finalWord + (char) (((wordValu[0] * key[0] + wordValu[1] * key[2]) % 26) + 65) +
                            (char) (((wordValu[0] * key[1] + wordValu[1] * key[3]) % 26) + 65) +
                            (char) (((wordValu[2] * key[0] + wordValu[3] * key[2]) % 26) + 65) +
                            (char) (((wordValu[2] * key[1] + wordValu[3] * key[3]) % 26) + 65);

             }
             catch (Exception e){
                 signal = 600;
            }
    }

    public int check(){

        int a=1;
        word = tbPlain2.getText().toString();
        word = word.toUpperCase();

        for(int i=0;i<word.length();i++){
            if ((int)word.charAt(i) >= 65 && (int) word.charAt(i)<= 90){
                a = 1;
            }
            else {
                return  0;

            }
        }

         if(word.length()!=4){
            a=2;
        }

        return a;
    }

}

