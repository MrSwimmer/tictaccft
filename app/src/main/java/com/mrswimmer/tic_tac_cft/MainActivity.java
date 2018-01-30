package com.mrswimmer.tic_tac_cft;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    int grid[][] = new int[3][3];
    public static final String APP_PREFERENCES = "mysettings";
    TextView cw, cl, cn;
    Button restart;
    private SharedPreferences mSettings;
    private int countw, countl, countn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearGrid();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        countw = mSettings.getInt("cw", 0);
        countl = mSettings.getInt("cl", 0);
        countn = mSettings.getInt("cn", 0);
        cw = findViewById(R.id.play_win_count);
        cl = findViewById(R.id.play_lose_count);
        cn = findViewById(R.id.play_n_count);
        cw.setText(countw+"");
        cl.setText(countl+"");
        cn.setText(countn+"");
        restart = findViewById(R.id.play_restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearGrid();
            }
        });
    }
    void clearGrid() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                grid[i][j]=2;
                ImageView imageView = findViewById(getCellId(i, j));
                imageView.setImageResource(R.color.white);
            }
        }
    }
    public void onClickCell(View v) {
        switch (v.getId()) {
            case R.id.cell_1:
                setTic(0, 0); break;
            case R.id.cell_2:
                setTic(0, 1); break;
            case R.id.cell_3:
                setTic(0, 2); break;
            case R.id.cell_4:
                setTic(1, 0); break;
            case R.id.cell_5:
                setTic(1, 1); break;
            case R.id.cell_6:
                setTic(1, 2); break;
            case R.id.cell_7:
                setTic(2, 0); break;
            case R.id.cell_8:
                setTic(2, 1); break;
            case R.id.cell_9:
                setTic(2, 2); break;
        }
    }
    int getCellId(int x, int y) {
        switch (x) {
            case 0:
                switch (y) {
                    case 0:
                        return R.id.cell_1;
                    case 1:
                        return R.id.cell_2;
                    case 2:
                        return R.id.cell_3;

                }
            case 1:
                switch (y) {
                    case 0:
                        return R.id.cell_4;
                    case 1:
                        return R.id.cell_5;
                    case 2:
                        return R.id.cell_6;

                }
            case 2:
                switch (y) {
                    case 0:
                        return R.id.cell_7;
                    case 1:
                        return R.id.cell_8;
                    case 2:
                        return R.id.cell_9;

                }
        }
        return 0;
    }
    void setTic(int x, int y) {
        if(grid[x][y]!=2) {
            Toast.makeText(getApplicationContext(), "Выберите незанятую клетку", Toast.LENGTH_LONG).show();
            return;
        } else {
            ImageView imageView = findViewById(getCellId(x, y));
            imageView.setImageResource(R.drawable.krest);
            grid[x][y]=1;
            int k=0, o=0;
            //горизонтали
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(grid[i][j]==1) {
                        k++;
                    }
                    if(grid[i][j]==0) {
                        o++;
                    }
                }
                if(k==3) {
                    addRes(2);
                    return;
                }
                if(k==2||o==2) {
                    for(int l=0; l<3; l++) {
                        if(grid[i][l]==2) {
                            if(o==2) {
                                setTac(i, l);
                                addRes(0);
                                Log.i("code", "nolwin");
                                return;
                            }
                            Log.i("code", "г"+ i + l);
                            setTac(i, l);
                            return;
                        }
                    }
                }
                k=0; o=0;
            }
            k=0; o=0;
            //вертикали
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(grid[j][i]==1) {
                        k++;
                    }
                    if(grid[j][i]==0) {
                        o++;
                    }
                }
                if(k==3) {
                    addRes(2);
                    return;
                }
                if(k==2||o==2) {
                    for(int l=0; l<3; l++) {
                        if(grid[l][i]==2) {
                            if(o==2) {
                                setTac(l, i);
                                addRes(0);
                                Log.i("code", "nolwin");
                                return;
                            }
                            Log.i("code", "в");
                            setTac(l, i);
                            return;
                        }
                    }
                }
                k=0; o=0;
            }
            k=0; o=0;
            //диагональ
            for(int i=0; i<3; i++) {
                if(grid[i][i]==1) {
                    k++;
                }
                if(grid[i][i]==0) {
                    o++;
                }
            }
            if(k==3) {
                addRes(2);
                return;
            }
            if(k==2||o==2) {
                for(int i=0; i<3; i++) {
                    if(grid[i][i]==2) {
                        //grid[i][i]=0;
                        if(o==2) {
                            setTac(i, i);
                            addRes(0);
                            Log.i("code", "nolwin");
                            return;
                        }
                        Log.i("code", "д");
                        setTac(i, i);
                        return;
                    }
                }
            }
            k=0; o=0;
            //побочная диагональ
            for(int i=0; i<3; i++) {
                if(grid[2-i][i]==1) {
                    k++;
                }
                if(grid[2-i][i]==0) {
                    o++;
                }
            }
            if(k==3) {
                addRes(2);
                return;
            }
            if(k==2||o==2) {
                for(int i=0; i<3; i++) {
                    if(grid[2-i][i]==2) {
                        if(o==2) {
                            setTac(2-i, i);
                            addRes(0);
                            Log.i("code", "nolwin");
                            return;
                        }
                        Log.i("code", "пд");
                        setTac(2-i, i);
                        return;
                    }
                }
            }
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(grid[i][j]==2) {
                        setTac(i, j);
                        return;
                    }
                }
            }
            addRes(1);
        }
    }
    void addRes(int res) {
        SharedPreferences.Editor editor = mSettings.edit();
        String message = null, button = null, title = null;
        switch (res) {
            case 0 :
                countl++;
                cl.setText(countl+"");
                editor.putInt("cl", countl);
                title = "Поражение!";
                message = "Пфф, не ощутил сопротивления";
                button = "Пусти, я ему втащу";
                break;
            case 1 :
                countn++;
                cn.setText(countn+"");
                editor.putInt("cn", countn);
                title = "Ничья!";
                message = "Расслабься, меня не одолеть";
                button = "Пусти, я ему втащу";
                break;
            case 2:
                countw++;
                cw.setText(countw+"");
                editor.putInt("cw", countw);
                title = "Победа!";
                message = "Что ты такое?!";
                button = "Развалить еще раз";
                break;
        }
        editor.apply();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                clearGrid();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    void setTac(int x, int y) {
        grid[x][y]=0;
        ImageView imageView = findViewById(getCellId(x, y));
        imageView.setImageResource(R.drawable.nolik);
    }
    void showDia(int res) {

    }
}
