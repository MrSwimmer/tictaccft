package com.mrswimmer.tic_tac_cft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    GridLayout gridLayout;
    int grid[][] = new int[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearGrid();
    }
    void clearGrid() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                grid[i][j]=2;
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
                if(k==2||o==2) {
                    for(int l=0; l<3; l++) {
                        if(grid[i][l]==2) {
                            if(o==2) {
                                setTac(i, l);
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
                if(k==2||o==2) {
                    for(int l=0; l<3; l++) {
                        if(grid[l][i]==2) {
                            if(o==2) {
                                setTac(l, i);
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
            if(k==2||o==2) {
                for(int i=0; i<3; i++) {
                    if(grid[i][i]==2) {
                        //grid[i][i]=0;
                        if(o==2) {
                            setTac(i, i);
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
            if(k==2||o==2) {
                for(int i=0; i<3; i++) {
                    if(grid[2-i][i]==2) {
                        if(o==2) {
                            setTac(2-i, i);
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
        }
    }
    void setTac(int x, int y) {
        grid[x][y]=0;
        ImageView imageView = findViewById(getCellId(x, y));
        imageView.setImageResource(R.drawable.nolik);
    }
    void showDia() {
        
    }
}
