package com.example.m0117059;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.m0117059.databinding.ActivityMainBinding;

//                      krn ingin membuat Activity sebagai listener, implements
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private Button btnAction;
//    private EditText edText;
//    private TextView tvShowResult;
    //gaperlu krn ada di binding

    // buat kelas si binding buatan java
    private ActivityMainBinding binding; //sesuai nama layoutmu activy_main.xml => ActivityMain + Binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set content itu ada 2 mcm, ada pake id ada pake view

        // skrng inflate si binding kita, tp pake setcontent view baru
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater()); //nge inflate

        View layout = binding.getRoot();
        this.setContentView(layout);

//        binding.btnAction.setOnClickblabla kl mau btn_action (sesuai nama id dr layout kita)
//        binding.etEdit.setText("blabla"); kl mau et_Edit
//        binding.tvShowResult kl mau tv_show_result

//      krn pake binding skrng, gaperlu findviewbyid satusatu lagi
//        this.btnAction = this.findViewById(R.id.btn_action);
//        this.edText = this.findViewById(R.id.et_edit);
//        this.tvShowResult = this.findViewById(R.id.tv_show_result);

//  Menurut pengamatan anda, apa yang dilakukan fungsi findViewById ?
//  = mencari view yg ingin direfer dari id nya. di file R
//  Mengapa sebaiknya kita perlu meminimalkan pemanggilan fungsi findViewById?
//  =

        //cara #1 pake anon class
        //1. anonymous class  (sprt Callback?)
//        this.btnAction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("debug", "clicked!"); //biar kasi pesan ke logcat, dgn type tag debug..  example.projectname D/debug: clicked!
//            }
//        });

// Perubahan apa yang harus dilakukan pada setOnClickListener bila kita menggunakan Activity kita sebagai listener?
// = mengganti isi setOnclicklistener dari memakai anonymous class membuat objek lagi dsb, menjadi merujuk ke kelas/objek ini lgsng, krn sudah ada onClick juga.. (krn implements onclicklistener)
        // cara pake si activity sebagai listener

        //this.btnAction.setOnClickListener(this); // dia akan lgsg tau this itu (objek saat ini) akan punya onClick.. krn implements si View.onclickblabla tadi
        //pake binding jd...
        binding.btnAction.setOnClickListener(this);

        // this.btnAction.setOnClickListener(this);
        // this.btnAction2.setOnClickListener(this); misal ada btn ke2 yg manggil, bisa binggung cara bedainnya.. jd gunain param View, biar tau view yg mana lg manggil method itu
        // dgn cara, salah satunya buar atribut private button btnAction dan 2 dulu.. dan di cocokin di if( view == this.btnAction) bla bla
    }


    @Override   //  param view ini, berfungsi sbg penentu, nanti kalo ada multi tombol biar tau bedainnya..
    public void onClick(View view) {
        Log.d("debug", "clicked!");
//        if(view == this.btnAction) {
//            this.changeText();
//        }

        //dengan binding
        if(view == this.binding.btnAction) {    //                                                  knp harus pake this. ?
            this.changeText();
        }
    }

    private void changeText() {
        //normal
        //this.tvShowResult.setText("Text :" + this.edText.getText());

        // challenge, pake binding
        binding.tvShowResult.setText("Text : " + binding.etEdit.getText());//                       perlu pake this. jgk?

        //challenge, tp yg muncul default hint nya
        //this.tvShowResult.setText("Text :" + getResources().getString(R.string.edtext_hint));
        //binding.tvShowResult.setText("Text :" + getResources().getString(R.string.edtext_hint));
    }
}