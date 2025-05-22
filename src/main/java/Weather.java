import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import weatherresponse.WeatherResponse;

public class Weather {
    public static void main(String[] args) {
        String apiKey="3ded9ce118161964953ca399889a5d1f";
        System.out.println("어떤 도시의 날씨가 궁금하신가요?");
        Scanner scanner=new Scanner(System.in);
        String city=scanner.nextLine();


        String urlString=String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                city, apiKey
        );

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println("날씨 정보!");

                Gson gson = new Gson();
                WeatherResponse result=gson.fromJson(response.toString(), WeatherResponse.class);

                System.out.println("도시: "+result.name);
                System.out.println("날씨: "+result.weather.get(0).description);
                System.out.println("기온: "+result.main.temp+"도");
            } else {
                System.out.println("API 요청 실패. 응답 코드: " + responseCode);
            }
        }catch(Exception e){
            System.out.println("오류 발생: "+e.getMessage());
        }
    }
}
