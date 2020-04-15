package ragunan.javafirst.apihelper;

public class UtilsApi {

    public static final String BASE_URL_API = "https://liburania.javafirst.id/tmii/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }


}
