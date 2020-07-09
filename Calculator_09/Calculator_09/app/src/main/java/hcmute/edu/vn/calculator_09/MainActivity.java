package hcmute.edu.vn.calculator_09;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private String Toantu="";
    private double[] So=new double[20];

    private  int dau=0;
    private boolean Landau=true;
    private String demo="";
    private String numText,sign,equation ="";
    private double  result = 0,num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);
        numText = "";
}

    public void  onClick(View view){
        try{
            Button button = (Button)view;

            numText += button.getText().toString();

            //check
            num = Double.valueOf(numText);
            screen.setText(numText);
           demo=numText;
           So[dau]=Double.parseDouble(demo);

        }catch (Exception Ex){
            numText = numText.substring(0,numText.length()-1);
        Toast.makeText(this,"Invalid decimal format!",Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickSign(View view){
        try{

        Button button = (Button)view;
        sign=button.getText().toString();
        screen.setText(sign);
        equation+=sign;
        numText ="";
            Toantu=Toantu+sign;
            dau++;
            // Toast.makeText(this, ""+demo, Toast.LENGTH_SHORT).show();
            //Toantu[dau]=sign;
            // dau++;

    }catch (Exception Ex){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickRefresh(View view){
        result = 0;
        equation = "";
        numText ="";
        Landau=true;
        dau=0;
        Toantu="";
        So=new double[10];
        screen.setText("");
    }
    //hàm tách số đưa vào mảng với đầu vào là một chuỗi gồm số và toán tử


    //ham xoa mang

    public double TinhToan(String a,double[]b)
    {
        //dau =1;

        //lam phep toan */ truoc
        for(int i=0;i<=dau;i++)
        {
            if(a.charAt(i)=='/'||a.charAt(i)=='*')
            {
                //thuc hien phep nhan va chia
                // neu la phep chia
                if(a.charAt(i)=='/')
                {
                    b[i]=b[i]/b[i+1];

                }
                // neu la phep nhan
                else if (a.charAt(i)=='*'){
                    b[i]=b[i]*b[i+1];
                }
                //tai vi tri thuc hien toan tu */ ta tru di 1 toan tu va 1 so hang
                    //xoa so sau
                for(int j=i+1;j<dau+2;j++){
                    b[j]=b[j+1];
                }
                    //xoa toan tu
                a=Xoakytu(a,i);
                i--; //sau khi doi lai neu khong tru 1 de xet lai se bi mat so
                dau--;
            }
        }
        //lam phep +-
        for(int i=0;i<=dau;i++)
        {
            if(a.charAt(i)=='+'||a.charAt(i)=='-')
            {
                //thuc hien phep nhan va chia
                // neu la phep cong
                if(a.charAt(i)=='+')
                {
                    b[i]=b[i]+b[i+1];

                }
                // neu la phep tru
                else if (a.charAt(i)=='-'){
                    b[i]=b[i]-b[i+1];
                }
                //tai vi tri thuc hien toan tu */ ta tru di 1 toan tu va 1 so hang
                //xoa so sau
                for(int j=i+1;j<dau+2;j++){
                    b[j]=b[j+1];
                }
                //xoa toan tu

                    a=Xoakytu(a,i);

                i--;
                dau--;
            }
        }
        return b[0];
    }

    public String Xoakytu(String s,int pos){
        return s.substring(0,pos)+s.substring(pos+1);
    }
    public  void onClickCalculate(View view){
        //Tính toán here

        try {
//            screen.setText(String.valueOf(Calculate(equation)) );
//            screen.setText(Calculate(equation)+"");
            //nhap so cuoi cung vao mang so

            if(Landau==true)
           {  So[dau]=Double.parseDouble(demo);
                dau--;
                screen.setText(""+TinhToan(Toantu,So));
            }


           // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            numText = "";
            //Toast.makeText(this, "ToanTu"+Toantu[0], Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
