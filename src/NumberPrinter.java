//import java.io.*;
//import java.math.*;
//import java.security.*;
//import java.text.*;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.function.*;
//import java.util.regex.*;
//import java.util.stream.*;
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//import java.net.*;
//import org.json.simple.*;
//import org.json.simple.parser.*;
//
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//
//class Result {
//    class UserRating{
//        float average_rating;
//        int votes;
//    }
//
//    class Data{
//        String city;
//        String name;
//        int estimated_cost;
//        UserRating userRating;
//        int id;
//    }
//
//    class WeatherData{
//        int page;
//        int per_page;
//        int total;
//        int total_pages;
//        List<Data> data;
//    }
//
//    public static List<String> getRelevantFoodOutlets(String city, int maxCost)
//    {
//        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=Denver&page=1";
//
//        try {
//            URL url = new URL(apiUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//            Gson gson = new Gson();
//            FoodOutletResponse foodOutletResponse = gson.fromJson(response.toString(), FoodOutletResponse.class);
//
//            List<WeatherData> foodOutlets = foodOutletResponse.data;
//            for (WeatherData outlet : foodOutlets) {
//                System.out.println(new Gson().toJson(outlet));
//            }
//            connection.disconnect();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//
//    static class FoodOutletResponse {
//        @SerializedName("data")
//        List<WeatherData> data;
//    }
//
//}
//
//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String city = bufferedReader.readLine();
//
//        int maxCost = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> result = Result.getRelevantFoodOutlets(city, maxCost);
//
//        for (int i = 0; i < result.size(); i++) {
//            bufferedWriter.write(result.get(i));
//
//            if (i != result.size() - 1) {
//                bufferedWriter.write("\n");
//            }
//        }
//
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}
