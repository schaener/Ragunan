package ragunan.javafirst.ui.Front.Register.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import ragunan.javafirst.R;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.apihelper.BaseApiService;
import ragunan.javafirst.apihelper.UtilsApi;
import ragunan.javafirst.ui.Front.Register.Model.RegisterModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroRegister extends AppCompatActivity {
    BaseApiService mApiService;
    ProgressDialog pDialog;
  DatePickerDialog datePickerDialog;
     SimpleDateFormat dateFormatter;
    Calendar newDate;
    ScriptHelper script_helper = new ScriptHelper();
    Calendar Ctgl_lahir;
    CardView register;
    Spinner jenis_kelamin;
    String genderValues = "1";
    EditText nama,email,alamat,no_hp,tgl_lahir,password;
    ArrayAdapter<String> adapter;
    String tgl_lahirstrValue="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_register);
        mApiService = UtilsApi.getAPIService();

        nama = findViewById(R.id.ETname);
        email = findViewById(R.id.ETemail);
        alamat = findViewById(R.id.ETalamat);
        jenis_kelamin = findViewById(R.id.ETjeniskelamin);
        no_hp = findViewById(R.id.ETnohp);
        tgl_lahir = findViewById(R.id.ETtanggallahir);
        register = findViewById(R.id.Bregister);
        password = findViewById(R.id.ETpassword);
        dateFormatter = new SimpleDateFormat( "dd/MM/yyyy");
        String[] Gender = new String[]{"Laki - Laki", "Perempuan"};

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.dropdown_gender_item,
                Gender);
        jenis_kelamin.setAdapter(adapter);
        jenis_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (jenis_kelamin.getSelectedItem().toString().equals("Laki - Laki")){
                    genderValues="1";
                }
                if (jenis_kelamin.getSelectedItem().toString().equals("Perempuan")){
                    genderValues="2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();

                datePickerDialog = new DatePickerDialog(IntroRegister.this, AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);

                                /**
                                 * Update TextView dengan tanggal yang kita pilih
                                 */
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.YEAR, year);
                                c.set(Calendar.MONTH, monthOfYear);
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                String formatTanggal = "yyyy-MM-dd";
                                SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                               tgl_lahirstrValue=sdf.format(c.getTime());

//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-")
                                String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                                tgl_lahir.setText(currentDateString);
                            }

                        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                /**
                 * Tampilkan DatePicker dialog
                 */
                datePickerDialog.show();
            }

        });
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FormValidation();
    }
});

    }



    public void FormValidation(){
        if (nama.getText().toString().equals("")){
            nama.setError("Isikan Nama Terlebih Dahulu !");
        }
       else if (email.getText().toString().equals("")){
            email.setError("Isikan Email Terlebih Dahulu !");
        }
       else if (!script_helper.isValidateEmail(email.getText().toString())){
            email.setError("Email Tidak Valid !");

        }
       else if (alamat.getText().toString().equals("")){
           alamat.setError("Isikan Alamat Terlebih Dahulu !");
        }

        else if (no_hp.getText().toString().equals("")){
            no_hp.setError("Isikan Nomor HP Anda Terlebih Dahulu !");
        }
        else if (tgl_lahir.getText().toString().equals("")){
           tgl_lahir.setError("Isikan Tanggal Lahir Anda Terlebih Dahulu !");
        }
        else if (password.getText().toString().equals("")){
            tgl_lahir.setError("Isikan password Anda Terlebih Dahulu !");
        }
        else {
Regis();
        }
    }
    private void Regis() {
        pDialog = new ProgressDialog(IntroRegister.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading ...");
        showDialog();


        String namastr= nama.getText().toString();
        String emailstr = email.getText().toString();

        String alamatstr = alamat.getText().toString();

        String no_hpstr = no_hp.getText().toString();
         String tgl_lahirstr = tgl_lahir.getText().toString();
        String passwordstr = password.getText().toString();

        final RegisterModel registerModel = new RegisterModel(namastr,emailstr
                ,passwordstr,genderValues,"",tgl_lahirstrValue,alamatstr,no_hpstr);


        mApiService.RegisterCustomer("application/json", registerModel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        String status_code = jsonResult.getString("id_user_code");

                        if (status_code.equals("200")) {

                            hideDialog();
                            Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                     finish();
                        }

                        else {
                            hideDialog();


                            Toast.makeText(getApplicationContext(), "Email Telah Terdaftar", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "Email Telah Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hideDialog();
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
