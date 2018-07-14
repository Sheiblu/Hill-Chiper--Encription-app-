package com.example.general.hillc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Decryption extends AppCompatActivity {

    Button btDecrypt,btClean,btBack;
    TextView tbPlain2,tbCipher2,tbx1,tbx2,tby1,tby2,tbSvalu;

    String finalWord="";
    String word ;
    int spactualValu , valu;
    int[] key = new int[4];
    int[] wordValu = new int[4];
    int sine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        btDecrypt = (Button) findViewById(R.id.btDecrypt);
        btClean = (Button) findViewById(R.id.btClean1);
        btBack = (Button) findViewById(R.id.btBack1);

        tbPlain2 =  (TextView) findViewById(R.id.tbPlain1);
        tbCipher2 = (TextView) findViewById(R.id.tbCipher1);
        tbx1 =  (TextView) findViewById(R.id.tbx1);
        tbx2 =  (TextView) findViewById(R.id.tbx2);
        tby1 =  (TextView) findViewById(R.id.tby1);
        tby2 =  (TextView) findViewById(R.id.tby2);
        tbSvalu = (TextView) findViewById(R.id.tbSvalu);

        btDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  decript();
                sine = check();
                if (sine==0) {

                            Toast.makeText(getApplicationContext(), "Wrong Input",
                                    Toast.LENGTH_SHORT).show();

                }
                else if (sine == 2){
                            Toast.makeText(getApplicationContext(), "Input length Must be 4",
                                    Toast.LENGTH_SHORT).show();

                }
                else {
                    decript();
                 if (spactualValu == 300) {
                        Toast.makeText(getApplicationContext(), "Spacial vale path is critical",
                                Toast.LENGTH_SHORT).show();
                        tbSvalu.setText("");
                        finalWord = "";
                    } else if (spactualValu == 600) {
                        Toast.makeText(getApplicationContext(), "Fill Up all input correctly",
                                Toast.LENGTH_SHORT).show();
                        tbSvalu.setText("");
                        finalWord = "";
                    } else {
                        tbPlain2.setText(String.valueOf(finalWord));
                        tbSvalu.setText(String.valueOf(spactualValu));
                        finalWord = "";
                    }
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
                Intent i = new Intent(Decryption.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void clean(){
        tbx1.setText("");
        tbx2.setText("");
        tby1.setText("");
        tby2.setText("");
        tbSvalu.setText("");
        tbPlain2.setText("");
        tbCipher2.setText("");
    }
    public void decript() {
        spactualValu=0;
        try {
            String word = tbCipher2.getText().toString();
            key[0] = Integer.valueOf(tbx1.getText().toString());
            key[1] = Integer.valueOf(tby1.getText().toString());
            key[2] = Integer.valueOf(tbx2.getText().toString());
            key[3] = Integer.valueOf(tby2.getText().toString());

            valu = (key[0]*key[3]) - (key[1]*key[2]);
            spactualValu = 0;

            for(int i = 0 ;i<word.length();i++){
                wordValu[i] = (int) word.toUpperCase().charAt(i) - 65;
            }


            int m = key[0];
            key[0] = key[3];
            key[3] = m;
            key[1] = 26-key[1];
            key[2] = 26-key[2];


            do{
                spactualValu++;
            } while ((valu*spactualValu)%26 != 1 && spactualValu<300);

            if(spactualValu<300){
                for (int i = 0; i < 4; i++) {
                    key[i] = (key[i] * spactualValu) % 26;
                }

                finalWord = finalWord + (char) (((wordValu[0] * key[0] + wordValu[1] * key[2]) % 26) + 65) +
                        (char) (((wordValu[0] * key[1] + wordValu[1] * key[3]) % 26) + 65) +
                        (char) (((wordValu[2] * key[0] + wordValu[3] * key[2]) % 26) + 65) +
                        (char) (((wordValu[2] * key[1] + wordValu[3] * key[3]) % 26) + 65);
            }
        }
        catch (Exception e){
            spactualValu = 600;
        }

    }

    public int check(){

        int a=1;
        word = tbCipher2.getText().toString();
        word = word.toUpperCase();

        tbSvalu.setText("");
        finalWord = "";

        for(int i=0;i<word.length();i++){
            if ((int)word.charAt(i) >= 65 && (int) word.charAt(i)<= 90){
                a = 1;
            }
            else {
                a = 0;
                break;
            }
        }
        if (word.equals("")){
            spactualValu = 600;
        }
        if (word.length()!=4){
            a = 2;
        }
        return a;
    }
}
