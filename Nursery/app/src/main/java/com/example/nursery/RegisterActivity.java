package com.example.nursery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;
import com.example.nursery.constants.Urls;
import com.example.nursery.network.ImageRequester;
import com.example.nursery.network.account.AccountService;
import com.example.nursery.network.account.dto.LoginDTO;
import com.example.nursery.network.account.dto.LoginResultDTO;
import com.example.nursery.network.account.dto.RegisterDTO;
import com.example.nursery.network.account.dto.ValidationRegisterDTO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ImageRequester imageRequester;
    private NetworkImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String url = Urls.BASE_URL+"/images/1.jpg";
        //String url = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg?resize=750px:*";
        imageRequester = ImageRequester.getInstance();
        myImage = findViewById(R.id.myImage);
        imageRequester.setImageFromUrl(myImage, url);
    }

    public void onClickRegister(View view) {
        final TextInputLayout displayNameLayout = findViewById(R.id.inputLayoutDisplayName);
        final TextInputEditText displayName = findViewById(R.id.textFieldDisplayName);

        final TextInputLayout emailLayout = findViewById(R.id.inputLayoutEmail);
        final TextInputEditText email = findViewById(R.id.textFieldEmail);

        final TextInputLayout emailPassword = findViewById(R.id.inputLayoutPassword);
        final TextInputEditText password = findViewById(R.id.textFieldPassword);
        //Log.d("clickLogin", email.getText().toString());
        //emailLayout.setError("У нас проблеми");
        RegisterDTO model = new RegisterDTO(
                email.getText().toString(),
                password.getText().toString(),
                displayName.getText().toString()
        );
        AccountService.getInstance()
                .getJSONApi()
                .register(model)
                .enqueue(new Callback<LoginResultDTO>() {
                    @Override
                    public void onResponse(Call<LoginResultDTO> call, Response<LoginResultDTO> response) {
                        if(response.isSuccessful()) {
                            LoginResultDTO result = response.body();
                            Log.d("Good Request", result.getToken());
                        }
                        else
                        {
                            try {
                                String json = response.errorBody().string();
                                Gson gson = new Gson();
                                ValidationRegisterDTO result = gson.fromJson(json, ValidationRegisterDTO.class);
                                //JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
                                //JsonArray jsonArray = result.getAsJsonArray("servers");
                                Log.d("Bad request: ", json);
                            } catch (Exception ex) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResultDTO> call, Throwable t) {

                    }
                });
    }
}