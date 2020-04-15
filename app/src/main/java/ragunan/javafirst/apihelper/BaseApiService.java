package ragunan.javafirst.apihelper;



import okhttp3.ResponseBody;
import ragunan.javafirst.ui.Front.Register.Model.RegisterModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApiService {
   @POST("user/insert_customer")
    Call<ResponseBody> RegisterCustomer(@Header("Content-Type") String contentType,
                                      @Body RegisterModel  registerModel);




}
