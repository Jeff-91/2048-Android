package com.example.aitounamohamed.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private TextView[][] tableauView=new TextView[4][4];
    private Button replay;
    private ArrayList<Stuc> list=new ArrayList<Stuc>();
    private int change=0;
    private int paus=0;

    private class Stuc{
        public int i;
        public int j;
        public int valeur;
        public Stuc(int a,int b)
        {
            i=a;
            j=b;
            valeur=0;
        }
    }
    private Hashtable ht = new Hashtable();
    private Stuc[][] tab=new Stuc[4][4];
    private int[] temp=new int[4];
    private int score=0;
    private TextView Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        //map d'indice puissances de deux et valeur une couleur
        ht.put(2,"#ef9207");
        ht.put(4,"#f46f02");
        ht.put(8,"#e1ff05");
        ht.put(16,"#04ff00");
        ht.put(32,"#fa0df6");
        ht.put(64,"#1826f1");
        ht.put(128,"#9e3a3a");
        ht.put(256,"#98ac12");
        ht.put(512, "#2fbffe");
        ht.put(1024, "#5cb601");
        ht.put(2048, "#26bf15");
        ht.put(4096, "#fffffe");
        ht.put(8192, "#92ff16");

        setContentView(R.layout.activity_main);
        Score=(TextView)findViewById(R.id.textView);
        tableauView[0][0]=(TextView)findViewById(R.id.C00);
        tableauView[0][1]=(TextView)findViewById(R.id.C01);
        tableauView[0][2]=(TextView)findViewById(R.id.C02);
        tableauView[0][3]=(TextView)findViewById(R.id.C03);
        tableauView[1][0]=(TextView)findViewById(R.id.C10);
        tableauView[1][1]=(TextView)findViewById(R.id.C11);
        tableauView[1][2]=(TextView)findViewById(R.id.C12);
        tableauView[1][3]=(TextView)findViewById(R.id.C13);
        tableauView[2][0]=(TextView)findViewById(R.id.C20);
        tableauView[2][1]=(TextView)findViewById(R.id.C21);
        tableauView[2][2]=(TextView)findViewById(R.id.C22);
        tableauView[2][3]=(TextView)findViewById(R.id.C23);
        tableauView[3][0]=(TextView)findViewById(R.id.C30);
        tableauView[3][1]=(TextView)findViewById(R.id.C31);
        tableauView[3][2]=(TextView)findViewById(R.id.C32);
        tableauView[3][3]=(TextView)findViewById(R.id.C33);

        replay=(Button)findViewById(R.id.replay);
        init();
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                init();
            }});
        Log.i("List", "listtaile="  + " listtaile=" + list.size());

    }
public void init() {
    score=0;
    paus=0;
    Score.setText(""+score);
    for (int i=0;i<4;i++)
    {
        for(int j=0;j<4;j++) {
            tab[i][j]=new Stuc(i,j);
            tableauView[i][j].setText("");
            tableauView[i][j].setBackgroundColor(Color.parseColor("#cdcec8"));
            list.add(tab[i][j]);
        }
    }
    Random r = new Random();
    int line1 = r.nextInt(3);
    int colone1 =r.nextInt(3);
    int line2=r.nextInt(3);
    int colone2 =r.nextInt(3);

    tableauView[line1][colone1].setText(""+2);
    tableauView[line2][colone2].setText(""+2);
    tab[line1][colone1].valeur=2;
    String coloeur=ht.get(tab[line1][colone1].valeur).toString();
    tableauView[line1][colone1].setBackgroundColor(Color.parseColor(coloeur));
    list.remove(tab[line1][colone1]);
    tab[line2][colone2].valeur=2;
    tableauView[line2][colone2].setBackgroundColor(Color.parseColor((coloeur)));
    list.remove(tab[line2][colone2]);
}
    public void leftLeft(Stuc[][] Tab,int ligne){
        Stuc[][] tabup=new Stuc[4][1];
        tabup[0][0]=Tab[ligne][3];
        tabup[1][0]=Tab[ligne][2];
        tabup[2][0]=Tab[ligne][1];
        tabup[3][0]=Tab[ligne][0];
        downDown(tabup,0);
    }
    public void rightRight(Stuc[][] Tab,int ligne){
        Stuc[][] tabup=new Stuc[4][1];
        tabup[0][0]=Tab[ligne][0];
        tabup[1][0]=Tab[ligne][1];
        tabup[2][0]=Tab[ligne][2];
        tabup[3][0]=Tab[ligne][3];
        downDown(tabup, 0);
    }
    public void upUp(Stuc[][] Tab,int colone){
        Stuc[][] tabup=new Stuc[4][1];
        tabup[0][0]=Tab[3][colone];
        tabup[1][0]=Tab[2][colone];
        tabup[2][0]=Tab[1][colone];
        tabup[3][0]=Tab[0][colone];
        downDown(tabup,0);
    }
    public void downDown(Stuc[][] Tab,int colone) {
                if (Tab[3][colone].valeur != 0) {
                    if (Tab[3][colone].valeur == Tab[2][colone].valeur) {
                        score=score+Tab[3][colone].valeur;
                        Score.setText(""+score);
                        changeDown1(Tab, 3, 2, colone);
                        if(Tab[2][colone].valeur==Tab[1][colone].valeur) {
                            score=score+Tab[2][colone].valeur;
                            Score.setText(""+score);
                            changeDown1(Tab,2,2,colone);}
                        else {
                            if(Tab[2][colone].valeur==0) changeDown1(Tab,2,1,colone);}}
                    else if (Tab[2][colone].valeur == 0) {
                            if(Tab[3][colone].valeur==Tab[1][colone].valeur){
                                score=score+Tab[3][colone].valeur;
                                Score.setText(""+score);
                                changeDown2(Tab,3,2,0,1,colone);}
                            else {
                               if(Tab[1][colone].valeur==0) {
                                   if(Tab[3][colone].valeur==Tab[0][colone].valeur){
                                       score=score+Tab[3][colone].valeur;
                                       Score.setText(""+score);
                                       changeDown2(Tab,3,2,1,0,colone);}
                                   else changeDown2(Tab,3,1,0,1,colone);}
                                 else {
                                   if (Tab[0][colone].valeur == Tab[1][colone].valeur) {
                                       score=score+Tab[2][colone].valeur;
                                       Score.setText(""+score);
                                       changeDown2(Tab,3,1,1,2,colone);}
                                   else changeDown1(Tab,2,1,colone);}}}
                    else {
                             if(Tab[2][colone].valeur==Tab[1][colone].valeur) {
                                score=score+Tab[2][colone].valeur;
                                Score.setText(""+score);
                                changeDown1(Tab,2,2,colone);}
                            else
                            {
                                 if(Tab[1][colone].valeur==0) {
                                       if(Tab[2][colone].valeur==Tab[0][colone].valeur) {
                                            score=score+Tab[2][colone].valeur;
                                            Score.setText(""+score);
                                            changeDown2(Tab,3,1,0,2,colone);}
                                     else changeDown1(Tab,1,1,colone);}
                                else{
                                     if(Tab[0][colone].valeur==Tab[1][colone].valeur) {
                                         score=score+Tab[1][colone].valeur;
                                         Score.setText(""+score);
                                         changeDown1(Tab,1,2,colone);}}}}}
                else {
                  if(Tab[2][colone].valeur==0) {
                      if(Tab[0][colone].valeur==Tab[1][colone].valeur) {
                          score=score+Tab[3][colone].valeur;
                          Score.setText(""+score);
                          changeDown2(Tab,1,2,1,0,colone);}
                      else {
                          if(Tab[1][colone].valeur==0) changeDown2(Tab,0,1,1,0,colone);
                          else changeDown2(Tab,1,1,0,1,colone);}}
                    else {
                      if(Tab[2][colone].valeur==Tab[1][colone].valeur) {
                          score=score+Tab[3][colone].valeur;
                          Score.setText(""+score);
                          changeDown2(Tab,1,2,0,1,colone);}
                      else {
                          if(Tab[1][colone].valeur==0) {
                              if(Tab[2][colone].valeur==Tab[0][colone].valeur) {
                                  score=score+Tab[3][colone].valeur;
                                  Score.setText(""+score);
                                  changeDown2(Tab,0,2,2,0,colone);}
                              else changeDown2(Tab,2,1,0,1,colone);}
                          else{
                              if(Tab[0][colone].valeur==Tab[1][colone].valeur) {
                                  score=score+Tab[2][colone].valeur;
                                  Score.setText(""+score);
                                  changeDown2(Tab,2,1,1,2,colone);}
                              else changeDown1(Tab,3,1,colone);}}}}}

            public void changeDown1(Stuc Tab[][],int first,int oneTwo,int colone)
            {
                 Log.i("valeur"," colone:"+colone+" "+tab[0][colone].valeur+" "+tab[1][colone].valeur+" "+tab[2][colone].valeur+" "+tab[3][colone].valeur+" ");
                 Tab[first][colone].valeur=oneTwo*Tab[first-1][colone].valeur;
                 for(int i=first-1;i>0;i--)
                 Tab[i][colone].valeur=Tab[i-1][colone].valeur;
                 Tab[0][colone].valeur=0;
                 Log.i("valeur"," colone:"+colone+" "+tab[0][colone].valeur+" "+tab[1][colone].valeur+" "+tab[2][colone].valeur+" "+tab[3][colone].valeur+" ");
            }
            public void changeDown2(Stuc Tab[][],int firstIndice,int oneTwo,int secondIndice,int twoOne,int colone){
                Tab[3][colone].valeur= oneTwo*Tab[firstIndice][colone].valeur;
                Tab[2][colone].valeur=twoOne*Tab[secondIndice][colone].valeur;
                Tab[1][colone].valeur=0;
                Tab[0][colone].valeur=0;
            }
            public boolean equalStuc(){
                for(int i=0;i<4;i++){
                    if(i==3){
                        if(tab[3][0].valeur==tab[3][1].valeur || tab[3][1].valeur==tab[3][2].valeur || tab[3][2].valeur==tab[3][3].valeur)
                            return false;
                    }
                    else {
                    for(int j=0;j<4;j++){
                        if(j==3){
                         if(tab[i][j].valeur==tab[i+1][j].valeur)
                                return false;
                        }
                        else if(tab[i][j].valeur==tab[i+1][j].valeur || tab[i][j].valeur==tab[i][j+1].valeur)
                              return false;
                    }
                }
                }
                return true;
            }
    public  void setUpChange(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tab[i][j].valeur != 0){
                    tableauView[i][j].setText("" + tab[i][j].valeur);
                    String coleur=ht.get(tab[i][j].valeur).toString();
                    tableauView[i][j].setBackgroundColor(Color.parseColor(coleur));
                    if(list.contains(tab[i][j]))
                        list.remove(tab[i][j]);
                }
                else
                {
                    tableauView[i][j].setText("");
                    tableauView[i][j].setBackgroundColor(Color.parseColor("#cdcec8"));
                    if(!list.contains(tab[i][j]))
                        list.add(tab[i][j]);
                }
            }

        }
        int size=list.size();
        if(size>0) {
            ArrayList<Integer> t=new ArrayList<Integer>();
            for (int i=0;i<size;i++){
                if(list.get(i).i==0 || list.get(i).j==0)
                    t.add(i);
            }
            Random r = new Random();
            int i,j;
            if(t.size()>0) {
                j = r.nextInt(t.size());
                i=t.get(j);
            }
            else {
                i = r.nextInt(list.size());
            }
            list.get(i).valeur=2;
            int l=list.get(i).i;
            int c=list.get(i).j;
            tableauView[l][c].setText(""+2);
            tableauView[l][c].setBackgroundColor(Color.parseColor("#ef9207"));
            list.remove(tab[l][c]);}
    }
            public void lose(){
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("GAME OVER");
                    adb.setMessage("Ton score est " + score);
                    adb.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            init();
                        }
                    });
                    adb.show();
              }
    private int down=0;
    private float x=0,y=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN  && down==0)
        {
            down=1;
          x=event.getX();
          y=event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE && down==1)
        {
            down=2;
        }
        if (event.getAction() == MotionEvent.ACTION_UP&& down==2 ){
            down=0;
            Log.i("MOVE","GOOD "+event.getX()+" "+event.getY());
            float xx=event.getX();
            float yy=event.getY();
            if(x-xx>0 && y-yy<x-xx && y-yy>-x+xx ){
                Log.i("MOVE","RIGHT->LEFT");
                change=0;
                for(int i=0;i<4;i++){
                    if(change==0) {
                        temp[0] = tab[i][0].valeur;
                        temp[1] = tab[i][1].valeur;
                        temp[2] = tab[i][2].valeur;
                        temp[3] = tab[i][3].valeur;
                    }
                    leftLeft(tab, i);
                    if(change==0)
                    {
                        if(temp[0]!=tab[i][0].valeur || temp[1]!=tab[i][1].valeur || temp[2]!=tab[i][2].valeur || temp[3]!=tab[i][3].valeur  )
                            change=1;
                    }
                }
                Log.i("change","="+change);
                if(change!=0) {
                    setUpChange();
                }
                if(list.size()==0){
                    if(equalStuc()==true)
                        lose();
                }
            }
           else if(x-xx<0 && y-yy<xx-x && y-yy>x-xx ){
                Log.i("MOVE","LEFT->RIGHT");

                change=0;
                for (int i = 0; i < 4; i++){
                    if(change==0) {
                        temp[0] = tab[i][0].valeur;
                        temp[1] = tab[i][1].valeur;
                        temp[2] = tab[i][2].valeur;
                        temp[3] = tab[i][3].valeur;
                    }
                    rightRight(tab, i);
                    if(change==0)
                    {
                        if(temp[0]!=tab[i][0].valeur || temp[1]!=tab[i][1].valeur || temp[2]!=tab[i][2].valeur || temp[3]!=tab[i][3].valeur  )
                            change=1;
                    }
                }
                Log.i("change","="+change);
                if(change!=0) {
                    setUpChange();
                }
                if(list.size()==0){
                    if(equalStuc()==true)
                        lose();
                }
            }
           else  if(y-yy<0 && x-xx<yy-y && x-xx>y-yy){
                Log.i("MOVE","Up->Down");


                change= 0;
                for (int i = 0; i < 4; i++)
                {
                    if(change==0) {
                        temp[0] = tab[0][i].valeur;
                        temp[1] = tab[1][i].valeur;
                        temp[2] = tab[2][i].valeur;
                        temp[3] = tab[3][i].valeur;
                    }
                    downDown(tab,i);
                    if(change==0)
                    {
                        if(temp[0]!=tab[0][i].valeur || temp[1]!=tab[1][i].valeur || temp[2]!=tab[2][i].valeur || temp[3]!=tab[3][i].valeur  )
                            change=1;
                    }
                }
                Log.i("change","="+change);
                if(change!=0) {
                    setUpChange();
                }
                if(list.size()==0){
                    if(equalStuc()==true)
                        lose();
                }
            }
            else if(y-yy>0 && x-xx<y-yy && x-xx>yy-y){
                Log.i("MOVE","Down->Up");
                change=0;
                for(int i=0;i<4;i++) {
                    if(change==0) {
                        temp[0] = tab[0][i].valeur;
                        temp[1] = tab[1][i].valeur;
                        temp[2] = tab[2][i].valeur;
                        temp[3] = tab[3][i].valeur;
                    }
                    upUp(tab,i);
                    if(change==0)
                    {
                        if(temp[0]!=tab[0][i].valeur || temp[1]!=tab[1][i].valeur || temp[2]!=tab[2][i].valeur || temp[3]!=tab[3][i].valeur  )
                            change=1;
                    }
                }
                Log.i("change","="+change);
                if(change!=0) {
                    setUpChange();
                }
                if(list.size()==0){
                    if(equalStuc()==true)
                        lose();
                }
            }
        }
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(paus==1)
            init();
    }
}
